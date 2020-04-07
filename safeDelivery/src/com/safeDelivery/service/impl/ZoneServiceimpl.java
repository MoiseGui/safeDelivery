package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeDelivery.model.Zone;
import com.safeDelivery.service.ZoneService;
import com.safeDelivery.utils.SingletonConnexion;

public class ZoneServiceimpl implements ZoneService {
	VilleServiceImpl villeserviceimpl = new VilleServiceImpl();

	@Override
	public int existByName(String nom) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select id from zone where nom = ?";
				PreparedStatement ps = conn.prepareStatement(query);
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
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
		return -4;
	}

	@Override
	public int saveZone(Zone zone) {
		int found = existByName(zone.getNom());
		System.out.println("found ////// =" + found);
		if (found < 0) {
			int rs = villeserviceimpl.saveVille(zone.getVille());
			System.out.println("creation de la ville = " + rs);
			if (rs >= 0) {
				try {
					Connection conn = SingletonConnexion.startConnection();
					if (conn != null) {
						String query = "insert into zone (nom,id_ville) values (?,?)";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setString(1, zone.getNom());
						ps.setLong(2, rs);
						int count = ps.executeUpdate();
						ps.close();
						if (count > 0) {

							SingletonConnexion.closeConnection(conn);
							ResultSet rs1 = ps.getGeneratedKeys();
							if (rs1.next())
								return rs1.getInt(1);
						} else {

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

		}
		return -1;
	}

}
