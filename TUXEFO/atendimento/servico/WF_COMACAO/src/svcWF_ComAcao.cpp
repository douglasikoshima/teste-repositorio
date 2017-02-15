
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Inserir Comentario - 295
 * Migracao: Script 291
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_ComAcaoPC.h"

//******************************************************************************************************************
// DTRACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFCOMACAO);

void implWFCOMACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFCOMACAO::Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFCOMACAO Concluida";

    cWF_InsereComentario rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implWFCOMACAO::Execute()");
}

//******************************************************************************************************************
// cWF_InsereComentario - Component Implementation
//******************************************************************************************************************

cWF_InsereComentario::cWF_InsereComentario(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_InsereComentario::Executar() 
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

    if ( idAgrEstTPrFt.ToInt() == 0 )
    { //OS 567 - Se não veio o agrupamento estado atual do processo então busca
        getAtendimento();
        idAgrEstTPrAt = idAgrEstTPrFt = idAgrEstadoTProcTestes;
    }

    inserirAndamento();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();

    if ( idMotivo.ToInt() > 0 )
    {
        inserirAndamentoMotivo();
    }

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();

    // Registro a ação no historico de portabilidade
    if ( sgTipoPortabilidade[0] )
    {
        gravarPessoaPortabilidadeHist("WFCOMACAO");
    }

    registrarAcaoDPR(User.ToInt(),idContato,"WFCOMACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Inserção de Comentário Concluida", "S" ); 

    xml_g->closeTag();
}

