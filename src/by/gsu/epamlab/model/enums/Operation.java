package by.gsu.epamlab.model.enums;

import java.util.List;

import by.gsu.epamlab.exceptions.CannotDeleteFileException;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.SkipOperationExeption;
import by.gsu.epamlab.helpers.FileHandler;
import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.impl.database.ConstantsSQL;

public enum Operation {
	REMOVE("Remove", ConstantsSQL.REMOVE_TASK), 
	FIX("Fix", ConstantsSQL.FIX_TASK), 
	RESTORE("Restore",ConstantsSQL.RESTORE_TASK), 
	DELETE("Delete", ConstantsSQL.DELETE_TASK) {

				@Override
				public void execute(int[] taskIds, User user, ITaskDAO dao)
						throws DAOException, CannotDeleteFileException {

					List<Task> tasks = dao.getTasks(taskIds);
					for (Task task : tasks) {
						if (task.getSystemFileName() != null) {
							FileHandler.deleteFile(task.getSystemFileName(), task.getTrueFileName());
						}
					}
					
					dao.executeOperation(taskIds, this);
				}

			},
	ADD("Add task", ConstantsSQL.INSERT_TASK) {

		@Override
		public void execute(int[] taskIds, User user, ITaskDAO dao) throws SkipOperationExeption {
			throw new SkipOperationExeption();
		}

	},
	DELETE_ALL("Delete all", ConstantsSQL.CLEAR_REMOVED_TASKS) {

		@Override
		public void execute(int[] taskIds, User user, ITaskDAO dao) throws DAOException, CannotDeleteFileException {
			List<Task> tasks = dao.getTasks(user, Section.RECYCLE_BIN);
			for (Task task : tasks) {
				if (task.getSystemFileName() != null) {
					FileHandler.deleteFile(task.getSystemFileName(), task.getTrueFileName());
				}
			}
			
			dao.clearRemovedTasks(user);
		}

	};

	private String name;
	private String query;

	private Operation(String name, String query) {
		this.name = name;
		this.query = query;
	}

	public String getName() {
		return name;
	}

	public String getQuery() {
		return query;
	}

	public void execute(int[] taskIds, User user, ITaskDAO dao)
			throws DAOException, SkipOperationExeption, CannotDeleteFileException {
		dao.executeOperation(taskIds, this);
	}

	public static Operation convertFromString(String operationName) {
		Operation operation = null;

		for (Operation item : values()) {
			if (item.name.equals(operationName)) {
				operation = item;
			}
		}

		return operation;
	}
}
