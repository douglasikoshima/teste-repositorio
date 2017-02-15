/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:03 $
 **/ 


/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Inserir Incidencia - 291
 * Migracao: Script 291
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_IniAcaoPC.h"

//******************************************************************************************************************
// INIACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(INIACAO);

void implINIACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implINIACAO::Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de INIACAO Concluida";

    cWF_InsereInsistencia rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implINIACAO::Execute()");
    
}

//******************************************************************************************************************
// cWF_InsereInsistencia - Component Implementation
//******************************************************************************************************************

cWF_InsereInsistencia::cWF_InsereInsistencia(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------
// Nota: O correto é InsereInsistencia
//
void cWF_InsereInsistencia::Executar() 
{
    cWF_Acao::Executar(); 

    if (idGrupo.ToInt() > 0)
    {
		idGrupoAtual = idGrupo.ToInt();
    }
	else
    {
        getAtendimentoGrupoAtualBko();
        idGrupo = idGrupoAtual;
    }

	getAtendimento();

	idFaseAtendimento = idFase.ToInt();
	qtInsistenciaAtendimento++;

	alterarAtendimento();
    getAlertaAtendimento();
	inserirAtendimentoAlerta();
	getAlertaDinamicaAtendimento();
	alterarAtendimentoPriorizacao();
    inserirAndamento();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"INIACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Inserção de Insistência Concluída", "S" ); 

    xml_g->closeTag();
}

