package com.safeDelivery.model;

import javafx.beans.property.SimpleIntegerProperty;

public class Livreur extends User {
	private SimpleIntegerProperty busy;

	public Livreur() {
		super();
		this.busy = new SimpleIntegerProperty();
	}

	public Livreur(String nom, String prenom, String email, String pass, String tel, int categorie, int enable) {
		super(nom, prenom, email, pass, tel, categorie, enable);
		this.busy = new SimpleIntegerProperty(1);
	}

	public Livreur(Long id, String nom, String prenom, String email, String pass, String tel, int categorie,
			int enable) {
		super(id, nom, prenom, email, pass, tel, categorie, enable);
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
