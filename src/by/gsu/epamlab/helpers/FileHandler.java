package by.gsu.epamlab.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.exceptions.CannotDeleteFileException;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.model.beans.FilePatameters;

public class FileHandler {

	private final static String FILE_PATH = "E:" + File.separator + "epam" + File.separator + "tasks" + File.separator
			+ "web1" + File.separator + "files" + File.separator;

	public static FilePatameters getFileParameters(HttpServletRequest request) throws IOException {
		final int LINE_LENGTH = 128;
		final String HEADER_PATTERN = "Content-Disposition: form-data; name=\"";
		final String FILENAME_PATTERN = "filename=\"";

		FilePatameters params = new FilePatameters();
		try (ServletInputStream in = request.getInputStream()) {

			byte[] line = new byte[LINE_LENGTH];
			int read = in.readLine(line, 0, LINE_LENGTH);
			int boundaryLength = read - 2;
			String boundary = new String(line, 0, boundaryLength);

			while (read != -1) {
				String newLine = new String(line, 0, read);

				if (newLine.startsWith(HEADER_PATTERN)) {

					int pos = newLine.indexOf(ConstantsJSP.KEY_HIDDEN_TASK_ID);
					if (pos != -1) {
						read = in.readLine(line, 0, LINE_LENGTH);
						read = in.readLine(line, 0, LINE_LENGTH);
						String taskId = new String(line, 0, read - 2);
						params.setTaskIdStr(taskId);
						continue;
					}

					String string = new String(line, 0, read - 2);
					pos = string.indexOf(FILENAME_PATTERN);
					if (pos != -1) {
						String filePath = string.substring(pos + 10, string.length() - 1);
						pos = filePath.indexOf("\\");
						String fileName = (pos != -1) ? filePath.substring(pos + 1) : filePath;
						if (fileName.equals(Constants.EMPTY_LINE)) {
							read = in.readLine(line, 0, LINE_LENGTH);
							continue;
						}
						params.setFilename(fileName);
					}

					read = in.readLine(line, 0, LINE_LENGTH);
					read = in.readLine(line, 0, LINE_LENGTH);
					read = in.readLine(line, 0, LINE_LENGTH);

					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					newLine = new String(line, 0, read);

					while (read != -1 && !newLine.startsWith(boundary)) {
						buffer.write(line, 0, read);

						read = in.readLine(line, 0, LINE_LENGTH);
						newLine = new String(line, 0, read);
					}
					params.setByteSequence(buffer.toByteArray());
				}
				read = in.readLine(line, 0, LINE_LENGTH);
			}
			return params;
		}
	}

	public static String generateSystemName(int taskId, String fileName) {
		final String DELIMETER = "@";
		return taskId + DELIMETER + fileName;
	}

	public static void uploadFile(String fileName, byte[] buffer) throws DAOException {
		try {
			RandomAccessFile file = new RandomAccessFile(FILE_PATH + fileName, "rw");
			file.write(buffer);
			file.close();
		} catch (IOException e) {
			throw new DAOException(Constants.ERROR_UPLOAD_FILE + e.getMessage());
		}
	}

	public static void downloadFile(String trueFileName, String systemFileName, HttpServletResponse response)
			throws DAOException {
		final String CONTENT_TYPE = "APPLICATION/OCTET-STREAM";
		final String HEADER_NAME = "Content-Disposition";
		final String HEADER_VALUE = "attachment; filename=\"" + trueFileName + "\"";

		try (InputStream in = new BufferedInputStream(new FileInputStream(new File(FILE_PATH + systemFileName)));
				OutputStream out = new BufferedOutputStream(response.getOutputStream())) {
			
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			int read;
			while ((read = in.read()) != -1) {
				buffer.write(read);
			}
			
			response.setContentType(CONTENT_TYPE);
			response.setHeader(HEADER_NAME, HEADER_VALUE);
			out.write(buffer.toByteArray());

		} catch (IOException e) {
			throw new DAOException(Constants.ERROR_DOWNLOAD_FILE + e.getMessage());
		}
	}
	
	public static void deleteFile(String systemFileName, String trueFileName) throws CannotDeleteFileException {
		File file = new File(FILE_PATH + systemFileName);
		if (!file.delete()) {
			throw new CannotDeleteFileException(String.format(Constants.ERROR_DELETE_FILE, trueFileName));
		}
	}
}
