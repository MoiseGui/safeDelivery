package com.safeDelivery.test;

import java.security.NoSuchAlgorithmException;

import com.safeDelivery.model.User;
import com.safeDelivery.model.Ville;
import com.safeDelivery.model.Zone;
import com.safeDelivery.service.impl.UserServiceImpl;
import com.safeDelivery.service.impl.VilleServiceImpl;
import com.safeDelivery.service.impl.ZoneServiceimpl;
import com.safeDelivery.utils.saltHashPassword;

public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		UserServiceImpl userService = new UserServiceImpl();
		VilleServiceImpl villeService = new VilleServiceImpl();
		ZoneServiceimpl zoneservice = new ZoneServiceimpl();
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
		
		
		
		
		
		//test saveVille
	Ville ville = new Ville("konoha");
//		int result = villeService.saveVille(ville);
//		System.out.println("le resultat est = "+result);
		
		
		
		
		//test saveZone
		Zone zone = new Zone("zone1", ville);
		int result1 = zoneservice.saveZone(zone);
		System.out.println("le resultat "+result1);
		
		
	}
}
