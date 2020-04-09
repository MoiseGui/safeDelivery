package com.safeDelivery.service.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.model.User;
import com.safeDelivery.service.UserService;
import com.safeDelivery.utils.SingletonConnexion;
import com.safeDelivery.utils.saltHashPassword;

public class UserServiceImpl implements UserService {

	@Override
	public long existByEmailAndPass(String email, String pass) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from user where email = ? and pass = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				try {
					ps.setString(2, saltHashPassword.generateHash(pass));
				} catch (Exception e) {
					e.printStackTrace();
					return -4;
				}
				ResultSet result = ps.executeQuery();
				result.next();
				if (result.getInt(1) == 1) {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return -1;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}

	@Override
	public User getUserByEmail(String email) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from user where email = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					User user = new User(result.getLong(1), result.getString(2), result.getString(3),
							result.getString(4), result.getString(5), result.getString(6), result.getInt(7),
							result.getInt(8));
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return user;
				} else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return null;
				}

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long addUser(User user) {

		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				User user1 = getUserByEmail(user.getEmail());
				if (user1 == null) {
					String query = "insert into user (nom,prenom,email,pass,tel,categorie,enable) values (?,?,?,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, user.getNom());
					ps.setString(2, user.getPrenom());
					ps.setString(3, user.getEmail());
					try {
						ps.setString(4, saltHashPassword.generateHash(user.getPass()));
					} catch (NoSuchAlgorithmException e) {
						return -3;
					}
					ps.setString(5, user.getTel());
					ps.setInt(6, user.getCategorie());
					ps.setInt(7, user.getEnable());
					int count = ps.executeUpdate();
					if (count > 0) {

						ResultSet rs = ps.getGeneratedKeys();
						if (rs.next()) {
							long id = rs.getInt(1);
							ps.close();
							SingletonConnexion.closeConnection(conn);
							return id;
						} else {
							return -4;
						}
					} else {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return -3;
					}
				} else {
					return -2;
				}
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -5;
		}
	}

	@Override
	public int disableUserByEmail(String email) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "update user set enable = 1 where email=? ";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				int count = ps.executeUpdate();
				if (count > 0) {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return -1;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}

	}

	@Override
	public int deleteUserByEmail(String email) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "delete from user where email = ? ";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, email);
				int count = ps.executeUpdate();
				if (count > 0) {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return -1;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}

	}

}
