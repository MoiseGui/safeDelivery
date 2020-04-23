package com.safeDelivery.service;

import java.io.File;
import java.util.List;

import com.safeDelivery.model.Plat;

public interface PlatService {
	public Plat findById(long id);
	long addPlat(Plat plat, long idResto, File file);
	public Plat findByNom(String nomPlat);
	int changePlat(Plat oldPlat, Plat newPlat, File file);
	public int deletePlat(long idPlat);
	public List<Plat> getRandomPlat (); 
	public List<Plat> findPlatByResto(String restaurant);
	public List<Plat> findPlatByVille(String ville);
	public List<Plat> findPlatByVilleAndResto(String ville, String restaurant);
	public long getIdByNom(String nomPlat);
	public List<Plat> findAll();
	public List<Plat> findAllByNom(String nom);
	
}
