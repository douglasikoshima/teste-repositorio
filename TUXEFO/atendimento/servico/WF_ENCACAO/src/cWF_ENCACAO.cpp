/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include "../../../commons/msgPadrao.h"
#include "../include/cWF_EncAcaoPC.h"
#include "../../WF_CERRAMEFEC/include/cWF_CERRAMEFECPC.h"

#include"../../Andamento/include/cWFAndamento.h"
#include"../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include"../../AndamentoTransferencia/include/cWFAndamentoTransferencia.h"
#include"../../AndamentoMotivo/include/cWFAndamentoMotivo.h"

#include"../../Atendimento/include/cWFAtendimento.h"
#include"../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
#include"../../Usuario/include/cWFUsuario.h"
#include"../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include"../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include"../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include"../../AtendimentoSuspeito/include/cWFAtendimentoSuspeito.h"
#include"../../CancelamentoSolicitado/include/cWFCancelamentoSolicitado.h"


cWF_ENCACAO::cWF_ENCACAO(DOMNode*dnode,XMLGen*xml_g)
{
    entrada = dnode;
    saida = xml_g;
}


/**
    Registra todos os dados do atendimento.
*/
void cWF_ENCACAO::Execute()
{
    ULOG_START("cWF_ENCACAO::Execute()");

    char dataAtual[64];
    char dataAndamento[64];

    int idPessoaUsuarioEncaminamento = 0;
    int idGrupoEncaminamento = 0;
    
    int idPessoaUsuarioAtd = 0;
    int idGrupoAtd = 0;

    int idAgrEstado = 0;

    int idMotivo = 0;

    int idStatusUsuario = 0;

    dataAtual[0] = 0;
    dataAndamento[0]=0;
    cWF_CERRAMEFECPC rcpc;
    char* p;

    rcpc.dataAtual(dataAtual);
    rcpc.dataAndamento(dataAndamento);

    if (p=tx.walkTree(dados.domUsuario, "idPessoaUsuario", 0 ),p)
    {
        idPessoaUsuarioEncaminamento=atoi(p);
        XMLString::release(&p);
    }

    if (p=tx.walkTree(dados.domGrupo, "idGrupo", 0 ),p)
    {
        idGrupoEncaminamento=atoi(p);
        XMLString::release(&p);
    }

    idAgrEstado = dados.idAgrEstTPrFt;

    // obtener grupo
    st_AtendimentoGrupoAtual m_stDadosGrup;
    st_vlAtendimentoGrupoAtual m_vlDadosGrup;
    XMLGen xmlObtemAtGrAt;

    memset(&m_stDadosGrup, 0, sizeof(m_stDadosGrup));
    memset(&m_vlDadosGrup,-1, sizeof(m_vlDadosGrup));
    m_stDadosGrup.idAtendimento=dados.idAtendimento;
    m_vlDadosGrup.idAtendimento=1;

    cWFAtendimentoGrupoAtual AtendimentoGrupoAtual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

    AtendimentoGrupoAtual.ObtemGrAt();

    if (wta.walkTreeXMLGen(&xmlObtemAtGrAt, "idGrupo", &p, 0)==0)
    {
        idGrupoAtd= atoi(p);
        XMLString::release(&p);         
    }   

    if (idPessoaUsuarioEncaminamento > 0)
    {
        st_VariaveisUsuario m_stUsr;
        st_vlVariaveisUsuario m_vlUsr;
        XMLGen xmlObtemUsuario;

        memset(&m_stUsr, 0, sizeof(m_stUsr));
        memset(&m_vlUsr,-1, sizeof(m_vlUsr));
        m_stUsr.idPessoaUsuario=idPessoaUsuarioEncaminamento;
        m_vlUsr.idPessoaUsuario=1;
        
        cWFUsuario Usuario (&m_stUsr, &m_vlUsr, &xmlObtemUsuario);

        idStatusUsuario = Usuario.pesqStatusUsuario();
        //idStatusUsuario = Usuario.ObterInDisponivelWF(entrada);

        if (idStatusUsuario != 1)
        {
            ULOG("Status de Usuario Invalido");
            saida->createTag("WFAcaoRetornoVO");
            saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            saida->addItem("acaoExecucao", "N");
            saida->addItem("mensagem", "Usuário Indisponível para Recepcionar Processos");
            saida->addItem("urlDestino", dados.urlDestino);
            saida->closeTag();
            
            return;
        }
    }
    // obtener usuario
    st_AtendimentoUsuarioAtual m_stDadosUsr;
    st_vlAtendimentoUsuarioAtual m_vlDadosUsr;
    XMLGen xmlObtemAtUsrAt;

    memset(&m_stDadosUsr, 0, sizeof(m_stDadosUsr));
    memset(&m_vlDadosUsr,-1, sizeof(m_vlDadosUsr));
    m_stDadosUsr.idAtendimento=dados.idAtendimento;
    m_vlDadosUsr.idAtendimento=1;
    m_vlDadosUsr.idPessoaUsuario=-1;

    cWFAtendimentoUsuarioAtual AtendimentoUsuarioAtual(&m_stDadosUsr, &m_vlDadosUsr, &xmlObtemAtUsrAt);

    AtendimentoUsuarioAtual.consultar();

    if (wta.walkTreeXMLGen(&xmlObtemAtUsrAt, "idPessoaUsuario", &p, 0)==0)
    {
        idPessoaUsuarioAtd= atoi(p);
        XMLString::release(&p);         
    }   

    if (idPessoaUsuarioAtd > 0)
    {
        st_AndamentoTransferencia m_stDadosTran;
        st_vlAndamentoTransferencia m_vlDadosTran;
        XMLGen xmlUltAndTrf;

        memset(&m_stDadosTran, 0, sizeof(m_stDadosTran));
        memset(&m_vlDadosTran,-1, sizeof(m_vlDadosTran));
        m_stDadosTran.idAtendimento=dados.idAtendimento;
        m_vlDadosTran.idAtendimento=1;
        m_vlDadosTran.idAndamento=-1;
        m_vlDadosTran.dtFinTransferencia=-1;

        cWFAndamentoTransferencia AndamentoTransferencia(&m_stDadosTran, &m_vlDadosTran, &xmlUltAndTrf);

    
        ULOG("Obtemos ultimo Andamento Transferencia");

        AndamentoTransferencia.obtemTrf();

        if (wta.walkTreeXMLGen(&xmlUltAndTrf, "idAndamento", &p, 0 )==0)
        {
            m_stDadosTran.idAndamento=atol(p);
            m_vlDadosTran.idAndamento=1;
            XMLString::release(&p);         
        }

        ULOG("IdAndamento: %ld", m_stDadosTran.idAndamento);
        if (m_vlDadosTran.idAndamento != -1)
        {
            strcpy(m_stDadosTran.dtFinTransferencia,dataAtual);
            m_vlDadosTran.dtFinTransferencia=1;
            m_vlDadosTran.dtInicioTransferencia=-1;
            m_vlDadosTran.idPessoaUsuario=-1;

            cWFAndamentoTransferencia AndamentoTransferencia(&m_stDadosTran, &m_vlDadosTran, &xmlUltAndTrf);

            ULOG("Alteramos ultimo Andamento Transferencia");
            AndamentoTransferencia.alterar();
        }

    }
    if (idPessoaUsuarioEncaminamento > 0)
    {
        m_stDadosUsr.idPessoaUsuario= idPessoaUsuarioEncaminamento;
        m_vlDadosUsr.idPessoaUsuario= 1;

        m_stDadosUsr.inPausaAtendimento= 0;
        m_vlDadosUsr.inPausaAtendimento= 1;
        strcpy(m_stDadosUsr.dtFimPausaAtendimento,"");
        m_vlDadosUsr.dtFimPausaAtendimento= 1;

        cWFAtendimentoUsuarioAtual AtendimentoUsuarioAtual(&m_stDadosUsr, &m_vlDadosUsr, &xmlObtemAtUsrAt);
        if (idPessoaUsuarioAtd > 0)
        {
            ULOG("Alteramos Usuario Atual");
            AtendimentoUsuarioAtual.alterar();      

        }
        else
        {
            ULOG("Inserimos Usuario Atual");
            AtendimentoUsuarioAtual.incluir();      
        }
    }
    else
    {
        ULOG("Excluimos Usuario Atual");
        AtendimentoUsuarioAtual.excluir();      

        idAgrEstado = dados.idAgrEstTPrAt;
    }
    
    m_stDadosGrup.idGrupo=idGrupoEncaminamento;
    m_vlDadosGrup.idGrupo=1;

    cWFAtendimentoGrupoAtual AtdGrupoAtual(&m_stDadosGrup, &m_vlDadosGrup, &xmlObtemAtGrAt);

    AtdGrupoAtual.alterar();

    if (idGrupoAtd != idGrupoEncaminamento)
    {
        st_AtendimentoNivel m_stDadosAtendimentoNivel;
        st_vlAtendimentoNivel m_vlDadosAtendimentoNivel;
        XMLGen AtdNivel;

        memset(&m_stDadosAtendimentoNivel, 0, sizeof(m_stDadosAtendimentoNivel));
        memset(&m_vlDadosAtendimentoNivel,-1, sizeof(m_stDadosAtendimentoNivel));
        m_stDadosAtendimentoNivel.idAtendimento=dados.idAtendimento;
        m_vlDadosAtendimentoNivel.idAtendimento=1;
        m_stDadosAtendimentoNivel.idGrupo=idGrupoAtd;
        m_vlDadosAtendimentoNivel.idGrupo=1;
        m_stDadosAtendimentoNivel.idFase=dados.idFase;
        m_vlDadosAtendimentoNivel.idFase=1;
        m_stDadosAtendimentoNivel.idAtividade=dados.idAtividade;
        m_vlDadosAtendimentoNivel.idAtividade=1;
        strcpy(m_stDadosAtendimentoNivel.dtNivel, dataAndamento);
        m_vlDadosAtendimentoNivel.dtNivel=1;
        m_stDadosAtendimentoNivel.idUsuarioAlteracao=dados.UsuarioAtual;
        m_vlDadosAtendimentoNivel.idUsuarioAlteracao=1;
        strcpy(m_stDadosAtendimentoNivel.dtUltimaAlteracao, dataAtual);
        m_vlDadosAtendimentoNivel.dtUltimaAlteracao=1;

        cWFAtendimentoNivel AtendimentoNivel(&m_stDadosAtendimentoNivel, &m_vlDadosAtendimentoNivel, &AtdNivel);
        
        AtendimentoNivel.incluir();
    }

    st_AtendimentoSuspeito m_stDadosAtenSuspeito;
    st_vlAtendimentoSuspeito m_vlDadosAtenSuspeito;
    XMLGen ExcSusp;

    memset(&m_stDadosAtenSuspeito, 0, sizeof(m_stDadosAtenSuspeito));
    memset(&m_vlDadosAtenSuspeito,-1, sizeof(m_vlDadosAtenSuspeito));
    m_stDadosAtenSuspeito.idAtendimento=dados.idAtendimento;
    m_vlDadosAtenSuspeito.idAtendimento=1;

    cWFAtendimentoSuspeito AtendimentoSuspeito(&m_stDadosAtenSuspeito, &m_vlDadosAtenSuspeito, &ExcSusp);
    AtendimentoSuspeito.excluir();

    //************* Inclusao do Andamento ********************;

    st_Andamento m_stDadosAndamento;
    st_vlAndamento m_vlDadosAndamento;
    XMLGen AndamentoIns;

    memset(&m_stDadosAndamento, 0, sizeof(m_stDadosAndamento));
    memset(&m_vlDadosAndamento,-1, sizeof(m_vlDadosAndamento));
    m_stDadosAndamento.idAtividade=dados.idAtividade;
    m_vlDadosAndamento.idAtividade=1;
    m_stDadosAndamento.idAgrupamentoEstadoTpProc=idAgrEstado;
    m_vlDadosAndamento.idAgrupamentoEstadoTpProc=1;
    m_stDadosAndamento.idAtendimento=dados.idAtendimento;
    m_vlDadosAndamento.idAtendimento=1;
    m_stDadosAndamento.idGrupo=idGrupoAtd;
    m_vlDadosAndamento.idGrupo=1;
    m_stDadosAndamento.idPessoaUsuario=dados.UsuarioAtual;
    m_vlDadosAndamento.idPessoaUsuario=1;
    strcpy(m_stDadosAndamento.dtAndamento,dataAndamento);
    m_vlDadosAndamento.dtAndamento=1;
    m_stDadosAndamento.idUsuarioAlteracao=dados.UsuarioAtual;
    m_vlDadosAndamento.idUsuarioAlteracao=1;
    strcpy(m_stDadosAndamento.dtUltimaAlteracao,dataAtual);
    m_vlDadosAndamento.dtUltimaAlteracao=1;

    cWFAndamento Andamento(&m_stDadosAndamento, &m_vlDadosAndamento, &AndamentoIns);

    Andamento.incluir();

    if (wta.walkTreeXMLGen(&AndamentoIns, "idAndamento", &p, 0)==0)
    {
        m_stDadosAndamento.idAndamento = atoi(p);
        m_vlDadosAndamento.idAndamento = 1;
        XMLString::release(&p);         
    }

    //************* Atualiza Andamento Atual ********************;

    st_AtendimentoAndamentoAtual m_stDadosAndamentoAtual;
    st_vlAtendimentoAndamentoAtual m_vlDadosAndamentoAtual;
    XMLGen AndAtual;

    memset(&m_stDadosAndamentoAtual, 0, sizeof(m_stDadosAndamentoAtual));
    memset(&m_vlDadosAndamentoAtual,-1, sizeof(m_vlDadosAndamentoAtual));
    m_stDadosAndamentoAtual.idAtendimento=dados.idAtendimento;
    m_vlDadosAndamentoAtual.idAtendimento=1;
    m_stDadosAndamentoAtual.idAndamento=m_stDadosAndamento.idAndamento;
    m_vlDadosAndamentoAtual.idAndamento=1;
    m_stDadosAndamentoAtual.idUsuarioAlteracao=dados.UsuarioAtual;
    m_vlDadosAndamentoAtual.idUsuarioAlteracao=1;
    strcpy(m_stDadosAndamentoAtual.dtUltimaAlteracao,dataAtual);
    m_vlDadosAndamentoAtual.dtUltimaAlteracao=1;

    cWFAtendimentoAndamentoAtual AtendimentoAndamentoAtual(&m_stDadosAndamentoAtual, &m_vlDadosAndamentoAtual, &AndAtual);

    AtendimentoAndamentoAtual.alterar();

    //********** Inclusao do Andamento Observacao *************

    st_AndamentoObservacao m_stDadosAndamentoObservacao;
    st_vlAndamentoObservacao m_vlDadosAndamentoObservacao;
    XMLGen Observacao;

    memset(&m_stDadosAndamentoObservacao, 0, sizeof(m_stDadosAndamentoObservacao));
    memset(&m_vlDadosAndamentoObservacao,-1, sizeof(m_vlDadosAndamentoObservacao));
    m_stDadosAndamentoObservacao.idAndamento=m_stDadosAndamento.idAndamento;
    m_vlDadosAndamentoObservacao.idAndamento=1;

    if (p=tx.walkTree(dados.domWorkflow, "dsObservacao", 0 ),p)
    {
        strcpy(m_stDadosAndamentoObservacao.dsAndamentoObservacao, p);
        m_stDadosAndamentoObservacao.pdsAndamentoObservacao = m_stDadosAndamentoObservacao.dsAndamentoObservacao;
        m_vlDadosAndamentoObservacao.dsAndamentoObservacao=1;
        XMLString::release(&p);
    }

    m_stDadosAndamentoObservacao.idUsuarioAlteracao=dados.UsuarioAtual;
    m_vlDadosAndamentoObservacao.idUsuarioAlteracao=1;
    strcpy(m_stDadosAndamentoObservacao.dtUltimaAlteracao, dataAtual);
    m_vlDadosAndamentoObservacao.dtUltimaAlteracao=1;

    cWFAndamentoObservacao AndamentoObservacao(&m_stDadosAndamentoObservacao, &m_vlDadosAndamentoObservacao, &Observacao);

    AndamentoObservacao.incluir();

    //********** Inclusao do Andamento Transferencia *************

    if (idPessoaUsuarioEncaminamento > 0)
    {
        st_AndamentoTransferencia m_stDadosTran;
        st_vlAndamentoTransferencia m_vlDadosTran;
        XMLGen xmlUltAndTrf;

        memset(&m_stDadosTran, 0, sizeof(m_stDadosTran));
        memset(&m_vlDadosTran,-1, sizeof(m_vlDadosTran));
        m_stDadosTran.idAtendimento=dados.idAtendimento;
        m_vlDadosTran.idAtendimento=1;
        m_stDadosTran.idAndamento=m_stDadosAndamento.idAndamento;
        m_vlDadosTran.idAndamento=1;
        m_stDadosTran.idPessoaUsuario=idPessoaUsuarioEncaminamento;
        m_vlDadosTran.idPessoaUsuario=1;
        strcpy(m_stDadosTran.dtInicioTransferencia,dataAtual);
        m_vlDadosTran.dtInicioTransferencia=1;

        cWFAndamentoTransferencia AndamentoTransferencia(&m_stDadosTran, &m_vlDadosTran, &xmlUltAndTrf);

        ULOG("Insereimos Andamento Transferencia");

        AndamentoTransferencia.incluir();
    }
    //********** Inclusao do Andamento Motivo *************

    if (p=tx.walkTree(dados.domMotivo, "idMotivo", 0 ),p)
    {
        idMotivo = atoi(p);
        XMLString::release(&p);
    }
    if (idMotivo > 0)
    {
        st_AndamentoMotivo m_stDadosAndamentoMotivo;
        st_vlAndamentoMotivo m_vlDadosAndamentoMotivo;
        XMLGen Motivo;

        memset(&m_stDadosAndamentoMotivo, 0, sizeof(m_stDadosAndamentoMotivo));
        memset(&m_vlDadosAndamentoMotivo,-1, sizeof(m_vlDadosAndamentoMotivo));
        m_stDadosAndamentoMotivo.idAndamento=m_stDadosAndamento.idAndamento;
        m_vlDadosAndamentoMotivo.idAndamento=1;

        m_stDadosAndamentoMotivo.idMotivo=idMotivo;
        m_vlDadosAndamentoMotivo.idMotivo=1;

        m_stDadosAndamentoMotivo.idFase=dados.idFase;
        m_vlDadosAndamentoMotivo.idFase=1;

        m_stDadosAndamentoMotivo.idUsuarioAlteracao=dados.UsuarioAtual;
        m_vlDadosAndamentoMotivo.idUsuarioAlteracao=1;
        strcpy(m_stDadosAndamentoMotivo.dtUltimaAlteracao, dataAtual);
        m_vlDadosAndamentoMotivo.dtUltimaAlteracao=1;

        cWFAndamentoMotivo AndamentoMotivo(&m_stDadosAndamentoMotivo, &m_vlDadosAndamentoMotivo, &Motivo);

        AndamentoMotivo.incluir();
    }

    ULOG("URL de Salida: %s", dados.urlDestino);
    saida->createTag("WFAcaoRetornoVO");
    saida->addProp("xmlns","workflow.fo.vivo.com.br/vo");
    saida->addItem("acaoExecucao", "S");
    if (idPessoaUsuarioEncaminamento > 0)
        saida->addItem("mensagem", "Usuário Disponível");
    else
        saida->addItem("mensagem", "Encaminhamento Concluido");
    saida->addItem("urlDestino", dados.urlDestino);
    saida->closeTag();

    ULOG_END("cWF_ENCACAO::Execute()");
}
