/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.17.6.1.8.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/12/05 13:13:12 $
 **/

#include "../include/cRegContato.h"

// Classes de apoio.
#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"

// Classes usadas no negócio.
#include "../../Usuario/include/stUsuario.h"
#include "../../Andamento/include/cWFAndamento.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoConta/include/cWFAtendimentoConta.h"
#include "../../AtendimentoContato/include/cWFAtendimentoContato.h"
#include "../../AtendimentoContatoComunic/include/cWFAtendimentoContatoComunic.h"
#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"
#include "../../AtendimentoFrmCampo/include/cWFAtendimentoFrmCampo.h"
#include "../../AtendimentoGerarXMLDPR/include/cWFAtendimentoGerarXMLDPR.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"
#include "../../AtendimentoObservacao/include/cWFAtendimentoObservacao.h"
#include "../../AtendimentoOrigem/include/cWFAtendimentoOrigem.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
#include "../../ChamadaAtendimento/include/cWFChamadaAtendimento.h"
#include "../../ENCCONTATO/include/cEncContato.h"
#include "../../ENCCONTATO/include/cEncContatoPC.h"
#include "../../FCHIMECONTATO/include/cFchImediatoContato.h"
#include "../../WF_ATDABREPROT/include/cWfAtdAbreProt.h"
#include "../../WF_ATDALTERPROT/include/cWfAtdAlterProt.h"

st_EncContatoPreValidacao preValidaEncContato;

// Funções de pré-processamento para relatórios
extern void proCAtualizarAtdPalitagem(int _idUFOperadora
                                     ,int _idContato
                                     ,short _idContatoFlag
                                     ,long _idPessoaUsuarioAbertura
                                     ,short _idPessoaUsuarioAberturaFlag
                                     ,int _idGrupoAbertura
                                     ,short _idGrupoAberturaFlag
                                     ,int _idUsuarioAlteracao
                                     ,short _idUsuarioAlteracaoFlag);

cRegContato::cRegContato(XMLGen*xml_g)
{
    saida = xml_g;

    rcpc = new cRegContatoPC(&dados,&dadosProtocolo);
}

cRegContato::~cRegContato()
{
    delete rcpc;
}

void cRegContato::EntradaValidacao(st_RegContato *dadosRCont)
{
    ULOG_START("cRegContato::EntradaValidacao()");

    if ( dadosRCont->naoValidarDados )
    {
        ULOGW("Chamador solicitou que entrada não seja validada!");
        ULOG_END("cRegContato::EntradaValidacao()");
        return;
    }

    //char cdConta[ 256 ];
    //char cdDigitoConta[ 256 ];
    //char dataFechamento[64];
    //char dataANATEL[64];
    //char dataCPrevio[64];
    //char peso[256]; // Peso de priorização do atendimento
    //double horasRC    = 0; // Prazo para o resposta ao cliente
    //double horas      = 0; // Prazo para o fechamento do atendimento
    //double horas2     = 0; // Prazo segmentado para o fechamento do atendimento
    //int idSequencia   = 0; // id da sequencia de abertura do atendimento
    //int idTipoRetorno = 0; // id do tipo de retorno ao solicitante do atendimento

    //dataFechamento[0] = dataANATEL[0] = dataCPrevio[0] = 0;

    // =========================================================================
    // Bloco de analise de valores obrigatórios no xml de entrada

    if ( -1 == dadosRCont->idContato )
    {
        SAFE_STRNCPY( erro.sCode, E_TAGIDCONTATO_NE );
        SAFE_STRNCPY( erro.mCode, M_TAGIDCONTATO_NE );
        throw &erro;
    }

    if ( -1 == dadosRCont->idSegmentacao )
    {
        SAFE_STRNCPY( erro.sCode, E_TAGIDSEGMENTACAO_NE );
        SAFE_STRNCPY( erro.mCode, M_TAGIDSEGMENTACAO_NE );
        throw &erro;
    }

    if ( -1 == dadosRCont->idProcedencia )
    {
        SAFE_STRNCPY( erro.sCode, E_TAGIDPROCEDENCIA_NE );
        SAFE_STRNCPY( erro.mCode, M_TAGIDPROCEDENCIA_NE );
        throw &erro;
    }

    if ( dadosRCont->nrTelefone[0]==0 )
    {
        SAFE_STRNCPY( erro.sCode, E_TAGNRTELCONTATO_NE );
        SAFE_STRNCPY( erro.mCode, M_TAGNRTELCONTATO_NE );
        throw &erro;
    }

    // Extrai o código de área do número do telefone
    //char ddd[3];
    //sprintf(ddd,"%.*s",sizeof(ddd)-1
    //    ,dadosRCont->nrTelefone[0]=='0' ? dadosRCont->nrTelefone+1 : dadosRCont->nrTelefone);

    //dadosRCont->cdAreaRegistro = atoi(ddd);

    // =========================================================================
    dadosRCont->gravarLinhaTelefonica = true;
    //pValida.idTipoLinha = dadosRCont->idTipoLinha;

    // Verifica Linha Telefonica
    if ( dadosRCont->idLinhaTelefonica == 0 )
    {
        if ( dadosRCont->idLinhaAtendimento > 0 )
        {
            dadosRCont->idLinhaTelefonica = dadosRCont->idLinhaAtendimento;
        }
        else
        {
            // Não vai inserir registro em ATENDIMENTO.ATENDIMENTOLINHA
            dadosRCont->gravarLinhaTelefonica = false;
        }
    }

    if ( dadosRCont->gravarLinhaTelefonica )
    {
        rcpc->obtemDadosLinhaTelefonica( dadosRCont->idPessoaDeParaCliente
                                       , dadosRCont->idLinhaTelefonica
                                       , &dadosRCont->idPessoaLinhaHistorico
                                       , &dadosRCont->idEstadoLinha
                                       , &dadosRCont->idTipoLinha );

        if ( dadosRCont->idPessoaLinhaHistorico < 1 
           || dadosRCont->idEstadoLinha < 1
           || dadosRCont->idTipoLinha < 1 )
        {
            // Não vai inserir registro em ATENDIMENTO.ATENDIMENTOLINHA
            dadosRCont->gravarLinhaTelefonica = false;
            dadosRCont->idTipoLinha = 0;
            dadosRCont->idEstadoLinha = 0;
        }
    }

    // Caso a tela inicial não envie o id do responsável pela abertura é atribuido valor 3.
    if (dadosRCont->idTipoRelacionamento == TIPO_RELA_FI_FALSO_INSERT)
    {
        dadosRCont->idTipoRelacionamento = TIPO_RELA_R_CONS_RELACIONAMENTO;
    }

    // Verifica se foi o usuário quem fez a abertura do atendimento.
    if (dadosRCont->idTipoRelacionamento == TIPO_RELA_U_USUARIO)
    {
        if (dadosRCont->idPessoaDeParaCliente == dadosRCont->idPessoaDeParaUsuario)
        {
            // Se o usuário também for cliente, grava como sendo solicitação do cliente.
            m_stDadosPessoa.idPessoaDePara = dadosRCont->idPessoaDeParaCliente;
            if ( rcpc->ValidaPessoaDePara(m_stDadosPessoa.idPessoaDePara) == false )
            {
                SAFE_STRNCPY( erro.sCode, E_CDPESSOADEPARA_NE );
                SAFE_STRNCPY( erro.mCode, M_CDPESSOADEPARA_NE );
                throw &erro;
            }
        }
        else
        {
            // Quando o solicitante do atendimento é o usuário é gravado um registro
            // apontado para o cliente da linha para que possa ser exibido o registro
            // na consulta do relacionamento tanto do cliente quanto do usuário.
            m_stDadosPessoa.idPessoaDePara = dadosRCont->idPessoaDeParaUsuario;
            if ( rcpc->ValidaPessoaDePara(m_stDadosPessoa.idPessoaDePara) == false )
            {
                SAFE_STRNCPY( erro.sCode, E_CDPESSOADEPARA_NE );
                SAFE_STRNCPY( erro.mCode, M_CDPESSOADEPARA_NE );
                throw &erro;
            }

            m_stDadosPessoa.idPessoaDePara = dadosRCont->idPessoaDeParaCliente;
            if ( rcpc->ValidaPessoaDePara(m_stDadosPessoa.idPessoaDePara) == false )
            {
                SAFE_STRNCPY( erro.sCode, E_CDPESSOADEPARA_NE );
                SAFE_STRNCPY( erro.mCode, M_CDPESSOADEPARA_NE );
                throw &erro;
            }
        }
    }
    else
    {
        // Solicitante assim como foi enviado.
        m_stDadosPessoa.idPessoaDePara = dadosRCont->idPessoaDeParaCliente;
        if ( rcpc->ValidaPessoaDePara(m_stDadosPessoa.idPessoaDePara) == false )
        {
            SAFE_STRNCPY( erro.sCode, E_CDPESSOADEPARA_NE );
            SAFE_STRNCPY( erro.mCode, M_CDPESSOADEPARA_NE );
            throw &erro;
        }
    }

    // Verifica a conta
    if ( dadosRCont->idConta > 0 )
    {
        rcpc->obtemDadosConta( &dadosRCont->idConta,dadosRCont->cdConta,dadosRCont->cdDigitoConta );

        if ( dadosRCont->cdConta[0]==0 )
        {
            SAFE_STRNCPY( erro.sCode, E_CONTA_NE );
            SAFE_STRNCPY( erro.mCode, M_CONTA_NE );
            throw &erro;
        }

        //SAFE_STRNCPY(dadosRCont->cdConta,cdConta)
        //SAFE_STRNCPY(dadosRCont->cdDigitoConta,cdDigitoConta)
    }

    // Prazo de atendimento
    //rcpc->obtemPrazoAtendimentoContato( &dadosRCont->horas,&dadosRCont->horasRC,dadosRCont->idContato );

    rcpc->obterDadosContatoFolha(dadosRCont);

    if ( dadosRCont->horas < 1 )
    {
        ULOGE( "Não foi possivel obter prazo de atendimento." );

        if ( dadosRCont->tpOperacao != PALITAGEM )
        { // Se estiver apenas palitando o prazo de atendimento não é necessário
            ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
            SAFE_STRNCPY( erro.sCode,E_PZAIN_NE );
            SAFE_STRNCPY( erro.mCode,M_PZAIN_NE );
            throw &erro;
        }
        else
        {
            dadosRCont->horas = 0.0;
        }
    }

    if ( dadosRCont->horasRC < 1 )
    { // Se o prazo de contato prévio não for encontrado apenas loga, pois não é obrigatório
        ULOGW( "Prazo de contato prévio não configurado para o contato %d",dadosRCont->idContato );
    }

    // Peso
    bool retornoPeso=rcpc->obtemPesoAtendimento(dadosRCont->peso
                                               ,dadosRCont->idContato
                                               ,dadosRCont->idSegmentacao
                                               ,dadosRCont->idProcedencia
                                               ,dadosRCont->idTipoCarteira
                                               ,dadosRCont->sgTipoPortabilidade
                                               ,dadosRCont->idContatoFolhaCampo /* CUIDADO: na realidade aqui é o idFormulario */
                                               ,dadosRCont->isVolE);

    if ( false == retornoPeso && dadosRCont->tpOperacao != PALITAGEM )
    { // O peso não é importante para palitagens, caso contrário lança exceção.
        ULOGE( "Não foi encontrado o peso de priorização do atendimento." );
        ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
        SAFE_STRNCPY( erro.sCode,E_PSATD_NE );
        SAFE_STRNCPY( erro.mCode,M_PSATD_NE );
        throw &erro;
    }
    //strcpy(pValida.peso,peso);

    // Prazo segmentado
    rcpc->obtemPrazoAtendimentoSegmentado(&dadosRCont->horas2
                                         ,dadosRCont->idContato
                                         ,dadosRCont->idSegmentacao
                                         ,dadosRCont->idProcedencia);

    if ( dadosRCont->horas2 > 0 )
    {
        dadosRCont->horas = dadosRCont->horas2;
    }

    obtemDataFechamento( dadosRCont->horas,dadosRCont->dataFechamento,24 );

    if ( dadosRCont->horasRC > 0 )
    {
        obtemDataFechamento( dadosRCont->horasRC,dadosRCont->dataCPrevio,0 );
    }

    obtemDataFechamentoAnatel( dadosRCont->dataANATEL );

    if ( dadosRCont->dataANATEL[0] == 0 )
    {
        ULOGE( "Data ANATEL não pode ser calculada por provável falta de parâmetros." );
        ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
        SAFE_STRNCPY( erro.sCode,E_DIAUT_NE );
        SAFE_STRNCPY( erro.mCode,M_DIAUT_NE );
        throw &erro;
    }

    //pValida.horas = horas;
    //pValida.horas2 = horas2;
    //pValida.horasRC = horasRC;

    //SAFE_STRNCPY(dadosRCont->dataFechamento,dataFechamento);
    //SAFE_STRNCPY(dadosRCont->dataCPrevio,dataCPrevio);
    //SAFE_STRNCPY(dadosRCont->dataANATEL,dataANATEL);

    //rcpc->obtemAgrupamentoEstadoTpProc(dadosRCont->idContato,&dadosRCont->idAgrupamentoEstadoTpProc );

    st_AgrupamentoEstadoTpProc stAgrupamento;
    st_vlAgrupamentoEstadoTpProc vlAgrupamento;
    memset( &stAgrupamento, 0,sizeof(stAgrupamento) );
    memset( &vlAgrupamento,-1,sizeof(vlAgrupamento) );

    if ( dadosRCont->tpOperacao == PALITAGEM )
    {
        stAgrupamento.idAtividade = FECHAMENTO_IMEDIATO_FI;
        stAgrupamento.idAgrupamentoEstadoTpProc = dadosRCont->idAgrupamentoEstadoTpProc;

        cWFAgrupamentoEstadoTpProc pa( &stAgrupamento, &vlAgrupamento );
        dadosRCont->idAgrupamentoEstadoTpProcFt = pa.proximoAgrupamento();
        if ( dadosRCont->idAgrupamentoEstadoTpProcFt < 0 )
        {
            ULOGE( "Proximo agrupamento inexistente" );
            ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
            SAFE_STRNCPY( erro.sCode, E_FCHPRXESTADO_NE );
            SAFE_STRNCPY( erro.mCode, M_FCHPRXESTADO_NE );
            throw &erro;
        }

        st_FchImediatoContato fchDados;
        //fchDados.idBaixa = dadosRCont->idBaixa;
        //fchDados.idBaixaMensagem = dadosRCont->idMensagemBaixa;
        cFchImediatoContatoPC fic( &fchDados );
        //pValida.idBaixaMensagem = 
        dadosRCont->idBaixaMensagem = 
            fic.obtemBaixaMensagem( &dadosRCont->idBaixa, &dadosRCont->idMensagemBaixa );
        //
        // Descobrimos que por um erro de código esta validação não estava sendo feita
        // em produção desde a sua implantação.
        // Decidimos manter desta maneira para não causar maiores problemas.
        // Dez/2006 - Cassio.
        //
        // if ( pValida.idBaixaMensagem < 0 )
        // {
        //     ULOGE( "idBaixaMensagem invalido" );
        //     ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
        //     SAFE_STRNCPY( erro.sCode, E_IDMSGBAIXA_NE );
        //     SAFE_STRNCPY( erro.mCode, M_IDMSGBAIXA_NE );
        //     throw &erro;
        // }
    }
    else
    {
        int idUFOperadoraNaoCliente = -1;
        st_VariaveisUsuario variaveisUsuario;

        rcpc->obtemIdTipoPessoa(&dadosRCont->idTipoPessoa,dadosRCont->inTipoPessoa);

        variaveisUsuario.idFase               = TRATAMENTO;
        variaveisUsuario.idTipoCarteira       = dadosRCont->idTipoCarteira;
        variaveisUsuario.idSegmentacao        = dadosRCont->idSegmentacao;
        variaveisUsuario.idProcedencia        = dadosRCont->idProcedencia;
        variaveisUsuario.idContato            = dadosRCont->idContato;
        variaveisUsuario.idGrupoAbertura      = dadosRCont->idGrupoAbertura;
        variaveisUsuario.idTipoPessoa         = dadosRCont->idTipoPessoa;
        variaveisUsuario.idTipoLinha          = dadosRCont->idTipoLinha;
        variaveisUsuario.idTipoRelacionamento = dadosRCont->idTipoRelacionamento;
        variaveisUsuario.idCanal              = dadosRCont->idCanal;

        // Quando for 'NÃO-CLIENTE' utilizamos idUFOperadora definido pelo idUF em customer.pessoa
        // esta alteração foi realizada para atender a incidência WR 3346
        if( rcpc->proCOperadoraNaoCliente(dadosRCont->idPessoaDeParaCliente,&idUFOperadoraNaoCliente))
        {
            dadosRCont->idUFOperadora = 
                    variaveisUsuario.idUFOperadora = 
                        idUFOperadoraNaoCliente;
            ULOG("EntradaValidacao:Não Cliente, idUFOperadora=%d",variaveisUsuario.idUFOperadora );
        }
        else
        {
            variaveisUsuario.idUFOperadora = dadosRCont->idUFOperadora;
            ULOG("EntradaValidacao:Cliente, idUFOperadora=%d",variaveisUsuario.idUFOperadora );
        }

        int idGrupo; // grupo de tratamento a ser determinado

        if ( dadosRCont->sgTipoPortabilidade[0] )
        {
            // Número do protocolo é obrigatório para processos de portout
            if ( 0==dadosRCont->nrProtocoloPortabilidade || 0==*dadosRCont->nrProtocoloPortabilidade )
            {
                ULOGE( M_NRPROTOPOUT_NE );
                ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
                SAFE_STRNCPY( erro.sCode,E_NRPROTOPOUT_NE );
                SAFE_STRNCPY( erro.mCode,M_NRPROTOPOUT_NE );
                throw &erro;
            }

            // Data de janela é obrigatória para processos de portout
            //if ( 0==dadosRCont->dtJanelaPortout || 0==*dadosRCont->dtJanelaPortout )
            //{
            //    ULOGE( M_JANELAPOUT_NE );
            //    ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
            //    SAFE_STRNCPY( erro.sCode,E_JANELAPOUT_NE );
            //    SAFE_STRNCPY( erro.mCode,M_JANELAPOUT_NE );
            //    throw &erro;
            //}

            // Operadora solicitante é obrigatória para processos de portout
            //if ( 0==dadosRCont->sgOperadoraSolicitante || 0==*dadosRCont->sgOperadoraSolicitante )
            //{
            //    ULOGE( M_OPERSOLICPOUT_NE );
            //    ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
            //    SAFE_STRNCPY( erro.sCode,E_OPERSOLICPOUT_NE );
            //    SAFE_STRNCPY( erro.mCode,M_OPERSOLICPOUT_NE );
            //    throw &erro;
            //}
        }

        if ( strcmp(dadosRCont->sgTipoPortabilidade,"PORTOUT")==0 )
        {
            // Busca o grupo de tratamento do processo
            //idGrupo = rcpc->obterGrupoTratamentoPortout(dadosRCont->sgTipoPortabilidade,dadosRCont->dsAcaoPortabilidade);
            // Grupo de tratamento é o mesmo que o de abertura para processos de PORTOUT
            idGrupo = dadosRCont->idGrupoAbertura;
            preValidaEncContato.inAssociado = 0;
            dadosRCont->idGrupoTratamento = idGrupo;
            preValidaEncContato.idProxGrupo = dadosRCont->idGrupoTratamento;
            preValidaEncContato.idSequencia = TRATAMENTO;

            ULOG( "Grupo de tratamento para '%s_%s'=%d"
                    ,dadosRCont->sgTipoPortabilidade
                    ,dadosRCont->dsAcaoPortabilidade
                    ,dadosRCont->idGrupoTratamento);

            if ( -1 == idGrupo )
            {
                ULOGE( M_GRPTRATPOUT_NE );
                ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
                SAFE_STRNCPY( erro.sCode,E_GRPTRATPOUT_NE );
                SAFE_STRNCPY( erro.mCode,M_GRPTRATPOUT_NE );
                throw &erro;
            }
        }
        else
        { // se não é processo de portout, trata como bko.
            if ( proCPesquisaGrupoFaseVariables( &variaveisUsuario, idGrupo ) == false )
            {
                ULOG( "proCPesquisaGrupoFaseVariables nao encontrou variaveis associadas" );
                ULOG( "idFase = %d",variaveisUsuario.idFase );
                ULOG( "idTipoCarteira = %d",variaveisUsuario.idTipoCarteira );
                ULOG( "idSegmentacao = %d",variaveisUsuario.idSegmentacao );
                ULOG( "idProcedencia = %d",variaveisUsuario.idProcedencia );
                ULOG( "idContato = %d",variaveisUsuario.idContato );
                ULOG( "idGrupoAbertura = %d",variaveisUsuario.idGrupoAbertura );
                ULOG( "idTipoPessoa = %d",variaveisUsuario.idTipoPessoa );
                ULOG( "idTipoLinha = %d",variaveisUsuario.idTipoLinha );
                ULOG( "idTipoRelacionamento = %d",variaveisUsuario.idTipoRelacionamento );
                ULOG( "idCanal = %d",variaveisUsuario.idCanal );
                ULOG( "idUFOperadora = %d",variaveisUsuario.idUFOperadora );

                preValidaEncContato.inAssociado = 1;

                proCPesquisaGrupoFase(&variaveisUsuario, idGrupo );
            }

            if ( -1 == idGrupo )
            {
                ULOGE( M_PREVLDPXGRU_NE );
                ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
                SAFE_STRNCPY( erro.sCode,E_PREVLDPXGRU_NE );
                SAFE_STRNCPY( erro.mCode,M_PREVLDPXGRU_NE );
                throw &erro;
            }

            dadosRCont->idGrupoTratamento = idGrupo;

            cEncContatoPC ec;
            preValidaEncContato.idSequencia =   
                    ec.obtemSequencia(dadosRCont->idGrupoTratamento,dadosRCont->idContato);
            if ( preValidaEncContato.idSequencia == -1 )
            {
                ULOGE( "cRegContato::EntradaValidacao():"M_PREVLDSQTRA_NE );
                ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
                SAFE_STRNCPY( erro.sCode,E_PREVLDSQTRA_NE );
                SAFE_STRNCPY( erro.mCode,M_PREVLDSQTRA_NE );
                throw &erro;
            }

            preValidaEncContato.idProxGrupo = dadosRCont->idGrupoTratamento;
        }

        stAgrupamento.idAtividade = ENCAMINHAR_E;
        vlAgrupamento.idAtividade = 1;

        stAgrupamento.idAgrupamentoEstadoTpProc = dadosRCont->idAgrupamentoEstadoTpProc;
        vlAgrupamento.idAgrupamentoEstadoTpProc = 1;

        cWFAgrupamentoEstadoTpProc pa( &stAgrupamento, &vlAgrupamento );
        dadosRCont->idAgrupamentoEstadoTpProcFt = pa.proximoAgrupamento();
        if ( dadosRCont->idAgrupamentoEstadoTpProcFt < 0 )
        {
            ULOGE( "cRegContato::EntradaValidacao():Proximo agrupamento inexistente" );
            ULOG_END( "*** SAIDA ANORMAL -> cRegContato::EntradaValidacao()" );
            SAFE_STRNCPY( erro.sCode,E_ENCPRXESTADO_NE );
            SAFE_STRNCPY( erro.mCode,M_ENCPRXESTADO_NE );
            throw &erro;
        }
        preValidaEncContato.proximoAgrupamento = dadosRCont->idAgrupamentoEstadoTpProcFt;
    }

    ULOG( "dtPrazoFinalInterno [%s]",dadosRCont->dataFechamento );
    ULOG( "dtPrazoFinalAnatel  [%s]",dadosRCont->dataANATEL );
    ULOG( "dtPrazoFinalCPrevio [%s]",dadosRCont->dataCPrevio );
    ULOG( "cdConta [%s]",dadosRCont->cdConta );
    ULOG( "cdDigitoConta [%s]",dadosRCont->cdDigitoConta );
    ULOG( "gravarLinhaTelefonica [%d]",dadosRCont->gravarLinhaTelefonica );
    ULOG( "idGrupoTratamento [%d]",dadosRCont->idGrupoTratamento );
    ULOG( "idPessoaLinhaHistorico [%ld]",dadosRCont->idPessoaLinhaHistorico );
    ULOG( "idEstadoLinha [%d]",dadosRCont->idEstadoLinha );
    ULOG( "idTipoLinha [%d]",dadosRCont->idTipoLinha );
    ULOG( "idTipoPessoa [%d]",dadosRCont->idTipoPessoa );

    ULOG_END("cRegContato::EntradaValidacao()");
}

