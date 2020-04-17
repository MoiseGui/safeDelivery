package com.safeDelivery.test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.safeDelivery.utils.DateTimeUtil;

import javafx.util.converter.DateTimeStringConverter;

public class MoiseTest {
	public static void main(String[] args) {
//		PlatServiceImpl platService = new PlatServiceImpl();
//		
//		
//		Plat plat = new Plat("Tacos Poulet", 35.00, "Tacos avec du poulet");
//		
//		//System.out.println(platService.addPlat(plat));
//		
//		plat.setId((long) 1);
//		System.out.println(platService.changePlat(plat, new Plat("Tacos Poulet", (double) 30, "Tacos au poulet")));
		
//		LocalDateTime dateTime = LocalDateTime.now();
//
//		System.out.println(DateUtil.format(dateTime));
//		
//		System.out.println(DateUtil.parse("2020-04-16 17:54:14"));
		
		String dateTime = DateTimeUtil.format(LocalDateTime.now());
		String date = dateTime.substring(0, 10);
		System.out.println(date);
		
	}
}
