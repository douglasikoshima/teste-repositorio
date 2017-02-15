
#include "../include/cWFMarcarSusp.h"


DECLARE_TUXEDO_SERVICE(MARCARSUSP);

void implMARCARSUSP::Execute( DOMNode*dnode,XMLGen*xml_g ) 
{

    TString retCode = "04I0000";
    TString retMesg = "Execucao de MARCARSUSP Concluida";

    cWFMarcarSusp rc(dnode, xml_g, getUser());

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
}

//******************************************************************************************************************
// cWFMarcarSusp - Component Implementation
//******************************************************************************************************************

cWFMarcarSusp::cWFMarcarSusp(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     ULOG("cWFMarcarSusp - user %s - Executando...", user);
     if ( user )
    {
       User = user; 
       ULOG("cWFMarcarSusp - user %d - Executando...", User.ToInt());
    }
}

//------------------------------------------------------------------------------------------------------------------

void cWFMarcarSusp::Executar() 
{
    ULOG_START("cWFMarcarSusp::Executar()");

    cWF_Acao::Executar(); 

    getAtendimentoGrupoAtual();

    // Tratamento para retirada de pausa o atendimento.
    getAtendimentoUsuarioAtual();

    if (idUsuarioAtendimento > 0)
    {
        ULOG("cWFMarcarSusp - Atendimento possui usuário, retirando pausa...");
        dtInicioTransferencia[0] = 0;
        inPausa = 0;

        alterarAtendimentoUsuarioAtual(idUsuarioAtendimento,true);
    }

    inserirAtendimentoSuspeito();
    removeCancelamentoSolicitado();

    inserirAndamento(idGrupoAtual);
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"MARCARSUSP");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Marcação Como Suspeito Concluida", "S" ); 

    xml_g->closeTag();

    ULOG_END("cWFMarcarSusp::Executar()");
}

