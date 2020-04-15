package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Ville;
import com.safeDelivery.model.Zone;
import com.safeDelivery.service.ZoneService;
import com.safeDelivery.utils.SingletonConnexion;

public class ZoneServiceimpl implements ZoneService {
	VilleServiceImpl villeserviceimpl = new VilleServiceImpl();

	@Override
	public long existByName(String nom) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select id from zone where nom = ?";
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nom);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					int id = result.getInt(1);
					if (id > 0) {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return id;
					} else {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return -1;
					}
				} else {
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

	@Override
	public long saveZone(Zone zone) {
		long found = existByName(zone.getNom());
		if (found < 0) {
			System.out.println("zone not found " + found);
			long rs = villeserviceimpl.saveVille(zone.getVille());
			System.out.println("creation de la ville = " + rs);
			zone.getVille().setId(rs);
			if (rs >= 0) {
				try {
					Connection conn = SingletonConnexion.startConnection();
					if (conn != null) {
						String query = "insert into zone (nom,id_ville) values (?,?)";
						PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, zone.getNom());
						ps.setLong(2, rs);
						int count = ps.executeUpdate();
						if (count > 0) {
							ResultSet rs1 = ps.getGeneratedKeys();
							if (rs1.next()) {
								int id = rs1.getInt(1);
								ps.close();
								System.out.println("l'id de la zone " + id);
								SingletonConnexion.closeConnection(conn);
								return id;
							}
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
			return -2;

		} else {
			return found;
		}

	}

	@Override
	public List<String> findAll() {
		List<String> list = new ArrayList<String>();
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from zone";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					list.add(result.getString(2));
				}
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
	public List<Zone> findById(long id) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from zone where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				List <Zone> zones = new ArrayList<Zone>();
				while (result.next()) {
					VilleServiceImpl vsimpl = new VilleServiceImpl(); 
					Ville ville = vsimpl.findById(result.getInt(3));
					if(ville==null) {
						return null;
					}
					else {
					Zone zone= new Zone(id, result.getString(2) , ville);
					zones.add(zone);
					ps.close();
					SingletonConnexion.closeConnection(conn);

					}
				}
					return zones;
					 
				
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
