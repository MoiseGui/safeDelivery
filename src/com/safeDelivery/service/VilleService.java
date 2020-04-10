package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Ville;

public interface VilleService {
	public long existByName(String nom);
	public long saveVille(Ville ville);
	public List<String> findAll();
}
