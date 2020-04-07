package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.User;
import com.safeDelivery.service.ClientService;
import com.safeDelivery.utils.SingletonConnexion;

public class ClientServiceImpl implements ClientService {

	UserServiceImpl userserviceimpl = new UserServiceImpl();

	@Override
	public int addClient(Client client) {
		User user = new User(client.getNom(), client.getPrenom(), client.getEmail(), client.getPass(),client.getTel(), client.getCategorie(), client.getEnable());
		int result = userserviceimpl.addUser(user);
		if(result == 1)
		{try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
			
				String query = "insert into client (adresse) values (?)";
				PreparedStatement ps = conn.prepareStatement(query);
//				ps.setString(1,client.getAdresse());
				int count = ps.executeUpdate();
				if (count > 0) {
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
