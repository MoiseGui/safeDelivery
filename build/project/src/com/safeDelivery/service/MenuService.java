package com.safeDelivery.service;

public interface MenuService {
	public long addMenu(long idPlat, long idResto);
	public int exists(long idPlat, long idResto);
	public int deleteMenu(long idPlat);
}
