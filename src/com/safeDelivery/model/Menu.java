package com.safeDelivery.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Menu {
	private ObjectProperty<Restaurant> restaurant;
	private ObjectProperty<Plat> plat;
	
	public Menu() {
		this.restaurant = new SimpleObjectProperty<Restaurant>();
		this.plat = new SimpleObjectProperty<Plat>();
	}

	public Menu(Restaurant restaurant, Plat plat) {
		super();
		this.restaurant = new SimpleObjectProperty<Restaurant>(restaurant);
		this.plat = new SimpleObjectProperty<Plat>(plat);
	}

	public Restaurant getRestaurant() {
		return restaurant.get();
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant.set(restaurant);
	}
	
	public ObjectProperty<Restaurant> restaurantProperty(){
		return restaurant;
	}
	

	public ObjectProperty<Plat> platProperty() {
		return plat;
	}

	public Plat getPlat() {
		return plat.get();
	}
	
	public void setPlat(Plat plat) {
		this.plat.set(plat);
	}
	
	
}
