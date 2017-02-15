#include "CreatorPlugInBE.h"
#include "../../PlugInBE/include/PlugInBE.h"

#include <tuxfw.h>

#include "../../PlugInATLYS/include/PlugInATLYS.h"
#include "../../PlugInPPS/include/PlugInPPS.h"
#include "../../PlugInNGIN/include/PlugInNGIN.h"


PlugInBE* CreatorPlugInBE::getPlugIn(DOMNode* dnode, XMLGen* xml_g, char* pcLinha)
{
	PlugInBE* pInstance = NULL;
	char vcIdLinhaSisOrig[16];
	char sgSistemaOrigem[256];
	int  idSistemaOrigem; 

	char  c_system[TXPB_SERVICE_NAME_SIZE];
	char* pc_env = NULL;
	bool  b_found = false;
	CTuxHelperClever tuxhp;
	tuxhp.setRelease(true);

	tuxfw_getlogger()->debug("CreatorPlugInBE::getPlugIn");

	if(pcLinha == NULL || *pcLinha == '\0')
	{
		
		char *pc_idGrupo = tuxhp.walkTree(dnode, TAG_XML_IN_ID_GRUPO, 0);

		if(pc_idGrupo != NULL)
			return NULL;
	}

	// Faz a consulta no banco de dados para determinar qual o sistema
	switch(idSistemaOrigem = chooseSystem(pcLinha, vcIdLinhaSisOrig, sizeof(vcIdLinhaSisOrig),sgSistemaOrigem))
	{
		case ATLYS:			
			pInstance = new PlugInATLYS(dnode, xml_g, vcIdLinhaSisOrig);			
			break;
		
		/*
		case NGIN:			
			pInstance = new PlugInNGIN(dnode, xml_g, vcIdLinhaSisOrig);
			break; 
		
		case PPS:
			pInstance = new PlugInPPS(dnode, xml_g);
			break; */
		
		case SYS_NOT_FOUND:
			throw new TuxBasicSvcException(ECD_SYS_NOT_FOUND, EMSG_SYS_NOT_FOUND);
			break;

		case SYS_NOT_SUPORTED:

		default:
			
			if(idSistemaOrigem > 0)
			{
				//procurando no env qual mbo acessar
				sprintf(c_system, "NGIN_%s", sgSistemaOrigem);

				tuxfw_getlogger()->debug("ENV: %s", c_system);

				pc_env = tuxgetenv(c_system);

				if(pc_env)
				{
					tuxfw_getlogger()->debug("NGIN: %s", pc_env);

					pInstance = new PlugInNGIN(dnode, xml_g, vcIdLinhaSisOrig,sgSistemaOrigem);
					pInstance->setServiceName(pc_env);
					b_found = true;
				}

			}

			if(b_found == false)
				throw new TuxBasicSvcException(ECD_SYS_NOT_SUPORTED, EMSG_SYS_NOT_SUPORTED);
	}

	if(pInstance == NULL)
		throw new TuxBasicSvcException(ECD_CAN_NOT_CREATE_PLUGIN, EMSG_CAN_NOT_CREATE_PLUGIN);

	if(pInstance->m_log.getParametroLog())
	{
		char *pc_idCanal = tuxhp.walkTree(dnode, "idCanal", 0);
		char *pc_proxyLinha = tuxhp.walkTree(dnode, "ProxyLinha", 0);
		char *pc_Operacao = tuxhp.walkTree(dnode, "ProxyOperacao", 0);
		pInstance->m_log.getSistemaOrigem(sgSistemaOrigem);
		pInstance->m_log.setIdCanal(pc_idCanal);
		pInstance->m_log.setNrTerminal(pc_proxyLinha);
		pInstance->m_log.setDsProcesso(pc_Operacao);
	}

	return pInstance;
}


