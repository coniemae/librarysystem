/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conie.library.manager.util;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author angelo
 */
public class Base64Cryptor {

	public static String encrypt(String text) {
		byte[] encoded = Base64.encodeBase64(text.getBytes());
		return new String(encoded);
	}

	public static String decrypt(String encoded) {
		byte[] decoded = Base64.decodeBase64(encoded);
		return new String(decoded);
	}

	public static String encrypt(byte[] text) {
		byte[] encoded = Base64.encodeBase64(text);
		return new String(encoded);
	}

	public static byte[] decryptBytes(String encoded) {
		byte[] decoded = Base64.decodeBase64(encoded);
		return decoded;
	}
}
