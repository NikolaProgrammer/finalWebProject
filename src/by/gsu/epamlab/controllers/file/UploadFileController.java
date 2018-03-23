package by.gsu.epamlab.controllers.file;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.helpers.FileHandler;
import by.gsu.epamlab.helpers.Validator;
import by.gsu.epamlab.interfaces.AbstractController;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.FilePatameters;
import by.gsu.epamlab.model.factories.TaskDAOFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UploadFileController extends AbstractController {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void performTask(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FilePatameters params = FileHandler.getFileParameters(request);
		
		try {
			int taskId = Validator.checkTaskId(params.getTaskIdStr());
			String systemFileName = FileHandler.generateSystemName(taskId, params.getFilename());
			FileHandler.uploadFile(systemFileName, params.getByteSequence());
			ITaskDAO taskDAO = TaskDAOFactory.getTaskDAO();
			taskDAO.setUploadedFile(taskId, params.getFilename(), systemFileName);
			response.sendRedirect(Constants.PREFIX + Constants.TASKS_CONTROLLER);
		} catch (ValidationException | DAOException e) {
			forwardError(e.getMessage(), request, response);
		}
	}


}
