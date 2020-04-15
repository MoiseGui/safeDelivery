package com.safeDelivery.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.safeDelivery.model.Adresse;
import com.safeDelivery.model.Ville;
import com.safeDelivery.model.Zone;
import com.safeDelivery.service.AdresseService;
import com.safeDelivery.utils.SingletonConnexion;

public class AdresseServiceImpl implements AdresseService {
	ZoneServiceimpl zoneserviceimpl = new ZoneServiceimpl();

	@Override
	public long saveAdresse(Adresse adresse) {
		long found = zoneserviceimpl.existByName(adresse.getZone().getNom());
		System.out.println("zone found adresse save" + found);
		if (found < 0) {
			long rs = zoneserviceimpl.saveZone(adresse.getZone());
			System.out.println("creation de la ville = " + rs);
			found = rs;
			adresse.getZone().setId(found);
			if (rs < 0) {
				return -2;
			}
		}
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "insert into adresse (detail,id_zone) values (?,?)";
				PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, adresse.getDetail());
				ps.setLong(2, adresse.getZone().getId());
				int count = ps.executeUpdate();
				if (count == 1) {
					ResultSet rs1 = ps.getGeneratedKeys();
					if (rs1.next()) {
						int rsgetint = rs1.getInt(1);
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return rsgetint;
					} else {
						ps.close();
						SingletonConnexion.closeConnection(conn);
						return -1;
					}
				}else {
					return -4;
				}
			} else {
				return -2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -3;
		}
	}

	@Override
	public Adresse findById(long id) {
		try {
			Connection conn = SingletonConnexion.startConnection();
			if (conn != null) {
				String query = "select * from adresse where id = ?";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, id);
				ResultSet result = ps.executeQuery();
				if (result.next()) {
					ZoneServiceimpl impl = new ZoneServiceimpl();
					Zone zone = impl.findById(result.getLong(3));
					if(zone==null) {
						return null;
					}
					else {
					Adresse adresse= new Adresse(result.getLong(1), result.getString(2), zone);
					ps.close();
					SingletonConnexion.closeConnection(conn);

					return adresse;
					}
				}
				else {
					return null;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
