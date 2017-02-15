/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @remark  Encaminhamento Manual
 * @author  Roberto Borges
 * @version $Revision: 1.1.2.9 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/03/08 20:16:11 $
 **/ 

#include "../include/cWF_EncAcaoPC.h"

DECLARE_TUXEDO_SERVICE(ENCACAO);

void implENCACAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implENCACAO:Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de ENCACAO Concluida";


    cWF_ENCACAO rc(dnode, xml_g, getUser());

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
    
	ULOG_END("implENCACAO:Execute()");

}

//******************************************************************************************************************
// cWF_ENCACAO - Component Implementation
//******************************************************************************************************************

cWF_ENCACAO::cWF_ENCACAO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_ENCACAO::Executar() 
{
    bool obterEstadoFuturo = true;

    cWF_Acao::Executar();

    getAtendimento();

    // if ( strcmp(szSgFluxoAtendimento,"MC1")==0 )
    // {
    //     SetMessage( "Processo 'Meu Cliente 1' não pode ser encaminhado.", "N" ); 
    //     xml_g->closeTag();
    //     return;
    // }

    if ( strcmp(szSgFluxoAtendimento,"MC1")==0 && UsuarioAtual.ToInt() == 0 )
    {
        SetMessage( "Processo 'Meu Cliente 1' não pode ser encaminhado somente para grupo", "N" ); 
        xml_g->closeTag();
        return;
    }

    if ( UsuarioAtual.ToInt() > 0 )
    {
        getStatusUsuario();

        if ( idStatusUsuario != 1 )
        {
            SetMessage( "Usuário Indisponível para recepcionar processos", "N" ); 
            xml_g->closeTag();
            return;
        }
    }
    else
    {
        obterEstadoFuturo = false;
    }

    // Obtém o próximo agrupamento/estado para o processo sendo removido da fila
    if ( obterEstadoFuturo )
    { // se usuário destino não informado, processo continua em TRATAMENTO FILA
        getAgrupEstadoTpProcFt(PROX_AGRUP_PODE_SER_IGUAL_ATUAL);
    }

    if ( idAgrEstTPrFt.ToInt() == -1 )
    {
        SetMessage( "Proximo estado não encontrado para este processo", "N" ); 
        xml_g->closeTag();
        return;
    }

    if ( strcmp(szSgFluxoAtendimento,"MC2")==0 )
    {
        if ( idFaseAtendimento != RETORNO )
        {
            if ( UsuarioAtual.ToInt() > 0 ) 
            {
                if ( UsuarioAtual == idPessoaUsuarioMC )
                {
                    SetMessage( "Use a ação de devolução para encaminhar protocolo Meu Cliente 2 para o analista de origem.","N" ); 
                    xml_g->closeTag();
                    return;
                }
            }
        }
        else // if ( idFaseAtendimento == RETORNO )
        {
            if ( UsuarioAtual.ToInt() > 0 ) 
            {
                int UsuarioAtualTmp = UsuarioAtual.ToInt(); // salva valor original
                getAtendimentoUsuarioDevolucao();
                int UsuarioDevolucao = UsuarioAtual.ToInt();
                UsuarioAtual = UsuarioAtualTmp; // restaura valor original

                if ( UsuarioDevolucao == UsuarioAtual.ToInt() )
                {
                    SetMessage( "Use ação 'DEVOLVER BKO' para retornar protocolo Meu Cliente 2 para o analista nivel 2.","N" );
                    xml_g->closeTag();
                    return;
                }

                trocarResponsavelMC2(UsuarioAtual.ToInt(),idGrupo.ToInt());
            }
        }
    }

    if ( UsuarioAtual.ToInt() > 0 )
    {
        atualizarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),true);
    }
    else
    {
        removeAtendimentoUsuarioAtual();
    }

    if ( strcmp(szSgFluxoAtendimento,"MC1")==0 )
    {
        atualizarPessoaUsuarioAbertura(UsuarioAtual.ToInt());
    }

    getAtendimentoGrupoAtualBko(); // BUSCA EM ATENDIMENTO.ATENDIMENTOGRUPOATUAL

    getAndamentoTransferencia();
    if (idAndamentoTrans > 0)
    {
        alterarAndamentoTransferencia();
    }

    inCriAtd = 0;
    alterarAtendimentoGrupoAtual();
    removeAtendimentoSuspeito();
    removeCancelamentoSolicitado();
    removeAtendimentoPausa();

    //if (idGrupoAtual != idGrupo.ToInt())
    //{
        inserirAtendimentoNivel();
    //}

    inserirAndamento();
    alterarAndamentoAtual();

    if (UsuarioAtual.ToInt() > 0)
    {
		inserirAndamentoTransferencia();
    }

    inserirAndamentoObservacao();
    inserirAndamentoMotivo();

    if ( inCri )
    {
        //getAtendimentoUsuarioCri(); // atendimento.atendimentocri
        //qtEnvioBko++;
        //UsuarioAtual = idUsuarioCri;
        //alterarAtendimentoCri();
        incAtendimentoUsuarioCriQtEnvioBKO();

        removeTratamentoCri();

        getAtendimentoGrupoBko();

        qtIntercambios++;
        if ( qtIntercambios == 1 )
        {
            inserirAtendimentoGrupoBko();
        }
        else
        {
            alterarAtendimentoGrupoBko();
        }
    }

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"ENCACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage(UsuarioAtual.ToInt()>0?"Usuário Disponível":"Encaminhamento Concluído","S"); 
    
    xml_g->closeTag();

}
