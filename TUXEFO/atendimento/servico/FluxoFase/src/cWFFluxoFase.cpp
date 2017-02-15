/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/ 


#include "../include/cWFFluxoFase.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/definesAtendimento.h"

extern bool proCFFObtemWFAcoesCri(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, Collection *colecaoAcoes);
extern bool proCFFObtemWFAcoesBko(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, Collection *colecaoAcoes);
extern bool proCFFObtemWFAcoesGrupo (int sIdContato, int sIdGrupo, int sIdAgrupamentoEstadoTpProc, Collection *colecaoAcoes);
extern bool proCFFObtemWFAcoesFase1Cri(int sIdContato, int sIdAgrupamentoEstadoTpProc, Collection *colecaoAcoes);
extern bool proCObtemWFAcoesRC(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, Collection *colecaoAcoes);
extern bool proCObtemWFAcoesRCAP(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, Collection *colecaoAcoes);
//extern bool proCFFObtemWFAcoesCri(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamento, XMLGen* saida);
//extern bool proCFFObtemWFAcoesBko(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamento, XMLGen* saida);
//extern bool proCFFObtemWFAcoesGrupo(int sIdContato, int sIdGrupo, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
//extern bool proCFFObtemWFAcoesFase1Cri(int sIdContato, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
//extern bool proCObtemWFAcoesRC (int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, XMLGen* saida);
//extern bool proCObtemWFAcoesRCAP(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, XMLGen* saida);

