#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::setFavorito()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setFavorito</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<servico>FAVMAR</servico>
			<numeroAntigo>1181819090</numeroAntigo>
			<numeroNovo>1191918080</numeroNovo>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;
	char* pc_numeroNovo = NULL;
	char* pc_numeroAntigo = NULL;
	int   iIsValid;


	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);


	// Pega numeroNovo
	pc_numeroNovo = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_NOVO, 0);

	if (pc_numeroNovo == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_NOVO, EMSG_TAG_XML_IN_NE_NUMERO_NOVO);
	}

	if ((*pc_numeroNovo != '\0') && (strlen(pc_numeroNovo) < 10 || strlen(pc_numeroNovo) > 11))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_numeroNovo);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_NUMERO_NOVO, EMSG_TAG_XML_IN_VI_NUMERO_NOVO);
	}


	// Pega numeroAntigo
	pc_numeroAntigo = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_ANTIGO, 0);

	if (pc_numeroAntigo == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_numeroNovo);	
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_ANTIGO, EMSG_TAG_XML_IN_NE_NUMERO_ANTIGO);
	}

	if ((*pc_numeroAntigo != '\0') && (strlen(pc_numeroAntigo) < 10 || strlen(pc_numeroAntigo) > 11))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_numeroNovo);
		XMLString::release(&pc_numeroAntigo);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_NUMERO_ANTIGO, EMSG_TAG_XML_IN_VI_NUMERO_ANTIGO);
	}

	// Verifica se numeroNovo ou numeroAntigo foi passado
	if ((*pc_numeroNovo == '\0') && (*pc_numeroAntigo == '\0'))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException("24E0000", "TAGS_<numeroNovo>_<numeroAntigo>_VALOR_VAZIO");
	}

/*	FO -> GW

	<ROOT>
		<TRANSACAO>3013</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<LOGIN>c_xpot</LOGIN>
		<IP>123.123.123.1</IP>
		<TEXTO>1171010159</TEXTO>
		<CODIGO>25</CODIGO>
		<ACAO>I-Inclui A-Altera E-</ACAO>
		<DATA></DATA>
		<ORIGEM>se alteracao</ORIGEM>
		<DESTINO>se alteracao</DESTINO>
		<BKO></BKO>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_SET_FAVORITO;
	int i_CODIGO = 25;
	char vc_ACAO[2];
	char* pc_TEXTO = NULL;
	char* pc_ORIGEM = NULL;
	char* pc_DESTINO = NULL;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


	// Inclusao do numero favorito
	if ((*pc_numeroNovo != '\0') && (*pc_numeroAntigo == '\0'))
	{
		if (! (iIsValid = Util::isValidLine(pc_numeroNovo)))
		{
			if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
			
			XMLString::release(&pc_numeroNovo);

			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ADD_FAVOR);
		}
		else if(iIsValid < 0)
		{
			if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
			
			XMLString::release(&pc_numeroNovo);

			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);
		}


		strcpy(vc_ACAO, "I");

		pc_TEXTO = pc_numeroNovo;
	}

	// Alteracao do numero favorito
	if ((*pc_numeroNovo != '\0') && (*pc_numeroAntigo != '\0'))
	{
		if (! (iIsValid = Util::isValidLine(pc_numeroNovo)))
		{
			if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
			
			XMLString::release(&pc_numeroNovo);
			XMLString::release(&pc_numeroAntigo);

			throw new TuxBasicSvcException(ECD_INVALID_LINE, EMSG_INVALID_LINE_ALTER_FAVOR);
		}
		else if(iIsValid < 0)
		{
			if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
			
			XMLString::release(&pc_numeroNovo);
			XMLString::release(&pc_numeroAntigo);

			throw new TuxBasicSvcException(ECD_DISABLED_LINE, EMSG_DISABLED_LINE_ADD_FAVOR);
		}


		strcpy(vc_ACAO, "A");

		pc_ORIGEM = pc_numeroAntigo;
		pc_DESTINO = pc_numeroNovo;
	}

	// Exclusao do numero favorito
	if ((*pc_numeroNovo == '\0') && (*pc_numeroAntigo != '\0'))
	{
		strcpy(vc_ACAO, "E");

		pc_TEXTO = pc_numeroAntigo;
	}


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LOGIN, TAG_XML_PPS_IN_VAL_LOGIN);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_IP, TAG_XML_PPS_IN_VAL_IP);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TEXTO, (pc_TEXTO == NULL)? "" : pc_TEXTO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CODIGO, i_CODIGO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_ACAO, vc_ACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DATA, "");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_ORIGEM, (pc_ORIGEM == NULL)? "" : pc_ORIGEM);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DESTINO, (pc_DESTINO == NULL)? "" : pc_DESTINO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_BKO, "");
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL)      XMLString::release(&pc_CELULAR);
	if (pc_numeroNovo != NULL)   XMLString::release(&pc_numeroNovo);
	if (pc_numeroAntigo != NULL) XMLString::release(&pc_numeroAntigo);

	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS, &o_XMLtoGW, NULL);

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

	<FavoritoVO>
	</FavoritoVO>
*/

	xml_g->createTag(TAG_XML_OUT_FAVORITO_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			XMLString::release(&pc_xmlns);
		}

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
