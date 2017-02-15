#include "PlugInATLYS.h"

#include <tuxfw.h>

#include "CallCircle.h"
#include "AvailableService.h"

#include <cstring>

using namespace std;


void PlugInATLYS::getFavoritos()
{


   char* pcTagXmlIn = NULL;
	int   iCdAreaRegistro = -1;
 	int   iNrLinha = -1;
 
   // Dados de Entrada da API
   char  cSbscrpId[256];
   char  cDate[30];

/* Ler o XML de Entrada vindo da camada JAVA.

<msg>
	<msgHdr>
		<user>2</user>
		<service>TUXPROXYBE</service>
	</msgHdr>
	<msgBody>
		<ProxyLinha>1171010160</ProxyLinha>
	</msgBody>
</msg>

*/

    // Obtem o SbscrpId
   	strcpy(cSbscrpId,getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 


    // Ler Tag <ProxyLinha> que j� foi validade pelo Proxy.
    pcTagXmlIn = tuxhp->walkTree(dnode,"ProxyLinha", 0);

    if ((iNrLinha = atoi(pcTagXmlIn + 2)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_NRLINHA, EMSG_ATL_TVI_NRLINHA);
    }

    *(pcTagXmlIn + 2) = '\0';

    if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
            throw new TuxBasicSvcException(ECD_ATL_TVI_CDAREAREGISTRO, EMSG_ATL_TVI_CDAREAREGISTRO);
    }

// Passa 2 - Acessar GetSubscriptionInfo

/*

<msg>
	<msgHdr>
		<user>2</user>
		<service>inputGetSubscriptionInfo</service>
	</msgHdr>
	<msgBody>
		<inputGetSubscriptionInfo sbscrpId="0035132738" />
	</msgBody>                    
</msg>

*/

	XMLGen oXmlEnvioGetSubscriptionInfo;
	
	oXmlEnvioGetSubscriptionInfo.createTag(XML_ATL_INPUT_SUBSCRIPTION_INFO);
	oXmlEnvioGetSubscriptionInfo.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
	oXmlEnvioGetSubscriptionInfo.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetSubscriptionInfo;

	int i;// indice para loop do parsing
	bool bAchouFAVORITOS  = false;
	DOMNode* auxDOMSvcAsgmInfo;
	DOMNode* auxDOMPrimarySvc;
	DOMNode* auxDOMCallCircle;

	// Chave de Retorno para a lista de CallCircle
	char cSeqNbr[15] = "";
	char cListTypeInd[3] = "";
	char cMethodInd[3] = "";

	// Recupera o Nome do Servico 
	char cNmSrvATLYS[30];
	strcpy(cNmSrvATLYS, this->traduzNomeServicoToATLYS("FAVORITOS", iCdAreaRegistro));
	
	//Contador do Nro de Tentativas em Chamar o setServico do ATLYS. 
	int iNroTentativasSetServico = 0;
    
	//Loop para Fazer as tentativas para Chamar setServico()
	do{
		outputDOMNodeGetSubscriptionInfo =  callRemoteAPI(this->getServiceName(), &oXmlEnvioGetSubscriptionInfo, XML_ATL_INPUT_SUBSCRIPTION_INFO );
		
		if(outputDOMNodeGetSubscriptionInfo == NULL){
			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
			throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
		}

		// Validar de a Opera��o  Remota retornou erro.
		if(strcmp(getLastStatusCode(),"24I0000") != 0){
		
			tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
			throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
		}

		// Faz o Parsing do PrimarySvc:srcNm="FAVORITOS" no XML de Retorno da API GetSubscriptionInfo
		// OBS: Se alguns dos valores de Retorno n�o forem encontrados no XML, ser� lancado uma Exec�ao.
		i=0; 
		while(!bAchouFAVORITOS && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
		{
			if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
			
				// lendo Atributo "svcNm" da Tag <primarySvc>
				pcTagXmlIn  = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcNm");	
				
				// Se Encontrou o "FAVORITOS"
				if(strcmp(pcTagXmlIn, cNmSrvATLYS) == 0){	

					// Se tem a TAG <callCircle>
					if((auxDOMCallCircle = tuxhp->walkDOM(auxDOMPrimarySvc,"callCircle", 0)) != NULL ){
					
						// lendo Atributo "seqNbr" da Tag <callCircle>
						pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"seqNbr");	
						strcpy(cSeqNbr,pcTagXmlIn);

						// lendo Atributo "listTypeInd" da Tag <callCircle>
						pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"listTypeInd");	
						strcpy(cListTypeInd,pcTagXmlIn);

						// lendo Atributo "methodInd" da Tag <callCircle>
						pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"methodInd");	
						strcpy(cMethodInd,pcTagXmlIn);

						bAchouFAVORITOS = true;
					}
					else{// Erro de consistencia do XML
						tuxfw_getlogger()->debug("TAG_<primarySvc:callCircle>_INEXISTENTE"); 
						throw new TuxBasicSvcException(ECD_ATL_TNE_PRIMARYSVC_CALLCIRCLE, EMSG_ATL_TNE_PRIMARYSVC_CALLCIRCLE);
					}	
				}
			}
			else{// Erro de consistencia do XML
				tuxfw_getlogger()->debug("TAG_<primarySvc>_INEXISTENTE"); 
				throw new TuxBasicSvcException(ECD_ATL_TNE_PRIMARYSVC, EMSG_ATL_TNE_PRIMARYSVC);
			}
			i++;
		}// fim do while do Parsing.

		//Se n�o Achou o servico FAVORITOS
		if(!bAchouFAVORITOS){

			//Se n�o Achou o servico FAVORITOS, chamar setServico(),em apenas 1 unica tentativa,
			// para Ativar o FAVORITOS. O que far� com que a TAG <primarySvc svcNm="FAVORITOS" apareca no XML da API GetSubscriptoinInfo

			//Se n�o foi feita nenhuma Tentativa, faz agora.
			if(iNroTentativasSetServico == 0){
				// Ser� permitido apenas 1 Tentativa)
				
				// Incrementa o contador de Tentativas de Chamadas ao setServico "FAVORITOS"
				iNroTentativasSetServico++;

				// Chama o metodo setServico() de uma instancia Local do PlugInATLYS, para preservar o
				// contextro da chamada original que no caso � setFavotio().
				char* pcXmlInSetServico ="\
							<msg>\
									<msgHdr>\
											<user>2</user>\
											<service>TuxProxyBE</service>\
									</msgHdr>\
									<msgBody>\
											<ProxyLinha>%d%d</ProxyLinha>\
											<operacao>1</operacao>\
											<servico>FAVORITOS</servico>\
									</msgBody>\
							</msg>";

				/*
				// Preparar instancias de DOMNode e o XMLGen locais, 
				// para a instancia Local do PlugInATLYS. 
				DOMNode* dnodeIn = createDOMNode(cXmlInSetServico);
				XMLGen*  xmlRet  = new XMLGen();

				// Instancia Local do PlugInATLYS, para chamar setServico().
				IBackEnd* pPlug = new PlugInATLYS(dnodeIn , xmlRet, getIdLinhaSistemaOrigem());

				try{
					// Chamada do Metodo.
					pPlug->setServico();
				}
				catch(...){
					tuxfw_getlogger()->debug("catch na Chamada Local pPlug->setServico()"); 
					// destruir os objs criados neste escopo					
					tuxfw_getlogger()->debug("delete pPlug"); 
					delete pPlug;  // PlugInATLYS  
					delete xmlRet; // XMLGen					
					throw;
				}

				// Obter o Retorno
				TuxMessage* outXml = new TuxMessage();
				outXml->setMessageBody(xmlRet);

				DOMNode* dnodeOut = createDOMNode(outXml->getMessageBody());

				if((tuxhp->walkDOM(dnodeOut,"ServicoVO", 0)) == NULL){
					
					tuxfw_getlogger()->debug("Erro em setServico(), Nao Retornou TAG <ServicoVO>"); 

					// destruir os objs criados neste escopo
					delete outXml; // TuxMessage
					delete pPlug;  // PlugInATLYS  
					delete xmlRet; // XMLGen

					throw new TuxBasicSvcException("24E0000", "Nao foi possivel ativar FAVORITOS");
				}  	
				
			    // destruir os objs criados neste escopo
				delete outXml; // TuxMessage
				delete pPlug;  // PlugInATLYS  
				delete xmlRet; // XMLGen
				*/

				char cXmlInSetServico[strlen(pcXmlInSetServico) + 11];

				sprintf(cXmlInSetServico, pcXmlInSetServico, iCdAreaRegistro, iNrLinha);

				callSetServico(cXmlInSetServico);

			}
			else{				
				tuxfw_getlogger()->debug("Nao Encontrado o PrimarySvc:svcNm=%s",cNmSrvATLYS); 
				tuxfw_getlogger()->debug("Mesmo apos %d Chamada de setServico().", iNroTentativasSetServico); 									
				throw new TuxBasicSvcException(ECD_ATL_TNE_PRIMARYSVC_SVCNM, EMSG_ATL_TNE_PRIMARYSVC_SVCNM);
			}

		}
	}
	while(!bAchouFAVORITOS);
