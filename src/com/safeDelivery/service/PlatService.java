package com.safeDelivery.service;

import com.safeDelivery.model.Plat;

public interface PlatService {
	public Plat findById(long id);
	public long addPlat(Plat plat, long idResto);
	public long changePlat(Plat oldPlat, Plat newPlat);
	public int deletePlat(long idPlat);
}
