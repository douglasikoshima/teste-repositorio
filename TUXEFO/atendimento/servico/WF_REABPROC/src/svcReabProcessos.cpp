/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:27 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Reabertura
 * Migracao: Script 563
 * Revisao.: Dec-02-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cRebProcessos.h"

//******************************************************************************************************************
// WF_REABPROC - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(REABPROC);

void implREABPROC::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implREABPROC::Executar()");
    TString retCode = "04I0000";
    TString retMesg = "Execucao de REABPROC Concluida";

    cWFReabProcessos rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implREABPROC::Executar()");

}

//******************************************************************************************************************
// cWF_Reabertura - Component Implementation
//******************************************************************************************************************

cWFReabProcessos::cWFReabProcessos(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
	 ULOG("cWFReabProcessos - user %s - Executando...", user);
     if ( user )
	{
       User = user; 
	   ULOG("cWFReabProcessos - user %d - Executando...", User.ToInt());
	}
}

//------------------------------------------------------------------------------------------------------------------

void cWFReabProcessos::Executar() 
{
    cWF_Acao::Executar(); 

    // Obtém o ID do último grupo CRI que atendeu ao processo
    // getAtendimentoGrupoCri();

    // Verifica se o processo é CRI e se esta dentro do prazo para retornar
    // ao inbox do último consultor que atendeu ao processo.
    //if ( processoCriDentroPrazoRetInboxSimNao() )
    //{
    //    if ( !idGrupoAtual )
    //    {
    //        // se tudo estiver correto com o sistema este passo nunca deveria
    //        // ser executado. Se chegar aqui provavel erro na base!
    //        SetMessage( "Ultimo grupo CRI do processo não encontrado", "S" ); 
    //        xml_g->closeTag();
    //        return;
    //    }
    //
    //    // Obtém o ID do último consultor que atendeu ao processo
    //    getAtendimentoUsuarioCri();
    //
    //    if ( !idUsuarioCri )
    //    {
    //        // se tudo estiver correto com o sistema este passo nunca deveria
    //        // ser executado. Se chegar aqui provavel erro na base!
    //        SetMessage( "Ultimo CRI responsavel não encontrado", "S" ); 
    //        xml_g->closeTag();
    //        return;
    //    }
    //
    //    idGrupo = idGrupoAtual; // ultimo grupo cri que tratou o processo
    //    UsuarioAtual = idUsuarioCri; // ultimo usuario cri que tratou o processo
    //
    //    // Se estiver dentro do prazo de retorno para inbox cri, devolve o processo
    //    // para o inbox.
    //    inserirTratamentoCri();
    //
    //    atualizarAtendimentoUsuarioAtual();
    //} else

    //if (idGrupo.ToInt() == 0)
    //{
    //    getGrupoAtualReabertura();
    //    idGrupo = idGrupoAtual;
    //}

    if ( idGrupo.ToInt() > 0 )
    {
        idGrupoAtual = idGrupo.ToInt();
    }
    else
    {
        getAtendimentoGrupoAtual();
    }

    if ( idGrupoAtual )
    {
        inserirAndamento(idGrupoAtual);
        alterarAndamentoAtual();

        inserirAndamentoObservacao();
        inserirAndamentoMotivo();
    }

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"REABPROC");
    // <== SM324--DPR--DEZ/2006--Cassio

    if ( idGrupoAtual )
    {
        SetMessage( "Reabertura Concluída", "S" ); 
    }
    else
    {
        SetMessage( "Não encontrado grupo para registrar REABERTURA.","M");
    }

    xml_g->closeTag();
}

