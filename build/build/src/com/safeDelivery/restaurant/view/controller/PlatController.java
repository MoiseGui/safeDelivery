package com.safeDelivery.restaurant.view.controller;

import java.io.IOException;
import java.sql.Connection;

import com.safeDelivery.model.Plat;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.service.impl.PlatServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
	void buttonActionHandler(ActionEvent event) throws IOException {
		if(event.getSource().equals(btn_deletePlat)) {
			parent.deletePlat(plat);
		}
		if(event.getSource().equals(btn_EditPlat)) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainResto.class.getResource("view/EditPlat.fxml"));
			BorderPane root = loader.load();
			EditPlatController controller = loader.getController();
			controller.setConnection(connection);
			controller.setRestaurant(parent.getRestaurant());
			controller.setPlat(plat);
			controller.setParent(parent);
			controller.setEdit(true);
			controller.fillPlat();
			Stage primaryStage = new Stage();
			primaryStage.setTitle("SafeDelivery - Modifier le plat "+plat.getNom());
			primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));
			primaryStage.setScene(new Scene(root));
			primaryStage.setResizable(false);
			controller.setStage(primaryStage);
			primaryStage.initOwner(parent.getStage());
			primaryStage.show();
		}
	}

	public void fillPlat() {
		lbl_nomPlat.setText(plat.getNom());
		lbl_prixPlat.setText(String.valueOf(plat.getPrix()));

		PlatServiceImpl platServiceImpl = new PlatServiceImpl(connection);
		lbl_revenuPlat.setText(String.valueOf(platServiceImpl.getRevenu(plat.getId(), plat.getPrix())));
		
		if (plat.getImage() != null && !plat.getImage().isEmpty()) {
			img_plat.setImage(new Image(plat.getImage()));
		}
		
	}
}
