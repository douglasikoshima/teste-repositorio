#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <cstdlib>
#include <cstring>

using namespace std;


void PlugInATLYS::getImpedimentos()
{

	char* pcIdContaATL;
	int iIdContaATL;	
	DOMNode* poResultNode;
	DOMNode* poUnitNode;
	int i=0;

	char *pszDataHora;
	char szInPrioridade[1 + 1];

	// Recupera id da conta no atlys.
	pcIdContaATL = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);
	if (pcIdContaATL == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);
	if (!*pcIdContaATL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_ID_CONTA_SIS_ORG_VV);
	if ((iIdContaATL = atoi(pcIdContaATL)) <= 0) 
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VI, MSG_ID_CONTA_SIS_ORG_VI);

	this->setIdContaSistemaOrigem(pcIdContaATL);

	// Chama a API inputSearchAccountNotes
	XMLGen  oXMLInAtlys;

	oXMLInAtlys.createTag(XML_ATL_INPUT_ACCT_NOTES);
	oXMLInAtlys.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);
	oXMLInAtlys.addProp(XML_ATL_ATTR_HI_PRIORITY_ONLY, XML_ATL_TRUE);

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLInAtlys, XML_ATL_INPUT_ACCT_NOTES);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E')
                throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	if (poResultNode == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Recupera as informações. Para cada nó, gera um nó no XML de saída.
	xml_g->createTag(TAG_XML_OUT_LISTA_IMPEDIMENTO_VO);
	xml_g->addProp(PROP_XML_OUT_XMLNS, PROP_XML_OUT_VAL_XMLNS);

	while ((poUnitNode = tuxhp->walkDOM(poResultNode, XML_ATL_ACCT_NOTE, i)) != NULL) {
		// Recupera os dados que precisam ser formatados em variáveis locais.
		char* pcDataNotaAtl = tuxhp->walkAttr(poResultNode, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_DATE, i);
		char* pcHoraNotaAtl = tuxhp->walkAttr(poResultNode, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_TIME, i);
		char* pszDsImpedimento = tuxhp->walkAttr(poResultNode, XML_ATL_ACCT_NOTE, XML_ATL_NOTE_DESC, i);
		char* pszDsPrioridade = tuxhp->walkAttr(poResultNode, XML_ATL_ACCT_NOTE, "hiPriorityFlag", i);
		char* pszNmSequencia = tuxhp->walkAttr(poResultNode, XML_ATL_ACCT_NOTE, "seqNbr", i);

	        // Realiza formatacao de data e hora e retorna a string concatenada
	        pszDataHora = this->formataDataHora(pcDataNotaAtl, pcHoraNotaAtl);

        	// define o valor de InPrioridade
        	if(pszDsPrioridade != NULL) {
            		if(!strcmp(pszDsPrioridade, XML_VAL_TRUE)) {
                		strcpy(szInPrioridade, "S");
            		} else if(!strcmp(pszDsPrioridade, XML_VAL_FALSE)) {
                		strcpy(szInPrioridade, "N");
           		} 
        	}

		xml_g->createTag(TAG_XML_OUT_IMPEDIMENTO_VO);
		xml_g->addItem(TAG_XML_OUT_DS_IMPEDIMENTO, pszDsImpedimento);
		xml_g->addItem(TAG_XML_OUT_DT_IMPEDIMENTO, pszDataHora);
		xml_g->addItem(TAG_XML_OUT_IN_PRIORIDADE, szInPrioridade);
		xml_g->addItem(TAG_XML_OUT_NM_SEQUENCIA, pszNmSequencia);
		xml_g->closeTag();

		// Incrementa 
		i++;
	}
}
