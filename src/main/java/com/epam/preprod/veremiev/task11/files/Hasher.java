package com.epam.preprod.veremiev.task11.files;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {

	public String hash(String method, String password) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		MessageDigest md = MessageDigest.getInstance(method);
		byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
