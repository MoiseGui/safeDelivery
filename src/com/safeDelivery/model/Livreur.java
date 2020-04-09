package com.safeDelivery.model;

import javafx.beans.property.SimpleIntegerProperty;

public class Livreur extends User {
	private SimpleIntegerProperty busy;

	public Livreur() {
		super();
		this.setCategorie(3);
		this.busy = new SimpleIntegerProperty();
	}

	public Livreur(String nom, String prenom, String email, String pass, String tel, int enable) {
		super(nom, prenom, email, pass, tel, 3, enable);
		this.busy = new SimpleIntegerProperty(1);
	}

	public Livreur(Long id, String nom, String prenom, String email, String pass, String tel,
			int enable) {
		super(id, nom, prenom, email, pass, tel, 3, enable);
		this.busy = new SimpleIntegerProperty(1);
	}

	public int getBusy() {
		return busy.get();
	}

	public void setBusy(int busy) {
		this.busy.set(busy);
	}

	public SimpleIntegerProperty busyProperty() {
		return this.busy;
	}
}
