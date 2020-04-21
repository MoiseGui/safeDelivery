package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Commande_item;
import com.safeDelivery.model.Panier;
import com.safeDelivery.model.Plat;
import com.safeDelivery.service.PanierService;

public class PanierServiceImpl implements PanierService {
	private Connection conn;

	public PanierServiceImpl(Connection connection) {
		this.conn = connection;
	}
	@Override
	public int existsBy(long id_client, long id_plat) {
		System.out.println("Id_client "+id_client+ " id_plat "+id_plat);
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select qte from panier where id_client = ? and id_plat = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id_client);
				ps.setLong(2, id_plat);
				ResultSet result = ps.executeQuery();
				if(result.next()) {
					int i = result.getInt(1);
					ps.close();
					return i;
				}
				else {
					ps.close();
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
	public long addPanier(Panier panier) {
		int ret = existsBy(panier.getClient().getId(), panier.getPlat().getId());
		if (ret < 0) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					panier.setQte(1);
					String query = "insert into panier (id_client,id_plat,qte) values (?,?,?)";
					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					ps.setLong(1, panier.getClient().getId());
					ps.setLong(2, panier.getPlat().getId());
					ps.setLong(3, panier.getQte());
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
			return -5;
		}
	}
	@Override
	public List<Panier> getByClient(Client client) {
		List<Panier> list = new ArrayList<Panier>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from panier where id_client = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, client.getId());
				ResultSet result = ps.executeQuery();
				while (result.next()) {
					PlatServiceImpl platServiceImpl = new PlatServiceImpl(conn);
					Plat plat = platServiceImpl.findById(result.getLong(3));
					Panier panier = new Panier(plat, client, result.getInt(4));
					list.add(panier);
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
	public long deletePanier(Panier panier) {
		int ret = existsBy(panier.getClient().getId(), panier.getPlat().getId());
		System.out.println("Resultat de existById "+ ret);
		if (ret > 0) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "delete from panier where id_plat = ? and id_client = ? ";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, panier.getPlat().getId());
					ps.setLong(2, panier.getClient().getId());
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
	public long validatePanier(List<Panier> paniers, double total) {
		CommandeServiceImpl commandeService = new CommandeServiceImpl(conn);
		Commande_itemServiceImpl commande_itemService = new Commande_itemServiceImpl(conn);
		Commande commande = new Commande(paniers.get(0).getClient(), total);
		long id = commandeService.addCommand(commande);
		commande.setId(id);
		System.out.println(commande.getId()+" ID client "+commande.getClient().getId());
		for (Panier panier : paniers) {
			Commande_item commande_item = new Commande_item(panier.getPlat(), commande, Long.valueOf(panier.getQte()), "En attente");
			commande_itemService.addCommandeItem(commande_item);
			deletePanier(panier);
		}
		return 1;
	}
	@Override
	public long upgradePanier(Client client, long  id_plat , int quantite) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "update  panier set qte = ?  where id_client  = ? and id_plat = ? ";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setInt(1,quantite);
					ps.setLong(2,client.getId());
					ps.setLong(3,id_plat);
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
	}
	@Override
	public long deleteAll(Client client) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "delete from panier where id_client = ? ";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, client.getId());
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return -1;
				}

			} else {
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}
	@Override
	public long deleteByClientAndPlat(Client client, long plat) {
		try {
			PlatServiceImpl platservice  = new PlatServiceImpl(conn);
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "delete from panier where id_client = ? and id_plat = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, client.getId());
				ps.setLong(2, plat);
				int count = ps.executeUpdate();
				if (count == 1) {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return 1;
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return -1;
				}

			} else {
				return -3;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -4;
		}
	}

}
