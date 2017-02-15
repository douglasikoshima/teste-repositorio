#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getDetalheFatura()
{
/*	XML de entrada

 	<msg>
 		<msgHdr>
 			<user>1</user>
 			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getDetalheFatura</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/
	CParametro oParametro;
	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_proxyLinha = NULL;
	char* pc_idCanal = NULL;
	char* pc_inInvoiceId = NULL;

	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	pc_inInvoiceId = tuxhp->walkTree(dnode, XML_NGN_ATTNM_IN_INVOICE_ID, 0);

	if(pc_inInvoiceId == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_INVOICEID, EMSG_TAG_XML_IN_INVOICEID);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);

	// recupera a linha 	
	pc_proxyLinha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);
	// Canal
	pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	int iNrInSid = oParametro.getNrInSid(pc_proxyLinha, pc_idCanal, pc_idUser, XML_NGN_GET_POST_PAID_INVOICE_ID_DETAIL);

	// montar XML de entrada 
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;

	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_INVOICE_ID,pc_inInvoiceId, XML_NGN_IN_ATTRIBUTE);
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// liberar tag de user
	XMLString::release(&pc_idUser);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_GET_POST_PAID_INVOICE_ID_DETAIL);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);
	

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

	
	DOMNode *po_record = NULL;
	char inOutInvoiceAccount[255];
	memset(&inOutInvoiceAccount,0,sizeof(inOutInvoiceAccount));

	
/*
<?xml version="1.0" encoding="ISO-8859-1" ?>
<response succeed="yes" timestamp="2006-06-20T13:02:02.976-03:00">
<body>
<record>
<field name="outDescLancamento" value="Franquia da linha 1199660000"/>
<field name="outTipoLancamento" value="Franquia"/>
<field name="outCodLancamento" value="D"/>
</record>
<record>
<field name="outDescLancamento" value="Desconto para linha 1199660000"/>
<field name="outTipoLancamento" value="Desconto"/>
<field name="outCodLancamento" value="C"/>
</record>
<record>
<field name="outDescLancFiscal" value="Franquia da linha 1199660000"/>
<field name="outTipoLancFiscal" value="Franquia"/>
<field name="outCodLancFiscal" value="D"/>
<field name="outQtde" value="1"/>
<field name="outICMS" value="25%"/>
</record>
<record>
<field name="outDescLancFiscal" value="Desconto para linha 1199660000"/>
<field name="outTipoLancFiscal" value="Desconto"/>
<field name="outCodLancFiscal" value="C"/>
<field name="outQtde" value="1"/>
<field name="outICMS" value="25%"/>
</record>
<record>
<field name="outTipoRegistro" value="98732987"/>
<field name="outAccountId" value="09783098"/>
<field name="outDataEmissao" value="10052007"/>
<field name="outMesRef" value="052007"/>
<field name="outDataInicio" value="01042007"/>
<field name="outDataFim" value="30042007"/>
<field name="outVencimento" value="15052007"/>
<field name="outTotalValue" value="99.99"/>
<field name="outMSISDN" value="1199660001"/>
<field name="outDetFatura" value="Franquia;Desconto"/>
<field name="outCodBarrasDV" value="99999999999999999"/>
<field name="OutCodBarras" value="99999999999999999"/>
<field name="outNome" value="José Inácio"/>
<field name="outTipoLogradouro" value="Rua"/>
<field name="outRua" value="Cubatão"/>
<field name="outNumero" value="320"/>
<field name="outComplemento" value="4o andar"/>
<field name="outBairro" value="Paraiso"/>
<field name="outCEP" value="04013001"/>
<field name="outCidade" value="São Paulo"/>
<field name="outEstado" value="SP"/>
<field name="outOperadora" value="VIVO"/>
<field name="outRuaOperadora" value="Av. Chucri Zaidan, 999"/>
<field name="outBairroOperadora" value="Morumbi"/>
<field name="outCEPOperadora" value="04583110"/>
<field name="outInscEstOperadora" value="9999999999999"/>
<field name="outCPNJOperadora" value="9999999999999"/>
<field name="outDocFiscal" value="9999999999999"/>
<field name="outInscEst" value=""/>
INTERFACES ONLINE UIF INVIVO ERDR 41464
GERT018 – 2.0
<field name="outCodDebitoAuto" value="0"/>
<field name="outDebitoAuto" value=""/>
<field name="outNF" value="99999"/>
<field name="outSerieNF" value="99999"/>
<field name="outCFOP" value="99999"/>
<field name="outDescCFOP" value="5.307"/>
<field name="outDetNF" value="Franquia;Desconto"/>
<field name="outICMSAliquota" value="25%"/>
<field name="outICMSCalc" value="99.99"/>
<field name="outICMSValor" value="99.99"/>
<field name="outServIsento" value="NAO"/>
<field name="outFUSTAliquota" value="9%"/>
<field name="outFUSTValor" value="99.99"/>
<field name="outFUNTELAliquota" value="9%"/>
<field name="outFUNTELValor" value="99.99"/>
</record>
</body>
</response>
*/
	// Tags de lançamento
	char *tagsRecord[] = {"outDescLancamento","outTipoLancamento","outCodLancamento","outValorLancamento"};
	// tags de lancamento fiscal
	char *tagsRecordFiscal[] = {"outDescLancFiscal","outTipoLancFiscal","outCodLancFiscal","outQtde","outICMS","outValorLancFiscal","outSeqLancFiscal","outCodServicoLancFiscal"};
	// Tags do cabeçalho
	char *tagsHead[] = {"outTipoRegistro","outAccountId","outDataEmissao","outMesRef","outDataInicio",
	"outDataFim","outVencimento","outTotalValue","outMSISDN","outDetFatura","outCodBarrasDV","outCodBarras",
	"outNome","outTipoLogradouro","outRua","outNumero","outComplemento","outBairro","outCEP","outCidade",
	"outEstado","outOperadora","outRuaOperadora","outBairroOperadora","outCEPOperadora","outInscEstOperadora",
	"outCNPJOperadora","outDocFiscal","outInscEst","outCodDebitoAuto","outDebitoAuto","outNF","outSerieNF",
	"outCFOP","outDescCFOP","outDetNF","outICMSAliquota","outICMSCalc","outICMSValor","outServIsento",
	"outFUSTAliquota","outFUSTValor","outFUNTELAliquota","outFUNTELValor","outValorCred","outValorDeb"};
	int sizeOfRecord = 4;
	int sizeOfRecordFiscal = 8;
	int sizeOfHead = 46;

	xml_g->createTag("DetalheFaturaVO");
	xml_g->addProp("xmlns","tuxproxy.vol.vivo.com.br/vo");
	for (int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
	{
		tuxfw_getlogger()->debug("record");
		bool tagLancamento = false;
		bool tagLancamentoFiscal = false;
		bool tagHead = false;
		char* responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

		if (Util::cmp(responseTM, XML_NGN_FLDNM_RESPONSE_TM) || Util::cmp(responseTM, "OUTRESPONSETIME"))
		{
			if (responseTM != NULL) XMLString::release(&responseTM);
			
			continue;
		}

		if (responseTM != NULL) XMLString::release(&responseTM);


		char *pc_field_name = NULL;
		int lengthRecord = 0;
		/**
		  * Ler registros de lançamento
		  */
		for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
		{
			char* pc_tagValue = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);		
			// ler os dados de lançamento
			for(int k=0;k < sizeOfRecord;k++){
				char*ptag = tagsRecord[k];				
				if(Util::cmp(pc_field_name, ptag))
				{						
					// cria a tag de lancamento
					if(!tagLancamento)
					{
						xml_g->createTag("LancamentoVO");					
						tagLancamento = true;
					}
					xml_g->addItem(ptag,pc_tagValue);	
					lengthRecord++;
				}					
			}
			if(lengthRecord == sizeOfRecord)
			{				
				xml_g->closeTag();	
				tagLancamento = false;
				lengthRecord = 0;
			}
			if (pc_tagValue != NULL) XMLString::release(&pc_tagValue);
		}
		int lengthRecordFiscal = 0;
		/**
		  * Ler registros de lançamento fiscal
		  */
		for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
		{
			char* pc_tagValue = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);	
			for(int k=0;k < sizeOfRecordFiscal;k++){
				char*ptag = tagsRecordFiscal[k];
				if(Util::cmp(pc_field_name, ptag))
				{	
					if(!tagLancamentoFiscal)
					{
						xml_g->createTag("LancamentoFiscalVO");
						tagLancamentoFiscal = true;
					}									
					xml_g->addItem(ptag,pc_tagValue);
					lengthRecordFiscal++;
				}					
			}
			if(lengthRecordFiscal == sizeOfRecordFiscal)
			{				
				xml_g->closeTag();
				tagLancamentoFiscal = false;
				lengthRecordFiscal = 0;				
			}
			if (pc_tagValue != NULL) XMLString::release(&pc_tagValue);
		}
		int lengthHead = 0;
		/**
		  * Ler registros de cabeçalho
		  */
		for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
		{
			char* pc_tagValue = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);	
			for(int k=0;k < sizeOfHead;k++){
				char*ptag = tagsHead[k];
				if(Util::cmp(pc_field_name, ptag))
				{	
					if(!tagHead)
					{
						xml_g->createTag("DetalheFaturaCabecalhoVO");					
						tagHead = true;
					}
					xml_g->addItem(ptag,pc_tagValue);
				}					
			}
			if(lengthHead == sizeOfHead)
			{
				xml_g->closeTag();
				tagHead = false;
				lengthHead = 0;
			}
			if (pc_tagValue != NULL) XMLString::release(&pc_tagValue);
		}	
		XMLString::release(&pc_field_name);
	}
	xml_g->closeTag();

	tuxfw_getlogger()->debug("inOutInvoiceAccount = %s",inOutInvoiceAccount);

}