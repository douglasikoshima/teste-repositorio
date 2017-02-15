/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.114.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/26 14:53:22 $
 **/

#ifndef __STWFATDGETDADOPRO_H_
#define __STWFATDGETDADOPRO_H_

struct DadosVOSaida
{
    char idAtendimentoProtocolo[39];
    char cdAreaRegistro[5];
    char nrTelefone[16];
    char idSistemaOrigem[39];
    char nmSistemaOrigem[256];
    char idPessoaDePara[39];
    char cdConta[101];
    char dtAbertura[32];
    char dtEncerramento[32];
    char qtProcessoAberto[5];
    char qtProcessoPendente[5];
    char idLinhaTelefonica[39];
    char idTipoAberturaProtocolo[2];
    char dsTipoAberturaProtocolo[256];
    char dsStatusProtocolo[256];
    char cdAreaRegistroSMS[5];
    char nrTelefoneSMS[16];

    DadosVOSaida() { memset(this,0,sizeof(DadosVOSaida)); }
};

#endif //#ifndef __STWFATDGETDADOPRO_H_
