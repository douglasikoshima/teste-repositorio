/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:10 $
 **/ 

/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Solicitacao de cancelamento
 * Migracao: Script 287
 * Revisao.: Dec-07-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_StcAcaoPC.h"

//******************************************************************************************************************
// STCACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(STCACAO);

void implSTCACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implSTCACAO::Execute()");
    
    cWF_SolicitaCancelamento rc(dnode, xml_g, getUser());

    rc.Executar(); 
  
    setStatusCode("04I0000","Execucao de STCACAO Concluida");
    
    ULOG_START("implSTCACAO::Execute()");
}

//******************************************************************************************************************
// cWF_SolicitaCancelamento - Component Implementation
//******************************************************************************************************************

cWF_SolicitaCancelamento::cWF_SolicitaCancelamento(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_SolicitaCancelamento::Executar() 
{
	cWF_Acao::Executar();

	if (idGrupo.ToInt() > 0)
    {
		idGrupoAtual = idGrupo.ToInt();
    }
	else
    {
		getAtendimentoGrupoAtual();
    }

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
	inserirAtendimentoAlerta();
	getAlertaDinamicaAtendimento();
	alterarAtendimentoPriorizacao();
	inserirAndamento();
	alterarAndamentoAtual();
	inserirAndamentoObservacao();
	idMotivo = getMotivoCancelamento();
	inserirAndamentoMotivo();

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"STCACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

	SetMessage( "Solicitação de Cancelamento Concluída", "S" ); 

    xml_g->closeTag();
}

