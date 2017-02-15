package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.autenticacao.to.DadosValidaSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ObjectFactory;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.entrada.ValidaSenhaVOType;

public class ValidarSenhaInJAXBTOBuilder {
	
    ObjectFactory factory = new ObjectFactory();

    static public MsgBodyType buildXmlIn (DadosValidaSenhaTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        ValidaSenhaVOType corpoIn = factory.createValidaSenhaVOType();
        
        corpoIn.setCdDDD(to.getCdDDD().intValue());
        corpoIn.setCdNumTelefone(to.getCdNumTelefone().intValue());
        corpoIn.setCdSenha(to.getSenha());        
        //String telefone = to.getCdDDD().toString() + to.getCdNumTelefone().toString();        
        //corpoIn.setTelefone(Integer.parseInt(telefone));
        
        corpoIn.setIdCanal("13");
        corpoIn.setIdTerminal("0");
        corpoIn.setValidarSenhaService("1");

        corpoInB.setValidaSenhaVO(corpoIn);
        
        return corpoInB;
    }
	
	
	
}
