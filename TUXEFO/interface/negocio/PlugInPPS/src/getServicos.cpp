#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getServicos()
{
/*	JAVA -> FO

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getServicos</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/
   
	char* pc_CELULAR = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

/*	FO -> GW
	<msg>
		<msgHdr>
			<service>GWPPS</service>
		</msgHdr>
		<msgBody>
			<ROOT>
				<TRANSACAO>1002</TRANSACAO>
				<CELULAR>1171010160</CELULAR>
			</ROOT>
		</msgBody>
	</msg>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_SERVICOS;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
	o_XMLtoGW.closeTag();

	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS, &o_XMLtoGW, NULL);

	// Verifica o retorno
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGW == NULL)
		throw new TuxBasicSvcException(ECD_PPS_GW_NOT_RESPOND, EMSG_PPS_GW_NOT_RESPOND);

/*	GW -> FO

	<ROOT>
		<REG>
			<SERVICO>EStado do Celular</SERVICO>
			<ESTADO>Ativado</ESTADO>
			<ATRIBUTO>1</ATRIBUTO>
			<ORIGEM>EO</ORIGEM>
		</REG>
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

	<ServicosVO>
		<ServicosItem>
			<nome></nome>
			<codigo></codigo>
			<descricao></descricao>
			<status></status>
			<validade></validade>
		</ServicosItem>
	</ServicosVO>
*/

	xml_g->createTag(TAG_XML_OUT_SERVICOS_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}


		DOMNode* po_REG;

		for (int i = 0; (po_REG = tuxhp->walkDOM(po_ROOT, TAG_XML_PPS_OUT_REG, i)) != NULL; i++)
		{

			xml_g->createTag(TAG_XML_OUT_SERVICOS_ITEM);

				char* pc_ATRIBUTO = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_ATRIBUTO, 0);
				char* pc_SERVICO  = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_SERVICO, 0);
				char* pc_ESTADO   = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_ESTADO, 0);


				xml_g->addItem(TAG_XML_OUT_NOME, this->traduzNomeServicoToDescricao(this->traduzAtributoToNomeServico(pc_ATRIBUTO)));

				xml_g->addItem(TAG_XML_OUT_CODIGO, this->traduzAtributoToNomeServico(pc_ATRIBUTO));

				xml_g->addItem(TAG_XML_OUT_DESCRICAO, (pc_SERVICO != NULL)? pc_SERVICO : "");

				xml_g->addItem(TAG_XML_OUT_STATUS, this->getServicoStatus(pc_ESTADO));

				xml_g->addItem(TAG_XML_OUT_VALIDADE, "");


				if (pc_ATRIBUTO != NULL) XMLString::release(&pc_ATRIBUTO);
				if (pc_SERVICO != NULL)  XMLString::release(&pc_SERVICO);
				if (pc_ESTADO != NULL)   XMLString::release(&pc_ESTADO);

			xml_g->closeTag();
		}

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
