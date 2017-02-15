#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getServicos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getServicos</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
    char* pc_idUser = NULL;


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
			<service>mblGetServices</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;


	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);
	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_SERVICES);

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

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);


	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_SERVICES);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime"       value="Tempo de resposta local"/>
				<field name="outName"               value="Nome do Serviço"/>
				<field name="outCode"               value="Código do Serviço"/>
				<field name="outServiceDescription" value="Descrição do Serviço"/>
				<field name="outStatus"             value="Descrição do Estado"/>
				<field name="outValidPeriod"        value="Data de validade do serviço"/>
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
	this->trataErroURA(po_response);

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
			<ServicosVO>
				<ServicosItem>
					     <nome>Valor de outName</nome>
					   <codigo>Valor de outCode</codigo>
					<descricao>Valor de outServiceDescription</descricao>
				  	   <status>Valor de outStatus</status>
					 <validade>Valor de outValidPeriod</validade>
				</ServicosItem>
			</ServicosVO>
		</msgBody>
	</msg>
*/

	xml_g->createTag(TAG_XML_OUT_SERVICOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}


		DOMNode *po_record = NULL;
		char    vc_date[32];

		char *pc_field_name = NULL;

		char *pc_outCodeCMP = NULL;
		char *pc_outStatusCMP = NULL;
		char *pc_outValidPeriodCMP = NULL;
		char cValidPeriodCMPBloqueioDDI[10+1]="";
		char cValidPeriodCMPBloqueioDDD[10+1]="";
		int	 iServicoValido=1;
		int  iAddServicoBloqueioDDI=1;
		int  iAddServicoBloqueioDDD=1;

		for (unsigned int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
		{
			char* pc_responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

			if (Util::cmp(pc_responseTM, XML_NGN_FLDNM_RESPONSE_TM))
			{
				if (pc_responseTM != NULL) XMLString::release(&pc_responseTM);
				
				continue;
			}

			if (pc_responseTM != NULL) XMLString::release(&pc_responseTM);


			for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
			{
				if (Util::cmp(pc_field_name, XML_NGN_FLDNM_CODE))			
					pc_outCodeCMP = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_STATUS))			
					pc_outStatusCMP = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_VALID_PERIOD))	
					pc_outValidPeriodCMP = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

				XMLString::release(&pc_field_name);
			}

			iServicoValido = 1;
			//Interte o status para os servicos Bloqueio DDD e DDI
			if (Util::cmp(pc_outCodeCMP, COD_BLOQUEIO_DDI)){
				if (Util::cmp(getServicoStatus(pc_outStatusCMP), "Ativado")){
					iAddServicoBloqueioDDI = 0;
					iServicoValido = 0;
					strcpy(cValidPeriodCMPBloqueioDDI , pc_outValidPeriodCMP);
				}
			}
			else if (Util::cmp(pc_outCodeCMP, COD_BLOQUEIO_DDD)){
				if (Util::cmp(getServicoStatus(pc_outStatusCMP), "Ativado")){
					iAddServicoBloqueioDDD = 0;
					iServicoValido = 0;
					strcpy(cValidPeriodCMPBloqueioDDD , pc_outValidPeriodCMP);
				}
			}
			//Quando o serviço for diferente de bloqueio DDI e DDD e o status estiver diferente de Ativado, nao retornar o servico.
			else if (! Util::cmp(getServicoStatus(pc_outStatusCMP), "Ativado")){
				iServicoValido = 0;
			}
			//tuxfw_getlogger()->debug("servico: %s  idServicoValido: %i  Status: %s", pc_outCodeCMP, iServicoValido, getServicoStatus(pc_outStatusCMP)); 


			if (iServicoValido == 1)
			{
				//tuxfw_getlogger()->debug("code :%s Param:%s", pc_outCodeCMP, COD_CAIXA_POSTAL); 
				//Considera apenas os servicos abaixo.
				if  ( (Util::cmp(pc_outCodeCMP, COD_ANTIBINA)) || 
					  (Util::cmp(pc_outCodeCMP, COD_CAIXA_POSTAL)) || 
					  (Util::cmp(pc_outCodeCMP, COD_IDENTIFICADOR)) || 
					  (Util::cmp(pc_outCodeCMP, COD_BLOQUEIO_DDI)) || 
					  (Util::cmp(pc_outCodeCMP, COD_BLOQUEIO_DDD)) || 
					  (Util::cmp(pc_outCodeCMP, COD_CHAMADA_ESPERA)) || 
					  (Util::cmp(pc_outCodeCMP, COD_TRANSF_CHAMADAS)) || 
					  (Util::cmp(pc_outCodeCMP, COD_WAP)))
				{

					xml_g->createTag(TAG_XML_OUT_SERVICOS_ITEM);


					for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
					{

					/* **Considerar a tag XML_NGN_FLDNM_SERVICE_DESC como Nome do serviço.**
						if (Util::cmp(pc_field_name, XML_NGN_FLDNM_NAME))
						{
							char* pc_outName = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

							xml_g->addItem(TAG_XML_OUT_NOME, traduzNomeToDescricao(pc_outName));

							if (pc_outName != NULL) XMLString::release(&pc_outName);
						}
					*/

														
						if (Util::cmp(pc_field_name, XML_NGN_FLDNM_CODE))			
						{
							char* pc_outCode = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

							xml_g->addItem(TAG_XML_OUT_CODIGO, (pc_outCode != NULL)? pc_outCode : "");
															
							if (pc_outCode != NULL) XMLString::release(&pc_outCode);
						}

						else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_SERVICE_DESC))			
						{
							char outServiceDescription[255];
							outServiceDescription[0]=0;
							char* pc_outServiceDescription = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

							// Altera a descricao do servico
							if (Util::cmp(pc_outCodeCMP, COD_CAIXA_POSTAL))
								strcpy(outServiceDescription, DES_CAIXA_POSTAL);
							else if (Util::cmp(pc_outCodeCMP, COD_IDENTIFICADOR))
								strcpy(outServiceDescription, DES_IDENTIFICADOR);
							else if (Util::cmp(pc_outCodeCMP, COD_BLOQUEIO_DDI))
								strcpy(outServiceDescription, DES_BLOQUEIO_DDI);
							else if (Util::cmp(pc_outCodeCMP, COD_BLOQUEIO_DDD))
								strcpy(outServiceDescription, DES_BLOQUEIO_DDD);
							else if (Util::cmp(pc_outCodeCMP, COD_CHAMADA_ESPERA))
								strcpy(outServiceDescription, DES_CHAMADA_ESPERA);
							else if (Util::cmp(pc_outCodeCMP, COD_TRANSF_CHAMADAS))
								strcpy(outServiceDescription, DES_TRANSF_CHAMADAS);
							else if (Util::cmp(pc_outCodeCMP, COD_WAP))
								strcpy(outServiceDescription, DES_WAP);
							else if (Util::cmp(pc_outCodeCMP, COD_ANTIBINA))
								strcpy(outServiceDescription, DES_ANTIBINA);


							//considerar a descricao do servico na tag Nome.							
							xml_g->addItem(TAG_XML_OUT_NOME, (strcmp(outServiceDescription,"") != 0)? pc_outServiceDescription : 00);

							xml_g->addItem(TAG_XML_OUT_DESCRICAO, (strcmp(outServiceDescription,"") != 0)? pc_outServiceDescription : 00);
														
							if (pc_outServiceDescription != NULL) XMLString::release(&pc_outServiceDescription);
						}

						else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_STATUS))			
						{
							char* pc_outStatus = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

							xml_g->addItem(TAG_XML_OUT_STATUS, getServicoStatus(pc_outStatus));

							if (pc_outStatus != NULL) XMLString::release(&pc_outStatus);
						}

						else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_VALID_PERIOD))			
						{
							char* pc_outValidPeriod = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

							xml_g->addItem(TAG_XML_OUT_VALIDADE, Util::formateDate(vc_date, pc_outValidPeriod, DATE_FORMAT_NGN_OUT, DATE_FORMAT_JAVA_ZERO_TIME));
														
							if (pc_outValidPeriod != NULL) XMLString::release(&pc_outValidPeriod);
						}
													
						XMLString::release(&pc_field_name);
					}

					xml_g->closeTag();
				}
			}
			if (pc_outCodeCMP != NULL) XMLString::release(&pc_outCodeCMP);
			if (pc_outStatusCMP != NULL) XMLString::release(&pc_outStatusCMP);
			if (pc_outValidPeriodCMP != NULL) XMLString::release(&pc_outValidPeriodCMP);
		}


		//Adiciona os servicos com conceito invertido.
		if (iAddServicoBloqueioDDI == 1){
			xml_g->createTag(TAG_XML_OUT_SERVICOS_ITEM);
				xml_g->addItem(TAG_XML_OUT_CODIGO, COD_BLOQUEIO_DDI);
				xml_g->addItem(TAG_XML_OUT_NOME, DES_BLOQUEIO_DDI);
				xml_g->addItem(TAG_XML_OUT_DESCRICAO, DES_BLOQUEIO_DDI);
				xml_g->addItem(TAG_XML_OUT_STATUS, "Ativado");
				xml_g->addItem(TAG_XML_OUT_VALIDADEINICIAL, Util::trim(cValidPeriodCMPBloqueioDDI));
				xml_g->addItem(TAG_XML_OUT_VALIDADE, "");				
			xml_g->closeTag();
		}
		if (iAddServicoBloqueioDDD == 1){
			xml_g->createTag(TAG_XML_OUT_SERVICOS_ITEM);
				xml_g->addItem(TAG_XML_OUT_CODIGO, COD_BLOQUEIO_DDD);
				xml_g->addItem(TAG_XML_OUT_NOME, DES_BLOQUEIO_DDD);
				xml_g->addItem(TAG_XML_OUT_DESCRICAO, DES_BLOQUEIO_DDD);
				xml_g->addItem(TAG_XML_OUT_STATUS, "Ativado");
				xml_g->addItem(TAG_XML_OUT_VALIDADEINICIAL, Util::trim(cValidPeriodCMPBloqueioDDD));
				xml_g->addItem(TAG_XML_OUT_VALIDADE, "");				
			xml_g->closeTag();
		}

	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();


}
