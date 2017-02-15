#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInATLYS::getInfoContaCobranca()
{

	char* pcIdContaATL;
	int iIdContaATL;
	DOMNode* poResultNode;
	int i=0;
	char sInCob[2];

	// Recupera id da conta no atlys.
	pcIdContaATL = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);
	if (pcIdContaATL == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);
	if (!*pcIdContaATL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_ID_CONTA_SIS_ORG_VV);
	if ((iIdContaATL = atoi(pcIdContaATL)) <= 0) 
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VI, MSG_ID_CONTA_SIS_ORG_VI);

	// Chama a API inputGetCollAcctStateV2, pra saber se a conta está em cobranca
	XMLGen oXMLtoGetCollAcctStateV2;

	oXMLtoGetCollAcctStateV2.createTag(XML_ATL_INPUT_COLL_ACCT_STATE);
	oXMLtoGetCollAcctStateV2.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);
	oXMLtoGetCollAcctStateV2.closeTag();

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetCollAcctStateV2, XML_ATL_INPUT_COLL_ACCT_STATE);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E')
                if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_CONTA_NAO_COB))
                        sprintf(sInCob, "N");
                else
                        throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
        else
                sprintf(sInCob, "S");
	if (poResultNode == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Busca informações de conta em cobrança, caso esteja. 
	xml_g->createTag(TAG_XML_OUT_LUPA_FATURAMENTO_POS_VO);
	xml_g->addProp(PROP_XML_OUT_XMLNS, PROP_XML_OUT_VAL_XMLNS);

	if (!strcmp(sInCob, "N")) {
		xml_g->closeTag();
		return;
	}


	XMLGen oXMLtoGetAcctAgedBalances;
	oXMLtoGetAcctAgedBalances.createTag(XML_ATL_INPUT_GET_ACCT_AGED_BALANCES);
	oXMLtoGetAcctAgedBalances.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);

    poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetAcctAgedBalances, XML_ATL_INPUT_GET_ACCT_AGED_BALANCES);

	if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') 
		 throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
        if (poResultNode == NULL)
                throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Pra cada nó de saída da API, gera um nó na saída para o FO.
	DOMNode* poAged = NULL;
	while ((poAged = tuxhp->walkDOM(poResultNode, XML_ATL_OUT_GET_AGED_BALANCES, i)) != NULL) {
		char* pcDtFat;
		char* pcVlFat;
		char* pcVlAbertoAtual;
		char* pcVl30Dias;
		char* pcVl60Dias;
		char* pcVl90Dias;
		char* pcVlMaisDias;
		
		pcDtFat = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_LAST_BILL_DATE, 0);
		pcVlFat = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_BAL_BILL_AMNT, 0);
		pcVlAbertoAtual = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_BAL_CURRENT_AMNT, 0);
		pcVl30Dias = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_BAL_30_DAYS, 0);
		pcVl60Dias = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_BAL_60_DAYS, 0);
		pcVl90Dias = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_BAL_90_DAYS, 0);
		pcVlMaisDias = tuxhp->walkAttr(poAged, XML_ATL_OUT_GET_AGED_BALANCES, XML_ATL_BAL_OVER_90_DAYS, 0);

		xml_g->createTag(TAG_XML_OUT_LF_COBRANCA);
		if (pcDtFat != NULL) formataData(pcDtFat);
		xml_g->addItem(TAG_XML_OUT_DT_FATURAMENTO, pcDtFat == NULL ? "" : pcDtFat);
		xml_g->addItem(TAG_XML_OUT_VL_FATURADO, pcVlFat == NULL ? "" : pcVlFat);
		xml_g->addItem(TAG_XML_OUT_VL_ABERTO_ATUAL, pcVlAbertoAtual == NULL ? "" : pcVlAbertoAtual);
		xml_g->addItem(TAG_XML_OUT_VL_30_DIAS, pcVl30Dias == NULL ? "" : pcVl30Dias);
		xml_g->addItem(TAG_XML_OUT_VL_60_DIAS, pcVl60Dias == NULL ? "" : pcVl60Dias);
		xml_g->addItem(TAG_XML_OUT_VL_90_DIAS, pcVl90Dias == NULL ? "" : pcVl90Dias); 
		xml_g->addItem(TAG_XML_OUT_VL_MAIS_DIAS, pcVlMaisDias == NULL ? "" : pcVlMaisDias);
		xml_g->closeTag();

		i++;
	}

	xml_g->closeTag();
}
