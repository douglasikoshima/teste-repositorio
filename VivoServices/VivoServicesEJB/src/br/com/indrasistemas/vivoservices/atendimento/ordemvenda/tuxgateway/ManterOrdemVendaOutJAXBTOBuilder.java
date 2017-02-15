package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.to.PalitagemTO;
import br.com.indrasistemas.vivoservices.atendimento.ordemvenda.tuxgateway.saida.MsgBodyType;

public class ManterOrdemVendaOutJAXBTOBuilder {

	static public PalitagemTO buildREGPALITAGEFOxmlOut(Object body) throws JAXBException {

	    PalitagemTO to = new PalitagemTO();

        if ( body != null && ((MsgBodyType) body).getAtendimentoVO() != null )
        {
            to.setNrProtocolo(((MsgBodyType) body).getAtendimentoVO().getNrProtocolo());
            to.setIdAtendimento(((MsgBodyType) body).getAtendimentoVO().getIdAtendimento());
        }

		return to;
	}
}
