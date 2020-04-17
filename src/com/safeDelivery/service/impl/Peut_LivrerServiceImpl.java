package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Livreur;
import com.safeDelivery.model.User;
import com.safeDelivery.model.Zone;
import com.safeDelivery.service.Peut_LivrerService;
import com.safeDelivery.utils.SingletonConnexion;

public class Peut_LivrerServiceImpl implements Peut_LivrerService {
	private Connection conn;

	public Peut_LivrerServiceImpl(Connection connection) {
		this.conn = connection;
	}

	@Override
	public List<Zone> findByLivreur(Livreur livreur) {

		UserServiceImpl userServiceimpl = new UserServiceImpl(conn);
		long idUser = userServiceimpl.existByEmailAndPass(livreur.getEmail(), livreur.getPass());
		System.out.println("l'id du user est = " + idUser);
		if (idUser > 0) {
			List<Zone> zones = new ArrayList<Zone>();
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "select id_zone from peut_livrer where id_livreur = ?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, livreur.getId());
					ResultSet result = ps.executeQuery();
					while (result.next()) {
						ZoneServiceimpl zoneServiceimpl = new ZoneServiceimpl(conn);
						Zone zone = zoneServiceimpl.findById(result.getInt(1));
						zones.add(zone);
					}
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return zones;
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}

		else {
			System.out.println("Le livreur n'existe pas");
			return null;
		}
	}

	@Override
	public List<Livreur> findByZone(Zone zone) {
		ZoneServiceimpl zoneServiceimpl = new ZoneServiceimpl(conn);
		long idZone = zoneServiceimpl.existByName(zone.getNom());
		System.out.println("l'id de la zone est = " + idZone);
		List<Livreur> livreurs = new ArrayList<Livreur>();
		if (idZone > 0) {
			try {
//				Connection conn = SingletonConnexion.startConnection();
				if (conn != null) {
					String query = "select id_livreur from peut_livrer where id_zone = ?";
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setLong(1, zone.getId());
					ResultSet result = ps.executeQuery();
					while (result.next()) {
						UserServiceImpl userServiceImpl = new UserServiceImpl(conn);
						User user = userServiceImpl.getUserById(result.getLong(1));
						if (user == null) {
							return null;
						} else {
							Livreur livreur = new Livreur(user);
							livreurs.add(livreur);
						}
					}
					ps.close();
//					SingletonConnexion.closeConnection(conn);
					return livreurs;
				} else {
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}

		}

		else {
			System.out.println("Le livreur n'existe pas");
			return null;
		}
	}

}
