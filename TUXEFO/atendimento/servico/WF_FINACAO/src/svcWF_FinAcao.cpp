
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Finaliza��o de analise de processo CRI com BKO
 * Migracao: Script 278
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_FinAcaoPC.h"

//******************************************************************************************************************
// WFFINACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFFINACAO);

void implWFFINACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFFINACAO:Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFFINACAO Concluida";

    cWF_FinalizarCri rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implWFFINACAO:Execute()");
}

//******************************************************************************************************************
// cWF_FinalizarCri - Component Implementation
//******************************************************************************************************************

cWF_FinalizarCri::cWF_FinalizarCri(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_FinalizarCri::Executar() 
{
    bool devolverFila = false;

    cWF_Acao::Executar(); 

    getAtendimentoUsuarioCri();

    UsuarioAtual = idUsuarioCri;

    getStatusUsuario();

    if ( idStatusUsuario != 1 )
    {
        devolverFila = true;
    }

    getAtendimentoGrupoAtualBko();

    int idGrupoAtualBKO = idGrupoAtual; // Salva o id do grupo BKO executor da a��o

    inserirAtendimentoNivel(idGrupoAtual);

    getAtendimentoGrupoCri();

    idGrupo = idGrupoAtual; // <== GRUPO ATUAL DO CRI RESPONS�VEL PELO PROCESSO

    // Vide explica��o do porque deste teste no texto REGRA DE RETORNO AO CRI abaixo.
    if ( UsuarioAtual.ToInt() && !devolverFila )
    {
        inserirTratamentoCri();
    }

    // incrementa a qtde de idas/voltas ao BKO
    incAtendimentoUsuarioCriQtEnvioBKO();

    // ------------------------------------------------------------------------------
    // incid�ncia 3270 - CRI 
    // esta inser��o estava fazendo com que a condicao de apari��o (idFluxoFase)
    // removesse a a��o AGENDAR das a��es do CRI.
    //inserirAtendimentoGrupoReceptor(); // ATENDIMENTO.ATENDIMENTOGRUPODEVOLUCAO
    // ------------------------------------------------------------------------------
    
    inCriAtd = 1;
    alterarAtendimentoGrupoAtual();

    getAtendimentoUsuarioAtual();

    // REGRA DE RETORNO AO CRI
    // idUsuarioAtendimento � usado apenas "pr�-forma" para executar INSERT ou
    // UPDATE em ATENDIMENTOUSUARIOATUAL se UsuarioAtual=0, significa que algum
    // supervisor executou a a��o TROCAR CRI com finalidade de enviar o processo
    // para a fila de CRI e portanto a a��o FINALIZAR ANALISE n�o pode devolver 
    // o processo diretamente ao CRI original.

    if ( UsuarioAtual.ToInt() && !devolverFila )
    {
        if ( idUsuarioAtendimento > 0 )
        {
            // Devolve o processo ao inbox do CRI que mandou ao BKO
            alterarAtendimentoUsuarioAtual();

            getAndamentoTransferencia();
            if (idAndamentoTrans > 0)
            {
                alterarAndamentoTransferencia();
            }
            // ------------------------------------------------------------------------------
            // incid�ncia 3270 - CRI 
            // esta inser��o estava fazendo com que a condicao de apari��o (idFluxoFase)
            // removesse a a��o AGENDAR das a��es do CRI.
            //
            // Registra o n�mero do usu�rio que esta finalizando a a��o em
            // ATENDIMENTO.ATENDIMENTOUSUARIODEVOLUCAO.
            getAtendimentoUsuarioDevolucao();

            if ( UsuarioAtual.ToInt() )
            {
                removeAtendimentoUsuarioReceptor();
            }

            inserirAtendimentoUsuarioReceptor();
            // ------------------------------------------------------------------------------
        }
        else
        {
            inserirAtendimentoUsuarioAtual();
        }
    }
    else
    {
        removeAtendimentoUsuarioAtual();
        removeAtendimentoCri();

        // Retorna o estado do processo para TRATAMENTO FILA
        switch (idAgrEstTPrAt.ToInt())
        {
            case 3:
                idAgrEstTPrFt = 2;
            break;
            case 16:
                idAgrEstTPrFt = 15;
            break;
        }
    }

    //getAtendimentoGrupoBko();
    //qtIntercambios = 0;
    //alterarAtendimentoGrupoBko();

    inserirAndamento(idGrupoAtualBKO);
    inserirAndamentoTransferencia();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();
    //
    // Alteracao para atendimento da incidencia 3249, para a atualizacao da devolucao
    //
    // getAtendimentoGrupoBko();

    struct st_AtendimentoGrupoAtual oDados;
    struct st_vlAtendimentoGrupoAtual oStatus;
    XMLGen xmlObtemAndamento;

    memset(&oDados, 0, sizeof(st_AtendimentoGrupoAtual));
    memset(&oStatus, -1, sizeof(st_vlAtendimentoGrupoAtual));

    oDados.idAtendimento = idAtendimento.ToLong();
    oStatus.idAtendimento = 1;

    strcpy(oDados.dtSaida, sSysDate.c_str());
    oStatus.dtSaida = 1;

    oDados.idGrupo = idGrupoAtualBKO;
    oStatus.idGrupo = 1;

    oDados.qtIntercambios = ++qtIntercambios;
    oStatus.qtIntercambios = 1;

    cWFAtendimentoGrupoAtual grpAtual(&oDados, &oStatus, &xmlObtemAndamento);

    grpAtual.alterarBko();

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"WFFINACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    if ( devolverFila )
    {
        SetMessage( "Analise BKO Finalizada. Processo retornado a Fila CRI.", "S" ); 
    }
    else
    {
        SetMessage( "Analise BKO Finalizada. Processo retornado ao CRI.", "S" ); 
    }

    xml_g->closeTag();
}
