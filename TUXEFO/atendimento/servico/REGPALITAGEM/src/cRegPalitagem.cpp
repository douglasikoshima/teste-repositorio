/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.8.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cRegPalitagem.h"

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
#include "../../REGCONTATO/include/cMsgErro.h"

//st_EncContatoPreValidacao preValidaEncContato;

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

cRegPalitagem::cRegPalitagem(XMLGen*xml_g)
{
    saida = xml_g;

    rcpc = new cRegContatoPC(&dados,&dadosProtocolo);
}

cRegPalitagem::cRegPalitagem(st_RegContato *st_regcontato)
{
    memcpy(&dados,st_regcontato,sizeof(st_RegContato));
    rcpc = new cRegContatoPC(&dados,&dadosProtocolo);
}

cRegPalitagem::~cRegPalitagem()
{
    delete rcpc;
}

//=========================================================================
//  Registra todos os dados do atendimento.
//=========================================================================
void cRegPalitagem::registra(char *nrProtocoloPrm,long *idAtendimentoPrm)
{
    ULOG_START("void cRegPalitagem::registra(char *nrProtocoloPrm,int *idAtendimentoPrm)");
    //double horas2;
    //
    //=========================================================================
    // Parte I: Obtém dados prévios para abertura da palitagem
    //=========================================================================
    //
    // Prazo de atendimento
    dados.horas = 0.0; // Não coloca info de prazo de atendimento em palitagens
    //rcpc->obtemPrazoAtendimentoContato( &dados.horas ,&horasRC,dados.idContato );
    //if ( dados.horas  < 1 )
    //{
    //    dados.horas  = 0.0;
    //}

    // Peso -- Como peso é usado somente na fila BKO não precisa calcular para a palitagem
    strcpy(dados.peso,"0"); // Não coloca info de prazo de atendimento em palitagens

    // bool retornoPeso=rcpc->obtemPesoAtendimento(dados.peso
    //                                            ,dados.idContato
    //                                            ,dados.idSegmentacao
    //                                            ,dados.idProcedencia
    //                                            ,dados.idTipoCarteira

    // Prazo segmentado
    //rcpc->obtemPrazoAtendimentoSegmentado(&horas2,dados.idContato,dados.idSegmentacao,dados.idProcedencia);
    //if ( horas2 > 0 )
    //{
    //    dados.horas  = horas2;
    //}

    //obtemDataFechamento( dados.horas ,dados.dataFechamento,24 );
    //obtemDataFechamentoAnatel( dados.dataANATEL );

    // agrupamentoEstadoTpProc de abertura
    rcpc->obterDadosContatoFolha(&dados);
    //rcpc->obtemAgrupamentoEstadoTpProc(dados.idContato,&dados.idAgrupamentoEstadoTpProc );

    st_AgrupamentoEstadoTpProc stAgrupamento;
    st_vlAgrupamentoEstadoTpProc vlAgrupamento;
    memset( &stAgrupamento, 0,sizeof(stAgrupamento) );
    memset( &vlAgrupamento,-1,sizeof(vlAgrupamento) );

    stAgrupamento.idAtividade = FECHAMENTO_IMEDIATO_FI;
    stAgrupamento.idAgrupamentoEstadoTpProc = dados.idAgrupamentoEstadoTpProc;

    // agrupamentoEstadoTpProc futuro
    cWFAgrupamentoEstadoTpProc pa( &stAgrupamento, &vlAgrupamento );
    dados.idAgrupamentoEstadoTpProcFt = pa.proximoAgrupamento();
    //
    // Determina se abre ou não um novo protocolo
    //
    cWfAtdAbreProt cwfatdabreprot;

    char idPessoaDeParaProtocolo[39];
    memset(idPessoaDeParaProtocolo,0x0,sizeof(idPessoaDeParaProtocolo));
    
    char idLinhaTelefonicaProtocolo[39];
    memset(idLinhaTelefonicaProtocolo,0x0,sizeof(idLinhaTelefonicaProtocolo));
    
    ULOG( "1. Passando..." );
    
    if ( strcmp(dadosProtocolo.idTipoAberturaProtocolo,TIPO_ABER_PROT_LINHA_CLIENTE) )
    {
        ULOG( "2. Passando..." );
        sprintf(idPessoaDeParaProtocolo,"%d",dados.idPessoaDeParaUsuario);
    }
    else
    {
        ULOG( "3. Passando..." );
        sprintf(idPessoaDeParaProtocolo,"%d",dados.idPessoaDeParaCliente);
    }

    sprintf(idLinhaTelefonicaProtocolo,"%d",dados.idLinhaTelefonica);
    ULOG( "idLinhaTelefonicaProtocolo [%s]",idLinhaTelefonicaProtocolo );
    ULOG( "4. Passando..." );

    cwfatdabreprot.setIdAtendimentoProtocolo(dados.idAtendimentoProtocolo);
    ULOG( "5. Passando..." );
    cwfatdabreprot.setCdAreaRegistro(dados.cdAreaRegistroSZ);
    ULOG( "6. Passando..." );
    if ( strlen(dados.nrTelefone) > 8 )
    {
       ULOG( "dados.nrTelefone [%s]", dados.nrTelefone);
       ULOG( "7. Passando..." );
       cwfatdabreprot.setNrTelefone(&dados.nrTelefone[strlen(dados.cdAreaRegistroSZ)]);
    }
    else
    {
       ULOG( "8. Passando..." );
       ULOG( "dados.nrTelefone [%s]", dados.nrTelefone);
       cwfatdabreprot.setNrTelefone(dados.nrTelefone);
    }
    ULOG( "9. Passando..." );
       ULOG( "dados.dddSMSProtocolo [%s]", dados.dddSMSProtocolo);
    cwfatdabreprot.setCdAreaRegistroSMS(dados.dddSMSProtocolo);
    ULOG( "10. Passando..." );
       ULOG( "dados.nrLinhaSMSProtocolo [%s]", dados.nrLinhaSMSProtocolo);
    cwfatdabreprot.setNrTelefoneSMS(dados.nrLinhaSMSProtocolo);
    ULOG( "11. Passando..." );
       ULOG( "dados.idSistemaOrigemProtocolo [%s]", dados.idSistemaOrigemProtocolo);
    cwfatdabreprot.setIdSistemaOrigem(dados.idSistemaOrigemProtocolo);
    ULOG( "12. Passando..." );
       ULOG( "idPessoaDeParaProtocolo [%s]", idPessoaDeParaProtocolo);
    cwfatdabreprot.setIdPessoaDePara(idPessoaDeParaProtocolo);
    ULOG( "13. Passando..." );
       ULOG( "dados.cdConta [%s]", dados.cdConta);
    cwfatdabreprot.setCdConta(dados.cdConta);
    ULOG( "14. Passando..." );
       ULOG( "idLinhaTelefonicaProtocolo [%s]", idLinhaTelefonicaProtocolo);
    cwfatdabreprot.setIdLinhaTelefonica(idLinhaTelefonicaProtocolo);
    ULOG( "15. Passando..." );
       ULOG( "dados.idTipoAberturaProtocolo [%s]", dados.idTipoAberturaProtocolo);
    cwfatdabreprot.setIdTipoAberturaProtocolo(dados.idTipoAberturaProtocolo);
    ULOG( "16. Passando..." );
       ULOG( "dados.idUsuarioBKOSZ [%s]", dados.idUsuarioBKOSZ);
    cwfatdabreprot.setIdUsuarioAlteracao(dados.idUsuarioBKOSZ);
    ULOG( "17. Passando..." );
       ULOG( "dados.forceUsoProtocoloNaoAberto [%s]", dados.forceUsoProtocoloNaoAberto);
    cwfatdabreprot.setForceUsoProtocoloNaoAberto(dados.forceUsoProtocoloNaoAberto);
    ULOG( "18. Passando..." );

    if ( cwfatdabreprot.AbrirProtocoloCondicional(dados.idAtendimentoProtocolo) )
    { // se abriu protocolo, copia o novo número
        ULOG( "Copiando protocolo [%s]",cwfatdabreprot.getIdAtendimentoProtocolo() );
        SAFE_STRNCPY(dados.idAtendimentoProtocolo,cwfatdabreprot.getIdAtendimentoProtocolo());
        ULOG("idAtendimentoProtocolo gerado=%s",dados.idAtendimentoProtocolo);
    }
    //
    //=========================================================================
    // Parte II: Registra a Palitagem
    //=========================================================================
    //
    // Executa a gravação dos registros referente a abertura do atendimento.
    ULOG( "1. Pegando Data Atual" );
    char sbf[64];
    sbf[0] = 0x0;
    rcpc->dataAtual(sbf);
    //rcpc->dataAtual(dados.dataAtual);
    strcpy(dados.dataAtual,sbf);
    ULOG( "2. Pegou Data Atual [%s]", dados.dataAtual );

    dados.idUFOperadora = 
        rcpc->buscarRegional(dados.cdAreaRegistro>0?dados.cdAreaRegistro:11);
    
    ULOG( "3." );
    
    gravaAtendimento();

    ULOG( "3." );
    gravaAndamento();

    ULOG( "4." );
    gravaAtendimentoConta();

    ULOG( "5." );
    gravaAtendimentoOrigem();

    ULOG( "6." );
    gravaAtendimentoContato();

    ULOG( "7." );
    gravaAtendimentoContatoComunic();

    ULOG( "8." );
    gravaAtendimentoPessoa();
    rcpc->gravarAtendimentoLinhas(dados.idAtendimento,&dados.atendimentoLinhas);
    gravaLinhaTelefonicaAtendimento();

    cWfAtdAlterProt cwfatdalterprot;
    cwfatdalterprot.IncrementarQuantidades(dados.idAtendimentoProtocolo,"1","0",dados.idUsuarioBKOSZ);

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

    if ( dados.idTerminal > 0 )
    {
        rcpc->registraTerminalVOL(&dados.idAtendimento, &dados.idTerminal);
    }

    st_FchImediatoContato fechamento;
    memset( &fechamento, 0, sizeof(fechamento) );

    fechamento.idAtendimento            = dados.idAtendimento;
    fechamento.idAtividade              = FECHAMENTO_IMEDIATO_FI;
    fechamento.idAgrupamentoEstadoTpProc= dados.idAgrupamentoEstadoTpProc;
    fechamento.idGrupoAbertura          = dados.idGrupoAbertura;
    fechamento.observacaoFechamento     = dados.observacaoFechamento;
    fechamento.idBaixa                  = dados.idBaixa;
    fechamento.idUsuarioBKO             = dados.idUsuarioBKO;
    cFchImediatoContatoPC fic( &fechamento );
    fechamento.idBaixaMensagem          = fic.obtemBaixaMensagem( &dados.idBaixa
                                                        ,&dados.idMensagemBaixa );
    cFchImediatoContato fi(&fechamento,&xmlDpr);

    fi.proximoAgrupamento = dados.idAgrupamentoEstadoTpProcFt;

    fi.registra();

    // Palitagem exclusiva não irá registrar ação de DPR
    // registrarAcaoDPR();

    // Alimenta base de dados de pré-processamento do relatório de palitagem
    atualizarPalitagem();

    // Altera info na tabela de SMS
    rcpc->alterarFilaSmsProtocolo(&dados);
    if ( nrProtocoloPrm )
    {
        strcpy( nrProtocoloPrm,dados.idAtendimentoProtocolo );
    }

    if ( idAtendimentoPrm )
    {
        *idAtendimentoPrm = dados.idAtendimento;
    }
    ULOG("nrProtocolo gerado=[%s]",nrProtocoloPrm);

    ULOG_END("void cRegPalitagem::registra(char *nrProtocoloPrm,int *idAtendimentoPrm)");
}

