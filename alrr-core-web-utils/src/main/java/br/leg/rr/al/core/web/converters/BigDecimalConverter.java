/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.converters;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@Named(value = "decimalConverter")
public class BigDecimalConverter implements Converter, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4606227561197025107L;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
		if (value == null || "".equals(value)) {
			// return new BigDecimal("0");
			return new String("");
		}

		String nValue = "";
		char[] array = value.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] != '.' && array[i] == ',')
				nValue += ".";
			else if (array[i] != '.' && array[i] != ',')
				nValue += array[i];
		}

		BigDecimal result = new BigDecimal(nValue);
		int pos = nValue.indexOf(".");
		if (pos > 0) {
			result.setScale(nValue.substring(pos + 1).length());
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
		BigDecimal valor = (BigDecimal) value;
		String str = valor.toString();
		String nValueFinal = "";

		String nValue = "";
		char[] array = str.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '.' && array[i] != ',') {
				nValue += ",";
			} else if (array[i] != '.' && array[i] != ',') {
				nValue += array[i];
			}
		}
		if (nValue.contains(",")) {
			// Insere os separadores de milhar
			String inteira = nValue.split(",")[0];
			String fracionaria = nValue.split(",")[1];
			int separadorMilharCount = 0;
			for (int i = 0; i <= inteira.length() - 1; i++) {
				if (separadorMilharCount == 3) {
					nValueFinal = nValueFinal + ".";
					separadorMilharCount = 0;
				}
				nValueFinal += inteira.charAt((inteira.length() - 1) - i);
				separadorMilharCount++;
			}
			String aux = "";
			for (int i = 0; i <= nValueFinal.length() - 1; i++) {
				aux += nValueFinal.charAt((nValueFinal.length() - 1) - i);
			}
			nValueFinal = aux + "," + fracionaria;
			return nValueFinal;
		} else {
			return nValue;
		}
	}

}
