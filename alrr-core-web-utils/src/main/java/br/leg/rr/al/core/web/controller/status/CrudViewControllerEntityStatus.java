package br.leg.rr.al.core.web.controller.status;

import java.io.Serializable;

import org.apache.commons.lang3.NotImplementedException;
import org.omnifaces.util.Faces;

import br.leg.rr.al.core.domain.NavigationOutcomeDefault;
import br.leg.rr.al.core.jpa.EntityStatus;
import br.leg.rr.al.core.web.util.FacesMessageUtils;

public class CrudViewControllerEntityStatus<T extends EntityStatus<ID>, ID extends Serializable> extends ViewControllerEntityStatus<T, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1203944215885392305L;

	/**
	 * Cria uma nova entiade e chama a próxima view.
	 * 
	 * @return ActionStatusType.NOVO
	 */
	@Override
	public String novo() {
		setEntity(createEntity());
		return NavigationOutcomeDefault.NOVO.toString();
	}

	/**
	 * Carrega a entidade para editar e chama a próxima view.
	 * 
	 * @return ActionStatusType.EDITAR
	 */
	@Override
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

	@Override
	public String detalhes() {
		FacesMessageUtils.addError("Método \"detalhes\" não implementado");
		throw new NotImplementedException("Método não implementado");
	}

	@Override
	public String excluir() {
		FacesMessageUtils.addError("Método \"excluir\" não implementado");
		throw new NotImplementedException("Método não implementado");
	}

	public String cancelar() {
		FacesMessageUtils.addError("Método \"cancelar\" não implementado");
		return NavigationOutcomeDefault.CANCELAR.toString();
	}

}
