#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstring>

using namespace std;


void PlugInATLYS::getPagamentos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getPagamentos</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<idcontasistemaorigem>1</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<dataIni></dataIni>
			<dataFim></dataFim>
		</msgBody>
	</msg>
*/
    
	char* pc_acctNbr = NULL;
	char* pc_fromDt = NULL;
	char* pc_toDt = NULL;
	char  c_date[32];


	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM);

	if (pc_acctNbr == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM);

	tuxfw_getlogger()->debug("IdContaSistemaOrigem: %s", pc_acctNbr);


	// Trata a TAG 'dataIni'
	pc_fromDt = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_INI, 0);

	if (pc_fromDt == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_INI, EMSG_TAG_XML_IN_NE_DATA_INI);
	
	if (*pc_fromDt == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_INI, EMSG_TAG_XML_IN_VV_DATA_INI);


	// Verifica a TAG 'dataFim'
	pc_toDt = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_FIM, 0);

	if (pc_toDt == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_FIM, EMSG_TAG_XML_IN_NE_DATA_FIM);
	
	if (*pc_toDt == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_FIM, EMSG_TAG_XML_IN_VV_DATA_FIM);

/*	FO -> GW

	<inputGetPaymentDetailsByAccount acctNbr="xxx" fromDt="xxx" toDt="xxx"/>
*/

	XMLGen o_XMLtoGetPaymentDetails;
	DOMNode* po_XMLfromGetPaymentDetails = NULL;

	
	o_XMLtoGetPaymentDetails.createTag(XML_ATL_INPUT_GET_PAYMENT_DETAILS_BY_ACCOUNT);
    o_XMLtoGetPaymentDetails.addProp(XML_ATL_ACCT_NBR, pc_acctNbr);
	o_XMLtoGetPaymentDetails.addProp(XML_ATL_FROM_DT, Util::formateDate(c_date, pc_fromDt, DATE_FORMAT_JAVA, DATE_FORMAT_ATL_IN));
	o_XMLtoGetPaymentDetails.addProp(XML_ATL_TO_DT, Util::formateDate(c_date, pc_toDt, DATE_FORMAT_JAVA, DATE_FORMAT_ATL_IN));
	o_XMLtoGetPaymentDetails.closeTag();


    po_XMLfromGetPaymentDetails = callRemoteAPI(this->getServiceName(), &o_XMLtoGetPaymentDetails, XML_ATL_INPUT_GET_PAYMENT_DETAILS_BY_ACCOUNT);

    if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
        throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
    
	if (po_XMLfromGetPaymentDetails == NULL)
        throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	GW -> FO


	<outputGetPaymentDetailsByAccount>
		<pymtDet>
			<pymt pymtID="xxx" acctNbr="xxx" pymtTypeCd="xxx" finTranType="xxx" capCd="xxx" receiveDt="xxx" currencyAmt="xxx" currencyNbr="xxx" amt="xxx" residAmt="xxx" activeAssignCount="xxx" pubId="xxx" userId="xxx"/>
		</pymtDet>
		<pymtDet>
			<pymt pymtID="xxx" acctNbr="xxx" pymtTypeCd="xxx" finTranType="xxx" capCd="xxx" receiveDt="xxx" currencyAmt="xxx" currencyNbr="xxx" amt="xxx" residAmt="xxx" activeAssignCount="xxx" pubId="xxx" userId="xxx"></pymt>
			<pymtReversal revDt="xxx" revTm="xxx" revReasonCd="xxx" finTranType="xxx" capCd="xxx" pubId="xxx"></pymtReversal>
		</pymtDet>
	</outputGetPaymentDetailsByAccount>
