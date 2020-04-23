package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Commande_item;
import com.safeDelivery.model.Plat;
import com.safeDelivery.service.Commande_itemService;

public class Commande_itemServiceImpl implements Commande_itemService {
	private Connection conn;

	public Commande_itemServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public int existsBy(long id_commande, long id_plat) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from commande_item where id_commande = ? and id_plat = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id_commande);
				ps.setLong(2, id_plat);

				ResultSet result = ps.executeQuery();
				result.next();
				int id = result.getInt(1);
				if (id == 1) {

					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return 1;

				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
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

	@Override
	public int addCommandeItem(Commande_item commande_item) {
		int ret = existsBy(commande_item.getCommande().getId(), commande_item.getPlat().getId());
		if (ret < 0) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into commande_item  values (?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, commande_item.getPlat().getId());
					ps.setLong(2, commande_item.getCommande().getId());
					ps.setLong(3, commande_item.getQte());
					ps.setString(4, "En attente");

					int count = ps.executeUpdate();
					if (count == 1) {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return 1;
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return -3;
					}
				} else {
					return -2;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		} else {
			return -1;
		}
	}

	@Override
	public int deleteCommandeItem(Commande_item commande_item) {
		int ret = existsBy(commande_item.getCommande().getId(), commande_item.getPlat().getId());
		if (ret > 0) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "delete from commande_item where id_plat = ? and id_commande = ? ";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, commande_item.getPlat().getId());
					ps.setLong(1, commande_item.getCommande().getId());
					int count = ps.executeUpdate();
					if (count == 1) {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return 1;
					} else {
						ps.close();
//						SingletonConnexion.closeConnection(conn);
						return -1;
					}

				} else {
					return -3;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return -4;
			}
		} else {
			return -2;
		}

	}

	@Override
	public List<Commande_item> findByIdCommande(long id_commande) {
		List<Commande_item> list = new ArrayList<Commande_item>();

		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from commande_item where id_commande = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id_commande);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					PlatServiceImpl platServiceImpl = new PlatServiceImpl(conn);
					Plat plat = platServiceImpl.findById(result.getLong(1));

					CommandeServiceImpl commandeServiceImpl = new CommandeServiceImpl(conn);
					Commande commande = commandeServiceImpl.findById(result.getLong(2));
					Commande_item comItem = new Commande_item(plat, commande, result.getLong(3), result.getString(4));
//					comItem.setId_plat(result.getLong(1));
//					comItem.setId_commande(result.getLong(2));
//					comItem.setQte(result.getLong(3));
//					comItem.setEtat(result.getString(4));

					list.add(comItem);
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

	public List<Commande_item> findByIdCommandeAndIdResto(long id_commande, long id_resto) {
		List<Commande_item> list = new ArrayList<Commande_item>();

		try {
//					Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
//				System.out.println("id reto "+id_resto+" id commande "+ id_commande);
				String query = "select commande_item.id_plat, commande_item.id_commande, commande_item.qte, commande_item.etat from restaurant, menu, plat, commande_item where restaurant.id = menu.id_restaurant and menu.id_plat = plat.id and plat.id = commande_item.id_plat and restaurant.id = ? and commande_item.id_commande = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id_resto);
				ps.setLong(2, id_commande);
				ResultSet result = ps.executeQuery();
				while (result.next()) {
//					System.out.println("i have come here");
					PlatServiceImpl platServiceImpl = new PlatServiceImpl(conn);
					Plat plat = platServiceImpl.findById(result.getLong(1));

					CommandeServiceImpl commandeServiceImpl = new CommandeServiceImpl(conn);
					Commande commande = commandeServiceImpl.findById(result.getLong(2));
					Commande_item comItem = new Commande_item(plat, commande, result.getLong(3), result.getString(4));

					list.add(comItem);
				}
				ps.close();
//						SingletonConnexion.closeConnection(conn);
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
	public int setEtat(long id_commande, long id_plat, String etat) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "update commande_item set etat = ? where id_commande = ? and id_plat = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, etat);
				ps.setLong(2, id_commande);
				ps.setLong(3, id_plat);
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return -3;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}

}
