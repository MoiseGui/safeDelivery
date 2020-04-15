package com.safeDelivery.service;

import com.safeDelivery.model.Restaurant;
import com.safeDelivery.model.Restaurateur;

public interface RestaurantService {
	public Restaurant findByRestaurateur(Restaurateur restaurateur);
	public long addRestaurant(Restaurant restaurant);
	public long existByNom(String nom);
	public int modifyResto(Restaurant oldRestaurant, Restaurant newRestaurant);
}
