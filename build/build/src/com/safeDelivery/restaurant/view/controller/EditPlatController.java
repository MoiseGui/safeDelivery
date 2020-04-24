package com.safeDelivery.restaurant.view.controller;

import java.io.File;
import java.sql.Connection;

import com.safeDelivery.model.Plat;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.service.impl.PlatServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditPlatController {

	private Connection connection;
	private boolean edit = false;
	private Plat plat;
	private Restaurant restaurant;
	private HomeController parent;
	private Stage stage;
	private String img_string = "";
	File curentFile;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Plat getPlat() {
		return plat;
	}

	public void setPlat(Plat plat) {
		this.plat = plat;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant resataurant) {
		this.restaurant = resataurant;
	}

	public HomeController getParent() {
		return parent;
	}

	public void setParent(HomeController parent) {
		this.parent = parent;
	}

	@FXML
	private Button btn_image;

	@FXML
	private Label lbl_errorImg;

	@FXML
	private Pane pnl_img;

	@FXML
	private ImageView ImgPlat;

	@FXML
	private Pane pnl_noImg;

	@FXML
	private Label lbl_title;

	@FXML
	private TextField txt_nom;

	@FXML
	private TextField txt_prix;

	@FXML
	private TextArea txt_description;

	@FXML
	private Label lbl_errorNom;

	@FXML
	private Label lbl_errorDescription;

	@FXML
	private Label lbl_errorPrix;

	@FXML
	private Button btn_annuler;

	@FXML
	private Button bnt_valider;

	@FXML
	void buttonActionHandler(ActionEvent event) {
		if (event.getSource().equals(btn_annuler)) {
			this.stage.close();
		}
		if (event.getSource().equals(bnt_valider)) {
			if (!notValidFilds()) {

				PlatServiceImpl impl = new PlatServiceImpl(connection);
				Plat newPlat = new Plat(txt_nom.getText(), Double.parseDouble(txt_prix.getText()),
						txt_description.getText(), img_string);

				boolean error = false;
				// TODO upload the image
				if (edit) {
					long id = impl.changePlat(plat, newPlat, curentFile);
					System.out.println("Résultat de changePlat "+id);
					if (id <= 0) {
						error = true;
					}
				} else {
					long id = impl.addPlat(newPlat, restaurant.getId(), curentFile);
					System.out.println("Id plat added "+id);
					if (id <= 0) {
						error = true;
					} else {
						newPlat.setId(id);
						System.out.println(newPlat.getId());
						parent.getPlats().add(newPlat);
					}
				}

				if(error) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(this.stage);
					alert.setTitle("Safe Delivery");
					alert.setHeaderText("Echec");
					String message = "Un problème est survenue lors de l'enregistrment";
					alert.setContentText(message);
					alert.showAndWait();
					
//					stage.close();
//					parent.fillPlatStats(impl);
//					parent.fillPlats();
				}
				else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.initOwner(this.stage);
					alert.setTitle("Safe Delivery");
					alert.setHeaderText("Succès");
					String message;
					if (edit) {
						message = "Plat modifié avec succès";
					} else {
						message = "Plat ajouté avec succès";
					}
					alert.setContentText(message);
					alert.showAndWait();
					
					stage.close();
					parent.fillPlatStats(impl);
					parent.fillPlats();
				}
			}
		}

		if (event.getSource().equals(btn_image)) {
			FileChooser fc = new FileChooser();
			FileChooser.ExtensionFilter ext1 = new FileChooser.ExtensionFilter("JPG files(*.jpg)", "*.JPG");
//			FileChooser.ExtensionFilter ext2 = new FileChooser.ExtensionFilter("PNG files(*.png)", "*.PNG");
//			fc.getExtensionFilters().addAll(ext1, ext2);
			fc.getExtensionFilters().addAll(ext1);
			File file = fc.showOpenDialog(stage);

			if (file != null) {
				System.out.println(file.getPath());
				Image image = new Image(file.toURI().toString());
				img_string = file.toURI().toString();
				System.out.println("URL: "+img_string);
				ImgPlat.setImage(image);
				curentFile = file;
				pnl_img.toFront();
			} else {
				if (plat.getImage() != null && !plat.getImage().isEmpty()) {
					ImgPlat.setImage(new Image(plat.getImage()));
					img_string = plat.getImage();
					curentFile = new File(img_string);
					pnl_img.toFront();
				} else {
					ImgPlat.setImage(null);
					img_string = "";
					file = null;
					pnl_noImg.toFront();
				}
			}
		}
	}

	private boolean notValidFilds() {
		lbl_errorNom.setText("");
		lbl_errorDescription.setText("");
		lbl_errorPrix.setText("");
		lbl_errorImg.setText("");
		boolean error = false;
		if (txt_nom.getText() == null || txt_nom.getText().isEmpty()) {
			error = true;
			txt_nom.setText(plat.getNom());
			lbl_errorNom.setText("Veuillez saisir un nom valide");
		}
		if (txt_description.getText() == null || txt_description.getText().isEmpty()
				|| txt_description.getText().length() < 10) {
			error = true;
			txt_description.setText(plat.getDescription());
			lbl_errorDescription.setText("Veuillez saisir une description plus longue");
		}
		if (txt_prix.getText() == null || txt_prix.getText().isEmpty() || !isValidPrix(txt_prix.getText())) {
			error = true;
			txt_prix.setText(String.valueOf(plat.getPrix()));
			lbl_errorPrix.setText("Veuillez saisir un prix valide");
		}
		if (ImgPlat.getImage() == null) {
			error = true;
			lbl_errorImg.setText("Veuillez choisir une image");
		}

		return error;
	}

	private boolean isValidPrix(String text) {
		double prix = 0;
		try {
			prix = Double.parseDouble(text);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (prix <= 0)
			return false;
		else
			return true;
	}

	public void fillPlat() {
		if (edit) {
			if (plat.getImage() != null && !plat.getImage().isEmpty()) {
				System.out.println(plat.getImage());
				ImgPlat.setImage(new Image(plat.getImage()));
				pnl_img.toFront();
				curentFile = new File(img_string);
			}
			lbl_title.setText("Modifier");
		} else {
			this.plat = new Plat();
		}

		txt_nom.setText(plat.getNom());
		txt_prix.setText(String.valueOf(plat.getPrix()));
		txt_description.setText(plat.getDescription());
	}
}
