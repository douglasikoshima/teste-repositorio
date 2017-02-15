/**
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1.2.6 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:38 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: DBKACAO - Devolver 
 * Migracao: Script 294
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_DbkAcaoPC.h"

extern int proCUsuarioTemSkill( int idPessoaUsuario, int idGrupo );
extern int proCUsuarioTemAcesso( int idPessoaUsuario, int idGrupo, long idAtendimento );
extern int procBuscaAndamentoDTRACAO(long _idAtendimento);

//******************************************************************************************************************
// WF_ENCAMINHAM - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(DBKACAO);

void implDBKACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implDBKACAO::Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de DBKACAO Concluida";

    cWF_DevolverBKO rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implDBKACAO::Execute()");

}

//******************************************************************************************************************
// cWF_DevolverBKO - Component Implementation
//******************************************************************************************************************

cWF_DevolverBKO::cWF_DevolverBKO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_DevolverBKO::Executar() 
{
    bool retornarParaFila = false;

    Init();

    getAtendimento();

    // if ( strcmp(szSgFluxoAtendimento,"MC1")==0 )
    // {
    //     SetMessage( "Processo 'Meu Cliente 1' não pode trafegar.", "N" );
    //     xml_g->closeTag();
    //     return;
    // }

    if ( idAtividade.ToInt() == 66 /* DEVOLVENDO PARA ANALISTA MC2 */)
    {
        if ( EncerrarMC2() == false )
        {
            return;
        }
    }

    // Executa a ação de devolução do processo
    if ( DevolverBKO(retornarParaFila) == false )
    {
        return;
    }

    // decide a mensagem de saída
    if ( strcmp(szSgFluxoAtendimento,"MC2") )
    {
        if ( retornarParaFila )
        {
            SetMessage( "Devolução a Tratamento Fila Concluída", "S" );
        }
        else
        {
            SetMessage( "Devolução a BKO Concluída", "S" );
        }
    }
    else
    {
        if ( idAtividade.ToInt() == 66 /* DEVOLVENDO PARA ANALISTA MC2 */)
        {
            if ( retornarParaFila )
            {
                SetMessage( "Protocolo devolvido para a fila do grupo do analista 'Meu Cliente 2'.", "S" );
            }
            else
            {
                SetMessage( "Protocolo devolvido ao analista 'Meu Cliente 2'.", "S" );
            }
        }
        else
        {
            if ( retornarParaFila )
            {
                SetMessage( "Protocolo 'Meu Cliente 2' retornado à fila do grupo do analista BKO anterior.", "S" );
            }
            else
            {
                SetMessage( "Protocolo 'Meu Cliente 2' retornado ao analista BKO anterior.", "S" );
            }
        }
    }

    xml_g->closeTag();
    
    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"DBKACAO");
    // <== SM324--DPR--DEZ/2006--Cassio
}


