/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/03/08 20:16:11 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Encaminhamento
 * Migracao: Script 81
 * Revisao.: Dec-02-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_CanAcaoPC.h"

//******************************************************************************************************************
// WF_CANACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(CANACAO);

void implCANACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implCANACAO::Execute()");

    TString retCode = "04I0000";
    TString retMesg = "Execucao de CANACAO Concluida";

    cWF_Cancelamento rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch ( TuxBasicOraException *tboe )
    {
        retMesg = tboe->pMsg;

        delete tboe;

        xml_g->createTag("WFAcaoRetornoVO");
            xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("acaoExecucao", "N");
            xml_g->addItem("mensagem", retMesg.c_str());
            xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }
    catch( TuxException* pTux )
    {
        retMesg = pTux->getMessage();

        delete pTux;

        xml_g->createTag("WFAcaoRetornoVO");
            xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("acaoExecucao", "N");
            xml_g->addItem("mensagem", retMesg.c_str());
            xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }
    catch(...)
    {
        retMesg = "Excecao desconhecida";

        xml_g->createTag("WFAcaoRetornoVO");
            xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
            xml_g->addItem("acaoExecucao", "N");
            xml_g->addItem("mensagem", retMesg.c_str());
            xml_g->addItem("urlDestino","1");
        xml_g->closeTag();
    }

    setStatusCode(retCode.c_str(),retMesg.c_str());
    
    ULOG_END("implCANACAO::Execute()");
}

//******************************************************************************************************************
// cWF_Cancelamento - Component Implementation
//******************************************************************************************************************

cWF_Cancelamento::cWF_Cancelamento(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_Cancelamento::Executar() 
{
    cWF_Acao::Executar();

    // verifica se a atividade e nulo 
    if ( idAtividade.ToInt() == 0 )
    {
       InitPesquisa();
    }

    if (idGrupo.ToInt() > 0)
    {
        idGrupoAtual = idGrupo.ToInt();
    }
    else
    {
        getAtendimentoGrupoAtual();
        idGrupo = idGrupoAtual;
    }

    // --------------------------------------------------------------------------------------
    // A incidência 2983 que gerou esta alteração esta cancelada pela Vivo. De qualquer modo
    // esta função não estava funcionamento corretamente em produção, pois a função de
    // verificar se um processo pode ser cancelado pelo usuário é feita pelo core workflow.
    // Nov/2005 - Cassio
    // --------------------------------------------------------------------------------------
    // Incidência 2983 CRI
    // Se usuário web não for o responsável pelo processo, envia mensagem solicitando o
    // cancelamento ao usuário responsável e sai
    //if ( !podeCancelarProcesso() )
    //{
    //    enviarMsgCancelamento();
    //
    //    return;
    //}
    // --------------------------------------------------------------------------------------
   
    //if ( inCri )
    //{
    //  getAtendimentoGrupoBko();
    //  if (qtIntercambios == 0)
    //  {
    //      inAssociado = 0;
    //      idGrupo = idGrupoAtual;
    //      inserirAtendimentoGrupoBko();
    //  }
    //}

    getAndamentoTransferencia();
    if (idAndamentoTrans > 0)
    {
        alterarAndamentoTransferencia();
    }

    //
    // ==> Remodelagem do Atendimento, Março, 2007 - Cassio 
    // O último grupo pelo qual o processo passou fica registrado mesmo após o cancelamento
    // para com isso se evitar uma pesquisa em andamento.andamento e obter ganho de
    // performance nas pesquisas de fila.
    //removeAtendimentoGrupoBko();

    // ==> Remodelagem do Atendimento, Março, 2007 - Cassio 
    // O último usuário responsável pelo processo fica registrado no próprio processo 
    // a partir da implantação do modelo novo.
    //removeAtendimentoUsuarioAtual();
    //
    // Processos sem usuário atual devem registrar o usuário que esta fechando o processo
    // como o usuário atual (responsável) do processo.
    getAtendimento();
    if ( idUsuarioAtualAtendimento == 0 )
    {
        alterarAtendimentoUsuarioAtual(User.ToInt(),false);
    }
    // ==> Remodelagem do Atendimento, Março, 2007 - Cassio 
    // O último grupo pelo qual o processo passou fica registrado mesmo após o cancelamento
    // para com isso se evitar uma pesquisa em andamento.andamento e obter ganho de
    // performance nas pesquisas de fila.
    //removeAtendimentoGrupoAtual();

    removeAtendimentoSuspeito();
    inserirAtendimentoNivel(true /*concluir=true*/);
    removeCancelamentoSolicitado();
    removeTratamentoCri();
    inserirAndamento();
    //alterarAndamentoAtual();
    inserirAndamentoObservacao();
    DecrementarPendentes();
    idMotivo = getMotivoCancelamento();
    inserirAndamentoMotivo();

    inserirAtendimentoFechamento();

    // ==> SM324--DPR--DEZ/2006--Cassio
    //getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"CANACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    SetMessage( "Cancelamento Concluído", "S" ); 

    xml_g->closeTag();
}



/*
  A incidência 2983 que gerou esta alteração esta cancelada pela Vivo. De qualquer modo
  esta função não estava funcionamento corretamente em produção, pois a função de
  verificar se um processo pode ser cancelado pelo usuário é feita pelo core workflow.
  Nov/2005 - Cassio

void cWF_Cancelamento::enviarMsgCancelamento() 
{
    getCancelamentoSolicitado();

    if (idAtendimentoCancelamento > 0)
    {
        alterarCancelamentoSolicitado();
    }
    else
    {
        inserirCancelamentoSolicitado();
    }

    getAlertaAtendimento();
    inserirAtendimentoAlerta(); // Obtemos a letra associada a atividade & Inserendo alerta 
    getAlertaDinamicaAtendimento();
    alterarAtendimentoPriorizacao();
    inserirAndamento();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    idMotivo = getMotivoCancelamento();
    inserirAndamentoMotivo();

    SetMessage( "Solicitação de Cancelamento ao CRI Concluída", "S" ); 

    xml_g->closeTag();
}
*/
