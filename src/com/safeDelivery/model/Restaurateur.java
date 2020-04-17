package com.safeDelivery.model;

import javafx.beans.property.LongProperty;

public class Restaurateur extends User {
	private LongProperty id;
	
	

	public Long getId() {
		return id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}
	
	public LongProperty idProperty() {
		return this.id;
	}

	public Restaurateur() {
		super();
		this.setCategorie(2);
	}

	public Restaurateur( User user ) {
		super(user);
	}
	public Restaurateur(Long id, String nom, String prenom, String email, String pass, String tel,
			int enable) {
		super(id, nom, prenom, email, pass, tel, 2, enable);
	}

	public Restaurateur(String nom, String prenom, String email, String pass, String tel, int enable) {
		super(nom, prenom, email, pass, tel, 2, enable);
	}
	
	
}
