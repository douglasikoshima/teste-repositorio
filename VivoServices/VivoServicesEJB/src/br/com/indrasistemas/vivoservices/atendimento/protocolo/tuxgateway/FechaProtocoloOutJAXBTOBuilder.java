package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdfechaprot.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdfechaprot.ObjectFactory;

public class FechaProtocoloOutJAXBTOBuilder {

    ObjectFactory factory = new ObjectFactory();

    static public ProtocoloTO buildProtocoloTO(Object body) throws JAXBException {
        ProtocoloTO to = new ProtocoloTO();
        MsgBodyType out = (MsgBodyType) body;

        to.setErrorCode(out.getFechaProtocoloOutTO().getCdError());
        to.setErrorDescription(out.getFechaProtocoloOutTO().getMsgError());

        return to;
    }
}
