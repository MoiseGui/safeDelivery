package com.safeDelivery.service;

import com.safeDelivery.model.Adresse;

public interface AdresseService {
	public Adresse findById(long id);
	public long saveAdresse(Adresse adresse);
}
