package br.leg.rr.al.core.web.controller;

import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.dao.JPADao;
import br.leg.rr.al.core.jpa.Entity;
import br.leg.rr.al.core.web.controller.status.ViewControllerEntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;
import br.leg.rr.al.core.web.util.FacesUtils;

/**
 * FIXME: Arrumar para adaptar a realidade da ALE.
 * 
 * @author ednil
 *
 */
public abstract class BaseController<T extends Entity<ID>, ID extends Serializable> implements Serializable {

	
	private static final long serialVersionUID = -7033073413761404445L;

	protected static Marker fatal = MarkerFactory.getMarker("FATAL");
	
	private JPADao<T, ID> bean;
	private ID id;
	private T entity;
	private List<T> entities = new ArrayList<T>();
	private EntityDataModel<T, ID> dataModel = new EntityDataModel<T, ID>(getEntities());

	@Resource
	protected UserTransaction utx;

	@Inject
	private ValidatorFactory validatorFactory;

	@SuppressWarnings("unchecked")
	private Class<T> getClassType() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		return ((Class<T>) parameterizedType.getActualTypeArguments()[0]);
	}

	/**
	 * Retorna uma nova instancia de entidade.
	 * 
	 * @return entidade
	 */
	protected T createEntity() {
		try {
			// Se não for abstract a classe, então gera uma instância.
			if (!Modifier.isAbstract(getClassType().getModifiers())) {
				return getClassType().newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Carrega novamente os dados da entidade selecionada.
	 */
	protected void carregarEntity() {
		if (getId() != null) {
			setEntity(getBean().buscar(getId()));
		} else if (getEntity().getId() != null) {
			setEntity(getBean().buscar(getEntity().getId()));
		}
	}

	protected void createDataModel(List<T> entities) {
		if (entities != null) {
			setDataModel(new EntityDataModel<T, ID>(entities));
			if (entities.size() < 1) {
				FacesMessageUtils.addError(CoreUtilsValidationMessages.REGISTRO_NAO_ENCONTRADO);
			}
		}
	}

	protected void createDataModel() {
		createDataModel(getEntities());
	}

	/**
	 * Executa todas as validações definidas no nivel de classe por grupo.
	 * 
	 * @return retorna valido caso não haja restrição.Caso contrário, retorna falso
	 *         e também atribui as mensagens de erro de validação.
	 */
	protected Boolean validar() {
		Validator validator = getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(getEntity());
		for (ConstraintViolation<?> violation : violations) {
			FacesMessageUtils
					.addError(violation.getPropertyPath().toString().concat(": ").concat(violation.getMessage()));
		}
		return (violations.size() < 1);
	}

	/**
	 * Verifica se o usuário tem permissão de
	 * 
	 * @param outcome
	 * @return
	 */
	public boolean isUserInRole(String permission) {

		// TODO: Essa mensagem não é lançada quando não há permissao. Ver como
		// fazer para disparar.
		if (StringUtils.isBlank(permission)) {
			// FacesMessageUtil.addErrorMessage(new
			// NullArgumentException("permissao").toString());
		}
		return FacesUtils.isUserInRole(permission);

	}

	/**
	 * 
	 * @param outcome
	 * @return
	 */
	public boolean hasViewPermission(String outcome) {

		if (StringUtils.isNotBlank(outcome) && StringUtils.isNotEmpty(outcome)) {
			return FacesUtils.hasViewPermission(outcome);
		}
		return false;

	}

	/**
	 * <p>
	 * Este método é responsável por buscar novamente a entidade trazendo os dados
	 * atualizados. O método é chamado pelo método
	 * {@link #renovarRegistroDataModel()}. Não há necessidade de alterar o método
	 * {@link #renovarRegistroDataModel()} após sovrescrever esse método.
	 * </p>
	 * <p>
	 * Caso queira carregar (fetch) as outras entidades associadas, o método
	 * {@link #preRenovarRegistroDatalModel()} deverá ser sobrescrito.
	 * </p>
	 * 
	 * @return entidade que será substituída pela grid de pesquisa.
	 * @see ViewControllerEntityStatus#renovarRegistroDataModel()
	 */
	protected T preRenovarRegistroDatalModel() {
		return getBean().buscar(getEntity());
	}

	public String renovarRegistroDataModel() {

		// captura o indice para trocar na lista a entidade.
		if (getEntities().size() > 0) {
			int pos = getDataModel().getIndex();

			if (pos >= 0) {
				// busca a entidade atualizada.
				preRenovarRegistroDatalModel();
				getEntities().set(pos, getEntity());
				FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_RENOVADO_COM_SUCESSO);
			}
		}

		return null;
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

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public EntityDataModel<T, ID> getDataModel() {
		return dataModel;
	}

	public void setDataModel(EntityDataModel<T, ID> dataModel) {
		this.dataModel = dataModel;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public JPADao<T, ID> getBean() {
		return bean;
	}

	public void setBean(JPADao<T, ID> bean) {
		this.bean = bean;
	}

	/**
	 * @return o validator
	 */
	protected Validator getValidator() {
		return validatorFactory.getValidator();
	}

}
