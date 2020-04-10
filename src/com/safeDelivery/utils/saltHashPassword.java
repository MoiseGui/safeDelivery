 package com.safeDelivery.utils;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

public class saltHashPassword {
  public static String generateHash(String data) throws NoSuchAlgorithmException
  {
	  String algorithm = "SHA-256";
	  MessageDigest digest = MessageDigest.getInstance(algorithm);
	  digest.reset();
	  byte[] hash = digest.digest(data.getBytes());
	  return bytesToHex(hash);
  }
  
  private final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = HEX_ARRAY[v >>> 4];
	        hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
	    }
	    return new String(hexChars);
	}
  
 
}
