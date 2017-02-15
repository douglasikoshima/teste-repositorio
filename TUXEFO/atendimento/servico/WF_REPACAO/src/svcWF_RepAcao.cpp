/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:02 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Devolver a Tratamento 
 * Migracao:Script 293
 * Revisao.: Dec-07-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_RepAcaoPC.h"

//******************************************************************************************************************
// DTRACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(REPACAO);

void implREPACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implREPACAO::Executar()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de REPACAO Concluida";

    cWF_ReicidenciaProcesso rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implREPACAO::Executar()");

}

//******************************************************************************************************************
// cWF_ReicidenciaProcesso - Component Implementation
//******************************************************************************************************************

cWF_ReicidenciaProcesso::cWF_ReicidenciaProcesso(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_ReicidenciaProcesso::Executar() 
{
    cWF_Acao::Executar(); 

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
    registrarAcaoDPR(User.ToInt(),idContato,"REPACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    if ( idGrupoAtual )
    {
        SetMessage( "Reincidencia Concluída","S" ); 
    }
    else
    {
        SetMessage( "Não encontrado grupo para registrar REINCIDÊNCIA.","M");
    }

    xml_g->closeTag();

}


