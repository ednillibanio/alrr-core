/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.web.util;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.TreeNode;

public final class FacesComponentUtils {

	/**
	 * Retorna o label do componente. Caso não exista, retorna o clientId. Método
	 * pode ser utilizado criar mensagens informando o label do componente
	 * 
	 * @param component
	 *            componente que será obtido o label.
	 * @return retorna o label.
	 */
	public static String getLabel(UIComponent component) {

		Object label = component.getAttributes().get("label");

		if (StringUtils.isEmpty((String) label)) {
			ValueExpression value = component.getValueExpression("label");

			if (value != null) {
				label = value.getValue(FacesUtils.getELContext());
			}
		}

		return (label != null) ? label.toString() : component.getClientId();

	}

	/**
	 * 
	 * @param root
	 */
	public static void collapseOrExpandTreeNode(TreeNode root, Boolean expanded) {

		for (TreeNode node : root.getChildren()) {
			node.setExpanded(expanded);
			node.setSelected(false);
			if (node.getChildren() != null) {
				collapseOrExpandTreeNode(node, expanded);
			}
		}

	}

}
