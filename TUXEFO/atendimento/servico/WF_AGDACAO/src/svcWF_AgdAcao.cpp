/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1.2.7 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/03/08 20:16:11 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Agendamento
 * Migracao: Script 81
 * Revisao.: Dec-02-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_AgdAcaoPC.h"

//******************************************************************************************************************
// WF_AGDACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(AGDACAO);

void implAGDACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implAGDACAO::Execute()"); 
    TString retCode = "04I0000";
    TString retMesg = "Execucao de AGDACAO Concluida";

    cWF_Agendamento rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch ( TuxBasicOraException *tboe )
    {
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);

        retCode = errCode;
        retMesg = tboe->pMsg;

        delete tboe;
    }
    catch( TuxException* pTux )
    {
        retCode = "04E9998";
        retMesg = pTux->getMessage();

        delete pTux;
    }
    catch(...)
    {
        retCode = "04E9999";
        retMesg = "Excecao desconhecida";
    }

    setStatusCode(retCode.c_str(),retMesg.c_str());
    
    ULOG_END("implAGDACAO::Execute()"); 

}

//******************************************************************************************************************
// cWF_Agendamento - Component Implementation
//******************************************************************************************************************

cWF_Agendamento::cWF_Agendamento(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_Agendamento::Executar() 
{
    cWF_Acao::Executar();

    getAtendimento();

    // if ( strcmp(szSgFluxoAtendimento,"MC1")==0 )
    // {
    //     if ( UsuarioAtual.ToInt() != 0 )
    //     {
    //         if ( UsuarioAtual.ToInt() != idUsuarioAtualAtendimento )
    //         {
    //             SetMessage( "Processo 'Meu Cliente 1' não pode ser encaminhado para outro analista", "N" ); 
    //             xml_g->closeTag();
    //             return;
    //         }
    //     }
    // }


    if ( strcmp(szSgFluxoAtendimento,"MC1")==0 && UsuarioAtual.ToInt() == 0 )
    {
        SetMessage( "Processo 'Meu Cliente 1' não pode ser encaminhado somente para grupo", "N" ); 
        xml_g->closeTag();
        return;
    }

    if ( strcmp(szSgFluxoAtendimento,"MC2")==0 )
    {
        if ( UsuarioAtual.ToInt() > 0 ) 
        {
            if ( UsuarioAtual.ToInt() == idUsuarioAbertura )
            {
                SetMessage( "Use a ação de devolução para agendar processo Meu Cliente 2 para o analista de origem.","N" ); 
                xml_g->closeTag();
                return;
            }

            int UsuarioAtualTmp = UsuarioAtual.ToInt(); // salva valor original
            getAtendimentoUsuarioDevolucao();
            int UsuarioDevolucao = UsuarioAtual.ToInt();
            UsuarioAtual = UsuarioAtualTmp; // restaura valor original

            if ( UsuarioDevolucao == UsuarioAtual.ToInt() )
            {
                SetMessage( "Use a ação de devolução para agendar processo Meu Cliente 2 para o analista nivel 2.","N" );
                xml_g->closeTag();
                return;
            }
        }
    }

    // Se for processo de portout a ação é de reagendamento de portout
    if ( strcmp(sgTipoPortabilidade,"PORTOUT") == 0 )
    {
        atualizarDataJanelaPortout();

        char observacaoPortabilidade[512];
        observacaoPortabilidade[0] = 0;

        strcpy(observacaoPortabilidade,"TIPO:PORTOUT");
        strcat(observacaoPortabilidade,"-ESTADO:");
        strcat(observacaoPortabilidade,dsAcaoPortabilidade.c_str());

        strcat(observacaoPortabilidade,"-DATA JANELA:");
        strcat(observacaoPortabilidade,dtJanelaPortout.c_str());

        strcat(observacaoPortabilidade,"-OPERADORA:");
        strcat(observacaoPortabilidade,
                    sgOperadoraSolicitante.Lenght() == 0 
                            ?"** NÃO INFORMADA **":sgOperadoraSolicitante.c_str());

        if ( dsObservacao.Lenght()>0 && observacaoPortabilidade[0] )
        {
            char dsObservacaoTemp[1001];
            sprintf(dsObservacaoTemp,"%.800s %.200s",
                                dsObservacao.c_str(),observacaoPortabilidade);
            dsObservacao = dsObservacaoTemp;
        }
        else if ( observacaoPortabilidade[0] )
        {
            dsObservacao = observacaoPortabilidade;
        }

        if ( getGrupoTratamentoRetencaoPout() == false )
        {
            SetMessage( "Grupo de tratamento de retenção de portout não especificado.", "N" );
            xml_g->closeTag();
            return;
        }

        inserirAndamento(idGrupoAtual);
        inserirAndamentoObservacao();

        // processo não irá ser exibido na fila de portout por um período parametrizado
        atualizarDataDelayFila();

        // Registra a ação no historico de portabilidade
        gravarPessoaPortabilidadeHist("AGDACAO");

        SetMessage("Reagendamento de portout concluído","S");
    }
    else
    { // se não é processo de portout ...
        bool informarOperadorBKO = false;

        if ( inCri == 1 )
        {
            UsuarioAtual = User;
        }

        getAtendimentoGrupoAtualBko(); // OBTÉM O GRUPO ATUAL EM ATENDIMENTOGRUPOATUAL

        if ( idGrupoAtual )
        {
            if ( preProcessarRelEncFech(ACAO_AGENDAMENTO) == false ) // Incidência 2331 PréProd - Fev/2006
            {
                SetMessage( "Falha na atualização dos relatórios on-line", "N" );

                xml_g->closeTag();

                return;
            }
        }

        if ( UsuarioAtual.ToInt() > 0 )
        {
            getStatusUsuario();

            if ( idStatusUsuario != 1 )
            {
                SetMessage( "Usuário Indisponível para Recepcionar Processos", "N" );
                xml_g->closeTag();
                return;
            }
            else
            {
                atualizarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),true);
            }
        }
        else
        {
            removeAtendimentoUsuarioAtual();
        }

        if ( strcmp(szSgFluxoAtendimento,"MC1")==0 )
        {
            atualizarPessoaUsuarioAbertura(UsuarioAtual.ToInt());
        }

        atualizarAtendimentoPausa(); // INSERT/UPDATE ATENDIMENTOPAUSA

        // Se ação estiver sendo executada por um CRI e o processo tiver sido 
        // encaminhado para um BKO, pega o processo de volta para agendar.
        if (inCri && !inCriAtd)
        {
            getAtendimentoGrupoCri();
            inCriAtd = 1;
            idGrupo = idGrupoAtual;
            alterarAtendimentoGrupoAtual();

            // devolve ao inbox do CRI
            inserirTratamentoCri();

            // vai avisar o BKO que estava com o processo sobre a remoção de seu inbox
            if ( idUsuarioAtendimento )
            {
                informarOperadorBKO = true;
            }
        }
        else
        {
            if ( idGrupo.ToInt() && idGrupoAtual && idGrupo.ToInt() != idGrupoAtual )
            { // se recebeu grupo de troca muda o processo de grupo
                alterarAtendimentoGrupoAtual(idGrupo.ToInt());
            }
            else
            {
                // Incidência 3200 erro ao gravar grupo.
                // como este processo esta com o BKO a variável idGrupo nao e carregada 
                // desta forma o erro ocorre
                // com esta  atribuicao sera gravado o grupo BKO que estiver com o processo
                idGrupo = idGrupoAtual;
            }
        }

        inserirAndamento(idGrupoAtual);
        inserirAndamentoObservacao();
        inserirAndamentoMotivo();
        inserirAtendimentoAgendamento();

        // Avisa o operador bko responsável atual pelo processo sobre a remoção de sua inbox
        if ( informarOperadorBKO )
        {
            inserirAndamentoMensagem(idUsuarioAtendimento // usuario destino
                                    ,User.ToInt() // usuario origem
                                    ,INORIGEM_ANDAMENTOMENSAGEM_CRI);
        }

        getAndamentoTransferencia();
        if (idAndamentoTrans > 0)
        {
            alterarAndamentoTransferencia();
        }
        if (UsuarioAtual.ToInt() > 0)
        {
            inserirAndamentoTransferencia();
        }

        //if (idGrupoAtual != idGrupo.ToInt())
        //{
            inserirAtendimentoNivel();
        //}

        removeAtendimentoSuspeito();
        removeCancelamentoSolicitado();

        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(User.ToInt(),idContato,"AGDACAO");
        // <== SM324--DPR--DEZ/2006--Cassio

        // Retorna processamento
        if ( UsuarioAtual.ToInt() > 0 )
        {
            SetMessage( "Usuário Disponível", "S" );
        }
        else
        {
            SetMessage( "Agendamento Concluído", "S" );
        }
    }

    xml_g->closeTag();
}
