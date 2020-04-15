package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.model.Restaurant;
import com.safeDelivery.model.Restaurateur;
import com.safeDelivery.model.User;
import com.safeDelivery.service.RestaurantService;
import com.safeDelivery.utils.SingletonConnexion;

public class RestaurantServiceImpl implements RestaurantService {

	@Override
	public long addRestaurant(Restaurant restaurant) {
		long found = existByNom(restaurant.getNom());
		if (found < 0) {
			System.out.println("restaurant not found " + found);
			UserServiceImpl userServiceImpl = new UserServiceImpl();
			long rs = userServiceImpl.existByEmailAndPass(restaurant.getRestaurateur().getEmail(), restaurant.getRestaurateur().getPass());
			System.out.println("test si le restaurateur existe = " + rs);
			if (rs >= 0) {
				try {
					restaurant.getRestaurateur().setId(rs);
					User restaurateur = userServiceImpl.getUserByEmail(restaurant.getRestaurateur().getEmail());
					Connection conn = SingletonConnexion.startConnection();
					if (conn != null) {
						String query = "insert into restaurant (nom, adresse, id_restaurateur) values (?,?,?)";
						PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, restaurant.getNom());
						ps.setString(2, restaurant.getAdress());
						ps.setLong(3,rs);
						int count = ps.executeUpdate();
						if (count == 1) {
							ResultSet rs1 = ps.getGeneratedKeys();
							if (rs1.next()) {
								int id = rs1.getInt(1);
								ps.close();
								System.out.println("l'id du restaurant "+id);
								SingletonConnexion.closeConnection(conn);
								return id;
							}
							else {
								ps.close();
								System.out.println("erreur sur l'id du resto ins�r� restaurant ");
								SingletonConnexion.closeConnection(conn);
								return -4;
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
			else {
				System.out.println("Restaurateur not found");
				return -2;
			}

		} else {
			System.out.println("restaurant exists already " + found);
			return -1;
		}
	}

	@Override
	public long existByNom(String nom) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select id from restaurant where nom = ?";
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
	public int modifyResto(Restaurant oldRestaurant, Restaurant newRestaurant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Restaurant findByRestaurateur(Restaurateur restaurateur) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
