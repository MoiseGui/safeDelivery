package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.User;
import com.safeDelivery.service.ClientService;
import com.safeDelivery.utils.SingletonConnexion;

public class ClientServiceImpl implements ClientService {

	UserServiceImpl userserviceimpl = new UserServiceImpl();

	@Override
	public long addClient(Client client) {
		AdresseServiceImpl adresseService = new AdresseServiceImpl();
		User user = new User(client.getNom(), client.getPrenom(), client.getEmail(), client.getPass(), client.getTel(),
				client.getCategorie(), client.getEnable());
		long idUser = userserviceimpl.addUser(user);
		System.out.println("l'id du user est = "+idUser);
		if (idUser > 0) {
			client.setId(idUser);
			long idAdresse = adresseService.saveAdresse(client.getAdresse());
			if (idAdresse > 0) {
				client.getAdresse().setId(idAdresse);
				try {
					Connection conn = SingletonConnexion.startConnection();
					if (conn != null) {
						String query = "insert into client (id,adresse) values (?,?)";
						PreparedStatement ps = conn.prepareStatement(query);
						ps.setLong(1, idUser);
						ps.setLong(2, idAdresse);
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
				return -2;
			}

		} else {
			return -1;
		}

	}

	@Override
	public User getClientByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int disableClientByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteClientByEmail(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

}
