#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getDetalheAparelho()
{

/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getDetalheAparelho</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* proxyLinha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblGetEquipmentNetworkInfo</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="inUsername">Usuário</atribute>
			<atribute name="inCellNumber">DDD + LINHA</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro	oParametro;

	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	int iNrInSid = oParametro.getNrInSid(proxyLinha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_EQUIPMENT_NET_INFO);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inUsername">idUser_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE,XML_NGN_IN_ATTRIBUTE_NAME, "inUsername", pc_idUser, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inCellNumber">idLinha_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE,XML_NGN_IN_ATTRIBUTE_NAME, "inCellNumber", proxyLinha, XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);


	// Chama o serviço e verifica o retorno
	fromGWNGIN = this->callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_EQUIPMENT_NET_INFO);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outProfile"			value="Perfil do assinante"/>
				<field name="outMSISDN"				value="MSISDN do Celular"/>
				<field name="outICCID"				value="ICCID do Cartão"/>
				<field name="outESNIMEI"			value="ESN do celular em caso de ser um celular CDMA/TDMA/AMPS e IMEI no caso de ser um celular GSM."/>
				<field name="outCellModelCode"      value="Código do Modelo do Celular"/>
				<field name="outCellModel"			value="Descrição do Modelo do Celular"/>
				<field name="outCellManufacturer"   value="Descrição da Marca do Aparelho"/>
				<field name="outActiveDate"			value="Data de ativação"/>
				<field name="outDealer"				value="Dealer"/>
				<field name="outStatus"				value="Estado da Conta Privada do Assinante"/>
			</record>
		</body>
	</response>
*/

	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<DetalheLinhaAparelhoVO>
				<perfil></perfil>
				<MSISDN></MSISDN>
				<ICCID></ICCID>
				<ESNIMEI></ESNIMEI>
				<codigoModelo></codigoModelo>
				<descricaoModelo></descricaoModelo>
				<descricaoMarca></descricaoMarca>
				<dtAtivacao></dtAtivacao>
				<dealer></dealer>
				<estadoConta></estadoConta>
			</DetalheLinhaAparelhoVO>
		</msgBody>
	</msg>
*/

	// Objeto para armazenar os itens 'record'
	DOMNode *po_record = NULL;

	// Verifica a tag 'record'
	po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, 0);
	
	if (po_record == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RECORD, EMSG_NGN_OUT_TNE_RECORD);


	xml_g->createTag("DetalheLinhaAparelhoVO");
	
		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}

		char *pc_field_name = NULL;

		for (unsigned int i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
		{

			if ((Util::cmp(pc_field_name, "outProfile")) || (Util::cmp(pc_field_name, "OutProfile")))
			{				
				xml_g->addItem("perfil", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outICCID")) || (Util::cmp(pc_field_name, "OutICCID")))
			{
				xml_g->addItem("ICCID", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outMSISDN")) || (Util::cmp(pc_field_name, "OutMSISDN")))
			{
				xml_g->addItem("MSISDN", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outESNIMEI")) || (Util::cmp(pc_field_name, "OutESNIMEI")))
			{
				char  c_ESN[32] = "";

				char* pc_outESN = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

                if(strlen(pc_outESN) == 0)
                {
    				xml_g->addItem("ESNIMEI", "");
                }
                else
                {
    				if(strlen(pc_outESN) == 15)
    				{
    					xml_g->addItem("ESNIMEI", pc_outESN);
    				}
    				else
    				{
    					this->convertESNDecToHex(c_ESN, pc_outESN);
    					xml_g->addItem("ESNIMEI", c_ESN);
    				}
                }
				if (pc_outESN != NULL) XMLString::release(&pc_outESN);

			}
			else
			if ((Util::cmp(pc_field_name, "outCellModelCode")) || (Util::cmp(pc_field_name, "OutCellModelCode")))
			{
				xml_g->addItem("codigoModelo", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outCellModel")) || (Util::cmp(pc_field_name, "OutCellModel")))
			{
				xml_g->addItem("descricaoModelo", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outCellManufacturer")) || (Util::cmp(pc_field_name, "OutCellManufacturer")))
			{
				xml_g->addItem("descricaoMarca", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outActiveDate")) || (Util::cmp(pc_field_name, "OutActiveDate")))
			{
				xml_g->addItem("dtAtivacao", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outDealer")) || (Util::cmp(pc_field_name, "OutDealer")))
			{
				xml_g->addItem("dealer", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}
			else
			if ((Util::cmp(pc_field_name, "outStatus")) || (Util::cmp(pc_field_name, "OutStatus")))
			{
				xml_g->addItem("estadoConta", tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i));
			}

			XMLString::release(&pc_field_name);
		}

	xml_g->closeTag();
}
