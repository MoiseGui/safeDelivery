package com.safeDelivery.test;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import com.safeDelivery.model.Zone;
import com.safeDelivery.service.impl.ZoneServiceimpl;
import com.safeDelivery.utils.SingletonConnexion;



public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException {
//		UserServiceImpl userService = new UserServiceImpl();
	Connection connection = SingletonConnexion.startConnection();
//		VilleServiceImpl villeService = new VilleServiceImpl(connection);
		ZoneServiceimpl zoneservice = new ZoneServiceimpl(connection);
//		AdresseServiceImpl adresseservice = new AdresseServiceImpl();
//		ClientServiceImpl clientService = new ClientServiceImpl();
//		LivreurServiceImpl livreurService = new LivreurServiceImpl();
//		RestaurateurServiceImpl restaurateurServiceImpl = new RestaurateurServiceImpl();
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

//		// test saveVille
//		long result = villeService.existByName("Konoha");
//		System.out.println("le resultat est = "+ result );
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
		Zone zone = zoneservice.findById(1);
		System.out.println("le resultat "+zone.getNom());
		
		SingletonConnexion.closeConnection(connection);

		
		
		//testClient 
//		Ville ville1 = new Ville("h99");
//		Zone zone = new Zone("zone52", ville1);
//		Adresse adresse = new Adresse("hellome", zone);
//		Client client = new Client("madara", "uchiha", "kabuto@gmail.com", "irisi","06228857333",adresse, 0, 1);
//		System.out.println(clientService.addClient(client));
		
		
//		Livreur livreur = new Livreur("sasuke","hashirama", "amaterasu@gmail.com","trololololo","06554433222211",0, 1);
//		System.out.println("id du livreur est "+livreurService.addLivreur(livreur)); 
		
		
//		// test de addUser
//		Restaurateur restaurateur = new Restaurateur("Uzumaki", "Naruto", "naruto@gmail.com", "i hate madara", "0655443322" , 1);
//		System.out.println("L'id du restaurateur est "+ restaurateurServiceImpl.addRestaurateur(restaurateur));
//		
//		
//		
//		
//		
//		
//		
	}
}
