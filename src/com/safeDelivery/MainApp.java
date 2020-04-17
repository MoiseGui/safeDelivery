package com.safeDelivery;

import java.io.IOException;
import java.sql.Connection;

import com.safeDelivery.utils.SingletonConnexion;
import com.safeDelivery.view.controller.LoginController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private AnchorPane main;
	private Connection connection;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
//        primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.setResizable(false);
//        this.primaryStage
		this.primaryStage.setTitle("SafeDelivery");
		this.primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));

		initRootLayout();

		showMain();
	}

	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MainBorder.fxml"));
			rootLayout = (BorderPane) loader.load();
//            rootLayout.widthProperty().Bind(main.widthProperty());
			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Montrer le MainBorder
	 */
	public void showMain() {
		try {
			// Load person overview.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/main.fxml"));
			this.main = (AnchorPane) loader.load();
			// Set person overview into the center of root layout.
			rootLayout.setCenter(main);
			LoginController controller = loader.getController();
			controller.setMainApp(this);
			connection = SingletonConnexion.startConnection();
			controller.setConnection(connection);

			// Give the controller access to the main app.
			// PersonOverviewController controller = loader.getController();
			// controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() throws Exception {
		SingletonConnexion.closeConnection(connection);
		super.stop();
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}
}

// test de push master
