#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getAvailableServices()
{	 
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getDetalhesSaldo</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

//	char* pc_idLinha = getIdLinhaSistemaOrigem();
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
			<service>mblGetBalance</service>
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

	
	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	//char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	//int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_BALANCE);


	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);
	
	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, 9700, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idUser">idUser_val</attribute>'
    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inUsername", "FO", XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idTrans">idTrans_val</attribute>'
	/* sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);  */

	// TAG '<attribute name="// TAG '<attribute name="idLinha">inCellNumber</attribute>'">inCellNumber</attribute>'
    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inCellNumber", pc_Linha, XML_NGN_IN_ATTRIBUTE);	

    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inProfile", "VIVOTODAGSM", XML_NGN_IN_ATTRIBUTE);	

    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inOperation", "14", XML_NGN_IN_ATTRIBUTE);

    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inReason", "19", XML_NGN_IN_ATTRIBUTE);

    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inServiceType", "DATA", XML_NGN_IN_ATTRIBUTE);
    
    sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inCostType", "1", XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);
    
	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, "GetAvailableServices");

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime" value="Tempo de resposta local"/>
				
				<field name="outBalanceType1" value="Tipo de saldo"/>
				<field name="outBalanceDesc1" value="Descrição do Saldo"/>
				<field name="outBalance1"     value="Valor do Saldo"/>
				<field name="outValidDate1"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType2" value="Tipo de saldo"/>
				<field name="outBalanceDesc2" value="Descrição do Saldo"/>
				<field name="outBalance2"     value="Valor do Saldo"/>
				<field name="outValidDate2"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType3" value="Tipo de saldo"/>
				<field name="outBalanceDesc3" value="Descrição do Saldo"/>
				<field name="outBalance3"     value="Valor do Saldo"/>
				<field name="outValidDate3"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType4" value="Tipo de saldo"/>
				<field name="outBalanceDesc4" value="Descrição do Saldo"/>
				<field name="outBalance4"     value="Valor do Saldo"/>
				<field name="outValidDate4"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType5" value="Tipo de saldo"/>
				<field name="outBalanceDesc5" value="Descrição do Saldo"/>
				<field name="outBalance5"     value="Valor do Saldo"/>
				<field name="outValidDate5"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType6" value="Tipo de saldo"/>
				<field name="outBalanceDesc6" value="Descrição do Saldo"/>
				<field name="outBalance6"     value="Valor do Saldo"/>
				<field name="outValidDate6"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType7" value="Tipo de saldo"/>
				<field name="outBalanceDesc7" value="Descrição do Saldo"/>
				<field name="outBalance7"     value="PP1"/>
				<field name="outValidDate7"   value="Data de Validade do Saldo"/>
				
				<field name="outBalanceType8" value="Tipo de saldo"/>
				<field name="outBalanceDesc8" value="Descrição do Saldo"/>
				<field name="outBalance8"     value="Valor do Saldo"/>
				<field name="outValidDate8"   value="Data de Validade do Saldo"/>
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
			<DetalhesSaldoVO>
				<cdSeguranca></cdSeguranca>
				<estadoLinha></estadoLinha>
				<prValidadeReal></prValidadeReal>

				<DetalhesSaldoItem>
					<prValidade>Valor de outValidDate[N]</prValidade>
					     <saldo>Valor de outBalance[N]</saldo>
					 <tipoSaldo>Valor de outBalanceDesc[N]</tipoSaldo>
				</DetalhesSaldoItem>
			</DetalhesSaldoVO>
		</msgBody>
	</msg>
*/

	// Objeto para armazenar os itens 'record'
	DOMNode *po_record = NULL;

	// Verifica a tag 'record'
	po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, 0);

	if (po_record == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RECORD, EMSG_NGN_OUT_TNE_RECORD);


	xml_g->createTag("ServicosDisponiveisVO");

        char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

        if (pc_xmlns != NULL)
        {
            xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
        
            XMLString::release(&pc_xmlns);
        }

        char cValue[256];		
        char *chrptr;
        char cFieldValue[256] = "";
        char *cptrFieldName;
        char c_outServiceState[256] = "";
        char c_outServiceId[256] = "";
        char c_outServiceDescription[256] = "";
        char c_outExpDate[256] = "";

        int i = 0;
        int j = 0;
        int k =0;

        tuxfw_getlogger()->debug("antes do while");

        while((po_record= tuxhp->walkDOM(fromGWNGIN, "record", i++))!=NULL)
        {
            j = k = 0;
            while(cptrFieldName = tuxhp->walkAttr(po_record, "field", "name", j))
            {

                strcpy(cFieldValue, tuxhp->walkAttr(po_record, "field", "value", j));	
                tuxfw_getlogger()->debug("idx              : [%d]", j );
                //tuxfw_getlogger()->debug("cptrFieldName    : [%s]", cptrFieldName );
                //tuxfw_getlogger()->debug("cFieldValue      : [%s]", cFieldValue );
                //tuxfw_getlogger()->debug("c_outServiceState: [%s]", c_outServiceState );
                j++;
                
                if (strcmp(cptrFieldName,"outServiceState") == 0)
                {
                    strcpy(c_outServiceState, cFieldValue);
                    tuxfw_getlogger()->debug("c_outServiceState: %s", c_outServiceState );
                }

                if (strcmp(cptrFieldName,"outServiceId") == 0)
                {
                    strcpy(c_outServiceId, cFieldValue);
                    //xml_g->addItem("outServiceId", c_outServiceId );
                    tuxfw_getlogger()->debug("c_outServiceId: %s", c_outServiceId );
                }
                
                if (strcmp(cptrFieldName,"outServiceDescription") == 0)
                {
                    strcpy(c_outServiceDescription, cFieldValue);
                    tuxfw_getlogger()->debug("c_outServiceDescription: %s", c_outServiceDescription );
                }

                if (strcmp(cptrFieldName,"outExpDate") == 0)
                {
                    strcpy(c_outExpDate, cFieldValue);
                    //xml_g->addItem("outExpDate", c_outExpDate );
                    tuxfw_getlogger()->debug("c_outExpDate: %s", c_outExpDate );
                }

            }
            if ( memcmp(c_outServiceState,"ACT",3) == 0)
            {
                xml_g->createTag("ServicoDisponivelItem");
                    xml_g->addItem("outServiceId", c_outServiceId );
                    xml_g->addItem("outServiceDescription", c_outServiceDescription );
                    xml_g->addItem("outExpDate", c_outExpDate );
                xml_g->closeTag();
            }
        
        }

    xml_g->closeTag();

}
