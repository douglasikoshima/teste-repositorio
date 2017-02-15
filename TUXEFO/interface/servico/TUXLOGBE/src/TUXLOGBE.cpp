#define TUXLOGCPP

#include<tuxfw.h>
#include"../../../negocio/TuxLog/include/Log.h"


DECLARE_TUXEDO_SERVICE(TUXLOGBE);


void implTUXLOGBE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
	tuxfw_getlogger()->debug("TUXLOGBE");

	CLog log;
	int retorno = 1;

	char*idSistemaOrigem = this->walkTree(dnode,"idSistemaOrigem",0);
	char*dtInicio = this->walkTree(dnode,"dtInicio",0);
	char*dtFim = this->walkTree(dnode,"dtFim",0);
	char*nrTerminal = this->walkTree(dnode,"nrTerminal",0);
	char*idLinhaSistemaOrigem = this->walkTree(dnode,"idLinhaSistemaOrigem",0);
	char*idContaSistemaOrigem = this->walkTree(dnode,"idContaSistemaOrigem",0);
	char*nmAPI = this->walkTree(dnode,"nmAPI",0);
	char*dsProcesso = this->walkTree(dnode,"dsProcesso",0);
	char*idCanal = this->walkTree(dnode,"idCanal",0);
	DOMNode*nodeXMLIn = this->walkDOM(dnode,"xmlin",0);
	char*statusCode = this->walkTree(dnode,"statusCode",0);
	char*statusText = this->walkTree(dnode,"statusText",0);
	char*idUser = this->walkTree(dnode,"idUser",0);
	char*idStatusComunicacao = this->walkTree(dnode,"idStatusComunicacao",0);

	log.setIdSistemaOrigem(idSistemaOrigem);
	log.setDtInicio(dtInicio);
	log.setDtFim(dtFim);
	log.setNrTerminal(nrTerminal);
	log.setIdLinhaSistemaOrigem(idLinhaSistemaOrigem);
	log.setIdContaSistemaOrigem(idContaSistemaOrigem);
	log.setNmAPI(nmAPI);
	log.setDsProcesso(dsProcesso);
	if(idCanal == NULL || !strcmp(idCanal,""))
		log.setIdCanal("15");
	else
		log.setIdCanal(idCanal);

	char*xmlGen = "";
	if(nodeXMLIn!=NULL)
		xmlGen = this->getNodeAsString(nodeXMLIn);

	try
	{
		int status = atoi(idStatusComunicacao);
		if(!strcmp(statusCode,"75E0013"))
			status = 5;
		else
		if(!strcmp(statusCode,"75E0006") || !strcmp(statusCode,"75E0010"))
			status = 4;		
		else
		if(!strcmp(statusCode,"97E0659"))
			status = 3;		
		else
		if(!strcmp(statusCode,"99E0007"))
			status = 6;	
		log.log(xmlGen,statusCode,statusText,idUser,status);
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro ao executar log");
		retorno = 0;
	}


	XMLString::release(&idSistemaOrigem);
	XMLString::release(&dtInicio);
	XMLString::release(&nrTerminal);
	XMLString::release(&idLinhaSistemaOrigem);
	XMLString::release(&idContaSistemaOrigem);
	XMLString::release(&nmAPI);
	XMLString::release(&dsProcesso);
	XMLString::release(&idCanal);
	XMLString::release(&xmlGen);
	XMLString::release(&statusCode);
	XMLString::release(&statusText);
	XMLString::release(&idUser);
	XMLString::release(&idStatusComunicacao);

	if(retorno)
		setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00W0001","Falha ao gravar log");
		
}