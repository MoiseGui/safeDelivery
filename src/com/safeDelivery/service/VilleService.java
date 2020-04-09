package com.safeDelivery.service;

import com.safeDelivery.model.Ville;

public interface VilleService {
	public long existByName(String nom);
	public long saveVille(Ville ville);
}
