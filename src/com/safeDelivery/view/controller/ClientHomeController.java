package com.safeDelivery.view.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.safeDelivery.MainApp;
import com.safeDelivery.model.Client;
import com.safeDelivery.model.Panier;
import com.safeDelivery.model.Plat;
import com.safeDelivery.service.impl.PanierServiceImpl;
import com.safeDelivery.service.impl.PlatServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ClientHomeController implements Initializable {

	private Connection connection;
	List<Plat> randomPlat = new ArrayList<Plat>();

	@FXML
	private Label lbl_nomClient;

	@FXML
	private Button btnHome;

	@FXML
	private Button btnOrders;

	@FXML
	private Button btnPanier;

	@FXML
	private Button btnsales;

	@FXML
	private Button btnaddoperator;

	@FXML
	private Button btnremoveoperator;

	@FXML
	private Button btnSettings;

	@FXML
	private Button btnSignout;

	@FXML
	private StackPane mainPane;

	@FXML
	private Pane pnlHome;

	@FXML
	private ScrollPane pn;

	@FXML
	private VBox vbx;

	@FXML
	private ImageView panier;

	@FXML
	private ComboBox<String> cbxville;

	@FXML
	private ComboBox<String> cbxresto;

	@FXML
	private Pane pnlCart;

	@FXML
	private Button btn_vider;

	@FXML
	private Button btn_commander;

	@FXML
	private ScrollPane scpPanier;

	@FXML
	private VBox pnIPanier;

	@FXML
	private Label lbl_total;

	@FXML
	private Label lbl_panierCount;

	@FXML
	void HomeHandle(ActionEvent event) {
		pnlHome.toFront();
	}

	private List<Panier> paniers = new ArrayList<Panier>();

	public List<Panier> getPaniers() {
		return paniers;
	}

	public void setPaniers(List<Panier> paniers) {
		this.paniers = paniers;
	}

	List<Plat> plats = new ArrayList<Plat>();
	private MainApp main;
	private Client client;

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private String v1 = "";
	private String v2 = "";

	int GT = 0;

	public MainApp getMain() {
		return main;
	}

	public void setMain(MainApp main) {
		this.main = main;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		pnlHome.toFront();
		lbl_total.setText(Integer.toString(0));
	}

	@FXML
	void CartHandle(MouseEvent event) throws IOException {
		showPanier();
		pnlCart.toFront();
	}

	public void loadData() throws IOException {
//		System.out.println("i'm here load data");
		PanierServiceImpl panierService = new PanierServiceImpl(connection);
		paniers = panierService.getByClient(client);
		if (paniers == null || paniers.isEmpty()) {
			System.out.println("paniers nul");
		} else {
			showPanier();
		}

	}

	public void removePanier(Panier panier) throws IOException {
		paniers.remove(panier);
		showPanier();
	}

	public void showPanier() throws IOException {
		showPanierCout();
		updateTotal();
		pnIPanier.getChildren().clear();
		for (Panier panier : paniers) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Panier.fxml"));
			HBox parent = (HBox) loader.load();
			PanierController panierController = loader.getController();
			panierController.setConnexion(connection);
			panierController.setPlat(panier.getPlat());
			panierController.setClientHomeController(this);
			panierController.setClient(client);
			panierController.setPanier(panier);
			panierController.fillPanier(panier);
			parent.setOnMouseEntered(event -> {
				parent.setStyle("-fx-background-color : #3E4C71");
			});
			parent.setOnMouseExited(event -> {
				parent.setStyle("-fx-background-color : #23314A");
			});

			pnIPanier.getChildren().add(parent);
		}
	}

	public void showPanierCout() {
		lbl_panierCount.setText(String.valueOf(paniers.size()));
	}

	public void updateTotal() {
		double total = 0;
		for (Panier panier : paniers) {
			System.out.println(panier.getPlat().getNom());
			total += panier.getQte() * panier.getPlat().getPrix();
		}
		lbl_total.setText(String.valueOf(total));
	}

	public void setRandomPlat(List<Plat> randomPlat) {
		this.randomPlat = randomPlat;
	}

	public int getRandomplat() {
		return randomPlat.size();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

//ça marche
	@FXML
	void filterResto(ActionEvent event) {
		PlatServiceImpl platService = new PlatServiceImpl(connection);
		v1 = cbxresto.getValue();
		if (!v1.equals("") && v2.equals("")) {
			List<Plat> plats = platService.findPlatByResto(v1);
			diplay(plats);
		}
		if (!v1.equals("") && !v2.equals("")) {
			List<Plat> plats = platService.findPlatByVilleAndResto(v2, v1);
			diplay(plats);
		}

	}

//ça marche
	@FXML
	void filterVille(ActionEvent event) {
		PlatServiceImpl platService = new PlatServiceImpl(connection);
		v2 = cbxville.getValue();
		if (!v2.equals("") && v1.equals("")) {
			List<Plat> plats = platService.findPlatByVille(v2);
			diplay(plats);
		}
		if (!v1.equals("") && !v2.equals("")) {
			List<Plat> plats = platService.findPlatByVilleAndResto(v2, v1);
			diplay(plats);
		}
	}

// ça marche
	public void loadVilles(List<String> villes) {
		System.out.println(villes);
		ObservableList<String> lVilles = FXCollections.observableArrayList(villes);
		if (lVilles == null) {
			return;
		} else {
			cbxville.setItems(lVilles);

		}
	}

// ça marche
	public void loadRestaurants(List<String> Restaurants) {
		ObservableList<String> lRestaurant = FXCollections.observableArrayList(Restaurants);
		if (lRestaurant == null) {
			return;
		} else {
			cbxresto.setItems(lRestaurant);

		}
	}

// ça marche
	public void loadPlat() throws IOException {
		lbl_nomClient.setText(this.client.getPrenom() + " " + this.client.getNom());
		diplay(randomPlat);
		loadData();
	}

// ça marche 
	@FXML
	void closeapp(ActionEvent event) {
		System.exit(0);
	}

// ça marche
	public void diplay(List<Plat> plats) {
		try {
			vbx.getChildren().clear();
			Node[] node = new Node[plats.size()];
			int compteur = 0;
			for (int i = 0; i < node.length; i = i + 3) {
				HBox hbox = new HBox();
				for (int j = 0; j < 3; j++) {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(MainApp.class.getResource("view/PlatItem.fxml"));
					if (compteur < node.length) {
						node[compteur] = (VBox) loader.load();
						PlatItemController platItemController = loader.getController();
						platItemController.setPlat(plats.get(compteur));
						platItemController.setConn(connection);
						platItemController.setClient(client);
						platItemController.setParent(this);
						platItemController.fillPlat();
						hbox.getChildren().add(node[compteur]);
						compteur++;
					}
				}
				hbox.setPadding(new Insets(0, 0, 10, 30));
				hbox.setSpacing(10);
				vbx.getChildren().add(hbox);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@FXML
	void commander(ActionEvent event) throws IOException {
		PanierServiceImpl panierService = new PanierServiceImpl(connection);
		panierService.validatePanier(paniers, Double.valueOf(lbl_total.getText()).doubleValue());
		paniers.clear();
		showPanier();
	}

	@FXML
	void viderPanier(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
		PanierServiceImpl panierService = new PanierServiceImpl(connection);
		panierService.deleteAll(client);
		paniers.clear();
		showPanier();
	}

}
