package com.socin.catchworks.domain.functions;

import java.math.BigInteger;
import java.security.MessageDigest;

public class ConverteSenha {

	public static String HashSenha(String senha) {

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(senha.getBytes("utf8"));
			toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}

}
