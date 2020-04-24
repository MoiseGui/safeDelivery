package com.safeDelivery.service;

import com.safeDelivery.model.Livreur;

public interface LivreurService {
   public long addLivreur(Livreur livreur);
   public long existByid(Livreur livreur);
   public Livreur findById(long id);
}
