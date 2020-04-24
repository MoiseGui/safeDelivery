package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.Panier;

public interface PanierService {

	public long deletePanier(Panier panier);

	public long validatePanier(List<Panier> paniers, double total);

	public int existsBy(long id_client, long id_plat);

	public long addPanier(Panier panier);

	public List<Panier> getByClient(Client client);


	public long deleteAll(Client client);

	public long deleteByClientAndPlat(Client client, long plat);

	public long upgradePanier(Client client, long id_plat, int quantite);

}
