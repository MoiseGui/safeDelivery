package com.safeDelivery.restaurant.view.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Commande_item;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.service.impl.Commande_itemServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommandeDetailsController {
	Stage primaryStage;
	List<Commande_itemController> commande_itemControllers = new ArrayList<Commande_itemController>();
	
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}




	private Commande commande = new Commande();

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

    @FXML
    private VBox pnl_commandeItems;

    @FXML
    private Label lbl_idCommande;

    @FXML
    private Label lbl_NomClient;
    
    @FXML
    private Label lbl_DateCmd;

    @FXML
    private Label lbl_Total;

    @FXML
    private Button btn_Annuler;

    @FXML
    private Button btn_valider;
	private Restaurant restaurant;
	
    
    public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	@FXML
    void buttonActionHandler(ActionEvent event) {
    	
    	if(event.getSource().equals(btn_Annuler)) {
    		this.primaryStage.close();
    	}
    	
    	if(event.getSource().equals(btn_valider)) {
    		boolean found = false;
    		for (Commande_itemController controller : commande_itemControllers) {
				if(controller.isChanged()) {
					found = true;
					Commande_itemServiceImpl commande_itemServiceImpl = new Commande_itemServiceImpl(connection);
					int i = commande_itemServiceImpl.setEtat(controller.getCommande_item().getCommande().getId(), controller.getCommande_item().getPlat().getId(), controller.getEtat());
					System.out.println("Change result " + i);
					if (i <= 0) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.initOwner(primaryStage);
						alert.setTitle("Safe Delivery");
						alert.setHeaderText("Erreur lors de la connexion");
						alert.setContentText("Les modifications n'ont pas été enregistées. Merci de rééssayer");
						alert.showAndWait();
					} else {
						controller.setChanged(false);
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.initOwner(primaryStage);
						alert.setTitle("Safe Delivery");
						alert.setHeaderText("Succès");
						alert.setContentText("Modifications enregistrées avec succès");
						alert.showAndWait();
					}
				}
			}
    		if (!found) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(primaryStage);
				alert.setTitle("Safe Delivery");
				alert.setHeaderText("Aucun Changement");
				alert.setContentText("Aucun changement à signaler. Merci de rééssayer");
				alert.showAndWait();
			}
    		
    	}
    	
    }
    
    
    
    void fillDetails() {
    	lbl_idCommande.setText(String.valueOf(this.commande.getId()));
    	lbl_NomClient.setText(this.commande.getClient().getNom());
    	lbl_Total.setText(String.valueOf(this.commande.getTotal()));
    	lbl_DateCmd.setText(String.valueOf(this.commande.getDateCommande()).substring(0, 10));
    	
    	Commande_itemServiceImpl commande_itemServiceImpl = new Commande_itemServiceImpl(this.connection);
    	List<Commande_item> commande_items = commande_itemServiceImpl.findByIdCommandeAndIdResto(this.commande.getId(), this.restaurant.getId());
    	
    	if(commande_items == null || commande_items.isEmpty()) {
    		System.out.println("La liste de commandes est nulle ou est vide dans fillDetails()");
    	}
    	else {
    		System.out.println("commandesToday size "+ commande_items.size());
    		Node[] nodes = new Node[commande_items.size()];
    		for (int i = 0; i < nodes.length; i++) {
    			try {
    				
    				final int j = i;
    				
    				FXMLLoader loader = new FXMLLoader();
    	            loader.setLocation(MainResto.class.getResource("view/commande_item.fxml"));
    	            nodes[i] = (HBox) loader.load();
    				
    				
    				Commande_itemController controller = loader.getController();
    				controller.setCommande_item(commande_items.get(i));
    				controller.fillCommandeItem();
    				commande_itemControllers.add(controller);
    				
    				
    				//give the items some effect
    				nodes[i].setOnMouseEntered(event -> {
    					nodes[j].setStyle("-fx-background-color : #363B46");
    				});
    				nodes[i].setOnMouseExited(event -> {
    					nodes[j].setStyle("-fx-background-color : transparent");
    				});
    				pnl_commandeItems.getChildren().add(nodes[i]);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    		
    	}
    }

}