/**
    Registra todos os dados do atendimento.
*/
void cRegContato::registra()
{
    ULOG_START("cRegContato::registra()");
	
	char idLinhaTelefonicaPRM[256];
	
	memset( idLinhaTelefonicaPRM, 0x0, sizeof(idLinhaTelefonicaPRM) );

    //rcpc->dataAtual(dataAtual,dataAtualSMS);
    rcpc->dataAtual(dados.dataAtual/*,dataAtualSMS*/);
    //
    // Determina se abre ou não um novo protocolo
    //
    cWfAtdAbreProt cwfatdabreprot;

    char idPessoaDeParaProtocolo[39] = { 0 };
    char idLinhaTelefonicaProtocolo[39] = { 0 };

    if ( strcmp(dadosProtocolo.idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA_CLIENTE) )
    {
        sprintf(idPessoaDeParaProtocolo,"%d",dados.idPessoaDeParaUsuario);
    }
    else
    {
        sprintf(idPessoaDeParaProtocolo,"%d",dados.idPessoaDeParaCliente);
    }

    sprintf(idLinhaTelefonicaProtocolo,"%d",dados.idLinhaTelefonica);

    cwfatdabreprot.setIdAtendimentoProtocolo(dados.idAtendimentoProtocolo);
    cwfatdabreprot.setCdAreaRegistro(dados.cdAreaRegistroSZ);
    cwfatdabreprot.setNrTelefone(&dados.nrTelefone[strlen(dados.cdAreaRegistroSZ)]);
    cwfatdabreprot.setCdAreaRegistroSMS(dados.dddSMSProtocolo);
    cwfatdabreprot.setNrTelefoneSMS(dados.nrLinhaSMSProtocolo);
    cwfatdabreprot.setIdSistemaOrigem(dados.idSistemaOrigemProtocolo);
    cwfatdabreprot.setIdPessoaDePara(idPessoaDeParaProtocolo);
    cwfatdabreprot.setCdConta(dados.cdConta);
    cwfatdabreprot.setIdLinhaTelefonica(idLinhaTelefonicaProtocolo);
    cwfatdabreprot.setIdTipoAberturaProtocolo(dados.idTipoAberturaProtocolo);
    cwfatdabreprot.setIdUsuarioAlteracao(dados.idUsuarioBKOSZ);
    cwfatdabreprot.setForceUsoProtocoloNaoAberto(dados.forceUsoProtocoloNaoAberto);

    ULOG(" idAtendimentoProtocolo=%s",cwfatdabreprot.getIdAtendimentoProtocolo());
    ULOG("         cdAreaRegistro=%s",cwfatdabreprot.getCdAreaRegistro());
    ULOG("             nrTelefone=%s",cwfatdabreprot.getNrTelefone());
    ULOG("        idSistemaOrigem=%s",cwfatdabreprot.getIdSistemaOrigem());
    ULOG("         idPessoaDePara=%s",cwfatdabreprot.getIdPessoaDePara());
    ULOG("                cdConta=%s",cwfatdabreprot.getCdConta());
    ULOG("             dtAbertura=%s",cwfatdabreprot.getDtAbertura());
    ULOG("         dtEncerramento=%s",cwfatdabreprot.getDtEncerramento());
    ULOG("       qtProcessoAberto=%s",cwfatdabreprot.getQtProcessoAberto());
    ULOG("     qtProcessoPendente=%s",cwfatdabreprot.getQtProcessoPendente());
    ULOG("      idLinhaTelefonica=%s",cwfatdabreprot.getIdLinhaTelefonica());
    ULOG("      cdAreaRegistroSMS=%s",cwfatdabreprot.getCdAreaRegistroSMS());
    ULOG("          nrTelefoneSMS=%s",cwfatdabreprot.getNrTelefoneSMS());
    ULOG("             dtEnvioSMS=%s",cwfatdabreprot.getDtEnvioSMS());
    ULOG("              dsErroSMS=%s",cwfatdabreprot.getDsErroSMS());
    ULOG("idTipoAberturaProtocolo=%s",cwfatdabreprot.getIdTipoAberturaProtocolo());

    ULOG( "regcontaro para palitagem" );
    
    strcpy( idLinhaTelefonicaPRM, cwfatdabreprot.getIdLinhaTelefonica() );
    ULOG( ">>> idLinhaTelefonicaPRM [%s]", idLinhaTelefonicaPRM );
	if ( idLinhaTelefonicaPRM[0] == 0x0 )
	{
		if ( cwfatdabreprot.AbrirProtocoloCondicional(dados.idAtendimentoProtocolo) )
		{ // se abriu protocolo, copia o novo número
			SAFE_STRNCPY(dados.idAtendimentoProtocolo,cwfatdabreprot.getIdAtendimentoProtocolo());
			ULOG("idAtendimentoProtocolo gerado=%s",dados.idAtendimentoProtocolo);
		}
	}
	else  // Aplicacao jah passou idLinhaTelefonica
	{
		if ( cwfatdabreprot.AbrirProtocoloCondicional(dados.idAtendimentoProtocolo, idLinhaTelefonicaPRM) )
		{ // se abriu protocolo, copia o novo número
			SAFE_STRNCPY(dados.idAtendimentoProtocolo,cwfatdabreprot.getIdAtendimentoProtocolo());
			ULOG("idAtendimentoProtocolo gerado=%s",dados.idAtendimentoProtocolo);
		}
	}
    //
    // Executa a gravação dos registros referentes a abertura do atendimento.
    //
    
    ULOG( "a." );
    
    obterConsultorRelacionamento();
    ULOG( "b." );
    gravaAtendimento();
    ULOG( "c." );

    gravaAtendimentoConta();
    ULOG( "d." );
    gravaAtendimentoOrigem();
    ULOG( "e." );
    gravaAtendimentoContato();
    ULOG( "f." );
    gravaAtendimentoContatoComunic();
    ULOG( "g." );
    gravaAtendimentoPessoa();
    ULOG( "h." );
    gravaAtendimentoFormularioDinamico();
    ULOG( "i." );
    gravaAtendimentoLinhas();
    ULOG( "j." );
    //gravaSmsProtocolo();
    gravaLinhaTelefonicaAtendimento();
    ULOG( "k." );
    gravaChamadaAtendimento();
    ULOG( "l." );

    gravaAtendimentoPriorizacao(&m_stDadosAtendimento,&m_vlDadosAtendimento
                               ,&m_stDadosPessoa,&m_vlDadosPessoa
                               //,&m_stDadosLinha,&m_vlDadosLinha
                               ,dados.idTipoLinha );

    ULOG( "m." );
    gravaAndamento();
    ULOG( "n." );
    gravaAndamentoObservacao(); // A chave que retorna é o mesmo id do idAtendimento.

    ULOG( "o." );
    gravaPessoaPortabilidadeHist();

    ULOG( "p." );
    // Alimenta base de dados de pré-processamento do relatório de palitagem
    atualizarPalitagem();
    ULOG( "q." );

    if ( dados.idTerminal > 0 )
    {
        rcpc->registraTerminalVOL(&dados.idAtendimento, &dados.idTerminal);
    }

    // Faz a chamada das ações referente ao caminho que o atendimento tomará.
    if (dados.tpOperacao == PALITAGEM) // Fechamento imediato.
    {
        ULOG("Registra Contato - Fechamento Imediato.......");

        st_FchImediatoContato fechamento;
        memset( &fechamento, 0, sizeof(fechamento) );

        fechamento.idAtendimento            = dados.idAtendimento;
        fechamento.idAtividade              = FECHAMENTO_IMEDIATO_FI;
        fechamento.idAgrupamentoEstadoTpProc= dados.idAgrupamentoEstadoTpProc;
        fechamento.idGrupoAbertura          = dados.idGrupoAbertura;
        fechamento.observacaoFechamento     = dados.observacaoFechamento;
        fechamento.idBaixa                  = dados.idBaixa;
        fechamento.idBaixaMensagem          = dados.idBaixaMensagem;
        fechamento.idUsuarioBKO             = dados.idUsuarioBKO;

        cFchImediatoContato fi(&fechamento,&xmlDpr);
        fi.proximoAgrupamento = dados.idAgrupamentoEstadoTpProcFt;

        fi.registra();
    }
    else
    {
        ULOG("Registra Contato - Encaminhamento.......");

        st_EncContato encaminha;
        memset( &encaminha, 0, sizeof(encaminha) );

        encaminha.idAtendimento             = dados.idAtendimento;
        encaminha.idAtendimentoOrigem       = dados.idAtendimentoOrigem;
        encaminha.idTipoReaberturaProcesso  = dados.idTipoReaberturaProcesso;
        encaminha.idAtividade               = ENCAMINHAR_E;
        encaminha.idCanal                   = dados.idCanal;
        encaminha.idContato                 = dados.idContato;
        encaminha.idPessoaDeParaCliente     = dados.idPessoaDeParaCliente;
        encaminha.idFormulario              = dados.idContatoFolhaCampo;
        encaminha.idFase                    = TRATAMENTO;
        encaminha.idGrupoAbertura           = dados.idGrupoAbertura;
        encaminha.idProcedencia             = dados.idProcedencia;
        encaminha.idSegmentacao             = dados.idSegmentacao;
        encaminha.idTipoCarteira            = dados.idTipoCarteira;
        encaminha.idUFOperadora             = dados.idUFOperadora;
        encaminha.idTipoLinha               = dados.idTipoLinha;
        encaminha.idTipoPessoa              = dados.idTipoPessoa;
        encaminha.idPessoaLinhaHistorico    = dados.idPessoaLinhaHistorico;
        encaminha.idTipoRelacionamento      = dados.idTipoRelacionamento;
        encaminha.idUsuarioBKO              = dados.idUsuarioBKO;
        encaminha.idAgrupamentoEstadoTpProc = dados.idAgrupamentoEstadoTpProc;
        encaminha.nrTelefone                = dados.nrTelefone;
        encaminha.dtAbertura                = dados.dataAtual;
        encaminha.idGrupo                   = dados.idGrupoTratamento;
        encaminha.isPortout                 = strcmp(dados.sgTipoPortabilidade,PROCESSO_PORTOUT)==0?true:false;
        encaminha.sgRegraEncaminhamento     = dados.sgRegraEncaminhamento;
        encaminha.sgFluxoAtendimento        = dados.sgFluxoAtendimento;
        encaminha.isVolE                    = dados.isVolE;
        encaminha.inConsultor               = dados.inConsultor;
        encaminha.nrDocumento               = dados.nrDocumento;

        st_CRI pCRI;  // Informações para CRI
        pCRI.idContato = dados.idContato;
        pCRI.idTipoLinha = dados.idTipoLinha;
        pCRI.idUFOperadora = dados.idUFOperadora;
        pCRI.idTipoRelacionamento = dados.idTipoRelacionamento;
        pCRI.idTipoPessoa = dados.idTipoPessoa;
        pCRI.idSegmentacao = dados.idSegmentacao;
        pCRI.idTipoCarteira = dados.idTipoCarteira;
        pCRI.idCanal = dados.idCanal;
        pCRI.idProcedencia = dados.idProcedencia;
        pCRI.idGrupoAbertura = dados.idGrupoAbertura;
        pCRI.idTipoReabertura = dados.idTipoReaberturaProcesso;
        // ==> Incidência 3288
        // A partir desta versão a classificação de tipo de abertura
        // é a mesma tanto para CRI como para BKO.
        // pCRI.atendimentoPorConta = dados.atendimentoPorConta;
        // <== Incidência 3288

        cEncContato ec( &encaminha, &pCRI, &preValidaEncContato, &xmlDpr );

        ec.registra();
    }

    // SM324--DPR--DEZ/2006--Cassio
    registrarAcaoDPR();

    // Altera info na tabela de SMS
    rcpc->alterarFilaSmsProtocolo(&dados);

    ULOG("         idAtendimento=%lu",dados.idAtendimento);
    ULOG("idAtendimentoProtocolo=%s",dados.idAtendimentoProtocolo);
    ULOG("           idAndamento=%ld",dados.idAndamento);

    //if ( dados.isVolE )
    //{ // Vol-E precisa do numero do atendimento (sem tempo pra fazer melhor)
    //    sprintf(dados.idAtendimentoProtocolo,"%d",dados.idAtendimento);
    //}

    ULOG_END("cRegContato::registra()");
}

