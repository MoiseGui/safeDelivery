package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeDelivery.model.Ville;
import com.safeDelivery.service.VilleService;
import com.safeDelivery.utils.SingletonConnexion;

public class VilleServiceImpl implements VilleService {

	@Override
	public int existByName(String nom) {

		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select id from ville where nom like ?";
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
	public int saveVille(Ville ville) {
		int found = existByName(ville.getNom());
		System.out.println("found saveville "+found);
		if (found < 0) {
			try {
				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into ville (nom) values (?)";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, ville.getNom());
					int count = ps.executeUpdate();
					if (count > 0) {
						ps.close();
						ResultSet rs = ps.getGeneratedKeys();
						int rsgetint = rs.getInt(1);
						if (rs.next()) {
							SingletonConnexion.closeConnection(conn);
							return rsgetint;
						}
					} else {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return -2;
					}
				} else {
					return -3;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
			return -5;
		} else {
			return found;
		}

	}

}
