package com.safeDelivery.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Panier  {
	private LongProperty id;
	private ObjectProperty<Plat> plat;
	private ObjectProperty<Client> client;
	private IntegerProperty qte;
	private StringProperty etat;
	public Panier(Plat plat , Client client , int qte  ) {
		this.id =  new SimpleLongProperty();
		this.plat = new SimpleObjectProperty<Plat>(plat);
		this.client = new SimpleObjectProperty<Client>(client);
		this.qte = new SimpleIntegerProperty(qte);
		this.etat = new SimpleStringProperty();
	}
	public Panier(Plat plat, Client client)
	{
		this.id =  new SimpleLongProperty();
		this.plat = new SimpleObjectProperty<Plat>(plat);
		this.client = new SimpleObjectProperty<Client>(client);
		this.qte = new SimpleIntegerProperty();
		this.etat = new SimpleStringProperty();
	}
	public LongProperty getId() {
		return id;
	}
	public void setId(LongProperty id) {
		this.id = id;
	}
	public ObjectProperty<Plat> platProperty() {
		return plat;
	}
	public Plat getPlat() {
		return plat.get();
	}
	public void setPlat(ObjectProperty<Plat> plat) {
		this.plat = plat;
	}
	public ObjectProperty<Client> clientProperty() {
		return client;
	}
	public Client getClient() {
		return client.get();
	}
	public void setClient(Client client) {
		this.client.set(client);
	}
	public IntegerProperty qteProperty() {
		return qte;
	}
	public int getQte() {
		return qte.get();
	}
	public void setQte(int qte) {
		this.qte.set(qte);
	}
	public StringProperty getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat.set(etat);
	}
}
