/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/ 

#include "../include/cWFAtdConAnalise.h"

DECLARE_TUXEDO_SERVICE(ATDCONANALISE);

void implATDCONANALISE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implATDCONANALISE::Execute()"); 

    TString retCode = "04I0000";
    TString retMesg = "Execucao de ATDCONANALISE Concluida";

    char *user = getUser();

    cWFAtdConAnalise rc(dnode, xml_g, getUser());

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

	ULOG_END("implATDCONANALISE::Execute()"); 

}

//******************************************************************************************************************
// cWFAtdConAnalise - Component Implementation
//******************************************************************************************************************

cWFAtdConAnalise::cWFAtdConAnalise(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWFAtdConAnalise::Executar() 
{
    ULOG_START("cWFAtdConAnalise::Executar()"); 

    cWF_Acao::Executar(); 

    ULOG("cWFAtdConAnalise - %d - Executando...", UsuarioAtual.ToInt());

    getAtendimentoGrupoAtual();

    inCriAtd = 0;
	getAtendimento();
	getAtendimentoPessoa();
	getAtendimentoLinha();
	getGrupoProximoNivel();
	removeAtendimentoUsuarioAtual();
	getAndamentoTransferencia();
	if (idAndamentoTrans > 0)
	{
	    alterarAndamentoTransferencia();
	}
    alterarAtendimentoGrupoAtual();
	removeAtendimentoSuspeito();
	removeCancelamentoSolicitado();
    inserirAtendimentoNivel();

	idFaseAtendimento = idFase.ToInt();
	nrNivelAtendimento++;
	alterarAtendimento();

    inserirAndamento();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"ATDCONANALISE");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Análise Concluída", "S" ); 
    xml_g->closeTag();
    
    ULOG_END("cWFAtdConAnalise::Executar()"); 

}
