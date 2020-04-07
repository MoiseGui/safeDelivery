package com.safeDelivery.service;

import com.safeDelivery.model.Ville;

public interface VilleService {
	public int findByName(String nom);
	public int saveVille(Ville ville);
}
