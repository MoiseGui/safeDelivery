package com.safeDelivery.service;

import com.safeDelivery.model.Ville;
import com.safeDelivery.model.Zone;

public interface ZoneService {
	public int existByName(String nom);
	public int saveZone(Zone zone);
}
