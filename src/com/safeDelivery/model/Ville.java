package com.safeDelivery.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ville {
	private LongProperty id;
	private StringProperty nom;
	
	
	
	public Ville() {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty();
	}


	public Ville( String nom) {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty(nom);
	}

	public Ville(Long id, String nom) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
	}


	public Long getId() {
		return this.id.get();
	}
	
	public LongProperty idProperty() {
		return id;
	}


	public void setId(Long id) {
		this.id.set(id);
	}


	public String getNom() {
		return this.nom.get();
	}
	
	
	public StringProperty nomProperty() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom.set(nom);
	}
	
}
