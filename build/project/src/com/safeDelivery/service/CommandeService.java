package com.safeDelivery.service;

import java.time.LocalDate;
import java.util.List;

import com.safeDelivery.model.Commande;

public interface CommandeService {
	
	public Commande findById(long id);
	
	public List<Commande> findAll();
	
	public long existById(Commande commande);

	public long addCommand(Commande commande);
 
	public double getTotal(Commande commande);

	public List<Commande> findByDate(LocalDate date);
}
