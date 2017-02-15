/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.7.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/01/23 18:08:54 $
 **/

#include "../include/cWFAtdAttrUsuSem.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../Andamento/include/cWFAndamento.h"
#include "../../Motivo/include/cWFMotivo.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../AndamentoMotivo/include/cWFAndamentoMotivo.h"
#include "../../AndamentoTransferencia/include/cWFAndamentoTransferencia.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include "../../AtdTratamentoCri/include/cWFAtdTratamentoCri.h"
#include "../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include "../../AgrupamentoEstadoTpProc/include/cWFAgrupamentoEstadoTpProc.h"

extern bool proCBuscarDataHora( st_AtdAttrUsuSem *dados );
extern bool proCBuscarDataHoraSS( st_AtdAttrUsuSem *dados );
// (sem uso) extern bool proCParametroDiscadorAtivo( st_AtdAttrUsuSem *dados );
extern bool proCObterGruposAssociados( int _idUsuario );
extern bool proCObterGruposAssociadosCri( int _idUsuario );
extern bool proCObterGruposAssociadosRC( int _idUsuario );
extern bool proCObterAtdFilaAlerta( int _idUsuario, long &_idAtendimento );
extern bool proCObterAtdFilaAlertaNotSkill( int _idUsuario,long &_idAtendimento );
extern bool proCObterAtdFilaAlertaSkill( int _idUsuario, long &_idAtendimento );
//extern bool proCObterAtdFilaAlertaSkillSemLinha( int _idUsuario, int &_idAtendimento );
extern int proCObterInDisponivelWF( int _idUsuario );
extern bool proCBuscarEncAutoObs( char *_observacao, size_t sizeofObservacao );
extern bool proCObterAtdFilaCriLinha( int _idUsuario, long &_idAtendimento, long _idPessoaLinhaHistorico );
extern bool proCObterAtdFilaCri( int _idUsuario, long &_idAtendimento );
extern bool prCIncluir_MSG_Usuario_old(unsigned long _idAndamento, int _idUsuario, long _idPessoaUsuarioAtual);

extern int proCQtdeRegsAtdFilaUsuario( int _idUsuario );
extern int proCQtdeRegsAtdFilaUsuarioCri( int _idUsuario );

extern int  proCQtdeRegsAtdFilaUsuarioRC( int _idUsuario );
extern bool proCObterAtdFilaAlertaCPrevio(int _idUsuario, long &_idAtendimento,st_AtdAttrUsuSem *dados,XMLDPR *xmlDpr);
extern bool procCProcessoAssociadoUsuario(long idAtendimento);

//Funcoes utilizada para Skill de usuario
extern int proCUsuarioTemSkill( long idPessoaUsuario );
extern int proCUsuarioTemSkill( int idPessoaUsuario, int idGrupo );
extern int proCObterIdStatusUsuario( int _idUsuario );

cWFAtdAttrUsuSem::cWFAtdAttrUsuSem(DOMNode *dnode, XMLGen *xml_g)
{
    entrada = dnode;
    saida = xml_g;

    carregaDados();
}

cWFAtdAttrUsuSem::cWFAtdAttrUsuSem(DOMNode *dnode, XMLGen *xml_g,int idUsuario)
{
    entrada = dnode;
    saida = xml_g;

    setarIdUsuario(idUsuario);

    carregaDados();
}

