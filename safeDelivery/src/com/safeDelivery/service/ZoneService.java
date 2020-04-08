package com.safeDelivery.service;

import com.safeDelivery.model.Zone;

public interface ZoneService {
	public long existByName(String nom);
	public long saveZone(Zone zone);
}
