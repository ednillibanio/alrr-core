package br.leg.rr.al.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashHelper {

	public static byte[] sha1(byte[] bytes) {
		try {
			MessageDigest digester = MessageDigest.getInstance("SHA1");
			digester.update(bytes);
			return digester.digest();
		} catch (NoSuchAlgorithmException e) {

		}
		return null;
	}
}
