package by.gsu.epamlab.model.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.ConnectionException;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.helpers.ConnectionDB;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.enums.Operation;
import by.gsu.epamlab.model.enums.Section;

public class DBTaskImpl implements ITaskDAO {

	@Override
	public List<Task> getTasks(User user, Section section) throws DAOException {
		final int ID_INDEX = 1;
		final int CONTENT_INDEX = 2;
		final int DATE_INDEX = 3;
		final int FILE_NAME_INDEX = 4;
		final int SYSTEM_FILE_NAME_INDEX = 5;

		List<Task> tasks = new ArrayList<>();

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(section.getQuery())) {

			preparedStatement.setInt(ID_INDEX, user.getId());
			section.setQuery(preparedStatement);

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					tasks.add(new Task(resultSet.getInt(ID_INDEX), resultSet.getString(CONTENT_INDEX),
							resultSet.getDate(DATE_INDEX), resultSet.getString(FILE_NAME_INDEX),
							resultSet.getString(SYSTEM_FILE_NAME_INDEX)));
				}
			}

			return tasks;
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(Constants.ERROR_GET_TASKS + e.getMessage());
		}
	}

	@Override
	public List<Task> getTasks(int[] taskIds) throws DAOException {
		final int TASK_ID_INDEX = 1;
		final int CONTENT_INDEX = 1;
		final int DATE_INDEX = 2;
		final int FILE_NAME_INDEX = 3;
		final int SYSTEM_FILE_NAME_INDEX = 4;

		List<Task> tasks = new ArrayList<>();
		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.SELECT_TASK_BY_ID)) {

			for (int taskId : taskIds) {
				preparedStatement.setInt(TASK_ID_INDEX, taskId);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						tasks.add(new Task(taskId, resultSet.getString(CONTENT_INDEX), resultSet.getDate(DATE_INDEX),
								resultSet.getString(FILE_NAME_INDEX), resultSet.getString(SYSTEM_FILE_NAME_INDEX)));
					}
				}
			}

			return tasks;
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(Constants.ERROR_GET_TASKS + e.getMessage());
		}
	}

	@Override
	public Task getTaskById(int taskId) throws DAOException {
		final int TASK_ID_INDEX = 1;
		final int CONTENT_INDEX = 1;
		final int DATE_INDEX = 2;
		final int FILE_NAME_INDEX = 3;
		final int SYSTEM_FILE_NAME_INDEX = 4;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.SELECT_TASK_BY_ID)) {

			preparedStatement.setInt(TASK_ID_INDEX, taskId);

			Task task = null;
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					task = new Task(taskId, resultSet.getString(CONTENT_INDEX), resultSet.getDate(DATE_INDEX),
							resultSet.getString(FILE_NAME_INDEX), resultSet.getString(SYSTEM_FILE_NAME_INDEX));
				}
			}

			return task;
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(String.format(Constants.ERROR_GET_TASK, taskId));
		}
	}

	@Override
	public void addTask(User user, Task task) throws DAOException {
		final int USER_ID_INDEX = 1;
		final int CONTENT_INDEX = 2;
		final int DATE_INDEX = 3;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.INSERT_TASK)) {

			preparedStatement.setInt(USER_ID_INDEX, user.getId());
			preparedStatement.setString(CONTENT_INDEX, task.getContent());
			preparedStatement.setDate(DATE_INDEX, task.getDate());
			preparedStatement.executeUpdate();
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(Constants.ERROR_INSERT_TASK);
		}
	}

	@Override
	public void executeOperation(int[] ids, Operation operation) throws DAOException {
		final int TASK_ID_INDEX = 1;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(operation.getQuery())) {

			for (int id : ids) {
				preparedStatement.setInt(TASK_ID_INDEX, id);
				preparedStatement.executeUpdate();
			}
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(String.format(Constants.ERROR_TASK_OPERATION, operation.getName()));
		}
	}

	@Override
	public void clearRemovedTasks(User user) throws DAOException {
		final int USER_ID_INDEX = 1;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.CLEAR_REMOVED_TASKS)) {

			preparedStatement.setInt(USER_ID_INDEX, user.getId());
			preparedStatement.executeUpdate();
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(Constants.ERROR_CLEAR_REMOVED_TAKS);
		}
	}

	@Override
	public void setUploadedFile(int taskId, String trueFileName, String systemFileName) throws DAOException {
		final int FILE_NAME_INDEX = 1;
		final int SYSTEM_FILE_NAME_INDEX = 2;
		final int TASK_ID_INDEX = 3;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.SET_UPLOADED_FILE)) {

			preparedStatement.setString(FILE_NAME_INDEX, trueFileName);
			preparedStatement.setString(SYSTEM_FILE_NAME_INDEX, systemFileName);
			preparedStatement.setInt(TASK_ID_INDEX, taskId);
			preparedStatement.executeUpdate();
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(Constants.ERROR_SET_FILE);
		}
	}

	@Override
	public void deleteFile(int taskId) throws DAOException {
		final int TASK_ID_INDEX = 1;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(ConstantsSQL.DELETE_FILE)) {

			preparedStatement.setInt(TASK_ID_INDEX, taskId);
			preparedStatement.executeUpdate();
		} catch (ConnectionException | SQLException e) {
			throw new DAOException(Constants.ERROR_DELETE_FILE_DB + e.getMessage());
		}
	}

}
