package com.safeDelivery.restaurant.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.utils.DateTimeUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CommandeController implements Initializable {

	private Connection connection;

	private Commande commande = new Commande();
	
	private Restaurant restaurant;

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		if (connection == null)
			System.out.println("Connection nulle dans le controller");
		this.connection = connection;
	}

	@FXML
	private Label lbl_nomClient;

	@FXML
	private Label lbl_heureCommande;

	@FXML
	private Label lbl_HeureLivraison;

	@FXML
	private Label lbl_totalCommande;

	@FXML
	private Label lbl_status;

	@FXML
	private Button btn_voirCommande;

	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void fillCommmande() {
		if (this.commande == null) {
			System.out.println("Commande nulle");
		} else {
			if(commande.getClient() == null) {
				System.out.println("Client nulle");
			}
			else {
				lbl_nomClient.setText(commande.getClient().getNom());
			}
			lbl_heureCommande.setText(DateTimeUtil.format(commande.getDateCommande()).substring(11));
			if (commande.getDateLivraison() != null) {
				lbl_HeureLivraison.setText(DateTimeUtil.format(commande.getDateLivraison()).substring(11));
			} else
				lbl_HeureLivraison.setText(commande.getEtat());
			lbl_totalCommande.setText(String.valueOf(commande.getTotal()));
			lbl_status.setText(commande.getEtat());
		}
	}

	@FXML
	public void showDetails() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainResto.class.getResource("view/CommandeDetails.fxml"));

//        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
		BorderPane details = (BorderPane) loader.load();

		CommandeDetailsController controller = loader.getController();
		controller.setCommande(this.commande);
		controller.setConnection(this.connection);
		controller.setRestaurant(this.restaurant);

		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(details));
		primaryStage.setTitle("Détails de la commande n° " + this.commande.getId());
		primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));
		primaryStage.setResizable(false);
		primaryStage.initOwner(this.stage);
		controller.setPrimaryStage(primaryStage);
//        primaryStage.initOwner(this);
		primaryStage.show();
		controller.fillDetails();
	}

	public void setOwnerStage(Stage stage) {
		this.stage = stage;
	}

}
