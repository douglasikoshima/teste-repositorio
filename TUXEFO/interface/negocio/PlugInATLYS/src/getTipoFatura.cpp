#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "AvailableService.h"

#include <cstring>

using namespace std;


void PlugInATLYS::getTipoFatura()
{
	tuxfw_getlogger()->debug("PlugInATLYS::getTipoFatura()");
	char cSbscrpId[256];
	char*valorTarifa = NULL;
	char*recurrChrgPeriod = NULL;
	char*chrgTypeCd = NULL;

	strcpy(cSbscrpId,getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 

	XMLGen oXmlEnvioGetSubscriptionInfo;
	oXmlEnvioGetSubscriptionInfo.createTag(XML_ATL_INPUT_SUBSCRIPTION_INFO);
	oXmlEnvioGetSubscriptionInfo.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
	oXmlEnvioGetSubscriptionInfo.closeTag();
	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetSubscriptionInfo;
	tuxfw_getlogger()->debug("PlugInATLYS::getTipoFatura():callRemoteAPI()");
	outputDOMNodeGetSubscriptionInfo =  callRemoteAPI(this->getServiceName(), &oXmlEnvioGetSubscriptionInfo, XML_ATL_INPUT_SUBSCRIPTION_INFO );
	
	if(outputDOMNodeGetSubscriptionInfo == NULL){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
		throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
	}

	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
	
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}
	// tratamente de erro
	trataErro(outputDOMNodeGetSubscriptionInfo, XML_ATL_INPUT_SUBSCRIPTION_INFO);

	bool faturaDefault = false;
	bool faturaDetalhada = false;
	bool faturaResumida = false;
	char nomeServico[256];
	DOMNode* auxDOMSvcAsgmInfo = NULL;
	DOMNode* auxDOMPrimarySvc = NULL;
	DOMNode* auxDOMMemberSvc = NULL;
	memset(&nomeServico,0,sizeof(nomeServico));

	char *pcSrvTyp;
	int i =0;
	while((auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{
		if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
		
			// lendo Atributo "svcTyp" da Tag <primarySvc>
			pcSrvTyp = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcTyp");		
			if(pcSrvTyp!=NULL && strcmp(pcSrvTyp, "DB") == 0 ){ // fatura detalhada
				faturaDetalhada = true;
			}else
			if(pcSrvTyp!=NULL && strcmp(pcSrvTyp, "SB") == 0 ){ // fatura resumida
				faturaResumida = true;
			}	
		}
		else{
			throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc>_INEXISTENTE");
		}
		i++;
	}
	
	if(faturaDetalhada)
		strcpy(nomeServico,"CONTA DET MENSAL");
	else
	if(faturaResumida)
		strcpy(nomeServico,"FATURA RESUMIDA");

	// se a fatura for semi-detalhada/default, não existe nome, então não é possível saber valor de tarifa por essa API
	if(faturaDetalhada == true || faturaResumida == true)
	{
		// retornar valor de tarifa
		XMLGen xmlEnvioGetServiceCharge;
		xmlEnvioGetServiceCharge.createTag(XML_ATL_INPUT_SERVICE_CHARGE);
		xmlEnvioGetServiceCharge.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
		xmlEnvioGetServiceCharge.addProp("serviceName", nomeServico);
		xmlEnvioGetServiceCharge.closeTag();

		// Guarda o xml de retorno
		DOMNode* outputDOMNodeGetServiceCharge;
		tuxfw_getlogger()->debug("PlugInATLYS::getServico():callRemoteAPI()");
		outputDOMNodeGetServiceCharge =  callRemoteAPI(this->getServiceName(), &xmlEnvioGetServiceCharge, XML_ATL_INPUT_SERVICE_CHARGE );

		if(outputDOMNodeGetServiceCharge == NULL){
			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
			throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
		}

		// Validar de a Operação  Remota retornou erro.
		if(strcmp(getLastStatusCode(),"24I0000") != 0){

			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
			throw new TuxBasicSvcException("24E0000", getLastStatusText());
		}

		valorTarifa = tuxhp->walkAttr(outputDOMNodeGetServiceCharge,"outputGetServiceCharge","chrgAmt",0);
		recurrChrgPeriod = tuxhp->walkAttr(outputDOMNodeGetServiceCharge,"outputGetServiceCharge","recurrChrgPeriod",0);
		chrgTypeCd = tuxhp->walkAttr(outputDOMNodeGetServiceCharge,"outputGetServiceCharge","chrgTypeCd",0);
	}

	if(faturaDetalhada)
		xml_g->addItem("tipoFatura","02");
	else
	if(faturaResumida)
		xml_g->addItem("tipoFatura","00");
	else
		xml_g->addItem("tipoFatura","01");
	double tarifa =	atof(valorTarifa);
	if(tarifa == 0.00)
	{
		xml_g->addItem("indicadorTarifa","N");
		xml_g->addItem("valorTarifa","0.00");
	}
	else
	{
		xml_g->addItem("indicadorTarifa","S");
		xml_g->addItem("valorTarifa",valorTarifa);
	}
}