//=========================================================================
//  Registra todos os dados do atendimento.
//=========================================================================
void cRegPalitagem::registra()
{
    ULOG_START("void cRegPalitagem::registra()");
    
    char nrProtocolo[39];
    long idAtendimento;
    registra(nrProtocolo,&idAtendimento);

    ULOG_END("void cRegPalitagem::registra()");
}

//=========================================================================
//  Registra os dados de atendimento.
//=========================================================================
void cRegPalitagem::gravaAtendimento() 
{
    memset( &m_stDadosAtendimento, 0,sizeof(m_stDadosAtendimento) );
    memset( &m_vlDadosAtendimento,-1,sizeof(m_vlDadosAtendimento) );

    SAFE_STRNCPY(m_stDadosAtendimento.dtAbertura,         dados.dataAtual);
    SAFE_STRNCPY(m_stDadosAtendimento.dtUltimaAlteracao,  dados.dataAtual);
    SAFE_STRNCPY(m_stDadosAtendimento.dtPrazoFinalInterno,dados.dataAtual/*dados.dataFechamento*/);
    SAFE_STRNCPY(m_stDadosAtendimento.dtPrazoFinalAnatel, dados.dataAtual/*dados.dataANATEL*/);
    //SAFE_STRNCPY(m_stDadosAtendimento.dtPrazoFinalCPrevio,dados.dataCPrevio);
    SAFE_STRNCPY(m_stDadosAtendimento.vlPesoAtendimento,  dados.peso);
    SAFE_STRNCPY(m_stDadosAtendimento.idAtendimentoProtocolo,dados.idAtendimentoProtocolo);

    // Dados de portabilidade
    if ( dados.sgTipoPortabilidade[0] )
    {
        SAFE_STRNCPY(m_stDadosAtendimento.sgTipoPortabilidade,dados.sgTipoPortabilidade);
        m_vlDadosAtendimento.sgTipoPortabilidade = 1;

        if ( dados.sgOperadoraSolicitante && *dados.sgOperadoraSolicitante )
        {
            SAFE_STRNCPY(m_stDadosAtendimento.sgOperadoraSolicitante,dados.sgOperadoraSolicitante);
            m_vlDadosAtendimento.sgOperadoraSolicitante = 1;
        }

        if ( dados.nrProtocoloPortabilidade && *dados.nrProtocoloPortabilidade )
        {
            SAFE_STRNCPY(m_stDadosAtendimento.nrProtocoloPortabilidade,dados.nrProtocoloPortabilidade);
            m_vlDadosAtendimento.nrProtocoloPortabilidade = 1;
        }

        if ( dados.dtJanelaPortout && *dados.dtJanelaPortout )
        {
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
    m_stDadosAtendimento.idTipoRetornoContato      = TP_RET_COM_RET_GRP_BKO;
    m_stDadosAtendimento.idUsuarioAlteracao        = dados.idUsuarioBKO;
    m_stDadosAtendimento.nrNivel                   = 0;
    m_stDadosAtendimento.idFase                    = TRATAMENTO;
    m_stDadosAtendimento.idAgrupamentoEstadoTpProc = dados.idAgrupamentoEstadoTpProc;

    if ( dados.idPessoaConta > 0 )
    {
        m_stDadosAtendimento.idPessoaConta         = dados.idPessoaConta;
        m_vlDadosAtendimento.idPessoaConta         = 1;
    }

    if ( dados.cdAreaRegistro > 0 )
    {
        m_stDadosAtendimento.cdAreaRegistro        = dados.cdAreaRegistro;
        m_vlDadosAtendimento.cdAreaRegistro        = 1;
    }

    if ( dados.idLinhaTelefonica > 0 )
    {
        m_stDadosAtendimento.idLinhaTelefonica     = dados.idLinhaTelefonica;
        m_vlDadosAtendimento.idLinhaTelefonica     = 1;
    }

    if ( dados.idPessoaLinhaHistorico > 0 )
    {
        m_stDadosAtendimento.idPessoaLinhaHistorico= dados.idPessoaLinhaHistorico;
        m_vlDadosAtendimento.idPessoaLinhaHistorico= 1;
    }

    if ( dados.idTipoLinha > 0 )
    {
        m_stDadosAtendimento.idTipoLinha           = dados.idTipoLinha;
        m_vlDadosAtendimento.idTipoLinha           = 1;
    }

    if ( dados.idEstadoLinha > 0 )
    {
        m_stDadosAtendimento.idEstadoLinha         = dados.idEstadoLinha;
        m_vlDadosAtendimento.idEstadoLinha         = 1;
    }

    if ( dados.idUFOperadora > 0 )
    {
        m_stDadosAtendimento.idUFOperadora         = dados.idUFOperadora;
        m_vlDadosAtendimento.idUFOperadora         = 1;
    }

    m_stDadosAtendimento.idTipoAbertura            = 
            dados.atendimentoPorConta ? ABERTURA_POR_CONTA : ABERTURA_POR_LINHA;

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

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimento atd;

    dados.idAtendimento = m_stDadosAtendimento.idAtendimento = 
            atd.incluir(&m_stDadosAtendimento,&m_vlDadosAtendimento,&xmlDpr);
    m_vlDadosAtendimento.idAtendimento = 1;
    // Se tiver observação então persiste.
    if ( dados.observacao && *dados.observacao )
    {
        gravaAtendimentoObservacao();
    }
}

//=========================================================================
//  Registra os dados de andamento do atendimento.
//=========================================================================
void cRegPalitagem::gravaAndamento() 
{
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
}


//=========================================================================
//  Insere observação do processo sendo criado
//=========================================================================
void cRegPalitagem::gravaAtendimentoObservacao()
{
    cWFAtendimentoObservacao cwfatendimentoobservacao;

    st_AtendimentoObservacao m_stDados;
    st_vlAtendimentoObservacao m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento      = dados.idAtendimento;
    m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
    m_stDados.pdsObservacao      = dados.observacao;

    m_vlDados.idAtendimento      = 1;
    m_vlDados.idUsuarioAlteracao = 1;
    m_vlDados.dsObservacao       = 1;

    cwfatendimentoobservacao.incluir(&m_stDados,&m_vlDados,&xmlDpr);
}

//=========================================================================
//  Registra os dados da conta associada ao usuário.
//=========================================================================
void cRegPalitagem::gravaAtendimentoConta()
{
    if (dados.idConta > 0 )
    {
        // Verifica a conta
        if ( rcpc->obtemDadosConta(&dados.idConta,dados.cdConta,dados.cdDigitoConta) )
        { // se encontrou dados da conta, associa ao processo
            st_AtendimentoConta     m_stDados;
            st_vlAtendimentoConta   m_vlDados;
    
            memset(&m_stDados,0,sizeof(m_stDados));
            memset(&m_vlDados,-1,sizeof(m_vlDados));
    
            m_stDados.idAtendimento = dados.idAtendimento;

            SAFE_STRNCPY(m_stDados.cdConta,dados.cdConta);
            m_stDados.cdDigitoConta = atoi(dados.cdDigitoConta);
            m_stDados.idUsuarioAlteracao = dados.idUsuarioBKO;
            SAFE_STRNCPY(m_stDados.dtUltimaAlteracao,dados.dataAtual);

            m_vlDados.idAtendimento         = 1;
            m_vlDados.cdConta               = 1;
            m_vlDados.cdDigitoConta         = 1;
            m_vlDados.idUsuarioAlteracao    = 1;
            m_vlDados.dtUltimaAlteracao     = 1;

            // Executa a chamada de inclusão do registro.
            XMLGen retorno;
            cWFAtendimentoConta ac(&m_stDados, &m_vlDados, &retorno);

            ac.incluir();
        }
    }
}

//=========================================================================
//  Registra os dados do Atendimento de Origem do Atendimento criado no processo.
//=========================================================================
void cRegPalitagem::gravaAtendimentoOrigem()
{
    if (dados.idAtendimentoOrigem > 0 )
    {
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
    }
}

//=========================================================================
//  Registra o dado de contato do cliente.
//=========================================================================
void cRegPalitagem::gravaAtendimentoContato()
{
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
}

//=========================================================================
//  Registra os dados do tipo de retorno do contato.
//=========================================================================
void cRegPalitagem::gravaAtendimentoContatoComunic()
{
    st_AtendimentoContatoComunic    m_stDados;
    st_vlAtendimentoContatoComunic  m_vlDados;
    AtendimentoRetorno* ar; 
    XMLGen retorno;

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

        // Executa a chamada de inclusão do registro.
        cWFAtendimentoContatoComunic ac(&m_stDados, &m_vlDados, &retorno);

        ac.incluir(&xmlDpr); //ATENDIMENTO.ATENDIMENTOCONTATOCOMUNIC
    }
}

//=========================================================================
//  Registra os dados da pessoa que abriu o atendimento.
//=========================================================================
void cRegPalitagem::gravaAtendimentoPessoa()
{
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
        m_stDadosPessoa.idPessoaDePara = dados.idPessoaDeParaCliente;
        m_stDadosPessoa.idTipoRelacionamento = 
            dados.idTipoRelacionamento = TIPO_RELA_C_CLIENTE;

        cWFAtendimentoPessoa ap(&m_stDadosPessoa, &m_vlDadosPessoa, &retorno);
        ap.incluir(&xmlDpr);

        rcpc->AtualizarDadosPessoaDeParaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
        rcpc->AtualizarDadosPessoaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);

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

        rcpc->AtualizarDadosPessoaDeParaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
        rcpc->AtualizarDadosPessoaDPR(m_stDadosPessoa.idPessoaDePara,&xmlDpr);
    }
}

