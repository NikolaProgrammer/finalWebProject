package by.gsu.epamlab.model.impl.ram;

import java.util.HashMap;
import java.util.Map;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.NoSuchUserException;
import by.gsu.epamlab.exceptions.UserAlreadyExistException;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;

public class RamUserImpl implements IUserDAO {

	public static Map<User, String> users = new HashMap<User, String>();
	private static int id = 0;

	@Override
	public User getUser(String name, String password) throws DAOException {
		User user = new User(name);

		if (!(users.containsKey(user)) || !(users.get(user).equals(password))) {
			throw new NoSuchUserException(Constants.ERROR_NO_SUCH_USER);
		}

		return user;
	}

	@Override
	public void addUser(String name, String password) throws UserAlreadyExistException {
		User user = new User(name);
		
		synchronized (RamUserImpl.class) {
			if (users.containsKey(user)) {
				throw new UserAlreadyExistException(Constants.ERROR_USER_ALREADY_EXIST);
			}

			user = new User(++id, name);
			users.put(user, password);
		}
	}

}
