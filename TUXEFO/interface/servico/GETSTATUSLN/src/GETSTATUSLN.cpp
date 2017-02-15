#define GETSTATUSLNCPP

#include<tuxfw.h>
#include"Linha.h"

DECLARE_TUXEDO_SERVICE(GETSTATUSLN);


void implGETSTATUSLN::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	Linha linha;
	char *cdNumTelefone = walkTree(dnode,"cdNumTelefone",0);
	char *cdDDD = walkTree(dnode,"cdDDD",0);
	char estadoLinha[256];
	estadoLinha[0] = 0;
	int retorno = 0;
	xml_g->createTag("LINHA");
	try
	{
		retorno = linha.getStatusLinha(cdNumTelefone,cdDDD,estadoLinha);
		xml_g->addItem("estadoLinha",estadoLinha);
	}
	catch (TuxBasicOraException eboe) 
	{		
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);	
		retorno = 0;
	}	
	xml_g->closeTag();
	if(retorno == 1)
		setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00W0001","Problemas ao executar query");
	XMLString::release(&cdNumTelefone);
	XMLString::release(&cdDDD);
}