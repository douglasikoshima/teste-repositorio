package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to.manterOrdemVenda;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to.manterOrdemVendaResponse;

public interface ManterOrdemVendaWS {

    public manterOrdemVendaResponse registrarPalito(RequestInfoTO requestInfo, manterOrdemVenda manterOrdemVendaParam);

}
