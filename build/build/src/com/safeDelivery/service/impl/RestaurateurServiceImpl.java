package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.safeDelivery.model.Restaurateur;
import com.safeDelivery.model.User;
import com.safeDelivery.service.RestaurateurService;

public class RestaurateurServiceImpl implements RestaurateurService {
	private Connection conn;

	public RestaurateurServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public long addRestaurateur(Restaurateur restaurateur) {
		UserServiceImpl userServiceimpl = new UserServiceImpl(conn);
		User user = new User(restaurateur.getNom(), restaurateur.getPrenom(), restaurateur.getEmail(), restaurateur.getPass(),
				restaurateur.getTel(), restaurateur.getCategorie(), restaurateur.getEnable());
		long idUser = userServiceimpl.addUser(user);
		System.out.println("l'id du user est = " + idUser);
		if (idUser > 0) {
			restaurateur.setId(idUser);
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into restaurateur (id) values (?)";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, idUser);
					int count = ps.executeUpdate();
					if (count > 0) {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return idUser;
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
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
	public Restaurateur findByid(long id) {
		UserServiceImpl userService = new UserServiceImpl(conn);
		User user = userService.getUserById(id);
		if(!(user == null))
		{
			Restaurateur restaurateur = new Restaurateur(user);
			return restaurateur;
		}else {
			return null;
		}
	}

}
