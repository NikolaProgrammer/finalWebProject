package by.gsu.epamlab.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.exceptions.ConnectionException;
import by.gsu.epamlab.model.impl.database.ConstantsSQL;

public class ConnectionDB {

	public static Connection getConnection() throws ConnectionException{
		try {
			Class.forName(ConstantsSQL.DB_DRIVER_CLASS_NAME);
			Connection con = DriverManager.getConnection(ConstantsSQL.DB_URL, ConstantsSQL.DB_USER, ConstantsSQL.DB_PASSWORD);
			return con;
		} catch (ClassNotFoundException | SQLException e) {
			throw new ConnectionException(Constants.ERROR_CONNECTION_DB, e);
		}
	}
}
