package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Zone;

public interface ZoneService {
	public long existByName(String nom);
	public long saveZone(Zone zone);
	public List<String> findAll();
	public List<Zone> findById(long id);
}
