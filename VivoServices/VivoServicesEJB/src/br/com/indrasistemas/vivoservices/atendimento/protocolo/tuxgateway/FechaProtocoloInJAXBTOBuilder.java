package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdfechaprot.FechaProtocoloTOType;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdfechaprot.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdfechaprot.ObjectFactory;

public class FechaProtocoloInJAXBTOBuilder {

    ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildATDFECHAPROTxmlIn(ProtocoloTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        FechaProtocoloTOType corpoIn = factory.createFechaProtocoloTOType();

        corpoIn.setIdSistemaOrigem(String.valueOf(to.getIdSistema()));
        corpoIn.setNrProtocolo(String.valueOf(to.getNrProtocolo()));
        
        corpoInB.setFechaProtocoloTO(corpoIn);

        return corpoInB;
    }
}
