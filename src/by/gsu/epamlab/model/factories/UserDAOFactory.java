package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.impl.database.DBUserImpl;
import by.gsu.epamlab.model.impl.ram.RamUserImpl;

public class UserDAOFactory {
	private static IUserDAO userDAO = null;

	public static void initDAO(String type) {
		TypeDAO dao = TypeDAO.valueOf(type);
		switch (dao) {
		case RAM:
			userDAO = new RamUserImpl();
			break;
		case DATABASE:
			userDAO = new DBUserImpl();
			break;
		}
	}

	public static IUserDAO getTypeDAO() {
		return userDAO;
	}
}
