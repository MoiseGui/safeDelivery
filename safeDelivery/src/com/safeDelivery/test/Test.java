package com.safeDelivery.test;

import com.safeDelivery.model.User;
import com.safeDelivery.service.impl.UserServiceImpl;

public class Test {
	public static void main(String[] args) {
		UserServiceImpl userService = new UserServiceImpl();
		
		//int i = userService.existByEmailAndPass("madara@gmail.com", "iiizzz222");
		
		//System.out.println("r�sultat: " + i);
		
		User user = userService.getUserByEmail("madara@gmail.com");
		
		if(user == null) System.out.println("Aucun r�sultat");
		else System.out.println(user.toString());
		
	}
}
