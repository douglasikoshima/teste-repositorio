/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:16 $
 **/


#include "../include/cWF_CERRAMEBKO.h"

DECLARE_TUXEDO_SERVICE(CERRAMEBKO);

void implCERRAMEBKO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implCERRAMEBKO::Execute()");
    TString retCode = "04I0000";
    TString retMesg = "Execucao de CERRAMEBKO Concluida";

    cWF_CERRAMEBKO rc(dnode, xml_g, getUser());

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
    ULOG_END("implCERRAMEBKO::Execute()");

}

//******************************************************************************************************************
// cWF_CERRAMEBKO - Component Implementation
//******************************************************************************************************************

cWF_CERRAMEBKO::cWF_CERRAMEBKO(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     if ( user ) 
       User = user; 
}

//------------------------------------------------------------------------------------------------------------------

void cWF_CERRAMEBKO::Executar() 
{

    cWF_Acao::Executar();

    getAtendimento();

    // if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato )
    // {
    //     SetMessage( "Processo é do tipo 'RETORNO POR GRUPO DE RETORNO'."
    //                 "\nParametrizações alteradas. Favor tentar encerrar o processo novamente.","M"
    //               ); 
    // 
    //     xml_g->closeTag();
    // 
    //     return;
    // }

    // urlDestino=1, é interpretado na página web que após a execução da ação o sistema
    // deverá retornar para a fila ou inbox do usuário e quando urlDestino=3 a página
    // web interpreta que o sistema deverá retornar para o detalhe do processo.
    // No caso de RETORNO POR GRUPO DE RETORNO, como o processo sai das mãos do usuário
    // responsável e vai para a fila do grupo de retorno, então urlDestino=1 e no caso
    // de RETORNO POR GRUPO BKO então urlDestino=3.
    urlDestino = TP_RET_COM_RET_GRP_RET == idTipoRetornoContato ? "1" :"3";

    Encerrar();

    // ==> SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR(User.ToInt(),idContato,"CERRAMEBKO");
    // <== SM324--DPR--DEZ/2006--Cassio

    // ==> OS 566 Julho/2007 - Marcelo
    carregarDadosFormulario(dnode);
    gravaAtendimentoFormularioDinamico(idAtendimento.ToLong());
    // <== OS 566 Julho/2007 - Marcelo


    if ( TP_RET_COM_RET_GRP_RET == idTipoRetornoContato )
    {
        SetMessage( "Encerramento Concluído para Grupo de Retorno", "S" );
    }
    else if ( TP_RET_COM_RET_GRP_CRI == idTipoRetornoContato )
    {
        SetMessage( "Encerramento Concluído Para Grupo de CRI", "S" );
    }
    else if ( strcmp(szSgRegraEncaminhamento,"MC")==0 )
    {
        SetMessage( "Encerramento Concluído Para 'Meu Cliente'", "S" );
    }
    else
    {
        SetMessage( "Encerramento Concluído Para Grupo de BKO", "S" );
    }

    xml_g->closeTag();
}

