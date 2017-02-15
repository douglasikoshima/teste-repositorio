#include "PlugInATLYS.h"

#include <tuxfw.h>

#include <cstdlib>
#include <cstring>

using namespace std;



void PlugInATLYS::getESN()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getESN</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_accessNbr = NULL;

	pc_accessNbr = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	
	XMLGen o_XMLtoGetAccessEquipment;
	DOMNode* po_XMLfromGetAccessEquipment = NULL;

	o_XMLtoGetAccessEquipment.createTag(XML_ATL_INPUT_ACCESSNBR);
	o_XMLtoGetAccessEquipment.addProp(XML_ATL_ACCT_ACCESSNBR, pc_accessNbr);
	o_XMLtoGetAccessEquipment.closeTag();
	
	po_XMLfromGetAccessEquipment = callRemoteAPI(this->getServiceName(), &o_XMLtoGetAccessEquipment, XML_ATL_INPUT_ACCESSNBR);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGetAccessEquipment == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<HexaVO>
				<ESN>12345678</ESN>
			</HexaVO>
		</msgBody>
	</msg>
*/

	xml_g->createTag(TAG_XML_OUT_HEXA_VO);
	
		char* pc_xmlns = NULL;

		pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

		xml_g->addItem(TAG_XML_OUT_ESN, tuxhp->walkAttr(po_XMLfromGetAccessEquipment, XML_ATL_PCSEQPATTR, XML_ATL_ESN, 0));

	xml_g->closeTag();
}
