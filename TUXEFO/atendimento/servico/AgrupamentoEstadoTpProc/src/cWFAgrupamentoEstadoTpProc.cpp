/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:01:28 $
 **/


#include "../include/cWFAgrupamentoEstadoTpProc.h"

extern bool proCIncluirWFAgrupamentoEstadoTpProc(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* status, XMLGen* saida);
extern bool proCAlterarWFAgrupamentoEstadoTpProc(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* status, XMLGen* saida);
extern bool proCExcluirWFAgrupamentoEstadoTpProc(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* status, XMLGen* saida);
extern bool proCConsultaWFAgrupamentoEstadoTpProc(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* status, char* order, XMLGen* saida);
extern int  proCConsultaProximoAgrupamento(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* status);
extern int proCObterProxAgrupamentoDif(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* status);

cWFAgrupamentoEstadoTpProc::cWFAgrupamentoEstadoTpProc(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* indicador)
{
	memcpy(&m_stDados, dados, sizeof(m_stDados));
	memcpy(&m_vlDados, dados, sizeof(m_vlDados));

	saida = 0;
    entrada = 0;

	locado = false;
}

cWFAgrupamentoEstadoTpProc::cWFAgrupamentoEstadoTpProc(DOMNode*dnode, XMLGen*xml_g)
{
	locado = false;

	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAgrupamentoEstadoTpProc::~cWFAgrupamentoEstadoTpProc()
{
}

bool cWFAgrupamentoEstadoTpProc::incluir()
{
	return proCIncluirWFAgrupamentoEstadoTpProc(&m_stDados, &m_vlDados, saida);
}

int cWFAgrupamentoEstadoTpProc::alterar()
{

	if (m_vlDados.idAgrupamentoEstadoTpProc == -1) 
	{
		return -1;
	}

	return proCAlterarWFAgrupamentoEstadoTpProc(&m_stDados, &m_vlDados, saida);
}

int cWFAgrupamentoEstadoTpProc::excluir()
{

	if (m_vlDados.idAgrupamentoEstadoTpProc == -1) 
	{
		return -1;
	}

	return proCExcluirWFAgrupamentoEstadoTpProc(&m_stDados, &m_vlDados, saida);
}

bool cWFAgrupamentoEstadoTpProc::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
        char *p;
	    if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

	return proCConsultaWFAgrupamentoEstadoTpProc(&m_stDados, &m_vlDados, order, saida);
}

int cWFAgrupamentoEstadoTpProc::proximoAgrupamento(bool agrupDeveSerDiferente)
{
   ULOG_START("cWFAgrupamentoEstadoTpProc::proximoAgrupamento()");
	int idAgrupamentoEstadoTpProc;

    if ( agrupDeveSerDiferente )
    {
	    idAgrupamentoEstadoTpProc = proCObterProxAgrupamentoDif(&m_stDados, &m_vlDados);
    }
    else
    {
	    idAgrupamentoEstadoTpProc = proCConsultaProximoAgrupamento(&m_stDados, &m_vlDados);
    }

	if (saida)
	{
		saida->createTag("ProximoAgrupamentoEstadoTpProcVO");
			saida->addItem("idAgrupamentoEstadoTpProc",idAgrupamentoEstadoTpProc);
		saida->closeTag();
	}

   ULOG_END("cWFAgrupamentoEstadoTpProc::proximoAgrupamento()");
	return idAgrupamentoEstadoTpProc;
}

void cWFAgrupamentoEstadoTpProc::carregaDados()
{
   ULOG_START("cWFAgrupamentoEstadoTpProc::carregaDados()");
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( !entrada )
    {
        return;
    }

	if ( p=tx.walkTree( entrada, "idAgrupamentoEstadoTpProc", 0 ),p )
	{
		m_stDados.idAgrupamentoEstadoTpProc = atoi(p);
		m_vlDados.idAgrupamentoEstadoTpProc = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idTipoProcesso", 0 ),p )
	{
		m_stDados.idTipoProcesso = atoi(p);
		m_vlDados.idTipoProcesso = 1;
        XMLString::release(&p);
	}


	if ( p=tx.walkTree( entrada, "idAgrupamentoEstado", 0 ),p )
	{
		m_stDados.idAgrupamentoEstado = atoi(p);
		m_vlDados.idAgrupamentoEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "inEstadoInicial", 0 ),p )
	{
		m_stDados.inEstadoInicial = atoi(p);
		m_vlDados.inEstadoInicial = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "nmURLDados", 0 ),p )
	{
		m_stDados.nmURLDados = atoi(p);
		m_vlDados.nmURLDados = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idAtividade", 0 ),p )
	{
		m_stDados.idAtividade = atoi(p);
		m_vlDados.idAtividade = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p )
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p )
	{
		strcpy(m_stDados.dtUltimaAlteracao,p);
		m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
	}
	ULOG_END("cWFAgrupamentoEstadoTpProc::carregaDados()");
}

