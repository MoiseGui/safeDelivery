package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Plat;

public interface PlatService {
	public Plat findById(long id);
	public Plat findByNom(String nomPlat);
	public long addPlat(Plat plat, long idResto);
	public long changePlat(Plat oldPlat, Plat newPlat);
	public int deletePlat(long idPlat);
	public List<Plat> getRandomPlat (); 
	public List<Plat> findPlatByResto(String restaurant);
	public List<Plat> findPlatByVille(String ville);
	public List<Plat> findPlatByVilleAndResto(String ville, String restaurant);
	public long getIdByNom(String nomPlat);
}
