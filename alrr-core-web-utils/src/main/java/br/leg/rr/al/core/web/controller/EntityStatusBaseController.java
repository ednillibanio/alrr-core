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

import br.leg.rr.al.core.CoreUtilsValidationMessages;
import br.leg.rr.al.core.dao.BeanException;
import br.leg.rr.al.core.dao.JPADaoStatus;
import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.jpa.EntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;

public abstract class EntityStatusBaseController<T extends EntityStatus<ID>, ID extends Serializable>
		extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5110462079044237057L;
	private JPADaoStatus<T, ID> bean;
	private ID id;
	private T entity;
	private List<T> entities = new ArrayList<T>();
	private EntityStatusDataModel<T, ID> dataModel = new EntityStatusDataModel<T, ID>(getEntities());

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
	 */
	protected T createEntity() {
		try {
			// Se não for abstract a classe, então gera uma instancia.
			if (!Modifier.isAbstract(getClassType().getModifiers())) {
				return getClassType().newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Atualiza os dados da entidade selecionada.
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
			setDataModel(new EntityStatusDataModel<T, ID>(entities));
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
		Set<ConstraintViolation<T>> violations = validator.validate(entity);
		for (ConstraintViolation<?> violation : violations) {
			FacesMessageUtils
					.addError(violation.getPropertyPath().toString().concat(": ").concat(violation.getMessage()));
		}
		return (violations.size() < 1);
	}

	/**
	 * Modifica a situação da entidade para ativa.
	 * 
	 * @see {@link StatusType}, {@link JPADaoStatus}
	 * @return Sempre retornará <code>null</code> porque a ação sempre ocorrerá na
	 *         mesma view.
	 */
	public String ativar() {

		try {
			int pos = dataModel.getIndex();
			T entidade = getBean().ativar(getEntity());
			getEntities().set(pos, entidade);
			FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_ATIVADO_COM_SUCESSO);
		} catch (BeanException e) {
			FacesMessageUtils.addError(e.getMessage());
		} catch (Exception e) {
			FacesMessageUtils.addError(e.getMessage());
		}
		return null;
	}

	/**
	 * Modifica a situação da entidade para inativa.
	 * 
	 * @see {@link StatusType}, {@link JPADaoStatus}
	 * @return Sempre retornará <code>null</code> porque a ação sempre ocorrerá na
	 *         mesma view.
	 */
	public String desativar() {

		try {

			int pos = dataModel.getIndex();
			T entidade = getBean().desativar(getEntity());
			getEntities().set(pos, entidade);
			FacesMessageUtils.addInfo(CoreUtilsValidationMessages.REGISTRO_DESATIVADO_COM_SUCESSO);
		} catch (BeanException e) {
			FacesMessageUtils.addError(e.getMessage());
		} catch (Exception e) {
			FacesMessageUtils.addError(e.getMessage());
		}
		return null;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
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

	public EntityStatusDataModel<T, ID> getDataModel() {
		return dataModel;
	}

	public void setDataModel(EntityStatusDataModel<T, ID> dataModel) {
		this.dataModel = dataModel;
	}

	public JPADaoStatus<T, ID> getBean() {
		return bean;
	}

	public void setBean(JPADaoStatus<T, ID> bean) {
		this.bean = bean;
	}

	/**
	 * @return the validatorFactory
	 */
	protected Validator getValidator() {
		return validatorFactory.getValidator();
	}

}
