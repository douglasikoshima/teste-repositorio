package br.com.vivo.catalogoPRS.ws.catalogoGeral.sn;

import org.apache.axis.message.MessageElement;
import org.apache.axis.message.PrefixedQName;
import org.apache.axis.message.SOAPHeaderElement;

public class CabecalhoVivoHeader implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	public CabecalhoVivoHeader() {
	  }		

	public SOAPHeaderElement getCabecalhoVivoHeaderPlano() {

		SOAPHeaderElement cabecalhoVivo = null;
		try {
			cabecalhoVivo = new MySoapHeaderElement(new PrefixedQName("http://www.vivo.com.br/MC/Geral", "cabecalhoVivo", "ger"));
			cabecalhoVivo.setActor(null);

			// Login Usuario
			MessageElement loginUsuario = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>loginUsuario"));
			loginUsuario.setObjectValue("VivoCatalogo");

			// Canal de Atendimento
			MessageElement canalAtendimento = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>canalAtendimento"));
			canalAtendimento.setObjectValue("1");

			// Codigo Sessao
			MessageElement codigoSessao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>codigoSessao"));
			codigoSessao.setObjectValue("1");

			// Nome Aplicacao
			MessageElement nomeAplicacao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>nomeAplicacao"));
			nomeAplicacao.setObjectValue("VivoCatalogo");

			// Token
			MessageElement token = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>token"));
			token.setObjectValue("1");

			// Endereco IP
			MessageElement enderecoIP = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>enderecoIP"));
			enderecoIP.setObjectValue("10.129.175.241");

			// Codigo Funcionalidade
			MessageElement codigoFuncionalidade = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>codigoFuncionalidade"));
			codigoFuncionalidade.setObjectValue("22");

			// Data Transacao
			MessageElement dataTransacao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>dataTransacao"));
			dataTransacao.setObjectValue("2011-02-09T15:00:00");

			// Nome Servico
			MessageElement nomeServico = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>nomeServico"));
			nomeServico.setObjectValue("CatalogoPlano");
			
			// Versao
			//MessageElement versao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>versao"));
			//versao.setObjectValue("1");

			cabecalhoVivo.addChild(loginUsuario);
			cabecalhoVivo.addChild(canalAtendimento);
			cabecalhoVivo.addChild(codigoSessao);
			cabecalhoVivo.addChild(nomeAplicacao);
			cabecalhoVivo.addChild(token);
			cabecalhoVivo.addChild(enderecoIP);
			cabecalhoVivo.addChild(codigoFuncionalidade);
			cabecalhoVivo.addChild(dataTransacao);
			cabecalhoVivo.addChild(nomeServico);
			//cabecalhoVivo.addChild(versao);
		} catch (Exception e) {

		}

		return cabecalhoVivo;
	}	
	
	public SOAPHeaderElement getCabecalhoVivoHeader() {

		SOAPHeaderElement cabecalhoVivo = null;
		try {
			cabecalhoVivo = new MySoapHeaderElement(new PrefixedQName("http://www.vivo.com.br/MC/Geral", "cabecalhoVivo", "ger"));
			cabecalhoVivo.setActor(null);

			// Login Usuario
			MessageElement loginUsuario = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>loginUsuario"));
			loginUsuario.setObjectValue("VivoCatalogo");

			// Canal de Atendimento
			MessageElement canalAtendimento = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>canalAtendimento"));
			canalAtendimento.setObjectValue("1");

			// Codigo Sessao
			MessageElement codigoSessao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>codigoSessao"));
			codigoSessao.setObjectValue("1");

			// Nome Aplicacao
			MessageElement nomeAplicacao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>nomeAplicacao"));
			nomeAplicacao.setObjectValue("VivoCatalogo");

			// Token
			MessageElement token = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>token"));
			token.setObjectValue("1");

			// Endereco IP
			MessageElement enderecoIP = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>enderecoIP"));
			enderecoIP.setObjectValue("10.129.175.241");

			// Codigo Funcionalidade
			/*MessageElement codigoFuncionalidade = new MessageElement(
					new javax.xml.namespace.QName(
							"http://www.vivo.com.br/MC/Geral",
							">CabecalhoVivo>codigoFuncionalidade"));
			codigoFuncionalidade.setObjectValue("22");*/

			// Data Transacao
			MessageElement dataTransacao = new MessageElement(new javax.xml.namespace.QName("http://www.vivo.com.br/MC/Geral",">CabecalhoVivo>dataTransacao"));
			dataTransacao.setObjectValue("2011-02-09T15:00:00");

			// Nome Servico
			/*MessageElement nomeServico = new MessageElement(
					new javax.xml.namespace.QName(
							"http://www.vivo.com.br/MC/Geral",
							">CabecalhoVivo>nomeServico"));
			nomeServico.setObjectValue("FO");*/

			cabecalhoVivo.addChild(loginUsuario);
			cabecalhoVivo.addChild(canalAtendimento);
			cabecalhoVivo.addChild(codigoSessao);
			cabecalhoVivo.addChild(nomeAplicacao);
			cabecalhoVivo.addChild(token);
			cabecalhoVivo.addChild(enderecoIP);
			//cabecalhoVivo.addChild(codigoFuncionalidade);
			cabecalhoVivo.addChild(dataTransacao);
			//cabecalhoVivo.addChild(nomeServico);
		} catch (Exception e) {

		}

		return cabecalhoVivo;
	}	

}
