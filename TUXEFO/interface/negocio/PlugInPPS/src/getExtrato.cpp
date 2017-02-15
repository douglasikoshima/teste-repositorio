#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getExtrato()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getExtrato</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<dataIni>2004-08-10 00:00:00</dataIni>
			<dataFim>2004-08-20 00:00:00</dataFim>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;
	char* pc_DTINI   = NULL;
	char* pc_DTFIM   = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	
	// Trata a tag DTINI
	pc_DTINI = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_INI, 0);

	if (pc_DTINI == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_INI, EMSG_TAG_XML_IN_NE_DATA_INI);
	}

	if (*pc_DTINI == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_INI, EMSG_TAG_XML_IN_VV_DATA_INI);
	}

	// compara o tamanho da data
	if (strlen(pc_DTINI) != 19)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_DATA_INI, EMSG_TAG_XML_IN_VI_DATA_INI);
	}

	// Trata a tag DTFIM
	pc_DTFIM = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_FIM, 0);

	if (pc_DTFIM == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_FIM, EMSG_TAG_XML_IN_NE_DATA_FIM);
	}

	if (*pc_DTFIM == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_FIM, EMSG_TAG_XML_IN_VV_DATA_FIM);
	}

	// compara o tamanho da data
	if (strlen(pc_DTFIM) != 19)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);
		XMLString::release(&pc_DTFIM);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_DATA_FIM, EMSG_TAG_XML_IN_VI_DATA_FIM);
	}

/*	FO -> GW

	<ROOT>
		<TRANSACAO>105</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<DTFIM>2004-07-10 23:59:59</DTFIM>
		<DTINI>2004-07-01 00:00:00</DTINI>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_EXTRATO;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DTINI, pc_DTINI);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DTFIM, pc_DTFIM);
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

	XMLString::release(&pc_DTINI);
	XMLString::release(&pc_DTFIM);

	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS_EXT, &o_XMLtoGW, NULL);

	// Verifica o retorno
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGW == NULL)
		throw new TuxBasicSvcException(ECD_PPS_GW_NOT_RESPOND, EMSG_PPS_GW_NOT_RESPOND);

/*	GW -> FO

	<ROOT>
		<ERR>0</ERR>
		<ERR_MENS></ERR_MENS>
	</ROOT>
*/

	// Variavel para armazenar o DOM de 'ROOT'
	DOMNode* po_ROOT = NULL;

	// Verifica a tag ROOT
	po_ROOT = tuxhp->walkDOM(po_XMLfromGW, TAG_XML_PPS_OUT_ROOT, 0);

	if (po_ROOT == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_PPS_OUT_NE_ROOT, EMSG_TAG_XML_PPS_OUT_NE_ROOT);

	// trata erro genérico do PPS
	this->trataErro(po_ROOT);

/*	FO -> JAVA

	<ExtratoVO>
		<buffer></buffer>
		<backEnd></backEnd>
	</ExtratoVO>
*/

	xml_g->createTag(TAG_XML_OUT_EXTRATO_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
			
			XMLString::release(&pc_xmlns);
		}

	
		xml_g->createTag(TAG_XML_OUT_BUFFER);
		
			DOMNode* auxDOM = NULL;
			char*    pc_Node = NULL;

			auxDOM = tuxhp->walkDOM(po_ROOT, "EXTRACTO_DETALHADO", 0);

			if(auxDOM)
				pc_Node = tuxhp->getNodeAsString(auxDOM);

			if((pc_Node != NULL) && (strlen(pc_Node) > 0))
			{
				xml_g->aggregateXML(pc_Node);
				
				tuxfw_getlogger()->debug("PlugInPPS:getExtrato:Liberando pc_Node");
				free(pc_Node);
			}	
				
		xml_g->closeTag();
		
		xml_g->addItem(TAG_XML_OUT_BACKEND, "PPS");

	xml_g->closeTag();



	// Registro de contato
	this->registrarContato();

}