// Chamador para construir um PlugInNGIN
PlugInNGIN* CreatorPlugInBE::getPlugInNGIN(DOMNode* dnode, XMLGen* xml_g, char* pcLinha)
{
	tuxfw_getlogger()->debug("CreatorPlugInBE::getPlugInNGIN");
	PlugInNGIN* pInstance = NULL;
	char vcIdLinhaSisOrig[256];
	char sgSistemaOrigem[256];
	char sgTipoLinha[256];
	char vcIdLinhaControle[256];
	int  idSistemaOrigem; 
	char  c_system[TXPB_SERVICE_NAME_SIZE];
	char* pc_env = NULL;
	bool  b_found = false;
	CTuxHelperClever tuxhp;
	tuxhp.setRelease(true);
	
	if(pcLinha == NULL || *pcLinha == '\0')
	{
		
		char *pc_idGrupo = tuxhp.walkTree(dnode, TAG_XML_IN_ID_GRUPO, 0);

		if(pc_idGrupo != NULL)
			return NULL;
	}
	// recuperar dados da linha
	idSistemaOrigem = getDadosLinha(pcLinha, vcIdLinhaSisOrig, sizeof(vcIdLinhaSisOrig),sgSistemaOrigem, vcIdLinhaControle,  sgTipoLinha);
	// Se o tipo de linha for controle, vamos alterar o IDLINHASISTEMAORIGEM para o campo IDLINHACONTROLE
	if(!strcmp(sgTipoLinha,"CONT") || !strcmp(sgTipoLinha,"CONTCHIP"))
	{
		tuxfw_getlogger()->debug("linha controle(%s), deve substituir IDLINHASISTEMAORIGEM por %s",sgTipoLinha,vcIdLinhaControle);
		memset(vcIdLinhaSisOrig,'\0',sizeof(vcIdLinhaSisOrig));
		strcpy(vcIdLinhaSisOrig,vcIdLinhaControle);
	}

	//procurando no env qual mbo acessar
	sprintf(c_system, "NGIN_NSP");
	tuxfw_getlogger()->debug("ENV: %s", c_system);
	pc_env = tuxgetenv(c_system);
	if(pc_env)
	{
		tuxfw_getlogger()->debug("NGIN: %s", pc_env);
		pInstance = new PlugInNGIN(dnode, xml_g, vcIdLinhaSisOrig,sgSistemaOrigem);
		pInstance->setServiceName(pc_env);
		b_found = true;
	}

	if(b_found == false)
		throw new TuxBasicSvcException(ECD_SYS_NOT_SUPORTED, EMSG_SYS_NOT_SUPORTED);

	if(pInstance == NULL)
		throw new TuxBasicSvcException(ECD_CAN_NOT_CREATE_PLUGIN, EMSG_CAN_NOT_CREATE_PLUGIN);

	return pInstance;
}

// Chamador para contruir um PlugInATLYS
PlugInATLYS* CreatorPlugInBE::getPlugInATLYS(DOMNode* dnode, XMLGen* xml_g, char* pcLinha)
{
	tuxfw_getlogger()->debug("CreatorPlugInBE::getPlugInATLYS");
	PlugInATLYS* pInstance = NULL;
	char vcIdLinhaSisOrig[256];
	char sgSistemaOrigem[256];
	char sgTipoLinha[256];
	char vcIdLinhaControle[256];
	int  idSistemaOrigem; 
	char  c_system[TXPB_SERVICE_NAME_SIZE];
	char* pc_env = NULL;
	bool  b_found = false;
	CTuxHelperClever tuxhp;
	tuxhp.setRelease(true);

	if(pcLinha == NULL || *pcLinha == '\0')
	{
		
		char *pc_idGrupo = tuxhp.walkTree(dnode, TAG_XML_IN_ID_GRUPO, 0);

		if(pc_idGrupo != NULL)
			return NULL;
	}
	// recuperar dados da linha
	idSistemaOrigem = getDadosLinha(pcLinha, vcIdLinhaSisOrig, sizeof(vcIdLinhaSisOrig),sgSistemaOrigem, vcIdLinhaControle,  sgTipoLinha);
	if(idSistemaOrigem == ATLYS)
	{
		pInstance = new PlugInATLYS(dnode, xml_g, vcIdLinhaSisOrig);
	}

	if(pInstance == NULL)
		throw new TuxBasicSvcException(ECD_CAN_NOT_CREATE_PLUGIN, EMSG_CAN_NOT_CREATE_PLUGIN);

	return pInstance;

}