/**
    Associa linhas ao protocolo - OS661 - SMP
*/
void cRegContato::gravaAtendimentoLinhas()
{
    ULOG_START("cRegContato::gravaAtendimentoLinhas()");

    rcpc->gravarAtendimentoLinhas(dados.idAtendimento,&dados.atendimentoLinhas);

    ULOG_END("cRegContato::gravaAtendimentoLinhas()");
}

void cRegContato::gravaPessoaPortabilidadeHist()
{
    ULOG_START("cRegContato::gravaPessoaPortabilidadeHist()");

    if ( dados.sgTipoPortabilidade[0] )
    {
        rcpc->gravaPessoaPortabilidadeHist(dados.idUsuarioBKO
                                          ,dados.idPessoaDeParaCliente
                                          ,dados.idTipoLinha
                                          ,dados.cdAreaRegistro
                                          ,dados.nrTelefone
                                          ,dados.sgTipoPortabilidade
                                          ,dados.dsAcaoPortabilidade
                                          ,dados.observacao
                                          ,dados.sgOperadoraSolicitante
                                          ,dados.nrProtocoloPortabilidade
                                          ,dados.dtJanelaPortout
                                          );
    }
    else
    {
        ULOG("Não é processo de portabilidade");
    }

    ULOG_END("cRegContato::gravaPessoaPortabilidadeHist()");
}

/**
    Enfileira mensagem SMS - OS661 - SMP
*/
// void cRegContato::gravaSmsProtocolo()
// {
//     ULOG_START("cRegContato::gravaSmsProtocolo()");
// 
//     if ( dados.naoEnvProtocoloSMS == false )
//     {
//         rcpc->gravarSmsProtocolo(dados.idAtendimento
//                                 ,dados.idContato
//                                 ,m_stDadosAtendimento.idAtendimentoProtocolo
//                                 ,dados.msgSMS
//                                 ,dados.dataAtualSMS);
//     }
// 
//     ULOG_END("cRegContato::gravaSmsProtocolo()");
// }

