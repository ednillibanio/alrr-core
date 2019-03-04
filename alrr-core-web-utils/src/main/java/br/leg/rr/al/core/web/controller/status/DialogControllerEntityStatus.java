package br.leg.rr.al.core.web.controller.status;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.domain.NavigationOutcomeDefault;
import br.leg.rr.al.core.jpa.EntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;
import br.leg.rr.al.core.web.util.FacesUtils;

public class DialogControllerEntityStatus<T extends EntityStatus<ID>, ID extends Serializable>
		extends BaseControllerEntityStatus<T, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -434743169223803479L;

	protected String jaExisteMsg = "Entidade já existe.";

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
	 * Campo utilizado pelo método {@link #pesquisar()}. Ele pode ser utilizado ou
	 * chamado dentro do método secundário {@link #prePesquisar()}.
	 */
	private Map<String, Object> filtros;

	/**
	 * Informe o valor da variavel 'widgetVar' do Dialogo Novo.
	 */
	private String novoDialogName;

	public DialogControllerEntityStatus() {
		setEntity(createEntity());
	}

	/**
	 * Método secundário chamado pelo método {@link #preSalvar() } que preenche uma
	 * entidade com valores customizados antes de serem validados e salvo na base de
	 * dados. <br>
	 * Por exemplo, preencher a data de cadastro do usuário.
	 */
	protected void preInserir() {

	}

	/**
	 * Método secundário chamado pelo método {@link #salvar()} que chama os métodos
	 * de {@link #preInserir()} e {@link #preAtualizar()}. <br>
	 * Nesse momento faz todas as inserções e atualizações customizadas na entidade,
	 * antes de chamar o método {@code salvar()}.
	 * 
	 */
	protected void preSalvar() {
		if (isEditar()) {
			preAtualizar();
		} else {
			preInserir();
		}
	}

	/**
	 * Método secundário que preenche variáveis, componentes, campos dos controller
	 * com valores da entidade antes de chamar a view ou dialog de detalhes. <br>
	 * Além disso pode fazer qualquer outra customização ou configuração antes de
	 * mostrar os detalhes da entidade. Por exemplo, chamar o método
	 * <code>bean.retrieve(entidade)</code> para carregar os dados atualizados da
	 * entidade. <br>
	 * <b>Exemplo:</b> <blockquote>
	 * 
	 * <pre>
	 * Entidade entidadeSelecionada = getEntity();
	 * 
	 * // Carrega os dados atualizados.
	 * setEntity(bean.retrieve(entidadeSelecionada));
	 * 
	 * </pre>
	 * 
	 * </blockquote>
	 */
	protected void preDetalhes() {

	}

	/**
	 * Método secundário que preenche variáveis, componentes, campos dos controller
	 * com valores da entidade antes de chamar a view ou dialog de edição. <br>
	 * Além disso pode fazer qualquer outra customização ou configuração antes da
	 * edição. Por exemplo, chamar o método <code>bean.retrieve(entidade)</code>
	 * para deixar os dados atualizados para edição. <br>
	 * <b>Exemplo:</b>
	 * 
	 * <pre>
	 * Entidade entidadeSelecionada = getEntity();
	 * 
	 * // Atualiza os dados para edição.
	 * setEntity(bean.retrieve(entidadeSelecionada));
	 * setNome(getEntity.getNome());
	 * </pre>
	 * 
	 */
	protected void preEditar() {

	}

	/**
	 * Método secundário chamado pelo método {@link #preSalvar()} que atualiza
	 * valores customizados na entidade antes de serem validados e salvo na base de
	 * dados. <br>
	 * Por exemplo, preencher a data do último acesso do usuário.
	 */
	protected void preAtualizar() {

	}

	/**
	 * Método secundário chamado pelo método {@link #pesquisar()} que faz a pesquisa
	 * na base de dados de acordo com os filtros informados. Esse método é o local
	 * ideal para definir os filtros que serão usados na pesquisa. <br>
	 * Por exemplo, preencher a data do último acesso do usuário.
	 */
	protected void prePesquisar() {

	}

	/**
	 * <p>
	 * Método secundário chamado pelo método {@link #editar()}. Este método é util
	 * principalmente para preencher campos na view que não fazem parte da uma
	 * entidade. Por exemplo, um formulário de cadastrar cep, que usa uma variavel
	 * 'numero' que serve pra pesquisar cep numa base de dados qualquer na internet.
	 * Quando retorna o valor, preenche os outros campos da entidade Cep. Este mesmo
	 * formulário de cadastro de Cep, é utilizado para editar. Quando o usuário
	 * clica em editar Cep, os dados da entidade Cep são carregados. Porém o sistema
	 * não sabe que o numero do cep é exibido num campo 'numero' e não na entidade.
	 * Neste caso, deve-se chamar o método {@link #posEditar()} para inserir este
	 * valor de forma manual na variavel 'numero'.
	 * 
	 * <pre>
	<code>
	{@literal @Override}
	protected void posEditar() {
		this.numero = cep.getNumero();
	} 
	</code>
	 * </pre>
	 * </p>
	 */
	protected void posEditar() {

	}

	/**
	 * 
	 * @see ViewControllerEntityStatus#posInserir()
	 */
	protected void posInserir() {
		FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_SALVO_COM_SUCESSO);
		setEntity(createEntity());
	}

	protected void posAtualizar() {
		FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_ATUALIZADO_COM_SUCESSO);
		FacesUtils.hideDialog(getEditarDialogName());
	}

	/**
	 * Método secundário chamado pelo método {@link #preSalvar()} que verifica se a
	 * entidade está carregada com os valores da base de dados. Também pode ser
	 * chamado por outros métodos em determinadas situações.
	 * 
	 * @return <code>true</code> se entidade está com valores da base de dados. Caso
	 *         contrário, <code>false</code>.
	 */
	protected Boolean isEditar() {
		return (getEntity() != null && getEntity().getId() != null);
	}

	/**
	 * Método secundário chamado pelo método {@link #salvar()} e que insere uma nova
	 * entidade na base de dados. <br>
	 * Após inserir na base de dados, o método {@link #posInserir()} é chamado.
	 */
	protected void inserir() {

		getBean().salvar(getEntity());
		posInserir();

	}

	/**
	 * Método secundário chamado pelo método {@link #salvar()} e que atualiza uma
	 * entidade já existente na base de dados. <br>
	 * Após atualizar os dados da entidade na base de dados, o método
	 * {@link #posAtualizar()} é chamado.
	 */
	protected void atualizar() {
		getBean().atualizar(getEntity());
		posAtualizar();
	}

	/**
	 * Cria uma nova entidade (createEntity()) e exibe o dialog para cadastra-la.
	 * Deve usar o método {@code action} do componente primefaces para chamar o
	 * método. <br>
	 * Antes de chamar o método {@code novo}, precisa preencher na inicialização a
	 * variável {@code novoDialogName}.
	 */
	public String novo() {
		if (getNovoDialogName() == null) {
			FacesMessageUtils.addError("Nome do Dialogo Novo não informado.");
			return null;
		}
		setEntity(createEntity());
		FacesUtils.showDialog(getNovoDialogName());
		return null;
	}

	/**
	 * Exibe o Dialog de Edição. Caso haja alguma configuração especifica, deve ser
	 * definido no método preEditar.
	 */
	public String editar() {
		if (getEditarDialogName() == null) {
			FacesMessageUtils.addError("Nome do Dialogo Editar não informado.");
			return null;
		}

		if (isEditar()) {
			preEditar();
			// setEntity(getBean().carregar(getEntity()));
			/**
			 * troquei o método de cima para usar o de baixo para ficar padrão que nem no
			 * detalhes. Seguindo o padrão pra usar carregarEntity do Controller. A partir
			 * daqui faz as alterações, pois cada controller carrega de um jeito.
			 **/
			carregarEntity();
			getBean().detached(getEntity());
			posEditar();
			FacesUtils.showDialog(getEditarDialogName());
		} else {
			FacesMessageUtils.createError(CoreUtilsValidationMessages.REGISTRO_NAO_SELECIONADO);
		}
		return null;
	}

	public String salvar() {

		preSalvar();

		if (validar()) {
			try {
				if (!getBean().jaExiste(getEntity())) {

					if (isEditar()) {
						atualizar();
						if (getEntities() != null) {
							if (getEntities().size() > 0) {
								int pos = getDataModel().getIndex();
								if (pos >= 0) {
									getEntities().set(pos, getEntity());
								}
							}
						}
						// return NavigationOutcomeDefault.ATUALIZADO_COM_SUCCESSO.toString();
					} else {
						inserir();
						// return NavigationOutcomeDefault.INSERIDO_COM_SUCCESSO.toString();
					}

				} else {

					FacesMessageUtils.addFatal(jaExisteMsg);
					// return NavigationOutcomeDefault.FALHA.toString();
				}
				return null;
				/*
				 * } catch (ControllerException e) { throw e;
				 */

			} catch (ConstraintViolationException e) {
				FacesMessageUtils.addFatal(e.getMessage());
			} catch (EntityExistsException e) {
				FacesMessageUtils.addFatal(e.getMessage());
			} catch (PersistenceException e) {
				// FIXME Terminar de arrumar o tratamento de exceção.
				// nunca cai nessa exceção. vamos ver quando cairá.s
				Throwable t = ExceptionUtils.getRootCause(e);
				if (t instanceof SQLException) {
					SQLException ex = (SQLException) t;
					if (ex.getErrorCode() == 23505 || ex.getSQLState().equals("23505")) {
						throw new PersistenceException(CoreUtilsValidationMessages.REGISTRO_JA_EXISTE);
					}
					if (ex.getErrorCode() == 23502 || ex.getSQLState().equals("23502")) {
						throw new ValidationException(ex.getMessage());
					}
				} else {
					FacesMessageUtils.addFatal(t.getMessage());
				}

			} catch (Exception e) {
				Throwable t = ExceptionUtils.getRootCause(e);
				if (t instanceof SQLException) {
					SQLException ex = (SQLException) t;

					/**
					 * Tratamento de exceção para entidade que já existe na base de dados. Ocorre
					 * quando o sistema não verifica corretamente se já existe a entidade antes de
					 * tentar inserir na base de dados.
					 **/
					if (ex.getErrorCode() == 23505 || ex.getSQLState().equals("23505")) {
						FacesMessageUtils.addFatal(CoreUtilsValidationMessages.REGISTRO_JA_EXISTE);
					}

					// Tratamento de exceção para coluna que possui constraint
					// que não permite nulo.
					else if (ex.getErrorCode() == 23502 || ex.getSQLState().equals("23502")) {
						String entidade = StringUtils.substringAfterLast(getEntity().getClass().toString(), ".");
						String campo = StringUtils.substringBetween(ex.getMessage(), "\"");
						FacesMessageUtils.addFatal(CoreUtilsValidationMessages.DB_ERROR_NOT_NULL_CONSTRAINT, entidade,
								campo);

					} else {
						String entidade = "Entidade: "
								.concat(StringUtils.substringAfterLast(getEntity().getClass().toString(), "."));
						String cod = ex.getSQLState();
						String msg = entidade.concat(". SQL State: ").concat(cod).concat(". ").concat(ex.getMessage());

						FacesMessageUtils.addFatal(msg);
					}

				} else {
					FacesMessageUtils.addFatal(e.getMessage());
				}
			}
		}

		return null;
	}

	public String detalhes() {

		if (getDetalhesDialogName() == null) {
			FacesMessageUtils.addError("Nome do Dialogo Editar não informado.");
			return null;
		}

		if (getEntity() != null && getEntity().getId() != null) {
			preDetalhes();
			carregarEntity();
			FacesUtils.showDialog(getDetalhesDialogName());
		} else {
			FacesMessageUtils.createError(CoreUtilsValidationMessages.REGISTRO_NAO_SELECIONADO);
		}
		return null;
	}

	public String cancelar() {
		FacesMessageUtils.addError("Método \"cancelar\" não implementado.");
		return NavigationOutcomeDefault.CANCELAR.toString();
	}

	/**
	 * Método que atualiza os dados da entidade já existente.
	 * 
	 * @return
	 */
	public String renovar() {
		carregarEntity();
		FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_RENOVADO_COM_SUCESSO);
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

	/**
	 * Método que pesquisa todos os registros na base de dados.
	 */
	public String pesquisar() {
		try {
			prePesquisar();
			
			if (filtros == null || filtros.isEmpty()) {
				getBean().flush();
				setEntities(getBean().buscarTodos());
			} else {
				setEntities(getBean().pesquisar(filtros));
			}

			if (getEntities().size() > 0) {
				createDataModel(getEntities());
			} else {
				FacesMessageUtils.addError(CoreUtilsValidationMessages.REGISTRO_NAO_ENCONTRADO);
			}
		} catch (Exception e) {
			Throwable t = ExceptionUtils.getRootCause(e);
			if (t instanceof SQLException) {
				SQLException ex = (SQLException) t;

				String entidade = "Entidade: "
						.concat(StringUtils.substringAfterLast(getEntity().getClass().toString(), "."));
				String cod = ex.getSQLState();
				String msg = entidade.concat(". SQL State: ").concat(cod).concat(". ").concat(ex.getMessage());

				FacesMessageUtils.addFatal(msg);

			} else {
				FacesMessageUtils.addFatal(e.getMessage());
			}

		}
		return null;
	}

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

	public Map<String, Object> getFiltros() {
		return filtros;
	}

	public void setFiltros(Map<String, Object> filtros) {
		this.filtros = filtros;
	}

}
