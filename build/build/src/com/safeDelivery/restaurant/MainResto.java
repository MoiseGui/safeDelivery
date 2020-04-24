package com.safeDelivery.restaurant;

import java.sql.Connection;
import java.time.LocalDate;

import com.safeDelivery.restaurant.view.controller.HomeController;
import com.safeDelivery.service.impl.RestaurantServiceImpl;
import com.safeDelivery.utils.SingletonConnexion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainResto extends Application {
    private double x, y;
    Connection connection = SingletonConnexion.startConnection();
    
    public Connection getConnection() {
		return connection;
	}


	@Override
    public void start(Stage primaryStage) throws Exception {
    	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainResto.class.getResource("view/Home.fxml"));
    	
//        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Parent root = (AnchorPane) loader.load();
        
        HomeController controller = loader.getController();
        RestaurantServiceImpl impl = new RestaurantServiceImpl(connection);
        controller.setRestaurant(impl.findByRestaurateur(21));
//        controller.setMain(this);
        connection = SingletonConnexion.startConnection();
        if(connection == null) {
        	System.out.println("Connection nulle");
        }
        else {
        	controller.setConnection(connection);
        }
        
        primaryStage.setScene(new Scene(root));
        
        //set stage borderless
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        primaryStage.show();
        controller.loadAllCommandes(LocalDate.now());
        controller.setNomResto();
        
    }


    @Override
	public void stop() throws Exception {
		SingletonConnexion.closeConnection(connection);
		super.stop();
	}


	public static void main(String[] args) {
        launch(args);
    }
}
