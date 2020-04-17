package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Commande_item;
import com.safeDelivery.service.Commande_itemService;
import com.safeDelivery.utils.SingletonConnexion;

public class Commande_itemServiceImpl implements Commande_itemService {

	@Override
	public int existsBy(Long id_commande, Long id_plat) {
		try {
			Connection conn = SingletonConnexion.startConnection();
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
					SingletonConnexion.closeConnection(conn);
					return 1;

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

	@Override
	public int addCommandeItem(Commande_item commande_item) {
		int ret = existsBy(commande_item.getId_commande(), commande_item.getId_plat());
		if (ret < 0) {
			try {
				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "insert into commande_item  values (?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, commande_item.getId_plat());
					ps.setLong(2, commande_item.getId_commande());
					ps.setLong(3, commande_item.getQte());
					ps.setString(4, commande_item.getEtat());

					int count = ps.executeUpdate();
					if (count == 1) {
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
		int ret = existsBy(commande_item.getId_commande(), commande_item.getId_plat());
		if (ret > 0) {
			try {
				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "delete from commande_item where id_plat = ? and id_commande = ? ";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, commande_item.getId_plat());
					ps.setLong(1, commande_item.getId_commande());
					int count = ps.executeUpdate();
					if (count == 1) {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return 1;
					} else {
						ps.close();
						SingletonConnexion.closeConnection(conn);
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
	public List<Commande_item> findByIdCommande(Long id_commande) {
		List<Commande_item> list = new ArrayList<Commande_item>();
		Commande_item comItem = new Commande_item();
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from commande_item";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {

					comItem.setId_plat(result.getLong(1));
					comItem.setId_commande(result.getLong(2));
					comItem.setQte(result.getLong(3));
					comItem.setEtat(result.getString(4));

					list.add(comItem);
				}
				statement.close();
				SingletonConnexion.closeConnection(conn);
				return list;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
