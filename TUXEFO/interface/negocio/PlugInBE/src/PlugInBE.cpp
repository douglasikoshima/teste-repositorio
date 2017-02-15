#include "PlugInBE.h"


#include "ManagerBackEndDOMNode.h"
#include <tuxfw.h>
#include "Util.h"
#include "RegistroContato.h"
#include "ComunicacaoUsuario.h"


#include <cstdio>
#include <cstring>
#include <cstdlib>

using namespace std;


PlugInBE::PlugInBE()
{
	tuxfw_getlogger()->debug("PlugInBE::PlugInBE");


	this->tuxhp = NULL;
	this->dnode = NULL;
	this->xml_g = NULL;

	memset(this->mvc_LastStatusCode, '\0', sizeof(this->mvc_LastStatusCode));
	memset(this->mvc_LastStatusText, '\0', sizeof(this->mvc_LastStatusText));
	memset(this->mvc_IdLinhaSistemaOrigem, '\0', sizeof(this->mvc_IdLinhaSistemaOrigem));
	memset(this->mvc_ServiceName, '\0', sizeof(this->mvc_ServiceName));
	memset(this->mvc_user, '\0', sizeof(this->mvc_user));
	memset(this->mvc_IdContaSistemaOrigem, '\0', sizeof(this->mvc_IdContaSistemaOrigem));
}

PlugInBE::~PlugInBE()
{
	tuxfw_getlogger()->debug("PlugInBE::~PlugInBE");
}


char* PlugInBE::getUser()
{
	return this->mvc_user;
}


void PlugInBE::setUser(char*user)
{
	memset(this->mvc_user, '\0', sizeof(this->mvc_user));

	if (user != NULL)
		strncpy(this->mvc_user, user, (sizeof(this->mvc_user) - 1));
}


char* PlugInBE::getLastStatusCode()
{
	if (this->mvc_LastStatusCode == NULL)
		return "";
	else
		return this->mvc_LastStatusCode;
}


char* PlugInBE::getLastStatusText()
{
	if (this->mvc_LastStatusText == NULL)
		return "";
	else
		return this->mvc_LastStatusText;
}


char* PlugInBE::getIdLinhaSistemaOrigem()
{
	if (this->mvc_IdLinhaSistemaOrigem == NULL)
		return "";
	else
		return this->mvc_IdLinhaSistemaOrigem;
}

char* PlugInBE::getIdContaSistemaOrigem()
{
	if (this->mvc_IdContaSistemaOrigem == NULL)
		return "";
	else
		return this->mvc_IdContaSistemaOrigem;
}

char* PlugInBE::getServiceName()
{
	if (this->mvc_ServiceName == NULL)
		return "";
	else
		return this->mvc_ServiceName;
}

char* PlugInBE::getSgSistemaOrigem()
{
	if(this->mvc_sgSistemaOrigem == NULL)
		return "";
	else
		return	this->mvc_sgSistemaOrigem;
}

void PlugInBE::setSgSistemaOrigem(char*sgSistemaOrigem)
{
	memset(this->mvc_sgSistemaOrigem, '\0', sizeof(this->mvc_sgSistemaOrigem));

	if (sgSistemaOrigem != NULL)
		strncpy(this->mvc_sgSistemaOrigem, sgSistemaOrigem, (sizeof(this->mvc_sgSistemaOrigem) - 1));
}

void PlugInBE::setLastStatusCode(char* lastStatusCode)
{
	memset(this->mvc_LastStatusCode, '\0', sizeof(this->mvc_LastStatusCode));

	if (lastStatusCode != NULL)
		strncpy(this->mvc_LastStatusCode, lastStatusCode, (sizeof(this->mvc_LastStatusCode) - 1));
}


void PlugInBE::setLastStatusText(char* lastStatusText)
{
	memset(this->mvc_LastStatusText, '\0', sizeof(this->mvc_LastStatusText));

	if (lastStatusText != NULL)
		strncpy(this->mvc_LastStatusText, lastStatusText, (sizeof(this->mvc_LastStatusText) - 1));
}


void PlugInBE::setIdLinhaSistemaOrigem(char* idLinhaSistemaOrigem)
{
	memset(this->mvc_IdLinhaSistemaOrigem, '\0', sizeof(this->mvc_IdLinhaSistemaOrigem));

	if (idLinhaSistemaOrigem != NULL)
		strncpy(this->mvc_IdLinhaSistemaOrigem, idLinhaSistemaOrigem, (sizeof(this->mvc_IdLinhaSistemaOrigem) - 1));
}

