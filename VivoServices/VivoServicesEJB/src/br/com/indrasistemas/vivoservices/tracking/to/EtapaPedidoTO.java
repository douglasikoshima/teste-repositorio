package br.com.indrasistemas.vivoservices.tracking.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class EtapaPedidoTO extends BaseTransferObject {

	private static final long serialVersionUID = -4717276232182556483L;

	private String dsEtapa;

	private String dtEtapa;

	public String getDsEtapa() {
		return dsEtapa;
	}

	public void setDsEtapa(String dsEtapa) {
		this.dsEtapa = dsEtapa;
	}

	public String getDtEtapa() {
		return dtEtapa;
	}

	public void setDtEtapa(String dtEtapa) {
		this.dtEtapa = dtEtapa;
	}

}
