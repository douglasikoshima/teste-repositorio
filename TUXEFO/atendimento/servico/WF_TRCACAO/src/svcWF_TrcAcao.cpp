
/*---------------------------------------------------------
 * Modulo..: Atendimento
 * Servico.: Troca Cri
 * Migracao: Script 800
 * Revisao.: Dec-09-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "../include/cWF_TrcAcaoPC.h"

//******************************************************************************************************************
// WFTRCACAO - Service Implementation
//******************************************************************************************************************

DECLARE_TUXEDO_SERVICE(WFTRCACAO);

void implWFTRCACAO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    TString retCode = "04I0000";
    TString retMesg = "Execucao de WFTRCACAO Concluida";

    cWF_TrocaCri rc(dnode, xml_g, getUser());

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
// cWF_TrocaCri - Component Implementation
//******************************************************************************************************************

cWF_TrocaCri::cWF_TrocaCri(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_TrocaCri::Executar() 
{
    cWF_Acao::Executar(); 

    getAtendimentoUsuarioCri(); // Atendimento.AtendimentoCri

    getAtendimentoGrupoAtualBko();

    if ( UsuarioAtual.ToInt() > 0 ) // ID do usuário destino (opcional)
    {
        getStatusUsuario();

        if ( idStatusUsuario != 1 )
        {
            // Atendente Nao Logado;
            SetMessage( "Usuário Indisponível para Recepcionar Processos", "N" ); 
            xml_g->closeTag();
            return;
        }
        else
        {
            // Se processo estiver sob cuidados de analista BKO (inCriAtd=0), deixa ele lá
            // mesmo com a troca de CRI.
            if ( inCriAtd == 1 )
            {
                getAtendimentoUsuarioAtual();

                if ( idUsuarioAtendimento > 0 )
                {
                    alterarAtendimentoUsuarioAtual(UsuarioAtual.ToInt(),true);

                    if ( idUsuarioCri && idUsuarioAtendimento != idUsuarioCri )
                    {
                        getAndamentoTransferencia();
                        if ( idAndamentoTrans > 0 )
                        {
                            alterarAndamentoTransferencia();
                        }
                    }
                }
            }
        }

        alterarAtendimentoCri(true);

        // A troca de CRI faz com que a contagem do prazo CRI seja
        // recalculado e por isso esta deve ser atualizada.
        atualizarDataPrazoCRI();

        alterarTratamentoCri();
        alterarTratamentoGrupoCri();
        alterarUsuarioDevolucaoCri();

    }
    else
    {
        // Se processo estiver sob cuidados de analista BKO (inCriAtd=0), deixa ele lá
        // mesmo com a troca de CRI.
        if ( inCriAtd == 1 )
        {
            // Se não recebeu ID de usuário, esta pedindo para trocar de inbox CRI
            // para a fila de processos.
            getAtendimentoUsuarioAtual();

            if ( idUsuarioAtendimento > 0 )
            {
                removeAtendimentoUsuarioAtual();
            }
        }

        removeAtendimentoCri();
        removeTratamentoCri();
        removeAtendimentoUsuarioReceptor();

        // Retorna status do processo para TRATAMENTO FILA
        if ( idAgrEstTPrAt.ToInt() ==  3 ) idAgrEstTPrFt =  2;
        if ( idAgrEstTPrAt.ToInt() ==  5 ) idAgrEstTPrFt =  2;
        if ( idAgrEstTPrAt.ToInt() == 16 ) idAgrEstTPrFt = 15;
        if ( idAgrEstTPrAt.ToInt() == 18 ) idAgrEstTPrFt = 15;
    }

    idGrupoAtual = idGrupo.ToInt();

    // Se processo estiver sob cuidados de analista BKO (inCriAtd=0), deixa ele lá
    // mesmo com a troca de CRI.
    if ( inCriAtd == 1 )
    {
        alterarAtendimentoGrupoAtual();
    }

    alterarGrupoCri();
    alterarGrupoDevolucaoCri();

    inserirAndamento();

    if ( UsuarioAtual.ToInt() != idUsuarioCri )
    {
        inserirAndamentoTransferencia();
    }
    alterarAndamentoAtual();
    inserirAndamentoObservacao();
    inserirAndamentoMotivo();
    inserirAtendimentoNivel();

    // Envia mensagem ao CRI que estiver "perdendo" o processo sobre o ocorrido.
    if ( idUsuarioCri )
    {
        inserirAndamentoMensagem();
    }

    // ==> SM324--DPR--DEZ/2006--Cassio
    getAtendimento();
    registrarAcaoDPR(User.ToInt(),idContato,"WFTRCACAO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // Retorna processamento
    SetMessage( "Troca de CRI Concluída", "S" ); 

    xml_g->closeTag();
}

