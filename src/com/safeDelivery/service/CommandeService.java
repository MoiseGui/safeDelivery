package com.safeDelivery.service;

import java.time.LocalDate;
import java.util.List;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.Commande;

public interface CommandeService {
	
	public Commande findById(long id);
	
	public List<Commande> findAll();
	
	public long existById(Commande commande);

	public long addCommand(Commande commande);
 
	public double getTotal(Commande commande);

	public List<Commande> findByDate(LocalDate date);

	public List<Commande> findByrestaurant(long idResto);

	public List<Commande> findByClient(Client client);
	
	
}
