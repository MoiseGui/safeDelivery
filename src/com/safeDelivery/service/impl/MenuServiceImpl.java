package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.service.MenuService;
import com.safeDelivery.utils.SingletonConnexion;

public class MenuServiceImpl implements MenuService {
	private Connection conn;

	public MenuServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public long addMenu(long idPlat, long idResto) {
		int exist = exists(idPlat, idResto);
		if (exist != 1) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into menu  values (?,?)";
					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, idPlat);
					ps.setLong(2, idResto);

					int count = ps.executeUpdate();
					if (count == 1) {
						ResultSet rs = ps.getGeneratedKeys();
						if (rs.next()) {
							int rsgetint = rs.getInt(1);
							ps.close();
//							SingletonConnexion.closeConnection(conn);
							return rsgetint;
						} else {
							ps.close();
//							SingletonConnexion.closeConnection(conn);
							return -5;
						}
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return -2;
					}
				} else {
					return -2;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		} else {
			return -1;
		}
	}

	@Override
	public int exists(long idPlat, long idResto) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from menu where id_plat = ? and id_restaurant = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, idPlat);
				ps.setLong(2, idResto);

				ResultSet result = ps.executeQuery();
				result.next();
				int id = result.getInt(1);
				if (id == 1) {

					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return 1;

				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
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
	public int deleteMenu(long idPlat, long idResto) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "delete from menu where id_plat = ? and id_restaurant = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, idPlat);
				ps.setLong(2, idResto);
				int count = ps.executeUpdate();
				if (count > 0) {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
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
