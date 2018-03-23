package by.gsu.epamlab.model.impl.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.ConnectionException;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.NoSuchUserException;
import by.gsu.epamlab.exceptions.UserAlreadyExistException;
import by.gsu.epamlab.helpers.ConnectionDB;
import by.gsu.epamlab.interfaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;

public class DBUserImpl implements IUserDAO {

	@Override
	public User getUser(String name, String password) throws DAOException {
		final int NAME_SELECT_INDEX = 1;

		final int ID_INDEX = 1;
		final int NAME_INDEX = 2;
		final int PASSWORD_INDEX = 3;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement prStatement = con.prepareStatement(ConstantsSQL.SELECT_USER)) {

			prStatement.setString(NAME_SELECT_INDEX, name);
			try (ResultSet resultSet = prStatement.executeQuery()) {
				if (!resultSet.next()) {
					throw new NoSuchUserException(Constants.ERROR_NO_SUCH_USER);
				}

				int userId = resultSet.getInt(ID_INDEX);
				String userName = resultSet.getString(NAME_INDEX);
				String userPassword = resultSet.getString(PASSWORD_INDEX);
				if (!password.equals(userPassword)) {
					throw new NoSuchUserException(Constants.ERROR_NO_SUCH_USER);
				}

				User user = new User(userId, userName);
				return user;
			}
		} catch (SQLException | ConnectionException e) {
			throw new DAOException(Constants.ERROR_AUTHORIZATION, e);
		}
	}

	@Override
	public void addUser(String name, String password) throws DAOException {
		final int NAME_INDEX = 1;
		final int COUNT_INDEX = 1;
		final int PASSWORD_INDEX = 2;

		try (Connection con = ConnectionDB.getConnection();
				PreparedStatement insertUserStatement = con.prepareStatement(ConstantsSQL.INSERT_USER);
				PreparedStatement findUserStatement = con.prepareStatement(ConstantsSQL.FIND_USER_BY_NAME)) {

			findUserStatement.setString(NAME_INDEX, name);
			insertUserStatement.setString(NAME_INDEX, name);
			insertUserStatement.setString(PASSWORD_INDEX, password);

			synchronized (DBUserImpl.class) {
				try (ResultSet resultSet = findUserStatement.executeQuery();) {
					resultSet.next();
					int countIndex = resultSet.getInt(COUNT_INDEX);
					if (countIndex != 0) {
						throw new UserAlreadyExistException(Constants.ERROR_USER_ALREADY_EXIST);
					}
				}
				insertUserStatement.executeUpdate();
			}
		} catch (SQLException | ConnectionException e) {
			throw new DAOException(Constants.ERROR_INSERT_USER, e);
		}
	}
}
