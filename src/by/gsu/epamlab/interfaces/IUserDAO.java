package by.gsu.epamlab.interfaces;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.model.beans.User;

public interface IUserDAO {
	User getUser(String name, String password) throws DAOException;
	
	void addUser(String name, String password) throws DAOException;
}
