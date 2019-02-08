/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.utils;

import static java.math.BigDecimal.ZERO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public final class DinheiroUtils implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1435686155438993476L;

	public static String format(Number number, String language) throws Exception {
		Locale locale = new Locale(language);
		return format(number, locale);
	}

	public static String format(Number number, String language, String country) throws Exception {
		Locale locale = new Locale(language, country);
		return format(number, locale);
	}

	public static String format(Number number, Locale locale) throws Exception {
		String result = null;
		NumberFormat nf = null;

		if (number != null && locale != null) {
			nf = NumberFormat.getCurrencyInstance(locale);
			nf.setMaximumFractionDigits(2);
			nf.setMinimumFractionDigits(2);
			if (nf != null) {
				result = nf.format(number.doubleValue());
			}
		}

		return result;
	}

	/**
	 * Remove formatação do valor em Dinheiro.
	 * 
	 * @param value
	 *            valro que será removido a formatação. O valor deve conter os
	 *            centavos também, mesmo que sejam zeros.
	 * @return retorna o valor em BigDecimal.
	 * @throws Exception
	 */
	public static BigDecimal unformat(String value) throws Exception {
		if (StringUtils.isNotBlank(value)) {

			value = value.replaceAll(Pattern.quote("."), "");
			value = value.replaceAll(",", "");
			value = value.trim();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < value.length(); i++) {
				char ch = value.charAt(i);
				if (Character.isDigit(ch)) {
					sb.append(ch);
				}
			}
			sb = sb.insert(sb.length() - 2, '.');
			return new BigDecimal(sb.toString());
		}
		return null;

	}

	/** Return <tt>true</tt> only if the amount is positive. */
	public static boolean isPlus(final BigDecimal value) {
		return value.compareTo(ZERO) > 0;
	}

	/** Return <tt>true</tt> only if the amount is negative. */
	public static boolean isMinus(final BigDecimal value) {
		return value.compareTo(ZERO) < 0;
	}

	/** Return <tt>true</tt> only if the amount is zero. */
	public static boolean isZero(final BigDecimal value) {
		return value.compareTo(ZERO) == 0;
	}

	/** Return <tt>true</tt> only if the amount is zero. */
	public static boolean isMenor(final BigDecimal source, final BigDecimal compareTo) {
		return source.compareTo(compareTo) < 0;
	}

	/** Return <tt>true</tt> only if the amount is zero. */
	public static boolean isMaior(final BigDecimal source, final BigDecimal compareTo) {
		return source.compareTo(compareTo) > 0;
	}
}
