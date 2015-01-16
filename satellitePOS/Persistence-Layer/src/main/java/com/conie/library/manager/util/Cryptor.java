package com.conie.library.manager.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cryptor {
	private static final String MD5 = "MD5";

	public static String md5(String s) {
		try {
			byte[] messageDigest = digestToMd5(s);
			return convertToString(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] digestToMd5(String s) throws NoSuchAlgorithmException {
		MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
		digest.update(s.getBytes());
		byte messageDigest[] = digest.digest();
		return messageDigest;
	}

	private static String convertToString(byte[] messageDigest) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++)
			hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
		return hexString.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(md5("admin"));	
	}
}
