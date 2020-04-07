package com.safeDelivery.test;

import com.safeDelivery.service.impl.VilleServiceImpl;

public class VilleTest {
	public static void main(String argv[]) {
		VilleServiceImpl vs = new VilleServiceImpl();
		
		int i = vs.findByName("Marrakech");
		
	     System.out.println("Resultat: "+i);
	}

}
