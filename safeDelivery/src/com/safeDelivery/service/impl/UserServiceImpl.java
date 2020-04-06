package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeDelivery.model.User;
import com.safeDelivery.service.UserService;
import com.safeDelivery.utils.SingletonConnexion;

public class UserServiceImpl implements UserService {

	@Override
	public int existByEmailAndPass(String email, String pass) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if(conn != null) {
				String query = "select count(*) from user where email = ? and pass = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				ps.setString(2, pass);
				ResultSet result = ps.executeQuery();
				result.next();
				
				if(result.getInt(1) == 1) {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return 1;
				}
				else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return -1;
				}
			}
			else {
				return -2;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if(conn != null) {
				String query = "select * from user where email = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				ResultSet result = ps.executeQuery();
				if(result.next()) {
					User user = new User(result.getLong(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6));
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return user;
				}
				else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					System.out.println("1 ");
					return null;
				}
				
			}
			else {
				System.out.println("2");
				return null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int disableUserByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteUserByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

}
