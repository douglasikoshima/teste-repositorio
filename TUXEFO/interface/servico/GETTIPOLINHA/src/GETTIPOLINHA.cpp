#define GETTIPOLINHA__

#include<tuxfw.h>
#include"TipoLinha.h"

DECLARE_TUXEDO_SERVICE(GETTIPOLINHA);


void implGETTIPOLINHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	TipoLinha tipoLinha;
	int retorno = 0;
	char *cdNumTelefone = walkTree(dnode,"cdNumTelefone",0);
	char *cdDDD = walkTree(dnode,"cdDDD",0);
	char ctipoLinha[256];
	char cIdLinhaTelefonica[256];
	xml_g->createTag("LINHA");
	xml_g->addProp("xmlns","tuxProxyBE/vo");
	memset(&ctipoLinha,0,sizeof(ctipoLinha));
	try
	{
		if(retorno = tipoLinha.getTipoLinha(cdDDD,cdNumTelefone,ctipoLinha,cIdLinhaTelefonica))
		{
			xml_g->addItem("tipoLinha",ctipoLinha);
			xml_g->addItem("idLinhaTelefonica",cIdLinhaTelefonica);
		}
		}
	catch(TuxBasicOraException eboe)
	{
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);
		XMLString::release(&cdNumTelefone);
		XMLString::release(&cdDDD);
		throw new TuxBasicSvcException("00W0002","erro de oracle");
	}

	if(retorno)
	setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00W0001","Linha não encontrada");

	XMLString::release(&cdNumTelefone);
	XMLString::release(&cdDDD);
	xml_g->closeTag();
}