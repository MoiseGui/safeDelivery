package com.safeDelivery.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Zone {
	private LongProperty id;
	private StringProperty nom;
	private ObjectProperty<Ville> ville;	
	
	
	public Zone() {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty();
		this.ville = new SimpleObjectProperty<Ville>();
	}


	public Zone(Long id, String nom) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.ville = new SimpleObjectProperty<Ville>();
	}
	

	public Zone(Long id, String nom, Ville ville) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.ville = new SimpleObjectProperty<Ville>(ville);
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


	public ObjectProperty<Ville> villeProperty() {
		return ville;
	}
	
	public Ville getVille() {
		return this.ville.get();
	}


	public void setVille(Ville ville) {
		this.ville.set(ville);
	}

	
	
}
