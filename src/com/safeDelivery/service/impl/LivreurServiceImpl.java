package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeDelivery.model.Livreur;
import com.safeDelivery.model.User;
import com.safeDelivery.service.LivreurService;
import com.safeDelivery.utils.SingletonConnexion;

public class LivreurServiceImpl implements LivreurService {

	@Override
	public long addLivreur(Livreur livreur) {
		UserServiceImpl userServiceimpl = new UserServiceImpl();
		User user = new User(livreur.getNom(), livreur.getPrenom(), livreur.getEmail(), livreur.getPass(),
				livreur.getTel(), livreur.getCategorie(), livreur.getEnable());
		long idUser = userServiceimpl.addUser(user);
		System.out.println("l'id du user est = " + idUser);
		if (idUser > 0) {
			livreur.setId(idUser);
			try {
				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into livreur (id) values (?)";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, idUser);
					int count = ps.executeUpdate();
					if (count > 0) {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return idUser;
					} else {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return -5;
					}
				} else {
					return -4;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -3;
			}

		} else {
			return -1;
		}
	}

	@Override
	public long existByid(Livreur livreur) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from livreur where id=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, livreur.getId());
				ResultSet result = ps.executeQuery();
				result.next();
				if (result.getInt(1) == 1) {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
					return -3;
				}
			} else {
				return -2;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Livreur findById(long id) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from livreur where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					
						UserServiceImpl userServiceImpl = new UserServiceImpl();
						User user = userServiceImpl.getUserById(result.getLong(1));
						Livreur livreur = new Livreur(user);
						ps.close();
						SingletonConnexion.closeConnection(conn);

						return livreur;
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

}
