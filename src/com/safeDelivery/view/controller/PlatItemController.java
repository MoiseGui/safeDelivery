package com.safeDelivery.view.controller;

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
import javafx.scene.text.Text;

public class PlatItemController implements Initializable {

	Plat plat = new Plat();
	ClientHomeController parent;

	public ClientHomeController getParent() {
		return parent;
	}

	public void setParent(ClientHomeController parent) {
		this.parent = parent;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	@FXML
	private ImageView ImgPlat;

	@FXML
	private Label lblNom;

	@FXML
	private Label lblPrix;

	@FXML
	private Text lblDesc;

	@FXML
	private Button BtnPanier;

	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private Connection conn;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@FXML
	void addPanier(ActionEvent event) {
		PanierServiceImpl panierService = new PanierServiceImpl(conn);
		Panier panier = new Panier(plat, client, 1);
		int result = panierService.existsBy(client.getId(), plat.getId());
		System.out.println("Résultat qte " + result);
		if (result > 0) {
			panier.setQte(result + 1);
			panierService.upgradePanier(client, plat.getId(), panier.getQte());
		} else {
			panierService.addPanier(panier);
		}
		parent.getPaniers().add(panier);
		parent.showPanierCout();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	public void fillPlat() {
		this.lblNom.setText(this.plat.getNom());
		this.lblDesc.setText(this.plat.getDescription());
		this.lblPrix.setText(this.plat.getPrix().toString());
		
		if (plat.getImage() != null && !plat.getImage().isEmpty()) {
			ImgPlat.setImage(new Image(plat.getImage()));
		}
	}

}
