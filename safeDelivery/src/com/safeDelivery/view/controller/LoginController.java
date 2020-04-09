package com.safeDelivery.view.controller;

import animatefx.animation.FadeInDown;
import animatefx.animation.ZoomIn;
import animatefx.animation.ZoomOut;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class LoginController {
	
	 @FXML
	    private AnchorPane container;
	
	 @FXML
	    private Pane anchRoot;

    @FXML
    private Circle btnClose;
    
    @FXML
    private Circle btnMin;

    @FXML
    private Label lblForgot2;

    @FXML
    private StackPane pnlStack;

    @FXML
    private Pane pnlSignUp;

    @FXML
    private TextField tEmail1;

    @FXML
    private Button btnStart;
    
    @FXML
    private ImageView btnBack;

    @FXML
    private Label lblForgot11;

    @FXML
    private Pane pnlLogin;

    @FXML
    private TextField tEmail;

    @FXML
    private PasswordField tPass;

    @FXML
    private Button btnConn;

    @FXML
    private Button btnInscr;

    @FXML
    private Label lblForgot;

    @FXML
    private Label lblForgot1;

    @FXML
    void handleButtonAction(ActionEvent event) {
    	if(event.getSource().equals(btnInscr)) {
    		new ZoomIn(pnlSignUp).play();
    		pnlSignUp.toFront();
    	}
    }

    @FXML
    void handleMouseEvent(MouseEvent event) {
    	if(event.getSource().equals(btnBack)) {
    		new ZoomIn(pnlLogin).play();
    		pnlLogin.toFront();
    	}
    	if(event.getSource().equals(btnClose)) {
    		new ZoomOut(container).play();
    		Platform.exit();
//    		System.exit(0);
    	}
    	if(event.getSource().equals(btnMin)) {
    		new FadeInDown(container).play();
//    		System.exit(0);
    	}
    }
    
    

}