/**
    Registra os dados de atendimento.
*/
void cRegContato::gravaAtendimento()
{
    ULOG_START("cRegContato::gravaAtendimento()");

    int idTipoRetorno = 0; // id do tipo de retorno ao solicitante do atendimento
    int inRetTelefone = 0; // Indica se o retorno é por telefone para buscar dados específcicos.
    int idRetTelefone = 0; // id dos dados específicos para retorno telefonico.
    
    int inEnvioTelefone = 0;

    // Caso a tela inicial não envie o id do responsável pela abertura é atribuido valor 3.
    // if (dados.idTipoRelacionamento == TIPO_RELA_FI_FALSO_INSERT)
    // {
    //     dados.idTipoRelacionamento = TIPO_RELA_R_CONS_RELACIONAMENTO;
    // }

    // ==> Março,2009 - Cassio
    // int idSequencia   = 0; // id da sequencia de abertura do atendimento.
    // idSequencia não esta sendo gravado em lugar algum e caso o mesmo não
    // seja encontrado não é lançado erro. Então com base nisso estou removendo
    // a chamada para obter a sequencia de abertura.
    //
    //rcpc->obtemSequenciaAbertura(&idSequencia,dados.idContato,dados.idGrupoAbertura);
    //
    // É obrigatório existir uma sequencia de abertura para o atendimento.
    //
    /*
    if (idSequencia == -1)
    {
        ULOGW("Não foi encontrada a sequencia de abertura do atendimento.");
        // throw new TuxException(E_IDSEQ_NE, M_IDSEQ_NE);
    }
    */
    // <== Março,2009 - Cassio

    //==> OS-517, Março, 2007 - Cassio
    // Esta OS afetou as alterações feitas na incidência 3271 de H.V., pois a partir desta
    // alteração todos os tipos de contatos de retorno são passíveis de serem tratados por
    // grupos de retorno, dependendo apenas da sua configuração, com exceção da opção
    // "Sem Retorno" que esta "hard-coded" no ambiente web e sempre gera processos com
    // tipo de retorno "sem retorno" para o contato.
    ULOG( "b1." );
    //rcpc->obtemTipoRetornoAtendimento(&idTipoRetorno,0);

    rcpc->obtemTipoRetornoAtendimento(&idTipoRetorno,&inEnvioTelefone);
    //char idTipoRetornoPrm[40];
    //idTipoRetornoPrm[0] = 0x0;
    //rcpc->obtemTipoRetornoAtendimento_11( idTipoRetornoPrm );
    
    ULOG( "b2." );
    if ( -1 == idTipoRetorno )
    //if ( idTipoRetornoPrm[0] == 0x0 )
    { // se não encontrou, assume retorno por grupo BKO inicialmente
        idTipoRetorno = TP_RET_COM_RET_GRP_BKO;
    }
    
    /*
    else
    {
        idTipoRetorno = atoi(idTipoRetornoPrm);
    }
    */
    //<== OS-517, Março, 2007 - Cassio

    // Processa a inclusão do atendimento.
    memset( &m_stDadosAtendimento, 0,sizeof(m_stDadosAtendimento) );
    memset( &m_vlDadosAtendimento,-1,sizeof(m_vlDadosAtendimento) );

    ULOG( "b3." );
    SAFE_STRNCPY(m_stDadosAtendimento.dtAbertura,            dados.dataAtual);
    ULOG( "b4." );
    SAFE_STRNCPY(m_stDadosAtendimento.dtUltimaAlteracao,     dados.dataAtual);
    ULOG( "b5." );
    SAFE_STRNCPY(m_stDadosAtendimento.dtPrazoFinalInterno,   dados.dataFechamento);
    ULOG( "b6." );
    SAFE_STRNCPY(m_stDadosAtendimento.dtPrazoFinalAnatel,    dados.dataANATEL);
    ULOG( "b7." );
    SAFE_STRNCPY(m_stDadosAtendimento.dtPrazoFinalCPrevio,   dados.dataCPrevio);
    ULOG( "b8." );
    SAFE_STRNCPY(m_stDadosAtendimento.vlPesoAtendimento,     dados.peso);
    ULOG( "b9." );
    SAFE_STRNCPY(m_stDadosAtendimento.idAtendimentoProtocolo,dados.idAtendimentoProtocolo);
    ULOG( "b10." );

    if ( dados.sgRegraEncaminhamento[0] )
    {
    ULOG( "b11." );
        SAFE_STRNCPY(m_stDadosAtendimento.sgRegraEncaminhamento, dados.sgRegraEncaminhamento);
        m_vlDadosAtendimento.sgRegraEncaminhamento = 1;
    }

    if ( dados.sgFluxoAtendimento[0] )
    {
    ULOG( "b12." );
        SAFE_STRNCPY(m_stDadosAtendimento.sgFluxoAtendimento,    dados.sgFluxoAtendimento);
        m_vlDadosAtendimento.sgFluxoAtendimento = 1;
    }

    if ( dados.nrOrdemVenda[0] )
    {
    ULOG( "b13." );
        SAFE_STRNCPY(m_stDadosAtendimento.nrOrdemVenda,    dados.nrOrdemVenda);
        m_vlDadosAtendimento.nrOrdemVenda = 1;
    }

    // Dados de portabilidade
    if ( dados.sgTipoPortabilidade[0] )
    {
    ULOG( "b14." );
        SAFE_STRNCPY(m_stDadosAtendimento.sgTipoPortabilidade,dados.sgTipoPortabilidade);
        m_vlDadosAtendimento.sgTipoPortabilidade = 1;
    ULOG( "b15." );

        if ( dados.sgOperadoraSolicitante && *dados.sgOperadoraSolicitante )
        {
    ULOG( "b16." );
            SAFE_STRNCPY(m_stDadosAtendimento.sgOperadoraSolicitante,dados.sgOperadoraSolicitante);
            m_vlDadosAtendimento.sgOperadoraSolicitante = 1;
        }

        if ( dados.nrProtocoloPortabilidade && *dados.nrProtocoloPortabilidade )
        {
    ULOG( "b17." );
            SAFE_STRNCPY(m_stDadosAtendimento.nrProtocoloPortabilidade,dados.nrProtocoloPortabilidade);
            m_vlDadosAtendimento.nrProtocoloPortabilidade = 1;
        }

        if ( dados.dtJanelaPortout && *dados.dtJanelaPortout )
        {
    ULOG( "b18." );
            SAFE_STRNCPY(m_stDadosAtendimento.dtJanelaPortout,dados.dtJanelaPortout);
            m_vlDadosAtendimento.dtJanelaPortout = 1;
        }
    }

    m_stDadosAtendimento.idContato                 = dados.idContato;
    m_stDadosAtendimento.idCanal                   = dados.idCanal;
    m_stDadosAtendimento.idProcedencia             = dados.idProcedencia;
    m_stDadosAtendimento.idTipoCarteira            = dados.idTipoCarteira;
    m_stDadosAtendimento.idSegmentacao             = dados.idSegmentacao;
    m_stDadosAtendimento.idPessoaUsuarioAbertura   = dados.idUsuarioBKO;
    m_stDadosAtendimento.idGrupoAbertura           = dados.idGrupoAbertura;
    m_stDadosAtendimento.qtHorasPrazoAtendimento   = int(dados.horas);
    m_stDadosAtendimento.idTipoRetornoContato      = idTipoRetorno;
    m_stDadosAtendimento.idUsuarioAlteracao        = dados.idUsuarioBKO;
    m_stDadosAtendimento.nrNivel                   = 0;
    m_stDadosAtendimento.idFase                    = TRATAMENTO;
    m_stDadosAtendimento.idAgrupamentoEstadoTpProc = dados.idAgrupamentoEstadoTpProc;

    if ( dados.idPessoaConta > 0 )
    {
    ULOG( "b19." );
        m_stDadosAtendimento.idPessoaConta         = dados.idPessoaConta;
        m_vlDadosAtendimento.idPessoaConta         = 1;
    }

    // ==> Remodelagem de ATENDIMENTO.ATENDIMENTO - Fev/2007 - Cassio
    if ( dados.cdAreaRegistro > 0 )
    {
    ULOG( "b20." );
        m_stDadosAtendimento.cdAreaRegistro        = dados.cdAreaRegistro;
        m_vlDadosAtendimento.cdAreaRegistro        = 1;
    }

    if ( dados.idLinhaTelefonica > 0 )
    {
    ULOG( "b21." );
        m_stDadosAtendimento.idLinhaTelefonica     = dados.idLinhaTelefonica;
        m_vlDadosAtendimento.idLinhaTelefonica     = 1;
    }

    if ( dados.idPessoaLinhaHistorico > 0 )
    {
    ULOG( "b22." );
        m_stDadosAtendimento.idPessoaLinhaHistorico= dados.idPessoaLinhaHistorico;
        m_vlDadosAtendimento.idPessoaLinhaHistorico= 1;
    }

    if ( dados.idTipoLinha > 0 )
    {
    ULOG( "b23." );
        m_stDadosAtendimento.idTipoLinha           = dados.idTipoLinha;
        m_vlDadosAtendimento.idTipoLinha           = 1;
    }

    if ( dados.idEstadoLinha > 0 )
    {
    ULOG( "b24." );
        m_stDadosAtendimento.idEstadoLinha         = dados.idEstadoLinha;
        m_vlDadosAtendimento.idEstadoLinha         = 1;
    }

    if ( dados.idUFOperadora > 0 )
    {
    ULOG( "b25." );
        m_stDadosAtendimento.idUFOperadora         = dados.idUFOperadora;
        m_vlDadosAtendimento.idUFOperadora         = 1;
    }
    // <== Remodelagem de ATENDIMENTO.ATENDIMENTO - Fev/2007 - Cassio

    // A partir desta versão, existe um campo na tabela ATENDIMENTO.ATENDIMENTO
    // informando o tipo de abertura para atender a solicitação da incidência
    // 3288 aberta no TD de homologação Vivo em novembro de 2005.
    ULOG( "b26." );
    m_stDadosAtendimento.idTipoAbertura            = 
            dados.atendimentoPorConta ? ABERTURA_POR_CONTA : ABERTURA_POR_LINHA;
    ULOG( "b27." );

    m_vlDadosAtendimento.dtAbertura                = 1;
    m_vlDadosAtendimento.idContato                 = 1;
    m_vlDadosAtendimento.idCanal                   = 1;
    m_vlDadosAtendimento.idProcedencia             = 1;
    m_vlDadosAtendimento.idTipoCarteira            = 1;
    m_vlDadosAtendimento.idSegmentacao             = 1;
    m_vlDadosAtendimento.idPessoaUsuarioAbertura   = 1;
    m_vlDadosAtendimento.idGrupoAbertura           = 1;
    m_vlDadosAtendimento.qtHorasPrazoAtendimento   = 1;
    m_vlDadosAtendimento.vlPesoAtendimento         = 1;
    m_vlDadosAtendimento.idTipoRetornoContato      = 1;
    m_vlDadosAtendimento.dtPrazoFinalInterno       = 1;
    m_vlDadosAtendimento.dtPrazoFinalAnatel        = 1;
    m_vlDadosAtendimento.idUsuarioAlteracao        = 1;
    m_vlDadosAtendimento.dtUltimaAlteracao         = 1;
    m_vlDadosAtendimento.nrNivel                   = 1;
    m_vlDadosAtendimento.idFase                    = 1;
    m_vlDadosAtendimento.idTipoAbertura            = 1;
    m_vlDadosAtendimento.idAgrupamentoEstadoTpProc = 1;
    m_vlDadosAtendimento.idAtendimentoProtocolo    = 1;

    ULOG( "b28." );
    // Se existe configuração de contato prévio, informa
    if ( dados.horasRC > 0 )
    {
    ULOG( "b29." );
        m_vlDadosAtendimento.dtPrazoFinalCPrevio   = 1;
    }

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimento atd;

    ULOG( "b30." );
    dados.idAtendimento = m_stDadosAtendimento.idAtendimento = 
                    atd.incluir(&m_stDadosAtendimento,&m_vlDadosAtendimento,&xmlDpr);
    m_vlDadosAtendimento.idAtendimento = 1;
    ULOG( "b31." );

    //ULOG("idAtendimento gerado=%d",m_stDadosAtendimento.idAtendimento);
    //ULOG("  nrProtocolo gerado='%s'",m_stDadosAtendimento.idAtendimentoProtocolo);

    // Se tiver observação então persiste.
    if ( dados.observacao[0] )
    {
    ULOG( "b32." );
        gravaAtendimentoObservacao();
    }

    ULOG_END("cRegContato::gravaAtendimento()");
}

/**
    Se estiver abrindo processo para workflow, informa a prioridade do atendimento
*/
void cRegContato::gravaAtendimentoPriorizacao(st_Atendimento *dadosAtendimento
                                             ,st_vlAtendimento *statusAtendimento
                                             ,st_AtendimentoPessoa *dadosAtendimentoPessoa
                                             ,st_vlAtendimentoPessoa *statusAtendimentoPessoa
                                             //,st_AtendimentoLinha *dadosAtendimentoLinha
                                             //,st_vlAtendimentoLinha *statusAtendimentoLinha
                                             ,int idTipoLinha)
{
    ULOG_START("cRegContato::gravaAtendimentoPriorizacao()");
    // Registro na tabela de priorizacao do atendimento.
    // Essa tabela tem o papel de ajudar na performance da pesquisa da fila.
    // Todo atendimento (-palitagens) obrigatoriamente deve ter um registro nessa tabela.
    
    cWfAtdAlterProt cwfatdalterprot;

    if ( dados.tpOperacao != PALITAGEM )
    {
        rcpc->registraTabelaPriorizacao(dados.idTipoReaberturaProcesso
                                       //,dados.idLinhaTelefonica
                                       ,dados.idTipoPessoa
                                       //,dados.idUFOperadora
                                       //,idTipoLinha
                                       ,dadosAtendimento
                                       ,statusAtendimento
                                       ,dadosAtendimentoPessoa
                                       ,statusAtendimentoPessoa
                                       //,dadosAtendimentoLinha
                                       //,statusAtendimentoLinha
                                       );

        // Incrementa o total de processos abertos e pendentes sob o protocolo.
        cwfatdalterprot.IncrementarQuantidades(dados.idAtendimentoProtocolo,"1","1",dados.idUsuarioBKOSZ);
    }
    else
    {
        cwfatdalterprot.IncrementarQuantidades(dados.idAtendimentoProtocolo,"1","0",dados.idUsuarioBKOSZ);
    }
    
    ULOG_END("cRegContato::gravaAtendimentoPriorizacao()");
}

/**
    Insere observação do processo sendo criado
*/
void cRegContato::gravaAtendimentoObservacao()
{
    ULOG_START("cRegContato::gravaAtendimentoObservacao()");

    cWFAtendimentoObservacao cwfatendimentoobservacao;

    st_AtendimentoObservacao m_stDados;
    st_vlAtendimentoObservacao m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento      = dados.idAtendimento;
    m_stDados.idUsuarioAlteracao = dados.idPessoaDeParaUsuario;
    m_stDados.pdsObservacao      = dados.observacao;

    m_vlDados.idAtendimento      = 1;
    m_vlDados.idUsuarioAlteracao = 1;
    m_vlDados.dsObservacao       = 1;

    cwfatendimentoobservacao.incluir(&m_stDados,&m_vlDados,&xmlDpr);

    ULOG_END("cRegContato::gravaAtendimentoObservacao()");
}

/**
    Registra os dados de andamento do atendimento.
*/
void cRegContato::gravaAndamento()
{
    ULOG_START("cRegContato::gravaAndamento()");

    st_Andamento            m_stDados;
    st_vlAndamento          m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAtividade = 1;
    m_stDados.idAgrupamentoEstadoTpProc = dados.idAgrupamentoEstadoTpProc;
    m_stDados.idAtendimento = dados.idAtendimento;
    m_stDados.idPessoaUsuario = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDados.dtAndamento, dados.dataAtual);
    m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);
    m_stDados.idGrupo = dados.idGrupoAbertura;

    m_vlDados.idAtividade               = 1;
    m_vlDados.idAgrupamentoEstadoTpProc = 1;
    m_vlDados.idAtendimento             = 1;
    m_vlDados.idPessoaUsuario           = 1;
    m_vlDados.dtAndamento               = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;
    m_vlDados.idGrupo                   = 1;
    
    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAndamento andx(&m_stDados, &m_vlDados, &retorno);

    dados.idAndamento = andx.incluir(&xmlDpr);

    ULOG("idAndamento gerado=%ld",dados.idAndamento);

    ULOG_END("cRegContato::gravaAndamento()");
}

