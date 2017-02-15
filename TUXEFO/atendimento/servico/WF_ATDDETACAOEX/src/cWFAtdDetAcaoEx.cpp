/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:18 $
 **/

#include "../include/cWFAtdDetAcaoEx.h"

#include "../../AtdTratamentoCri/include/cWFAtdTratamentoCri.h"

#include "../../CondicaoAparicao/include/cWFCondicaoAparicao.h"
#include "../../FluxoFase/include/cWFFluxoFase.h"

extern bool proCUsuarioPossuiPerfilAbertura(int _idPessoaUsuario,int _idContato);

WFAtdDetAcaoEx::WFAtdDetAcaoEx(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    memset(&m_stAtdBaixa,0,sizeof(m_stAtdBaixa));
    memset(&m_vlAtdBaixa,-1,sizeof(m_vlAtdBaixa));

    carregaDados();
}

bool WFAtdDetAcaoEx::executar()
{
    ULOG_START("WFAtdDetAcaoEx::executar()"); 

    saida->createTag("AtendimentoVO");
    saida->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

    ObterDetalheAtdEx();

    ObterAcoesEx( saida );

    saida->closeTag();

    ULOG_END("WFAtdDetAcaoEx::executar()"); 

    return true;
}

void WFAtdDetAcaoEx::ObterDetalheAtdEx()
{
    ULOG_START("WFAtdDetAcaoEx::ObterDetalheAtd()"); 

    cWFAtendimento cwfAtendimento;

    if ( cwfAtendimento.ObtemDetalheAtendEx(m_stDados.idAtendimento,&detalheAtendimento) == false )
    {
        throw new TuxBasicSvcException("00E0000","Falha na obtenção do detalhe do processo");
    }

    ULOG_END("WFAtdDetAcaoEx::ObterDetalheAtd()"); 
}

