#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstring>

using namespace std;


void PlugInATLYS::setSuspendeCelular()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyLinha></ProxyLinha>
			<ProxyOperacao>setSuspendeCelular</ProxyOperacao>
			<operacao></operacao>
			<xmlns></xmlns>
		</msgBody>
	</msg>
*/

	char    *pcOperacao   = NULL;
	char    *pcXmlns      = NULL;
	DOMNode *poResultNode = NULL;
	char*	pcMotivo	  = NULL;

	// Chamada para obter o valor da tag '<operacao>'
	pcOperacao = tuxhp->walkTree(dnode, TAG_XML_IN_OPERACAO, 0);
	pcMotivo = tuxhp->walkTree(dnode, TAG_XML_IN_MOTIVO, 0);

	// Verificacao da tag '<operacao>'
	if (pcOperacao == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_OPERACAO, EMSG_ATL_TNE(TAG_XML_IN_OPERACAO));
	else if (*pcOperacao == '\0')
		throw new TuxBasicSvcException(ECD_ATL_TVV_OPERACAO, EMSG_ATL_TVV(TAG_XML_IN_OPERACAO));
	else if (! Util::isNum(pcOperacao))
		throw new TuxBasicSvcException(ECD_ATL_TVI_OPERACAO, EMSG_ATL_TVI(TAG_XML_IN_OPERACAO));

	// Verificacao da tag '<motivo>'
	if ((pcMotivo == NULL) || (pcMotivo == '\0') || (! Util::isNum(pcMotivo)))
		pcMotivo = "3";


	// Monta XML de entrada para a chamada da API.
	XMLGen  oXMLInAtlys;

	switch(atoi(pcOperacao)) {
		case 0:
			/*	FO -> GW (desbloquear - 0)

				<msg>
					<msgHdr>
						<user>2</user>
						<service>inputRemoveSubscriptionHotline</service>
					</msgHdr>
					<msgBody>
						<inputRemoveSubscriptionHotline sbscrpId="0034439254" />
					</msgBody>
				</msg>
			*/

			oXMLInAtlys.createTag(XML_ATL_INPUT_REMOVE_SUBSCRIPTION_HOTLINE);
			oXMLInAtlys.addProp(XML_ATL_ACCT_SBSCRID, getIdLinhaSistemaOrigem());

			// Chama a API para trazer as informações de histórico de atendimento.
			poResultNode = callRemoteAPI(this->getServiceName(), &oXMLInAtlys, XML_ATL_INPUT_REMOVE_SUBSCRIPTION_HOTLINE);

			break;
		case 1:
			/*	FO -> GW (bloquear - 1)

				<msg>
					<msgHdr>
						<user>2</user>
						<service>inputHotlineSubscription</service>
					</msgHdr>
					<msgBody>
						<inputHotlineSubscription sbscrpId="0034439254" reasonCd="" />
					</msgBody>
				</msg>
			*/

			oXMLInAtlys.createTag(XML_ATL_INPUT_HOTLINE_SUBSCRIPTION);
			oXMLInAtlys.addProp(XML_ATL_ACCT_SBSCRID, getIdLinhaSistemaOrigem());

			if (atoi(pcMotivo) == 25)
				oXMLInAtlys.addProp("reasonCd", "ROUB");
			else
				oXMLInAtlys.addProp("reasonCd", "PERDA");

			// Chama a API para trazer as informações de histórico de atendimento.
			poResultNode = callRemoteAPI(this->getServiceName(), &oXMLInAtlys, XML_ATL_INPUT_HOTLINE_SUBSCRIPTION);

			break;
		default:
			throw new TuxBasicSvcException(ECD_ATL_TVI_OPERACAO, EMSG_ATL_TVI(TAG_XML_IN_OPERACAO));
	}

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (poResultNode == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);


	// tratamento de erro

	// Verifica se existe o DOM 'fault'
	DOMNode* po_fault = NULL;

	// DOM fault
	po_fault = tuxhp->walkDOM(poResultNode, XML_ATL_FAULT, 0);
	
	if(po_fault != NULL)
	{
		tuxfw_getlogger()->debug("Tratando Erro");

		char* pc_errCode = tuxhp->walkAttr(po_fault, XML_ATL_FAULT, XML_ATL_ERR_CD, 0);
		char* pc_msg     = tuxhp->walkAttr(po_fault, XML_ATL_FAULT, XML_ATL_MSG, 0);
		int   i_errCode  = atoi(pc_errCode);

		if(i_errCode == 1277)
			throw new TuxBasicSvcException("24E1277",pc_msg);
	}


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

	pcXmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

	if ((pcXmlns != NULL) && (*pcXmlns != '\0'))
		xml_g->addProp(PROP_XML_OUT_XMLNS, pcXmlns);

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

	// Comunicar Usuario
	this->comunicarUsuario();

}
