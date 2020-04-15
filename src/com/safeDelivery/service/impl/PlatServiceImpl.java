package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.model.Plat;
import com.safeDelivery.service.PlatService;
import com.safeDelivery.utils.SingletonConnexion;

public class PlatServiceImpl implements PlatService {

	@Override
	public long addPlat(Plat plat) {
		try {
			Connection conn = SingletonConnexion.startConnection();
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
						SingletonConnexion.closeConnection(conn);
						return rsgetint;
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
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public long deletePlat(Plat plat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long changePlat(Plat oldPlat, Plat newPlat) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "update plat set nom = ?, prix = ?, description = ? where id = ?";
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, newPlat.getNom());
				ps.setDouble(2, newPlat.getPrix());
				ps.setString(3, newPlat.getDescription());
				ps.setLong(4, oldPlat.getId());
				int count = ps.executeUpdate();
				ps.close();
				SingletonConnexion.closeConnection(conn);
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

}