package com.safeDelivery.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.safeDelivery.model.Client;
import com.safeDelivery.model.Panier;
import com.safeDelivery.model.Plat;
import com.safeDelivery.service.impl.PanierServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PanierController implements Initializable {
	private Client client;
	private Plat plat;
	private Panier panier;
	private double total;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	private ClientHomeController clientHomeController;

	public ClientHomeController getClientHomeController() {
		return clientHomeController;
	}

	public void setClientHomeController(ClientHomeController clientHomeController) {
		this.clientHomeController = clientHomeController;
	}

	public Plat getPlat() {
		return plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	private Connection connection;

	public Connection getConnexion() {
		return connection;
	}

	public void setConnexion(Connection connexion) {
		this.connection = connexion;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@FXML
	private ImageView img_plat;

	@FXML
	private Label lblNom;

	@FXML
	private Label lblPrix;

	@FXML
	private Label lblqte;

	@FXML
	private Button btn_diminuer;

	@FXML
	private ImageView btnsup;

	@FXML
	private Button btn_deletePlat;

	@FXML
	private ImageView btnajout;

	@FXML
	void augmenter(ActionEvent event) {
		System.out.println("Augmenter");
		int quantite = Integer.parseInt(lblqte.getText()) + 1;
		if (quantite < 10) {
			PanierServiceImpl panierService = new PanierServiceImpl(connection);
			panierService.upgradePanier(client, plat.getId(), quantite);
			panier.setQte(quantite);
			total += plat.getPrix();
			lblPrix.setText(String.valueOf(total));
			lblqte.setText(String.valueOf(quantite));
			clientHomeController.updateTotal();
		}
	}

	@FXML
	void diminuer(ActionEvent event) throws IOException {
		System.out.println("Diminuer");
		PanierServiceImpl panierService = new PanierServiceImpl(connection);
		int quantite = Integer.parseInt(lblqte.getText()) - 1;
		if (quantite > 0) {
			panierService.upgradePanier(client, plat.getId(), quantite);
			panier.setQte(quantite);
			total -= plat.getPrix();
			lblPrix.setText(String.valueOf(total));
			lblqte.setText(String.valueOf(quantite));
			clientHomeController.updateTotal();
		} else {
			System.out.println("Resultat du delete" + panierService.deletePanier(panier));
			clientHomeController.removePanier(panier);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	public void fillPanier(Panier panier) {

		total = panier.getPlat().getPrix() * panier.getQte();

		lblNom.setText(panier.getPlat().getNom());
		lblPrix.setText(String.valueOf(panier.getPlat().getPrix()));
		System.out.println("fill panier" + String.valueOf(panier.getQte()));
		lblqte.setText(String.valueOf(panier.getQte()));

		if (plat.getImage() != null && !plat.getImage().isEmpty()) {
			img_plat.setImage(new Image(panier.getPlat().getImage()));
		}

	}

}
