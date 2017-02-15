/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Marcelo Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:19 $
 **/ 

#include "../include/cWF_PORECOFIVR.h"

//******************************************************************************************************************
// WF_PORECOFIVR - Service Implementation
// Descri��o da a��o: Usado pelo modulo de retencao, para indicar o aceite de oferta por intercepta��o URA.
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(PORECOFIVR);

void implPORECOFIVR::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implPORECOFIVR::Execute()"); 
    TString retCode = "04I0000";
    TString retMesg = "Execucao de PORECOFIVR Concluida";

    cWF_PORECOFIVR rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implPORECOFIVR::Execute()"); 

}

//******************************************************************************************************************
// cWF_PORECOFIVR - Component Implementation
//******************************************************************************************************************

cWF_PORECOFIVR::cWF_PORECOFIVR(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_PORECOFIVR::Executar() 
{
    cWF_Acao::Executar();
    cWF_Acao::getAtendimento();

    if ( getGrupoTratamentoRetencaoPout() == false )
    {
        SetMessage( "Grupo de tratamento de reten��o de portout n�o especificado.", "N" );
        xml_g->closeTag();
        return;
    }

    inserirAndamento(idGrupoAtual);
    inserirAndamentoObservacao();

    // processo n�o ir� ser exibido na fila de portout por um per�odo parametrizado
    atualizarDataDelayFila();

    // Registra a a��o no historico de portabilidade
    gravarPessoaPortabilidadeHist("PORECOFIVR");

    SetMessage("Intercepta��o IVR de recusa oferta","S");

    xml_g->closeTag();
}
