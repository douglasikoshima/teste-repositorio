package br.com.indrasistemas.vivoservices.atendimento.backoffice.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;

public class ComprovanteCancelamentoTOBuilder {

	public static List buildComprovanteCancelamentoTO(List list) {
		List result = null;

		if (list != null && list.size() > 0) {
			result = new ArrayList();
			ComprovanteCancelamentoTO to = null;

			for (Iterator i = list.iterator(); i.hasNext();) {
				Object[] element = (Object[]) i.next();

				to = new ComprovanteCancelamentoTO();
				
				to.setCdConta((String) element[0]);
				to.setCdDigitoConta(new Integer(((BigDecimal) element[1]).intValue()));
				to.setCdAreaRegistro(new Integer(((BigDecimal) element[2]).intValue()));
				to.setNrLinha((BigDecimal) element[3]);
				to.setDtAbertura((Date) element[4]);
				to.setIdAtendimento(((Long)element[5]));
				to.setNrDocumento((String) element[6]);				
				to.setNmPessoa((String) element[7]);

				result.add(to);
			}

		}

		return result;
	}
}
