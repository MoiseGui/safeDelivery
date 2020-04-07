package com.safeDelivery.service;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.User;

public interface ClientService {
	public int addClient(Client client);

	public User getClientByEmail(String email);

	public int disableClientByEmail(String email);

	public int deleteClientByEmail(String email);
}
