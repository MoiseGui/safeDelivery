package com.safeDelivery.test;

import java.security.NoSuchAlgorithmException;

import com.safeDelivery.model.User;
import com.safeDelivery.service.impl.UserServiceImpl;
import com.safeDelivery.utils.saltHashPassword;

public class Test {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		UserServiceImpl userService = new UserServiceImpl();
	    // test add user
		/*
		User user = new User((long) 3,"itachi", "sasuke", "sasuke@gmail.com","keykey","2222222", 0, 0);
		int rs1 = userService.addUser(user);
		System.out.println("le resultat est "+result);
		*/
		// test disableuserbyemail 
		/*
		int rs2 = userService.disableUserByEmail("sharingan@gmail.com");
		System.out.println("le resultat est "+ rs);
		*/
        // test deletebyuseremail
		int res3 = userService.deleteUserByEmail("sharingan@gmail.com");
		System.out.println("le resultat est "+res3);
	}
}