//=========================================================================
//  Grava os dados da linha telefonica.(só atualiza DPR)
//=========================================================================
void cRegPalitagem::gravaLinhaTelefonicaAtendimento()
{
    if ( dados.idPessoaLinhaHistorico && dados.idEstadoLinha )
    {
        memset(&m_stDadosLinha,0,sizeof(m_stDadosLinha));
        memset(&m_vlDadosLinha,-1,sizeof(m_vlDadosLinha));

        m_stDadosLinha.idAtendimento = dados.idAtendimento;
        m_vlDadosLinha.idAtendimento = 1;

        m_stDadosLinha.idPessoaLinhaHistorico = dados.idPessoaLinhaHistorico;
        m_vlDadosLinha.idPessoaLinhaHistorico = 1;

        m_stDadosLinha.idEstadoLinha = dados.idEstadoLinha;
        m_vlDadosLinha.idEstadoLinha = 1;

        cWFAtendimentoLinha cwfatendimentolinha;
        cwfatendimentolinha.incluir(&xmlDpr); // só atualiza dados de DPR

        // ==> SM324--DPR--DEZ/2006--Cassio
        rcpc->AtualizarDadosPessoaLinhaHistorico(dados.idPessoaLinhaHistorico,&xmlDpr);
        // <== SM324--DPR--DEZ/2006--Cassio
    }
}

