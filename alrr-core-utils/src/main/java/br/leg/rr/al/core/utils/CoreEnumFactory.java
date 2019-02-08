/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.utils;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.leg.rr.al.core.domain.DiaSemana;
import br.leg.rr.al.core.domain.EmailType;
import br.leg.rr.al.core.domain.Mes;
import br.leg.rr.al.core.domain.SistemaLocale;
import br.leg.rr.al.core.domain.StatusType;
import br.leg.rr.al.core.domain.TurnoType;

/**
 * @author <a href="mailto:ednil.libanio@gmail.com"> Ednil Libanio da Costa
 *         Junior</a>
 * 
 * @since 1.0.0
 */
@Named
public class CoreEnumFactory {

	@Produces
	public DiaSemana[] getDiasSemana() {
		return DiaSemana.values();
	}

	@Produces
	public EmailType[] getEmailTypes() {
		return EmailType.values();
	}

	@Produces
	public Mes[] getMeses() {
		return Mes.values();
	}

	@Produces
	public TurnoType[] getTurnoTypes() {
		return TurnoType.values();
	}

	@Produces
	public StatusType[] getStatusType() {
		return StatusType.values();
	}

	@Produces
	public SistemaLocale[] getIdiomas() {
		return SistemaLocale.values();
	}

}
