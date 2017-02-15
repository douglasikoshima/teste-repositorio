#define GETPARBANNERCPP

#include<tuxfw.h>
#include "../../../negocio/VolNegocio/include/Banner.h"

DECLARE_TUXEDO_SERVICE(GETPARBANNER);


void implGETPARBANNER::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	Banner banner;	
	int retorno = 1;
	try{
		xml_g->createTag("VOLTAVManterBannerVO");
		xml_g->addProp("xmlns","banner.tav.vivo.com.br");
		xml_g->createTag("BannerListaVO");
		banner.getParametrosBusca(xml_g);
		xml_g->closeTag();
		xml_g->closeTag();
	}
	catch (TuxBasicOraException eboe) 
	{		
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);	
		retorno = 0;
	}		
	if(retorno)
		setStatusCode("00I0000","sucesso");
	else
		setStatusCode("00W0000","problemas ao executar query");
}