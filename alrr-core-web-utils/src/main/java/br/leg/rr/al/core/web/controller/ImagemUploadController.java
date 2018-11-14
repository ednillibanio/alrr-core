package br.leg.rr.al.core.web.controller;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.leg.rr.al.core.web.util.FacesMessageUtils;
import br.leg.rr.al.core.web.util.ImagemHelper;

@Named
@ViewScoped
public class ImagemUploadController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6952305704698483499L;

	private UploadedFile imagemEnviada;

	private byte[] imagemConteudo;

	/**
	 * Helper que irá tratar a imagem capturada pela webcam ou pelo FileUpload que
	 * carrega a imagem. Os controller que fazem a tarefa são o WebCamController e
	 * ImagemUploadController.
	 */
	// @Inject
	// TODO removido por que removi @ViewScope do ImagemHelper.
	private ImagemHelper imagemHelper;

	public void upload(FileUploadEvent event) {
		UploadedFile img = event.getFile();
		if (img != null && img.getContents() != null) {
			setImagemConteudo(img.getContents());
			FacesMessageUtils.addInfo("Foto selecionada com sucesso.");
		}
	}

	public void enviarImagemSelecionada() {
		if (imagemHelper != null && imagemConteudo != null) {
			imagemHelper.setImagem(imagemConteudo);
		}
	}

	public StreamedContent getImagemSelecionada() {
		return new DefaultStreamedContent(new ByteArrayInputStream(getImagemConteudo()));
	}

	public UploadedFile getImagemEnviada() {
		return imagemEnviada;
	}

	public void setImagemEnviada(UploadedFile imagemEnviada) {
		this.imagemEnviada = imagemEnviada;
	}

	public byte[] getImagemConteudo() {
		return imagemConteudo;
	}

	public void setImagemConteudo(byte[] imagemConteudo) {
		this.imagemConteudo = imagemConteudo;
	}

	public ImagemHelper getImagemHelper() {
		return imagemHelper;
	}

	public void setImagemHelper(ImagemHelper imagemHelper) {
		this.imagemHelper = imagemHelper;
	}

}