/*
	outputDOMNodeGetSubscriptionInfo =  callRemoteAPI(SERVICE_ATLYS, &oXmlEnvioGetSubscriptionInfo, XML_ATL_INPUT_SUBSCRIPTION_INFO );
	
	if(outputDOMNodeGetSubscriptionInfo == NULL){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
		throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
	}

	// Validar de a Opera��o  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
	
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException("24E0000", getLastStatusText());
	}

	// Faz o Parsing do PrimarySvc:srcNm="FAVORITOS" no XML de Retorno da API GetSubscriptionInfo
	// OBS: Se alguns dos valores de Retorno n�o forem encontrados no XML, ser� lancado uma Exec�ao.

	bool bAchouCallCircle = false;
	DOMNode* auxDOMSvcAsgmInfo;
	DOMNode* auxDOMPrimarySvc;
	DOMNode* auxDOMCallCircle;

    // Chave de Retorno para a lista de CallCircle
	char cSeqNbr[15] = "";
	char cListTypeInd[3] = "";
	char cMethodInd[3] = "";

	// Recupera o Nome do Servico 
	char cNmSrvATLYS[30];
	strcpy(cNmSrvATLYS, this->traduzNomeServicoToATLYS("FAVORITOS", iCdAreaRegistro));
	
	int i=0; 
	while(!bAchouCallCircle && (auxDOMSvcAsgmInfo = tuxhp->walkDOM(outputDOMNodeGetSubscriptionInfo,"svcAsgmInfo", i)) != NULL )
	{
		if((auxDOMPrimarySvc = tuxhp->walkDOM(auxDOMSvcAsgmInfo,"primarySvc", 0)) != NULL ){
		
			// lendo Atributo "svcNm" da Tag <primarySvc>
			pcTagXmlIn  = tuxhp->getAttrValue(auxDOMPrimarySvc,"svcNm");	
			if(strcmp(pcTagXmlIn, cNmSrvATLYS) == 0){	
				// Obter <callCircle>
				if((auxDOMCallCircle = tuxhp->walkDOM(auxDOMPrimarySvc,"callCircle", 0)) != NULL ){
				
					// lendo Atributo "seqNbr" da Tag <callCircle>
					pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"seqNbr");	
					strcpy(cSeqNbr,pcTagXmlIn);

					// lendo Atributo "listTypeInd" da Tag <callCircle>
					pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"listTypeInd");	
					strcpy(cListTypeInd,pcTagXmlIn);

					// lendo Atributo "methodInd" da Tag <callCircle>
					pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"methodInd");	
					strcpy(cMethodInd,pcTagXmlIn);

					bAchouCallCircle = true;
				}
				else{
					tuxfw_getlogger()->debug("TAG_<callCircle>_INEXISTENTE"); 
					throw new TuxBasicSvcException("24E0000", "TAG_<callCircle>_INEXISTENTE");
				}	
			}
		}
		else{
			tuxfw_getlogger()->debug("TAG_<primarySvc>_INEXISTENTE"); 
			throw new TuxBasicSvcException("24E0000", "TAG_<primarySvc>_INEXISTENTE");
		}
		i++;
	}

	if(!bAchouCallCircle){
		tuxfw_getlogger()->debug("Nao Encontrado o PrimarySvc:svcNm=%s",cNmSrvATLYS); 
		throw new TuxBasicSvcException("24E0000", "N�o Encontrado o PrimarySvc:svcNm requisitado.");
	}
*/

  /*
	<inputGetCallingCircle>
       <callCircleInfo methodInd="" listTypeInd="" seqNbr="291436050" /> 
    </inputGetCallingCircle>

	*/
	XMLGen oXmlEnvioGetCallingCircle;
	
	oXmlEnvioGetCallingCircle.createTag("inputGetCallingCircle");
		oXmlEnvioGetCallingCircle.createTag("callCircleInfo");
			oXmlEnvioGetCallingCircle.addProp("methodInd",cMethodInd);
			oXmlEnvioGetCallingCircle.addProp("listTypeInd", cListTypeInd);
			oXmlEnvioGetCallingCircle.addProp("seqNbr", cSeqNbr );
		oXmlEnvioGetCallingCircle.closeTag();
	oXmlEnvioGetCallingCircle.closeTag();

	// Guarda o xml de retorno
	DOMNode* outputDOMNodeGetCallingCircle;
	
	outputDOMNodeGetCallingCircle = callRemoteAPI(this->getServiceName(), &oXmlEnvioGetCallingCircle, "inputGetCallingCircle" );
	
	if(outputDOMNodeGetCallingCircle == NULL){
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 
		throw new TuxBasicSvcException(getLastStatusCode(),getLastStatusText());	
	}

	// Validar de a Opera��o  Remota retornou erro.
	if(strcmp(getLastStatusCode(),"24I0000") != 0){
	
		tuxfw_getlogger()->debug("Erro %s: %s",getLastStatusCode(),getLastStatusText()); 		
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	}

	// Faz o Parsing da lista de CallCircle no XML de Retorno da API GetCallingCircle
	// e Monta uma lista com os CallCircles

	// Lista de Retorno
	list<CallCircle> olstCallCircle;
	CallCircle oCallCircle;

	// Data de Hoje para Fazer o Filtro dos Nro Expirados
	char cDataHoje[15];
	strcpy(cDataHoje,CAvailableService::getSysDateBD(cDate));

    //Montar a Lista
	i=0;	
	while((auxDOMCallCircle = tuxhp->walkDOM(outputDOMNodeGetCallingCircle,"callCircleInfo", i)) != NULL ){

		// lendo Atributo "exprDt" da Tag <callCircleInfo>
		pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"exprDt");	
		
		// Filtrar os Nros j� expirados, para n�o exbir na Camada JAVA.
		// Se a Data de Expeirtacao(Atributo "exprDt") n�o for menor ou igual a Hoje
		if(!(strcmp(pcTagXmlIn,cDataHoje) <= 0)){

			oCallCircle.setExprDt(pcTagXmlIn);

			// lendo Atributo "accessNbr" da Tag <callCircleInfo>
			pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"accessNbr");	
			oCallCircle.setAccNbr(pcTagXmlIn);
			
			// lendo Atributo "effDt" da Tag <callCircleInfo>
			pcTagXmlIn  = tuxhp->getAttrValue(auxDOMCallCircle,"effDt");	
			oCallCircle.setEffDt(pcTagXmlIn);

 			olstCallCircle.push_back( oCallCircle );
		}

		i++;
	}

	/*
	if(olstCallCircle.size() == 0)
	{
		tuxfw_getlogger()->debug("LISTA VAZIA: Nenhum <callCircleInfo> Encontrado"); 
		throw new TuxBasicSvcException("24E0000", "Nenhum <callCircleInfo> Encontrado");
	}
	*/

	// Monta XML de SAIDA
/* 
<FavoritosVO>
  <FavoritosItem><numero>1171010101</numero></FavoritosItem>
   <FavoritosItem><numero>1171010102</numero></FavoritosItem>
   <FavoritosItem><numero>1171010103</numero></FavoritosItem>
</FavoritosVO>

*/
	xml_g->createTag("FavoritosVO");
	if((pcTagXmlIn = tuxhp->walkTree(dnode,TAG_XML_IN_XMLNS, 0)) != NULL)
	{
		xml_g->addProp( PROP_XML_OUT_XMLNS, pcTagXmlIn );
	};
	// Percorer a Lista e Montar as tags <callCircleMemberInfo>
	while( olstCallCircle.size() > 0){

		// Le o valor do elemento do topo.
		oCallCircle = olstCallCircle.front();

		// Preenche o Xml de retorno 
		xml_g->createTag("FavoritosItem");
			xml_g->addItem("numero", oCallCircle.getAccNbr());
		xml_g->closeTag();

		// remove no topo da lista
		olstCallCircle.pop_front();
	};									
	xml_g->closeTag();	

	// Registro de contato
	this->registrarContato();

};
