#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getDetalhesSaldo()
{
/*	JAVA -> FO

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getDetalhesSaldo</ProxyOperacao>
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
				<TRANSACAO>1000</TRANSACAO>
				<CELULAR>1171010160</CELULAR>
			</ROOT
		</msgBody>
	</msg
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_DETALHES_SALDO;
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
		<MIMO_CODIGO>99999999</MIMO_CODIGO>
		<MIMO_MSISDN>1171010160</MIMO_MSISDN>
		<CD>06</CD>
		<ESN>E0101010</ESN>
		<MIMO_ACTIVADO>2003-01-01 00:01:10</MIMO_ACTIVADO>
		<MIMO_DESACTIVADO></MIMO_DESACTIVADO>
		<CLIENTE_CODIGO>123234</CLIENTE_CODIGO>
		<ANTERIOR_SITUACAO></ANTERIOR_SITUACAO>
		<POSTERIOR_SITUACAO></POSTERIOR_SITUACAO>
		<MIMO_DIVIDA>0.00</MIMO_DIVIDA>
		<CATEGORIA_DESC>Livre</CATEGORIA_DESC>
		<CODPERFIL_IN_CET>BABY</CODPERFIL_IN_CET>
		<CATEGORIA_DIVIDA>0.00</CATEGORIA_DIVIDA>
		<NOME>PTI Brasil</NOME>
		<OPCAO_CODIGO>1</OPCAO_CODIGO>
		<VALIDADE>2004-12-31</VALIDADE>
		<ESTADO>Activado</ESTADO>
		<SALDO_REAL>154.00</SALDO_REAL>
		<SALDO_ON_NET>4.00</SALDO_ON_NET>
		<SALDO_DIARIO>3.50</SALDO_DIARIO>
		<VALIDADE_REAL>2004-12-31</VALIDADE_REAL>
		<HOST_IN>Vl.Mariana</HOST_IN>
		<PLANTA>PRE</PLANTA>
		<PLATAFORMA_CODIGO>1</PLATAFORMA_CODIGO>
		<MARCA>NOKIA</MARCA>
		<MODELO>6324</MODELO>
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

	<DetalhesSaldoVO>
		<cdSeguranca></cdSeguranca>
		<estadoLinha></estadoLinha>
		<prValidadeReal></prValidadeReal>

		<DetalhesSaldoItem>
			<prValidade></prValidade>
			<saldo></saldo>
			<tipoSaldo></tipoSaldo>
		</DetalhesSaldoItem>
	</DetalhesSaldoVO>
	*/

	xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			XMLString::release(&pc_xmlns);
		}
		
		char* pc_CD            = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_CD, 0);
		char* pc_ESTADO        = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_ESTADO, 0);
		char* pc_VALIDADE_REAL = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_VALIDADE_REAL, 0);
		char* pc_VALIDADE      = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_VALIDADE, 0);
		char* pc_SALDO_DIARIO  = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_SALDO_DIARIO, 0);
		char* pc_SALDO_ON_NET  = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_SALDO_ON_NET, 0);
		char* pc_SALDO_REAL    = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_SALDO_REAL, 0);

		char  vc_validade_real[32] = "";
		char  vc_validade[32] = "";

		Util::formateDate(vc_validade_real, pc_VALIDADE_REAL, DATE_FORMAT_PPS_OUT, DATE_FORMAT_JAVA_ZERO_TIME);
		Util::formateDate(vc_validade, pc_VALIDADE, DATE_FORMAT_PPS_OUT, DATE_FORMAT_JAVA_ZERO_TIME);

		xml_g->addItem(TAG_XML_OUT_CD_SEGURANCA, (pc_CD != NULL)? pc_CD : "");
		xml_g->addItem(TAG_XML_OUT_ESTADO_LINHA, (pc_ESTADO != NULL)? pc_ESTADO : "");
		xml_g->addItem(TAG_XML_OUT_PR_VALIDADE_REAL, vc_validade_real);

		xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_ITEM);
			xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, vc_validade);
			xml_g->addItem(TAG_XML_OUT_SALDO, (pc_SALDO_DIARIO != NULL)? pc_SALDO_DIARIO : "");
			xml_g->addItem(TAG_XML_OUT_TIPO_SALDO, TAG_XML_PPS_OUT_VAL_SALDO_DIARIO);
		xml_g->closeTag();

		xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_ITEM);
			xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, vc_validade);
			xml_g->addItem(TAG_XML_OUT_SALDO, (pc_SALDO_ON_NET != NULL)? pc_SALDO_ON_NET : "");
			xml_g->addItem(TAG_XML_OUT_TIPO_SALDO, TAG_XML_PPS_OUT_VAL_SALDO_ON_NET);
		xml_g->closeTag();

		xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_ITEM);
			xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, vc_validade);
			xml_g->addItem(TAG_XML_OUT_SALDO, (pc_SALDO_REAL != NULL)? pc_SALDO_REAL : "");
			xml_g->addItem(TAG_XML_OUT_TIPO_SALDO, TAG_XML_PPS_OUT_VAL_SALDO_REAL);
		xml_g->closeTag();

		xml_g->addItem(TAG_XML_OUT_SALDO_TOTAL, (pc_SALDO_REAL != NULL)? pc_SALDO_REAL : "");

		
		if (pc_CD != NULL)            XMLString::release(&pc_CD);
		if (pc_ESTADO != NULL)        XMLString::release(&pc_ESTADO);
		if (pc_VALIDADE_REAL != NULL) XMLString::release(&pc_VALIDADE_REAL);
		if (pc_VALIDADE != NULL)      XMLString::release(&pc_VALIDADE);
		if (pc_SALDO_DIARIO != NULL)  XMLString::release(&pc_SALDO_DIARIO);
		if (pc_SALDO_ON_NET != NULL)  XMLString::release(&pc_SALDO_ON_NET);
		if (pc_SALDO_REAL != NULL)    XMLString::release(&pc_SALDO_REAL);

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
