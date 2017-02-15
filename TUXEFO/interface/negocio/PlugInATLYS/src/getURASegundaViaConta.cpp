#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstring>

using namespace std;



void PlugInATLYS::getURASegundaViaConta()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getURASegundaViaConta</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
		</msgBody>
	</msg>
*/
	DOMNode *poResultNode = NULL;
	char* pc_accessNbr = NULL;

	pc_accessNbr = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

/*
	FO -> GW

	<inputReprintInvoiceByAccessNbr accessNbr="xxx"/>
*/

	char idContaSistemaOrigem[256];
	char* pcIdConta = Util::getIdContaSistemaOrigem(idContaSistemaOrigem, pc_accessNbr);
	if (pcIdConta == NULL)
		throw new TuxBasicSvcException(NRO_CONTA_NAO_ENCONTRADA, MSG_CONTA_NAO_ENCONTRADA);

	// Chama a API getBillDates para achar o ctEndCycle
	XMLGen oXMLtoGetInputBillDates;

	oXMLtoGetInputBillDates.createTag(XML_ATL_INPUT_GET_BILL_DATES);
	oXMLtoGetInputBillDates.addProp(XML_ATL_ACCT_NBR, pcIdConta);

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetInputBillDates, XML_ATL_INPUT_GET_BILL_DATES);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') {
                if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_NO_BILL)) {
			xml_g->addItem("statCom",1);
                        xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO,"00");
                        return;
                } else
                        throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
        }
        if (poResultNode == NULL)
                throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	char *cycleEndDt = NULL;
	char *pcCycleEndDt = NULL;
	char *mesAno = NULL;
	int k=0;
	mesAno = tuxhp->walkTree(dnode, "mesRef", 0); // MMYYYY
	if(mesAno != NULL)
	{
		while ((cycleEndDt = tuxhp->walkAttr(poResultNode, "billDt", XML_ALT_CYCLEENDDT, k++)) != NULL) 
		{		
			// cycleEndDt YYYY-MM-DD
			if(strlen(cycleEndDt) > 9 && strlen(mesAno) > 5)
			{		
				// Compara o mês e o ano
				if(mesAno[0]  == cycleEndDt[5] && mesAno[1]  == cycleEndDt[6] &&
					mesAno[2]  == cycleEndDt[0] && mesAno[3]  == cycleEndDt[1] &&
					mesAno[4]  == cycleEndDt[2] && mesAno[5]  == cycleEndDt[3])
				{
					pcCycleEndDt = cycleEndDt;
				}
			}
		}
	}



	XMLGen o_XMLtoReprintInvoice;
	DOMNode* po_XMLfromReprintInvoice = NULL;

	o_XMLtoReprintInvoice.createTag(XML_ATL_INPUT_REPRINT_INVOICE);
	o_XMLtoReprintInvoice.addProp(XML_ATL_ACCT_ACCESSNBR, pc_accessNbr);
	if(pcCycleEndDt != NULL)
	{
		o_XMLtoReprintInvoice.addProp(XML_ALT_CYCLEENDDT, pcCycleEndDt);
	}
	o_XMLtoReprintInvoice.closeTag();
	

	po_XMLfromReprintInvoice = callRemoteAPI(this->getServiceName(), &o_XMLtoReprintInvoice, XML_ATL_INPUT_REPRINT_INVOICE);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromReprintInvoice == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
		
	char *errCd = tuxhp->walkAttr(po_XMLfromReprintInvoice, "fault", "errCd", 0);
	char *msg = tuxhp->walkAttr(po_XMLfromReprintInvoice, "fault", "msg", 0);
	
	char codigoRetorno[512];
	memset(codigoRetorno,0,sizeof(codigoRetorno));
	if (errCd != NULL && strlen(errCd) > 0) {		
		sprintf(codigoRetorno, "%s|%s", errCd, msg);	
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, codigoRetorno);
	}


/*	FO -> JAVA

	<cdCodigoRetorno></cdCodigoRetorno>
*/

	xml_g->addItem("statCom", 1);
	xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO, "00");

	// Registro de contato
	this->registrarContato();

}

