package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Plat;
import com.safeDelivery.service.PlatService;

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
	public int deletePlat(long idPlat) {
		MenuServiceImpl impl = new MenuServiceImpl(conn);
		int deleteMenu = impl.deleteMenu(idPlat);
		if (deleteMenu == 1) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "update plat set deleted = 1 where id = ? ";
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
		} else {
			return -4;
		}
	}

	@Override
	public Plat findById(long id) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from plat where id = ? and deleted = 0";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Plat plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3),
							result.getString(4), result.getString(5));
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

	public List<Plat> findByIdRestaurant(long id) {
		try {
			if (conn != null) {
				String query = "select id, nom, prix, description, image from plat,menu where id = id_plat and  id_restaurant = ? and deleted = 0";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				List<Plat> plats = new ArrayList<Plat>();
				while (result.next()) {
					Plat plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3),
							result.getString(4), result.getString(5));
					plats.add(plat);
				}
				ps.close();
				return plats;

			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public double getRevenu(long id, double prix) {
		try {
			if (conn != null) {
				String query = "select sum(qte) from commande_item where id_plat = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if(result.next()) {
					double qte = result.getDouble(1);
					if (qte > 0) {
						ps.close();
						return qte*prix;
					} else {
						ps.close();
						return 0;
					}
				}
				else {
					ps.close();
					return -4;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}
	
	public Plat getJamaisCmd() {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from plat where id = ? and deleted = 0";
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Plat plat = new Plat(result.getLong(1), result.getString(2), result.getDouble(3),
							result.getString(4), result.getString(5));
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
