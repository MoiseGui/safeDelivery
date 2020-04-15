package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Commande_item;


public interface Commande_itemService {
	
	public int existsBy(Long id_commande,Long id_plat);
	public int addCommandeItem(Commande_item commande_item);
	public int deleteCommandeItem(Commande_item commande_item);
	public List<Commande_item> findByIdCommande(Long id_commande);
	
}
