package br.leg.rr.al.core.web.util;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.inject.Named;
import javax.persistence.Transient;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

// TODO comentei pra ver se funciona sem isso daqui. Porque não faz sentido.
// @ViewScoped
@Named
public class ImagemHelper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1357429274906324016L;

	private byte[] imagem;

	public StreamedContent getImagemStreamed() {
		return getImagemStreamed(getImagem());
	}

	@Transient
	public StreamedContent getImagemStreamed(byte[] imagem) {
		if (imagem != null) {
			return new DefaultStreamedContent(new ByteArrayInputStream(imagem), "image/jpeg");
		}
		return null;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

}
