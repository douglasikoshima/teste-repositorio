/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#include <tuxfw.h>
#include "../include/cWFAtendimentoUsuarioAtual.h"

extern bool proCIncluirWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status, XMLGen* saida, XMLDPR *xmlDpr);
extern bool proCIncluirWFAtendimentoUsuarioAtual1aVez(long idAtendimento,long idPessoaUsuarioAtual,XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status, XMLGen* saida, XMLDPR *xmlDpr);
extern void proCExcluirWFAtendimentoUsuarioAtual(long _idAtendimento,long _idPessoaUsuarioAtual,XMLDPR *xmlDpr);
extern bool proCExisteWFAtendimentoUsuarioAtual(long _idAtendimento);
extern bool proCExcluirLogWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status, XMLGen* saida);
extern long proCObterIdPessoaUsuarioAtual(long _idAtendimento);

cWFAtendimentoUsuarioAtual::cWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

	entrada = 0;
	saida   = 0;
}

cWFAtendimentoUsuarioAtual::cWFAtendimentoUsuarioAtual(st_AtendimentoUsuarioAtual* dados, st_vlAtendimentoUsuarioAtual* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

	entrada = 0;
	saida   = xml_g;
}

cWFAtendimentoUsuarioAtual::cWFAtendimentoUsuarioAtual(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
bool cWFAtendimentoUsuarioAtual::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoUsuarioAtual::incluir() indevido!");
}

bool cWFAtendimentoUsuarioAtual::incluir(XMLDPR *xmlDpr)
{
	return proCIncluirWFAtendimentoUsuarioAtual(&m_stDados, &m_vlDados, saida, xmlDpr);
}

bool cWFAtendimentoUsuarioAtual::incluir1aVez(long idAtendimento,long idPessoaUsuarioAtual,XMLDPR *xmlDpr)
{
    return proCIncluirWFAtendimentoUsuarioAtual1aVez(idAtendimento,idPessoaUsuarioAtual,xmlDpr);
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoUsuarioAtual::alterar()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoUsuarioAtual::alterar() indevido!");
}

int cWFAtendimentoUsuarioAtual::alterar(XMLDPR *xmlDpr)
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoUsuarioAtual(&m_stDados, &m_vlDados, saida, xmlDpr);
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
bool cWFAtendimentoUsuarioAtual::excluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoUsuarioAtual::excluir() indevido!");
}

bool cWFAtendimentoUsuarioAtual::excluir(XMLDPR *xmlDpr)
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return false;
	}

	proCExcluirWFAtendimentoUsuarioAtual(m_stDados.idAtendimento,m_stDados.idPessoaUsuario,xmlDpr);

    return true;
}

bool cWFAtendimentoUsuarioAtual::excluir(long idAtendimento,int idPessoaUsuario,XMLDPR *xmlDpr)
{
	proCExcluirWFAtendimentoUsuarioAtual(idAtendimento,idPessoaUsuario,xmlDpr);

    return true;
}

int cWFAtendimentoUsuarioAtual::excluirLogicamente()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirLogWFAtendimentoUsuarioAtual(&m_stDados, &m_vlDados, saida);
}

long cWFAtendimentoUsuarioAtual::obterIdPessoaUsuarioAtual(long idAtendimento)
{
	return proCObterIdPessoaUsuarioAtual(idAtendimento);
}

bool cWFAtendimentoUsuarioAtual::existe(long idAtendimento)
{
	return proCExisteWFAtendimentoUsuarioAtual(idAtendimento);
}

void cWFAtendimentoUsuarioAtual::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( !entrada )
    {
        return;
    }

    char *p;

	if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idPessoaUsuario", 0 ),p) 
	{
		m_stDados.idPessoaUsuario = atoi(p);
		m_vlDados.idPessoaUsuario = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "inPausaAtendimento", 0 ),p) 
	{
		m_stDados.inPausaAtendimento = atol(p);
		m_vlDados.inPausaAtendimento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idMotivoPausaAtendimento", 0 ),p) 
	{
		m_stDados.idMotivoPausaAtendimento = atoi(p);
		m_vlDados.idMotivoPausaAtendimento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p) 
	{
		strcpy( m_stDados.dtUltimaAlteracao,p );
		m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtFimPausaAtendimento", 0 ),p) 
	{
		strcpy( m_stDados.dtFimPausaAtendimento,p );
		m_vlDados.dtFimPausaAtendimento = 1;
        XMLString::release(&p);
	}
}
