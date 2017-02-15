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
 * Servico.: Devolver a Tratamento 
 * Migracao:Script 293
 * Revis2ao.: Dec-07-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_DtrAcaoPC.h"

extern int procBuscaAndamentoDTRACAO(long _idAtendimento);
extern int proCUsuarioTemSkill( int idPessoaUsuario, int idGrupo );
extern int proCUsuarioTemAcesso( int idPessoaUsuario, int idGrupo, long idAtendimento );

//******************************************************************************************************************
// DTRACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(DTRACAO);

void implDTRACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implDTRACAO:Execute()");

    TString retCode = "04I0000";
    TString retMesg = "Execucao de DTRACAO Concluida";

    cWF_DevTratamento rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
    }
    catch ( TuxBasicOraException *tboe )
    {
		ULOGE("DTRACAO - ERRO ORACLE na execucao...");
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);

        retCode = errCode;
        retMesg = tboe->pMsg;

        delete tboe;
    }
    catch( TuxException* pTux )
    {
		ULOGE("DTRACAO - ERRO TUXEDO na execucao...");
        retCode = "04E9998";
        retMesg = pTux->getMessage();

        delete pTux;
    }
    catch(...)
    {
		ULOGE("DTRACAO - ERRO DESCONHECIDO na execucao...");
        retCode = "04E9999";
        retMesg = "Excecao desconhecida";
    }

    setStatusCode(retCode.c_str(),retMesg.c_str());
    
    ULOG_END("implDTRACAO:Execute()");
}

//******************************************************************************************************************
// cWF_DevTratamento - Component Implementation
//******************************************************************************************************************

cWF_DevTratamento::cWF_DevTratamento(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_DevTratamento::Executar() 
{
    bool retornarParaFila = false;

    Init();

    if ( idAgrEstTPrAt.ToInt() == 4 || idAgrEstTPrAt.ToInt() == 17 )
    { // Se estiver em estado de TRATAMENTO MASSA, esta usando a ação REANALISE
      // para devolver ao BKO...
        if ( ReanalisarBKO(retornarParaFila) == false )
        {
            return;
        }

        // decide a mensagem de saída
        if ( retornarParaFila )
        {
            SetMessage( "Processo retornado à fila para reanalise", "S" );
        }
        else
        {
            SetMessage( "Processo retornado a BKO para reanalise", "S" );
        }
    }
    else
    {
        // Executa a ação de devolução do processo
        if ( DevolverBKO(retornarParaFila) == false )
        {
            return;
        }

        // decide a mensagem de saída
        if ( retornarParaFila )
        {
            SetMessage( "Devolução a Tratamento Fila Concluída", "S" );
        }
        else
        {
            SetMessage( "Devolução a BKO Concluída", "S" );
        }
    }

    xml_g->closeTag();
}

