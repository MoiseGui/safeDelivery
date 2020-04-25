package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Livreur;
import com.safeDelivery.service.CommandeService;
import com.safeDelivery.utils.DateTimeUtil;
import com.safeDelivery.utils.DateUtil;

public class CommandeServiceImpl implements CommandeService {
	ClientServiceImpl clientServiceImp;
	LivreurServiceImpl livreurServiceImp;
	private Connection conn;

	public CommandeServiceImpl(Connection connection) {
		this.conn = connection;
		clientServiceImp = new ClientServiceImpl(connection);
		livreurServiceImp = new LivreurServiceImpl(connection);
	}

	@Override
	public long existById(Commande commande) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from commande where id=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, commande.getId());
				ResultSet result = ps.executeQuery();
				result.next();
				if (result.getInt(1) == 1) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public long addCommand(Commande commande) {

		long result1 = existById(commande);
		if (result1 != 1) {
			long result2 = clientServiceImp.existById(commande.getClient());
			if (result2  > 0) {
				try {
//					Connection conn = SingletonConnexion.startConnection();
					if (conn != null) {
						String query = "insert into commande (id_client,total) values (?,?)";
						PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
						ps.setLong(1, commande.getClient().getId());
						ps.setDouble(2, commande.getTotal());
						int count = ps.executeUpdate();
						if (count > 0) {
							ResultSet rs = ps.getGeneratedKeys();
							if (rs.next()) {
								long id = rs.getInt(1);
								ps.close();
//								SingletonConnexion.closeConnection(conn);
								return id;
							} else {
								ps.close();
//								SingletonConnexion.closeConnection(conn);
								return -6;
							}
						} else {
							ps.close();
//							SingletonConnexion.closeConnection(conn);
							return -5;
						}
					} else {
						return -4;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return -3;
				}
			}
			else {
				return -2;
			}
		}
		else {
			return -1;
		}

	}

	@Override
	public double getTotal(Commande commande) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
//				String query = "";
			} else {
				return -2;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public List<Commande> findByDate(LocalDate date) {
		List<Commande> commandes = new ArrayList<Commande>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String realDate = DateUtil.format(date);
				String query = "select * from commande where dateCommande like '" + realDate + "%'";
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					ClientServiceImpl clientServiceImpl = new ClientServiceImpl(conn);
					Client client = clientServiceImpl.findById(result.getLong(2));

					Livreur livreur = null;
					if(result.getLong(5) !=  0) {
						LivreurServiceImpl livreurServiceImpl = new LivreurServiceImpl(conn);
						livreur = livreurServiceImpl.findById(result.getLong(5));
					}
					
					LocalDateTime dateLiv = null;
					if(result.getString(7) != null) {
						dateLiv = DateTimeUtil.parse(result.getString(7));
					}

					Commande commande = new Commande(result.getLong(1), client, result.getDouble(3),
							result.getString(4), livreur, DateTimeUtil.parse(result.getString(6)),
							dateLiv);
					commandes.add(commande);
				}
				statement.close();
//				SingletonConnexion.closeConnection(conn);
				return commandes;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Commande> findAll() {
		List<Commande> commandes = new ArrayList<Commande>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from commande";
				Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet result = statement.executeQuery(query);
				try {
					result.last();
					int size = result.getRow() - 1;
					System.out.println("Taille de résultat " + size);
					result.beforeFirst();
				} catch (Exception ex) {
					ex.printStackTrace();
					return null;
				}
				while (result.next()) {
					ClientServiceImpl clientServiceImpl = new ClientServiceImpl(conn);
					Client client = clientServiceImpl.findById(result.getLong(2));
					if (client == null)
						System.out.println("Commande nulle");

					Livreur livreur = null;
					if(result.getLong(5) !=  0) {
						LivreurServiceImpl livreurServiceImpl = new LivreurServiceImpl(conn);
						livreur = livreurServiceImpl.findById(result.getLong(5));
					}
					
					LocalDateTime dateLiv = null;
					if(result.getString(7) != null) {
						dateLiv = DateTimeUtil.parse(result.getString(7));
					}

					Commande commande = new Commande(result.getLong(1), client, result.getDouble(3),
							result.getString(4), livreur, DateTimeUtil.parse(result.getString(6)),
							dateLiv);
					commandes.add(commande);
				}
				statement.close();
//				SingletonConnexion.closeConnection(conn);
				if (commandes.isEmpty())
					System.out.println("Liste vide depuis findAll de commandes");
				return commandes;
			} else {
				System.out.println("Passed by here");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    @Override
	public List<Commande> findByrestaurant(long idResto) {
		List<Commande> commandes = new ArrayList<Commande>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select commande.id, commande.id_client, commande.total, commande.etat, commande.id_livreur, commande.dateCommande, commande.dateLivraison from restaurant, menu, plat, commande_item, commande where restaurant.id = menu.id_restaurant and menu.id_plat = plat.id and plat.id = commande_item.id_plat and commande_item.id_commande = commande.id and restaurant.id = "+idResto+"  order by dateCommande desc";
				Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					    ResultSet.CONCUR_READ_ONLY);
//				PreparedStatement statement = conn.prepareStatement();
//				statement.setLong(1, idResto);
				ResultSet result = statement.executeQuery(query);

				try {
					result.last();
					int size = result.getRow() - 1;
					System.out.println("Taille de résultat " + size);
					result.beforeFirst();
				} catch (Exception ex) {
					ex.printStackTrace();
					return null;
				}
				while (result.next()) {
					ClientServiceImpl clientServiceImpl = new ClientServiceImpl(conn);
					Client client = clientServiceImpl.findById(result.getLong(2));
					if (client == null)
						System.out.println("Client nulle dans findByRestaurant");
					Livreur livreur = null;
					if(result.getLong(5) !=  0) {
						LivreurServiceImpl livreurServiceImpl = new LivreurServiceImpl(conn);
						livreur = livreurServiceImpl.findById(result.getLong(5));
					}
					if (livreur == null)
						System.out.println("livreur nulle dans findByRestaurant");
					
					LocalDateTime dateLiv = null;
					if(result.getString(7) != null) {
						dateLiv = DateTimeUtil.parse(result.getString(7));
					}
					
					Commande commande = new Commande(result.getLong(1), client, result.getDouble(3),
							result.getString(4), livreur, DateTimeUtil.parse(result.getString(6)),
							dateLiv);
					commandes.add(commande);
				}
				statement.close();
//				SingletonConnexion.closeConnection(conn);
				if (commandes.isEmpty())
					System.out.println("Liste vide depuis findAll de commandes");
				return commandes;
			} else {
				System.out.println("Passed by here");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Commande findById(long id) {
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from commande where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					ClientServiceImpl clientServiceImpl = new ClientServiceImpl(conn);
					Client client = clientServiceImpl.findById(result.getLong(2));
					if (client == null)
						System.out.println("Commande nulle");

					LivreurServiceImpl livreurServiceImpl = new LivreurServiceImpl(conn);
					Livreur livreur = livreurServiceImpl.findById(result.getLong(5));
					if (livreur == null)
						System.out.println("Commande nulle");

					LocalDateTime dateLiv = null;
					if(result.getString(7) != null) {
						dateLiv = DateTimeUtil.parse(result.getString(7));
					}
					Commande commande = new Commande(result.getLong(1), client, result.getDouble(3),
							result.getString(4), livreur, DateTimeUtil.parse(result.getString(6)),
							dateLiv);
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return commande;
				} else {
					ps.close();
//					SingletonConnexion.closeConnection(conn);
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

	@Override
	public List<Commande> findByClient(Client client) {
		List<Commande> commandes = new ArrayList<Commande>();
		try {
//			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select commande.* from client,commande where client.id = commande.id_client and client.id = "+client.getId()+" order by dateCommande desc";
				Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
					    ResultSet.CONCUR_READ_ONLY);
//				PreparedStatement statement = conn.prepareStatement();
//				statement.setLong(1, idResto);
				ResultSet result = statement.executeQuery(query);
				while (result.next()) {
					Livreur livreur = null;
					if(result.getLong(5) !=  0) {
						LivreurServiceImpl livreurServiceImpl = new LivreurServiceImpl(conn);
						livreur = livreurServiceImpl.findById(result.getLong(5));
					}
					if (livreur == null)
						System.out.println("livreur nulle dans findByRestaurant");
					
					LocalDateTime dateLiv = null;
					if(result.getString(7) != null) {
						dateLiv = DateTimeUtil.parse(result.getString(7));
					}
					
					Commande commande = new Commande(result.getLong(1), client, result.getDouble(3),
							result.getString(4), livreur, DateTimeUtil.parse(result.getString(6)),
							dateLiv);
					commandes.add(commande);
				}
				statement.close();
//				SingletonConnexion.closeConnection(conn);
				if (commandes.isEmpty())
					System.out.println("Liste vide depuis findAll de commandes");
				return commandes;
			} else {
				System.out.println("Passed by here");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
