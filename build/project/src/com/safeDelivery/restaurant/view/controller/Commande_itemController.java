package com.safeDelivery.restaurant.view.controller;


import java.net.URL;
import java.util.ResourceBundle;

import com.safeDelivery.model.Commande_item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Commande_itemController implements Initializable {
	private boolean changed = false;
	private String etat;
	private Commande_item commande_item;
	
	public String getEtat() {
		return etat;
	}

	public void setCommande_item(Commande_item commande_item) {
		this.commande_item = commande_item;
	}

	public Commande_item getCommande_item() {
		return commande_item;
	}

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}


	@FXML
	private ImageView img_plat;

	@FXML
	private Label lbl_nomPlat;

	@FXML
	private Label lbl_Qte;

	@FXML
	private Label lbl_total;

	@FXML
	private ComboBox<String> cbx_etat;
	
	@FXML
	void changeHandler() {
		this.changed = true;
		this.etat = cbx_etat.getValue();
	}
	
	void fillCommandeItem() {
		if(this.commande_item == null) System.out.println("Commande_iten");
		if(this.commande_item.getPlat() == null) System.out.println("Plat");
		if(this.commande_item.getPlat().getNom() == null) System.out.println(("Nom du plat"));
		lbl_nomPlat.setText(this.commande_item.getPlat().getNom());
		lbl_Qte.setText(String.valueOf(commande_item.getQte()));
		lbl_total.setText(String.valueOf(this.commande_item.getPlat().getPrix() * this.commande_item.getQte()));
		cbx_etat.setValue(this.commande_item.getEtat());
		this.etat = this.commande_item.getEtat();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbx_etat.getItems().add("En attente");
		cbx_etat.getItems().add("En traitement");
		cbx_etat.getItems().add("Prêt");
		cbx_etat.getItems().add("Remis au livreur");
		cbx_etat.setValue("En attente");
		
	}
	
}
