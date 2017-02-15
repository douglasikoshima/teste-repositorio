/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.114.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/26 14:53:22 $
 **/

#ifndef __STWFATDALTERPROT_H_
#define __STWFATDALTERPROT_H_

#include "../../../commons/definesAtendimento.h"

#define OPERACAO_ALTERAR_DADOS  "alterar"
#define OPERACAO_ALTERAR_STATUS "altStatus"
#define OPERACAO_INCR_TOTABRPEN "incrTotAberPend"

struct st_DadosEntradaAltProt
{
    char operacao[32];
    char idAtendimentoProtocolo[39];
    char cdAreaRegistro[5];
    char nrTelefone[16];
    char idSistemaOrigem[39];
    char idPessoaDePara[39];
    char cdConta[101];
    char dtAbertura[32];
    char dtEncerramento[32];
    char qtProcessoAberto[5];
    char qtProcessoPendente[5];
    char idLinhaTelefonica[39];
    char cdAreaRegistroSMS[5];
    char nrTelefoneSMS[16];
    char dtEnvioSMS[32];
    char dsErroSMS[121];
    char idTipoAberturaProtocolo[2];
    char idUsuarioAlteracao[39];
    char dtUltimaAlteracao[32];
    char incAberto[5];
    char incPendente[5];
};

struct st_StatusEntradaAltProt
{
    short operacao;
    short idAtendimentoProtocolo;
    short cdAreaRegistro;
    short nrTelefone;
    short idSistemaOrigem;
    short idPessoaDePara;
    short cdConta;
    short dtAbertura;
    short dtEncerramento;
    short qtProcessoAberto;
    short qtProcessoPendente;
    short idLinhaTelefonica;
    short cdAreaRegistroSMS;
    short nrTelefoneSMS;
    short dtEnvioSMS;
    short dsErroSMS;
    short idTipoAberturaProtocolo;
    short idUsuarioAlteracao;
    short dtUltimaAlteracao;
    short incAberto;
    short incPendente;
};

#endif //#ifndef __STWFATDALTERPROT_H_
