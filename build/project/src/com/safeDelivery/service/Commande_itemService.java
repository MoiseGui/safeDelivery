package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Commande_item;


public interface Commande_itemService {
	
	public int existsBy(long id_commande,long id_plat);
	public int addCommandeItem(Commande_item commande_item);
	public int deleteCommandeItem(Commande_item commande_item);
	public int setEtat(long id_commande,long id_plat, String etat);
	public List<Commande_item> findByIdCommande(long id_commande);
	
}
