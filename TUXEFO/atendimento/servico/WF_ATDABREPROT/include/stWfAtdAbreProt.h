/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2010/12/16 18:31:34 $
 **/

#ifndef __STWFATDABREPROT_H_
#define __STWFATDABREPROT_H_

#include "../../../commons/definesAtendimento.h"

struct st_DadosEntrada
{
    char idAtendimentoProtocolo[128];
    char cdAreaRegistro[32];
    char nrTelefone[64];
    char idSistemaOrigem[128];
    char idPessoaDePara[128];
    char cdConta[512];
    char dtAbertura[128];
    char dtEncerramento[128];
    char qtProcessoAberto[9];
    char qtProcessoPendente[9];
    char idLinhaTelefonica[128];
    char cdAreaRegistroSMS[9];
    char nrTelefoneSMS[16];
    char dtEnvioSMS[64];
    char dsErroSMS[256];
    char idTipoAberturaProtocolo[9];
    char idUsuarioAlteracao[64];
    char dtUltimaAlteracao[64];
    int forceUsoProtocoloNaoAberto;
};

struct st_StatusEntrada
{
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
    short forceUsoProtocoloNaoAberto;
};

#endif //#ifndef __STWFATDABREPROT_H_
