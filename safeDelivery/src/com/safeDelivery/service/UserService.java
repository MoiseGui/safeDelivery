package com.safeDelivery.service;

import com.safeDelivery.model.User;

public interface UserService {
	public long existByEmailAndPass(String email, String pass);
	public User getUserByEmail(String email);
	public long addUser(User user);
	public int disableUserByEmail(String email);
	public int deleteUserByEmail(String email);
}
