package by.gsu.epamlab.model.beans;

import java.util.Arrays;

public class FilePatameters {
	
	private String filename;
	private String taskIdStr;
	private byte[] byteSequence;
	
	public FilePatameters() {
		super();
	}

	public FilePatameters(String filename, byte[] byteSequence, String taskIdStr) {
		this.filename = filename;
		this.byteSequence = byteSequence;
		this.taskIdStr = taskIdStr;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getByteSequence() {
		return byteSequence;
	}

	public void setByteSequence(byte[] byteSequence) {
		this.byteSequence = byteSequence;
	}

	public String getTaskIdStr() {
		return taskIdStr;
	}

	public void setTaskIdStr(String taskIdStr) {
		this.taskIdStr = taskIdStr;
	}

	@Override
	public String toString() {
		return "FilePatameters [filename=" + filename + ", taskIdStr=" + taskIdStr + ", byteSequence="
				+ Arrays.toString(byteSequence) + "]";
	}
	
	
}
