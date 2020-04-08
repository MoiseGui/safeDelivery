package com.safeDelivery.test;

import java.security.NoSuchAlgorithmException;

import com.safeDelivery.model.Adresse;
import com.safeDelivery.model.Client;
import com.safeDelivery.model.Livreur;
import com.safeDelivery.model.User;
import com.safeDelivery.model.Ville;
import com.safeDelivery.model.Zone;
import com.safeDelivery.service.impl.AdresseServiceImpl;
import com.safeDelivery.service.impl.ClientServiceImpl;
import com.safeDelivery.service.impl.LivreurServiceImpl;
import com.safeDelivery.service.impl.UserServiceImpl;
import com.safeDelivery.service.impl.VilleServiceImpl;
import com.safeDelivery.service.impl.ZoneServiceimpl;
import com.safeDelivery.utils.saltHashPassword;

public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		UserServiceImpl userService = new UserServiceImpl();
		VilleServiceImpl villeService = new VilleServiceImpl();
		ZoneServiceimpl zoneservice = new ZoneServiceimpl();
		AdresseServiceImpl adresseservice = new AdresseServiceImpl();
		ClientServiceImpl clientService = new ClientServiceImpl();
		LivreurServiceImpl livreurService = new LivreurServiceImpl();
		// test add user
//		User user = new User((long) 3,"itachi", "sasuke", "sasuke@gmail.com","keykey","2222222", 0, 0);
//		int rs1 = userService.addUser(user);
//		System.out.println("le resultat est "+rs1);

		// test disableuserbyemail
//		int rs2 = userService.disableUserByEmail("sasuke@gmail.com");
//		System.out.println("le resultat est "+ rs2);

		// test deletebyuseremail
//	   int res3 = userService.deleteUserByEmail("sasuke@gmail.com");
//	   System.out.println("le resultat est "+res3);

		// test saveVille
//	Ville ville = new Ville("konoha");
//		int result = villeService.saveVille(ville);
//		System.out.println("le resultat est = "+result);
//
//		Ville ville1 = new Ville("h7");
//		Zone zone = new Zone("zone51", ville1);
//		Adresse adresse = new Adresse("hello", zone);
//		System.out.println("l'id de l'adresse est = "+adresseservice.saveAdresse(adresse));
//	Zone zone1 = new Zone("zone2", ville1);
//	System.out.println(zoneservice.saveZone(zone1));
//	Adresse adresse = new Adresse("gueliz fstg marrakech", zone1);
//	int result = adresseservice.saveAdresse(adresse);
//	System.out.println("le resultat est = "+result);

		// test saveZone
//		Zone zone = new Zone("zone1", ville);
//		int result1 = zoneservice.saveZone(zone);
//		System.out.println("le resultat "+result1);
		

		
		
		//testClient 
//		Ville ville1 = new Ville("h99");
//		Zone zone = new Zone("zone52", ville1);
//		Adresse adresse = new Adresse("hellome", zone);
//		Client client = new Client("madara", "uchiha", "kabuto@gmail.com", "irisi","06228857333",adresse, 0, 1);
//		System.out.println(clientService.addClient(client));
		
		
		Livreur livreur = new Livreur("sasuke","hashirama", "amaterasu@gmail.com","trololololo","06554433222211",0, 1);
		System.out.println("id du livreur est "+livreurService.addLivreur(livreur)); 
		
		
		
		
		
		
		
	}
}
