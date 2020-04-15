package com.safeDelivery.service;

import com.safeDelivery.model.Plat;

public interface PlatService {
	public long addPlat(Plat plat);
	public long deletePlat(Plat plat);
	public long changePlat(Plat oldPlat, Plat newPlat);
}
