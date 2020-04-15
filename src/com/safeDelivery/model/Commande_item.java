package com.safeDelivery.model;


import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande_item {
	private LongProperty id_plat;
	private LongProperty id_commande;
	private LongProperty qte;
	private StringProperty etat;
	
	public Commande_item() {
		this.id_plat = new SimpleLongProperty();
		this.id_commande = new SimpleLongProperty();
		this.qte = new SimpleLongProperty();
		this.etat = new SimpleStringProperty();
	}

	public Commande_item(Long id_plat, Long id_commande, Long qte, String etat) {
		this.id_plat = new SimpleLongProperty(id_plat);
		this.id_commande = new SimpleLongProperty(id_commande);
		this.qte = new SimpleLongProperty(qte);
		this.etat = new SimpleStringProperty(etat);
	}

	public Long getId_plat() {
		return id_plat.get();
	}

	public void setId_plat(Long id_plat) {
		this.id_plat.set(id_plat);
	}

	public LongProperty id_platProperty() {
		return id_plat;
	}
	
	public Long getId_commande() {
		return id_commande.get();
	}

	public void setId_commande(Long id_commande) {
		this.id_commande.set(id_commande);
	}
	public LongProperty id_commandeProperty() {
		return id_commande;
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
