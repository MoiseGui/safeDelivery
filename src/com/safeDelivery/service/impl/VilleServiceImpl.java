package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Ville;
import com.safeDelivery.service.VilleService;
import com.safeDelivery.utils.SingletonConnexion;

public class VilleServiceImpl implements VilleService {
	private Connection conn;
	
	

	public VilleServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public long existByName(String nom) {

		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select id from ville where nom like ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, nom);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					int id = result.getInt(1);
					if (id > 0) {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return id;
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return -1;
					}
				}else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return -4 ;
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
	public long saveVille(Ville ville) {
		long found = existByName(ville.getNom());
		System.out.println("found saveville "+found);
		if (found < 0) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into ville (nom) values (?)";
					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, ville.getNom());
					int count = ps.executeUpdate();
					if (count == 1) {
						ResultSet rs = ps.getGeneratedKeys();
						if (rs.next()) {
							int rsgetint = rs.getInt(1);
							ps.close();
//							SingletonConnexion.closeConnection(conn);
							return rsgetint;
						}
						else {
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
					return -3;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		} else {
			ville.setId(found);
			return found;
		}

	}

	@Override
	public List<String> findAll() {
		List<String> list = new ArrayList<String>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from ville";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while(result.next()) {
					list.add(result.getString(2));
				}
				statement.close();
//				SingletonConnexion.closeConnection(conn);
				return list;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Ville findById(long id) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from ville where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Ville ville = new Ville(id, result.getString(2));
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return ville;
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
