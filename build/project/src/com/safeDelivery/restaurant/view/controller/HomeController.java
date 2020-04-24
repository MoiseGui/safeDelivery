package com.safeDelivery.restaurant.view.controller;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.safeDelivery.model.Commande;
import com.safeDelivery.model.Plat;
import com.safeDelivery.model.Restaurant;
import com.safeDelivery.restaurant.MainResto;
import com.safeDelivery.service.impl.CommandeServiceImpl;
import com.safeDelivery.service.impl.PlatServiceImpl;
import com.safeDelivery.service.impl.UserServiceImpl;
import com.safeDelivery.utils.DateTimeUtil;
import com.safeDelivery.utils.DateUtil;
import com.safeDelivery.utils.DateUtilViewFormat;
import com.safeDelivery.utils.saltHashPassword;
import com.safeDelivery.view.controller.LoginController;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable {
	Stage stage;
	LoginController main;
	private Connection connection;
	private List<Commande> commandes = new ArrayList<Commande>();
	private Restaurant restaurant;
	List<Plat> plats = new ArrayList<Plat>();

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<Plat> getPlats() {
		return plats;
	}

	public void setPlats(List<Plat> plats) {
		this.plats = plats;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public void setMain(LoginController main) {
		this.main = main;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		if (connection == null)
			System.out.println("Connection nulle dans le controller");
		this.connection = connection;
	}

	private boolean dejaMenu = false;

	@FXML
	private ImageView img_logoResto;

	@FXML
	private Label lbl_nomResto;

	@FXML
	private Button btnOverview;

	@FXML
	private Button btnMenus;

	@FXML
	private Button btnOrders;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnAccount;

	@FXML
	private Button btnPackages;

	@FXML
	private Pane pnlCustomer;

	@FXML
	private Pane pnlOrders;

	@FXML
	private Pane pnlOverview;

	@FXML
	private DatePicker dateCommandes;

	@FXML
	private Label lbl_cmdsDay;

	@FXML
	private Label lbl_totalCommandes;

	@FXML
	private Label lbl_cmdLivres;

	@FXML
	private Label lbl_cmdEnAttente;

	@FXML
	private Label lbl_cmdInTrait;

	@FXML
	private VBox pnItems;

	@FXML
	private Pane pnlMenus;

	@FXML
	private TextField txtSearPlat;

	@FXML
	private Label lbl_totalPlats;

	@FXML
	private Label lbl_platMode;

	@FXML
	private Label lbl_platMoisCmd;

	@FXML
	private VBox pnlPlats;

	@FXML
	private Button btn_addPlat;

	@FXML
	private Pane pnlAccount;

	@FXML
	private TextField tEmailChangePass;

	@FXML
	private Button btnChangePass;

	@FXML
	private PasswordField txt_oldPass;

	@FXML
	private PasswordField txt_newPass;

	@FXML
	private Label lbl_errorOldPass;

	@FXML
	private Label lbl_errorNewPass;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

//    	this.connection = this.
//    	loadAllCommandes(LocalDate.now());

//    	chargerPage(LocalDate.now());
		dateCommandes.setValue(LocalDate.now());
		lbl_cmdsDay.setText("d'aujourd'hui");

	}

	public void handleClicks(ActionEvent actionEvent) {
		if (actionEvent.getSource() == btnMenus) {
			pnlMenus.toFront();
			if (!dejaMenu) {
				chargerMenus();
				dejaMenu = true;
			}
		}
		if (actionEvent.getSource() == btnOverview) {
			pnlOverview.toFront();
		}
		if (actionEvent.getSource() == btnOrders) {
			pnlOrders.toFront();
		}
		if (actionEvent.getSource().equals(btnAccount)) {
			tEmailChangePass.setText(restaurant.getRestaurateur().getEmail());
			tEmailChangePass.setEditable(false);
			pnlAccount.toFront();
		}
	}

	public void chargerMenus() {
		PlatServiceImpl platServiceImpl = new PlatServiceImpl(connection);
		plats = platServiceImpl.findByIdRestaurant(this.restaurant.getId());

		if (plats == null || plats.isEmpty()) {
			System.out.println("La liste de plats est nulle ou est vide dans chargerPage()");
		} else {

			fillPlatStats(platServiceImpl);

			fillPlats(plats);

		}

	}

	public void fillPlats(List<Plat> plats) {
		Node[] nodes = new Node[plats.size()];
		pnlPlats.getChildren().clear();
		for (int i = 0; i < nodes.length; i++) {
			try {

				final int j = i;

				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(MainResto.class.getResource("view/Plat.fxml"));
				nodes[i] = (HBox) loader.load();

				PlatController controller = loader.getController();
				controller.setPlat(plats.get(i));
				controller.setConnection(this.connection);
				controller.setParent(this);
//				controller.setOwnerStage(this.main.getPrimaryStage());
				controller.fillPlat();

				// give the items some effect
				nodes[i].setOnMouseEntered(event -> {
					nodes[j].setStyle("-fx-background-color : #363B46");
				});
				nodes[i].setOnMouseExited(event -> {
					nodes[j].setStyle("-fx-background-color : transparent");
				});
				pnlPlats.getChildren().add(nodes[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void fillPlatStats(PlatServiceImpl impl) {
		lbl_totalPlats.setText(String.valueOf(plats.size()));

		String platMode = "Aucun";
		String platMoins = "Aucun";
		double revenuMin = 5, revenuMax = 0;

		for (Plat plat : plats) {
			double revenu = impl.getRevenu(plat.getId(), plat.getPrix());
			System.out.println("revenu " + revenu);
			if (revenu >= revenuMax) {
				revenuMax = revenu;
				platMode = plat.getNom();
			}
			if (revenu <= revenuMin) {
				revenuMin = revenu;
				platMoins = plat.getNom();
			}
		}
		lbl_platMode.setText(platMode);
		lbl_platMoisCmd.setText(platMoins);
	}

	List<Commande> loadCommandes(LocalDate date) {
		String newDate = DateUtil.format(date);
//    	return this.commandes;
		if (this.commandes != null)
			return this.commandes.stream()
					.filter(c -> DateTimeUtil.format(c.getDateCommande()).substring(0, 10).equals(newDate))
					.collect(Collectors.toList());
		return new ArrayList<Commande>();
	}

	void fillLabels(List<Commande> commandes) {
		int totalLivre = 0, totalAttente = 0, totalTrait = 0;
		int total = commandes.size();

		for (Commande commande : commandes) {
			if (commande.getEtat().equals("Livr�")) {
				totalLivre++;
			}
			if (commande.getEtat().equals("En attente")) {
				totalAttente++;
			}
			if (commande.getEtat().equals("En traitement")) {
				totalTrait++;
			}
		}

		lbl_totalCommandes.setText(String.valueOf(total));
		lbl_cmdLivres.setText(String.valueOf(totalLivre));
		lbl_cmdEnAttente.setText(String.valueOf(totalAttente));
		lbl_cmdInTrait.setText(String.valueOf(totalTrait));
	}

	public void chargerPage(LocalDate date) {
		pnItems.getChildren().clear();
		List<Commande> commandesToday = new ArrayList<Commande>();
		commandesToday = loadCommandes(date);
//    		dateCommandes.setValue(date);
		fillLabels(commandesToday);
		System.out.println("commandesToday size " + commandesToday.size());
		if (commandesToday == null || commandesToday.isEmpty()) {
			System.out.println("La liste de commandes est nulle ou est vide dans chargerPage()");
		} else {
			Node[] nodes = new Node[commandesToday.size()];
			for (int i = 0; i < nodes.length; i++) {
				try {

					final int j = i;

					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainResto.class.getResource("view/commande.fxml"));
					nodes[i] = (HBox) loader.load();

					CommandeController controller = loader.getController();
					controller.setCommande(commandesToday.get(i));
					controller.setConnection(this.connection);
					controller.setRestaurant(restaurant);
					controller.setOwnerStage(this.main.getPrimaryStage());
					controller.fillCommmande();

					// give the items some effect
					nodes[i].setOnMouseEntered(event -> {
						nodes[j].setStyle("-fx-background-color : #363B46");
					});
					nodes[i].setOnMouseExited(event -> {
						nodes[j].setStyle("-fx-background-color : transparent");
					});
					pnItems.getChildren().add(nodes[i]);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public void loadAllCommandes(LocalDate date) {
//    	checkConnection();
		if (connection == null)
			System.out.println("Connection nulle dans le controller -> loadAllCommdes()");
		CommandeServiceImpl commandeServiceImpl = new CommandeServiceImpl(connection);
		List<Commande> commandesTest = commandeServiceImpl.findByrestaurant(this.restaurant.getId());

		if (commandesTest != null && !commandesTest.isEmpty()) {
			this.commandes = commandesTest;
			chargerPage(date);
			System.out.println("Page charg�e ");
		}

		Timeline timeline = new Timeline(
				new KeyFrame(Duration.millis(30000), ae -> loadAllCommandes(dateCommandes.getValue())));
		timeline.play();

		System.out.println("Task scheduled.");
	}

	@FXML
	void dateHasChanged(ActionEvent actionEvent) {
		chargerPage(dateCommandes.getValue());

		if (dateCommandes.getValue().equals(LocalDate.now())) {
			lbl_cmdsDay.setText("d'aujourd'hui");
		} else
			lbl_cmdsDay.setText("du " + DateUtilViewFormat.format(dateCommandes.getValue()));

	}

	public void setNomResto() {
		lbl_nomResto.setText(this.restaurant.getNom());
	}

	void deletePlat(Plat plat) {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(this.stage);
		alert.setTitle("Suppression");
//		alert.setHeaderText("Look, a Confirmation Dialog");
		alert.setContentText("Voulez vous vraiment supprimer le plat " + plat.getNom() + " ?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... user chose OK
			PlatServiceImpl platServiceImpl = new PlatServiceImpl(connection);
			int resultat = platServiceImpl.deletePlat(plat.getId());

			if (resultat == 1) {
				plats.remove(plat);

				fillPlatStats(platServiceImpl);
				fillPlats(plats);
			} else {
				Alert alertError = new Alert(AlertType.ERROR);
				alertError.initOwner(this.stage);
				alertError.setTitle("Safe Delivery");
				alertError.setHeaderText("Erreur");
				alertError.setContentText("Une erreur s'est produite lors de l'enregistrement. veuillez r�essayer.");
			}
		}

	}

	@FXML
	void buttonActionHandler() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainResto.class.getResource("view/EditPlat.fxml"));
		BorderPane root = loader.load();
		EditPlatController controller = loader.getController();
		controller.setConnection(connection);
		controller.setRestaurant(restaurant);
		controller.setParent(this);
		controller.fillPlat();
		Stage primaryStage = new Stage();
		primaryStage.setTitle("SafeDelivery - Ajouter un plat");
		primaryStage.getIcons().add(new Image("file:resources/images/logo_sans_titre.png"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		controller.setStage(primaryStage);
		primaryStage.initOwner(stage);
		primaryStage.show();
	}

	@FXML
	void search(KeyEvent event) {
		String q = txtSearPlat.getText();
		if (q.isEmpty()) {
			fillPlats(plats);
			return;
		}

		List<Plat> results = new ArrayList<Plat>();

		for (Plat plat : plats) {
			if (plat.getNom().contains(q))
				results.add(plat);
		}

		fillPlats(results);
	}

	@FXML
	void handleButtonAction(ActionEvent event) throws NoSuchAlgorithmException {
		boolean error = false;
		lbl_errorNewPass.setText("");
		lbl_errorOldPass.setText("");
		
		String oldPass = txt_oldPass.getText(), newPass = txt_newPass.getText();
		
		if(newPass.isEmpty() || newPass.length() < 6) {
			error = true;
			lbl_errorNewPass.setText("Au moins 6 caract�res");
			txt_newPass.setText("");
		}
		if(oldPass.isEmpty() || !saltHashPassword.generateHash(oldPass).equals(restaurant.getRestaurateur().getPass())){
			error = true;
			lbl_errorOldPass.setText("Ancien mot de passe incorrect");
			txt_oldPass.setText("");
			txt_newPass.setText("");
		}
		
		if(!error) {
			UserServiceImpl impl = new UserServiceImpl(connection);
			int res = impl.changePass(restaurant.getRestaurateur().getId(), newPass);
			
			if (res <= 0) {
				txt_oldPass.setText("");
				txt_newPass.setText("");
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(stage);
				alert.setTitle("Safe Delivery");
				alert.setHeaderText("Erreur lors de la connexion");
				alert.setContentText("Le changement n'as pas pu �tre op�r�.");
				alert.showAndWait();
			} else {
				txt_oldPass.setText("");
				txt_newPass.setText("");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(stage);
				alert.setTitle("Safe Delivery");
				alert.setHeaderText("Succ�s");
				alert.setContentText("Votre mot de passe est chang� avec succ�s");
				alert.showAndWait();
			}
			
		}
	}
	
	@FXML
    void signOutHandler(ActionEvent event) {
		this.stage.close();
    }
}
