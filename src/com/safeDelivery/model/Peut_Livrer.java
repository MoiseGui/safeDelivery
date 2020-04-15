package com.safeDelivery.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Peut_Livrer {
	private ObjectProperty<Livreur> livreur;
	private ObjectProperty<Zone> zone;
	
	public Peut_Livrer() {
	
		this.livreur = new SimpleObjectProperty<Livreur>();
		this.zone = new SimpleObjectProperty<Zone>();
		
	}

	public Peut_Livrer(Livreur livreur, Zone zone) {
		this.livreur = new SimpleObjectProperty<Livreur>(livreur);
		this.zone = new SimpleObjectProperty<Zone>(zone);
	}

	public Livreur getLivreur() {
		return livreur.get();
	}

	public void setLivreur(Livreur livreur) {
		this.livreur.set(livreur);
	}
	
	public ObjectProperty<Livreur> livreurProperty(){
		return this.livreur;
		}
	
	public Zone getZone() {
		return zone.get();
	}

	public void setZone(Zone zone) {
		this.zone.set(zone);
	}
	
	public ObjectProperty<Zone> zoneProperty(){
		return this.zone;
	}

	

}
