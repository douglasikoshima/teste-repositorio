#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "AvailableService.h"

#include <cstring>

using namespace std;


void PlugInATLYS::setTipoFatura()
{
	tuxfw_getlogger()->debug("PlugInATLYS::setTipoFatura()");
	char cSbscrpId[256];
	char *tipo = tuxhp->walkTree(dnode,"tipo", 0);

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

	bool bAchouBasicoNum = false;
	DOMNode* auxDOMBasicSrvList = NULL;
	DOMNode* auxDOMSvcAsgmInfo = NULL;
	DOMNode* auxDOMPrimarySvc = NULL;
	DOMNode* auxDOMMemberSvc = NULL;
	char cBasicSvcId[256];	
	int i=0;
	int j=0;
	memset(&cBasicSvcId,0,sizeof(cBasicSvcId));
	while(!bAchouBasicoNum && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{	
	
  		// Procurando as TAGS <primarySvc>
		if( ((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) !=NULL ) ){

			// Testar se a TAG <primarySvc> possui TAG interna <basicSvcList>
			if( (auxDOMBasicSrvList = tuxhp->walkDOM(auxDOMPrimarySvc,"basicSvcList", 0)) != NULL ){

				// Se basicSvcNm="BASICO NUMÈRO"
				if(strcmp(tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcNm"),"BASICO NUMÈRO") == 0){
					//armazenar seu "basicSvcId"
					strcpy(cBasicSvcId,tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcId"));		
					bAchouBasicoNum = true;  
				};
			}					
		}
		 
		// Procurando as TAGS <memberSvcs>
		j=0;
		while(!bAchouBasicoNum && ((auxDOMMemberSvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"memberSvcs", j)) !=NULL)){
			
			// Testar se <memberSvcs svcNm="BASICO NUMÈRO">
			if(strcmp(tuxhp->getAttrValue(auxDOMMemberSvc,"svcNm"),"BASICO NUMÈRO") == 0){
				// armazenar seu "svcId"
				strcpy(cBasicSvcId,tuxhp->getAttrValue(auxDOMMemberSvc,"svcId"));		
				bAchouBasicoNum = true;  
			}
            else//Testar se <memberSvcs> possui TAG interna <basicSvcList>
				if((auxDOMBasicSrvList = tuxhp->walkDOM(auxDOMMemberSvc,"basicSvcList", 0)) != NULL ){

					// Se basicSvcNm="BASICO NUMÈRO"
					if(strcmp(tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcNm"),"BASICO NUMÈRO") == 0){
						//armazenar seu "basicSvcId"
						strcpy(cBasicSvcId,tuxhp->getAttrValue(auxDOMBasicSrvList,"basicSvcId"));		
						bAchouBasicoNum = true;  
					};					
			}
			j++;	
		}
		i++;
	}

	if(!bAchouBasicoNum){
		throw new TuxBasicSvcException("24E0000", "Operacao nao Realizada. Valor (BASICO NUMÈRO) nao encontrado nas tags <primarySvc:basicSvcList> ou <memberSvcs> ou <memberSvcs:basicSvcList> ");
	}


	bool faturaDefault = false;
	bool faturaDetalhada = false;
	bool faturaResumida = false;
	char nomeServico[256];
	char serviceType[256];
	memset(&nomeServico,0,sizeof(nomeServico));
	memset(&serviceType,0,sizeof(serviceType));

	char *pcSrvTyp;
	i = 0;
	while((auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{
		if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
		
			// lendo Atributo "svcTyp" da Tag <primarySvc>
			pcSrvTyp = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcTyp");		
			if(pcSrvTyp!=NULL && strcmp(pcSrvTyp, "DB") == 0 ){ // fatura detalhada
				faturaDetalhada = true;
				// se o comando for ativar fatura resumida ou semi-detalhada, desativar qualquer fatura detalhada
				if(!strcmp(tipo,"00") || !strcmp(tipo,"01"))
				{
					// montar XML para atualizar serviço removendo
					this->updateService("CONTA DET MENSAL",cSbscrpId,auxDOMSvcAsgmInfo,auxDOMPrimarySvc,pcSrvTyp);
				}
			}
			else
			if(pcSrvTyp!=NULL && strcmp(pcSrvTyp, "SB") == 0 ){ // fatura resumida
				faturaResumida = true;
				// se o comando for ativar fatura detalhada ou semi-detalhada, desativar qualquer fatura resumida
				if(!strcmp(tipo,"02") || !strcmp(tipo,"01"))
				{
					// montar XML para atualizar serviço removendo
					this->updateService("FATURA RESUMIDA",cSbscrpId,auxDOMSvcAsgmInfo,auxDOMPrimarySvc,pcSrvTyp);
				}
			}	
		}
		else{
			throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc>_INEXISTENTE");
		}
		i++;
	}

	// verificar se serviço já existe antes de adicionar
	if((!strcmp(tipo,"00") && faturaResumida) || (!strcmp(tipo,"02") && faturaDetalhada) ||
		(faturaResumida == false && faturaDetalhada == false && !strcmp(tipo,"01")  ))	
		throw new TuxBasicSvcException("24E8096", "Serviço já existente");

	// adicionar o serviço se for fatura resumida ou detalhada
	if(!strcmp(tipo,"00") || !strcmp(tipo,"02"))
	{
		if(!strcmp(tipo,"00"))
		{
			sprintf(nomeServico,"FATURA RESUMIDA");
			sprintf(serviceType,"SB");
		}
		else
		if(!strcmp(tipo,"02"))
		{
			sprintf(nomeServico,"CONTA DET MENSAL");
			sprintf(serviceType,"DB");
		}		

		XMLGen oXmlEnvioMainSubsServices;
		oXmlEnvioMainSubsServices.createTag("inputMaintainSubscriptionServices");
		oXmlEnvioMainSubsServices.addProp("sbscrpId", cSbscrpId);

			// TAG addSvcLst
			oXmlEnvioMainSubsServices.createTag("addSvcLst");				

				// TAG salesChnl
				oXmlEnvioMainSubsServices.createTag("salesChnl");
					oXmlEnvioMainSubsServices.addProp("salesChnlId", "00000");
				oXmlEnvioMainSubsServices.closeTag(); // <salesChnl>

				// TAG primarySvc
				oXmlEnvioMainSubsServices.createTag("primarySvc");
					oXmlEnvioMainSubsServices.addProp("svcNm", nomeServico);
					oXmlEnvioMainSubsServices.addProp("svcTyp", serviceType );
			
						// TAG basicSvcNm
						oXmlEnvioMainSubsServices.createTag("basicSvcList");
							oXmlEnvioMainSubsServices.addProp("basicSvcNm", "BASICO NUMÈRO");		
							oXmlEnvioMainSubsServices.addProp("basicSvcId", cBasicSvcId );
						oXmlEnvioMainSubsServices.closeTag(); // <basicSvcList>

				oXmlEnvioMainSubsServices.closeTag(); // <primarySvc>

			oXmlEnvioMainSubsServices.closeTag(); // <addSvcLst>

		oXmlEnvioMainSubsServices.closeTag();	// <inputMaintainSubscriptionServices>		

		// enviar XML
		DOMNode* outputDOMNodeMainSubsServices;
		outputDOMNodeMainSubsServices =  callRemoteAPI(this->getServiceName(), &oXmlEnvioMainSubsServices, "inputMaintainSubscriptionServices" );
		this->trataErro(outputDOMNodeMainSubsServices);
		// Validar de a Operação  Remota retornou erro.
		if(strcmp(getLastStatusCode(),"24I0000") != 0){
			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
			throw new TuxBasicSvcException("24E0000", getLastStatusText());
		}
	}
	this->registrarContato();
}

void  PlugInATLYS::updateService(char*nomeServico,char*cSbscrpId,
	DOMNode*auxDOMSvcAsgmInfo,DOMNode*auxDOMPrimarySvc,char*pcSrvTyp)
{
	// montar o xml
	char cDate[30];
	char*pcTagXmlIn = NULL;
	XMLGen oXmlEnvioMainSubsServices;
	oXmlEnvioMainSubsServices.createTag("inputMaintainSubscriptionServices");
	oXmlEnvioMainSubsServices.addProp("sbscrpId", cSbscrpId);

		// TAG updateSvcLst
		oXmlEnvioMainSubsServices.createTag("updateSvcLst");				
			// ler Atributo "svcEffDt" da TAG <svcAsgmInfo>
			pcTagXmlIn = tuxhp->getAttrValue(auxDOMSvcAsgmInfo,"svcEffDt");		
			oXmlEnvioMainSubsServices.addProp("svcEffDt", pcTagXmlIn); 
			oXmlEnvioMainSubsServices.addProp("svcExprDt", CAvailableService::getSysDateBD(cDate)); 

			// TAG salesChnl
			oXmlEnvioMainSubsServices.createTag("salesChnl");
				oXmlEnvioMainSubsServices.addProp("salesChnlId", "00000");
			oXmlEnvioMainSubsServices.closeTag(); // <salesChnl>

			// TAG primarySvc
			oXmlEnvioMainSubsServices.createTag("primarySvc");
				oXmlEnvioMainSubsServices.addProp("svcNm", nomeServico);
				oXmlEnvioMainSubsServices.addProp("svcTyp", pcSrvTyp );
				// ler Atributo "svcId" da TAG <primarySvc>
				pcTagXmlIn = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcId");		
				oXmlEnvioMainSubsServices.addProp("svcId", pcTagXmlIn );

			oXmlEnvioMainSubsServices.closeTag(); // <primarySvc>

		oXmlEnvioMainSubsServices.closeTag(); // <updateSvcLst>

	oXmlEnvioMainSubsServices.closeTag();	// <inputMaintainSubscriptionServices>		
	
	DOMNode* outputDOMNodeMainSubsServices;
	
	outputDOMNodeMainSubsServices =  callRemoteAPI(this->getServiceName(), &oXmlEnvioMainSubsServices, "inputMaintainSubscriptionServices" );
	
	this->trataErro(outputDOMNodeMainSubsServices);

	// Validar de a Operação  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}
}