void PlugInBE::setIdContaSistemaOrigem(char*idContaSistemaOrigem)
{
	memset(this->mvc_IdContaSistemaOrigem, '\0', sizeof(this->mvc_IdContaSistemaOrigem));

	if (idContaSistemaOrigem != NULL)
		strncpy(this->mvc_IdContaSistemaOrigem, idContaSistemaOrigem, (sizeof(this->mvc_IdContaSistemaOrigem) - 1));
}

void PlugInBE::setServiceName(char* serviceName)
{
	memset(this->mvc_ServiceName, '\0', sizeof(this->mvc_ServiceName));

	if (serviceName != NULL)
	{
		if(strlen(serviceName) >= sizeof(this->mvc_ServiceName))
			strncpy(this->mvc_ServiceName, serviceName, sizeof(this->mvc_ServiceName) - 1);
		else
			strcpy(this->mvc_ServiceName, serviceName);
	}
}


DOMNode* PlugInBE::callRemoteAPI(char* serviceName, XMLGen*  xmlEnvio, char* svcTagValue)
{
	tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI");

	TuxMessage o_inputMessage;
	TuxRemoteService o_remoteService;

	o_inputMessage.setMessageBody(xmlEnvio);
	o_inputMessage.setService((svcTagValue == NULL)? serviceName : svcTagValue);

	// Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
	o_remoteService.setServiceName(serviceName);
	o_remoteService.setInputMessage(&o_inputMessage);

	/*
	m_log.setNmAPI(svcTagValue);
	m_log.setIdLinhaSistemaOrigem(getIdLinhaSistemaOrigem());
	m_log.setIdContaSistemaOrigem(getIdContaSistemaOrigem());
	m_log.getTimeSysdate();
	*/

	try
	{
		if(o_remoteService.remoteCall() != TUXFWRET_OK)
			throw new TuxBasicSvcException("11E0999","Erro de comunicação com sistema Legado");
	}
	catch(...)
	{
		// registrar log		
		// m_log.log(xmlEnvio,o_remoteService.getOutputMessage()->getStatusCode(),o_remoteService.getOutputMessage()->getStatusText(),getUser(),4);
		throw;
	}


	// Registra o último status e texto
	char* statusCode = o_remoteService.getOutputMessage()->getStatusCode();
	char* statusText = o_remoteService.getOutputMessage()->getStatusText();

	this->setLastStatusCode(statusCode);
	this->setLastStatusText(statusText);

	tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:LastStatusCode: '%s'", statusCode);
	tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:LastStatusText: '%s'", statusText);

	tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:Liberando statusCode");
	free(statusCode);

	tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:Liberando statusText");
	free(statusText);

	char* pc_textMessage = o_remoteService.getOutputMessage()->getMessageBody();

	if (pc_textMessage == NULL)
	{
		tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI - NULL");

		// m_log.log(xmlEnvio,getLastStatusCode(),getLastStatusText(),getUser(),3);

		return NULL;
	}
	else
	{

		char* outputFull = (char*) malloc(strlen(pc_textMessage) + 100);
		sprintf(outputFull, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>%s", pc_textMessage);
		tuxfw_getlogger()->debug("getMessageBody() nova chamada");

		DOMNode* po_textMessage = NULL;

		po_textMessage = createDOMNode(outputFull);

		tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:Liberando pc_textMessage");
		free(pc_textMessage);
		free(outputFull);

		if(po_textMessage == NULL)
		{
			tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:createDOMNode - ERRO");

			// m_log.log(xmlEnvio,getLastStatusCode(),getLastStatusText(),getUser(),2);
		
			return NULL;
		}
		else
		{
			tuxfw_getlogger()->debug("PlugInBE::callRemoteAPI:createDOMNode - OK");

			// m_log.log(xmlEnvio,getLastStatusCode(),getLastStatusText(),getUser(),1);

			TuxHelper o_tuxhp;
		
			return tuxhp->walkDOM(po_textMessage, "msgBody", 0);
		}
	}
}


DOMNode* PlugInBE::callRemoteAPI(char* serviceName, XMLGen*  xmlEnvio)
{
	return this->callRemoteAPI(serviceName, xmlEnvio, NULL);
}


// Parser e Buffer para gerenciar as Alocações de DOMNode
DOMNode* PlugInBE::createDOMNode(char* textXml)
{
    tuxfw_getlogger()->debug("PlugInBE::createDOMNode");

	return m_pManangerDOMNode->createDOMNode(textXml);
}

int PlugInBE::registrarContato(XMLGen*xml_g,bool tags)
{
	int retorno = this->registrarContato();

	char* pc_inRegistrarContato   = tuxhp->walkTree(dnode, TAG_XML_IN_INREGISTRAR_CONTATO, 0);
	if ((pc_inRegistrarContato != NULL) && (*pc_inRegistrarContato != '\0') && Util::isNum(pc_inRegistrarContato) && (atoi(pc_inRegistrarContato) == 1))
	{
		this->createTagProtocolo(xml_g,tags);
	}	
	return retorno;
}

// Registrar Contato
int PlugInBE::registrarContato()
{
	int	  iRet = 0;
	bool  bRelease;

	bRelease = false;

	if(tuxhp->getRelease() == true)
	{
		bRelease = true;
		tuxhp->setRelease(false);
	}

	char* pc_inRegistrarContato   = tuxhp->walkTree(dnode, TAG_XML_IN_INREGISTRAR_CONTATO, 0);

	if ((pc_inRegistrarContato != NULL) && (*pc_inRegistrarContato != '\0') && Util::isNum(pc_inRegistrarContato) && (atoi(pc_inRegistrarContato) == 1))
	{
		char* pc_cdAreaRegistro       = tuxhp->walkTree(dnode, TAG_XML_IN_CD_AREA_REGISTRO, 0);
		char* pc_nrLinha              = tuxhp->walkTree(dnode, TAG_XML_IN_NR_LINHA, 0);
		char* pc_idTipoRelacionamento = tuxhp->walkTree(dnode, TAG_XML_IN_ID_TIPO_RELACIONAMENTO, 0);
		char* pc_cdContato            = tuxhp->walkTree(dnode, TAG_XML_IN_CD_CONTATO, 0);
		char* pc_idCanal              = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CANAL, 0);
		char* pc_idTerminal           = tuxhp->walkTree(dnode, TAG_XML_IN_ID_TERMINAL, 0);
		char* pc_idGrupoAbertura      = tuxhp->walkTree(dnode, "idGrupoAbertura", 0);
		char* pc_idSistemaOrigem      = tuxhp->walkTree(dnode, "idSistemaOrigem", 0);
		char* pc_idTipoAberturaProtocolo    = tuxhp->walkTree(dnode, "idTipoAberturaProtocolo", 0);
		char* pc_nrProtocolo           = tuxhp->walkTree(dnode, "nrProtocolo", 0);

		// caso o canal for URA, não efetuamos a palitagem
		if(pc_idCanal != NULL && !strcmp(pc_idCanal,"9"))
		{
			return 0;
		}
	
		try
		{
			// Registro de contato
			CRegistroContato o_RegContato;

			o_RegContato.setInRegistrarContato(pc_inRegistrarContato);
			o_RegContato.setCdAreaRegistro(pc_cdAreaRegistro);
			o_RegContato.setNrLinha(pc_nrLinha);
			o_RegContato.setIdTipoRelacionamento(pc_idTipoRelacionamento);
			o_RegContato.setCdContato(pc_cdContato);
			o_RegContato.setIdCanal(pc_idCanal);
			o_RegContato.setIdTerminal(pc_idTerminal);
			o_RegContato.setIdUser(this->getUser());
			o_RegContato.setIdGrupoAbertura(pc_idGrupoAbertura);
			o_RegContato.setIdTipoAberturaProtocolo(pc_idTipoAberturaProtocolo);
			o_RegContato.setIdSistemaOrigem(pc_idSistemaOrigem);
			o_RegContato.setNrProtocolo(pc_nrProtocolo);

			iRet = o_RegContato.registrarContatoProtocolo();
			// apenas setar o novo número de protocolo caso tenha expirado o tempo de sessão
			this->setNrProtocolo(o_RegContato.getNrProtocolo());

		}
		catch(...)
		{
			iRet = -1;
		}

		if (pc_inRegistrarContato != NULL)   XMLString::release(&pc_inRegistrarContato);
		if (pc_cdAreaRegistro != NULL)       XMLString::release(&pc_cdAreaRegistro);
		if (pc_nrLinha != NULL)              XMLString::release(&pc_nrLinha);
		if (pc_idTipoRelacionamento != NULL) XMLString::release(&pc_idTipoRelacionamento);
		if (pc_cdContato != NULL)            XMLString::release(&pc_cdContato);
		if (pc_idCanal != NULL)              XMLString::release(&pc_idCanal);
		if (pc_idTerminal != NULL)           XMLString::release(&pc_idTerminal);
		if (pc_idGrupoAbertura != NULL)      XMLString::release(&pc_idGrupoAbertura);
		if (pc_idSistemaOrigem != NULL)		 XMLString::release(&pc_idSistemaOrigem);
		if (pc_idTipoAberturaProtocolo != NULL) XMLString::release(&pc_idTipoAberturaProtocolo);
		if (pc_nrProtocolo != NULL)			 XMLString::release(&pc_nrProtocolo);

	}
	else
	{
		if (pc_inRegistrarContato != NULL)   XMLString::release(&pc_inRegistrarContato);

		tuxfw_getlogger()->debug("PlugInBE::registrarContato - Registro nao necessario");		
		
		tuxhp->setRelease(bRelease);

		return -1;
	}

	if (iRet != 0)
		tuxfw_getlogger()->debug("PlugInBE::registrarContato - FALHOU");
	else
		tuxfw_getlogger()->debug("PlugInBE::registrarContato - OK");

	tuxhp->setRelease(bRelease);

	return iRet;
}



