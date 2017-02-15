package br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway;

import javax.xml.bind.JAXBException;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.to.ProtocoloTO;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdabreprot.AbreProtocoloTOType;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdabreprot.MsgBodyType;
import br.com.indrasistemas.vivoservices.atendimento.protocolo.tuxgateway.entrada.atdabreprot.ObjectFactory;

public class AbreProtocoloInJAXBTOBuilder {

    ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildATDABREPROTxmlIn(ProtocoloTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        
        MsgBodyType corpoInB = factory.createMsgBodyType();
        AbreProtocoloTOType corpoIn = factory.createAbreProtocoloTOType();

        if(to.getNrLinha()!=null){
            corpoIn.setCdAreaRegistro(String.valueOf(to.getNrLinha()).substring(0, 2));
            corpoIn.setNrTelefone(String.valueOf(to.getNrLinha()).substring(2));
        }else{
            corpoIn.setCdAreaRegistro("");
            corpoIn.setNrTelefone("");
        }
        if(to.getNrLinhaSMS()!=null){
            corpoIn.setCdAreaRegistroSMS(String.valueOf(to.getNrLinhaSMS()).substring(0, 2));
            corpoIn.setNrTelefoneSMS(String.valueOf(to.getNrLinhaSMS()).substring(2));
        }else{
            corpoIn.setCdAreaRegistroSMS("");
            corpoIn.setNrTelefoneSMS("");
        }
        if(to.getCdConta()!=null){
            corpoIn.setCdConta(to.getCdConta());
        }else{
            corpoIn.setCdConta("");
        }
        corpoIn.setIdSistemaOrigem(String.valueOf(to.getIdSistema()));
        corpoIn.setIdTipoAberturaProtocolo(String.valueOf(to.getTpAbertura()));
        
        corpoInB.setAbreProtocoloTO(corpoIn);

        return corpoInB;
    }
}
