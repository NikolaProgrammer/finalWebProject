package by.gsu.epamlab.interfaces;

import java.util.List;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.model.enums.Section;

public interface ITaskDAO {

	List<Task> getTasks(User user, Section section) throws DAOException;
	
	List<Task> getTasks(int[] taskIds) throws DAOException;
	
	void addTask(User user, Task task) throws DAOException;
	
	void executeOperation(int[] ids, Operation operation) throws DAOException;
	
	void clearRemovedTasks(User user) throws DAOException;
	
	void setUploadedFile(int taskId, String trueFileName, String systemFileName) throws DAOException;
	
	Task getTaskById(int taskId) throws DAOException;
	
	void deleteFile(int taskId) throws DAOException;
}