*/

	DOMNode* po_outputGetPaymentDetails = NULL;

	po_outputGetPaymentDetails = tuxhp->walkDOM(po_XMLfromGetPaymentDetails, XML_ATL_OUTPUT_GET_PAYMENT_DETAILS_BY_ACCOUNT, 0);

	if (po_outputGetPaymentDetails == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	FO -> JAVA

	<LupaFaturamentoPosVO>
		<LFPagamento>
			<idConta></idConta>
			<dtPagamento></dtPagamento>
			<vlPagamento></vlPagamento>
			<dsTipoPagamento></dsTipoPagamento>
			<dsReferencia></dsReferencia>
			<dsMotivoReversao></dsMotivoReversao>
			<dtReversao></dtReversao>
		</LFPagamento>
	</LupaFaturamentoPosVO>
*/

    xml_g->createTag(TAG_XML_OUT_LUPA_FATURAMENTO_POS_VO);

		// Pega o valor da tag 'xmlns' de entrada do xml
		char* pc_xmlns = NULL;

		pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if(pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);


		DOMNode* po_pymtDet = NULL;

		for (unsigned int i = 0; (po_pymtDet = tuxhp->walkDOM(po_outputGetPaymentDetails, XML_ATL_PYMTDET, i)) != NULL; i++)
		{
			xml_g->createTag(TAG_XML_OUT_LF_PAGAMENTO);

				xml_g->addItem(TAG_XML_OUT_ID_CONTA, pc_acctNbr);

				
				// receiveDt
				char* pc_receiveDt = NULL;
				
				pc_receiveDt = tuxhp->walkAttr(po_pymtDet, XML_ATL_PYMT, XML_ATL_RECEIVE_DT, i);

				if (pc_receiveDt != NULL)
					xml_g->addItem(TAG_XML_OUT_DT_PAGAMENTO, Util::formateDate(c_date, pc_receiveDt, DATE_FORMAT_ATL_OUT, "DD/MM/YYYY"));
				else
					xml_g->addItem(TAG_XML_OUT_DT_PAGAMENTO, "");


				// amt
				char* pc_amt = NULL;
				
				pc_amt = tuxhp->walkAttr(po_pymtDet, XML_ATL_PYMT, XML_ATL_AMT, i);

				if (pc_amt != NULL)
					xml_g->addItem(TAG_XML_OUT_VL_PAGAMENTO, pc_amt);
				else
					xml_g->addItem(TAG_XML_OUT_VL_PAGAMENTO, "");
		

				// pubId
				char* pc_pubId = NULL;

				pc_pubId = tuxhp->walkAttr(po_pymtDet, XML_ATL_PYMT, XML_ATL_PUB_ID, i);
				
				if (pc_pubId != NULL)
					xml_g->addItem(TAG_XML_OUT_DS_REFERENCIA, pc_pubId);
				else
					xml_g->addItem(TAG_XML_OUT_DS_REFERENCIA, "");


				// pymtReversal
				char* pc_pymtReversal = NULL;

				pc_pymtReversal = tuxhp->walkAttr(po_pymtDet, XML_ATL_PYMT, XML_ATL_PYMT_REVERSAL, i);
        
				if (pc_pymtReversal != NULL)
				{
					xml_g->addItem(TAG_XML_OUT_DS_MOTIVO_REVERSAO, pc_pymtReversal);
					xml_g->addItem(TAG_XML_OUT_DT_REVERSAO, pc_pymtReversal);
				}
				else
				{
					xml_g->addItem(TAG_XML_OUT_DS_MOTIVO_REVERSAO, "");
					xml_g->addItem(TAG_XML_OUT_DT_REVERSAO, "");
				}


				// pymtTypeCd
				char *pc_pymtTypeCd = NULL;

				pc_pymtTypeCd = tuxhp->walkAttr(po_pymtDet, XML_ATL_PYMT, XML_ATL_PYMT_CODE, i);
        
				if (pc_pymtTypeCd != NULL)
					xml_g->addItem(TAG_XML_OUT_DS_TIPO_PAGAMENTO, traduzFormaPagto(pc_pymtTypeCd));
				else
					xml_g->addItem(TAG_XML_OUT_DS_TIPO_PAGAMENTO, "");


			xml_g->closeTag();
		}

	xml_g->closeTag();
}
