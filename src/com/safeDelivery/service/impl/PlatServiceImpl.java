package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.model.Plat;
import com.safeDelivery.model.Ville;
import com.safeDelivery.service.PlatService;
import com.safeDelivery.utils.SingletonConnexion;

public class PlatServiceImpl implements PlatService {
	private Connection conn;

	public PlatServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public long addPlat(Plat plat, long idResto) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "insert into plat (nom, prix, description) values (?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, plat.getNom());
				ps.setDouble(2, plat.getPrix());
				ps.setString(3, plat.getDescription());
				int count = ps.executeUpdate();
				if (count == 1) {
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						int rsgetint = rs.getInt(1);
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						MenuServiceImpl impl = new MenuServiceImpl(conn);
						impl.addMenu(rsgetint, idResto);
						return rsgetint;
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return -4;
					}
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return -3;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}


	@Override
	public long changePlat(Plat oldPlat, Plat newPlat) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "update plat set nom = ?, prix = ?, description = ? where id = ?";
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, newPlat.getNom());
				ps.setDouble(2, newPlat.getPrix());
				ps.setString(3, newPlat.getDescription());
				ps.setLong(4, oldPlat.getId());
				int count = ps.executeUpdate();
				ps.close();
//				SingletonConnexion.closeConnection(conn);
				if (count == 1) {
					return 1;
				} else {
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
	public int deletePlat(long idPlat, long idResto) {
		MenuServiceImpl impl = new MenuServiceImpl(conn);
		int deleteMenu = impl.deleteMenu(idPlat, idResto);
		if(deleteMenu == 1) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "delete from plat where id = ? ";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, idPlat);
					int count = ps.executeUpdate();
					if (count > 0) {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return 1;
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
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
		else {
			return -4;
		}
	}

	@Override
	public Plat findById(long id) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from plat where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Plat plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3), result.getString(4), result.getString(5));
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return plat;
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
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

}