bool cWFAtdAttrUsuSem::Executar()
{
    ULOG_START("cWFAtdAttrUsuSem::Executar()"); 

    bool retorno;
    int idProxAgrupEstadoTpProc;
    long proxIdAtendimento;

    SmallString me; // mensagem de erro

    int idUsuario = obterIdUsuario();
    if ( 1 == m_stDados.discador )
    {
        me  = "Usuario '";
        me += idUsuario;
        me += "' faz parte de um grupo de retorno e o discador esta ativo";

        saida->createTag("RetornoVO");
            saida->aggregateXML((char*)me);
        saida->closeTag();

        ULOG(mensagemSimples((char*)me));

        return true;
    }

    int numProcessos;

    if (m_stDados.inCri == 1)
    {
        numProcessos = proCQtdeRegsAtdFilaUsuarioCri(idUsuario);
    }
    else if (m_stDados.inRC == 1)
    {
        numProcessos = proCQtdeRegsAtdFilaUsuarioRC(idUsuario);
    }
    else
    {
        numProcessos = proCQtdeRegsAtdFilaUsuario(idUsuario);
    }

    if ( numProcessos > 0 )
    {
        me  = "Usuario '";
        me += idUsuario;
        me += "' ainda possui ";
        me += numProcessos;

        if ( numProcessos == 1 )
        {
            me += " processo a ser tratado";
        }
        else
        {
            me += " processos a serem tratados";
        }

        saida->createTag("RetornoVO");
            saida->aggregateXML((char*)me);
        saida->closeTag();

        ULOG(mensagemSimples((char*)me));

        ULOG_END("cWFAtdAttrUsuSem::Executar()"); 
        return true;
    }

    if ( proCObterInDisponivelWF(idUsuario) != 1 )
    {
        me = "Usuário indísponível para receber processo.";

        saida->createTag("RetornoVO");
            saida->aggregateXML((char*)me);
        saida->closeTag();

        ULOG(mensagemSimples((char*)me));

        ULOG_END("cWFAtdAttrUsuSem::Executar()"); 
        return true;
    }

    if (m_stDados.inRC == 1)
    {
        retorno = proCObterGruposAssociadosRC(idUsuario);
        if (!retorno ) me = "Usuário não possui grupos de contato prévio associados.";
    }
    else if (m_stDados.inCri == 1)
    {
        retorno = proCObterGruposAssociadosCri(idUsuario);
        if (!retorno ) me = "Usuário não possui grupos de CRI associados.";
    }
    else
    {
        retorno = proCObterGruposAssociados(idUsuario);
        if (!retorno ) me = "Usuário não possui grupos associados.";
    }

    if ( !retorno )
    {
        saida->createTag("RetornoVO");
            saida->aggregateXML((char*)me);
        saida->closeTag();

        ULOG(mensagemSimples((char*)me));

        ULOG_END("cWFAtdAttrUsuSem::Executar()"); 

        return true;
    }

    bool proximosAtendimentos = false;
    bool usuarioTemSkill;
    bool retProC;
    int teste = 1;
    int i = 0;
    int retry=0;
    int idMotivoEncAutomatico = 100;
    cWFMotivo cwfmotivo;
    cWFAtendimentoUsuarioAtual cwfAtendimentoUsuarioAtual;
    cWFAtendimento cwfAtendimento;

    //==========================================================================
    // Se usuário não esta ativo, não pode receber processo
    if ( proCObterIdStatusUsuario( idUsuario ) != 1 )
    {
        me = "Usuário não ativo não pode tratar processos.";

        saida->createTag("RetornoVO");
            saida->aggregateXML((char*)me);
        saida->closeTag();

        ULOG(mensagemSimples((char*)me));

        ULOG_END("cWFAtdAttrUsuSem::Executar()"); 

        return true;
    }

    //==========================================================================
    // Verifica se usuário é "skilled"
    usuarioTemSkill = proCUsuarioTemSkill(idUsuario) > 0?true:false;

    ULOG("usuarioTemSkill=%d",usuarioTemSkill);

    //==========================================================================
    // Motivo
    if ( cwfmotivo.obterIdMotivo("Encaminhamento Automatico",idMotivoEncAutomatico) == false )
    {
        me = "Motivo de encaminhamento automatico nao encontrado";

        saida->createTag("RetornoVO");
            saida->aggregateXML((char*)me);
        saida->closeTag();

        ULOG(mensagemSimples((char*)me));

        ULOG_END("cWFAtdAttrUsuSem::Executar()"); 

        return true;
    }

    //==========================================================================
    // Data e hora da transação
    proCBuscarDataHora(&m_stDados);

    //==========================================================================
    // Tenta atribuir um processo ao usuário
    while ( 1 == teste && ++retry<6 )
    {
        ULOG("retry=%d",retry);

        if (m_stDados.inCri == 1)
        {
            if (proximosAtendimentos)
            {
                retProC=proCObterAtdFilaCriLinha(idUsuario,proxIdAtendimento
                                                ,m_stDados.idPessoaLinhaHistorico);
            }
            else
            {
                retProC=proCObterAtdFilaCri(idUsuario,proxIdAtendimento);
            }
        }
        else
        {
            if (m_stDados.inRC == 1)
            {
                retProC=proCObterAtdFilaAlertaCPrevio(idUsuario,proxIdAtendimento,&m_stDados,&xmlDpr);
            }
            else
            {
                if( usuarioTemSkill )
                {
                    // Primeiro verifica os processos COM LINHA para os grupos com skill
                    retProC=proCObterAtdFilaAlertaSkill(idUsuario,proxIdAtendimento);

                    if( retProC == false )
                    {   
                        retProC=proCObterAtdFilaAlertaNotSkill(idUsuario,proxIdAtendimento);
                    
                        if ( retProC == false )
                        {
                            retProC=proCObterAtdFilaAlerta(idUsuario,proxIdAtendimento);
                        }
                    }
                }
                else
                {
                    retProC=proCObterAtdFilaAlerta(idUsuario,proxIdAtendimento);
                }
            }
        }

        if ( retProC == false )
        {
            saida->createTag("RetornoVO");
                saida->aggregateXML("Nenhum processo disponível para tratamento");
            saida->closeTag();

            ULOG_END("cWFAtdAttrUsuSem::Executar()"); 
            return true;
        }

        if ( 1 == teste )
        {
            if ( cwfAtendimentoUsuarioAtual.incluir1aVez(proxIdAtendimento,obterIdUsuario(),&xmlDpr) )
            //if ( IncluirAtdUsuarioAtual(proxIdAtendimento) )
            {
                me  = "Processo '";
                me += proxIdAtendimento;
                me += "' disponível para tratamento";

                saida->clearAndDestroy();

                saida->createTag("RetornoVO");
                    saida->aggregateXML((char*)me);
                saida->closeTag();

                m_stDados.idAtendimento = proxIdAtendimento;
                m_vlDados.idAtendimento = 1;

                //bool retAtendimento = ObterDetalheAtd();
                bool retAtendimento = cwfAtendimento.ObtemDetalheAtend(proxIdAtendimento,&detalheAtendimento);

                if (m_stDados.inCri == 1)
                {
                    ObterGrupoCri();
                    InserirAtendimentoCri(idUsuario);
                    proximosAtendimentos = true;
                }
                else
                {
                    teste = 0;
                    m_stDados.idGrupoAtual = detalheAtendimento.idGrupoAtual;

                    if ( detalheAtendimento.idGrupoAtual>0 )
                    {
                        ULOG("idGrupoAtual=%d",m_stDados.idGrupoAtual);
                        m_vlDados.idGrupoAtual = 1;
                    }
                    else
                    {
                        ULOG("idGrupoAtual não encontrado");
                        m_vlDados.idGrupoAtual = -1;
                    }
                }

                if ( retAtendimento )
                {
                    long idAndamentoIns;

                    if (m_stDados.inRC )
                    {
                        idProxAgrupEstadoTpProc = detalheAtendimento.idAgrEstadoTProc;

                        if ( 15 == idProxAgrupEstadoTpProc )
                        {
                             idProxAgrupEstadoTpProc = 16 ;
                        }
                        else if ( 2 == idProxAgrupEstadoTpProc )
                        {
                             idProxAgrupEstadoTpProc = 3 ;
                        }
                    }
                    else if ( !ObterProximoAgrupamento(&idProxAgrupEstadoTpProc) )
                    {
                        throw new TuxException("04I0001","Não foi possível obter o próximo agrupamento do processo");
                    }
                    //
                    // incidência 3452 
                    // É solicitado que quando se clica no botao disponivel seja gerado 
                    // um andamento com a atividade 34-ENCAMINHAR C.PREVIO
                    //
                    // Incidência 3452 como foi solicitado só existira um andamento quando o
                    // botão disponível for  pressionando antes desta solicitação era gerado 
                    // um andamento para adquirir e um para encaminhamento
                    if ( !IncluirAndamento(idProxAgrupEstadoTpProc,&idAndamentoIns,m_stDados.inRC ) )
                    {
                        throw new TuxException("04I0002","Falha na geração do andamento do processo");
                    }

                    // caso o processo ja tenha um usuario, informa ao mesmo 
                    // que o processo esta em cprevio
                    if ( m_stDados.idPessoaUsuarioAtual > -1 )
                    {
                        prCIncluir_MSG_Usuario_old(idAndamentoIns,idUsuario, m_stDados.idPessoaUsuarioAtual);
                    }

                    IncluirAtendimentoObservacao(idAndamentoIns);
                    IncluirAdandamentoMotivo(idAndamentoIns,idMotivoEncAutomatico,detalheAtendimento.idFase);
                    IncluirAndamentoTransferencia(idAndamentoIns,idMotivoEncAutomatico);
                } // if ( retAtendimento )
            } // if ( cwfAtendimentoUsuarioAtual.incluir1aVez(idAtendimento,obterIdUsuario(),&xmlDpr) )
        } // if ( 1 == teste )
    } // while ( 1 == teste )

    if ( 0 == teste )
    {
        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(idUsuario,detalheAtendimento.idContato,idProxAgrupEstadoTpProc);
        // <== SM324--DPR--DEZ/2006--Cassio
        retorno = true;
    }
    else
    {
        saida->clearAndDestroy();

        saida->createTag("RetornoVO");
            saida->aggregateXML("Não foi possível atribuir processo. Tente novamente.");
        saida->closeTag();
        retorno = false;
    }

    ULOG_END("cWFAtdAttrUsuSem::Executar()"); 

    return retorno;
}

