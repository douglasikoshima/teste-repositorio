#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <cstring>
#include <cstdio>

using namespace std;


void PlugInATLYS::getFormaPagamento()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyLinha></ProxyLinha>
			<ProxyOperacao>getFormaPagamento</ProxyOperacao>
			<idcontasistemaorigem></idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_acctNbr = NULL;

	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);

	if (pc_acctNbr == '\0')
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_ID_CONTA_SIS_ORG_VV);

	tuxfw_getlogger()->debug("IdContaSistemaOrigem: %s", pc_acctNbr);


/*	FO -> GW

	<inputGetPaymentMethod acctNbr="xxx"/>
*/

	XMLGen XMLtoGetPaymentMethod;
	DOMNode* XMLfromGetPaymentMethod = NULL;
	
	XMLtoGetPaymentMethod.createTag(XML_ATL_INPUT_PAYMT_METHOD);
	XMLtoGetPaymentMethod.addProp(XML_ATL_ACCT_NBR, pc_acctNbr);
	XMLtoGetPaymentMethod.closeTag();

	// Faz a chamada remota e verifica o retorno
	XMLfromGetPaymentMethod =  callRemoteAPI(this->getServiceName(), &XMLtoGetPaymentMethod, XML_ATL_INPUT_PAYMT_METHOD);
	
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if	(XMLfromGetPaymentMethod == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	
/* GW -> FO

	<outputGetPaymentMethod  acctNbr="xxx" effDt="xxx" exprDt="xxx" pymtTypeCd="xxx" pymtCategoryCode ="xxx" authCd="xxx" authDt="xxx" rjctnDt="xxx" preNotifyInd="xxx" currencyNbr="xxx"/>
*/
	
	DOMNode* po_outputGetPaymentMethod = NULL;

	po_outputGetPaymentMethod = tuxhp->walkDOM(XMLfromGetPaymentMethod, XML_ATL_OUTPUT_PAYMT_METHOD, 0);

	if (po_outputGetPaymentMethod == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	FO -> JAVA

	<LupaFaturamentoPosVO>
		<LFFormaPagamento>
			<dsFormaPagamento></dsFormaPagamento>
		</LFFormaPagamento>
	</LupaFaturamentoPosVO>
*/

	char* pc_pymtTypeCode = NULL;

	// lendo Atributo "pymtTypeCode" da Tag <outputGetPaymentMethod> API GetPaymentMethod
	pc_pymtTypeCode = tuxhp->getAttrValue(po_outputGetPaymentMethod, XML_ATL_PYMT_TYPE_CODE);


	xml_g->createTag(TAG_XML_OUT_LUPA_FATURAMENTO_POS_VO);

		// Pega o valor da tag 'xmlns' de entrada do xml
		char* pc_xmlns = NULL;

		pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if(pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);


		xml_g->createTag(TAG_XML_OUT_LF_FORMA_PAGAMENTO);

			xml_g->addItem(TAG_XML_OUT_DS_FORMA_PAGAMENTO, traduzFormaPagto(pc_pymtTypeCode));

		xml_g->closeTag();
		
	xml_g->closeTag();
}
