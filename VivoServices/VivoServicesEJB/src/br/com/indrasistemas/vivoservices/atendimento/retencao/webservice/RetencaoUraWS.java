package br.com.indrasistemas.vivoservices.atendimento.retencao.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to.ParametrosWSTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to.RetencaoWSTO;

public interface RetencaoUraWS {

    // public RetencaoWSTO retencaoURA(RequestInfoTO requestInfo, String
    // nrOperacao, String nrTelefone, String nrTipo, String cdOferta);
    public RetencaoWSTO retencaoURA(RequestInfoTO requestInfo, ParametrosWSTO parametros);

}
