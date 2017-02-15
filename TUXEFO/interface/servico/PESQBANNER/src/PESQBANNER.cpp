#define PESQBANNERCPP

#include<tuxfw.h>
#include "../../../negocio/VolNegocio/include/Banner.h"

DECLARE_TUXEDO_SERVICE(PESQBANNER);


void implPESQBANNER::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	Banner banner;	
	int retorno = 1;
	int i = 0;
	char cidArea[256];
	char cidUF[512];
	char cidTipoLinha[256];
	char cidGrupo[256];

	memset(&cidArea,0,sizeof(cidArea));	
	memset(&cidUF,0,sizeof(cidUF));	
	memset(&cidTipoLinha,0,sizeof(cidTipoLinha));
	memset(&cidGrupo,0,sizeof(cidGrupo));
	

	char*areaNode = NULL;
	for(i=0; (areaNode = walkTree(dnode,"idArea",i)) != NULL;i++)
	{			
		if(i > 0 ) 
		 sprintf(cidArea,"%s,%s",cidArea,areaNode);
		else
		 sprintf(cidArea,"%s",areaNode);
		XMLString::release(&areaNode);
	}

	char*UFNode = NULL;
	for(i=0; (UFNode = walkTree(dnode,"idUF",i)) != NULL;i++)
	{
		if(i > 0 ) 
		 sprintf(cidUF,"%s,%s",cidUF,UFNode);
		else
		 sprintf(cidUF,"%s",UFNode);
		XMLString::release(&UFNode);
	}

	char*TipoLinhaNode = NULL;
	for(i=0; (TipoLinhaNode = walkTree(dnode,"idTipoLinha",i)) != NULL;i++)
	{		
		if(i > 0 ) 
		 sprintf(cidTipoLinha,"%s,%s",cidTipoLinha,TipoLinhaNode);
		else
		 sprintf(cidTipoLinha,"%s",TipoLinhaNode);
		XMLString::release(&TipoLinhaNode);
	}

	char*grupoNode = NULL;
	for(i=0; (grupoNode = walkTree(dnode,"idGrupo",i)) != NULL;i++)
	{	
		if(i > 0 ) 
		 sprintf(cidGrupo,"%s,%s",cidGrupo,grupoNode);
		else
		 sprintf(cidGrupo,"%s",grupoNode);
		XMLString::release(&grupoNode);
	}

	char*pagina = walkTree(dnode,"pagina",0);
	char*registros = walkTree(dnode,"registros",0);


	int numPagina = 0;
	int regPagina = 0;

	if(pagina!=NULL)
		numPagina = atoi(pagina);
	else
		throw new TuxBasicSvcException("00W0002","Tag pagina inválida");

	if(registros!=NULL)
		regPagina = atoi(registros);
	else
		throw new TuxBasicSvcException("00W0003","Tag registros inválida");

	xml_g->createTag("VOLTAVManterBannerVO");
	xml_g->addProp("xmlns","banner.tav.vivo.com.br");
	tuxfw_getlogger()->debug("cidArea=%s",cidArea);
	tuxfw_getlogger()->debug("cidUF=%s",cidUF);
	tuxfw_getlogger()->debug("cidTipoLinha=%s",cidTipoLinha);
	tuxfw_getlogger()->debug("cidGrupo=%s",cidGrupo);
	try{
		banner.pesquisaBanner(xml_g,cidArea,cidUF,cidTipoLinha,cidGrupo,numPagina,regPagina);
	}
	catch (TuxBasicOraException eboe) 
	{		
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);	
		retorno = 0;
	}	
	xml_g->closeTag();
	XMLString::release(&pagina);
	XMLString::release(&registros);
	if(retorno == 1)
		setStatusCode("00I0000","sucesso");
	else
		setStatusCode("00W0001","Problemas ao executar query");

}