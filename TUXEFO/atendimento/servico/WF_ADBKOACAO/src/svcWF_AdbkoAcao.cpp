
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Troca Cri
 * Migracao: Script 800
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_AdbkoAcaoPC.h"

//******************************************************************************************************************
// WFADBKOACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFADBKOACAO);

void implWFADBKOACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFADBKOACAO::Execute()"); 
    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFADBKOACAO Concluida";

    cWF_AdquirirBko rc(dnode, xml_g, getUser());

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
    ULOG_END("implWFADBKOACAO::Execute()"); 
}

//******************************************************************************************************************
// cWF_AdquirirBko - Component Implementation
//******************************************************************************************************************

cWF_AdquirirBko::cWF_AdquirirBko(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 

    idUsuarioCRIAlterado=0;
}

//------------------------------------------------------------------------------------------------------------------
void cWF_AdquirirBko::Executar() 
{
    cWF_Acao::Executar(); 

    if ( !inRC && !inCri )
    {
        SetMessage( "Esta ação só pode ser executada para contato prévio ou CRI.", "N" ); 
        xml_g->closeTag();
        return;
    }

    getAtendimentoGrupoAtualBko();

    //int idGrupoAtualBKO = idGrupoAtual;

    if ( inRC )
    {
        adquirirContatoPrevio();
    }
    else
    {
        if ( !adquirirCRI() )
        {
            SetMessage( "Proximo estado não encontrado para o processo", "N" ); 
            xml_g->closeTag();
            return;
        }
    }

    if ( UsuarioAtual.ToInt() == 0 ) 
    {
        UsuarioAtual = User;
    }

	inserirAtendimentoNivel();

    atualizarAtendimentoUsuarioAtual(User.ToInt(),true);

    getAndamentoTransferencia();

    if (idAndamentoTrans > 0)
    {
    	alterarAndamentoTransferencia();
    }

    inserirAndamento();
    inserirAndamentoTransferencia();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    if ( idUsuarioAtendimento > 0 )
    {
        if ( inRC )
        {
            inserirAndamentoMensagemRC(idUsuarioAtendimento);
        }
        else if ( idUsuarioCRIAlterado ) // Se estiver adquirindo processo em poder de BKO...
        {
            inserirAndamentoMensagem(idUsuarioAtendimento,User.ToInt()
                                    ,INORIGEM_ANDAMENTOMENSAGEM_CRI);
        }
    }

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"WFADBKOACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Aquisição de Processo Concluída", "S" );

    xml_g->closeTag();
}

bool cWF_AdquirirBko::adquirirCRI()
{
	getAtendimentoGrupoCri();

    inCriAtd = 1; // ATRIBUI inCriAtd A COLUNA ATENDIMENTOGRUPOATUAL.INCRI

    alterarAtendimentoGrupoAtual(idGrupoAtual);

    idGrupo = idGrupoAtual;

    UsuarioAtual = User.ToInt();

    getAtendimentoUsuarioCri(); // Atendimento.AtendimentoCri
    if ( idUsuarioCri == 0 )
    {
        // Obtém o próximo agrupamento/estado para o processo sendo removido da fila
        getAgrupEstadoTpProcFt(PROX_AGRUP_DIFERENTE_DO_ATUAL);

        if ( idAgrEstTPrFt.ToInt() == -1 )
        {
            getAgrupEstadoTpProcFt(PROX_AGRUP_PODE_SER_IGUAL_ATUAL);
        }

        if ( idAgrEstTPrFt.ToInt() == -1 )
        {
            return false;
        }

        inserirAtendimentoCri();
    }
    else
    {
        alterarAtendimentoCri();
        // Salva indicador de que o processo já esteve uma vez na mão de um CRI
        idUsuarioCRIAlterado = idUsuarioCri;
    }

    getTratamentoUsuarioCri(); // Atendimento.TratamentoCri
    if ( idUsuarioCri == 0 )
    {
        inserirTratamentoCri(); // Atendimento.TratamentoCri
    }
    else
    {
        alterarTratamentoCri();
    }

    return true;
}