void cWFAtdAttrUsuSem::registrarAcaoDPR(int idUsuarioWeb,int /*idContato*/,int idAgrupamentoEstadoTpProc)
{
    ULOG_START("cWFAtdAttrUsuSem::registrarAcaoDPR");

    if ( idAgrupamentoEstadoTpProc >= INI_AGRPTPPROC_TECNICO 
       && idAgrupamentoEstadoTpProc <= FIM_AGRPTPPROC_TECNICO )
    {
        cWFAtdGerarXMLDPR cwfatdgerarxmldpr;

        char idAtendimentoStr[32];

        sprintf(idAtendimentoStr,"%d",m_stDados.idAtendimento);
        xmlDpr.idAtendimento = idAtendimentoStr;

        xmlDpr.idUser = idUsuarioWeb;
        xmlDpr.nomeServico = "ATDATTRUSUSEM";
        //xmlDpr.idContato = idContato;
        xmlDpr.idAtendimento = idAtendimentoStr;

        cwfatdgerarxmldpr.persistirDadosDPRContatoTecnico(&xmlDpr);
    }
    else
    {
        ULOG("Processo %d não é técnico",m_stDados.idAtendimento);
    }

    ULOG_END("cWFAtdAttrUsuSem::registrarAcaoDPR");
}

