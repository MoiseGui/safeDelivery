package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeDelivery.model.Ville;
import com.safeDelivery.service.VilleService;
import com.safeDelivery.utils.SingletonConnexion;

public class VilleServiceImpl implements VilleService{

	@Override
	public int findByName(String nom) {
		
		try {
			Connection conn = SingletonConnexion.startConnection();
			if(conn != null) {
				String query = "select count(*) from ville where nom = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, nom);
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
	public int saveVille(Ville ville) {
		// TODO Auto-generated method stub
		return 0;
	}

}
