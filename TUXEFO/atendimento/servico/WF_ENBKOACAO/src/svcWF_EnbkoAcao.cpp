/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:38 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: WFENBKOACAO - Devolver de CRI a BKO
 * Migracao: Script 294
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_EnbkoAcaoPC.h"

extern int proCUsuarioTemSkill( int idPessoaUsuario, int idGrupo );
extern int proCUsuarioTemSkill( long idPessoaUsuario );
extern int proCUsuarioTemAcesso( int idPessoaUsuario, int idGrupo, long idAtendimento );

//******************************************************************************************************************
// WFENBKOACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFENBKOACAO);

void implWFENBKOACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFENBKOACAO::Execute()");

    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFENBKOACAO Concluida";

    cWF_EnBKOAcao rc(dnode, xml_g, getUser());

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
    
    ULOG_START("implWFENBKOACAO::Execute()");


}

//******************************************************************************************************************
// cWF_EnBKOAcao - Component Implementation
//******************************************************************************************************************

cWF_EnBKOAcao::cWF_EnBKOAcao(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_EnBKOAcao::Executar() 
{
    cWF_Acao::Executar(); 

    ULOG("cWF_EnBKOAcao - %d - Executando...", UsuarioAtual.ToInt());

    getAtendimento();

    //getAtendimentoGrupoAtualBko();

    getAtendimentoGrupoAtual();

    //getAtendimentoGrupoDevolucao();

    getAtendimentoGrupoBko();

    if ( idGrupoAtualBKO == 0 )
    {
        SetMessage( "Grupo de devolução não encontrado", "N" ); 
        xml_g->closeTag();
        return;
    }

    getAtendimentoUsuarioDevolucao();

    if ( UsuarioAtual.ToInt() > 0 )
    {
        // =============================================================
        // incidência 2939 - TD Sanity Skill Vivo - Novembro/2005
        // -------------------------------------------------------------
        // Alteração para atender ao comentário seguinte:
        // 
        // "Avaliar se o processo é válido para o usuário do passo 1, 
        //  caso contrário retornar o processo para a Fila";
        bool retornarParaFila = false;

        if ( proCUsuarioTemSkill( UsuarioAtual.ToInt()),idGrupo.ToInt() )
        {
            retornarParaFila = 
                proCUsuarioTemAcesso(UsuarioAtual.ToInt()
                                    ,idGrupo.ToInt()
                                    ,idAtendimento.ToLong()) ? false:true;
        }

        // ==============================================================

        getStatusUsuario();

        if ( idStatusUsuario != 1 || retornarParaFila )
        {
            removeAtendimentoUsuarioAtual();
        }
        else
        {
            getAtendimentoUsuarioAtual();
            if ( idUsuarioAtendimento > 0 ) 
            {
                alterarAtendimentoUsuarioAtual();
                getAndamentoTransferencia();
                if (idAndamentoTrans > 0)
                {
                    alterarAndamentoTransferencia();
                }
            }
            else
            {
                inserirAtendimentoUsuarioAtual();
            }
        }
    }

    //getAtendimentoGrupoBko();
    idGrupo = idGrupoAtualBKO;
    inCriAtd = 0;
    alterarAtendimentoGrupoAtual();

    qtIntercambios++;
    alterarAtendimentoGrupoBko();

    //removeAtendimentoGrupoReceptor();
    removeAtendimentoUsuarioReceptor();
    //removeAtendimentoBaixaAtual();

    //if (idFase.ToInt() > 2)
    //{
    //    concluirAtendimentoNivel(idFase.ToInt());
    //}
    //else
    //{
        inserirAtendimentoNivel(idGrupoAtual);
    //}

    //idFaseAtendimento = 2;
    //alterarAtendimento();

    inserirAndamento(idGrupoAtual);
    alterarAndamentoAtual();

    if ( UsuarioAtual.ToInt() > 0 )
    {
        inserirAndamentoTransferencia();
    }

    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    removeTratamentoCri();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"WFENBKOACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    SetMessage( "Devolução de CRI a BKO Concluída", "S" );

    xml_g->closeTag();
}