bool cWFAtdAttrUsuSem::ObterGrupoCri()
{
    ULOG_START("cWFAtdAttrUsuSem::ObterGrupoCri()"); 

    st_AtdTratamentoCri m_stDadosCri;
    st_vlAtdTratamentoCri m_vlDadosCri;
    XMLGen xmlObtemCri;

    m_stDadosCri.idAtendimento = m_stDados.idAtendimento;
    m_vlDadosCri.idAtendimento = 1;
        
    cWFAtdTratamentoCri cWFAtdTratamentoCri(&m_stDadosCri, &m_vlDadosCri, &xmlObtemCri);

    bool retorno = cWFAtdTratamentoCri.getGrupoCri();

    if ( retorno )
    {
        m_stDados.idGrupoAtual = cWFAtdTratamentoCri.getGrupo();
        m_stDados.idPessoaLinhaHistorico = cWFAtdTratamentoCri.getPessoaLinhaHistorico();

        m_vlDados.idGrupoAtual = 1;
        m_vlDados.idPessoaLinhaHistorico = 1;

        ULOG("idGrupoAtual=%d",m_stDados.idGrupoAtual);
        ULOG("idPessoaLinhaHistorico=%ld",m_stDados.idPessoaLinhaHistorico);
    }

    ULOG_END("cWFAtdAttrUsuSem::ObterGrupoCri()"); 

    return retorno;
}

