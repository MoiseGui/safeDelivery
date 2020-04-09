package com.safeDelivery.view.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.safeDelivery.model.User;

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
	private int categorie; // 1 2 3
	User user = new User();

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
	private Pane addRestaurant;

	@FXML
	private TextField tNomResto;

	@FXML
	private Button btnStart;

	@FXML
	private ImageView btnBack;

	@FXML
	private Label lblForgot11;

	@FXML
	private TextField tNomResto1;

	@FXML
	private Label lblForgot112;

	@FXML
	private Label lblForgot113;

	@FXML
	private Label lblForgot1131;

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
	private Pane pnlSignUpNomPrenom;

	@FXML
	private ImageView btnBackNomPrenom;

	@FXML
	private TextField tprenom;

	@FXML
	private Button btnContinuerNomPrenom;

	@FXML
	private Label lblForgot1111;

	@FXML
	private TextField tnom;

	@FXML
	private Label lblForgot11111;

	@FXML
	private TextField ttel;

	@FXML
	private Label lblForgot111111;

	@FXML
	private Pane pnlSignUpEmailPass;

	@FXML
	private ImageView btnBackEmailPass;

	@FXML
	private Button btnContinuerEmailPass;

	@FXML
	private Label lblForgot111;

	@FXML
	private PasswordField tPassInsc;

	@FXML
	private PasswordField tpassConfirm;

	@FXML
	private Pane pnlChoix;

	@FXML
	private ImageView btnBackChoix;

	@FXML
	private Button choixClient;

	@FXML
	private Button choixLivreur;

	@FXML
	private Button choixResto;
	
	@FXML
    private Label lblErrorEmail;

    @FXML
    private Label lblErrorPass;

	@FXML
	void handleButtonAction(ActionEvent event) {
		if (event.getSource().equals(btnInscr)) {
			new ZoomIn(pnlChoix).play();
			pnlChoix.toFront();
		}

		if (event.getSource().equals(choixClient)) {
			this.categorie = 1;
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}

		if (event.getSource().equals(choixResto)) {
			this.categorie = 2;
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}

		if (event.getSource().equals(choixLivreur)) {
			this.categorie = 3;
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}

		if (event.getSource().equals(btnContinuerEmailPass)) {
			boolean error = false;
			
			if(tEmail.getText() == null || tEmail.getText().length() == 0 || !isValidEmail(tEmail.getText())) {
				error = true;
				tEmail.setText("");
//				tEmail.setStyle("-fx-border-color:red");
//				tEmail.setStyle("-fx-background-color:red");
//				tEmail.setStyle("-fx-border-color:#424242; -fx-border-width:1px;-fx-background-color:rgba(255, 255, 255, 0.87);");

				lblErrorEmail.setText("Adresse email incorrecte");
			}
			
			if(tPassInsc.getText() == null || tpassConfirm.getText() == null || tPassInsc.getText().length() < 6) {
				error = true;
				tPassInsc.setText("");
				tpassConfirm.setText("");
				lblErrorPass.setText("Mot de passe incorrecte. Au moins 6 caractères.");
			}
			else if(! tPassInsc.getText().equals(tpassConfirm.getText())) {
				error = true;
				tPassInsc.setText("");
				tpassConfirm.setText("");
				lblErrorPass.setText("Les 2 mots de passe doivent être conformes.");
			}
			
			
			if(!error) {
				user.setEmail(tEmail.getText());
				user.setPass(tPassInsc.getText());
				new ZoomIn(pnlSignUpNomPrenom).play();
				pnlSignUpNomPrenom.toFront();
//				System.out.println(user);
			}
		}
	}
	
	@FXML
	void handleMouseEvent(MouseEvent event) {
		if (event.getSource().equals(btnBackChoix)) {
			new ZoomIn(pnlLogin).play();
			pnlLogin.toFront();
		}
		if (event.getSource().equals(btnBackEmailPass)) {
			new ZoomIn(pnlChoix).play();
			pnlChoix.toFront();
		}
		if (event.getSource().equals(btnBackNomPrenom)) {
			new ZoomIn(pnlSignUpEmailPass).play();
			pnlSignUpEmailPass.toFront();
		}
		if (event.getSource().equals(btnClose)) {
			new ZoomOut(container).play();
			Platform.exit();
//    		System.exit(0);
		}
		if (event.getSource().equals(btnMin)) {
			new FadeInDown(container).play();
//    		System.exit(0);
		}
	}

	public boolean isValidEmail(String emailAddress) {
//		return emailAddress.contains(" ") && emailAddress.matches(".+@.+\\.[az]+"); String regex = ^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$
		String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
		
	}

}
