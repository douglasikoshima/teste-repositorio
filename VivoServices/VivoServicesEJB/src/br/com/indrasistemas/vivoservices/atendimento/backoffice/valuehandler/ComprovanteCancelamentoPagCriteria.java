package br.com.indrasistemas.vivoservices.atendimento.backoffice.valuehandler;

import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.vivoservices.atendimento.backoffice.to.ComprovanteCancelamentoTO;

public class ComprovanteCancelamentoPagCriteria extends PaginatedCriteria {

	ComprovanteCancelamentoTO filtro;

	public ComprovanteCancelamentoPagCriteria(Integer firstResult, Integer maxResults) {
		super(firstResult, maxResults);
	}

	public ComprovanteCancelamentoTO getFiltro() {
		return filtro;
	}

	public void setFiltro(ComprovanteCancelamentoTO filtro) {
		this.filtro = filtro;
	}

}