bool cWFAtdAttrUsuSem::InserirAtendimentoCri(int idPessoaUsuario)
{
    ULOG_END("cWFAtdAttrUsuSem::InserirAtendimentoCri()"); 

    st_AtdTratamentoCri m_stDadosCri;
    st_vlAtdTratamentoCri m_vlDadosCri;
    XMLGen xmlObtemCri;

    m_stDadosCri.idAtendimento = m_stDados.idAtendimento;
    m_vlDadosCri.idAtendimento = 1;
        
    m_stDadosCri.idPessoaLinhaHistorico = m_stDados.idPessoaLinhaHistorico;
    m_vlDadosCri.idPessoaLinhaHistorico = 1;
    ULOG("idPessoaLinhaHistorico=%ld",m_stDadosCri.idPessoaLinhaHistorico);

    m_stDadosCri.idPessoaUsuario = idPessoaUsuario;
    m_vlDadosCri.idPessoaUsuario = 1;
    
    m_stDadosCri.idUsuarioAlteracao = idPessoaUsuario;
    m_vlDadosCri.idUsuarioAlteracao = 1;

    strcpy(m_stDadosCri.dtAbertura,m_stDados.dataAndamento);
    m_vlDadosCri.dtAbertura = 1;

    strcpy(m_stDadosCri.dtUltimaAlteracao,m_stDados.dataAndamento);
    m_vlDadosCri.dtUltimaAlteracao = 1;

    strcpy(m_stDadosCri.dtPrazoCri,m_stDados.dataAndamento);
    m_vlDadosCri.dtPrazoCri = 1;

    cWFAtdTratamentoCri cWFAtdTratamentoCri(&m_stDadosCri, &m_vlDadosCri, &xmlObtemCri);

    if (cWFAtdTratamentoCri.incluirTratamentoCri() == 0)
    {   
        ULOG("FALHA EM -> cWFAtdTratamentoCri.incluirTratamentoCri() == 0 -> return false");
        ULOG_END("cWFAtdAttrUsuSem::InserirAtendimentoCri()"); 
        return false;
    }

    if (cWFAtdTratamentoCri.incluirAtendimentoCri() == 0)
    {
        ULOG("FALHA EM ->cWFAtdTratamentoCri.incluirAtendimentoCri() == 0 -> return false ");
        ULOG_END("cWFAtdAttrUsuSem::InserirAtendimentoCri()"); 
        return false;
    }

    ULOG_END("cWFAtdAttrUsuSem::InserirAtendimentoCri()"); 

    return true;
}

bool cWFAtdAttrUsuSem::IncluirAndamento(int    idProxAgrupEstadoTpProc
                                       ,long * idAndamentoIns
                                       ,int    inRC)
{
    ULOG_START("cWFAtdAttrUsuSem::IncluirAndamento()"); 

    bool retorno = true;

    if ( 1 == m_vlDados.idGrupoAtual )
    {
        st_Andamento dados;
        st_vlAndamento status;

        memset(&dados,0,sizeof(dados));
        memset(&status,-1,sizeof(status));

        // incidência 3452 
        // É solicitado que quando se clica no botao disponivel seja gerado 
        // uma linha em andamento com a atividade 'ENCAMINHADO CPREVIO'.
        // quando este botão é pressionado no inbox de contato prévio ao invés
        // da tradicinal ação ENCAMINHAR.
        dados.idAtividade = inRC == 1 ? ENCAMINHADO_CPREVIO_ERC : ENCAMINHAR_E;
        dados.idAgrupamentoEstadoTpProc = idProxAgrupEstadoTpProc;
        dados.idAtendimento = m_stDados.idAtendimento;
        dados.idPessoaUsuario = obterIdUsuario();
        dados.idGrupo = m_stDados.idGrupoAtual;
        dados.idUsuarioAlteracao = dados.idPessoaUsuario;
        strcpy(dados.dtAndamento,m_stDados.dataAndamento);
        strcpy(dados.dtUltimaAlteracao,m_stDados.dataAtual);

        status.idAtividade =
        status.idAgrupamentoEstadoTpProc =
        status.idAtendimento =
        status.idPessoaUsuario =
        status.idGrupo =
        status.idUsuarioAlteracao =
        status.dtAndamento =
        status.dtUltimaAlteracao = 1;

        cWFAndamento cwfandamento(&dados, &status,NULL);

        *idAndamentoIns = cwfandamento.incluir(&xmlDpr);

        ULOG("idAndamentoIns=%ld",*idAndamentoIns);
    }
    else
    { // se grupo atual não fornecido não dá pra inserir andamento
        retorno = false;
    }

    ULOG_END("cWFAtdAttrUsuSem::IncluirAndamento()"); 

    return retorno;
}

