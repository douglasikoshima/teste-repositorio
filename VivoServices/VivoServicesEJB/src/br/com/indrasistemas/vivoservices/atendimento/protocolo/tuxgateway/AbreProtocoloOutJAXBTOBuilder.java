package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.saida.atdabreprot.ObjectFactory;

public class AbreProtocoloOutJAXBTOBuilder {

    ObjectFactory factory = new ObjectFactory();

    static public ProtocoloTO buildProtocoloTO(Object body) throws JAXBException {
        ProtocoloTO to = new ProtocoloTO();
        MsgBodyType out = (MsgBodyType) body;

        if(out.getAbreProtocoloOutTO()!=null && out.getAbreProtocoloOutTO().getNrProtocolo()!=null && !"".equals(out.getAbreProtocoloOutTO().getNrProtocolo())){
            to.setNrProtocolo(new Long(out.getAbreProtocoloOutTO().getNrProtocolo()));
        }else{
            to.setNrProtocolo(new Long(0));
        }
        to.setErrorCode(out.getAbreProtocoloOutTO().getCdError());
        to.setErrorDescription(out.getAbreProtocoloOutTO().getMsgError());
        
        return to;
    }
}