//=========================================================================
//  Obtem a data de fechamento do atendimento de acordo com as regras de priorização.
//=========================================================================
// void cRegPalitagem::obtemDataFechamento(double horas,char *dataFechamento,int incremento)
// {
//     cWFAtendimento atd;
// 
//     /* Incidência 2491 - WR-RS
//        Reabertura de processos deve considerar a data corrente
//     if (dados.idTipoReaberturaProcesso == 2)
//         rcpc->proCDataFechamento(dados.idAtendimentoOrigem, dataFechamento);
//     else
//     */
// 
//     atd.diasUteis(dados.nrTelefone,horas,incremento,dataFechamento);
// }

//=========================================================================
//  Obtem a data de fechamento usando como base o prazo estabelecido pela ANATEL.
//=========================================================================
// void cRegPalitagem::obtemDataFechamentoAnatel(char *dataFechamentoAnatel)
// {
//     cWFAtendimento atd;
//     int incremento = 24;
//     double prazo = (double) rcpc->obtemPrazoANATEL();
// 
//     /* Incidência 2491 - WR-RS
//        Reabertura de processos deve considerar a data corrente
//     if (dados.idTipoReaberturaProcesso == 2)
//         rcpc->proCDataFechamentoAnatel(dados.idAtendimentoOrigem, dataFechamentoAnatel);
//     else
//     */
// 
//     atd.diasUteis(dados.nrTelefone,prazo,incremento,dataFechamentoAnatel);
// }

