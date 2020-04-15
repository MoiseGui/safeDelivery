package com.safeDelivery.test;

import com.safeDelivery.model.Plat;
import com.safeDelivery.service.impl.PlatServiceImpl;

public class MoiseTest {
	public static void main(String[] args) {
		PlatServiceImpl platService = new PlatServiceImpl();
		
		
		Plat plat = new Plat("Tacos Poulet", 35.00, "Tacos avec du poulet");
		
		//System.out.println(platService.addPlat(plat));
		
		plat.setId((long) 1);
		System.out.println(platService.changePlat(plat, new Plat("Tacos Poulet", (double) 30, "Tacos au poulet")));
	}
}
