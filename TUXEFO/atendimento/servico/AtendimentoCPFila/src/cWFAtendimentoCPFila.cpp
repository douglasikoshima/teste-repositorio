/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:37:32 $
 **/


#include "../include/cWFAtendimentoCPFila.h"

extern long proCIncluirWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status, XMLGen* saida);
extern bool  proCAlterarWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status, XMLGen* saida);
extern bool  proCExcluirWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status, XMLGen* saida);
extern bool  proCConsultaWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status, char* order, XMLGen* saida);
extern unsigned long proCObterWFRegistroCPFila(long sIdAtendimento,st_AtendimentoCPFila* dados);
extern bool proCExisteCPrevio( long _idAtendimento );
extern bool proCAlterarWFAtendimentoCPFilaPessGrupo(st_AtendimentoCPFila* dados);
extern bool proCObterAtendimentoGrupoRCConfigurado( FetchResultado &_dFetchResultado,int &idGrupoEncaminhamento, int _idUsuarioGrupo );
extern bool proCObterGrupoRCConfigurado(long _idAtendimento ,int _idContato ,int _idCanal,int _idProcedencia ,int _idSegmentacao ,int _idTipoCarteira,int _idUFOperadora ,int &idGrupoEncaminhar,int _idUsuarioGrupo );
extern bool proCObterAtdCPrevio(long idAtendimento,st_AtdCprevio *dados );
extern int proCGetGruposAssociadosRC( int _idUsuario );

cWFAtendimentoCPFila::cWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status)
{
    XMLGen xml_g ;
    entrada = 0;
    saida   = &xml_g;
    
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));
}


cWFAtendimentoCPFila::cWFAtendimentoCPFila(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}



cWFAtendimentoCPFila::cWFAtendimentoCPFila(st_AtendimentoCPFila* dados, st_vlAtendimentoCPFila* status, XMLGen*xml_g)
{
    entrada = 0;
    saida   = xml_g;
    
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

long cWFAtendimentoCPFila::incluir()
{
    return proCIncluirWFAtendimentoCPFila(&m_stDados, &m_vlDados, saida);
}

long cWFAtendimentoCPFila::alterar()
{
    if (m_vlDados.idAtendimento == -1) 
    {
        return -1;
    }

    return proCAlterarWFAtendimentoCPFila(&m_stDados, &m_vlDados, saida);
}

long cWFAtendimentoCPFila::alterar(st_AtendimentoCPFila* dados)
{
    
    return proCAlterarWFAtendimentoCPFilaPessGrupo( dados);
}

long cWFAtendimentoCPFila::incluir(st_AtendimentoCPFila* dados,st_vlAtendimentoCPFila* status)
{
    return proCIncluirWFAtendimentoCPFila(dados, status, saida);
}


long cWFAtendimentoCPFila::excluir()
{
    if (m_vlDados.idAtendimento == -1) 
    {
        return -1;
    }

    return proCExcluirWFAtendimentoCPFila(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoCPFila::consultar()
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

    return proCConsultaWFAtendimentoCPFila(&m_stDados, &m_vlDados, order, saida);
}



long cWFAtendimentoCPFila::obterRegistroCPFila(long sIdAtendimento,st_AtendimentoCPFila* dados)
{
    return proCObterWFRegistroCPFila(sIdAtendimento, dados);
}

bool cWFAtendimentoCPFila::ExistAtendimento(long _idAtendimento)
{
    return proCExisteCPrevio( _idAtendimento );
}


void cWFAtendimentoCPFila::carregaDados()
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

    if (p=tx.walkTree( entrada, "idGrupoOrigem", 0 ),p) 
    {
        m_stDados.idGrupoOrigem= atoi(p);
        m_vlDados.idGrupoOrigem = 1;
      XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "idPessoaUsuarioOrigem", 0 ),p) 
    {
        m_stDados.idPessoaUsuarioOrigem = atoi(p);
        m_vlDados.idPessoaUsuarioOrigem = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "inContatoPrevioRealizado", 0 ),p) 
    {
        m_stDados.inContatoPrevioRealizado = atoi(p);
        m_vlDados.inContatoPrevioRealizado = 1;
        XMLString::release(&p);
    }


    if (p=tx.walkTree( entrada, "nrTentativas", 0 ),p)  
    {
        m_stDados.nrTentativas = atoi(p);
        m_vlDados.nrTentativas = 1;
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
        strcpy(m_stDados.dtUltimaAlteracao, p);
        m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtCalculada", 0 ),p) 
    {
        strcpy(m_stDados.dtCalculada, p);
        m_vlDados.dtCalculada = 1;
        XMLString::release(&p);
    }

	
	
	
}

bool cWFAtendimentoCPFila::obterAtendimentoGrupoRCConfigurado( FetchResultado &_dFetchResultado,int &idGrupoEncaminhamento,int _idUsuarioGrupo )
{
  return  proCObterAtendimentoGrupoRCConfigurado( _dFetchResultado,idGrupoEncaminhamento, _idUsuarioGrupo );
}
bool cWFAtendimentoCPFila::obterGrupoRCConfigurado(long _idAtendimento ,int _idContato ,int _idCanal,int _idProcedencia ,int _idSegmentacao ,int _idTipoCarteira,int _idUFOperadora ,int &idGrupoEncaminhar ,int _idUsuarioGrupo)
{
  return proCObterGrupoRCConfigurado( _idAtendimento , _idContato , _idCanal, _idProcedencia , _idSegmentacao , _idTipoCarteira, _idUFOperadora , idGrupoEncaminhar , _idUsuarioGrupo );   
}

bool cWFAtendimentoCPFila::ObterAtdCPrevio(long idAtendimento,struct st_AtdCprevio *dados )
{
    return proCObterAtdCPrevio( idAtendimento, dados ) ;
}


int cWFAtendimentoCPFila::GetGruposAssociadosRC( int _idUsuario )
{
    return proCGetGruposAssociadosRC( _idUsuario );
}