/**
    Registra os dados de andamento observacao.
*/
void cRegContato::gravaAndamentoObservacao()
{
    ULOG_START("cRegContato::gravaAndamentoObservacao()");

    st_AndamentoObservacao      m_stDados;
    st_vlAndamentoObservacao    m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    char dsObservacao[1001];
    char observacaoPortabilidade[512];
    observacaoPortabilidade[0] = 0;

    if ( dados.sgTipoPortabilidade[0] )
    {
        char df[32]; // DD/MM/YYYY HHhMIminSSs

        if ( dados.dtJanelaPortout[0] )
        {
            sprintf(df,"%.2s%c%.2s%c%.4s %.2s:%.2s:%.2s",
                        dados.dtJanelaPortout,'/',
                        dados.dtJanelaPortout+2,'/',
                        dados.dtJanelaPortout+4,
                        dados.dtJanelaPortout+8,
                        dados.dtJanelaPortout+10,
                        dados.dtJanelaPortout+12);

        }
        else
        {
            strcpy(df,"** NÃO INFORMADA **");
        }

        strcpy(observacaoPortabilidade,"TIPO:");
        strcat(observacaoPortabilidade,dados.sgTipoPortabilidade);
        strcat(observacaoPortabilidade,"-ESTADO:");
        strcat(observacaoPortabilidade,dados.dsAcaoPortabilidade);

        if ( strcmp(dados.sgTipoPortabilidade,PROCESSO_PORTOUT) )
        {
            strcat(observacaoPortabilidade,"-DATA JANELA:");
            strcat(observacaoPortabilidade,df);
        }
        else if ( strcmp(dados.sgTipoPortabilidade,PROCESSO_PORTOUT) == 0 )
        {
            if ( strcmp(dados.dsAcaoPortabilidade,"Processando Autorização")
              && strcmp(dados.dsAcaoPortabilidade,"Autorização Negada")
              && strcmp(dados.dsAcaoPortabilidade,"Retenção")
               )
            {
                strcat(observacaoPortabilidade,"-DATA JANELA:");
                strcat(observacaoPortabilidade,df);
            }
        }

        strcat(observacaoPortabilidade,"-OPERADORA:");
        strcat(observacaoPortabilidade,
                    dados.sgOperadoraSolicitante[0]==0
                            ?"** NÃO INFORMADA **":dados.sgOperadoraSolicitante);
    }

    dsObservacao[0] = 0;

    if ( dados.observacao[0] && observacaoPortabilidade[0] )
    {
        sprintf(dsObservacao,"%.800s %.200s",
                (dados.observacao[0])?dados.observacao:"",
                (observacaoPortabilidade[0])?observacaoPortabilidade:"");
    }
    else if ( dados.observacao[0] )
    {
        SAFE_STRNCPY(dsObservacao,dados.observacao);
    }
    else if ( observacaoPortabilidade[0] )
    {
        SAFE_STRNCPY(dsObservacao,observacaoPortabilidade);
    }

    if ( dsObservacao[0] )
    {
        m_stDados.idAndamento = dados.idAndamento;
        m_stDados.pdsAndamentoObservacao = dsObservacao;

        m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
        SAFE_STRNCPY(m_stDados.dtUltimaAlteracao,dados.dataAtual);

        m_vlDados.idAndamento           = 1;
        m_vlDados.dsAndamentoObservacao = 1;
        m_vlDados.idUsuarioAlteracao    = 1;
        m_vlDados.dtUltimaAlteracao     = 1;

        // Executa a chamada de inclusão do registro.
        cWFAndamentoObservacao ando(&m_stDados, &m_vlDados,0);

        ando.incluir(&xmlDpr);
    }

    ULOG_END("cRegContato::gravaAndamentoObservacao()");
}

/**
    Registra os dados da conta associada ao usuário.
*/
void cRegContato::gravaAtendimentoConta()
{
    ULOG_START("cRegContato::gravaAtendimentoConta()");

    if (dados.idConta <= 0)
    {
        ULOG("Registra Contato - Nâo foi informado o código da conta, não será gravado nada nessa tabela.");
        ULOG_END("cRegContato::gravaAtendimentoConta()");
        return;
    }

    st_AtendimentoConta     m_stDados;
    st_vlAtendimentoConta   m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAtendimento = dados.idAtendimento;

    SAFE_STRNCPY(m_stDados.cdConta, dados.cdConta);
    m_stDados.cdDigitoConta = atoi(dados.cdDigitoConta);
    m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);

    m_vlDados.idAtendimento         = 1;
    m_vlDados.cdConta               = 1;
    m_vlDados.cdDigitoConta         = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoConta ac(&m_stDados, &m_vlDados, &retorno);

    ac.incluir();

    ULOG_END("cRegContato::gravaAtendimentoConta()");
}

/**
    Registra os dados do Atendimento de Origem do Atendimento criado no processo.
*/
void cRegContato::gravaAtendimentoOrigem()
{
    ULOG_START("cRegContato::gravaAtendimentoOrigem()");

    if (dados.idAtendimentoOrigem <= 0)
    {
        ULOG("Registra Contato - Nâo foi informado o código do atendimento de origem.");
        ULOG_END("cRegContato::gravaAtendimentoOrigem()");
        return;
    }

    st_AtendimentoOrigem    m_stDados;
    st_vlAtendimentoOrigem  m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAtendimento = dados.idAtendimento;
    m_stDados.idAtendimentoOrigem = dados.idAtendimentoOrigem;
    m_stDados.idTipoReaberturaProcesso = dados.idTipoReaberturaProcesso;
    m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);

    m_vlDados.idAtendimento             = 1;
    m_vlDados.idAtendimentoOrigem       = 1;
    m_vlDados.idTipoReaberturaProcesso  = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoOrigem ao(&m_stDados, &m_vlDados, &retorno);

    ao.incluir(&xmlDpr);

    ULOG_END("cRegContato::gravaAtendimentoOrigem()");
}

/**
    Registra o dado de contato do cliente.
*/
void cRegContato::gravaAtendimentoContato()
{
    ULOG_START("cRegContato::gravaAtendimentoContato()");

    st_AtendimentoContato   m_stDados;
    st_vlAtendimentoContato m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento = dados.idAtendimento;
    m_vlDados.idAtendimento = 1;

    if ( dados.nmContato[0] )
    {
        SAFE_STRNCPY(m_stDados.nmContato,dados.nmContato);
        m_vlDados.nmContato = 1;
    }

    if ( dados.nmFalandoCom[0] )
    {
        SAFE_STRNCPY(m_stDados.nmFalandoCom,dados.nmFalandoCom);
        m_vlDados.nmFalandoCom = 1;
    }

    if ( dados.nrTelefone[0] )
    {
        SAFE_STRNCPY(m_stDados.nrTelefoneContato,dados.nrTelefone);
        m_vlDados.nrTelefoneContato = 1;

        m_stDados.cdAreaRegistro = dados.cdAreaRegistro;
        m_vlDados.cdAreaRegistro = 1;
    }

    m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
    m_vlDados.idUsuarioAlteracao= 1;

    SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);
    m_vlDados.dtUltimaAlteracao = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoContato ac(&m_stDados, &m_vlDados, &retorno);

    ac.incluir(&xmlDpr); // ATENDIMENTO.ATENDIMENTOCONTATO

    ULOG_END("cRegContato::gravaAtendimentoContato()");
}

/**
    Registra os dados do tipo de retorno do contato.
*/
void cRegContato::gravaAtendimentoContatoComunic()
{
    ULOG_START("cRegContato::gravaAtendimentoContatoComunic()");

    st_AtendimentoContatoComunic    m_stDados;
    st_vlAtendimentoContatoComunic  m_vlDados;
    AtendimentoRetorno* ar; 
    XMLGen retorno;

    ULOG("dados.contatoComunic.GetCount()=%d",dados.contatoComunic.GetCount());

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    // Executa a chamada de inclusão do registro.
    for ( int i = 0; i < dados.contatoComunic.GetCount() ; i++ )
    {
        ar = (AtendimentoRetorno*) dados.contatoComunic.GetItem(i);

        if (!ar)
        {
            ULOG("gravaAtendimentoContatoComunic - Não foi recuperado contato comunicação,i=%d",i);
            continue;
        }

        if ( 0 == ar->dsComunicacao )
        {
            ULOGE("dsComunicacao nulo,i=%d",i);
            continue;
        }

        if ( 0 == ar->idTipoComunicacao )
        {
            ULOGE("idTipoComunicacao nulo,i=%d",i);
            continue;
        }

        m_stDados.idAtendimento = dados.idAtendimento;
        SAFE_STRNCPY(m_stDados.dsComunicacao, ar->dsComunicacao);
        m_stDados.idTipoComunicacao = ar->idTipoComunicacao;
        m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
        SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);
        m_stDados.nrOrdemUtilizacao = i;

        m_vlDados.idAtendimento     = 1;
        m_vlDados.dsComunicacao     = 1;
        m_vlDados.idTipoComunicacao = 1;
        m_vlDados.idUsuarioAlteracao= 1;
        m_vlDados.dtUltimaAlteracao = 1;
        m_vlDados.nrOrdemUtilizacao = 1;

        ULOG("dsComunicacao=%s",ar->dsComunicacao);

        // Executa a chamada de inclusão do registro.
        cWFAtendimentoContatoComunic ac(&m_stDados, &m_vlDados, &retorno);

        ac.incluir(&xmlDpr); //ATENDIMENTO.ATENDIMENTOCONTATOCOMUNIC
    }

    ULOG_END("cRegContato::gravaAtendimentoContatoComunic()");
}

