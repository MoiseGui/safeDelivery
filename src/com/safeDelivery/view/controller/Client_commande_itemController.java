package com.safeDelivery.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.safeDelivery.model.Commande_item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class Client_commande_itemController implements Initializable {

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
	    private Label lblEtat;
	void fillCommandeItem() {
		if (this.commande_item == null)
			System.out.println("Commande_iten");
		if (this.commande_item.getPlat() == null)
			System.out.println("Plat");
		if (this.commande_item.getPlat().getNom() == null)
			System.out.println(("Nom du plat"));
		lbl_nomPlat.setText(this.commande_item.getPlat().getNom());
		lbl_Qte.setText(String.valueOf(commande_item.getQte()));
		lbl_total.setText(String.valueOf(this.commande_item.getPlat().getPrix() * this.commande_item.getQte()));
		lblEtat.setText(this.commande_item.getEtat());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
