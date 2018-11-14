package br.leg.rr.al.core.utils;

import java.text.Normalizer;

import javax.swing.text.MaskFormatter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;

public final class StringHelper {

	public static String removerAcentos(String str) {

		if (!StringUtils.isEmpty(str)) {
			str = Normalizer.normalize(str, Normalizer.Form.NFD);
			str = str.replaceAll("[^\\p{ASCII}]", "");
		}
		return str;
	}

	/**
	 * 
	 * @param txt
	 * @return
	 */
	public static String capitalizeFully(String txt) {
		if (StringUtils.isEmpty(txt)) {
			return txt;
		}
		String texto = WordUtils.capitalizeFully(txt);

		texto = texto.replaceAll(" De ", " de ").replaceAll(" Do ", " do ").replaceAll(" Da ", " da ")
				.replaceAll(" O ", " o ").replaceAll(" A ", " a ").replaceAll(" E ", " e ");

		return texto;
	}

	public static boolean isNumerico(String str) {
		return !StringUtils.isEmpty(str) && StringUtils.isNumeric(str);
	}

	/**
	 * 
	 * Formata a String de acordo com o padrao informado
	 * 
	 * @param texto
	 * @param formato
	 * @return
	 * @throws Exception
	 */
	public String formatar(String texto, String formato) throws Exception {
		String resultado = null;
		if (StringUtils.isNotBlank(texto) && StringUtils.isNotBlank(formato)) {
			MaskFormatter mf = new MaskFormatter(formato);
			mf.setValueContainsLiteralCharacters(false);
			resultado = mf.valueToString(texto);
		}
		return resultado;
	}

	/**
	 * 
	 * @param str
	 * @param searchStr
	 * @param delimitador
	 * @return
	 */
	public static boolean containsIgnoreCase(final String str, String searchStr, final String delimitador) {

		String word = null;
		while (searchStr.length() > 0) {
			word = StringUtils.substringBefore(searchStr, delimitador);
			searchStr = StringUtils.substringAfter(searchStr, delimitador);

			if (StringUtils.containsIgnoreCase(str, word)) {
				return true;

			}
		}

		return false;
	}

}
