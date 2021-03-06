/*******************************************************************************
 * Copyright (c) 2017, KMDR Consultoria e Serviços, Boa Vista, RR - Brasil.
 * Todos os direitos reservados. Este programa é propriedade da Assembleia Legislativa do Estado de Roraima e não é permitida a distribuição, alteração ou cópia da mesma sem prévia autoriazação.
 ******************************************************************************/
package br.leg.rr.al.core.util.comparator;

import java.util.Comparator;
import java.util.Date;

public class DateComparator implements Comparator<Date> {

    @Override
	public int compare(final Date d1, final Date d2) {        
        return d1.compareTo(d2);
    }

}
