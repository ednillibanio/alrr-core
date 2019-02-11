package br.leg.rr.al.core.web.controller.status;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.jpa.EntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;
import br.leg.rr.al.core.web.util.FacesUtils;

public class DominioController<T extends EntityStatus<Integer>> extends ViewControllerEntityStatus<T, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -434743169223803479L;

	private String painelGeralName;

	/**
	 * Informe o valor da variavel 'widgetVar' do Dialogo Editar.
	 */
	private String editarDialogName;

	/**
	 * Informe o valor da variavel 'widgetVar' do Dialogo Detalhes.
	 */
	private String detalhesDialogName;

	/**
	 * Informe o valor da variavel 'widgetVar' do Dialogo Novo.
	 */
	private String novoDialogName;

	public DominioController() {
		setEntity(createEntity());
	}

	/**
	 * Cria uma nova entidade (createEntity()) e exibe o dialog para cadastra-la.
	 * Deve usar o método {@code action} do componente primefaces para chamar o
	 * método. <br>
	 * Antes de chamar o método {@code novo}, precisa preencher na inicialização a
	 * variável {@code novoDialogName}.
	 */
	@Override
	public String novo() {
		setEntity(createEntity());
		FacesUtils.showDialog(getNovoDialogName());
		return null;
	}

	@Override
	public void preEditar() {
		super.preEditar();

	}

	/**
	 * Exibe o Dialog de Edição. Caso haja alguma configuração especifica, deve ser
	 * definido no método preEditar.
	 */
	@Override
	public String editar() {
		preEditar();
		if (isEditar()) {
			T elemento = getBean().buscar(getEntity());
			if (!elemento.equals(getEntity())) {
				FacesUtils.showDialog("dlgConfirmWV");
			} else {
				setEntity(getBean().carregar(getEntity()));
				getBean().detached(getEntity());
				FacesUtils.update("frm-feriado");
				FacesUtils.showDialog(getEditarDialogName());
			}
		} else {
			FacesMessageUtils.createError(CoreUtilsValidationMessages.REGISTRO_NAO_SELECIONADO);
		}
		return null;
	}

	@Override
	public String salvar() {

		super.salvar();
		return null;

	}

	/*
	 * 
	 * @see ViewControllerEntityStatus#posInserir()
	 */
	@Override
	protected void posInserir() {
		FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_SALVO_COM_SUCESSO);
		setEntity(createEntity());
	}

	@Override
	protected void posAtualizar() {
		super.posAtualizar();
		FacesUtils.hideDialog(getEditarDialogName());
	}

	@Override
	public String detalhes() {
		preDetalhes();
		if (getEntity() != null && getEntity().getId() != null) {
			carregarEntity();
			FacesUtils.showDialog(getDetalhesDialogName());
		} else {
			// TODO: Colocar um pop-up informando que tem que selecionar um
			// registro.
			// Implementar algo que mostre uma msg dialog dizendo para
			// selecionar uma entidade.
		}
		return null;
	}

	/**
	 * Método chamado após a confirmação de exclusão. Exclui a "entidade"
	 * selecionada e fecha o dialog de exclusão. Caso <i>entity</i> seja vazio,
	 * retorna uma mensagem de erro.
	 */
	public String excluir() {

		if (getEntity() == null) {
			FacesMessageUtils.addError(CoreUtilsValidationMessages.REGISTRO_NAO_SELECIONADO);
		} else {
			try {
				getBean().excluir(getEntity());
				if (getEntities() != null && getEntities().contains(getEntity())) {
					if (getEntities().remove(getEntity())) {
						FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_EXCLUIDO_COM_SUCESSO);
					}
				}

			} catch (Exception e) {
				FacesMessageUtils.addFatal(e.getMessage());
				e.printStackTrace();
			}
		}

		return null;

	}

	/*
	 * @Override public void limpar() { FacesUtils.resetView(); }
	 */

	/**
	 * Retorna o valor da variavel 'widgetVar' do Dialogo Editar.
	 */
	public String getEditarDialogName() {
		return editarDialogName;
	}

	/**
	 * Retorna o nome do Dialogo Editar.
	 * 
	 * @param widgetVar valor da variavel 'widgetVar'.
	 */
	public void setEditarDialogName(String widgetVar) {
		this.editarDialogName = widgetVar;
	}

	/**
	 * Retorna o valor da variavel 'widgetVar' do Dialogo Detalhes.
	 */
	public String getDetalhesDialogName() {
		return detalhesDialogName;
	}

	/**
	 * Retorna o nome do Dialogo Detalhes.
	 * 
	 * @param widgetVar valor da variavel 'widgetVar'.
	 */
	public void setDetalhesDialogName(String widgetVar) {
		this.detalhesDialogName = widgetVar;
	}

	/**
	 * Retorna o valor da variavel 'widgetVar' do Dialogo Novo.
	 */
	public String getNovoDialogName() {
		return novoDialogName;
	}

	/**
	 * Retorna o nome do Dialogo Novo.
	 * 
	 * @param widgetVar valor da variavel 'widgetVar'.
	 */
	public void setNovoDialogName(String widgetVar) {
		this.novoDialogName = widgetVar;
	}

	public String getPainelGeralName() {
		return painelGeralName;
	}

	public void setPainelGeralName(String painelGeralName) {
		this.painelGeralName = painelGeralName;
	}

}
