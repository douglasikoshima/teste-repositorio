#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <ctime>
#include <cstring>
#include <cstdio>
#include <cstdlib>

using namespace std;


void PlugInATLYS::getFidelizacao()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getFidelizacao</ProxyOperacao>
			<ProxyLinha></ProxyLinha>
			<idcontasistemaorigem>1</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pcidlinhasistemaorigem = NULL;

	// Recupera numero da linha
	pcidlinhasistemaorigem = tuxhp->walkTree(dnode, TAG_XML_IN_ID_LINHA_SIS_ORIGEM, 0);

	if (pcidlinhasistemaorigem == NULL)
		throw new TuxBasicSvcException(NRO_NR_LINHA_SIS_ORG_NE, MSG_NR_LINHA_SIS_ORG_NE);
	
	if (*pcidlinhasistemaorigem == '\0')
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_NR_LINHA_SIS_ORG_VV);
	
	if (! Util::isNum(pcidlinhasistemaorigem)) 
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VI, MSG_NR_LINHA_SIS_ORG_VI);

/*	FO -> GW

	<inputSubscriptionInfo sbscrpId="xxx"/>
*/	
	XMLGen   oXMLInAtlys;
	DOMNode* poResultNodeLinha = NULL;

	// Monta XML de entrada para a chamada da API de linha.
	oXMLInAtlys.createTag(XML_ATL_INPUT_SUBSCRIPTION_INFO);
	oXMLInAtlys.addProp(XML_ATL_ACCT_SBSCRID, pcidlinhasistemaorigem);


	// Chama a API para trazer as informações de uma linha
	poResultNodeLinha = callRemoteAPI(this->getServiceName(), &oXMLInAtlys, XML_ATL_INPUT_SUBSCRIPTION_INFO );
	
	if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E')
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if (poResultNodeLinha == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/* GW -> FO

	<outputGetSubscriptionInfo ixc="00011">
		<subscriberInfo acctNbr="xxx" sbscrpId="xxx" accessNbr="xxx" actvtnDt="xxx" csa="xxx" custId="xxx" svcEffDt="xxx" svcExprDt="xxx" sbscrpTypCd="xxx" taxAddrSeqNbr="xxx" chnlTypeInd="xxx" leaderFlag="xxx"/>
		<svcAsgmInfo svcEffDt="xxx" svcExprDt="xxx" realtimeSharedFlag="xxx">
		<salesChnl salesChnlId="xxx"/>
		<subTotal amntDue="xxx"/>
		<primarySvc svcNm="xxx" svcId="xxx" svcTyp="xxx" svcDesc="xxx" chnlTypeInd="xxx" suspendStat="xxx" svcDurQty="xxx" svcCategory="xxx" srchCategory="xxx" extnClassNm="xxx">
		<depositAmt/>
		<charge chgTypeCd="xxx" chgStDt="xxx" recurChgPeriodCd="xxx">
		<chgAmt amntDue="xxx"/>
		</charge>
		<charge chgTypeCd="xxx" chgStDt="xxx" recurChgPeriodCd="xxx">
		<chgAmt amntDue="xxx"/>
		</charge>
		<svcAttr/>
		</primarySvc>
		<memberSvcs svcNm="xxx" svcId="xxx" svcTyp="xxx" svcDesc="xxx" chnlTypeInd="xxx" suspendStat="xxx" svcDurQty="xxx" svcCategory="xxx" srchCategory="xxx" extnClassNm="xxx">
		<depositAmt/>
		<svcAttr>
		<pcsEpq esn="xxx" esnType="xxx">
		<eqpManufacturer mfcCd="xxx" mfcNm="xxx" modelNbr="xxx" modelNm="xxx" freqTypCd="xxx" freqModeCd="xxx" stolenDt="xxx" recoveryDt="xxx"/>
		</pcsEpq>
		<pcsAcsNbr MDN="xxx" MDNEffDt="xxx" MDNEffTm="xxx" MDNExpDt="xxx" MDNExpTm="xxx" reasonCd="xxx" portStatus="xxx"/>
		<addlAttrs attrNm="xxx" attrValue="xxx" attrSeqNbr="xxx"/>
		</svcAttr>
		</memberSvcs>
		</svcAsgmInfo>
		..............
		<sbscrpMktPgm mktPgmId="xxx" mktPgmDesc="xxx" csa="xxx" enrollDt="xxx"/>
		........................................................................
	</outputGetSubscriptionInfo>
*/


	//Geral
	char* pcsvcExprDt;
	char* pcsvcEffDt;
	
	char  cData[10+1];

	//XML
	DOMNode* poResultNodeData;
	DOMNode* poResultNodeAux;
	DOMNode* poResultNodeService;
	
	//Data
	time_t curr;
	tm local;
	
	//Recupera a data correte
	memset(cData, '\0', sizeof(cData));

	time(&curr);
	
	local =* (localtime(&curr));
	
	sprintf(cData, "%4d-%2d-%d", local.tm_year+1900, local.tm_mon+1, local.tm_mday);

		
	//Verifico todas as tags XML_ATL_SVCASGMINFO
	for(unsigned int x = 0;; x++)
	{
		poResultNodeAux = tuxhp->walkDOM( poResultNodeLinha, XML_ATL_SVCASGMINFO, x);

		//Caso nao existam ou se tenha percorrido todas, entao para
		if (poResultNodeAux == NULL)
			break;

		//Recupera a data
		pcsvcExprDt = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_SVCASGMINFO, XML_ATL_OUT_SVCEXPRDT, 0);


		//Recupera a data de início do contrato
		pcsvcEffDt  = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_SVCASGMINFO, XML_ATL_OUT_SVCEFFDT, 0);
		
		
		//Verifica se a data retornada do atlys eh maior que a data atual
		if( strcmp( pcsvcExprDt, cData ) > 0 )
		{
			char* pcContrato = NULL;
			char* pcVlMulta = NULL;

			poResultNodeAux = tuxhp->walkDOM( poResultNodeAux, XML_ATL_PRIMARYSVC, 0 );

			if (poResultNodeAux == NULL) {
				XMLString::release(&pcsvcExprDt);
				XMLString::release(&pcsvcEffDt);
				throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
			}

			char *serviceName = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_PRIMARYSVC, XML_ATL_OUT_SVCNM, 0);
			char *serviceType = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_PRIMARYSVC, "svcTyp", 0);			

			XMLGen oXMLInAtlysService;

			oXMLInAtlysService.createTag(XML_ATL_INPUT_GET_MEMBER_SERVICES);
			oXMLInAtlysService.createTag(XML_ATL_ACCT_SVCNMTYPE);
			oXMLInAtlysService.addProp(XML_ATL_OUT_SVCNM, serviceName);
			oXMLInAtlysService.addProp("svcTyp", serviceType);

			XMLString::release(&serviceName);
			XMLString::release(&serviceType);

			// chamar a API getMemberService
			poResultNodeService = callRemoteAPI(this->getServiceName(), &oXMLInAtlysService, XML_ATL_INPUT_GET_MEMBER_SERVICES );
			if (poResultNodeService == NULL) {
				XMLString::release(&pcsvcExprDt);
				XMLString::release(&pcsvcEffDt);
				throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
			}

			char *svcAttrNm = tuxhelper.walkAttr(poResultNodeService, XML_ATL_ADMINSVC, XML_ATL_SVCATTRNM, 0);
				
			pcContrato = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_PRIMARYSVC, XML_ATL_OUT_SVCNM, 0);

            DOMNode* poValorNode;

            int cont = 0;
            
			while ((poValorNode = tuxhp->walkDOM(poResultNodeAux, XML_ATL_CHARGE, cont)) != NULL)
			{
                    char* pcContType = tuxhelper.walkAttr(poValorNode, XML_ATL_CHARGE, "chgTypeCd", cont++);
            
					tuxfw_getlogger()->debug("TAG CHARGE encontrada, com tipo %s.", (pcContType == NULL ? "null" : pcContType));
                    
					if (pcContType != NULL && strcmp(pcContType, "C") == 0)
					{
                            tuxfw_getlogger()->debug("TAG CHARGE com tipo C encontrada");
                            
							pcVlMulta = tuxhelper.walkAttr(poValorNode, "chgAmt", XML_ATL_AMNT_DUE, 0);
                            
							if (pcVlMulta)
                                    tuxfw_getlogger()->debug("Valor da multa: %s", pcVlMulta);
                            
							XMLString::release(&pcContType);
							break;
                    }
					XMLString::release(&pcContType);
            }

			
			// Monta XML de entrada para a chamada da API de data.			
			/*
			delete poXMLInAtlys;
			poXMLInAtlys = new XMLGen();
			poXMLInAtlys->createTag(XML_ATL_INPUT_SERVICES_REF_DATAV1);
			poXMLInAtlys->addProp(XML_ATL_ACCT_SVCNAME, pcContrato);
			
			// Chama a API para trazer as informações cada servico XML_ATL_SVCASGMINFO
			poResultNodeData = callRemoteAPI(SERVICE_ATLYS, poXMLInAtlys, XML_ATL_INPUT_SERVICES_REF_DATAV1 );
			
			if (poResultNodeData == NULL)
				throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
			
			delete poXMLInAtlys;
			*/
			
			if(svcAttrNm != NULL) {
				// Achou o contrato de fidelização. Gera saída.
				xml_g->addItem(TAG_XML_OUT_CONTRATO, pcContrato);
				xml_g->addItem(TAG_XML_OUT_VALOR_MULTA, pcVlMulta);
				formataData(pcsvcEffDt);				
				xml_g->addItem (TAG_XML_OUT_DATA_INICIO_CONTRATO, pcsvcEffDt);  
				formataData(pcsvcExprDt);
				xml_g->addItem(TAG_XML_OUT_DATA_FIM_CONTRATO, pcsvcExprDt);


			}
			XMLString::release(&svcAttrNm);
			XMLString::release(&pcContrato);
			XMLString::release(&pcVlMulta);			
		}
		
		XMLString::release(&pcsvcExprDt);
		XMLString::release(&pcsvcEffDt);
	}
}