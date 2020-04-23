package com.safeDelivery.service;

import java.util.List;

import com.safeDelivery.model.Restaurant;

public interface RestaurantService {
	public Restaurant findByRestaurateur(long idRestaurateur);
	public long addRestaurant(Restaurant restaurant);
	public long existByNom(String nom);
	public int modifyResto(Restaurant oldRestaurant, Restaurant newRestaurant);
	List<String> findAll();
	public List<String> findRestoByVille(String ville);
}
