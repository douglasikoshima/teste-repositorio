package br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.autenticacao.to.DadosAlteraSenhaTO;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.entrada.AlteraSenhaVOType;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.entrada.MsgBodyType;
import br.com.indrasistemas.vivoservices.autenticacao.tuxgateway.alterarSenha.entrada.ObjectFactory;

public class AlterarSenhaInJAXBTOBuilder {

	ObjectFactory factory = new ObjectFactory();
	
	static public MsgBodyType buildXmlIn (DadosAlteraSenhaTO to) throws JAXBException {

        ObjectFactory factory = new ObjectFactory();
        MsgBodyType corpoInB = factory.createMsgBodyType();
        AlteraSenhaVOType corpoIn = factory.createAlteraSenhaVOType();
        
        
        corpoIn.setIdCanal("13");
        corpoIn.setIdTerminal("0");
        corpoIn.setCdSenha(to.getSenha());
        corpoIn.setTelefone(to.getNrTelefone());
        corpoIn.setAlteraSenhaService("1");


        corpoInB.setAlteraSenhaVO(corpoIn);
        
        return corpoInB;		
		
	}

	
}
