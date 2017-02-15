#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstring>

using namespace std;


void PlugInATLYS::getDetalhesConsumo()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getDetalhesConsumo</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<idcontasistemaorigem>123</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char  cSbscrpId[256];
	char  cAcctNbr[256];
	char* pc_acctNbr = NULL;
	char* pcTagXmlIn = NULL;

	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL || *pc_acctNbr == '\0')
	{
		tuxfw_getlogger()->debug("TAG idcontasistemaorigem não encontrada");

		char* pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

		if (pc_Linha == NULL || *pc_Linha == '\0')
		{
			tuxfw_getlogger()->debug("TAG ProxyLinha também não encontrada");

			// mensagem em relação ao idcontasistemaorigem
			if (pc_acctNbr == NULL)
				throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM);

			if (*pc_acctNbr == '\0')
				throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM);

		}

		pc_acctNbr = Util::getIdContaSistemaOrigem(cAcctNbr, pc_Linha);

		if(pc_acctNbr == NULL || *pc_acctNbr == '\0')
			throw new TuxBasicSvcException(ECD_ID_CONTA_NOT_FOUND, EMSG_ID_CONTA_NOT_FOUND);

	}

	tuxfw_getlogger()->debug("IdContaSistemaOrigem: %s", pc_acctNbr);

	strcpy(cSbscrpId, this->getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 
	

/*	FO -> GW

	<inputGetUsageDetail acctNbr="" sbscrpId="" billUnbilledInd="U"/>
*/

	XMLGen o_XMLtoGetUsageDetail;
	DOMNode* po_XMLfromGetUsageDetail = NULL;

	o_XMLtoGetUsageDetail.createTag(XML_ATL_INPUT_GET_USAGE_DETAIL);
	o_XMLtoGetUsageDetail.addProp(XML_ATL_ACCT_NBR, pc_acctNbr);
	o_XMLtoGetUsageDetail.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
	o_XMLtoGetUsageDetail.addProp(XML_ATL_BILLUNBILLED, XML_VAL_UNBILLED);
	o_XMLtoGetUsageDetail.closeTag();

	po_XMLfromGetUsageDetail = callRemoteAPI(this->getServiceName(), &o_XMLtoGetUsageDetail, XML_ATL_INPUT_GET_USAGE_DETAIL);

	this->trataErro(po_XMLfromGetUsageDetail);
	
	xml_g->createTag("DetalhesConsumoVO");

	DOMNode* po_usgDtl = NULL;

	for(unsigned int i = 0; (po_usgDtl = tuxhp->walkDOM(po_XMLfromGetUsageDetail, XML_ATL_USG_DTL, i)) != NULL; i++)
	{
		// Obtem o valor de 'usgDtl:eventType'
		char* pc_eventType = tuxhp->getAttrValue(po_usgDtl, XML_ATL_EVENT_TYPE);

		if(pc_eventType == NULL)
			throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_EVENT_TYPE);

		tuxfw_getlogger()->debug("eventType: %s", pc_eventType);

		DOMNode* po_event = tuxhp->walkDOM(po_usgDtl, pc_eventType);

		if(po_event == NULL)
			throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_EVENT);

		// Obtem o valor de 'evento:EVENT_DT'
		char* pc_eventDate = tuxhp->getAttrValue(po_event, XML_ATL_EVENT_DT);

		if(pc_eventDate == NULL)
			throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_EVENT_DT);

		// Obtem o valor de 'evento:CALLNG_ACCESS_NBR'
		char* pc_from = tuxhp->getAttrValue(po_event, XML_ATL_CALLNG_ACCESS_NBR);

		if(pc_from == NULL)
			throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_CALLNG_ACCESS_NBR);

		// Obtem o valor de 'evento:CALLED_ACCESS_NBR'
		char* pc_to = tuxhp->getAttrValue(po_event, XML_ATL_CALLED_ACCESS_NBR);

		if(pc_to == NULL)
			throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_CALLED_ACCESS_NBR);

		DOMNode* po_chg = tuxhp->walkDOM(po_usgDtl, XML_ATL_CHG);

		if(po_chg == NULL)
			throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_CHG);

		xml_g->createTag(TAG_XML_OUT_CONSUMO);

			int i_tipoDados = 0;

			if(Util::cmp(pc_eventType, XML_VAL_DADOS_X) || Util::cmp(pc_eventType, XML_VAL_DADOS_C))
			{
				// Obtem o valor de 'CHG:svcTypeCd'
				char* pc_svcTypeCd = tuxhp->getAttrValue(po_chg, XML_ATL_SVC_TYPE_CD);
				
				if(pc_svcTypeCd == NULL)
					throw new TuxBasicSvcException(ECD_ATL_TNE_GEN, EMSG_ATL_TNE_SVC_TYPE_CD);

				if(Util::cmp(pc_svcTypeCd, XML_VAL_DADOS_DOWNLOAD))
				{
					xml_g->addItem(TAG_XML_OUT_DS_TIPO, DES_DADOS_DOWNLOAD);
					i_tipoDados = DADOS_DOWNLOAD;
				}
				else
				{
					xml_g->addItem(TAG_XML_OUT_DS_TIPO, DES_DADOS);
					i_tipoDados = DADOS;
				}
			}
			else if(Util::cmp(pc_eventType, XML_VAL_VOICE))
			{
				xml_g->addItem(TAG_XML_OUT_DS_TIPO, DES_VOZ);
				i_tipoDados = VOZ;
			}
			else if(Util::cmp(pc_eventType, XML_VAL_SMS))
			{
				xml_g->addItem(TAG_XML_OUT_DS_TIPO, DES_SMS);
				i_tipoDados = SMS;
			}

			char cDate[32];
			xml_g->addItem(TAG_XML_OUT_DATA, Util::formateDate(cDate, pc_eventDate, "YYYY-MM-DD", "DD/MM/YYYY"));
			
			xml_g->addItem(TAG_XML_OUT_ORIGEM, pc_from);

			xml_g->addItem(TAG_XML_OUT_DESTINO, pc_to);
					
			// Obtem o valor de 'CHG:ROUND_UNIT_QTY'
			double d_roundUnitQty = atof(tuxhp->getAttrValue(po_chg, XML_ATL_ROUND_UNIT_QTY));
			
			if(i_tipoDados == DADOS)
			{
				//tirando decimal
				d_roundUnitQty /= 100;

				//gerando KB o valor já está em KB
				//d_roundUnitQty /= 1000;

			}
			else if(i_tipoDados == DADOS_DOWNLOAD)
			{
				//tirando decimal
				d_roundUnitQty /= 100;
			}
			else if(i_tipoDados == VOZ)
			{
				//retornando em minutos
				d_roundUnitQty /= 60;
			}

			char c_unidConsumidas[64];
			
			sprintf(c_unidConsumidas, "%0.2f", d_roundUnitQty);

			xml_g->addItem(TAG_XML_OUT_UNID_CONSUMIDAS, c_unidConsumidas);

			// Obtem o valor de 'CHG:PRICE_AMT'
			char   c_valor[64];
			double d_priceAmt = atof(tuxhp->getAttrValue(po_chg, XML_ATL_PRICE_AMT));

			sprintf(c_valor, "%0.2f", d_priceAmt);

			xml_g->addItem(TAG_XML_OUT_VALOR, c_valor);

		xml_g->closeTag();

	}

	xml_g->closeTag();

/*
	xml_g->createTag("DetalhesConsumoVO");

	if((pcTagXmlIn = tuxhp->walkTree(dnode,TAG_XML_IN_XMLNS, 0)) != NULL)
		xml_g->addProp( PROP_XML_OUT_XMLNS, pcTagXmlIn );
	
		for(int i = 0; i < 10; i++)
		{
			xml_g->createTag("consumo");

				xml_g->addItem("dsTipo",  "Voz");
				xml_g->addItem("data",    "10/03/2005");
				xml_g->addItem("origem",  "1171527088");
				xml_g->addItem("destino", "1131221478");
				xml_g->addItem("unidConsumidas", "3");
				xml_g->addItem("valor", "10.30");

			xml_g->closeTag();
		}


	xml_g->closeTag();
*/

}