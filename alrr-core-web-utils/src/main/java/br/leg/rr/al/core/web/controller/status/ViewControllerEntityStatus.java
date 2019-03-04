package br.leg.rr.al.core.web.controller.status;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Map;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.omnifaces.util.Faces;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.dao.JPADaoStatus;
import br.leg.rr.al.core.domain.NavigationOutcomeDefault;
import br.leg.rr.al.core.jpa.EntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;
import br.leg.rr.al.core.web.util.FacesUtils;

/**
 * @author Ednil Libanio da Costa Junior
 * @date 17-04-2018
 * @param <T>
 * @param <ID>
 */
public abstract class ViewControllerEntityStatus<T extends EntityStatus<ID>, ID extends Serializable>
		extends BaseControllerEntityStatus<T, ID> {

	private static final long serialVersionUID = -3680839734379111991L;

	protected String jaExisteMsg = "Entidade já existe.";

	/**
	 * Campo utilizado pelo método {@link #pesquisar()}. Ele pode ser utilizado ou
	 * chamado dentro do método secundário {@link #prePesquisar()}.
	 */
	private Map<String, Object> filtros;

	/**
	 * Método secundário chamado pelo método {@link #preSalvar() } que preenche uma
	 * entidade com valores customizados antes de serem validados e salvo na base de
	 * dados. <br>
	 * Por exemplo, preencher a data de cadastro do usuário.
	 */
	protected void preInserir() {

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
	 * Método secundário chamado pelo método {@link #inserir()} que cria uma nova
	 * entidade e lança uma mensagem de sucesso após inserir na base de dados.
	 * 
	 * @see #inserir()
	 */
	protected void posInserir() {
		FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_SALVO_COM_SUCESSO);
		setEntity(createEntity());
	}

	/**
	 * Método secundário chamado pelo método {@link #atualizar()} que lança uma
	 * mensagem de sucesso após atualizar os dados da entidade na base de dados.
	 * 
	 * @see #atualizar()
	 */
	protected void posAtualizar() {
		FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_ATUALIZADO_COM_SUCESSO);
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
	 * Cria uma nova entiade e chama a próxima view.
	 * 
	 * @return ActionStatusType.NOVO
	 */
	public String novo() {
		setEntity(createEntity());
		return NavigationOutcomeDefault.NOVO.toString();
	}

	/**
	 * <p>
	 * Método responsável por incluir ou atualizar os dados de uma entidade na base
	 * de dados. Antes de inserir ou atualizar os dados, o método {@link #validar()}
	 * é executado. Se validado, chama o método
	 * {@link JPADaoStatus#jaExiste(EntityStatus)} que verifica se já existe os
	 * dados informados.
	 * </p>
	 * <p>
	 * O método {@link JPADaoStatus#jaExiste(EntityStatus)} deve ser implementado
	 * para cada <code>Service</code> e satisfazer aos dois fluxos: atualizar e
	 * inserir. No caso do fluxo atualizar, deve-se incluir como condição na
	 * verificação o atributo <code>id</code> da entidade para evitar que a própria
	 * entidade seja pesquisada. Por exemplo:
	 * </p>
	 * 
	 * <pre>
	* <code>
	* if (entidade.getId() != null){
	* 	Predicate cond = cb.notEqual(root.get(Entidade_.id), entidade.getId());
	* }
	* </code>
	 * </pre>
	 * 
	 * Caso não seja tratado para o fluxo atualizar, ocorrerá erro na hora de
	 * atualizar os dados.
	 * 
	 * @return Navegação da view de acordo com a ação:<br>
	 *         <ul>
	 *         <li>Se for <strong>inserido</strong> com sucesso, retorna
	 *         {@value NavigationOutcomeDefault#INSERIDO_COM_SUCCESSO}.</li>
	 *         <li>Se for <strong>atualizado</strong> com sucesso, retorna
	 *         {@value NavigationOutcomeDefault#ATUALIZADO_COM_SUCCESSO}.</li> Caso
	 *         contrário, {@value NavigationOutcomeDefault#FALHA}.
	 */
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
						return NavigationOutcomeDefault.ATUALIZADO_COM_SUCCESSO.toString();
					} else {
						inserir();
						return NavigationOutcomeDefault.INSERIDO_COM_SUCCESSO.toString();
					}

				} else {

					FacesMessageUtils.addFatal(jaExisteMsg);
					return NavigationOutcomeDefault.FALHA.toString();
				}
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
					FacesMessageUtils.addFatal(ex.getMessage());

				} else {

					FacesMessageUtils.addFatal(t.getMessage());
				}

				FacesMessageUtils.addFatal(e.getMessage());

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

	/**
	 * Carrega a entidade para editar e chama a próxima view. Caso haja alguma
	 * configuração especifica, deve ser definido no método {@link #preEditar}.
	 * 
	 * @return ActionStatusType.EDITAR
	 */
	public String editar() {
		preEditar();
		if (getId() != null) {
			setEntity(getBean().buscar(getId()));
			Faces.setFlashAttribute("entity", getEntity());

		} else if (isEditar()) {
			setEntity(getEntity());
			Faces.setFlashAttribute("entity", getEntity());
		}

		return NavigationOutcomeDefault.EDITAR.toString();
	}

	/**
	 * Método chamado após a confirmação de exclusão. Exclui a "entidade"
	 * selecionada e fecha o dialog de exclusão ou limpa a view de exlusão. Caso
	 * <i>entidade</i> seja vazia, retorna uma mensagem de erro.
	 * 
	 * @return
	 */
	public String excluir() {
		FacesMessageUtils.addError("Método \"excluir\" não implementado");
		throw new NotImplementedException("Método não implementado");
	}

	/**
	 * Limpa a view inteira. Não usar para Dialog. Usar o método cancelar() em vez
	 * disso.
	 * 
	 * @return TODO
	 */
	public String limpar() {
		FacesUtils.getViewMap().clear();
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
	 * Recarrega a entidade com os dados atualizados e exibe o Dialog de Detalhes.
	 * Ativar com ActionListener.
	 * 
	 */
	public String detalhes() {
		FacesMessageUtils.addError("Método \"detalhes\" não implementado.");
		throw new NotImplementedException("Método não implementado");
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

	/*
	 * public String renovarRegistroDataModel() { if (isEditar()) {
	 * 
	 * // captura o indice para trocar na lista a entidade. if (getEntities().size()
	 * > 0) { int pos = getDataModel().getIndex();
	 * 
	 * if (pos >= 0) { // busca a entidade atualizada.
	 * preRenovarRegistroDatalModel(); getEntities().set(pos, getEntity());
	 * FacesMessageUtils.addInfo(CoreUtilsValidationMessages.
	 * REGISTRO_RENOVADO_COM_SUCESSO); } }
	 * 
	 * }
	 * 
	 * return null; }
	 */

	public Boolean isEditavel() {
		return isEditar();
	}

	public Boolean isNotEditavel() {
		return (!isEditar());
	}

	/**
	 * TODO: Colocar comentário
	 */
	/* public abstract void limpar(); */

	public String getJaExisteMsg() {
		return jaExisteMsg;
	}

	public void setJaExisteMsg(String jaExisteMsg) {
		this.jaExisteMsg = jaExisteMsg;
	}
}
