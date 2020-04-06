package com.safeDelivery.test;

import com.safeDelivery.model.User;
import com.safeDelivery.service.impl.UserServiceImpl;

public class Test {
	public static void main(String[] args) {
		UserServiceImpl userService = new UserServiceImpl();
		
		//int i = userService.existByEmailAndPass("madara@gmail.com", "iiizzz222");
		
		//System.out.println("résultat: " + i);
		
		User user = userService.getUserByEmail("madara@gmail.com");
		
		if(user == null) System.out.println("Aucun résultat");
		else System.out.println(user.toString());
		
	}
}
