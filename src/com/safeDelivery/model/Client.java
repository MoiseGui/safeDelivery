package com.safeDelivery.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Client extends User{
	private ObjectProperty<Adresse> adresse;

	public Client() {
		super();
		this.adresse = new SimpleObjectProperty<Adresse>();
	}

	public Client( String nom, String prenom, String email, String pass, String tel, Adresse adresse,int enable) {
		super(nom, prenom, email, pass, tel, 1,enable);
		this.adresse = new SimpleObjectProperty<Adresse>(adresse);
	}
	public Client(Long id, String nom, String prenom, String email, String pass, String tel, Adresse adresse,int enable) {
		super(id, nom, prenom, email, pass, tel, 1,enable);
		this.adresse = new SimpleObjectProperty<Adresse>(adresse);
	}

	public Adresse getAdresse() {
		return adresse.get();
	}

	public void setAdresse(Adresse adresse) {
		this.adresse.set(adresse);
	}
	
	public ObjectProperty<Adresse> adresseProperty(){
		return this.adresse;
	}
	
	
}