extern bool proCFFIncluirWFFluxoFase(st_FluxoFase* dados, st_vlFluxoFase* status, XMLGen* saida);
extern bool proCFFAlterarWFFluxoFase(st_FluxoFase* dados, st_vlFluxoFase* status, XMLGen* saida);
extern bool proCFFExcluirWFFluxoFase(st_FluxoFase* dados, st_vlFluxoFase* status, XMLGen* saida);
extern bool proCFFConsultaWFFluxoFase(st_FluxoFase* dados, st_vlFluxoFase* status, char* order, XMLGen* saida);
extern bool proCFFObtemWFAcoes(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
extern bool proCFFObtemWFAcoesBko(int sIdContato, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, Collection *colecaoAcoes);
extern bool proCFFObtemWFFluxo(int sIdContato, int idAtividade, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
extern bool proCFFObtemWFFluxoCri(int sIdContato, int idAtividade, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdTipoRelacionamentoBko, XMLGen* saida);
extern bool proCFFObtemWFCancelamento(int sIdAtividade, int sIdFase, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
extern bool proCFFObtemWFFluxoFuturo(int sIdContato, int sIdAtividade, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdAgrupamentoEstadoTpProcFt, int sIdFase, XMLGen* saida);
extern bool proCFFObtemWFFluxoFuturoBko(int sIdContato, int sIdAtividade, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdAgrupamentoEstadoTpProcFt, int sIdFase, int sIdTipoRelacionamentoBko, XMLGen* saida);
extern bool proCFFObtemWFFluxoFuturoBkoRC(int sIdAtividade,int sIdAgrupamentoEstadoTpProc,int sIdAgrupamentoEstadoTpProcFt,int sIdFase,int sIdTipoRelacionamentoBko,XMLGen* saida);
extern bool proCFFObtemWFFluxoFuturoCri(int sIdContato, int sIdAtividade, int sIdPessoaUsuario, int sIdAgrupamentoEstadoTpProc, int sIdAgrupamentoEstadoTpProcFt, int sIdFase, int sIdTipoRelacionamentoBko, XMLGen* saida);
extern bool proCFFObtemWFAcoesAbertura (st_FluxoFase* dados,st_vlFluxoFase* status,Collection *colecaoAcoes);
extern bool proCFFObtemWFAcoesAberturaCore(st_FluxoFase* dados, st_vlFluxoFase* status, XMLGen* saida);
extern bool proCFFObtemWFFluxoAtividade(int sIdContato, int sIdAtividade, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
extern bool proCFFObtemWFFluxoAtividadePout(int sIdContato, int sIdAtividade, int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
extern bool proCFFObtemWFFluxoFuturoRC(int sIdAtividade,int sIdAgrupamentoEstadoTpProc,int sIdAgrupamentoEstadoTpProcFt,int sIdFase,XMLGen* saida);
extern bool proCFFObtemWFFluxoRC(int sIdAtividade,int sIdAgrupamentoEstadoTpProc, XMLGen* saida);
extern bool proCFFObtemWFFluxoPout(int sIdAtividade,int sIdAgrupamentoEstadoTpProc,XMLGen* saida);

extern int proCObterTipoRelacionamentoBKO(int _tipoRelacionamentoBko);
extern int proCFFObtemWFUsuarioAtualCri(long sIdAtendimento);

cWFFluxoFase::cWFFluxoFase(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}

bool cWFFluxoFase::incluir()
{
    ULOG_START("cWFFluxoFase::incluir()");

    bool retorno = proCFFIncluirWFFluxoFase(&m_stDados, &m_vlDados, saida);

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::incluir()");

    return retorno;
}

int cWFFluxoFase::alterar()
{
    ULOG_START("cWFFluxoFase::alterar()");

    int retorno = -1;

    if (m_vlDados.idFluxoFase != -1) 
    {
        retorno = proCFFAlterarWFFluxoFase(&m_stDados, &m_vlDados, saida);
    }

    ULOG("retorno=%d", retorno);

    ULOG(("< alterar()"));

    return retorno;
}

int cWFFluxoFase::excluir()
{
    ULOG(("> excluir()"));

    int retorno = -1;

    if (m_vlDados.idFluxoFase != -1) 
    {
        retorno = proCFFExcluirWFFluxoFase(&m_stDados, &m_vlDados, saida);
    }

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::alterar()");

    return retorno;
}

bool cWFFluxoFase::consultar()
{
    ULOG_START("cWFFluxoFase::consultar()");

    bool retorno;
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

    retorno = proCFFConsultaWFFluxoFase(&m_stDados, &m_vlDados, order, saida);

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::consultar()");

    return retorno;
}

// bool cWFFluxoFase::ObtemWFAcoes(st_FluxoFase *dados, st_vlFluxoFase *status,XMLGen *saida,int idTipoProcesso)
// {
//     ULOG(("> ObtemWFAcoes()"));
// 
//     if ( !dados->inRC )
//     {
//         if ( status->idContato == -1 ) 
//         { 
//             ULOG(("< ObtemWFAcoes()"));
//             return false; 
//         }
// 
//         if ( status->idPessoaUsuario == -1 )
//         { 
//             ULOG(("< ObtemWFAcoes()"));
//             return false;
//         }
// 
//         if ( status->idAgrupamentoEstadoTpProc == -1 )
//         { 
//             ULOG(("< ObtemWFAcoes()"));
//             return false;
//         }
//     }
// 
//     bool retorno;
// 
//     if ( dados->idGrupo == 0 )
//     {
//      getTipoRelacionamento(dados, status);
// 
//      if (dados->inCri == 0)
//         {
//             if (dados->inRC == 0)
//             {
//              retorno = proCFFObtemWFAcoesBko(dados->idContato
//                                             ,dados->idPessoaUsuario
//                                             ,dados->idAgrupamentoEstadoTpProc
//                                             ,dados->idTipoRelacionamentoBko
//                                             ,saida);
//             }
//             else if (dados->inRC == 2)
//             {
//                 retorno = proCObtemWFAcoesRCAP(dados->idContato
//                                            ,dados->idPessoaUsuario
//                                            ,dados->idAgrupamentoEstadoTpProc
//                                            ,dados->idTipoRelacionamentoBko
//                                            ,saida);
//             }
//             else
//             {
//                 retorno = proCObtemWFAcoesRC(dados->idContato
//                                          ,dados->idPessoaUsuario
//                                          ,dados->idAgrupamentoEstadoTpProc
//                                          ,dados->idTipoRelacionamentoBko
//                                          ,saida);
//             }
//         }
//         else
//         {
//          retorno = proCFFObtemWFAcoesCri(dados->idContato
//                                         ,dados->idPessoaUsuario
//                                         ,dados->idAgrupamentoEstadoTpProc
//                                         ,dados->idTipoRelacionamentoBko
//                                         ,saida );
//         }
//     }
//     else
//     {
//         if ( idTipoProcesso == PROCESSO_CRI )
//         {
//          retorno = proCFFObtemWFAcoesFase1Cri(dados->idContato
//                                                 ,dados->idAgrupamentoEstadoTpProc
//                                                 ,saida );
// 
//         }
//         else
//         {
//      retorno = proCFFObtemWFAcoesGrupo(dados->idContato
//                                       ,dados->idGrupo
//                                       ,dados->idAgrupamentoEstadoTpProc
//                                           ,saida );
//     }
//     }
// 
// 
//     ULOG(("< ObtemWFAcoes()"));
// 
//     return retorno;
// }

bool cWFFluxoFase::ObtemWFAcoes(st_FluxoFase *dados, st_vlFluxoFase *status,Collection *colecaoAcoes,int idTipoProcesso)
{
    ULOG_START("cWFFluxoFase::ObtemWFAcoes(st_FluxoFase *, st_vlFluxoFase *,Collection *,int )");

    if ( !dados->inRC )
    {
        if ( status->idContato == -1 ) 
        { 
            ULOG_END("cWFFluxoFase::ObtemWFAcoes()");
            return false; 
        }

        if ( status->idPessoaUsuario == -1 )
        { 
            ULOG_END("cWFFluxoFase::ObtemWFAcoes()");
            return false;
        }

        if ( status->idAgrupamentoEstadoTpProc == -1 )
        { 
            ULOG_END("cWFFluxoFase::ObtemWFAcoes()");
            return false;
        }
    }

    bool retorno;

    if ( dados->idGrupo == 0 )
    {
        getTipoRelacionamento(dados, status);

        if (dados->inCri == 0)
        {
            if (dados->inRC == 0)
            {
                retorno = proCFFObtemWFAcoesBko(dados->idContato
                                               ,dados->idPessoaUsuario
                                               ,dados->idAgrupamentoEstadoTpProc
                                               ,dados->idTipoRelacionamentoBko
                                               ,colecaoAcoes);
            }
            else if (dados->inRC == 2)
            {
                retorno = proCObtemWFAcoesRCAP(dados->idContato
                                              ,dados->idPessoaUsuario
                                              ,dados->idAgrupamentoEstadoTpProc
                                              ,dados->idTipoRelacionamentoBko
                                              ,colecaoAcoes);
            }
            else
            {
                retorno = proCObtemWFAcoesRC(dados->idContato
                                            ,dados->idPessoaUsuario
                                            ,dados->idAgrupamentoEstadoTpProc
                                            ,dados->idTipoRelacionamentoBko
                                            ,colecaoAcoes);
            }
        }
        else
        {
            retorno = proCFFObtemWFAcoesCri(dados->idContato
                                           ,dados->idPessoaUsuario
                                           ,dados->idAgrupamentoEstadoTpProc
                                           ,dados->idTipoRelacionamentoBko
                                           ,colecaoAcoes );
        }
    }
    else
    {
        if ( idTipoProcesso == PROCESSO_CRI )
        {
            retorno = proCFFObtemWFAcoesFase1Cri(dados->idContato
                                                ,dados->idAgrupamentoEstadoTpProc
                                                ,colecaoAcoes );

        }
        else
        {
            retorno = proCFFObtemWFAcoesGrupo(dados->idContato
                                             ,dados->idGrupo
                                             ,dados->idAgrupamentoEstadoTpProc
                                             ,colecaoAcoes );
        }
    }

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFAcoes(st_FluxoFase *, st_vlFluxoFase *,Collection *,int )");
  

    return retorno;
}

bool cWFFluxoFase::ObtemWFAcoes()
{
    ULOG_START("cWFFluxoFase::ObtemWFAcoes()");

    if ( m_vlDados.idContato == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoes()");
        return false; 
    }

    if ( m_vlDados.idPessoaUsuario == -1 )
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoes()");
        return false;
    }

    if ( m_vlDados.idAgrupamentoEstadoTpProc == -1 )
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoes()");
        return false;
    }

    bool retorno = proCFFObtemWFAcoes(m_stDados.idContato
                                     ,m_stDados.idPessoaUsuario
                                     ,m_stDados.idAgrupamentoEstadoTpProc
                                     ,saida );

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFAcoes()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFAcoesAbertura(st_FluxoFase *dados, st_vlFluxoFase *status,Collection *colecaoAcoes)
{
    ULOG_START("cWFFluxoFase::ObtemWFAcoesAbertura()");

    if ( status->idContato == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoesAbertura()");
        return false; 
    }

    if ( status->idGrupo == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoesAbertura()");
        return false; 
    }

    if ( colecaoAcoes == 0 ) 
    { 
        ULOG("ponteiro invalido");

        ULOG_END("cWFFluxoFase::ObtemWFAcoesAbertura()");
        return false; 
    }

    bool retorno = proCFFObtemWFAcoesAbertura(dados, status, colecaoAcoes );

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFAcoesAbertura()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFAcoesAberturaCore(st_FluxoFase *dados, st_vlFluxoFase *status,XMLGen *saida)
{
    ULOG_START("cWFFluxoFase::ObtemWFAcoesAberturaCore()");

    if ( status->idContato == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoesAberturaCore()");
        return false; 
    }

    if ( status->idGrupo == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFAcoesAberturaCore()");
        return false; 
    }

    bool retorno = proCFFObtemWFAcoesAberturaCore(dados, status, saida );

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFAcoesAberturaCore()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFFluxo(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida)
{
    ULOG_START("cWFFluxoFase::ObtemWFFluxo(st_FluxoFase *,st_vlFluxoFase *,XMLGen *)");

    if ( status->idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo(st_FluxoFase *,st_vlFluxoFase *,XMLGen *)");
        return false; 
    }

    if ( status->idPessoaUsuario == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo(st_FluxoFase *,st_vlFluxoFase *,XMLGen *)");
        return false; 
    }

    if ( status->idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo(st_FluxoFase *,st_vlFluxoFase *,XMLGen *)");
        return false; 
    }

    getTipoRelacionamento(dados, status);
    
    bool retorno;

    if (dados->inPortout)
    {
        retorno = proCFFObtemWFFluxoPout( dados->idAtividade
                                        , dados->idAgrupamentoEstadoTpProc
                                        , saida );
    }
    else if (dados->inCri)
    {
        retorno = proCFFObtemWFFluxoCri( dados->idContato
                                       , dados->idAtividade
                                       , dados->idPessoaUsuario
                                       , dados->idAgrupamentoEstadoTpProc
                                       , dados->idTipoRelacionamentoBko
                                       , saida );
    }
    else if (dados->inRC)
    {
        retorno = proCFFObtemWFFluxoRC( dados->idAtividade
                                      , dados->idAgrupamentoEstadoTpProc
                                      , saida );
    }
    else
    {
        retorno = proCFFObtemWFFluxo( dados->idContato
                                    , dados->idAtividade
                                    , dados->idPessoaUsuario
                                    , dados->idAgrupamentoEstadoTpProc
                                    , saida );
    }

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFFluxo(st_FluxoFase *,st_vlFluxoFase *,XMLGen *)");

    return retorno;
}

bool cWFFluxoFase::ObtemWFFluxo()
{
    ULOG_START("cWFFluxoFase::ObtemWFFluxo()");

    if ( m_vlDados.idContato == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo()");
        return false; 
    }

    if ( m_vlDados.idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo()");
        return false; 
    }

    if ( m_vlDados.idPessoaUsuario == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo()");
        return false; 
    }

    if ( m_vlDados.idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxo()");
        return false; 
    }

    bool retorno = proCFFObtemWFFluxo(m_stDados.idContato
                                     ,m_stDados.idAtividade
                                     ,m_stDados.idPessoaUsuario
                                     ,m_stDados.idAgrupamentoEstadoTpProc
                                     ,saida);

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFFluxo()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFFluxoFt(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida)
{
    ULOG_START("cWFFluxoFase::ObtemWFFluxoFt()");

    if ( status->idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( status->idPessoaUsuario == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( status->idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( status->idAgrupamentoEstadoTpProcFt == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( status->idFase == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    getTipoRelacionamento(dados, status);
    
    ULOG("inCri=%d", dados->inCri);

    bool retorno;

    if (dados->inCri > 0)
    {
        retorno = proCFFObtemWFFluxoFuturoCri( dados->idContato
                                             , dados->idAtividade
                                             , dados->idPessoaUsuario
                                             , dados->idAgrupamentoEstadoTpProc
                                             , dados->idAgrupamentoEstadoTpProcFt
                                             , dados->idFase
                                             , dados->idTipoRelacionamentoBko
                                             , saida );
    }
    else
    {
        if (dados->inRC == 0)
        {
            retorno = proCFFObtemWFFluxoFuturoBko( dados->idContato
                                                 , dados->idAtividade
                                                 , dados->idPessoaUsuario
                                                 , dados->idAgrupamentoEstadoTpProc
                                                 , dados->idAgrupamentoEstadoTpProcFt
                                                 , dados->idFase
                                                 , dados->idTipoRelacionamentoBko
                                                 , saida );
        }
        else
        {
            retorno = proCFFObtemWFFluxoFuturoBkoRC( dados->idAtividade
                                                   , dados->idAgrupamentoEstadoTpProc
                                                   , dados->idAgrupamentoEstadoTpProcFt
                                                   , dados->idFase
                                                   , dados->idTipoRelacionamentoBko
                                                   , saida );
        }
    }

    ULOG("retorno=%d", retorno);

        
    ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFFluxoAtividade(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida)
{
    ULOG_START("cWFFluxoFase::ObtemWFFluxoAtividade()");

    if ( status->idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoAtividade()");
        return false; 
    }

    if ( status->idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoAtividade()");
        return false; 
    }

    bool retorno = proCFFObtemWFFluxoAtividade( dados->idContato
                                              , dados->idAtividade
                                              , dados->idAgrupamentoEstadoTpProc
                                              , saida );

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFFluxoAtividade()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFFluxoAtividadePOut(st_FluxoFase *dados,st_vlFluxoFase *status,XMLGen *saida)
{
    ULOG_START("cWFFluxoFase::ObtemWFFluxoAtividadePOut()");

    if ( status->idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoAtividadePOut()");
        return false; 
    }

    if ( status->idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoAtividadePOut()");
        return false; 
    }

    bool retorno = proCFFObtemWFFluxoAtividadePout( dados->idContato
                                                  , dados->idAtividade
                                                  , dados->idAgrupamentoEstadoTpProc
                                                  , saida );

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFFluxoAtividadePOut()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFFluxoFt()
{
    ULOG_START("cWFFluxoFase::ObtemWFFluxoFt()");

    if ( m_vlDados.idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( m_vlDados.idPessoaUsuario == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( m_vlDados.idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( m_vlDados.idAgrupamentoEstadoTpProcFt == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    if ( m_vlDados.idFase == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");
        return false; 
    }

    bool retorno;

    if ( m_stDados.inRC == 0 ) 
    {
        retorno = proCFFObtemWFFluxoFuturo( m_stDados.idContato
                                          , m_stDados.idAtividade
                                          , m_stDados.idPessoaUsuario
                                          , m_stDados.idAgrupamentoEstadoTpProc
                                          , m_stDados.idAgrupamentoEstadoTpProcFt
                                          , m_stDados.idFase
                                          , saida );
    }
    else
    {
        retorno = proCFFObtemWFFluxoFuturoRC( m_stDados.idAtividade
                                            , m_stDados.idAgrupamentoEstadoTpProc
                                            , m_stDados.idAgrupamentoEstadoTpProcFt
                                            , m_stDados.idFase
                                            , saida );
    }

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFFluxoFt()");

    return retorno;
}

bool cWFFluxoFase::ObtemWFCanc(st_FluxoFase *dados, st_vlFluxoFase *status,XMLGen *saida)
{
    ULOG_START("cWFFluxoFase::ObtemWFCanc(st_FluxoFase *, st_vlFluxoFase *,XMLGen *)");

    if ( status->idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFCanc(st_FluxoFase *, st_vlFluxoFase *,XMLGen *)");
        return false; 
    }

    if ( status->idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFCanc(st_FluxoFase *, st_vlFluxoFase *,XMLGen *)");
        return false; 
    }

    if ( status->idFase == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFCanc(st_FluxoFase *, st_vlFluxoFase *,XMLGen *)");
        return false; 
    }

    bool retorno = proCFFObtemWFCancelamento( dados->idAtividade
                                            , dados->idFase
                                            , dados->idAgrupamentoEstadoTpProc
                                            , saida );

    ULOG_END("cWFFluxoFase::ObtemWFCanc(st_FluxoFase *, st_vlFluxoFase *,XMLGen *)");

    return retorno;
}

bool cWFFluxoFase::ObtemWFCanc()
{
    ULOG_START("cWFFluxoFase::ObtemWFCanc()");

    if ( m_vlDados.idAtividade == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFCanc()");
        return false; 
    }

    if ( m_vlDados.idAgrupamentoEstadoTpProc == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFCanc()");
        return false; 
    }

    if ( m_vlDados.idFase == -1 ) 
    { 
        ULOG_END("cWFFluxoFase::ObtemWFCanc()");
        return false; 
    }
    
    bool retorno = proCFFObtemWFCancelamento( m_stDados.idAtividade
                                            , m_stDados.idFase
                                            , m_stDados.idAgrupamentoEstadoTpProc
                                            , saida );

    ULOG("retorno=%d", retorno);

    ULOG_END("cWFFluxoFase::ObtemWFCanc()");

    return retorno;
}

void cWFFluxoFase::getTipoRelacionamento(st_FluxoFase *dados,st_vlFluxoFase *status)
{
    ULOG_START("cWFFluxoFase::getTipoRelacionamento()");

    if (dados->inRC)
    {
        dados->idTipoRelacionamentoBko = proCObterTipoRelacionamentoBKO(ANALISTA_RESP_CLI);
    }
    else if (dados->inPortout)
    {
        dados->idTipoRelacionamentoBko = proCObterTipoRelacionamentoBKO(ANALISTA_DE_RETENCAO);
    }
    else
    {
        if (dados->inCri == 0)
        {
            if (dados->inSupervisor == 1)
            {
                if (dados->idUsuarioCri > 0)
                {
                    dados->idTipoRelacionamentoBko = 
                            proCObterTipoRelacionamentoBKO(SUPERVISOR_BKO_COM_CRI);
                }
                else
                {
                    dados->idTipoRelacionamentoBko = 
                            proCObterTipoRelacionamentoBKO(SUPERVISOR_BKO);
                }
            }
            else
            {
                if (dados->idUsuarioCri > 0)
                {
                    if ( dados->idFase == ABERTURA )
                    { // se fase = abertura, então chamado partiu da tela inicial e neste
                      // caso nunca é o CRI responsável.
                        dados->idTipoRelacionamentoBko = 
                                proCObterTipoRelacionamentoBKO(CRI_NAO_RESPONSAVEL);
                    }
                    else
                    {
                        dados->idTipoRelacionamentoBko = 
                                proCObterTipoRelacionamentoBKO(ANALISTA_BKO_COM_CRI);
                    }
                }
                else
                {
                    dados->idTipoRelacionamentoBko = 
                            proCObterTipoRelacionamentoBKO(ANALISTA_BKO_SEM_CRI);
                }
            }
        }
        else if (dados->inCri)
        {
            //int idUsuarioAtual = proCFFObtemWFUsuarioAtualCri(dados->idAtendimento);
            int idUsuarioAtual = status->idUsuarioCri == 1?dados->idUsuarioCri:0;

            if ( (idUsuarioAtual > 0) && (dados->idUsuarioCri == idUsuarioAtual) )
            {
                dados->idTipoRelacionamentoBko = 
                        proCObterTipoRelacionamentoBKO(dados->inCri==1?CRI_RESPONSAVEL
                                             :SUPERVISOR_CRI);
            }
            else
            {
                dados->idTipoRelacionamentoBko = 
                    proCObterTipoRelacionamentoBKO(dados->inCri==1?CRI_NAO_RESPONSAVEL
                                            :SUPERVISOR_CRI);
            }
        }
        //else
        //{
        //    dados->idTipoRelacionamentoBko = 
        //            proCObterTipoRelacionamentoBKO(SUPERVISOR_CRI);
        //}
    }

    if ( dados->idTipoRelacionamentoBko > 0 )
    {
        status->idTipoRelacionamentoBko = 1;
    }

    ULOG_END("cWFFluxoFase::getTipoRelacionamento()");
}

void cWFFluxoFase::carregaDados()
{
    ULOG_START("cWFFluxoFase::carregaDados()");

    char *p;

    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( p=tx.walkTree( entrada, "idFluxoFase", 0 ),p ) 
    {
        m_stDados.idFluxoFase = atoi(p);
        m_vlDados.idFluxoFase = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idAtendimento", 0 ),p ) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idFase", 0 ),p ) 
    {
        m_stDados.idFase = atoi(p);
        m_vlDados.idFase = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idFluxo", 0 ),p ) 
    {
        m_stDados.idFluxo = atoi(p);
        m_vlDados.idFluxo = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idGrupo", 0 ),p ) 
    {
        m_stDados.idGrupo = atoi(p);
        m_vlDados.idGrupo = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "nmURLDestino", 0 ),p ) 
    {
        strcpy(m_stDados.nmURLDestino, p);
        m_vlDados.nmURLDestino = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "nmFuncValidacao", 0 ),p ) 
    {
        strcpy(m_stDados.nmFuncValidacao, p);
        m_vlDados.nmFuncValidacao = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idContato", 0 ),p ) 
    {
        m_stDados.idContato = atoi(p);
        m_vlDados.idContato = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idAtividade", 0 ),p ) 
    {
        m_stDados.idAtividade = atoi(p);
        m_vlDados.idAtividade = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idPessoaUsuario", 0 ),p ) 
    {
        m_stDados.idPessoaUsuario = atoi(p);
        m_vlDados.idPessoaUsuario = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idAgrupamentoEstadoTpProc", 0 ),p ) 
    {
        m_stDados.idAgrupamentoEstadoTpProc = atoi(p);
        m_vlDados.idAgrupamentoEstadoTpProc = 1;
        XMLString::release(&p);
    }

    if ( p=tx.walkTree( entrada, "idAgrupamentoEstadoTpProcFt", 0 ),p ) 
    {
        m_stDados.idAgrupamentoEstadoTpProcFt = atoi(p);
        m_vlDados.idAgrupamentoEstadoTpProcFt = 1;
        XMLString::release(&p);
    }

    ULOG_END("cWFFluxoFase::carregaDados()");
}
