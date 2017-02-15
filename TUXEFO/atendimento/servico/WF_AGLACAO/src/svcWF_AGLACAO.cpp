/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  Agrega linhas a um processo - OS 661 - Protocolo
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/

#include "../include/cWF_AGLACAOPC.h"

//******************************************************************************************************************
// WFAGLACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFAGLACAO);

void implWFAGLACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFAGLACAO:Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFAGLACAO Concluida";

    cWF_AglAcao rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implWFAGLACAO:Execute()");
}

//******************************************************************************************************************
// cWF_AglAcao - Component Implementation
//******************************************************************************************************************

cWF_AglAcao::cWF_AglAcao(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
    carregaDados(dnode);

    SetNodesDef( dnode, xml_g ); 

    if ( user ) { User = user;  }
}

//------------------------------------------------------------------------------------------------------------------
void cWF_AglAcao::Executar() 
{
    cWF_Acao::Executar();

    int qtLinhasAssociadas = listaAtendimentoLinhas.GetCount();

    if ( qtLinhasAssociadas > 0 )
    {
        getAtendimentoGrupoAtualBko();
        getAtendimento();

        inserirAtendimentoLinhas(idAtendimento.ToLong(),&listaAtendimentoLinhas);

        inserirAndamento(idGrupoAtual);
        inserirAndamentoObservacao();
        inserirAndamentoMotivo();

        // ==> SM324--DPR--DEZ/2006--Cassio
        registrarAcaoDPR(User.ToInt(),idContato,"WFAGLACAO");
        // <== SM324--DPR--DEZ/2006--Cassio

        if ( qtLinhasAssociadas == 1 )
        {
            SetMessage("Linha inserida ao processo.","S");
        }
        else
        {
            SetMessage("Linhas inseridas ao processo.","S");
        }
    }
    else
    {
        SetMessage("Nenhuma linha informada para associação ao processo.","M");
    }

    xml_g->closeTag();
}

void cWF_AglAcao::carregaDados(DOMNode* entrada)
{
    int contador = 0;
    stAtendimentoLinhas *statendimentolinhas;
    char *cdConta;
    char *nrTelefone;
    DOMNode *registro;

    while ( (registro=walkDOM(entrada,"LinhasAssociadasVO",contador++)),registro )
    {
        statendimentolinhas = new stAtendimentoLinhas();

        if ( cdConta=walkTree(registro,"cdConta",0),!cdConta )
        {
            continue;
        }

        if ( 0 == *cdConta )
        {
            XMLString::release(&cdConta);
            continue;
        }

        if ( nrTelefone=walkTree(registro,"nrTelefone",0),!nrTelefone )
        {
            XMLString::release(&cdConta);
            continue;
        }

        if ( 0 == *nrTelefone )
        {
            XMLString::release(&cdConta);
            XMLString::release(&nrTelefone);
            continue;
        }

        SAFE_STRNCPY(statendimentolinhas->cdConta,cdConta);
        SAFE_STRNCPY(statendimentolinhas->nrTelefone,nrTelefone);

        XMLString::release(&cdConta);
        XMLString::release(&nrTelefone);

        listaAtendimentoLinhas.AddItem((void*) statendimentolinhas);
    }
}