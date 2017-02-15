/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/ 
 
#include "../include/cWFAtdTratamentoCri.h"
#include "../../../commons/msgPadrao.h"

extern int proCIncluirWFAtendimentoTratamentoGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long proCIncluirWFAtendimentoTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long proCIncluirWFAtendimentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCIncrementarQtEnvioBKO(long idAtendimento);
extern long proCAlterarWFAtendimentoGrupoAtualCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFTratamentoGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern long proCAlterarWFAtendimentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern unsigned long proCAlterarWFTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFUsuarioDevolucaoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCAlterarWFGrupoDevolucaoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCExcluirWFTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status);
extern int proCExcluirWFAtendimentoCri(long _idAtendimento);
extern void proCAtualizarDataPrazoCRI(long _idAtendimento);

extern int proCObtemWFUsuarioAtualCri(long sIdAtendimento);

extern bool proCObtemWFGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCObtemWFGrupoCriEx(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCObtemWFTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCObtemWFTratamentoGrupoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCObtemWFAtendimentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCObtemWFAtendimentoCriEx(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen* saida);
extern bool proCProcessoDentroPrazoRetornoInbox(long _idAtendimento);

cWFAtdTratamentoCri::cWFAtdTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status, XMLGen*xml_g)
{
    m_stDados = dados;
    m_vlDados = status;

    entrada = 0;
    saida   = xml_g;

    alocado = false;
}

cWFAtdTratamentoCri::cWFAtdTratamentoCri(st_AtdTratamentoCri* dados, st_vlAtdTratamentoCri* status)
{
    m_stDados = dados;
    m_vlDados = status;

    entrada = 0;
    saida   = 0;

    alocado = false;
}

cWFAtdTratamentoCri::cWFAtdTratamentoCri(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    m_stDados = new st_AtdTratamentoCri;
    m_vlDados = new st_vlAtdTratamentoCri;

    alocado = true;

    carregaDados();
}

cWFAtdTratamentoCri::~cWFAtdTratamentoCri()
{
    if ( alocado )
    {
        delete m_stDados;
        delete m_vlDados;
    }
}

int cWFAtdTratamentoCri::incAtendimentoCriQtEnvioBKO(long idAtendimento)
{
    return proCIncrementarQtEnvioBKO(idAtendimento);
}

