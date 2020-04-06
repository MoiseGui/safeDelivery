package com.safeDelivery.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public final class SingletonConnexion {
	public static Connection connection = null;

	static {
		try {
			String url = "jdbc:mysql://81.16.28.154/u917243327_safeDelivery";
			String uname = "u917243327_irisi";
			String pass = "irisi";

			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, uname, pass);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
}
