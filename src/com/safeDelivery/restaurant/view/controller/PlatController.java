package com.safeDelivery.restaurant.view.controller;

import java.sql.Connection;

import com.safeDelivery.model.Plat;
import com.safeDelivery.service.impl.PlatServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PlatController {
	private HomeController parent;
	private Plat plat;
	private Connection connection;
	
	

	public HomeController getParent() {
		return parent;
	}

	public void setParent(HomeController parent) {
		this.parent = parent;
	}

	public Plat getPlat() {
		return plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@FXML
	private ImageView img_plat;

	@FXML
	private Label lbl_nomPlat;

	@FXML
	private Label lbl_prixPlat;

	@FXML
	private Label lbl_revenuPlat;

	@FXML
	private Button btn_EditPlat;
	
	@FXML
	private Button btn_deletePlat;

	@FXML
	void buttonActionHandler(ActionEvent event) {
		if(event.getSource().equals(btn_deletePlat)) {
			parent.deletePlat(plat);
		}
	}

	public void fillPlat() {
		lbl_nomPlat.setText(plat.getNom());
		lbl_prixPlat.setText(String.valueOf(plat.getPrix()));

		PlatServiceImpl platServiceImpl = new PlatServiceImpl(connection);
		lbl_revenuPlat.setText(String.valueOf(platServiceImpl.getRevenu(plat.getId(), plat.getPrix())));
	}
}