long cWFAtdTratamentoCri::incluirTratamentoCri()
{
    return proCIncluirWFAtendimentoTratamentoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::incluirTratamentoGrupoCri()
{
    return proCIncluirWFAtendimentoTratamentoGrupoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::incluirAtendimentoCri()
{
    return proCIncluirWFAtendimentoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::alterarAtendimentoGrupoAtualCri()
{
    return proCAlterarWFAtendimentoGrupoAtualCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::alterarTratamentoGrupoCri()
{
    return proCAlterarWFTratamentoGrupoCri(m_stDados, m_vlDados);
}

void cWFAtdTratamentoCri::atualizarPrazoCRI(long idAtendimento)
{
    proCAtualizarDataPrazoCRI(idAtendimento);
}

long cWFAtdTratamentoCri::alterarGrupoCri()
{
    return proCAlterarWFGrupoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::alterarAtendimentoCri()
{
    return proCAlterarWFAtendimentoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::alterarTratamentoCri()
{
    return proCAlterarWFTratamentoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::alterarUsuarioDevolucaoCri()
{
    return proCAlterarWFUsuarioDevolucaoCri(m_stDados, m_vlDados);
}

long cWFAtdTratamentoCri::alterarGrupoDevolucaoCri()
{
    return proCAlterarWFGrupoDevolucaoCri(m_stDados, m_vlDados);
}

bool cWFAtdTratamentoCri::processoDentroPrazoRetInboxSimNao(long idAtendimento)
{
    return proCProcessoDentroPrazoRetornoInbox(idAtendimento);
}

long cWFAtdTratamentoCri::excluirAtendimentoCri(long idAtendimento)
{
    return proCExcluirWFAtendimentoCri(idAtendimento);
}

long cWFAtdTratamentoCri::excluirTratamentoCri()
{
    // apaga dados de ATENDIMENTO.TRATAMENTOGRUPOCRI e ATENDIMENTO.TRATAMENTOCRI
    return proCExcluirWFTratamentoCri(m_stDados, m_vlDados);
}

bool cWFAtdTratamentoCri::getGrupoCri(long idAtendimento)
{
    st_AtdTratamentoCri m_stDados;
    st_vlAtdTratamentoCri m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento = idAtendimento;
    m_vlDados.idAtendimento = 1;

    return proCObtemWFGrupoCri(&m_stDados,&m_vlDados, 0 );
}

bool cWFAtdTratamentoCri::getGrupoCriEx(long idAtendimento)
{
    st_AtdTratamentoCri m_stDados;
    st_vlAtdTratamentoCri m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento = idAtendimento;
    m_vlDados.idAtendimento = 1;

    return proCObtemWFGrupoCriEx(&m_stDados,&m_vlDados, 0 );
}

bool cWFAtdTratamentoCri::getGrupoCri()
{
    if (m_vlDados->idAtendimento == -1)
        return false;

    return proCObtemWFGrupoCri(m_stDados, m_vlDados, saida );
}

bool cWFAtdTratamentoCri::getTratamentoCri()
{
    if (m_vlDados->idAtendimento == -1)
        return false;

    return proCObtemWFTratamentoCri(m_stDados, m_vlDados, saida );
}

bool cWFAtdTratamentoCri::getTratamentoGrupoCri()
{
    if (m_vlDados->idAtendimento == -1)
        return false;

    return proCObtemWFTratamentoGrupoCri(m_stDados, m_vlDados, saida );
}

bool cWFAtdTratamentoCri::getAtendimentoCri()
{
    if (m_vlDados->idAtendimento == -1)
        return false;

    return proCObtemWFAtendimentoCri(m_stDados, m_vlDados, saida );
}

bool cWFAtdTratamentoCri::getAtendimentoCriEx()
{
    if (m_vlDados->idAtendimento == -1)
        return false;

    return proCObtemWFAtendimentoCriEx(m_stDados, m_vlDados, saida );
}

int cWFAtdTratamentoCri::getUsuarioAtualCri(long idAtendimento)
{
    if (idAtendimento == -1)
        return false;

    return proCObtemWFUsuarioAtualCri(idAtendimento);
}

int cWFAtdTratamentoCri::getGrupo()
{
    return ( m_stDados->idGrupo ); 
}

long cWFAtdTratamentoCri::getPessoaLinhaHistorico()
{
    return ( m_stDados->idPessoaLinhaHistorico ); 
}

int cWFAtdTratamentoCri::getIdPessoaUsuarioCri()
{
    return ( m_stDados->idPessoaUsuario ); 
}

bool cWFAtdTratamentoCri::usuarioIsSupervisor(int idGrupo) 
{
    ULOG_START( "cWFAtdTratamentoCri::usuarioIsSupervisor()" );

    bool isSupervisor;
    cWFUsuario usuario;
    
    //Verifica se o usuario é ou nao supervisor do grupo CRI
    usuario.getUsuarioSupervisorSimNao( getIdPessoaUsuarioCri(), idGrupo, isSupervisor );
    
    ULOG("isSupervisor:[%d]", isSupervisor);
    ULOG("idGrupo:[%d]", idGrupo);

    ULOG_END( "cWFAtdTratamentoCri::usuarioIsSupervisor()" );

    return isSupervisor;
}

void cWFAtdTratamentoCri::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    if ( m_stDados ) memset(m_stDados,0,sizeof(st_AtdTratamentoCri));
    if ( m_vlDados ) memset(m_vlDados,-1,sizeof(st_vlAtdTratamentoCri));

    if ( !entrada )
    {
        return;
    }

    char* p;

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
    {
        m_stDados->idAtendimento = atol( p );
        m_vlDados->idAtendimento = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idGrupo", 0 ), p ) 
    {
        m_stDados->idGrupo = atoi( p );
        m_vlDados->idGrupo = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p ) 
    {
        m_stDados->idPessoaUsuario = atoi( p );
        m_vlDados->idPessoaUsuario = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtAbertura", 0 ), p ) 
    {
        strcpy(m_stDados->dtAbertura, p );
        m_vlDados->dtAbertura = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idPessoaLinhaHistorico", 0 ), p ) 
    {
        m_stDados->idPessoaLinhaHistorico = atol( p );
        m_vlDados->idPessoaLinhaHistorico = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
    {
        m_stDados->idUsuarioAlteracao = atoi( p );
        m_vlDados->idUsuarioAlteracao = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
    {
        strcpy(m_stDados->dtUltimaAlteracao, p );
        m_vlDados->dtUltimaAlteracao = 1;
        XMLString::release(&p);
    }
}