void WFAtdDetAcaoEx::ObterAcoesEx(XMLGen* _montagem)
{
    ULOG_START("WFAtdDetAcaoEx::ObterAcoes()"); 

    cWFCondicaoAparicao cwfCondicaoAparicao;
    cWFFluxoFase cwfFluxoFase;
    Collection colecaoAcoes;
    int idAtividadeAt=0;
    SmallString nmFuncValidacaoAt;
    st_CondicaoAparicao dadosCondicao;
    st_vlCondicaoAparicao statusCondicao;
    st_FluxoFase dados;
    st_vlFluxoFase status;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idAtendimento = m_stDados.idAtendimento;
    status.idAtendimento = m_vlDados.idAtendimento;
   
    dados.idContato = detalheAtendimento.idContato;
    status.idContato = 1;

    dados.idPessoaUsuario = obterIdUsuario();
    status.idPessoaUsuario = 1;

    dados.idAgrupamentoEstadoTpProc = detalheAtendimento.idAgrEstadoTProc;
    status.idAgrupamentoEstadoTpProc = 1;

    dados.idGrupo = m_stDados.idGrupo;
    status.idGrupo = m_vlDados.idGrupo;

    dados.inCri = m_stDados.inCri;
    status.inCri = m_vlDados.inCri;

    dados.inSupervisor = m_stDados.inSupervisor;
    status.inSupervisor = m_vlDados.inSupervisor;

    dados.inRC = m_stDados.inRC;
    status.inRC = m_vlDados.inRC;

    int tipoProcesso;
    if ( !dados.inCri )
    {
        // prencher esta variável só é importante quando este serviço é acessado
        // através de um usuário não CRI.
        tipoProcesso = processoCriSimNao()?PROCESSO_CRI:PROCESSO_BKO;
    }

    getUsuarioCri(dados.idPessoaUsuario);

    dados.idUsuarioCri = m_stDados.idUsuarioCri;
    status.idUsuarioCri = m_vlDados.idUsuarioCri;

    ULOG("idContato=%d", dados.idContato);
    ULOG("idPessoaUsuario=%d", dados.idPessoaUsuario);
    ULOG("idAgrupamentoEstadoTpProc=%d",dados.idAgrupamentoEstadoTpProc);

    if ( cwfFluxoFase.ObtemWFAcoes(&dados,&status,&colecaoAcoes,tipoProcesso) == false )
    {
        throw new TuxBasicSvcException("00E0000","cWFFluxoFase::ObtemWFAcoes() falhou execução");
    }

    memset(&dadosCondicao,0,sizeof(dadosCondicao));
    memset(&statusCondicao,-1,sizeof(statusCondicao));

    statusCondicao.idParametro = 1;
    statusCondicao.idFluxoFase = 1;
    statusCondicao.idAtividade = 1;
    statusCondicao.idAtividadeAt = 1;

    ULOG("idAtividadeAt=%d",idAtividadeAt);

    int nAcoes = colecaoAcoes.GetCount();

    for ( int i = 0; i < nAcoes ; i++ )
    {
        ACOES_FLUXO *af = (ACOES_FLUXO *) colecaoAcoes.GetItem(i);

        ULOG("_idAtividade: %d", af->idAtividade);
        // ULOG("_dsAtividade: %s", af->dsAtividade.c_str());
        ULOG("_nmFuncValidacao: %s", af->nmFuncValidacao.c_str());
        ULOG("_idFluxoFase: %d", af->idFluxoFase);

        if ( af->idAtividade > idAtividadeAt 
           || (dados.inCri && strcmp(nmFuncValidacaoAt.c_str(),af->nmFuncValidacao.c_str())) )
        {
            // Vai verificar se existe alguma condição impeditiva para que a ação
            // seja exibida na página web.

            dadosCondicao.idParametro = m_stDados.idAtendimento;
            dadosCondicao.idFluxoFase = af->idFluxoFase;
            dadosCondicao.idAtividade = af->idAtividade;
            dadosCondicao.idAtividadeAt = idAtividadeAt;

            if ( cwfCondicaoAparicao.ObtemWFCndAcoes(&dadosCondicao,&statusCondicao) )
            {
                // ======================================================================
                // Incidência 3203 - WR Sanity RJ - Maio, 2006 - Cassio
                // ======================================================================
                // "Resultado esperado: somente apresentar a operação INSERIR INSISTÊNCIA
                //  quando o usuário possuir perfil de abertura de processos".
                // "Tatiana Fernandez <cons_tatianaf>, 24/4/2006:  Conforme esclarecido 
                //  hoje com Antônio Paulo, o "Perfil de Abertura" dito nesta incidência,
                //  refere-se aos grupos de abertura configurados na folha do processo.
                //  Ou seja, para o analista de BKO ter disponível a operação 
                //  INSERIR INSISTÊNCIA através de sua inbox, deverá estar associado a um
                //  dos Grupos de Abertura da folha. Isso vale para o CRI e para o 
                //  Supervisor também.".
                //
                //  No caso de analista de FO esta operação é exibida mesmo nos casos em
                //  que o analista não possui perfil de abertura.
                // ======================================================================
                //
                bool exibirAcao = true;

                if ( af->idAtividade == INSERIR_INSISTENCIA_I && m_stDados.inFO == 0 )
                {
                    exibirAcao = usuarioPossuiPerfilAbertura();
                }
                else if ( af->idAtividade == REABRIR_RB )
                {
                    // Incidência 3887 Homologação VIVO, Outubro 2006, Cassio
                    // Feito com base na incidência 1932 que pede que se o usuário
                    // que estiver executando a ação não participar de grupo de 
                    // abertura esta ação não deve ser exibida.
                    exibirAcao = usuarioPossuiPerfilAbertura();
                }

                if ( exibirAcao )
                {
                    char *nmFuncValidadaoPtr = af->nmFuncValidacao.c_str();

                    _montagem->createTag("WFAcaoVO");
                        _montagem->addItem("idAtividade", af->idAtividade);
                        _montagem->addItem("dsAtividade", af->dsAtividade.c_str());
                        // Tivemos de fazer uma "gambi" aqui pois o ambiente java duplicou 
                        // várias funções e não havia como resolver a chamada dos métodos
                        // duplicados sem que o nome do método duplicado fosse chamado 
                        // a partir daqui. - Setembro,2006 - Cassio.
                        if ( 1 == m_stDados.inFO )
                        {
                            if ( INSERIR_INSISTENCIA_I == af->idAtividade )
                            {
                                nmFuncValidadaoPtr = "insistenciaAbaRel()";
                            }
                            else if ( INSERIR_COMENTARIO_IC == af->idAtividade )
                            {
                                nmFuncValidadaoPtr = "inserirComentarioAbaRel()";
                            }
                            else if ( CANCELAR_C == af->idAtividade )
                            {
                                nmFuncValidadaoPtr = "cancelarAbaRel()";
                            }
                            else if ( REABRIR_RB == af->idAtividade || REINCIDENCIA_R == af->idAtividade )
                            {
                                nmFuncValidadaoPtr = "reaberturaAbaRel()";
                            }
                        }

                        _montagem->addItem("jsAtividade",nmFuncValidadaoPtr);
                    _montagem->closeTag();
                }
                else
                {
                    ULOG("Atividade %d removida da lista",af->idAtividade);
                }
            }
        }

        idAtividadeAt = af->idAtividade;
        nmFuncValidacaoAt = af->nmFuncValidacao;

        ULOG("idAtividadeAt=%d",idAtividadeAt);
        ULOG("nmFuncValidacaoAt=%d",nmFuncValidacaoAt);
    }

    ULOG_END("WFAtdDetAcaoEx::ObterAcoes()"); 
}

