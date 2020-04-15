package com.safeDelivery.service;

import com.safeDelivery.model.Commande;

public interface CommandeService {
	public long existById(Commande commande);

	public long addCommand(Commande commande);
 
	public double getTotal(Commande commande);


}