//=========================================================================
// Registra todo o rastreio que foi feito para o DPR
//=========================================================================
void cRegPalitagem::registrarAcaoDPR()
{
    if ( dados.idAgrupamentoEstadoTpProc >= INI_AGRPTPPROC_TECNICO 
        && dados.idAgrupamentoEstadoTpProc <= FIM_AGRPTPPROC_TECNICO )
    {
        cWFAtdGerarXMLDPR cwfatdgerarxmldpr;

        char idAtendimentoStr[32];

        sprintf(idAtendimentoStr,"%lu",dados.idAtendimento);
        xmlDpr.idAtendimento = idAtendimentoStr;

        xmlDpr.idUser = dados.idUsuarioBKO;
        xmlDpr.nomeServico = "REGPALITAGEM";
        //xmlDpr.idContato = idContato;
        xmlDpr.idAtendimento = idAtendimentoStr;

        cwfatdgerarxmldpr.persistirDadosDPRContatoTecnico(&xmlDpr);
    }
}

//=========================================================================
// Atualiza relatórios de palitagem
//=========================================================================
void cRegPalitagem::atualizarPalitagem()
{
#ifndef WIN32
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

        inputMessage = new TuxMessage();
        inputMessage->setService("PALITAGE");
        inputMessage->setUser(dados.idUsuarioBKOSZ);
        inputMessage->setMessageBody(pclXmlGen);

        string strMsgIn = 
            "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
                "<msg>"
                    "<msgHdr>"
                        "<user>"+(string)dados.idUsuarioBKOSZ+"</user>"
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

#else // se estiver em ambiente windows, faz o insert sincrono 
    proCAtualizarAtdPalitagem(dados.idUFOperadora,dados.idContato,1
                             ,dados.idUsuarioBKO,1,dados.idGrupoAbertura,1
                             ,dados.idUsuarioBKO,1);
#endif // WIN32
}

