package by.gsu.epamlab.model.impl.database;

public class ConstantsSQL {
	public static final String DB_DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/todolist";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";

	public static final String FIND_USER_BY_NAME = "SELECT count(idUser) FROM users WHERE name LIKE ? ";
	public static final String INSERT_USER = "INSERT INTO users (name, password) VALUES (?, ?)";
	public static final String SELECT_USER = "SELECT * FROM users WHERE name LIKE ?";

	public static final String SELECT_TASKS = "SELECT idTask, content, date, fileName, systemFileName FROM tasks WHERE ";
	public static final String SELECT_TASKS_TODAY = SELECT_TASKS
			+ "idUser = ?  AND date <= ? AND isFixed = 0 AND isRemoved = 0";
	public static final String SELECT_TASKS_TOMORROW = SELECT_TASKS
			+ "idUser = ?  AND date = ? AND isFixed = 0 AND isRemoved = 0";
	public static final String SELECT_TASKS_SOMEDAY = SELECT_TASKS
			+ "idUser = ?  AND date > ? AND isFixed = 0 AND isRemoved = 0";
	public static final String SELECT_TASKS_FIXED = SELECT_TASKS + "idUser = ? AND isFixed = 1 AND isRemoved = 0";
	public static final String SELECT_TASKS_RECYCLE_BIN = SELECT_TASKS + "idUser = ? AND isRemoved = 1";
	public static final String SELECT_TASK_BY_ID = "SELECT content, date, fileName, systemFileName FROM tasks WHERE idTask = ?";
	
	public static final String INSERT_TASK = "INSERT INTO tasks (idUser, content, date, isFixed, isRemoved) VALUES (?, ?, ?, '0', '0')";
	public static final String REMOVE_TASK = "UPDATE tasks SET isRemoved = 1 WHERE idTask = ?";
	public static final String FIX_TASK = "UPDATE tasks SET isFixed = 1 WHERE idTask = ?";
	public static final String RESTORE_TASK = "UPDATE tasks SET isRemoved = 0 WHERE idTask = ?";
	public static final String DELETE_TASK = "DELETE  FROM tasks WHERE idTask = ?";
	public static final String CLEAR_REMOVED_TASKS = "DELETE  FROM tasks WHERE idUser = ? AND isRemoved = 1";
	
	public static final String SET_UPLOADED_FILE = "UPDATE tasks SET fileName = ?, systemFileName = ? WHERE idTask = ?";
	public static final String DELETE_FILE = "UPDATE tasks SET fileName = NULL, systemFileName = NULL WHERE idTask = ?";
	
}
