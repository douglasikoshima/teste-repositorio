#define GETBANNERCPP

#include<tuxfw.h>
#include "../../../negocio/VolNegocio/include/Banner.h"

DECLARE_TUXEDO_SERVICE(GETBANNER);


void implGETBANNER::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	Banner banner;	
	int retorno = 1;
	char* idAreaBanner = walkTree(dnode,"idAreaBanner",0);
	try{
		xml_g->createTag("VOLTAVManterBannerVO");
		xml_g->addProp("xmlns","banner.tav.vivo.com.br");
		banner.getBanners(xml_g,idAreaBanner);
		xml_g->closeTag();
	}
	catch (TuxBasicOraException eboe) 
	{		
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);	
		retorno = 0;
	}		

	XMLString::release(&idAreaBanner);

	if(retorno)
		setStatusCode("00I0000","sucesso");
	else
		setStatusCode("00W0000","problemas ao executar query");
}