/**
    Registra os dados da pessoa que abriu o atendimento.
*/
void cRegContato::gravaAtendimentoPessoa()
{
    ULOG_START("cRegContato::gravaAtendimentoPessoa()");

    XMLGen retorno;

    memset(&m_stDadosPessoa,0,sizeof(m_stDadosPessoa));
    memset(&m_vlDadosPessoa,-1,sizeof(m_vlDadosPessoa));

    m_stDadosPessoa.idAtendimento = dados.idAtendimento;
    m_stDadosPessoa.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDadosPessoa.dtUltimaAlteracao, dados.dataAtual);

    m_vlDadosPessoa.idAtendimento = 1;
    m_vlDadosPessoa.idUsuarioAlteracao = 1;
    m_vlDadosPessoa.dtUltimaAlteracao = 1;
    m_vlDadosPessoa.idPessoaDePara = 1;
    m_vlDadosPessoa.idTipoRelacionamento = 1;

    // Verifica se foi o usuário quem fez a abertura do atendimento.
    if (dados.idTipoRelacionamento == TIPO_RELA_U_USUARIO)
    {
        ///////////////////////////////////////////////////////////////////////////////////
        // M0007 Setembro de 2006, Cassio
        // Foi removido o valor 'falso insert' da tabela CUSTOMER.TIPORELACIONAMENTO e
        // a partir desta versão sempre que for registrado contato para usuário a tabela
        // ATENDIMENTO.ATENDIMENTOPESSOA irá continuar gerando 2 registros, um para
        // usuário e outro para cliente, com a diferença que o registro para o cliente
        // irá usar o tipo de relacionamento de cliente ao invés do tipo de
        // relacionamento 'falso insert'.
        ///////////////////////////////////////////////////////////////////////////////////
        //
        // if (dados.idPessoaDeParaCliente == dados.idPessoaDeParaUsuario)
        // {
        //     // Se o usuário também for cliente, grava como sendo solicitação do cliente.
        //     m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaCliente;
        //     m_stDadosPessoa.idTipoRelacionamento = 
        //         dados.idTipoRelacionamento = TIPO_RELA_C_CLIENTE; // CLIENTE
        // 
        //     cWFAtendimentoPessoa ap(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
        //     ap.incluir();
        // }
        // else
        // {
        //     // Quando o solicitante do atendimento é o usuário é gravado um registro apontado 
        //     // para o cliente da linha para que possa ser exibido o registro na consulta do 
        //     // relacionamento tanto do cliente quanto do usuário.
        //     m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaCliente;
        //     m_stDadosPessoa.idTipoRelacionamento = TIPO_RELA_FI_FALSO_INSERT;
        // 
        //     cWFAtendimentoPessoa ap1(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
        //     ap1.incluir();
        // 
        //     m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaUsuario;
        //     m_stDadosPessoa.idTipoRelacionamento = TIPO_RELA_U_USUARIO;
        // 
        //     cWFAtendimentoPessoa ap2(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
        //     ap2.incluir();
        // }
        m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaCliente;
        m_stDadosPessoa.idTipoRelacionamento = 
            dados.idTipoRelacionamento = TIPO_RELA_C_CLIENTE;

        cWFAtendimentoPessoa ap(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
        ap.incluir(&xmlDpr);

        // ==> SM324--DPR--DEZ/2006--Cassio
        rcpc->AtualizarDadosPessoaDeParaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
        rcpc->AtualizarDadosPessoaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
        // <== SM324--DPR--DEZ/2006--Cassio

        if (dados.idPessoaDeParaCliente != dados.idPessoaDeParaUsuario)
        {
            m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaUsuario;
            m_stDadosPessoa.idTipoRelacionamento = TIPO_RELA_U_USUARIO;

            cWFAtendimentoPessoa ap1(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
            ap1.incluir(&xmlDpr);
        }
    }
    else
    {
        // Grava o registro do solicitante assim como foi enviado.
        m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaCliente;
        m_stDadosPessoa.idTipoRelacionamento = dados.idTipoRelacionamento;

        cWFAtendimentoPessoa ap(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
        ap.incluir(&xmlDpr);

        // ==> SM324--DPR--DEZ/2006--Cassio
        rcpc->AtualizarDadosPessoaDeParaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
        rcpc->AtualizarDadosPessoaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
        // <== SM324--DPR--DEZ/2006--Cassio
    }
    
    ULOG_END("cRegContato::gravaAtendimentoPessoa()");
}

/**
    Registra os dados do formulário dinamico do atendimento.
*/
void cRegContato::gravaAtendimentoFormularioDinamico()
{
    ULOG_START("cRegContato::gravaAtendimentoFormularioDinamico()");

    if (dados.formularioDinamico.GetCount() == 0)
    {
        ULOG("Registra Contato - Não foi respondido questionário.");
        return;
    }           
    
    st_AtendimentoFrm           m_stDados;
    st_vlAtendimentoFrm         m_vlDados;

    st_AtendimentoFrmCampo      m_stCampo;
    st_vlAtendimentoFrmCampo    m_vlCampo;

    memset(&m_stDados, 0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    AtendimentoFormulario*  af; 
    FormularioResposta*     fr;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    Collection* respostas;
    long idAtendimentoFrm;

    m_stDados.idAtendimento = dados.idAtendimento;
    m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);

    m_vlDados.idAtendimento         = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    m_stCampo.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stCampo.dtUltimaAlteracao, dados.dataAtual);

    m_vlCampo.idUsuarioAlteracao = 1;
    m_vlCampo.dtUltimaAlteracao  = 1;

    // Registra o campo.
    for ( int i = 0 ; i < dados.formularioDinamico.GetCount() ; i++ )
    {
        af = (AtendimentoFormulario*) dados.formularioDinamico.GetItem(i);

        if (!af)
        {
            ULOG("Registra Contato - gravaAtendimentoFormularioDinamico - Não foi recuperado atendimentoFormulario...");
            continue;
        }

        m_stDados.idCampo = af->idCampo;
        ULOG("Registra Contato - gravaAtendimentoFormularioDinamico - idCampo [%d]...", m_stDados.idCampo);

        cWFAtendimentoFrm afrm(&m_stDados, &m_vlDados, &retorno);
        
        respostas = af->respostas;

        idAtendimentoFrm = 0;

        // Registra as respostas do campo.
        for ( int b = 0; b < respostas->GetCount(); b++ )
        {
            bool incluir = true;

            fr = (FormularioResposta*) respostas->GetItem(b);
            
            if (!fr)
            {
                ULOG("Registra Contato - gravaAtendimentoFormularioDinamico - "
                            "Não foi recuperado formularioResposta...");
                continue;
            }

            if (fr->idDominio > 0)
            {
                m_stCampo.idDominio = fr->idDominio;
                m_stCampo.dsValor[0] = 0;

                ULOG("Registra Contato - gravaAtendimentoFormularioDinamico - "
                            "Resposta possui dominio [%d]...", m_stCampo.idDominio);

                m_vlCampo.idDominio = 1;
                m_vlCampo.dsValor   = -1;
            }
            else
            {
                
                SAFE_STRNCPY(m_stCampo.dsValor, fr->dsResposta);
                m_stCampo.idDominio = 0;

                if ( m_stCampo.dsValor[0] == 0 )
                {
                    incluir = false;
                }
                else
                {
                    ULOG("Registra Contato - gravaAtendimentoFormularioDinamico - "
                            "Resposta possui valor [%s]", m_stCampo.dsValor);
                }

                m_vlCampo.dsValor   = 1;
                m_vlCampo.idDominio = -1;
            }

            if (incluir)
            {
                if (idAtendimentoFrm == 0)
                {
                    idAtendimentoFrm = afrm.incluir(&xmlDpr);
                }

                m_stCampo.idAtendimentoFrm = idAtendimentoFrm;
                m_vlCampo.idAtendimentoFrm = 1;

                // ==> SM324--DPR--DEZ/2006--Cassio
                // Estes campos não são persistidos na tabela ATENDIMENTOFRMCAMPO
                // mas trafegam no VO do XML para uso pelo DPR.
                m_stCampo.idAtendimento = dados.idAtendimento;
                m_vlCampo.idAtendimentoFrm = 1;

                m_stCampo.idCampo = af->idCampo;
                m_vlCampo.idCampo = 1;
                // <== SM324--DPR--DEZ/2006--Cassio

                ULOG("Registra Contato - gravaAtendimentoFormularioDinamico - Incluindo...");
                cWFAtendimentoFrmCampo afrmc(&m_stCampo, &m_vlCampo, &retorno);

                afrmc.incluir(&xmlDpr);
            }
        }

        delete respostas;
    }

    ULOG_END("cRegContato::gravaAtendimentoFormularioDinamico()");
}

/**
    Grava os dados da linha telefonica.
*/
void cRegContato::gravaLinhaTelefonicaAtendimento()
{
    ULOG_START("cRegContato::gravaLinhaTelefonicaAtendimento()");

    memset(&m_stDadosLinha,0,sizeof(m_stDadosLinha));
    memset(&m_vlDadosLinha,-1,sizeof(m_vlDadosLinha));

    if ( false == dados.gravarLinhaTelefonica )
    {
        ULOGW("Registra Contato - Não foi informado idLinhaAtendimento");
        ULOG_END("cRegContato::gravaLinhaTelefonicaAtendimento()");
        return;
    }

    m_stDadosLinha.idAtendimento = dados.idAtendimento;
    m_stDadosLinha.idPessoaLinhaHistorico = dados.idPessoaLinhaHistorico;
    m_stDadosLinha.idEstadoLinha = dados.idEstadoLinha;
    m_stDadosLinha.idUsuarioAlteracao = dados.idUsuarioBKO;
    SAFE_STRNCPY(m_stDadosLinha.dtUltimaAlteracao, dados.dataAtual);
    m_vlDadosLinha.idAtendimento          = 1;
    m_vlDadosLinha.idPessoaLinhaHistorico = 1;
    m_vlDadosLinha.idEstadoLinha          = 1;
    m_vlDadosLinha.idUsuarioAlteracao     = 1;
    m_vlDadosLinha.dtUltimaAlteracao      = 1;

    XMLGen retorno;
    cWFAtendimentoLinha al( &m_stDadosLinha, &m_vlDadosLinha, &retorno );
    al.incluir(&xmlDpr);

    // ==> SM324--DPR--DEZ/2006--Cassio
    rcpc->AtualizarDadosPessoaLinhaHistorico(m_stDadosLinha.idPessoaLinhaHistorico,&xmlDpr);
    // <== SM324--DPR--DEZ/2006--Cassio

    ULOG_END( "cRegContato::gravaLinhaTelefonicaAtendimento()" );
}

/**
    Registra a chamada atendimento
*/
void cRegContato::gravaChamadaAtendimento()
{
    ULOG_START("cRegContato::gravaChamadaAtendimento()");

    if ( dados.idChamadaTelefonica > 0 )
    {
        st_ChamadaAtendimento       m_stDados;
        st_vlChamadaAtendimento     m_vlDados;

        m_stDados.idAtendimento = dados.idAtendimento;
        m_stDados.idChamadaTelefonica = dados.idChamadaTelefonica;
        m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
        SAFE_STRNCPY(m_stDados.dtUltimaAlteracao, dados.dataAtual);

        m_vlDados.idAtendimento         = 1;
        m_vlDados.idChamadaAtendimento  = 1;
        m_vlDados.idUsuarioAlteracao    = 1;
        m_vlDados.dtUltimaAlteracao     = 1;

        XMLGen retorno;
        cWFChamadaAtendimento ca(&m_stDados, &m_vlDados, &retorno);

        ca.incluir();
    }

    ULOG_END("cRegContato::gravaChamadaAtendimento()");
}

/**
    Verifica se o processo pertence a um Consultor de Relacionamento
*/
void cRegContato::obterConsultorRelacionamento()
{
    ULOG_START("cRegContato::obterConsultorRelacionamento()");

    if ( strcmp(dados.sgTipoPortabilidade,"PORTOUT")==0 )
    { // só relaciona processo se for de portout
        if ( dados.idPessoaDeParaCliente > 0 )
        { // se não tem a pessoa cliente do processo não relaciona
            bool retorno = 
                    rcpc->proCObterConsultorRelacionamento(dados.idConta
                                                          ,dados.idPessoaDeParaCliente
                                                          ,&dados.idPessoaConta);
            if ( retorno ) 
            {
                ULOG("idPessoaConta retornado=%d",dados.idPessoaConta);
            }
        }
        else
        {
            ULOG("idPessoaDePara-Cliente não informado");
        }
    }

    ULOG_END("cRegContato::obterConsultorRelacionamento()");
}

/**
    Obtem a data de fechamento do atendimento de acordo com as regras de priorização.
*/
void cRegContato::obtemDataFechamento(double horas,char *dataFechamento,int incremento)
{
    ULOG_START("cRegContato::obtemDataFechamento()");
    // este incremento foi passado para o metodo como parametro, 
    // para nao duplicar o codigo, o valor do incremento sera passado 
    // em funcao da cada tipo de data, no caso de cprevio incremento = 0 dataanametl= 24
    // int incremento = 24;

    cWFAtendimento atd;

    /* Incidência 2491 - WR-RS
       Reabertura de processos deve considerar a data corrente
    if (dados.idTipoReaberturaProcesso == 2)
        rcpc->proCDataFechamento(dados.idAtendimentoOrigem, dataFechamento);
    else
    */

    atd.diasUteis(dados.nrTelefone, horas, incremento, dataFechamento);

    ULOG("Data de fechamento obtida = [%s]", dataFechamento);

    ULOG_END("cRegContato::obtemDataFechamento()");
}

/**
    Obtem a data de fechamento usando como base o prazo estabelecido pela ANATEL.
*/
void cRegContato::obtemDataFechamentoAnatel(char *dataFechamentoAnatel)
{
    ULOG_START("cRegContato::obtemDataFechamentoAnatel()");

    //cRegContatoPC rcpcLoc(&dados);
    cWFAtendimento atd;
    int incremento = 24;
    //double prazo = (double) rcpcLoc.obtemPrazoANATEL();
    double prazo = (double) rcpc->obtemPrazoANATEL();


    /* Incidência 2491 - WR-RS
       Reabertura de processos deve considerar a data corrente
    if (dados.idTipoReaberturaProcesso == 2)
        rcpc->proCDataFechamentoAnatel(dados.idAtendimentoOrigem, dataFechamentoAnatel);
    else
    */

    atd.diasUteis(dados.nrTelefone, prazo, incremento, dataFechamentoAnatel);

    ULOG("Data de fechamento ANATEL obtida = [%s]", dataFechamentoAnatel);

    ULOG_END("cRegContato::obtemDataFechamentoAnatel()");
}

void cRegContato::registrarAcaoDPR()
{
    ULOG_START("cRegContato::registrarAcaoDPR()");

    if ( dados.idAgrupamentoEstadoTpProc >= INI_AGRPTPPROC_TECNICO 
        && dados.idAgrupamentoEstadoTpProc <= FIM_AGRPTPPROC_TECNICO )
    {
        cWFAtdGerarXMLDPR cwfatdgerarxmldpr;

        char idAtendimentoStr[32];

        sprintf(idAtendimentoStr,"%lu",dados.idAtendimento);
        xmlDpr.idAtendimento = idAtendimentoStr;
        xmlDpr.idUser = dados.idUsuarioBKO;
        xmlDpr.nomeServico = "REGCONTATO";
        //xmlDpr.idContato = idContato;
        xmlDpr.idAtendimento = idAtendimentoStr;

        cwfatdgerarxmldpr.persistirDadosDPRContatoTecnico(&xmlDpr);
    }
    else
    {
        ULOG("Processo %lu não é técnico",dados.idAtendimento);
    }

    ULOG_END("cRegContato::registrarAcaoDPR()");
}

void cRegContato::atualizarPalitagem()
{
#ifndef WIN32
    // Se estiver palitando para usuários URA/VOL/TAV/MIGRAÇÃO/UNIFICAÇÃO (usuários virtuais)
    // então não insere no relatório de palitagem devido ao grande volume de dados gerados
    // por estes usuários para impedir excesso de locks na tabela AUXATDPALITAGEM;
    // Esta alteração foi feita em acordo com a área funcional, aos 26/05/2006. Cassio.
    // if ( dados.tpOperacao == PALITAGEM
    //   && dados.idUsuarioBKO == URA_MIGRACAO
    //   && dados.idUsuarioBKO == USUARIO_VOL
    //   && dados.idUsuarioBKO == USUARIO_TAV
    //   && dados.idUsuarioBKO == INTERFACE_DW
    //   && dados.idUsuarioBKO == UNIFICACAO
    //   && dados.idUsuarioBKO == USUARIO_URA )
    // {
    //     tuxfw_getlogger()->debug("chamada assíncrona não executada.");
    // }
    // else
    // {
        ULOG("Inicio da chamada assincrona de DPALITAGE");

        TuxMessage *inputMessage;
        XMLGen *pclXmlGen = new XMLGen;

        try
        {
            pclXmlGen->addItem("idTipoReaberturaProcesso", dados.idTipoReaberturaProcesso);
            pclXmlGen->addItem("idLinhaTelefonica", dados.idLinhaTelefonica);
            pclXmlGen->addItem("idTipoPessoa", dados.idTipoPessoa);
            pclXmlGen->addItem("idUFOperadora", dados.idUFOperadora);
            pclXmlGen->addItem("idTipoLinha", dados.idTipoLinha );
            pclXmlGen->addItem("idContato", dados.idContato);
            pclXmlGen->addItem("idContatoFlag", 1);
            pclXmlGen->addItem("idPessoaUsuarioAbertura", dados.idUsuarioBKO);
            pclXmlGen->addItem("idPessoaUsuarioAberturaFlag", 1);
            pclXmlGen->addItem("idGrupoAbertura", dados.idGrupoAbertura);
            pclXmlGen->addItem("idGrupoAberturaFlag", 1);
            pclXmlGen->addItem("idUsuarioAlteracao", dados.idUsuarioBKO);
            pclXmlGen->addItem("idUsuarioAlteracaoFlag", 1);

            char usuario[256];

            sprintf( usuario,"%d",dados.idUsuarioBKO );

            inputMessage = new TuxMessage();
            inputMessage->setService("PALITAGE");
            inputMessage->setUser(usuario);
            inputMessage->setMessageBody(pclXmlGen);

            //
            // Nos foi recomentado usar getMessageBody() pois o Framework não libera a memória usada 
            // pelo getMessageXML(). -- Agosto/2007 -- Cassio
            //
            //char *sMsgIn = inputMessage->getMessageXML();
            //
            string strMsgIn = 
                "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
                    "<msg>"
                        "<msgHdr>"
                            "<user>"+(string)usuario+"</user>"
                            "<service>PALITAGE</service>"
                        "</msgHdr>"
                        "<msgBody>"
                            + inputMessage->getMessageBody() +
                        "</msgBody>"
                    "</msg>";

            char *sMsgIn = (char*)strMsgIn.c_str();

            this->remoteCall("PALITAGE", sMsgIn, 0);
        }
        catch(...)
        {
            ULOG("Erro de comunicacao com servico de palitagem...");

            delete pclXmlGen;
            delete inputMessage;

            throw;
        }

        delete pclXmlGen;
        delete inputMessage;

        ULOG("Final da chamada de PALITAGE");
    // }
#else // se estiver em ambiente windows, faz o insert sincrono 

    // Pré-processamento do relatório de palitagem (incidência 2517 WR)
    // Se estiver palitando para usuários URA/VOL/TAV/MIGRAÇÃO/UNIFICAÇÃO (usuários virtuais)
    // então não insere no relatório de palitagem devido ao grande volume de dados gerados
    // por estes usuários para impedir excesso de locks na tabela AUXATDPALITAGEM;
    // Esta alteração foi feita em acordo com a área funcional, aos 26/05/2006. Cassio.

    //if ( dados.tpOperacao == PALITAGEM
    //   && dados.idUsuarioBKO == URA_MIGRACAO
    //   && dados.idUsuarioBKO == USUARIO_VOL
    //   && dados.idUsuarioBKO == USUARIO_TAV
    //   && dados.idUsuarioBKO == INTERFACE_DW
    //   && dados.idUsuarioBKO == UNIFICACAO
    //   && dados.idUsuarioBKO == USUARIO_URA )
    //{
    //    ULOG("palitagem para usuário de sistema.");
    //}
    //else
    //{
        proCAtualizarAtdPalitagem(dados.idUFOperadora,dados.idContato,1
                                 ,dados.idUsuarioBKO,1,dados.idGrupoAbertura,1
                                 ,dados.idUsuarioBKO,1);
    //}
#endif // WIN32
}

bool cRegContato::VerificarUserNumericoSimNao(const char *userNN)
{
    return rcpc->proCVerificarUserNumericoSimNao(userNN);
}

bool cRegContato::ObterIdUserUra(const char *userNN,char *idPessoaUsuario)
{
    return rcpc->proCObterIdUserUra(userNN,idPessoaUsuario);
}

void cRegContato::carregaDados(DOMNode*dnode)
{
    ULOG_START("cRegContato::carregaDados()");

    char *p;

    memset( &dados, 0, sizeof(dados) );

    if (p=walkTree( dnode, "idContato", 0 ),p)
    {
        dados.idContato = atoi(p);
        XMLString::release(&p);
        if (0 == dados.idContato) {dados.idContato = -1;}
    }
    else
    {
        dados.idContato = -99;
    }

    if ( -99 == dados.idContato )
    {
        if (p=walkTree( dnode, "nmPath", 0 ),p)
        {
            dados.idContato = rcpc->obtemIdContato(p);
            XMLString::release(&p);
        }
        else
        {
            dados.idContato = -1;
        }
    }

    if (p=walkTree( dnode, "nrOrdemVenda", 0 ),p)
    {
        SAFE_STRNCPY(dados.nrOrdemVenda,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idSegmentacao", 0 ),p)
    {
        dados.idSegmentacao = atoi(p);
        XMLString::release(&p);
        if (0 == dados.idSegmentacao) {dados.idSegmentacao = -1;}
    }
    else
    {
        dados.idSegmentacao = -1;
    }

    if (p=walkTree( dnode, "idProcedencia", 0 ),p)
    {
        dados.idProcedencia = atoi(p);
        XMLString::release(&p);
        if (0 == dados.idProcedencia) {dados.idProcedencia = -1;}
    }
    else
    {
        dados.idProcedencia = -1;
    }

    if (p=walkTree( dnode, "idTipoCarteira", 0 ),p)
    {
        if (strlen(p) > 0)
        {
            if (atoi(p) > 0)
            {
                dados.idTipoCarteira = atoi(p);
            }
            else
            {
                dados.idTipoCarteira = TPC_NAO_CLASSIFICADO;
            }
        }
        else
        {
            dados.idTipoCarteira = TPC_NAO_CLASSIFICADO;
        }
        XMLString::release(&p);
    }
    else
    {
        dados.idTipoCarteira = TPC_NAO_CLASSIFICADO;
    }

    if (p=walkTree( dnode, "idCanal", 0 ),p)
    {
        dados.idCanal = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "tpOperacao", 0 ),p)
    {
        dados.tpOperacao = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idGrupoAbertura", 0 ),p)
    {
        dados.idGrupoAbertura = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "inResponsavelAbertura", 0 ),p)
    {
        dados.idTipoRelacionamento = atoi(p);
        XMLString::release(&p);
    }

    // Remodelagem Atendimento - Fev/2007 - Cassio
    //if (p=walkTree( dnode, "idDiscador", 0 ),p)
    //{
    //    dados.idDiscador = atoi(p);
    //    XMLString::release(&p);
    //}

    if (p=walkTree( dnode, "idTerminal", 0 ),p)
    {
        dados.idTerminal = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idConta", 0 ),p)
    {
        dados.idConta = atoi(p);
        XMLString::release(&p);
    }

    //p=walkTree( dnode, "LinhaVO", 0 );
    //if (strlen(p)!=0)
    //{
    //  dados.atendimentoPorConta = false;
    //}
    //else
    //{
    //  dados.atendimentoPorConta = true;
    //}
    //ULOG("atendimentoPorConta - %d", dados.atendimentoPorConta);

    if (p=walkTree( dnode, "idAtendimento", 0 ),p)
    {
		//dados.idAtendimentoOrigem = atoi(p);
		dados.idAtendimentoOrigem = atol (p);

        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idTipoReaberturaProcesso", 0 ),p)
    {
        dados.idTipoReaberturaProcesso  = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idChamadaTelefonica", 0 ),p)
    {
        dados.idChamadaTelefonica = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idBaixa", 0 ),p)
    {
        dados.idBaixa = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idBaixaMensagem", 0 ),p)
    { // na realidade é idMensagemBaixa que deve vir
        dados.idMensagemBaixa = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idMensagemBaixa", 0 ),p)
    {
        dados.idMensagemBaixa = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idLinhaAtendimento", 0 ),p)
    {
        dados.idLinhaAtendimento = atol(p);
        XMLString::release(&p);
    }

    /*
    if (p=walkTree( dnode, "idUfOperadora", 0 ),p)
    {
        dados.idUFOperadora = atoi(p);
        XMLString::release(&p);
    }
    */

    if (p=walkTree( dnode, "idTipoLinha", 0 ),p)
    {
        dados.idTipoLinha = atoi(p);
        XMLString::release(&p);
    }

    //========================================================
    // OS 686 - Tags especificas para portabilidade - portout
    //========================================================
    //
    if (p=walkTree( dnode, "nrProtocoloPortabilidade", 0 ),p)
    {
        SAFE_STRNCPY(dados.nrProtocoloPortabilidade,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "dtJanelaPortout", 0 ),p)
    { // DDMMYYYYHHMISS
        SAFE_STRNCPY(dados.dtJanelaPortout,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "sgOperadoraSolicitante", 0 ),p)
    {
        SAFE_STRNCPY(dados.sgOperadoraSolicitante,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "tipoPortabilidade", 0 ),p)
    {
        SAFE_STRNCPY(dados.sgTipoPortabilidade,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "dsAcaoPortabilidade", 0 ),p)
    {
        SAFE_STRNCPY(dados.dsAcaoPortabilidade,p);
        XMLString::release(&p);
    }

    //======================================================
    //
    // Obtem o id do cliente do atendimento.
    DOMNode* dom = walkDOM( dnode, "PessoaVO");

    if ( p =walkTree( dom, "idPessoa", 0 ),p )
    {
        dados.idPessoaDeParaCliente = atoi( p );
        XMLString::release(&p);
    }

    // Verifica se esta abrindo por conta ou linha (feature do CRI)
    dom = walkDOM(dnode, "LinhaVO");

    if ( p=walkTree(dom,"idPessoaLinhaHistorico", 0 ),p)
    {
        dados.atendimentoPorConta = false;
        XMLString::release(&p);
    }
    else
    {
        dados.atendimentoPorConta = true;
    }

    ULOG("atendimentoPorConta - %d", dados.atendimentoPorConta);

    // Obtem o id do usuário do atendimento.
    dom = walkDOM( dnode, "UsuarioLinhaVO");
    if ( p=walkTree( dom, "idPessoa", 0 ),p)
    {
        dados.idPessoaDeParaUsuario = atoi( p );
        XMLString::release(&p);
    }

    if ( 0 == dados.idPessoaDeParaUsuario )
    { // se não enviou o ID do usuário, assume que é o mesmo do cliente para não replicar gravação
        dados.idPessoaDeParaUsuario = dados.idPessoaDeParaCliente;
    }

    if ( p=walkTree( dnode, "idPessoaLinhaHistorico", 0 ),p )
    {
        dados.idLinhaTelefonica = atol( p );
        XMLString::release(&p);
    }

    // Obtem o id do tipo de retorno do VO.
    dom = walkDOM( dnode, "AtendimentoRetornoVO");
    if ( p=walkTree( dom, "idTipoComunicacao", 0 ),p )
    {
        dados.idTipoRetorno = atoi( p );
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "nrTelefone", 0 ),p)
    {
        SAFE_STRNCPY(dados.nrTelefone,p);
        XMLString::release(&p);
    }

    dados.cdAreaRegistro = -1;
    if ( dados.nrTelefone[0] )
    {
        sprintf(dados.cdAreaRegistroSZ,"%.2s",&dados.nrTelefone[(dados.nrTelefone[0] == '0') ? 1:0]);

        dados.cdAreaRegistro = atoi(dados.cdAreaRegistroSZ);
    }

    dados.idUFOperadora = 
        rcpc->buscarRegional(dados.cdAreaRegistro>0?dados.cdAreaRegistro:11);

    if (p=walkTree( dnode, "observacao", 0 ),p)
    {
        SAFE_STRNCPY(dados.observacao,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "nmContato", 0 ),p)
    {
        SAFE_STRNCPY(dados.nmContato,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "nmFalandoCom", 0 ),p)
    {
        SAFE_STRNCPY(dados.nmFalandoCom,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "observacaoFechamento", 0 ),p)
    {
        SAFE_STRNCPY(dados.observacaoFechamento,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "inTipoPessoa", 0 ),p)
    {
        SAFE_STRNCPY(dados.inTipoPessoa,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "naoValidarDados", 0 ),p)
    {
        dados.naoValidarDados  = true;
        XMLString::release(&p);
    }
    else
    {
        dados.naoValidarDados  = false;
    }

    // OS661-SMP - S (SIM) = Não enviar SMS de protocolo.
    //dados.naoEnvProtocoloSMS  = false;
    //if (p=walkTree( dnode, "naoEnvProtocoloSMS", 0 ),p)
    //{
    //    if ( 'S' == *p || 's' == *p )
    //    {
    //        dados.naoEnvProtocoloSMS  = true;
    //    }
    //    XMLString::release(&p);
    //}

    //
    dom = walkDOM( dnode, "DadosProtocoloVO");

    if (p=walkTree( dnode, "nrProtocolo", 0 ),p)
    {
        char str[40];
        char tmp[40];
        int len = strlen(p);
        int i,j;

        sprintf(str,"%.*s",len,p);
        memset(tmp,0x0,sizeof(tmp));

        for ( j = i = 0; i < len; i++ )
        {
            if (isdigit(str[i]))
            {
               tmp[j++] = str[i];
               
            }
        }

        //SAFE_STRNCPY(dados.idAtendimentoProtocolo,p);
        SAFE_STRNCPY(dados.idAtendimentoProtocolo,tmp);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idTipoAberturaProtocolo", 0 ),p)
    {
        SAFE_STRNCPY(dados.idTipoAberturaProtocolo,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idSistemaOrigemProtocolo", 0 ),p)
    {
        SAFE_STRNCPY(dados.idSistemaOrigemProtocolo,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "dddSMSProtocolo", 0 ),p)
    {
        SAFE_STRNCPY(dados.dddSMSProtocolo,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "nrLinhaSMSProtocolo", 0 ),p)
    {
        SAFE_STRNCPY(dados.nrLinhaSMSProtocolo,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "forceUsoProtocoloNaoAberto", 0 ),p)
    {
        dados.forceUsoProtocoloNaoAberto = true;
        XMLString::release(&p);
    }

    DOMNode *registro;
    int contador;

    // =========================================================================
    // OS 661 - SMP -- Dez/2007
    // =========================================================================

    // Esta mensagem se sobrepõe a regra de mensagens definidas na documentação
    // if (p=walkTree( dnode, "msgSMS", 0 ),p)
    // {
    //     SAFE_STRNCPY(dados.msgSMS,p);
    //     XMLString::release(&p);
    // }

    AtendimentoLinhas *atendimentoLinhas;
    char *cdConta;
    char *nrTelefone;

    contador = 0;
    while (registro = walkDOM( dnode, "LinhasAssociadasVO", contador++))
    {
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

        atendimentoLinhas = new AtendimentoLinhas();

        atendimentoLinhas->cdConta = cdConta;
        atendimentoLinhas->nrTelefone = nrTelefone;

        dados.atendimentoLinhas.AddItem( (void*) atendimentoLinhas );
    }

    // =========================================================================
    /* 
       IMPORTANTE:
       Os ponteiros ar precisam ser criados com 'new', para que o destrutor da classe
       não seja chamado a cada iteração do while() abaixo e com isso a area do walkTree
       armazenada na classe ser desalocada antes de ser usada.
    */

    AtendimentoRetorno *ar;
    char *pDsComunicacao;

    contador = 0;
    while (registro = walkDOM( dnode, "AtendimentoRetornoVO", contador++))
    {
        if ( pDsComunicacao=walkTree(registro,"dsComunicacao",0),!pDsComunicacao )
        {
            continue;
        }

        if ( 0 == *pDsComunicacao )
        { // página web esta enviando valor vazio, gerando erro Oracle "ORA-01400"
            XMLString::release(&pDsComunicacao);
            continue;
        }

        // ======================================================================
        // Verifica se um dsTipoComunicacao idêntico já existe na lista
        // se já existir, não insere pois gera erro de constraint
        // na tabela ATENDIMENTO.ATENDIMENTOCONTATOCOMUNIC.
        // Erro reportado em ambiente de produção (sem incidência)
        // //////////////////////////////////////////////////////////////////////
        // Março de 2006 - Cassio
        // ======================================================================
        //
        bool inserir = true;
        int iCount = dados.contatoComunic.GetCount();

        for ( int i = 0; i < iCount; i++ )
        {
            if ( ((AtendimentoRetorno*)dados.contatoComunic.GetItem(i))->dsComunicacao )
            {
                if (strcmp(((AtendimentoRetorno*)dados.contatoComunic.GetItem(i))->dsComunicacao
                        ,pDsComunicacao) == 0)
                {
                    inserir = false; // forma de comunicação já informada uma vez
                    break;
                }
            }
        }

        if ( inserir )
        {
            ar = new AtendimentoRetorno();

            ar->dsComunicacao = pDsComunicacao;

            if ( p=walkTree(registro, "idTipoComunicacao", 0),p )
            {
                ar->idTipoComunicacao = atoi( p );
                XMLString::release(&p);
            }

            dados.contatoComunic.AddItem( (void*) ar );
        } // if ( inserir )
    }

    /**
        Coloca todos registros de resposta do formulário dinamico em uma collection.
    */  

    DOMNode* subregistro;
    //int idContatoFolhaCampo = 0;
    int contador2 = 0;

    /* 
       IMPORTANTE:
       Os ponteiros af e fr precisam ser criados com 'new', para que o destrutor da classe
       não seja chamado a cada iteração do while() abaixo e com isso a Collection armazenada
       na classe ser desalocada antes de ser usada.
    */
    AtendimentoFormulario* af;

    FormularioResposta* fr;

    ULOG("Registra Contato - Carrega Dados - Entrando na pesquisa dos campos enviados...");

    contador = 0;
    while (registro = walkDOM( dnode, "FormularioCampoVO", contador++))
    {
        // ********************************************************************************************************
        // **************************************** INFORMAÇÂO IMPORTANTE *****************************************
        // ********************************************************************************************************
        // Houve atrazo na entrega da OS 1264 (Banco do Brasil) e devido a falta de tempo relacionada a isto
        // este campo esta sendo usado pelo webService do VOL-E para trafegar o id do formulário para este serviço.
        // O valor deste campo é subsequentemente enviado para o ENCCONTATO para atender às regras de encaminhamento
        // de formulário associado a uma folha. -- Jan/2010 -- Cassio
        //
        if ( p=walkTree(registro, "idContatoFolhaCampo", 0),p )
        {
            dados.idContatoFolhaCampo = atoi( p );
            XMLString::release(&p);
        }
        else
        {
            dados.idContatoFolhaCampo = 0;
        }

        ULOG("Registra Contato - Carrega Dados - Carregando idContatoFolhaCampo [%d]...", dados.idContatoFolhaCampo);

        if (dados.idContatoFolhaCampo > 0)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            if ( p=walkTree(registro, "idCampo", 0),p)
            {
                af->idCampo = atoi( p );
                XMLString::release(&p);
            }

            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            while (subregistro = walkDOM( registro, "FormularioCampoValorVO", contador2++))
            {
                fr = new FormularioResposta();

                fr->idDominio  = 0;
                fr->dsResposta = 0;

                if (p=walkTree(subregistro, "idFormularioCampoValor", 0),p)
                {
                    fr->idDominio  = atoi( p );
                    XMLString::release(&p);
                    ULOG("Registra Contato - Carrega Dados - Campo possui dominio [%d]...", fr->idDominio);
                }

                if ((p=walkTree(subregistro, "valor", 0),p) && fr->idDominio == 0)
                {
                    fr->dsResposta = p;
                    ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
                }

                af->respostas->AddItem( (void*) fr);
            }

            contador2 = 0;

            dados.formularioDinamico.AddItem( (void*) af );
        }
    }

    //
    // Alteração conforme SM448
    // Gravamos os grupos de campos que estão no formulário
    //
    
    /*
    ULOG("Registra Contato - Carrega Dados - Grupos de Campos enviados...");

    DOMNode* grupocampos;
    int contadorGrupoCampos = 0;
    */

    // Para cada grupo de campos
    /*
    while (grupocampos = walkDOM( dnode, "AdmGrupoCamposVO", contadorGrupoCampos++))
    {
        contador = 0;
        while (registro = walkDOM( grupocampos, "FormularioCampoVO", contador++))
        {
            if ( p=walkTree(registro, "idContatoFolhaCampo", 0),p )
            {
                idContatoFolhaCampo = atoi( p );
                XMLString::release(&p);
            }

            ULOG("Registra Contato - Carrega Dados - Carregando idContatoFolhaCampo [%d]...", idContatoFolhaCampo);

            if (idContatoFolhaCampo > 0)
            {
                af = new AtendimentoFormulario();
                af->respostas = new Collection();

                if ( p=walkTree(registro, "idCampo", 0),p)
                {
                    af->idCampo = atoi( p );
                    XMLString::release(&p);
                }

                ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

                while (subregistro = walkDOM( registro, "FormularioCampoValorVO", contador2++))
                {
                    fr = new FormularioResposta();

                    fr->idDominio  = 0;
                    fr->dsResposta = 0;

                    if (p=walkTree(subregistro, "idFormularioCampoValor", 0),p)
                    {
                        fr->idDominio  = atoi( p );
                        XMLString::release(&p);
                        ULOG("Registra Contato - Carrega Dados - Campo possui dominio [%d]...", fr->idDominio);
                    }

                    if ((p=walkTree(subregistro, "valor", 0),p) && fr->idDominio == 0)
                    {
                        fr->dsResposta = p;
                        ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
                    }

                    af->respostas->AddItem( (void*) fr);
                } // while() FormularioCampoValorVO

                contador2 = 0;

                dados.formularioDinamico.AddItem( (void*) af );
            }
        } // while() FormularioCampoVO

    } // while() AdmGrupoCampos
    */


    //
    // Alteração conforme SM448
    // Gravamos os grupos de campos que estão no formulário
    //
    ULOG("Registra Contato - Carrega Dados - Funcionalidade ENDERECO...");
    if (registro = walkDOM( dnode, "EnderecoVO", 0))
    {
        ULOG("Registra Contato - Carrega Dados - Funcionalidade ENDERECO Inicio.");
        //
        // TipoEnderecoVO
        //
        if (subregistro = walkDOM( registro, "TipoEnderecoVO", 0))
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 1485;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;
            fr->dsResposta = 0;

            if (p=walkTree(subregistro, "idTipoEndereco", 0),p)
            {
                // fr->idDominio  = atoi( p );
                fr->dsResposta = p;
                // ULOG("Registra Contato - Carrega Dados - Campo possui dominio [%d]...", fr->idDominio);
                ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
            }

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nmTipoLogradouro
        //
        if (p=walkTree(registro, "nmTipoLogradouro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 177;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nmTituloLogradouro
        //
        if (p=walkTree(registro, "nmTituloLogradouro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 1487;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nmLogradouro
        //
        if (p=walkTree(registro, "nmLogradouro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 174;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nmBairro
        //
        if (p=walkTree(registro, "nmBairro", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 176;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nmMunicipio
        //
        if (p=walkTree(registro, "nmMunicipio", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 180;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nrEndereco
        //
        if (p=walkTree(registro, "nrEndereco", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 1488;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // dsEnderecoComplemento
        //
        if (p=walkTree(registro, "dsEnderecoComplemento", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 194;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // nrCEP
        //
        if (p=walkTree(registro, "nrCEP", 0),p)
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 181;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;

            fr->dsResposta = p;
            ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // UFVO
        //
        if (subregistro = walkDOM( registro, "UFVO", 0))
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 179;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;
            fr->dsResposta = 0;

            if (p=walkTree(subregistro, "sgUF", 0),p)
            {
                fr->dsResposta = p;
                ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
            }

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

        //
        // PaisVO
        //
        if (subregistro = walkDOM( registro, "PaisVO", 0))
        {
            af = new AtendimentoFormulario();
            af->respostas = new Collection();

            // Pega o idCampo
            af->idCampo = 234;
       
            ULOG("Registra Contato - Carrega Dados - Carregando af->idCampo [%d]...", af->idCampo);

            fr = new FormularioResposta();

            fr->idDominio  = 0;
            fr->dsResposta = 0;

            if (p=walkTree(subregistro, "nmPais", 0),p)
            {
                fr->dsResposta = p;
                ULOG("Registra Contato - Carrega Dados - Campo possui resposta [%s]...", fr->dsResposta);
            }

            af->respostas->AddItem( (void*) fr);
            dados.formularioDinamico.AddItem( (void*) af );
        }

    } // if()


    ULOG_END("cRegContato::carregaDados()");
}

#ifndef WIN32
char* cRegContato::remoteCall(char* nomeServico, char* sMsgIn, int lFlags)
{
    ULOG_START("cRegContato::remoteCall()");
    // Declaracao de variaveis
    char*   sMsgOut = 0L;
    int ret = TUXFWRET_ERROR;

    char*   bufferE = 0L;
    char*   bufferS = 0L;

    // Preenche os tamanhos de buffers para a chamada ao tpacall
    long    snd_len = strlen(sMsgIn);
    long    rcv_len = TUXFW_MAX_MSG_LEN;

    //
    // Aloca um buffer de envio com o tamanho do XML de entrada
    if ((bufferE = (char *)tpalloc("STRING", NULL, snd_len+1)) == NULL)
    {
        ULOG_END("cRegContato::remoteCall()");
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }
    //
    // Aloca um buffer de recepcao com tamanho maximo possivel para retorno
    if ((bufferS = (char *)tpalloc("STRING", NULL, TUXFW_MAX_MSG_LEN)) == NULL)
    {
        tpfree(bufferE);
        ULOG_END("cRegContato::remoteCall()");
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }

    // clona o buffer de entrada no buffer tuxedo de envio
    strcpy(bufferE, sMsgIn);
    ULOG("CRemoteLogBase::remoteCall: dump call parameters:\n\t# serviceName=[%s]\n\tinputBuffer=[%s]\n\t# inputLen=[%d]\n\t# flags=[%d]", nomeServico, bufferE, snd_len, lFlags );
    if ( tpacall(nomeServico, (char*) bufferE, snd_len, TPNOTRAN|TPNOREPLY) == -1)
    {
        long errNo = tperrno;
        long urCode = tpurcode;
        ULOGE("CRemoteLogBase::remoteCall: tpacall com erro, Tperrno = %d, TpUrCode = %d ", errNo, urCode);
        //
        // O porquisse !!! Tenta aproveitar alguma coisa do buffer de retorno mesmo tendo
        // dado erro na chamada se for erro 11 !!!!
        if( errNo == TPESVCFAIL )
        {
            // Copia o buffer tuxedo de retorno
            bufferS[rcv_len]='\0';
            //sMsgOut = derivStr( bufferS );
            ULOG("CRemoteLogBase::remoteCall: tpacall processada com ERRO, outputMessage=[%s]", sMsgOut);
        }
        else 
        {
            tpfree(bufferE); 
            bufferE = 0L;

            tpfree(bufferS);
            bufferS = 0L;

            ULOG_END("cRegContato::remoteCall()");

            throw new TuxException(ERR_TUX_REMOTE_CALL_CD , ERR_TUX_REMOTE_CALL_MSG, nomeServico, errNo, urCode);
        }
    }
    else
    {
        // Copia o buffer tuxedo de retorno
        bufferS[rcv_len]='\0';
        //sMsgOut = derivStr( bufferS );
        ULOGI("CRemoteLogBase::remoteCall: tpacall processada com sucesso, outputMessage=[%s], tamanho=[%d]", sMsgOut, strlen(sMsgOut));
    }

    if(bufferE) tpfree(bufferE); bufferE = 0L;
    if(bufferS) tpfree(bufferS); bufferS = 0L;

    // throw new TuxBasicSvcException(ERR_TUX_NOT_IMPL_CD,"Esta chamada exige debug integrado com Tuxedo");

    ULOG_END("cRegContato::remoteCall()");

    return sMsgOut;
}

#endif
