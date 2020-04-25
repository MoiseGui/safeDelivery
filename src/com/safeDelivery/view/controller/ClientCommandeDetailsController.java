package com.safeDelivery.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.safeDelivery.MainApp;
import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Commande_item;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.restaurant.view.controller.Commande_itemController;
import com.safeDelivery.service.impl.Commande_itemServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientCommandeDetailsController implements Initializable {

	Stage primaryStage;
	List<Commande_itemController> commande_itemControllers = new ArrayList<Commande_itemController>();
	
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


   private MainApp main;
   

	public MainApp getMain() {
	return main;
}


public void setMain(MainApp main) {
	this.main = main;
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
    private Label lbl_DateCmd;

    @FXML
    private Label lbl_Total;

  

    @FXML
    private Button fermer;
	private Restaurant restaurant;
	
    
    public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	@FXML
    void buttonActionHandler(ActionEvent event) {
    	
    	if(event.getSource().equals(fermer)) {
    		this.primaryStage.close();
    	}
    }
    
    public void fillDetails() {
    	System.out.println("ici c'est fill details");
    	lbl_idCommande.setText(String.valueOf(this.commande.getId()));
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
    	            loader.setLocation(MainApp.class.getResource("view/Client_commande_item.fxml"));
    	            nodes[i] = (HBox) loader.load();
    				
    				
    	            Client_commande_itemController controller = loader.getController();
    				controller.setCommande_item(commande_items.get(i));
    				controller.fillCommandeItem();
    				
    				
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


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