bool cWFAtdAttrUsuSem::ObterProximoAgrupamento(int *idProxAgrupEstadoTpProc)
{
    ULOG_START("cWFAtdAttrUsuSem::ObterProximoAgrupamento()"); 

    bool retorno = true;
    st_AgrupamentoEstadoTpProc dados;
    st_vlAgrupamentoEstadoTpProc status;

    memset(&dados,0,sizeof(dados));
    memset(&status,-1,sizeof(status));

    dados.idAgrupamentoEstadoTpProc = detalheAtendimento.idAgrEstadoTProc;
    dados.idAtividade = 3;

    status.idAgrupamentoEstadoTpProc = 1;
    status.idAtividade = 1;

    cWFAgrupamentoEstadoTpProc cwfAgrupamentoEstadoTpProc(&dados,&status);

    *idProxAgrupEstadoTpProc = cwfAgrupamentoEstadoTpProc.proximoAgrupamento();

    if ( *idProxAgrupEstadoTpProc == -1 )
    {
        SetarErro(NULL,"Próximo estado para o processo não encontrado");
        retorno = false;
    }

    ULOG_END("cWFAtdAttrUsuSem::ObterProximoAgrupamento()"); 

    return retorno;
}

//bool cWFAtdAttrUsuSem::IncluirAtdUsuarioAtual(long idAtendimento)
//{
//    ULOG_START("cWFAtdAttrUsuSem::IncluirAtdUsuarioAtual()"); 
//
//    cWFAtendimentoUsuarioAtual cwfAtendimentoUsuarioAtual;
//
//    bool retorno = 
//        cwfAtendimentoUsuarioAtual.incluir1aVez(idAtendimento,obterIdUsuario(),&xmlDpr);
//
//    ULOG_END("cWFAtdAttrUsuSem::IncluirAtdUsuarioAtual()"); 
//
//    return retorno;
//}

//bool cWFAtdAttrUsuSem::ObterDetalheAtd()
//{
//    ULOG_START("cWFAtdAttrUsuSem::ObterDetalheAtd()");
//
//    cWFAtendimento cwfAtendimento;
//
//    bool retorno = 
//        cwfAtendimento.ObtemDetalheAtend(m_stDados.idAtendimento,&detalheAtendimento);
//
//    ULOG_END("cWFAtdAttrUsuSem::ObterDetalheAtd()");
//
//    return retorno;
//}

void cWFAtdAttrUsuSem::IncluirAtendimentoObservacao( long idAndamento )
{
    ULOG_START("cWFAtdAttrUsuSem::IncluirAtendimentoObservacao()"); 

    struct st_AndamentoObservacao dados; 
    struct st_vlAndamentoObservacao status;
    XMLGen saida;

    memset (&dados , 0, sizeof(st_AndamentoObservacao)); 
    memset (&status,-1, sizeof(st_vlAndamentoObservacao));

    dados.idAndamento = idAndamento;
    dados.idUsuarioAlteracao = obterIdUsuario();

    sprintf(dados.dtUltimaAlteracao,"%.*s"
           , sizeof(dados.dtUltimaAlteracao)-1, m_stDados.dataAtual);

    if ( !proCBuscarEncAutoObs(dados.dsAndamentoObservacao
                             ,sizeof(dados.dsAndamentoObservacao)) )
    {
        dados.pdsAndamentoObservacao = "ENCAMINHAMENTO AUTOMATICO";
    }
    else
    {
        dados.pdsAndamentoObservacao = dados.dsAndamentoObservacao;
    }

    status.idAndamento           = 1;
    status.idUsuarioAlteracao    = 1;
    status.dtUltimaAlteracao     = 1;
    status.dsAndamentoObservacao = 1;

    cWFAndamentoObservacao cwfAndamentoObservacao(&dados, &status, &saida);

    cwfAndamentoObservacao.incluir(&xmlDpr);

    ULOG_END("cWFAtdAttrUsuSem::IncluirAtendimentoObservacao()"); 
}

