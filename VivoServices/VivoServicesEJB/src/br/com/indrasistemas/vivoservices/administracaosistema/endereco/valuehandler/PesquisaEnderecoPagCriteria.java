package br.com.indrasistemas.vivoservices.administracaosistema.endereco.valuehandler;

import br.com.indrasistemas.framework.service.valuehandler.PaginatedCriteria;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO;

public class PesquisaEnderecoPagCriteria extends PaginatedCriteria {

	PesquisaEnderecoTO filtro;

	public PesquisaEnderecoPagCriteria(Integer firstResult, Integer maxResults) {
		super(firstResult, maxResults);
	}

	public PesquisaEnderecoTO getFiltro() {
		return filtro;
	}

	public void setFiltro(PesquisaEnderecoTO filtro) {
		this.filtro = filtro;
	}

}
