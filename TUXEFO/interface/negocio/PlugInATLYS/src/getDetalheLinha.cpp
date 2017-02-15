#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <ctime>
#include <cstring>
#include <cstdio>

using namespace std;


void PlugInATLYS::getDetalheLinha()
{
/*	JAVA -> FO

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getDetalheLinha</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/


	// Geral
	char* pcidlinhasistemaorigem;

	// Recupera numero da linha
	pcidlinhasistemaorigem = getIdLinhaSistemaOrigem();




	char* pcsvcExprDt;
	char  cData[11];

	// XML
	DOMNode* poResultNodeLinha;
	DOMNode* poResultNodeData;
	DOMNode* poResultNodeAux;

	char sAchouContr[2];
	char sMulta[256];
	char sAparelho[512];
	char sModelo[512];
	char sDescricao[512];
	char sESN[256];
	char sTecnologia[256];
	char *psTecnologia = NULL;

	//Data
	time_t curr;
	tm local;

	// Limpa as variaveis
	memset(sAchouContr, '\0', sizeof(sAchouContr));
	memset(sMulta, '\0', sizeof(sMulta));
	memset(sAparelho, '\0', sizeof(sAparelho));
	memset(sModelo, '\0', sizeof(sModelo));
	memset(sDescricao, '\0', sizeof(sDescricao));
	memset(sESN, '\0', sizeof(sESN));
	memset(sTecnologia, '\0', sizeof(sTecnologia));
	memset(cData, '\0', sizeof(cData));

	//Recupera a data correte
	time(&curr);
	local =* (localtime(&curr));
	sprintf(cData, "%4d-%2d-%d", (local.tm_year + 1900), (local.tm_mon + 1), local.tm_mday);



	strcpy(sAchouContr, "N");

	// Monta XML de entrada para a chamada da API de linha.
	XMLGen oXMLtoGetInputSubscriptionInfo;

	oXMLtoGetInputSubscriptionInfo.createTag(XML_ATL_INPUT_SUBSCRIPTION_INFO);
	oXMLtoGetInputSubscriptionInfo.addProp(XML_ATL_ACCT_SBSCRID, pcidlinhasistemaorigem);

	// Chama a API para trazer as informações de uma linha
	poResultNodeLinha = callRemoteAPI(this->getServiceName(), &oXMLtoGetInputSubscriptionInfo, XML_ATL_INPUT_SUBSCRIPTION_INFO );
    
	if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E')
        throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if (poResultNodeLinha == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Recupera informações de aparelho e tecnologia.
	char *pcMfcNm = tuxhp->walkAttr(poResultNodeLinha, "eqpManufacturer", "mfcNm", 0);

	if(pcMfcNm == NULL)
		pcMfcNm = tuxhp->walkAttr(poResultNodeLinha, "eqpMfc", "mfcNm", 0);

	if (pcMfcNm)
		sprintf(sAparelho, "%s", pcMfcNm);

	char *pcModelNbr = tuxhp->walkAttr(poResultNodeLinha, "eqpManufacturer", "modelNbr", 0);

	if(pcModelNbr == NULL)
		pcModelNbr = tuxhp->walkAttr(poResultNodeLinha, "eqpMfc", "modelNbr", 0);

	if (pcModelNbr)
		sprintf(sModelo, "%s", pcModelNbr);

	char *pcModelNm = tuxhp->walkAttr(poResultNodeLinha, "eqpManufacturer", "modelNm", 0);

	if(pcModelNm == NULL)
		pcModelNm = tuxhp->walkAttr(poResultNodeLinha, "eqpMfc", "modelNm", 0);

	if (pcModelNm)
		sprintf(sDescricao, "%s", pcModelNm);

	char *pcPcsEpq = tuxhp->walkAttr(poResultNodeLinha, "pcsEpq", "esn", 0);
	if (pcPcsEpq)
		sprintf(sESN, "%s", pcPcsEpq);
	
	char *pcFreqModeCd = tuxhp->walkAttr(poResultNodeLinha, "eqpManufacturer", "freqModeCd", 0);
	
	if(pcFreqModeCd == NULL)
		pcFreqModeCd = tuxhp->walkAttr(poResultNodeLinha, "eqpMfc", "freqModeCd", 0);

	char* eqpAttr = NULL;
	char* iccid = "";
	for(int i=0; (eqpAttr = tuxhelper.walkAttr(poResultNodeLinha, "eqpAttr", "name", i))!=NULL; i++ )
	{
		if(!strcmp(eqpAttr,"ICCID"))
		{
			 if (iccid != NULL && strlen(iccid) > 0) {
				XMLString::release(&iccid);
				iccid = "";
		}
			 iccid = tuxhelper.walkAttr(poResultNodeLinha, "eqpAttr", "value", i);
		}
		XMLString::release(&eqpAttr);
	}
	
	if (pcFreqModeCd)
	{
		XMLGen oXMLtoGetRefData;

		oXMLtoGetRefData.createTag(XML_ATL_REF_DATA);
		oXMLtoGetRefData.createTag(XML_ATL_TABLE_NAME);
		oXMLtoGetRefData.addProp(XML_ATL_VAL, "FREQUENCY_MODE");
		oXMLtoGetRefData.closeTag();

        	// Chama a API para trazer as informações de uma linha
        	poResultNodeAux = callRemoteAPI(this->getServiceName(), &oXMLtoGetRefData, XML_ATL_REF_DATA);
        	if (poResultNodeAux == NULL)
                	throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

		// Procura pelo mesmo código. Se não encontrar, mantém o código
		sprintf(sTecnologia, "%s", pcFreqModeCd);	
		
		char* pcFrqCd = NULL;
		
		int iFrqCnt = 0;
		
		while ((pcFrqCd = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_REF_DATA_LST, XML_ATL_CODE, iFrqCnt)) != NULL) {
			if (strcmp(pcFrqCd, pcFreqModeCd) == 0) {
				psTecnologia = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_REF_DATA_LST, XML_ATL_DESC, iFrqCnt);
				sprintf(sTecnologia, "%s", psTecnologia);
				XMLString::release(&pcFrqCd);
				XMLString::release(&psTecnologia);
				break;
			}
			
			iFrqCnt++;
			XMLString::release(&pcFrqCd);
		}
	}
	
	//Verifico todas as tags XML_ATL_SVCASGMINFO
	for( int x = 0;; x++ )
	{
		poResultNodeAux = tuxhp->walkDOM( poResultNodeLinha, XML_ATL_SVCASGMINFO, x );
		
		//Caso nao existam ou se tenha percorrido todas, entao para
		if (poResultNodeAux == NULL)
			break;

		//Recupera a data
		pcsvcExprDt = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_SVCASGMINFO, XML_ATL_OUT_SVCEXPRDT, 0);
		tuxfw_getlogger()->debug("DATA EXPIRACAO: %s", pcsvcExprDt);
		
		//Verifica se a data retornada do atlys eh maior que a data atual
		if( strcmp( pcsvcExprDt, cData ) > 0 )
		{
			char* pcContrato = NULL;
			char* pcVlMulta = NULL;

			poResultNodeAux = tuxhp->walkDOM( poResultNodeAux, XML_ATL_PRIMARYSVC, 0 );
			if (poResultNodeAux == NULL) {
				XMLString::release(&pcsvcExprDt);
				throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
			}
				
			pcContrato = tuxhelper.walkAttr(poResultNodeAux, XML_ATL_PRIMARYSVC, XML_ATL_OUT_SVCNM, 0);

			DOMNode* poValorNode;
			int cont = 0;
			while ((poValorNode = tuxhp->walkDOM(poResultNodeAux, XML_ATL_CHARGE, cont)) != NULL) {
				char* pcContType = tuxhelper.walkAttr(poValorNode, XML_ATL_CHARGE, "chgTypeCd", cont++);
				tuxfw_getlogger()->debug("TAG CHARGE encontrada, com tipo %s.", (pcContType == NULL ? "null" : pcContType));
				if (pcContType != NULL && strcmp(pcContType, "C") == 0) {
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
			tuxfw_getlogger()->debug("PCCONTRATO: %s", pcContrato);
			
			XMLGen oXMLtoGetInputServices;

			oXMLtoGetInputServices.createTag(XML_ATL_INPUT_SERVICES_REF_DATAV1);
			oXMLtoGetInputServices.addProp(XML_ATL_ACCT_SVCNAME, pcContrato);
			
			// Chama a API para trazer as informações cada servico XML_ATL_SVCASGMINFO
			poResultNodeData = callRemoteAPI(this->getServiceName(), &oXMLtoGetInputServices, XML_ATL_INPUT_SERVICES_REF_DATAV1 );

			if (poResultNodeData == NULL) {
				XMLString::release(&pcContrato);
				XMLString::release(&pcsvcExprDt);
				XMLString::release(&pcVlMulta);
				throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
			}
			
			char *pAtlAutoExprFlag = NULL;
			pAtlAutoExprFlag = tuxhp->walkAttr(poResultNodeData, XML_ATL_SVC_REF_DATA, XML_ATL_AUTO_EXPR_FLAG, 0);
			
			if(pAtlAutoExprFlag != NULL &&
			   strcmp(pAtlAutoExprFlag, XML_ATL_TRUE) == 0) {
				// Achou o contrato de fidelização. Gera saída.
				sprintf(sAchouContr, "S");
				sprintf(sMulta, "%s", pcVlMulta);
				XMLString::release(&pcContrato);
				XMLString::release(&pcsvcExprDt);	
				XMLString::release(&pcVlMulta);	
				XMLString::release(&pAtlAutoExprFlag);				
				break;
			}
			XMLString::release(&pcContrato);
			XMLString::release(&pAtlAutoExprFlag);	
			XMLString::release(&pcVlMulta);			
		}
		XMLString::release(&pcsvcExprDt);		
	}

	tuxfw_getlogger()->debug("MODELO: %s", sModelo);
	xml_g->addItem("modelo", sModelo);

	tuxfw_getlogger()->debug("DESCRICAO: %s", sDescricao);
	xml_g->addItem("descricao", sDescricao);

	tuxfw_getlogger()->debug("MARCA: %s", sAparelho);
	xml_g->addItem("marca", sAparelho);

	tuxfw_getlogger()->debug("ESN: %s", sESN);
	xml_g->addItem("ESN", sESN);

	tuxfw_getlogger()->debug("ICCID: %s", iccid);
	xml_g->addItem("ICCID", iccid);
	XMLString::release(&iccid);

	tuxfw_getlogger()->debug("TECNOLOGIA: %s", sTecnologia);
	xml_g->addItem("dsTecnologia", sTecnologia);
	
	tuxfw_getlogger()->debug("MULTA: %s", sMulta);
	xml_g->addItem("dsMultaContrato", sMulta);
	
	tuxfw_getlogger()->debug("CONTRATOFID: %s", sAchouContr);
	xml_g->addItem("ContratoFidelizacao", sAchouContr);


/*	FO -> JAVA

	<DetalheLinhaVO>
		<modelo></modelo>
		<descricao></descricao>
		<marca></marca>
		<dsTecnologia></dsTecnologia>
		<dsMultaContrato></dsMultaContrato>
		<contratoFidelizacao></contratoFidelizacao>
	</DetalheLinhaVO>
*/

}