bool WFAtdDetAcaoEx::usuarioPossuiPerfilAbertura()
{
    ULOG_START("WFAtdDetAcaoEx::usuarioPossuiPerfilAbertura()"); 

    bool retorno = proCUsuarioPossuiPerfilAbertura(obterIdUsuario(),detalheAtendimento.idContato);

    ULOG_END("WFAtdDetAcaoEx::usuarioPossuiPerfilAbertura()"); 

    return retorno;
}

bool WFAtdDetAcaoEx::processoCriSimNao()
{
    ULOG_START("WFAtdDetAcaoEx::processoCriSimNao()"); 

    cWFAtdTratamentoCri cWFAtdTratamentoCri;

    bool retorno = cWFAtdTratamentoCri.getGrupoCriEx(m_stDados.idAtendimento);

    ULOG_END("WFAtdDetAcaoEx::processoCriSimNao()"); 

    return retorno;
}

void WFAtdDetAcaoEx::getUsuarioCri(int idPessoaUsuario)
{
    ULOG_START("WFAtdDetAcaoEx::getUsuarioCri()"); 

    st_AtdTratamentoCri dados;
    st_vlAtdTratamentoCri status;
    XMLGen saida;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idAtendimento = m_stDados.idAtendimento;
    status.idAtendimento = 1;

    cWFAtdTratamentoCri cWFAtdTratamentoCri(&dados,&status,&saida);
    
    if ( !cWFAtdTratamentoCri.getAtendimentoCriEx() )
    {
        m_stDados.idUsuarioCri = 0;
        m_vlDados.idUsuarioCri = -1;

        ULOG("idUsuarioCri=%d (nao é CRI)",m_stDados.idUsuarioCri);
    }
    else
    {
        m_stDados.idUsuarioCri = cWFAtdTratamentoCri.getIdPessoaUsuarioCri();
        if ( m_stDados.idUsuarioCri )
        {
            m_vlDados.idUsuarioCri = 1;

            ULOG("idUsuarioCri=%d",m_stDados.idUsuarioCri);
        }
        else
        {
            ULOG("idUsuarioCri=%d (nao é CRI)",m_stDados.idUsuarioCri);
        }
    }

    ULOG_END("WFAtdDetAcaoEx::getUsuarioCri()"); 
}

void WFAtdDetAcaoEx::carregaDados()
{
    ULOG_START("WFAtdDetAcaoEx::carregaDados()"); 

    char *p;
    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados.idAtendimento = atol(p);
        m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
        ULOG("idAtendimento=%d",m_stDados.idAtendimento);
    }
    else
    {
        ULOG("tag 'idAtendimento' nao fornecida");
    }

    if (p=tx.walkTree( entrada, "idGrupo", 0 ),p) 
    {
        m_stDados.idGrupo = atoi(p);
        m_vlDados.idGrupo = 1;
        XMLString::release(&p);
        ULOG("idGrupo=%d",m_stDados.idGrupo);
    }
    else
    {
        ULOG("tag 'idGrupo' nao fornecida");
    }

    if (p=tx.walkTree( entrada, "inCRI", 0 ),p) 
    {
        m_stDados.inCri = atoi(p);
        if ( m_stDados.inCri ) m_vlDados.inCri = 1;
        XMLString::release(&p);
        ULOG("inCri=%d",m_stDados.inCri);
    }
    else
    {
        ULOG("tag 'inCRI' nao fornecida");
    }

    if (p=tx.walkTree( entrada, "inRC", 0 ),p) 
    {
        m_stDados.inRC = atoi(p);
        if ( m_stDados.inRC ) m_vlDados.inRC = 1;
        XMLString::release(&p);
        ULOG("inRC=%d",m_stDados.inRC);
    }
    else
    {
        ULOG("tag 'inRC' nao fornecida");
    }

    if (p=tx.walkTree( entrada, "inSupervisor", 0 ),p) 
    {
        m_stDados.inSupervisor = 1;
        m_vlDados.inSupervisor = 1;
        XMLString::release(&p);
        ULOG("inSupervisor=%d",m_stDados.inSupervisor);
    }
    else
    {
        ULOG("tag 'inSupervisor' nao fornecida");
    }

    if (p=tx.walkTree( entrada, "inFO", 0 ),p) 
    {
        m_stDados.inFO = 1;
        m_vlDados.inFO = 1;
        XMLString::release(&p);
        ULOG("inFO=%d",m_stDados.inFO);
    }
    else
    {
        ULOG("tag 'inFO' nao fornecida");
    }

    ULOG_END("WFAtdDetAcaoEx::carregaDados()"); 
}
