package com.safeDelivery.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public final class SingletonConnexion {
	public static Connection connection = null;
	

	public static Connection startConnection() {
	try {		
		String url = "jdbc:mysql://localhost/u917243327_safeDelivery";
		String uname = "root";
		String pass = "";
		Class.forName("com.mysql.cj.jdbc.Driver");
		if(SingletonConnexion.connection == null || SingletonConnexion.connection.isClosed()) {
			SingletonConnexion.connection  = DriverManager.getConnection(url, uname, pass);
		}
		SingletonConnexion.connection  = DriverManager.getConnection(url, uname, pass);
		return connection;

	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
}

	public static int closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				return 1;
			} else
				return -1;

		} catch (Exception e) {
			return -2;
		}
	}

}
