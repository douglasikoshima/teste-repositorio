#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getDetalhesSaldo()
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

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_BALANCE);


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
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_BALANCE);

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


	xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}

	//	char* pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

		int i_nrDigitoLinha = Util::getDigitoLinha(pc_Linha);

		if(i_nrDigitoLinha >= 0)
			xml_g->addItem(TAG_XML_OUT_CD_SEGURANCA, i_nrDigitoLinha);
		else
			xml_g->addItem(TAG_XML_OUT_CD_SEGURANCA, "");

		XMLString::release(&pc_Linha);

		xml_g->addItem(TAG_XML_OUT_ESTADO_LINHA, "");
		xml_g->addItem(TAG_XML_OUT_PR_VALIDADE_REAL, "");

	char *name = NULL;
		char *n    = NULL;
		char *saldo = NULL;
		int N[8];
		bool isSMS = false;
		double d_SaldoTotalNor = 0;    // Variavel para calculo do SaldoTotal
		double d_SaldoTotalHib = 0;
		double d_SaldoTotal = 0;
		char   vc_date[32];
		char  c_Saldo[16];
		double d_Saldo;
		int i = 0;
		int j = 0, k = 0;
		char chrDescricao[50];
		char chrValue[30];
		char chrDtValidade[30];
		char chrBalanceUnit[30];
		char *chrptr;
		char cFieldValue[256]="";
		char *cptrFieldName;

		
		cptrFieldName = cFieldValue;

		if(strcmp(this->getSgSistemaOrigem(),"NSP")==0 || strcmp(this->getSgSistemaOrigem(),"ATY")==0){
		
			while((po_record= tuxhp->walkDOM(fromGWNGIN, "record", i++))!=NULL) {

				xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_ITEM);
					
				j = k = 0;

				while(cptrFieldName = tuxhp->walkAttr(po_record, "field", "name", j)){
							
					strcpy(cFieldValue, tuxhp->walkAttr(po_record, "field", "value", j));	
								
					if (strcmp(cptrFieldName,"OutBalanceDesc") == 0)
						strcpy(chrDescricao, cFieldValue);	
										
					else if (strcmp(cptrFieldName, "OutBalanceValue") == 0)
						strcpy(chrValue, cFieldValue);
								
					else if (strcmp(cptrFieldName, "OutBalanceDate") == 0)
						strcpy(chrDtValidade, cFieldValue);	

					else if (strcmp(cptrFieldName, "OutBalanceUnit") == 0)
                         {
                            strcpy(chrBalanceUnit, cFieldValue);	
                            xml_g->addItem( "unMedida", cFieldValue );
                         }

					j++;
				}
				
				chrptr = strstr(chrValue, ",");

				if (chrptr != NULL)
					*chrptr = '.';

				d_Saldo = atof(chrValue);	
				
				memset(c_Saldo, '\0', sizeof(c_Saldo));

				sprintf(c_Saldo, "%.2f", d_Saldo);	
				
				if (strstr(chrBalanceUnit,"Reais"))
                {  //se são valores em real somar
		
					d_SaldoTotal += atof(c_Saldo);

					xml_g->addItem(TAG_XML_OUT_SALDO, c_Saldo);
				
				}
                else 
                    xml_g->addItem(TAG_XML_OUT_SALDO, chrValue);

				
				if(strlen(chrDtValidade) > 0)					
					xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, Util::formateDate(vc_date, chrDtValidade, DATE_FORMAT_NGN_OUT_SALDO, DATE_FORMAT_JAVA_ZERO_TIME));
				else
					xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, "");
			

				xml_g->addItem(TAG_XML_OUT_TIPO_SALDO, chrDescricao);	

				xml_g->closeTag();	
		
			}	


			char vc_SaldoTotal[16];
			
			memset(vc_SaldoTotal, '\0', sizeof(vc_SaldoTotal));

			sprintf(vc_SaldoTotal, "%.2f", d_SaldoTotal);

			xml_g->addItem(TAG_XML_OUT_SALDO_TOTAL, vc_SaldoTotal);
			

			//xml_g->closeTag();
			

		}else{ /*********************REGIONAIS DIFERENTE DE SP************************/

			memset(N, '\0', sizeof(N));

			for (int x = 1; x <= 8; x++)
			{
				for (int i = 0; (name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
				{
					n = (name + (strlen(name) - 1));

					if (Util::isNum(n) && (atoi(n) == x))
					{
						if (N[x - 1] == 0)
							xml_g->createTag(TAG_XML_OUT_DETALHES_SALDO_ITEM);

						char temp[16];
						
						strcpy(temp, XML_NGN_FLDNM_VALID_DATE);
						strcat(temp, n);

						if (Util::cmp(name, temp))
						{
							char* pc_prValidade = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

							if(pc_prValidade != NULL && strlen(pc_prValidade) > 0)					
								xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, Util::formateDate(vc_date, pc_prValidade, DATE_FORMAT_NGN_OUT_SALDO, DATE_FORMAT_JAVA_ZERO_TIME));
							else
								xml_g->addItem(TAG_XML_OUT_PR_VALIDADE, "");
						
							if (pc_prValidade != NULL) XMLString::release(&pc_prValidade);
						}

						strcpy(temp, XML_NGN_FLDNM_BALANCE);
						strcat(temp, n);
						
						if (Util::cmp(name, temp))
						{
							saldo = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
							
							d_Saldo = atof(saldo);
							
							memset(c_Saldo, '\0', sizeof(c_Saldo));

							sprintf(c_Saldo, "%.2f", d_Saldo);
							
							xml_g->addItem(TAG_XML_OUT_SALDO, c_Saldo);						
						}

						strcpy(temp, XML_NGN_FLDNM_BALANCE_DESC);
						strcat(temp, n);

						if (Util::cmp(name, temp))
						{
							char *tmp  = NULL;

							tmp = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

							if (strstr(tmp, "SMS") != NULL)
								isSMS = true;
							else
								isSMS = false;

							xml_g->addItem(TAG_XML_OUT_TIPO_SALDO, tmp);

							if (tmp != NULL) XMLString::release(&tmp);
						}

						if (N[x - 1] == 3)
							xml_g->closeTag();
					
						N[x - 1]++;
					}
				
					XMLString::release(&name);
				}
			
				// Soma o saldo no total se não for SMS
				if (! isSMS)
					d_SaldoTotal += atof(saldo);

				if (saldo != NULL) XMLString::release(&saldo);
			}

			// Variavel para armazenamento do SaldoTotal
			char vc_SaldoTotal[16];
			
			memset(vc_SaldoTotal, '\0', sizeof(vc_SaldoTotal));

			sprintf(vc_SaldoTotal, "%.2f", d_SaldoTotal);

			xml_g->addItem(TAG_XML_OUT_SALDO_TOTAL, vc_SaldoTotal);

		// xml_g->closeTag();

	}

	// Registro de contato
	this->registrarContato(xml_g,false);

	xml_g->closeTag();

}
