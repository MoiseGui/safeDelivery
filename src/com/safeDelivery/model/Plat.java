package com.safeDelivery.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Plat {
	private LongProperty id;
	private StringProperty nom;
	private DoubleProperty prix;
	private StringProperty description;
	private StringProperty image;
	
	

	public String getImage() {
		return image.get();
	}


	public void setImage(String image) {
		this.image.set(image);
	}
	
	public StringProperty imageProperty() {
		return this.image;
	}


	public Plat() {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty();
		this.prix = new SimpleDoubleProperty();
		this.description = new SimpleStringProperty();
		this.image = new SimpleStringProperty();
	}
	

	public Plat(String nom, Double prix, String description) {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty(nom);
		this.prix = new SimpleDoubleProperty(prix);
		this.description = new SimpleStringProperty(description);
		this.image = new SimpleStringProperty();
	}
	
	public Plat(String nom, Double prix, String description, String image) {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty(nom);
		this.prix = new SimpleDoubleProperty(prix);
		this.description = new SimpleStringProperty(description);
		this.image = new SimpleStringProperty(image);
	}
	
	
	public Plat(long id, String nom, Double prix, String description, String image) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.prix = new SimpleDoubleProperty(prix);
		this.description = new SimpleStringProperty(description);
		this.image = new SimpleStringProperty(image);
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


	public Double getPrix() {
		return prix.get();
	}


	public void setPrix(Double prix) {
		this.prix.set(prix);
	}
	
	public DoubleProperty prixProperty() {
		return this.prix;
	}


	public String getDescription() {
		return description.get();
	}


	public void setDescription(String description) {
		this.description.set(description);
	}
	
	public StringProperty descriptionProperty() {
		return this.description;
	}
	
	
}
