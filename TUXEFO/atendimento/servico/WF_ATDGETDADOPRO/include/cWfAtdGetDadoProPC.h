/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#ifndef __CWFATDGETDADOPROTPC_H__
#define __CWFATDGETDADOPROTPC_H__

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#include <tuxfw.h>

#include "stWfAtdGetDadoPro.h"
#include "../../../commons/definesAtendimento.h"

class cWfAtdGetDadoProPC
{
    //public:
        //TuxHelper tx;

    private:
        struct DadosVOSaida dadosVOSaida;

    public:
        cWfAtdGetDadoProPC() {}
        ~cWfAtdGetDadoProPC() {}

    public:
        bool proCObterDadosProtocolo(const char *idAtendimentoProtocolo);
        bool proCObterDadosProtocoloSMS(const char *idAtendimentoProtocolo);
        bool proCObterDadosProtocoloEmAtendimento(const char *cdAreaRegistro,
                                                  const char *nrTelefone,
                                                  const char *idSistemaOrigem);
        DadosVOSaida *getDadosAtendimentoProtocolo() { return &dadosVOSaida; }

        char *getIdAtendimentoProtocolo() { return dadosVOSaida.idAtendimentoProtocolo; }
        char *getCdAreaRegistro() { return dadosVOSaida.cdAreaRegistro; }
        char *getNrTelefone() { return dadosVOSaida.nrTelefone; }
        char *getCdAreaRegistroSMS() { return dadosVOSaida.cdAreaRegistroSMS; }
        char *getNrTelefoneSMS() { return dadosVOSaida.nrTelefoneSMS; }
        char *getIdSistemaOrigem() { return dadosVOSaida.idSistemaOrigem; }
        char *getNmSistemaOrigem() { return dadosVOSaida.nmSistemaOrigem; }
        char *getIdPessoaDePara() { return dadosVOSaida.idPessoaDePara; }
        char *getCdConta() { return dadosVOSaida.cdConta; }
        char *getDtAbertura() { return dadosVOSaida.dtAbertura; }
        char *getDtEncerramento() { return dadosVOSaida.dtEncerramento; }
        char *getQtProcessoAberto() { return dadosVOSaida.qtProcessoAberto; }
        char *getQtProcessoPendente() { return dadosVOSaida.qtProcessoPendente; }
        char *getIdLinhaTelefonica() { return dadosVOSaida.idLinhaTelefonica; }
        char *getIdTipoAberturaProtocolo() { return dadosVOSaida.idTipoAberturaProtocolo; }
        char *getDsTipoAberturaProtocolo() { return dadosVOSaida.dsTipoAberturaProtocolo; }
        char *getDsStatusProtocolo() { return dadosVOSaida.dsStatusProtocolo; }

        bool statusEmAtendimentoSimNao() { return strcmp(dadosVOSaida.dsStatusProtocolo,STATPROT_EM_ATENDIMENTO)==0?true:false; }

    private:
        void sql_error_WfAtdGetDadoProPC(sqlca *sqlca);

};

#endif // __CWFATDGETDADOPROTPC_H__
