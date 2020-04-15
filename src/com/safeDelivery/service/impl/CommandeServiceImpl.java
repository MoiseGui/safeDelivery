package com.safeDelivery.service.impl;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.safeDelivery.model.Commande;
import com.safeDelivery.service.CommandeService;
import com.safeDelivery.utils.SingletonConnexion;
import com.safeDelivery.utils.saltHashPassword;

public class CommandeServiceImpl implements CommandeService {
ClientServiceImpl clientServiceImp = new ClientServiceImpl();
LivreurServiceImpl livreurServiceImp = new LivreurServiceImpl();
	@Override
	public long existById(Commande commande) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from commande where id=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, commande.getId());
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
	public long addCommand(Commande commande) {
		
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				long result1 = existById(commande);
				if (result1 == 1) {
                     long result2 = clientServiceImp.existById(commande.getClient());
                     long result3 = livreurServiceImp.existByid(commande.getLivreur());
                     if(result2 == 1 && result3 == 1)
                     {
                    	String query = "insert into commande (id_client,total,etat,id_livreur) values (?,?,?,?)";
     					PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
     					ps.setLong(1, commande.getClient().getId());
     					ps.setDouble(2, commande.getTotal());
     					ps.setString(3, commande.getEtat());
     					ps.setLong(4, commande.getLivreur().getId());
     					int count = ps.executeUpdate();
     					if (count > 0) {
    						ResultSet rs = ps.getGeneratedKeys();
    						if (rs.next()) {
    							long id = rs.getInt(1);
    							ps.close();
    							SingletonConnexion.closeConnection(conn);
    							return id;
    						} else {
    							return -5;
    						}
    					} else {
    						ps.close();
    						SingletonConnexion.closeConnection(conn);
    						return -4;
    					}
                     }else {
                    	 return -4;
                     }
				
				} else {
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
	public double getTotal(Commande commande) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "";
			} else {
				return -2;

			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
