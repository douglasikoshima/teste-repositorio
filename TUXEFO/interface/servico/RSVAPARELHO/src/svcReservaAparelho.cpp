#include "ReservaAparelho.hpp"
#include <tuxfw.h>

DECLARE_TUXEDO_SERVICE(RSVAPARELHO);

void implRSVAPARELHO::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	ReservaAparelho reservaAparelho;
	
	char* retornoReservaSAP = NULL;
	XMLGen xmlIn;
	
/*
<![CDATA[
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ger="http://www.vivo.com.br/MC/Geral" xmlns:mat="http://www.vivo.com.br/SN/Material">
   <soapenv:Header>
      <wsse:Security xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
         <wsse:UsernameToken xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd">
            <wsse:Username>vivo360guides</wsse:Username>
            <wsse:Password Type="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText">vivo360guides</wsse:Password>
         </wsse:UsernameToken>
      </wsse:Security>
      <ger:cabecalhoVivo>
         <ger:loginUsuario>VivoVivonet</ger:loginUsuario>
         <ger:canalAtendimento>1</ger:canalAtendimento>
         <ger:codigoSessao>1</ger:codigoSessao>
         <ger:nomeAplicacao>VivoVivonet</ger:nomeAplicacao>
         <ger:codigoFuncionalidade xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
      </ger:cabecalhoVivo>
   </soapenv:Header>
   <soapenv:Body>
      <mat:expirarReservaAparelhoEmLoteRequest>
         <mat:listaReservas>
            <!--Zero or more repetitions:-->
            <mat:itemReserva>
               <mat:codigoReservaSap>1</mat:codigoReservaSap>
            </mat:itemReserva>
         </mat:listaReservas>
      </mat:expirarReservaAparelhoEmLoteRequest>
   </soapenv:Body>
</soapenv:Envelope>
]]>
*/	
	char url[256];
	char username[256];
	char password[256];
	
	memset(url,0,sizeof(url));
	memset(username,0,sizeof(username));
	memset(password,0,sizeof(password));
	
	reservaAparelho.consultarParametro("URL_CANCELAR_RESERVA_APARELHO",url);
	reservaAparelho.consultarParametro("USUARIO_CANCELAR_RESERVA_APARELHO",username);
	reservaAparelho.consultarParametro("SENHA_CANCELAR_RESERVA_APARELHO",password);
	
	xmlIn.createTag("xmlContent");
	xmlIn.addItem("url",url);
	xmlIn.createTag("conteudo");
	xmlIn.createTag("soapenv:Envelope");
	xmlIn.addProp("xmlns:soapenv","http://schemas.xmlsoap.org/soap/envelope/");
	xmlIn.addProp("xmlns:ger","http://www.vivo.com.br/MC/Geral");
	xmlIn.addProp("xmlns:mat","http://www.vivo.com.br/SN/Material");
	xmlIn.createTag("soapenv:Header");
	xmlIn.createTag("wsse:Security");
	xmlIn.addProp("xmlns:wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
	xmlIn.createTag("wsse:UsernameToken");
	xmlIn.addProp("xmlns:wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
	xmlIn.addItem("wsse:Username",username);
	xmlIn.addItem("wsse:Password",password);	
	xmlIn.closeTag(); // </wsse:UsernameToken>
	xmlIn.closeTag(); // </wsse:Security>
	xmlIn.createTag("ger:cabecalhoVivo");
	xmlIn.addItem("ger:loginUsuario","VivoVivonet");
	xmlIn.addItem("ger:canalAtendimento","1");
	xmlIn.addItem("ger:codigoSessao","1");
	xmlIn.addItem("ger:nomeAplicacao","VivoVivonet");
	xmlIn.createTag("ger:codigoFuncionalidade");
	xmlIn.addProp("xsi:nil","true");
	xmlIn.addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	xmlIn.closeTag(); // </ger:codigoFuncionalidade>
	xmlIn.closeTag(); // </ger:cabecalhoVivo>	
	xmlIn.closeTag(); // </soapenv:Header>
	
	xmlIn.createTag("soapenv:Body");	
	xmlIn.createTag("mat:expirarReservaAparelhoEmLoteRequest");
	
	xmlIn.createTag("mat:listaReservas");
	for ( int i = 0; (retornoReservaSAP = walkTree(dnode, "retornoReservaSAP", i)) != NULL; i++ ) {
		xmlIn.createTag("mat:itemReserva");
		xmlIn.addItem("mat:codigoReservaSap" ,retornoReservaSAP);
		xmlIn.closeTag(); // </mat:itemReserva>
	}
	xmlIn.closeTag();	// </mat:listaReservas>
	
	xmlIn.closeTag(); // </mat:expirarReservaAparelhoEmLoteRequest>	
	xmlIn.closeTag(); // </soapenv:Body>	
	xmlIn.closeTag(); // </soapenv:Envelope>
	xmlIn.closeTag(); // </conteudo>
	xmlIn.closeTag(); // </xmlContent>

	try {
		DOMNode* xml = reservaAparelho.enviarXML(&xmlIn);
		char*faultstring = this->walkTree(xml,"faultstring",0);
		
		if (faultstring == NULL) {
			tuxfw_getlogger()->debug("sucesso na chamada do serviço de integração");
			setStatusCode("00I0000","sucesso");			
			for ( int i = 0; (retornoReservaSAP = walkTree(dnode, "retornoReservaSAP", i)) != NULL; i++ ) {
				reservaAparelho.cancelarReservaAparelho(retornoReservaSAP);
			}				
		} else {
			setStatusCode("00W0000",faultstring);	
		}
	} catch(TuxBasicSvcException tse) {
		setStatusCode("00W0001","erro na chamada da integração");	
	}
	
	XMLString::release(&retornoReservaSAP);

}