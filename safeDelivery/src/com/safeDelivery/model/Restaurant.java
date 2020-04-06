package com.safeDelivery.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Restaurant {
	private LongProperty id;
	private StringProperty nom;
	private StringProperty adresse;
	private ObjectProperty<Restaurateur> restaurateur;

	public Restaurant() {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty();
		this.adresse = new SimpleStringProperty();
		this.restaurateur = new SimpleObjectProperty<Restaurateur>();
	}

	public Restaurant(Long id, String nom, String adresse) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.adresse = new SimpleStringProperty(adresse);
	}

	public Restaurant(Long id, String nom, String adresse, Restaurateur restaurateur) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.adresse = new SimpleStringProperty(adresse);
		this.restaurateur = new SimpleObjectProperty<Restaurateur>(restaurateur);
	}

	public Long getId() {
		return this.id.get();
	}

	public void setId(Long id) {
		this.id.set(id);
	}

	public LongProperty idProperty() {
		return this.id;
	}

	public String getNom() {
		return this.nom.get();
	}

	public StringProperty nomProperty() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getAdress() {
		return this.adresse.get();
	}

	public StringProperty  adresseProperty() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}

	public ObjectProperty<Restaurateur> RestaurateurProperty() {
		return restaurateur;
	}

	public Restaurateur getRestaurateur() {
		return restaurateur.get();
	}

	public void setRestaurateur(Restaurateur restaurateur) {
		this.restaurateur.set(restaurateur);
		
	}

}
