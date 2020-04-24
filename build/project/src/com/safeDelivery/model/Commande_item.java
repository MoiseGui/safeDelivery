package com.safeDelivery.model;


import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande_item {
	private ObjectProperty<Plat> plat;
	private ObjectProperty<Commande> commande;
	private LongProperty qte;
	private StringProperty etat;
	
	public Commande_item() {
		this.plat = new SimpleObjectProperty<Plat>();
		this.commande = new SimpleObjectProperty<Commande>();
		this.qte = new SimpleLongProperty();
		this.etat = new SimpleStringProperty();
	}

	public Commande_item(Plat plat, Commande commande, Long qte, String etat) {
		this.plat = new SimpleObjectProperty<Plat>(plat);
		this.commande = new SimpleObjectProperty<Commande>(commande);
		this.qte = new SimpleLongProperty(qte);
		this.etat = new SimpleStringProperty(etat);
	}

	public Plat getPlat() {
		return plat.get();
	}

	public void setPlat(Plat plat) {
		this.plat.set(plat);
	}

	public ObjectProperty<Plat> platProperty() {
		return plat;
	}
	
	public Commande getCommande() {
		return commande.get();
	}

	public void setCommande(Commande commande) {
		this.commande.set(commande);
	}
	public ObjectProperty<Commande> commandeProperty() {
		return commande;
	}

	public Long getQte() {
		return qte.get();
	}

	public void setQte(Long qte) {
		this.qte.set(qte);
	}
	public LongProperty qteProperty() {
		return qte;
	}

	public String getEtat() {
		return etat.get();
	}

	public void setEtat(String etat) {
		this.etat.set(etat);
	}
	
	public StringProperty etatProperty() {
		return etat;
	}
	
}
