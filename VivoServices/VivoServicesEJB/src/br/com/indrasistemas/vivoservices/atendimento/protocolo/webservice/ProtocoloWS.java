package br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.to.ProtocoloParamWSTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.to.ProtocoloWSTO;

public interface ProtocoloWS {

    public ProtocoloWSTO aberturaProtocolo(RequestInfoTO requestInfo, ProtocoloParamWSTO protocoloParam);

    public ProtocoloWSTO fechamentoProtocolo(RequestInfoTO requestInfo, Long nrProtocolo, Long idSistema);

}
