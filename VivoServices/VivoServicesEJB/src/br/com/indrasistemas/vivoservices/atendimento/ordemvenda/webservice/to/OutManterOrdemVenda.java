package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class OutManterOrdemVenda extends BaseTransferObject {

	private static final long serialVersionUID = -4055214518937815L;

    private String cdRetorno="";
    private String dsRetorno="";
    
    public String getCdRetorno() {
        return cdRetorno;
    }
    public void setCdRetorno(String cdRetorno) {
        this.cdRetorno = cdRetorno;
    }
    public String getDsRetorno() {
        return dsRetorno;
    }
    public void setDsRetorno(String dsRetorno) {
        this.dsRetorno = dsRetorno;
    }
}