void cWFAtdAttrUsuSem::IncluirAdandamentoMotivo(long idAndamento,int idMotivo,int idFase)
{
    ULOG_START("cWFAtdAttrUsuSem::IncluirAdandamentoMotivo()"); 

    struct st_AndamentoMotivo   dados;
    struct st_vlAndamentoMotivo status; 
    XMLGen saida;

    memset ( &dados , 0, sizeof( st_AndamentoMotivo  )); 
    memset ( &status,-1, sizeof( st_vlAndamentoMotivo));

    dados.idAndamento = idAndamento;
    dados.idMotivo = idMotivo;
    dados.idFase = idFase;
    dados.idUsuarioAlteracao = obterIdUsuario();
    sprintf(dados.dtUltimaAlteracao,"%.*s"
           , sizeof(dados.dtUltimaAlteracao)-1, m_stDados.dataAtual);

    status.idAndamento = 1;
    status.idMotivo = 1;
    status.idFase = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;
    
    cWFAndamentoMotivo cwfAndamentoMotivo(&dados,&status,&saida);

    cwfAndamentoMotivo.incluir();

    ULOG_END("cWFAtdAttrUsuSem::IncluirAdandamentoMotivo()"); 
}

void cWFAtdAttrUsuSem::IncluirAndamentoTransferencia( long idAndamento, int idMotivo )
{
    ULOG_START("cWFAtdAttrUsuSem::IncluirAndamentoTransferencia()"); 

    ULOG("idAndamento:[%ld]",idAndamento);
    ULOG("idMotivo:[%d]",idMotivo);

    struct st_AndamentoTransferencia dados; 
    struct st_vlAndamentoTransferencia status;
    XMLGen xmlObtemAndamento;

    memset (&dados , 0, sizeof(st_AndamentoTransferencia)); 
    memset (&status,-1, sizeof(st_vlAndamentoTransferencia));

    dados.idAtendimento             = m_stDados.idAtendimento;
    dados.idAndamento               = idAndamento;
    dados.idPessoaUsuario           = obterIdUsuario();
    dados.idMotivoEncaminhamento    = idMotivo;
    sprintf(dados.dtInicioTransferencia,"%.*s"
           , sizeof(dados.dtInicioTransferencia)-1, m_stDados.dataAndamento);
    dados.idUsuarioAlteracao        = obterIdUsuario();
    sprintf(dados.dtUltimaAlteracao,"%.*s"
           , sizeof(dados.dtUltimaAlteracao)-1, m_stDados.dataAtual);

    status.idAtendimento = 1;
    status.idAndamento = 1;
    status.idPessoaUsuario = 1;
    status.idMotivoEncaminhamento = 1;
    status.dtInicioTransferencia = 1;
    status.idUsuarioAlteracao = 1;
    status.dtUltimaAlteracao = 1;

    cWFAndamentoTransferencia cwfAndamentoTransferencia(&dados,&status, &xmlObtemAndamento);
    cwfAndamentoTransferencia.incluir();
    
    ULOG_END("cWFAtdAttrUsuSem::IncluirAndamentoTransferencia()"); 
}

void cWFAtdAttrUsuSem::carregaDados()
{
    ULOG_START("cWFAtdAttrUsuSem::carregaDados()"); 

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( entrada )
    {
        char *p;

        if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
        {
            m_stDados.idAtendimento = atol(p);
            m_vlDados.idAtendimento = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "discador", 0 ),p) 
        {
            m_stDados.discador = atoi(p);
            m_vlDados.discador = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "inCRI", 0 ),p) 
        {
            m_stDados.inCri = atoi(p);
            m_vlDados.inCri = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "dsObservacao", 0 ),p) 
        {
            strcpy( m_stDados.dsObservacao, p );
            m_vlDados.dsObservacao = 1;
            XMLString::release(&p);
        }

        if (p=tx.walkTree( entrada, "inRC", 0 ),p) 
        {
            m_stDados.inRC = atoi(p);
            m_vlDados.inRC = 1;
            XMLString::release(&p);
        }

        ULOG("inCri=%d",m_stDados.inCri);
        ULOG("inRC=%d",m_stDados.inRC);
        ULOG("idAtendimento=%d",m_stDados.idAtendimento);
        ULOG("discador=%d",m_stDados.discador);
        ULOG("dsObservacao=%s",m_stDados.dsObservacao);
    }

    ULOG_END("cWFAtdAttrUsuSem::carregaDados()"); 
}