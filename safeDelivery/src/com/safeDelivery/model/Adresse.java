package com.safeDelivery.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Adresse {
	private LongProperty id;
	private StringProperty detail;
	private ObjectProperty<Zone> zone;
	
	
	public Adresse() {
		this.id = new SimpleLongProperty();
		this.detail = new SimpleStringProperty();
		this.zone = new SimpleObjectProperty<Zone>();
	}


	public Adresse(Long id, String detail, Zone zone) {
		this.id = new SimpleLongProperty(id);
		this.detail = new SimpleStringProperty(detail);
		this.zone = new SimpleObjectProperty<Zone>(zone);
	}


	public Long getId() {
		return id.get();
	}
	
	public LongProperty idProperty() {
		return this.id;
	}


	public void setId(Long id) {
		this.id.set(id);
	}


	public String getDetail() {
		return detail.get();
	}
	
	public StringProperty detailProperty() {
		return this.detail;
	}


	public void setDetail(String detail) {
		this.detail.set(detail);
	}


	public Zone getZone() {
		return zone.get();
	}
	
	public ObjectProperty<Zone> zoneProperty(){
		return this.zone;
	}


	public void setZone(Zone zone) {
		this.zone.set(zone);
	}
	
	
	
}