// ===========================================================================================
// As ações de encerramento para processos com RETORNO não estavam com diferenças funcionais 
// e a partir desta versão criei um método Encerrar() na classe de ACAO que é usada em comum
// pelos serviços de encerramento CERRAMEBKO e CERRAMERET.
// Julho, 2006 - Cassio
// ===========================================================================================
//
// void cWF_CERRAMEBKO::Executar() 
// {
//     cWF_Acao::Executar(); 
// 
//     ULOG("UsuarioAtual = [%d] ", UsuarioAtual.ToInt());
//         
//     inCriAtd = 0;
//     getAtendimento();
// 
//     if ( TP_RET_COM_RET_GRP_BKO != idTipoRetornoContato )
//     {
//         SetMessage( "\nProcesso não é do tipo 'RETORNO POR GRUPO DE BKO'.", "M" );
// 
//         xml_g->closeTag();
// 
//         return;
//     }
// 
//     getAtendimentoGrupoAtual();
// 
//     if ( preProcessarRelEncFech(ACAO_ENCERRAMENTO) == false ) // Incidência 2331 PréProd - Fev/2006
//     {
//         SetMessage( "Falha na atualização dos relatórios on-line", "N" );
// 
//         xml_g->closeTag();
// 
//         return;
//     }
// 
//     if (inCri)
//     {
//         getAtendimentoGrupoDevolucao();
//         if (idGrupo.ToInt() <= 0)
//         {
//             idGrupo = idGrupoAtual;
//             inCriAtd = 2;
// 
//             getAtendimentoGrupoBko();
//             if (qtIntercambios == 0)
//             {
//                 inAssociado = 1;
//                 inserirAtendimentoGrupoBko();
//                 alterarAtendimentoTipoRetorno();
//             }
//             else
//             {
//                 qtIntercambios = 0;
//                 alterarAtendimentoGrupoBko();
//             }
// 
//             alterarAtendimentoGrupoAtual();
//             inserirAtendimentoGrupoReceptor();
//         }
//         getTratamentoUsuarioCri();
//     }
//     else
//     {
//         getAtendimentoPessoa();
//         getAtendimentoLinha();
// 
//         if ( getGrupoRetorno() == false )
//         { // Obtém o grupo da fase de retorno para onde o processo deve ir
//             SetMessage( "Grupo de retorno não encontrado", "M" );
// 
//             xml_g->closeTag();
// 
//             return;
//         }
// 
//         removeAtendimentoGrupoReceptor(); // ATENDIMENTOGRUPODEVOLUCAO
//         inserirAtendimentoGrupoReceptor(idGrupoAtual); // ATENDIMENTOGRUPODEVOLUCAO
// 
//         alterarAtendimentoGrupoAtual(idGrupo.ToInt()); // envia para o grupo de retorno
//     }
// 
//     inserirAndamento(idGrupoAtual);
//     alterarAndamentoAtual();
// 
//     inserirAtendimentoBaixaHistorico();
//     if ( getBaixaMensagem() )
//     {
//         inserirAtendimentoBaixaMensagem();
//     }
// 
//     getAtendimentoBaixaAtual();
//     if (idBaixaAtendimento > 0)
//     {
//         alterarAtendimentoBaixaAtual();
//     }
//     else
//     {
//         inserirAtendimentoBaixaAtual();
//     }
// 
//     getAtendimentoUsuarioAtual();
//     if (idUsuarioAtendimento > 0)
//     {
//         if (inCriAtd == 2) // se o processo é CRI em poder de BKO então
//         { // o usuário de devolução será o BKO e não o CRI caso a devolução ocorra
//             removeAtendimentoUsuarioReceptor(); // ATENDIMENTOUSUARIODEVOLUCAO
//             inserirAtendimentoUsuarioReceptor(); // ATENDIMENTOUSUARIODEVOLUCAO
//         }
// 
//         UsuarioAtual = idUsuarioAtendimento;
//         alterarAtendimentoUsuarioAtual();
//     }
//     removeAtendimentoSuspeito();
//     removeCancelamentoSolicitado();
// 
//     idFase = TRATAMENTO;
//     inserirAtendimentoNivel(idGrupoAtual);
// 
//     idFaseAtendimento = RETORNO;
//     alterarAtendimento();
//     inserirAndamentoObservacao();
//     inserirFormulario();
// 
//     // incidência 3325 CRI - Processos EM RETORNO devem aparecer no inbox do CRI
//     // removeTratamentoCri();
// 
//     // se estiver fechando a partir de janela CRI e processo não estava nas mãos
//     // de um usuário CRI, envia mensagem ao BKO que estava com o processo sobre
//     // a ação de fechamento
//     if ( inCri && idUsuarioCri == 0 && idUsuarioAtendimento > 0 )
//     {
//         inserirAndamentoMensagem(idUsuarioAtendimento,User.ToInt()
//                                 ,INORIGEM_ANDAMENTOMENSAGEM_CRI);
//     }
// 
// #ifndef WIN32
//     getFormaRetornoAtendimento(); // envia mensagem de fechamento ao cliente
// #endif
// 
//     // Retorna processamento
//     SetMessage( "Encerramento Concluido", "S" ); 
// 
//     xml_g->closeTag();
// }
