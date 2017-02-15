#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include "CallCircle.h"
#include "AvailableService.h"

#include <cstring>

using namespace std;


void PlugInATLYS::setCaixaPostal()
{
	char* pcTagXmlIn = NULL;
	char cProxyLinha[12];
	int  iCdAreaRegistro = -1;
 	int  iNrLinha = -1;
	char chrOperacao[2]="";
	bool bComunicarUsuario = false;


/* Ler o XML de Entrada vindo da camada JAVA.

<msg>
	<msgHdr>
		<user>2</user>
		<service>TUXPROXYBE</service>
	</msgHdr>
	<msgBody>
		<ProxyLinha>1171010160</ProxyLinha>
		<servico>setCaixaPostal</servico> 
		<operacao>ReiniciaSenha</operacao>
		<operacao>ReiniciaCaixaPostal</operacao>
	
	</msgBody>
</msg>

*/
    pcTagXmlIn = tuxhp->walkTree(dnode,"ProxyLinha", 0);	

	strcpy(cProxyLinha, pcTagXmlIn);

	tuxfw_getlogger()->debug("CPROXULINHA: %s", cProxyLinha); 
    if ((iNrLinha = atoi(pcTagXmlIn + 2)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_NRLINHA, EMSG_ATL_TVI_NRLINHA);
    }
    *(pcTagXmlIn + 2) = '\0';
    if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_CDAREAREGISTRO, EMSG_ATL_TVI_CDAREAREGISTRO);
    }
	

	if( ( (pcTagXmlIn = tuxhp->walkTree(dnode,"operacao", 0)) == NULL) ){
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_INEXISTENTE");
	}
	if(!*pcTagXmlIn){
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_VALOR_VAZIO");
	}
	
	if (!strcmp(pcTagXmlIn,"ReiniciaSenha"))		
		strcpy(chrOperacao,"P");
	else if (!strcmp(pcTagXmlIn,"ReiniciaCaixaPostal")){	
		strcpy(chrOperacao,"S");
		bComunicarUsuario = true;

	}else
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_VALOR_INVALIDO");
	

/*

<msg>
	<msgHdr>
		<service>inputResetVoiceMail</service>
     </msgHdr>
     <msgBody>
		<inputResetVoiceMail accessNbr="1183231479" actionCd="P"/>
     </msgBody>
</msg>
 
*/
     
	XMLGen oXmlInputResetVoiceMail;
	
	oXmlInputResetVoiceMail.createTag(XML_ATL_INPUT_RESET_VOICEMAIL);
	oXmlInputResetVoiceMail.addProp(XML_ATL_ACCT_ACCESSNBR, cProxyLinha);	
	oXmlInputResetVoiceMail.addProp("actionCd", chrOperacao);
	oXmlInputResetVoiceMail.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetSubscriptionInfo;
	
	tuxfw_getlogger()->debug("PlugInATLYS::setReiniciaCaixaPostal():callRemoteAPI()");

	outputDOMNodeGetSubscriptionInfo =  callRemoteAPI(this->getServiceName(), &oXmlInputResetVoiceMail, XML_ATL_INPUT_RESET_VOICEMAIL);
	
	if(outputDOMNodeGetSubscriptionInfo == NULL){
		tuxfw_getlogger()->debug("%s: %s",getLastStatusCode(),getLastStatusText()); 
		throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
	}

	this->trataErro(outputDOMNodeGetSubscriptionInfo, XML_ATL_INPUT_RESET_VOICEMAIL);

	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}

	

	// Registro de contato
	this->registrarContato(xml_g,true);


	if (bComunicarUsuario)
		this->comunicarUsuario(); 

}
