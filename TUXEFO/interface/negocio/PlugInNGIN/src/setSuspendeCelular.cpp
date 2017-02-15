#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::setSuspendeCelular()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setSuspendeCelular</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>

			<usuario></usuario>
			<operacao></operacao>
			<motivo></motivo>
			<observacao></observacao>
			<numeroSerial></numeroSerial>
			<cidade></cidade>
			<BONumero></BONumero>
			<BOData></BOData>
			<BODelegacia></BODelegacia>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_idOper = NULL;
	char* pc_inReasonCode = NULL;
	char* pc_inObs = NULL;
	char* pc_inSerialNbr = NULL;
	char* pc_inCityName = NULL;
	char* pc_inPoliceReportNbr = NULL;
	char* pc_inPoliceReportDate = NULL;
	char* pc_inPoliceStation = NULL;


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);


	// Trata a TAG 'operacao'
	pc_idOper = tuxhp->walkTree(dnode, TAG_XML_IN_OPERACAO, 0);

	if (pc_idOper == NULL)
	{
		XMLString::release(&pc_idUser);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_OPERACAO, EMSG_TAG_XML_IN_NE_OPERACAO);
	}

	if (*pc_idOper == '\0')
	{
		XMLString::release(&pc_idUser);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_OPERACAO, EMSG_TAG_XML_IN_VV_OPERACAO);
	}

	if (! Util::isNum(pc_idOper))
	{
		XMLString::release(&pc_idUser);
		XMLString::release(&pc_idOper);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_OPERACAO, EMSG_TAG_XML_IN_VI_OPERACAO);
	}


	// Verifica a TAG 'motivo'
	pc_inReasonCode = tuxhp->walkTree(dnode, TAG_XML_IN_MOTIVO, 0);

	/*
	if (pc_inReasonCode == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_MOTIVO, EMSG_TAG_XML_IN_NE_MOTIVO);
	
	if (! *pc_inReasonCode)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_MOTIVO, EMSG_TAG_XML_IN_VV_MOTIVO);
	
	if (! Util::isNum(pc_inReasonCode))
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_MOTIVO, EMSG_TAG_XML_IN_VI_MOTIVO);
	*/

	if ((pc_inReasonCode == NULL) || (*pc_inReasonCode == '\0'))
		pc_inReasonCode = "3";


	// Trata a TAG 'observacao'
	pc_inObs = tuxhp->walkTree(dnode, TAG_XML_IN_OBSERVACAO, 0);

	/*
	if (pc_inObs == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_OBSERVACAO, EMSG_TAG_XML_IN_NE_OBSERVACAO);
	
	if (*pc_inObs == '\0)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_OBSERVACAO, EMSG_TAG_XML_IN_VV_OBSERVACAO);
	*/


	// Trata a TAG 'numeroSerial'
	pc_inSerialNbr = tuxhp->walkTree(dnode, TAG_XML_IN_NUMERO_SERIAL, 0);

	/*
	if (pc_inSerialNbr == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NUMERO_SERIAL, EMSG_TAG_XML_IN_NE_NUMERO_SERIAL);
	
	if (*pc_inSerialNbr == '\0)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_NUMERO_SERIAL, EMSG_TAG_XML_IN_VV_NUMERO_SERIAL);
	*/


	// Trata a TAG 'cidade'
	pc_inCityName = tuxhp->walkTree(dnode, TAG_XML_IN_CIDADE, 0);

	/*
	if (pc_inCityName == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_CIDADE, EMSG_TAG_XML_IN_NE_CIDADE);

	if (*pc_inCityName == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_CIDADE, EMSG_TAG_XML_IN_VV_CIDADE);
	*/


	// Trata a TAG 'BONumero'
	pc_inPoliceReportNbr = tuxhp->walkTree(dnode, TAG_XML_IN_BO_NUMERO, 0);

	/*
	if (pc_inPoliceReportNbr == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_BO_NUMERO, EMSG_TAG_XML_IN_NE_BO_NUMERO);

	if (*pc_inPoliceReportNbr == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_BO_NUMERO, EMSG_TAG_XML_IN_VV_BO_NUMERO);
	*/


	// Trata a TAG 'BOData'
	pc_inPoliceReportDate = tuxhp->walkTree(dnode, TAG_XML_IN_BO_DATA, 0);

	/*
	if (pc_inPoliceReportDate == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_BO_DATA, EMSG_TAG_XML_IN_NE_BO_DATA);

	if (*pc_inPoliceReportDate == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_BO_DATA, EMSG_TAG_XML_IN_VV_BO_DATA);
	*/


	// Trata a TAG 'BODelegacia'
	pc_inPoliceStation = tuxhp->walkTree(dnode, TAG_XML_IN_BO_DELEGACIA, 0);

	/*
	if (pc_inPoliceStation == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_BO_DELEGACIA, EMSG_TAG_XML_IN_NE_BO_DELEGACIA);

	if (*pc_inPoliceStation == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_BO_DELEGACIA, EMSG_TAG_XML_IN_VV_BO_DELEGACIA);
	*/

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblChangeSuspendMobile</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
			<atribute name="idOper">Código da Operação</atribute>
			<atribute name="inReasonCode">Código do motivo de suspensão</atribute>
			<atribute name="inObs">Observações</atribute>
			<atribute name="inSerialNbr">Numero serial do celular da Nota Fiscal</atribute>
			<atribute name="inCityName">Cidade da ocorrência</atribute>
			<atribute name="inPoliceReportNbr">Boletim de ocorrência - no caso de roubo</atribute>
			<atribute name="inPoliceReportDate">Data do Boletim de Ocorrência</atribute>
			<atribute name="inPoliceStation">Delegacia - no caso de roubo</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_SET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	char vc_date[32];
	CParametro oParametro;

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);	

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);


	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_CHANGE_SUSP_MOBILE);

	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idUser">idUser_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_USER, pc_idUser, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idTrans">idTrans_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idLinha">idLinha_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_LINHA, pc_idLinha, XML_NGN_IN_ATTRIBUTE);
	
	// TAG '<attribute name="idOper">idOper_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_OPER, pc_idOper, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inReasonCode">inReasonCode_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_REASON_CD, pc_inReasonCode, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inObs">inObs_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_OBS, pc_inObs, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inSerialNbr">inSerialNbr_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SERIAL_NR, pc_inSerialNbr, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inCityName">inCityName_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_CITY_NM, pc_inCityName, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inPoliceReportNbr">inPoliceReportNbr_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_POLICE_REPORT_NR, pc_inPoliceReportNbr, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inPoliceReportDate">inPoliceReportDate_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_POLICE_REPORT_DT, Util::formateDate(vc_date, pc_inPoliceReportDate, DATE_FORMAT_JAVA, DATE_FORMAT_NGN_IN), XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inPoliceStation">inPoliceStation_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_POLICE_STATION, pc_inPoliceStation, XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);
	XMLString::release(&pc_idOper);
	
	if (pc_inReasonCode != NULL)       XMLString::release(&pc_inReasonCode);
	if (pc_inObs != NULL)              XMLString::release(&pc_inObs);
	if (pc_inSerialNbr != NULL)        XMLString::release(&pc_inSerialNbr);
	if (pc_inCityName != NULL)         XMLString::release(&pc_inCityName);
	if (pc_inPoliceReportNbr != NULL)  XMLString::release(&pc_inPoliceReportNbr);
	if (pc_inPoliceReportDate != NULL) XMLString::release(&pc_inPoliceReportDate);
	if (pc_inPoliceStation != NULL)    XMLString::release(&pc_inPoliceStation);


	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_CHANGE_SUSP_MOBILE);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime"     value="Tempo de resposta local"/>
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
			<SuspendeCelularVO>
			</SuspendeCelularVO>
		</msgBody>
	</msg>
*/

	xml_g->createTag(TAG_XML_OUT_SUSPENDE_CELULAR_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			XMLString::release(&pc_xmlns);
		}
	
	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

	// Comunicar Usuario
	this->comunicarUsuario();

}
