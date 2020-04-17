package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.safeDelivery.model.Adresse;
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
		System.out.println("l'id du user est = " + idUser);
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

	@Override
	public long existById(Client client) {

		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select count(*) from client where id=?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, client.getId());
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
	public Client findById(Long id) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from client where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					Long idC = result.getLong(1);
					AdresseServiceImpl adresseServiceImpl = new AdresseServiceImpl();
					Adresse adresse = adresseServiceImpl.findById(result.getLong(2));
					if (adresse == null) {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return null;
					} else {
						UserServiceImpl userServiceImpl = new UserServiceImpl();
						User user = userServiceImpl.getUserById(idC);
						Client client = new Client(user, adresse);
						ps.close();
						SingletonConnexion.closeConnection(conn);

						return client;
					}
				} else {
					ps.close();
					SingletonConnexion.closeConnection(conn);
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
