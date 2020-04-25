package com.safeDelivery.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import com.safeDelivery.MainApp;
import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.restaurant.view.controller.CommandeDetailsController;
import com.safeDelivery.utils.DateTimeUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Client_commandeController implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

	private Connection connection;

	private Commande commande = new Commande();

	private Restaurant restaurant;
    private MainApp main;
    
	public MainApp getMain() {
		return main;
	}

	public void setMain(MainApp main) {
		this.main = main;
	}

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
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void fillCommmande() {
		if (this.commande == null) {
			System.out.println("Commande nulle");
		} else {
			if (commande.getClient() == null) {
				System.out.println("Client nulle");
			} else {
				
				lbl_nomClient.setText(String.valueOf(commande.getId()));
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
	    void showDet(ActionEvent event) throws IOException {
		   FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Client_commandeDetails.fxml"));

//	        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
			BorderPane details = (BorderPane) loader.load();

			ClientCommandeDetailsController controller = loader.getController();
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
//	        primaryStage.initOwner(this);
			primaryStage.show();
			controller.setMain(main);
			controller.fillDetails();
	    }

//	@FXML
//	void showDetails(ActionEvent event) {
//		System.out.println("hello there");
//	}

	public void setOwnerStage(Stage stage) {
		this.stage = stage;
	}

}
