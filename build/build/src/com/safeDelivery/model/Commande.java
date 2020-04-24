package com.safeDelivery.model;

import java.time.LocalDateTime;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Commande {
	private LongProperty id;
	private ObjectProperty<Client> client;
	private DoubleProperty total;
	private StringProperty etat;
	private ObjectProperty<Livreur> livreur;
	private ObjectProperty<LocalDateTime> dateCommande;
	private ObjectProperty<LocalDateTime> dateLivraison;

	public LocalDateTime getDateCommande() {
		return dateCommande.get();
	}

	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande.set(dateCommande);
	}

	public ObjectProperty<LocalDateTime> dateCommandeProperty() {
		return this.dateCommande;
	}

	public LocalDateTime getDateLivraison() {
		return dateLivraison.get();
	}

	public void setDateLivraison(LocalDateTime dateLivraison) {
		this.dateLivraison.set(dateLivraison);
	}

	public ObjectProperty<LocalDateTime> dateLivraisonProperty() {
		return this.dateLivraison;
	}

	public Commande(Long id, Client client, double total, String etat, Livreur livreur, LocalDateTime dateCommande,
			LocalDateTime dateLivraison) {
		this.id = new SimpleLongProperty(id);
		this.client = new SimpleObjectProperty<Client>(client);
		this.total = new SimpleDoubleProperty(total);
		this.etat = new SimpleStringProperty(etat);
		this.livreur = new SimpleObjectProperty<Livreur>(livreur);
		this.dateCommande = new SimpleObjectProperty<LocalDateTime>(dateCommande);
		this.dateLivraison = new SimpleObjectProperty<LocalDateTime>(dateLivraison);
	}

	public Commande(Client client, double total, String etat, Livreur livreur, LocalDateTime dateCommande,
			LocalDateTime dateLivraison) {
		this.id = new SimpleLongProperty();
		this.client = new SimpleObjectProperty<Client>(client);
		this.total = new SimpleDoubleProperty(total);
		this.etat = new SimpleStringProperty(etat);
		this.livreur = new SimpleObjectProperty<Livreur>(livreur);
		this.dateCommande = new SimpleObjectProperty<LocalDateTime>(dateCommande);
		this.dateLivraison = new SimpleObjectProperty<LocalDateTime>(dateLivraison);
	}

	public Commande(Client client, double total) {
		this.id = new SimpleLongProperty();
		this.client = new SimpleObjectProperty<Client>(client);
		this.total = new SimpleDoubleProperty(total);
		this.etat = new SimpleStringProperty();
		this.livreur = new SimpleObjectProperty<Livreur>();
		this.dateCommande = new SimpleObjectProperty<LocalDateTime>();
		this.dateLivraison = new SimpleObjectProperty<LocalDateTime>();
	}

	public Commande() {
		this.id = new SimpleLongProperty();
		this.client = new SimpleObjectProperty<Client>();
		this.total = new SimpleDoubleProperty();
		this.etat = new SimpleStringProperty();
		this.livreur = new SimpleObjectProperty<Livreur>();
		this.dateCommande = new SimpleObjectProperty<LocalDateTime>();
		this.dateLivraison = new SimpleObjectProperty<LocalDateTime>();
	}

	// id
	public Long getId() {
		return this.id.getValue();
	}

	public LongProperty IdProperty() {
		return id;
	}

	public void setId(long id) {
		this.id.set(id);
	}

// client
	public ObjectProperty<Client> clientProperty() {
		return client;
	}

	public Client getClient() {
		return this.client.get();
	}

	public void setClient(ObjectProperty<Client> client) {
		this.client = client;
	}

// total
	public double getTotal() {
		return total.get();
	}

	public DoubleProperty totalProperty() {
		return this.total;
	}

	public void setTotal(DoubleProperty total) {
		this.total = total;
	}

// etat
	public String getEtat() {
		return this.etat.get();
	}

	public StringProperty etatProperty() {
		return this.etat;
	}

	public void setEtat(StringProperty etat) {
		this.etat = etat;
	}

// livreur
	public ObjectProperty<Livreur> livreurProperty() {
		return livreur;
	}

	public void setLivreur(ObjectProperty<Livreur> livreur) {
		this.livreur = livreur;
	}

	public Livreur getLivreur() {
		return this.livreur.get();
	}

}
