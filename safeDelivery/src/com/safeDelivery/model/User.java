package com.safeDelivery.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private DoubleProperty id;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty email;
	private StringProperty pass;
	private StringProperty tel;
	
	public User() {
		this.id = new SimpleDoubleProperty();
		this.nom = new SimpleStringProperty();
		this.prenom = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.pass = new SimpleStringProperty();
		this.tel = new SimpleStringProperty();
	}

	public User(Double id, String nom, String prenom, String email, String pass,
			String tel) {
		super();
		this.id.set(id);
		this.nom.set(nom);
		this.prenom.set(prenom);
		this.email.set(email);
		this.pass.set(pass);
		this.tel.set(tel);
	}

	public Double getId() {
		return id.get();
	}

	public void setId(Double id) {
		this.id.set(id);
	}
	
	public DoubleProperty idProperty() {
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

	public String getPrenom() {
		return this.prenom.get();
	}
	
	
	public StringProperty PrenomProperty() {
		return this.prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public StringProperty emailProperty() {
		return this.email;
	}

	public String getPass() {
		return pass.get();
	}

	public void setPass(String pass) {
		this.pass.set(pass);
	}
	
	public StringProperty passProperty() {
		return this.pass;
	}

	public String getTel() {
		return tel.get();
	}

	public void setTel(String tel) {
		this.tel.set(tel);
	}
	
	public StringProperty telProperty() {
		return this.tel;
	}
	
}
