package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.BonusPesquisaVOType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.ObjectFactory;

public class RetencaoUraGetInJAXBTOBuilder {

    br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.getbonus.ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildGETBONUSxmlIn(RetencaoTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        
        MsgBodyType corpoInB = factory.createMsgBodyType();
        
        BonusPesquisaVOType corpoIn = factory.createBonusPesquisaVOType();

        corpoIn.setInURA("1");
        corpoIn.setNrOperacao(to.getNrOperacao());
        corpoIn.setNrTelefone(to.getNrTelefone());
        corpoIn.setNrTipo(to.getNrTipo());
        
        corpoInB.setBonusPesquisaVO(corpoIn);

        return corpoInB;
    }

}