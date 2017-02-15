/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  Encaminha ao Cri
 * @author  Miguel Angel Benaventes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:00 $
 **/

#include "../include/cWF_EcriAcaoPC.h"

//******************************************************************************************************************
// WFECRIACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFECRIACAO);

void implWFECRIACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFECRIACAO::Execute()");

    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFECRIACAO Concluida";

    cWF_EncaminhaCri rc(dnode, xml_g, getUser());

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
    
    ULOG_END("implWFECRIACAO::Execute()");
}

//******************************************************************************************************************
// cWF_EncaminhaCri - Component Implementation
//******************************************************************************************************************

cWF_EncaminhaCri::cWF_EncaminhaCri(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_EncaminhaCri::Executar() 
{
    cWF_Acao::Executar(); 

    getAtendimentoUsuarioAtual();

    if ( idUsuarioAtendimento > 0 )
    {
        SetMessage( "Processo já esta sob responsabilidade de um consultor", "N" );
        xml_g->closeTag();
        return;
    }

    // Checa se ID destino foi informado
    if (UsuarioAtual.ToInt() == 0)
    {
        SetMessage( "Consultor destino não informado", "N" ); 
        xml_g->closeTag();
        return;
    }

    // Verifica o status do usuario destino
    getStatusUsuario();

    if ( idStatusUsuario != 1 )
    {
        SetMessage( "Usuário Indisponível para Recepcionar Processos", "N" ); 
        xml_g->closeTag();
        return;
    }

    //getAtendimentoUsuarioCri();

    // Se grupo destino não foi informado, 
    if (idGrupo.ToInt() <= 0)
    {
        SetMessage( "Grupo destino não informado", "N" ); 
        xml_g->closeTag();
        return;
    }

    int usuarioAtualBKO = UsuarioAtual.ToInt();

    if ( idAgrEstTPrAt.ToInt() == 2 ) // se estiver em trat.fila passa para trat.bko
    {
        getAgrupEstadoTpProcFt(PROX_AGRUP_DIFERENTE_DO_ATUAL);
    }

    getAtendimentoGrupoAtual(); // importante apenas porque tras idPessoaLinhaHistorico de GrupoCri

    //UsuarioAtual = User;

    //removeAtendimentoUsuarioAtual();

    inCriAtd = 1; // faz com que INCRI seja = 1 em ATENDIMENTO.ATENDIMENTOGRUPOATUAL

	inserirAtendimentoNivel();
    inserirAtendimentoUsuarioAtual();
    inserirAtendimentoCri();
    inserirTratamentoCri();
    alterarGrupoCri();
    alterarAtendimentoGrupoAtual();

    inserirAndamento();
    inserirAndamentoTransferencia();
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    //if ( usuarioAtualBKO > 0 )
    //{ // deixa mensagem ao usuário bko sobre a ação do consultor CRI
    //    inserirAndamentoMensagem(usuarioAtualBKO,User.ToInt());
    // }

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"WFECRIACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Encaminhamento ao CRI Concluído", "S" ); 

    xml_g->closeTag();
}

