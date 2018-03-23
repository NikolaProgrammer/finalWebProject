package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.interfaces.ITaskDAO;
import by.gsu.epamlab.model.impl.database.DBTaskImpl;

public class TaskDAOFactory {
	private static ITaskDAO taskDAO = null;

	public static void initDAO(String type) {
		TypeDAO typeDAO = TypeDAO.valueOf(type);
		switch (typeDAO) {
		case RAM:
			break;
		case DATABASE:
			taskDAO = new DBTaskImpl();
			break;
		}
	}

	public static ITaskDAO getTaskDAO() {
		return taskDAO;
	}
	
}
