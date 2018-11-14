package br.leg.rr.al.core.web.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.CaptureEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.leg.rr.al.core.web.util.FacesMessageUtils;
import br.leg.rr.al.core.web.util.FacesUtils;
import br.leg.rr.al.core.web.util.ImagemHelper;

@Named
@ViewScoped
public class WebCamController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2175901513895966202L;

	/**
	 * Conteúdo da imagem que foi enviada pelo usuário.
	 */
	private byte[] imagem;

	/**
	 * Helper que irá tratar a imagem capturada pela webcam ou pelo FileUpload que
	 * carrega a imagem. Os controller que fazem a tarefa são o WebCamController e
	 * ImagemUploadController.
	 */
	private ImagemHelper imagemHelper;

	private Boolean webCamAtiva = false;

	public void onCapture(CaptureEvent captureEvent) {

		imagem = captureEvent.getData();

	}

	public void showWebCam() {
		ativarWebCam();
		setImagem(null);
		FacesUtils.showDialog("dlg-capturar-imagem");
	}

	public void enviarImagemCapturada() {
		if (imagem != null) {
			imagemHelper.setImagem(imagem);
			setWebCamAtiva(false);
		} else {
			FacesMessageUtils.addError("Nenhuma imagem foi selecionada.");
		}
	}

	public void ativarWebCam() {
		setWebCamAtiva(true);
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Boolean getWebCamAtiva() {
		return webCamAtiva;
	}

	public void setWebCamAtiva(Boolean webCamAtiva) {
		this.webCamAtiva = webCamAtiva;
	}

	public StreamedContent getImagemStreamed() {
		return new DefaultStreamedContent(new ByteArrayInputStream(getImagem()));
	}

	public ImagemHelper getImagemHelper() {
		return imagemHelper;
	}

	public void setImagemHelper(ImagemHelper imagemHelper) {
		this.imagemHelper = imagemHelper;
	}

}
