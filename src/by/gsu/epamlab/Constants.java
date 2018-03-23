package by.gsu.epamlab;

public class Constants {
	public static final String EMPTY_LINE = "";
	
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String USER_DAO_TYPE = "userTypeDao";
	public static final String TASK_DAO_TYPE = "taskTypeDao";
	
	public static final String PREFIX = "/web1";
	public static final String LOGIN_PAGE = "/login.jsp";
	public static final String REGISTRATION_PAGE = "/reg.jsp";
	public static final String MAIN_PAGE = "/index.jsp";
	public static final String ERROR_PAGE = "/error.jsp";
	public static final String TASKS_PAGE = "/tasks.jsp";
	public static final String ADD_TASKS_PAGE = "/addTask.jsp";
	
	public static final String TASKS_CONTROLLER = "/tasks";
	
	public static final String ERROR_NO_SUCH_USER = "such a user does not exist";
	public static final String ERROR_USER_ALREADY_EXIST = "User with this name already exist";
	public static final String ERROR_USER_OR_PASSWORD_ABSENT = "Login or password are absent";
	public static final String ERROR_EMPTY_PARAMETERS = "The %s is empty";
	public static final String ERROR_INSERT_USER = "Cannot insert new user";
	public static final String ERROR_AUTHORIZATION = "Error during authorization";
	
	public static final String ERROR_CONNECTION_DB = "Database connection error";
	public static final String ERROR_CLOSING_RESOURCE = "Cannot close connection";
	
	public static final String ERROR_GET_TASKS = "Cannot get tasks: ";
	public static final String ERROR_GET_TASK = "Task with id = %d can not be obtained ";
	public static final String ERROR_EMPTY_TASK_CONTENT = "Task content is absent";
	public static final String ERROR_EMPTY_TASK_DATE = "Task date is absent";
	public static final String ERROR_INCORRECT_TASK_DATE = "Task date has incorrect format";
	public static final String ERROR_INSERT_TASK = "Error associated with the addition of task";
	public static final String ERROR_TASK_OPERATION = "Error during executing operation: %s";
	public static final String ERROR_CLEAR_REMOVED_TAKS = "Error while clearing removed tasks";
	
	public static final String ERROR_EMPTY_TASK_ID = "Task ID is absent";
	
	public static final String ERROR_UPLOAD_FILE = "Upload file error: ";
	public static final String ERROR_DOWNLOAD_FILE = "Download file error: ";
	public static final String ERROR_SET_FILE = "Set file error. Cannot attach file.";
	public static final String ERROR_DELETE_FILE = "File with name %s cannot be deleted";
	public static final String ERROR_DELETE_FILE_DB = "Remove file error: ";
	
}
