package com.safeDelivery.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import animatefx.animation.Bounce;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class PreloadController implements Initializable{
	
	
	@FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new Bounce(circle1).setCycleDuration(6).setCycleCount(10).setDelay(Duration.valueOf("500ms")).play();
		new Bounce(circle2).setCycleDuration(6).setCycleCount(10).setDelay(Duration.valueOf("1000ms")).play();
		new Bounce(circle3).setCycleDuration(6).setCycleCount(10).setDelay(Duration.valueOf("1100ms")).play();
	}
	
	
	
    
    
   
}
