package by.gsu.epamlab.model.beans;

import java.sql.Date;

import by.gsu.epamlab.Constants;

public class Task {
	private final static int UNKNOWN_ID = -1;
	
	private int id;
	private String content;
	private Date date;
	private String trueFileName;
	private String systemFileName;
	
	public Task(int id, String content, Date date, String trueFileName, String systemFileName) {
		this.id = id;
		this.content = content;
		this.date = date;
		this.trueFileName = trueFileName;
		this.systemFileName = systemFileName;
	}

	public Task(String content, Date date) {
		this(UNKNOWN_ID, content, date, Constants.EMPTY_LINE, Constants.EMPTY_LINE);
	}
	
	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public String getTrueFileName() {
		return trueFileName;
	}

	public String getSystemFileName() {
		return systemFileName;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", content=" + content + ", date=" + date + ", trueFileName=" + trueFileName
				+ ", systemFileName=" + systemFileName + "]";
	}

	
}
