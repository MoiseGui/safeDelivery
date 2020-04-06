package com.safeDelivery.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private LongProperty id;
	private StringProperty nom;
	private StringProperty prenom;
	private StringProperty email;
	private StringProperty pass;
	private StringProperty tel;
	
	public User() {
		this.id = new SimpleLongProperty();
		this.nom = new SimpleStringProperty();
		this.prenom = new SimpleStringProperty();
		this.email = new SimpleStringProperty();
		this.pass = new SimpleStringProperty();
		this.tel = new SimpleStringProperty();
	}

	public User(Long id, String nom, String prenom, String email, String pass,
			String tel) {
		this.id = new SimpleLongProperty(id);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.email = new SimpleStringProperty(email);
		this.pass = new SimpleStringProperty(pass);
		this.tel = new SimpleStringProperty(tel);
	}

	public Long getId() {
		return id.get();
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

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", pass=" + pass
				+ ", tel=" + tel + "]";
	}
	
	
	
}
