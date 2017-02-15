#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInATLYS::getAjustes()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getAjustes</ProxyOperacao>
			<ProxyLinha></ProxyLinha>
			<idcontasistemaorigem>123</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<dataIni></dataIni>
			<dataFim></dataFim>
		</msgBody>
	</msg>
*/

	char* pcIdContaATL = NULL;
    char* pDtInicio = NULL;
	char* pDtFim = NULL;

	
	// Recupera id da conta no atlys.
	pcIdContaATL = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pcIdContaATL == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);
	
	if (*pcIdContaATL == '\0')
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_ID_CONTA_SIS_ORG_VV);
	
	if (! Util::isNum(pcIdContaATL)) 
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VI, MSG_ID_CONTA_SIS_ORG_VI);

	
	// XML_ATL_DT_INICIO
	pDtInicio = tuxhp->walkTree(dnode, XML_ATL_DT_INICIO, 0);

	if (pDtInicio == NULL)
		throw new TuxBasicSvcException(NRO_DT_INICIO_NAO_ENCONTRADA_NE, MSG_DT_INIC_NAO_ENCONTRADA_NE);
	
	if (*pDtInicio == '\0')
		throw new TuxBasicSvcException(NRO_DT_INICIO_NAO_ENCONTRADA_VV, MSG_DT_INIC_NAO_ENCONTRADA_VV);


	// XML_ATL_DT_FIM
	pDtFim = tuxhp->walkTree(dnode, XML_ATL_DT_FIM, 0);
	
	if (pDtFim == NULL)
		throw new TuxBasicSvcException(NRO_DT_FIM_NAO_ENCONTRADA_NE, MSG_DT_FIM_NAO_ENCONTRADA_NE);
	
	if (*pDtFim == '\0')
		throw new TuxBasicSvcException(NRO_DT_FIM_NAO_ENCONTRADA_VV, MSG_DT_FIM_NAO_ENCONTRADA_VV);


/*	FO -> GW

	<inputGetAdjustments acctNbr="xxx" fromDt="xxx" toDt="xxx"/>
*/

	XMLGen   oXMLInAtlys;
	DOMNode* poResultNode = NULL;

	oXMLInAtlys.createTag(XML_ATL_INPUT_GET_ADJUSTMENTS);
	oXMLInAtlys.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);
	oXMLInAtlys.addProp(XML_ATL_FROM_DT, pDtInicio);
	oXMLInAtlys.addProp(XML_ATL_TO_DT, pDtFim);
	oXMLInAtlys.closeTag();

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLInAtlys, XML_ATL_INPUT_GET_ADJUSTMENTS);
	
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if (poResultNode == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	GW -> FO

	<outputGetAdjustments>
		<adj createDtTm="xxx" amt="xxx" credit="xxx" billed="xxx" fnclTrnsctTypeId="xxx" desc="xxx" sbscrpId="xxx"/>
		<adj createDtTm="xxx" amt="xxx" credit="xxx" billed="xxx" fnclTrnsctTypeId="xxx" desc="xxx" sbscrpId="xxx"/>
		............................................................................................................  
	</outputGetAdjustments>
*/

/*	FO -> JAVA

	<LupaFaturamentoPosVO>
		<LFAjustes>
			<idConta></idConta>
			<idAjustes></idAjustes>
			<vlAjuste></vlAjuste>
			<dsAjuste></dsAjuste>
			<inPersonalizada></inPersonalizada>
		</LFAjustes>
	</LupaFaturamentoPosVO>
*/

	// Recupera as informações. Para cada nó, gera um nó no XML de saída.
	xml_g->createTag(TAG_XML_OUT_LUPA_FATURAMENTO_POS_VO);
	xml_g->addProp(PROP_XML_OUT_XMLNS, PROP_XML_OUT_VAL_XMLNS);

	
	DOMNode* poUnitNode = NULL;
	int i = 0;

	while ((poUnitNode = tuxhp->walkDOM(poResultNode, XML_ATL_ADJ, i)) != NULL)
	{
		// Recupera os dados que precisam ser formatados em variáveis locais.
		char* pCreateDtTm = tuxhp->walkAttr(poResultNode, XML_ATL_ADJ, XML_ATL_CREATE_DT_TM, i);
		char* pCredit     = tuxhp->walkAttr(poResultNode, XML_ATL_ADJ, XML_ATL_CREDIT, i);
		char* pAmt        = tuxhp->walkAttr(poResultNode, XML_ATL_ADJ, XML_ATL_AMT, i);
		char* pDsAjuste   = tuxhp->walkAttr(poResultNode, XML_ATL_ADJ, XML_ATL_DS_AJUSTE, i);
		char* pBilled     = tuxhp->walkAttr(poResultNode, XML_ATL_ADJ, XML_ATL_BILLED, i);

		xml_g->createTag(TAG_XML_OUT_LF_AJUSTES);
			xml_g->addItem(TAG_XML_OUT_ID_CONTA, pcIdContaATL);

			// formata data e hora
			// de YYYY-MM-DDTHH:MI:SS para DD/MM/YYYY HH:MI:SS
			char szDataHora[19 + 1];

			sprintf(szDataHora, "%.*s/%.*s/%.*s %.*s:%.*s:%.*s",
				2, (pCreateDtTm + 8),
				2, (pCreateDtTm + 5),
				4, pCreateDtTm,
				2, (pCreateDtTm + 11),
				2, (pCreateDtTm + 14),
				2, (pCreateDtTm + 17)
			);

			xml_g->addItem(TAG_XML_OUT_DT_AJUSTE, szDataHora);

			if (pCredit != NULL)
			{
				if (strcmp(pCredit, XML_VAL_FALSE) == 0)
				{
					char* pVlAjuste = NULL;

					if ((pVlAjuste = (char *)malloc(strlen(pAmt) + 1 + 1)) == NULL)
						throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS_ALLOC);

					sprintf(pVlAjuste, "-%s", pAmt);
				
					tuxfw_getlogger()->debug("pAmt[%s]", pAmt);
					tuxfw_getlogger()->debug("pVlAjuste[%s]", pVlAjuste);

					xml_g->addItem(TAG_XML_OUT_VL_AJUSTE, pVlAjuste);
			
					free(pVlAjuste);
				}
				else
					xml_g->addItem(TAG_XML_OUT_VL_AJUSTE, pAmt);
			}

			char szDsAjuste[256];

			memset(szDsAjuste, '\0', sizeof(szDsAjuste));
			
			if (pDsAjuste != NULL)
				strncpy(szDsAjuste, pDsAjuste, sizeof(szDsAjuste));

			xml_g->addItem("inPersonalizada", szDsAjuste);

			char szBilled[2];

			if(pBilled != NULL)
			{
				if (strcmp(pBilled, XML_VAL_TRUE) == 0)
					strcpy(szBilled, "S");
				else if (strcmp(pBilled, XML_VAL_FALSE) == 0)
					strcpy(szBilled, "N");
			}

			xml_g->addItem("inFaturado", szBilled);
		
		xml_g->closeTag();

		i++;
	}
}