// Comunicar Usuario
int PlugInBE::comunicarUsuario()
{
	int	  iRet = 0;
	bool  bRelease;

	bRelease = false;

	if(tuxhp->getRelease() == true)
	{
		bRelease = true;
		tuxhp->setRelease(false);
	}

	char* pc_inComunicarUsuario   = tuxhp->walkTree(dnode, TAG_XML_IN_INCOMUNICAR_USUARIO, 0);

	if ((pc_inComunicarUsuario != NULL) && (*pc_inComunicarUsuario != '\0') && Util::isNum(pc_inComunicarUsuario) && (atoi(pc_inComunicarUsuario) == 1))
	{
		char* pc_cdAreaRegistro       = tuxhp->walkTree(dnode, TAG_XML_IN_CD_AREA_REGISTRO, 0);
		char* pc_nrLinha              = tuxhp->walkTree(dnode, TAG_XML_IN_NR_LINHA, 0);
		char* pc_idTipoRelacionamento = tuxhp->walkTree(dnode, TAG_XML_IN_ID_TIPO_RELACIONAMENTO, 0);
		char* pc_cdMsg                = tuxhp->walkTree(dnode, TAG_XML_IN_CD_MSG, 0);

		try
		{
			// Comunicar Usuario
			CComunicacaoUsuario o_ComUsuario;

			o_ComUsuario.setCdAreaRegistro(pc_cdAreaRegistro);
			o_ComUsuario.setNrLinha(pc_nrLinha);
			o_ComUsuario.setIdTipoRelacionamento(pc_idTipoRelacionamento);
			o_ComUsuario.setCdMsg(pc_cdMsg);

			iRet = o_ComUsuario.comunicarUsuario();
		}
		catch(...)
		{
			iRet = -1;
		}

		if (pc_inComunicarUsuario != NULL)   XMLString::release(&pc_inComunicarUsuario);
		if (pc_cdAreaRegistro != NULL)       XMLString::release(&pc_cdAreaRegistro);
		if (pc_nrLinha != NULL)              XMLString::release(&pc_nrLinha);
		if (pc_idTipoRelacionamento != NULL) XMLString::release(&pc_idTipoRelacionamento);
		if (pc_cdMsg != NULL)                XMLString::release(&pc_cdMsg);
		
	}
	else
	{
		if (pc_inComunicarUsuario != NULL)   XMLString::release(&pc_inComunicarUsuario);

		tuxfw_getlogger()->debug("PlugInBE::comunicarUsuario - Comunicacao nao necessaria");

		tuxhp->setRelease(bRelease);

		return -1;
	}

	if (iRet != 0)
		tuxfw_getlogger()->debug("PlugInBE::comunicarUsuario - FALHOU");
	else
		tuxfw_getlogger()->debug("PlugInBE::comunicarUsuario - OK");

	tuxhp->setRelease(bRelease);

	return iRet;
}

