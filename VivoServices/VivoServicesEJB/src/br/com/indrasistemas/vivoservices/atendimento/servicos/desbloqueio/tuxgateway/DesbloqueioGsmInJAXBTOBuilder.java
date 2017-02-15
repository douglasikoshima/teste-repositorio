package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to.DesbloqueioGsmTO;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway.entrada.DesbloqueioGsmVOType;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.tuxgateway.entrada.ObjectFactory;

public class DesbloqueioGsmInJAXBTOBuilder {

    ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildGETSIMLOCKxmlIn(DesbloqueioGsmTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        DesbloqueioGsmVOType corpoIn = factory.createDesbloqueioGsmVOType();

        corpoIn.setNrIMEI(to.getNrIMEI());
        corpoIn.setNrIP(to.getNrIP());
        corpoIn.setIdTipoRelacionamento(to.getIdTipoRelacionamento());
        corpoIn.setIdGrupoAbertura(to.getIdGrupoAbertura());
        corpoIn.setCdAreaRegistro(to.getCdAreaRegistro());
        corpoIn.setNrLinha(to.getNrLinha());
        corpoIn.setIdPessoa(to.getIdPessoa());

        corpoInB.setDesbloqueioGsmVO(corpoIn);
        return corpoInB;
    }

}