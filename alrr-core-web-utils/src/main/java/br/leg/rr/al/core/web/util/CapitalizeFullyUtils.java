package br.leg.rr.al.core.web.util;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import br.leg.rr.al.core.utils.StringHelper;

@Named
@RequestScoped
public class CapitalizeFullyUtils {

	private String textoCapitalized;

	/**
	 * 
	 * @param txt
	 * @return
	 */
	public void capitalizeFully(ActionEvent evt) {

		String texto = (String) FacesUtils.getAttributes(evt).get("texto");
		setTextoCapitalized(StringHelper.capitalizeFully(texto));

	}

	public String getTextoCapitalized() {
		return textoCapitalized;
	}

	public void setTextoCapitalized(String textoCapitalized) {
		this.textoCapitalized = textoCapitalized;
	}

}