//=========================================================================
// Parse do XML de entrada
//=========================================================================
void cRegPalitagem::carregaDados(DOMNode*dnode)
{
    char *p;
    DOMNode* dom;

    if (p=walkTree( dnode, "idContato", 0 ),p)
    {
        int p0 = atoi(p);
        
        if ( p0 )
        {
            dados.idContato = atoi(p);
        }

        XMLString::release(&p);
    }
    else
    {
        dados.idContato = -99;
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

    dados.idGrupoAbertura = -99;
    if (p=walkTree( dnode, "idGrupoAbertura", 0 ),p)
    {
        dados.idGrupoAbertura = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idTerminal", 0 ),p)
    {
        dados.idTerminal = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idAtendimentoOrigem", 0 ),p)
    {
        dados.idAtendimentoOrigem = atol(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idTipoReaberturaProcesso", 0 ),p)
    {
        dados.idTipoReaberturaProcesso  = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idBaixa", 0 ),p)
    {
        dados.idBaixa = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idMensagemBaixa", 0 ),p)
    {
        dados.idMensagemBaixa = atoi(p);
        XMLString::release(&p);
    }

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

    // Verifica se esta abrindo por conta ou linha (feature do CRI)
    dom = walkDOM(dnode, "LinhaPalitagemVO");

    if (p=walkTree( dnode, "idTipoRelacionamento", 0 ),p)
    {
        dados.idTipoRelacionamento = atoi(p);
        XMLString::release(&p);
    }
    else
    {
        dados.idTipoRelacionamento = TIPO_RELA_R_CONS_RELACIONAMENTO;
    }

    if (p=walkTree( dnode, "idTipoLinha", 0 ),p)
    {
        dados.idTipoLinha = atoi(p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idEstadoLinha", 0 ),p)
    {
        dados.idEstadoLinha = atoi(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(dom,"idPessoaLinhaHistorico", 0 ),p)
    {
        dados.idPessoaLinhaHistorico = atol(p);
        XMLString::release(&p);
    }

    if ( p=walkTree(dom,"idLinhaTelefonica", 0 ),p)
    {
        dados.idLinhaTelefonica = atoi(p);
        XMLString::release(&p);
    }

    if ( dados.idPessoaLinhaHistorico==0 && dados.idLinhaTelefonica==0 )
    {
        dados.atendimentoPorConta = true;
    }
    else
    {
        dados.atendimentoPorConta = false;
    }

    dom = walkDOM( dnode, "PessoaClientePalitagemVO");

    if (p=walkTree( dnode, "idConta", 0 ),p)
    {
        dados.idConta = atoi(p);
        XMLString::release(&p);
    }

    if ( p =walkTree( dom, "idPessoaDePara", 0 ),p )
    {
        dados.idPessoaDeParaCliente = atoi( p );
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "idTipoPessoa", 0 ),p)
    {
        dados.idTipoPessoa = atoi(p);
        XMLString::release(&p);
    }

    dom = walkDOM( dnode, "PessoaNaoClientePalitagemVO");
    if ( p=walkTree( dom, "idPessoaDePara", 0 ),p)
    {
        dados.idPessoaDeParaUsuario = atoi(p);
        XMLString::release(&p);
    }

    if ( 0 == dados.idPessoaDeParaUsuario )
    {
        dados.idPessoaDeParaUsuario = dados.idPessoaDeParaCliente;
    }

    // Obtem o id do tipo de retorno do VO.
    dom = walkDOM( dnode, "AtendimentoRetornoVO");
    // if ( p=walkTree( dom, "idTipoComunicacao", 0 ),p )
    // {
    //     dados.idTipoComunicacao = atoi( p );
    //     XMLString::release(&p);
    // }

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

    if (p=walkTree( dnode, "observacao", 0 ),p)
    {
        SAFE_STRNCPY(dados.observacao,p);
        XMLString::release(&p);
    }

    if (p=walkTree( dnode, "observacaoFechamento", 0 ),p)
    {
        SAFE_STRNCPY(dados.observacaoFechamento,p);
        XMLString::release(&p);
    }

    if ( dados.observacao[0] && dados.observacaoFechamento[0] &&
         stricmp(dados.observacao,dados.observacaoFechamento)==0 )
    { // se enviou 2 obs's iguais só grava uma
        dados.observacaoFechamento[0] = 0;
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

    dom = walkDOM( dnode, "DadosProtocoloVO");

    if (p=walkTree( dnode, "nrProtocolo", 0 ),p)
    {
        SAFE_STRNCPY(dados.idAtendimentoProtocolo,p);
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

    AtendimentoLinhas *atendimentoLinhas;
    char *cdConta;
    char *nrTelefone;
    DOMNode *registro;
    int contador=0;

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

} // carregaDados(...)

#ifndef WIN32
char* cRegPalitagem::remoteCall(char* nomeServico, char* sMsgIn, int lFlags)
{
    char*   sMsgOut = 0L;
    char*   bufferE = 0L;
    char*   bufferS = 0L;
    int     ret     = TUXFWRET_ERROR;
    long    snd_len = strlen(sMsgIn);
    long    rcv_len = TUXFW_MAX_MSG_LEN;
    //
    // Aloca um buffer de envio com o tamanho do XML de entrada
    if ((bufferE = (char *)tpalloc("STRING", NULL, snd_len+1)) == NULL)
    {
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }
    //
    // Aloca um buffer de recepcao com tamanho maximo possivel para retorno
    if ((bufferS = (char *)tpalloc("STRING", NULL, TUXFW_MAX_MSG_LEN)) == NULL)
    {
        tpfree(bufferE);
        throw new TuxException(ERR_TUX_TPALLOC_RET_CD, ERR_TUX_TPALLOC_RET_MSG);
    }
    //
    // Clona o buffer de entrada no buffer tuxedo de envio
    strcpy(bufferE, sMsgIn);
    if ( tpacall(nomeServico, (char*) bufferE, snd_len, TPNOTRAN|TPNOREPLY) == -1)
    {
        long errNo = tperrno;
        long urCode = tpurcode;
        if( errNo == TPESVCFAIL )
        {
            bufferS[rcv_len]='\0';
        }
        else 
        {
            tpfree(bufferE); 
            bufferE = 0L;

            tpfree(bufferS);
            bufferS = 0L;

            throw new TuxException(ERR_TUX_REMOTE_CALL_CD , ERR_TUX_REMOTE_CALL_MSG, nomeServico, errNo, urCode);
        }
    }
    else
    {
        bufferS[rcv_len]='\0';
    }

    if(bufferE) tpfree(bufferE); bufferE = 0L;
    if(bufferS) tpfree(bufferS); bufferS = 0L;

    return sMsgOut;
}

#endif
