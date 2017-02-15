#define SETBANNERCPP

#include<tuxfw.h>
#include "../../../negocio/VolNegocio/include/Banner.h"

DECLARE_TUXEDO_SERVICE(SETBANNER);


void implSETBANNER::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	Banner banner;
	int retorno = 1;
	char*operacao =  walkTree(dnode,"operacao",0);
	char*login = walkTree(dnode,"login",0);
	char*ip = walkTree(dnode,"ip",0);
	if(!strcmp(operacao,"1"))
	{
		DOMNode *GrupoBanner = NULL;
		for(int i=0; (GrupoBanner=walkDOM(dnode,"GrupoBanner",i))!=NULL  ; i++)
		{
			char*idGrupoBanner = walkTree(GrupoBanner,"idGrupoBanner",0);
			char*idBanner = walkTree(GrupoBanner,"idBanner",0);
			char*nmLink = walkTree(GrupoBanner,"nmLink",0);
			try
			{
				banner.associarGrupoBanner(idGrupoBanner,idBanner);
				banner.log(idGrupoBanner,login,ip,"ASSOCIAR");	
				if(nmLink!=NULL)
					banner.updateBanner(idBanner,nmLink);
			}
			catch (TuxBasicOraException eboe) 
			{		
				tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);	
				retorno = 0;
			}	
			XMLString::release(&idGrupoBanner);
			XMLString::release(&idBanner);			
		}
	}
	else
	if(!strcmp(operacao,"2"))
	{
		DOMNode *GrupoBanner = NULL;
		for(int i=0; (GrupoBanner=walkDOM(dnode,"GrupoBanner",i))!=NULL  ; i++)
		{
			char*idGrupoBanner = walkTree(GrupoBanner,"idGrupoBanner",0);
			try
			{
				banner.log(idGrupoBanner,login,ip,"REMOVER");
				banner.removerGrupoBanner(idGrupoBanner);				
			}
			catch (TuxBasicOraException eboe) 
			{						
				tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);	
				retorno = 0;
			}	
			XMLString::release(&idGrupoBanner);
		}
	}
	else
	if(!strcmp(operacao,"3"))
	{
		char* urlBanner = walkTree(dnode,"urlBanner",0);
		char* idCampanha = walkTree(dnode,"idCampanha",0);
		char* idAreaBanner = walkTree(dnode,"idAreaBanner",0);
		char* idTipoBanner = walkTree(dnode,"idTipoBanner",0);
		char* dsBanner = walkTree(dnode,"dsBanner",0);
		char* nmBanner = walkTree(dnode,"nmBanner",0);
		char idBanner[21];
		try
		{
			banner.incluirBanner(idCampanha,urlBanner,idAreaBanner,idTipoBanner,dsBanner,nmBanner,idBanner);	
			banner.log(idBanner,login,ip);
		}
		catch(TuxBasicOraException eboe)
		{
			tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);
			retorno = 0;
		}
		XMLString::release(&urlBanner);
		XMLString::release(&idAreaBanner);
		XMLString::release(&idTipoBanner);
		XMLString::release(&dsBanner);
		XMLString::release(&idCampanha);
	}

	if(retorno)
		setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00W0000","Problemas ao executar query");
}