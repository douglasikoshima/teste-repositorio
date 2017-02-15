package br.com.indrasistemas.vivoservices.tracking.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class OrdemVendaTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	private Long idOrdemVenda;
	
	private Long nrOrdemVenda;

	public Long getIdOrdemVenda() {
		return idOrdemVenda;
	}

	public void setIdOrdemVenda(Long idOrdemVenda) {
		this.idOrdemVenda = idOrdemVenda;
	}

	public Long getNrOrdemVenda() {
		return nrOrdemVenda;
	}

	public void setNrOrdemVenda(Long nrOrdemVenda) {
		this.nrOrdemVenda = nrOrdemVenda;
	}

}
