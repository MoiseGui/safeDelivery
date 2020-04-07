package com.safeDelivery.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public final class SingletonConnexion {

	public static Connection startConnection() {
		try {
//			"jdbc:mysql://81.16.28.154/u917243327_safeDelivery";
			String url = "jdbc:mysql://localhost:3306/safedelivery";
			String uname = "root";
			String pass = "";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, uname, pass);
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
