package com.safeDelivery.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Livreur extends User {
	private BooleanProperty busy;
	
	
	public Livreur() {
		super();
		this.busy = new SimpleBooleanProperty();
	}
	
	public Livreur(Long id, String nom, String prenom, String email, String pass, String tel, boolean busy,int categorie,int enable) {
		super(id, nom, prenom, email, pass, tel, categorie,enable);
		this.busy = new SimpleBooleanProperty(busy);
	}

	public boolean isBusy() {
		return busy.get();
	}

	public void setBusy(boolean busy) {
		this.busy.set(busy);
	}
	
	public BooleanProperty busyProperty() {
		return this.busy;
	}
	
}