void PlugInBE::setNrProtocolo(char*value)
{
	if(value != NULL)	
		strcpy(this->m_nrProtocolo,value);	
}

void PlugInBE::setInProtocolo(char*value)
{
	if(value != NULL)	
		strcpy(this->m_inProtocolo,value);	
}

void PlugInBE::setInRelacionamento(char*value)
{
	if(value != NULL)	
		strcpy(this->m_inRelacionamento,value);	
}

void PlugInBE::setExibeProtocolo(char*value)
{
	if(value != NULL)	
		strcpy(this->m_exibeProtocolo,value);	
}

char* PlugInBE::getNrProtocolo()
{
	return this->m_nrProtocolo;
}

char* PlugInBE::getInProtocolo()
{
	return this->m_inProtocolo;
}

char* PlugInBE::getInRelacionamento()
{
	return this->m_inRelacionamento;
}

char* PlugInBE::getExibeProtocolo()
{
	return this->m_exibeProtocolo;
}

void PlugInBE::createTagProtocolo(XMLGen *xml_g,bool properties)
{
	if(properties)
	{
		xml_g->createTag("ProtocoloVO");
		xml_g->addProp("xmlns","tuxproxy.fo.vivo.com.br/vo");
	}
	
	xml_g->addItem("nrProtocolo",this->getNrProtocolo());	
	
	if(properties)	
		xml_g->closeTag();
}
