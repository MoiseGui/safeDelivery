package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Livreur;
import com.safeDelivery.model.Zone;

public interface Peut_LivrerService {
public List<Zone> findByLivreur(Livreur livreur);
public List<Livreur> findByZone(Zone zone);

}
