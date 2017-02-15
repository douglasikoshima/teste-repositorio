
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Prosseguir BKO
 * Migracao: Script 280
 * Revisao.: Dec-09-2004 - ???
 *--------------------------------------------------------*/

#include "../include/cWF_PbkoAcaoPC.h"

//******************************************************************************************************************
// WFPBKOACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFPBKOACAO);

void implWFPBKOACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFPBKOACAO::Executar()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFPBKOACAO Concluida";

    cWF_ProsseguirBko rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implWFPBKOACAO::Executar()");
}

//******************************************************************************************************************
// cWF_ProsseguirBko - Component Implementation
//******************************************************************************************************************

cWF_ProsseguirBko::cWF_ProsseguirBko(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_ProsseguirBko::Executar() 
{
    cWF_Acao::Executar(); 

    getAtendimentoGrupoAtual();

    getAtendimento();
    getAtendimentoPessoa();
    getAtendimentoLinhaCri();
    getAtendimentoUsuarioAtual();

	idFase = 1;
    getGrupoProximaFaseSemConsiderarRetorno(); // prox. grupo bko

    if ( idGrupo.ToInt() == 0 )
    {
        SetMessage( "Grupo BKO não configurado para este contato na fase TRATAMENTO", "N" ); 
        xml_g->closeTag();
        return;
    }

    inCriAtd = 0; // INCRI = 0
    alterarAtendimentoGrupoAtual();
    // alterarAtendimentoTipoRetorno(); // ?? para que isso?? Março,2006/Cassio
    getAtendimentoGrupoBko();

    if ( idGrupoAtualBKO == 0 )
    {
        qtIntercambios = 1;
        inserirAtendimentoGrupoBko();
    }
    else
    {
        qtIntercambios++;
        alterarAtendimentoGrupoBko();
    }

    idFase = 2;
	inserirAtendimentoNivel(idGrupoAtual);  
    removeAtendimentoUsuarioAtual();

    getAndamentoTransferencia();
    if (idAndamentoTrans > 0)
    {
        alterarAndamentoTransferencia();
    }

    // Obtém o próximo agrupamento/estado para o processo sendo removido da fila
    getAgrupEstadoTpProcFt(PROX_AGRUP_PODE_SER_IGUAL_ATUAL);

    if ( idAgrEstTPrFt.ToInt() == -1 )
    {
        SetMessage( "Proximo estado não encontrado para este processo", "N" ); 
        xml_g->closeTag();
        return;
    }

    inserirAndamento( idGrupoAtual );
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    getAtendimentoUsuarioCri();
    qtEnvioBko++;
    UsuarioAtual = idUsuarioCri;
    alterarAtendimentoCri();

    removeTratamentoCri();

    // Avisa o operador responsável atual pelo processo sobre o prosseguimento
    if ( idUsuarioAtendimento )
    {
        inserirAndamentoMensagem(idUsuarioAtendimento
                                ,User.ToInt()
                                ,inCri?INORIGEM_ANDAMENTOMENSAGEM_CRI:INORIGEM_ANDAMENTOMENSAGEM_BKO);
    }

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"WFPBKOACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Encaminhamento ao Bko Concluído", "S" ); 

    xml_g->closeTag();
}

