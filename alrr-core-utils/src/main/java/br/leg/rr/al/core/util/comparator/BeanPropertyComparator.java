/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.util.comparator;

import java.util.Comparator;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * Descricao da classe.
 * 
 * @param <T>
 */
public class BeanPropertyComparator<T> implements Comparator<T> {

	/**
	 * Atributo utilizado para remocao de acentos.
	 */
	static String acentuado = "\u00e7\u00c7\u00e1\u00e9\u00ed\u00f3\u00fa\u00fd\u00c1\u00c9\u00cd\u00d3\u00da\u00dd\u00e0\u00e8\u00ec\u00f2\u00f9\u00c0\u00c8\u00cc\u00d2\u00d9\u00e3\u00f5\u00f1\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00c4\u00cb\u00cf\u00d6\u00dc\u00c3\u00d5\u00d1\u00e2\u00ea\u00ee\u00f4\u00fb\u00c2\u00ca\u00ce\u00d4\u00db";

	/**
	 * Atributo utilizado para remocao de acentos.
	 */
	static String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";

	/**
	 * Tabela contendo a relacao entre os caraceteres com e sem acentuacao.
	 */
	static char[] tabela;
	static {
		tabela = new char[256];
		for (int i = 0; i < tabela.length; ++i) {
			tabela[i] = (char) i;
		}
		for (int i = 0; i < acentuado.length(); ++i) {
			tabela[acentuado.charAt(i)] = semAcento.charAt(i);
		}
	}

	private String propriedade;
	private boolean crescente;

	public BeanPropertyComparator(String propriedade, boolean crescente) {
		this.propriedade = propriedade;
		this.crescente = crescente;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(T bean1, T bean2) {

		Comparable valor1 = null;
		Comparable valor2 = null;

		try {
			valor1 = (Comparable) PropertyUtils.getProperty(bean1, propriedade);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Nao foi possivel obter a propriedade [" + propriedade + "] do bean ["
					+ bean1.getClass().getName() + "]", ex);
		}
		try {
			valor2 = (Comparable) PropertyUtils.getProperty(bean2, propriedade);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Nao foi possivel obter a propriedade [" + propriedade + "] do bean ["
					+ bean2.getClass().getName() + "]", ex);
		}

		// caso as propriedades sejam String, faz a comparacao sem considerar maiusculas
		// e minusculas
		int comparacao;
		if (valor1 instanceof String && valor2 instanceof String) {
			comparacao = compareSemAcentos((String) valor1, (String) valor2);
		} else {
			// para objetos nao string, compara usando o comparador normal
			comparacao = valor1.compareTo(valor2);
		}
		return crescente ? comparacao : comparacao * -1;

	}

	public static int compareSemAcentos(String s1, String s2) {
		String s1Normalizada = org.apache.commons.lang3.StringUtils.trimToEmpty(s1);
		s1Normalizada = removerAcentuacao(s1Normalizada);

		String s2Normalizada = org.apache.commons.lang3.StringUtils.trimToEmpty(s2);
		s2Normalizada = removerAcentuacao(s2Normalizada);

		return s1Normalizada.compareToIgnoreCase(s2Normalizada);
	}

	/**
	 * Realiza a remocao de letras acentuadas substituindo-as pelos equivalentes sem
	 * acentuacao.
	 *
	 * @param s
	 *            String a ser manipulada.
	 * @return String recebida sem acentuacao.
	 */
	public static String removerAcentuacao(String s) {
		StringBuilder sb = new StringBuilder();
		if (s != null) {
			for (int i = 0; i < s.length(); ++i) {
				char ch = s.charAt(i);
				if (ch < 256) {
					sb.append(tabela[ch]);
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}

}
