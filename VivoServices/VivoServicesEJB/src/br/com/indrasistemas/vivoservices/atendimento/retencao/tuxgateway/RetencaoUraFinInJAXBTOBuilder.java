package br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.finalret.FinalizaRetencaoVOType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.finalret.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.retencao.tuxgateway.entrada.finalret.ObjectFactory;

public class RetencaoUraFinInJAXBTOBuilder {

    ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildFINALRETENCAOxmlIn(RetencaoTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        FinalizaRetencaoVOType corpoIn = factory.createFinalizaRetencaoVOType();

        corpoIn.setInURA("1");
        corpoIn.setNrOperacao(to.getNrOperacao());
        corpoIn.setNrTelefone(to.getNrTelefone());
        corpoIn.setNrTipo(to.getNrTipo());
        corpoIn.setCdOferta(to.getCdOferta());
        
        corpoInB.setFinalizaRetencaoVO(corpoIn);

        return corpoInB;
    }

}