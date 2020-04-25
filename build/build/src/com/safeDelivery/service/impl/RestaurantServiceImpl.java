package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Adresse;
import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.model.Restaurateur;
import com.safeDelivery.model.User;
import com.safeDelivery.service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {
	private Connection conn;

	public RestaurantServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public long addRestaurant(Restaurant restaurant) {
		long found = existByNom(restaurant.getNom());
		if (found < 0) {
			System.out.println("restaurant not found " + found);
			UserServiceImpl userServiceImpl = new UserServiceImpl(conn);
			long rs = userServiceImpl.existByEmailAndPass(restaurant.getRestaurateur().getEmail(),
					restaurant.getRestaurateur().getPass());
			System.out.println("test si le restaurateur existe = " + rs);
			if (rs >= 0) {
//				User restaurateur = userServiceImpl.getUserByEmail(restaurant.getRestaurateur().getEmail());
				try {
//					Connection conn = SingletonConnexion.startConnection();
					if (conn != null) {
						String query = "insert into restaurant (nom, adresse, id_restaurateur) values (?,?,?)";
						PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, restaurant.getNom());
						ps.setLong(2, restaurant.getAdress().getId());
						ps.setLong(3, restaurant.getRestaurateur().getId());
						int count = ps.executeUpdate();
						if (count == 1) {
							ResultSet rs1 = ps.getGeneratedKeys();
							if (rs1.next()) {
								int id = rs1.getInt(1);
								ps.close();
								System.out.println("l'id du restaurant " + id);
//								SingletonConnexion.closeConnection(conn);
								return id;
							} else {
								ps.close();
								System.out.println("erreur sur l'id du resto ins�r� restaurant ");
//								SingletonConnexion.closeConnection(conn);
								return -4;
							}
						} else {
							ps.close();
//							SingletonConnexion.closeConnection(conn);
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
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select id from restaurant where nom = ?";
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
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
		return 0;
	}

	@Override
	public Restaurant findByRestaurateur(long idRestaurateur) {
		UserServiceImpl impl = new UserServiceImpl(conn);
		User user = impl.getUserById(idRestaurateur);
		if (user == null) {
			return null;
		} else {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "select * from restaurant where id_restaurateur = ?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, idRestaurateur);

					ResultSet result = ps.executeQuery();

					if (result.next()) {
						AdresseServiceImpl adresseServiceImpl = new AdresseServiceImpl(conn);
						Adresse adresse = adresseServiceImpl.findById(result.getLong(3));
						if (adresse == null) {
							ps.close();
//							SingletonConnexion.closeConnection(conn);
							return null;
						} else {
							Restaurateur restaurateur = new Restaurateur(user);
							Restaurant restaurant = new Restaurant(result.getLong(1), result.getString(2), adresse,
									restaurateur);
							ps.close();
//							SingletonConnexion.closeConnection(conn);
							return restaurant;
						}
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
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

	@Override
	public List<String> findAll() {
		List<String> list = new ArrayList<String>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from restaurant";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
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
	public List<String> findRestoByVille(String ville) {
		List<String> list = new ArrayList<String>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select restaurant.nom from restaurant,adresse,zone,ville where restaurant.adresse = adresse.id and adresse.id_zone = zone.id and zone.id_ville = ville.id and ville.nom = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, String.valueOf(ville));
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					list.add(result.getString(1));
				}
				ps.close();
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
	public Restaurant findByCommande(Commande commande) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select DISTINCT restaurant.* from restaurant,menu,plat,commande_item,commande,client where restaurant.id = menu.id_restaurant and menu.id_plat = plat.id and plat.id = commande_item.id_plat and commande_item.id_commande = commande.id and commande.id_client = client.id and client.id = "+Math.toIntExact(commande.getClient().getId())+" and commande.id =  "+ Math.toIntExact(commande.getId());
				Statement ps = conn.createStatement();
				ResultSet result = ps.executeQuery(query);
				Restaurant restaurant = new Restaurant();
				if (result.next()) {
					AdresseServiceImpl adresseService = new AdresseServiceImpl(conn);
					RestaurateurServiceImpl restaurateurServicec = new RestaurateurServiceImpl(conn);
					restaurant = new Restaurant(result.getLong(1), result.getString(2),
							adresseService.findById(result.getLong(3)),
							restaurateurServicec.findByid(result.getLong(4)));
					System.out.println("restaurant a �t� trouv�");
				}
				ps.close();
//				SingletonConnexion.closeConnection(conn);
				System.out.println(restaurant.toString());
				return restaurant;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
