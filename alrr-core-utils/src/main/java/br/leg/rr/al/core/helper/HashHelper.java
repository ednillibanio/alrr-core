/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.helper;

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
