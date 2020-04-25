package com.safeDelivery.service;

import com.safeDelivery.model.Restaurateur;

public interface RestaurateurService {
	public long addRestaurateur(Restaurateur restaurateur);
	public Restaurateur findByid(long id);
}
