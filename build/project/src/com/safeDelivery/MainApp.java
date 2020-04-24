package com.safeDelivery;

import java.io.IOException;
import java.sql.Connection;

import com.safeDelivery.utils.SingletonConnexion;
import com.safeDelivery.view.controller.LoginController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

	private Stage primaryStage;
	private Parent rootLayout;
	BorderPane animation;
	private Connection connection;

	@Override
	public void start(Stage primaryStage) {
		connection = SingletonConnexion.startConnection();
		this.primaryStage = primaryStage;
//        primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.setResizable(false);
//        this.primaryStage
		this.primaryStage.setTitle("SafeDelivery");
		this.primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));
		try {
			showAnimation();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void showHome() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/main.fxml"));
			rootLayout = (BorderPane) loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			LoginController controller = loader.getController();
			controller.setMainApp(this);
			controller.setConnection(connection);
			controller.fillVillesAndZones();

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void showAnimation() throws IOException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/Preload.fxml"));
		animation = (BorderPane) loader.load();
		Scene scene = new Scene(animation);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		new ScreenWait().start();
	}

	class ScreenWait extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(5000);

				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						showHome();
						animation.getScene().getWindow().hide();
					}
				});

			} catch (Exception e) {
				e.printStackTrace();
			}
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
