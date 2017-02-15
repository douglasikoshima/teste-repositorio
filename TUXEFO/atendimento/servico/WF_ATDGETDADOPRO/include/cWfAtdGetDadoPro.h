/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:19 $
 **/

#include "cWfAtdGetDadoProPC.h"

class cWfAtdGetDadoPro : public TuxBasicSvc
{
    public:
        DOMNode *entrada;
        XMLGen *saida;
        cWfAtdGetDadoProPC cwfatdgetdadopropc;

        DadosVOSaida *dadosVOSaida;

    public:
        cWfAtdGetDadoPro(DOMNode *dnode,XMLGen *xml_g) {saida = xml_g;entrada = dnode;}
        ~cWfAtdGetDadoPro() {}

    public:
        void Executar();

        char *getIdAtendimentoProtocolo() { return dadosVOSaida->idAtendimentoProtocolo; }
        char *getCdAreaRegistro() { return dadosVOSaida->cdAreaRegistro; }
        char *getNrTelefone() { return dadosVOSaida->nrTelefone; }
        char *getCdAreaRegistroSMS() { return dadosVOSaida->cdAreaRegistroSMS; }
        char *getNrTelefoneSMS() { return dadosVOSaida->nrTelefoneSMS; }
        char *getIdSistemaOrigem() { return dadosVOSaida->idSistemaOrigem; }
        char *getIdPessoaDePara() { return dadosVOSaida->idPessoaDePara; }
        char *getCdConta() { return dadosVOSaida->cdConta; }
        char *getDtAbertura() { return dadosVOSaida->dtAbertura; }
        char *getDtEncerramento() { return dadosVOSaida->dtEncerramento; }
        char *getQtProcessoAberto() { return dadosVOSaida->qtProcessoAberto; }
        char *getQtProcessoPendente() { return dadosVOSaida->qtProcessoPendente; }
        char *getIdLinhaTelefonica() { return dadosVOSaida->idLinhaTelefonica; }
        char *getIdTipoAberturaProtocolo() { return dadosVOSaida->idTipoAberturaProtocolo; }
        char *getDsTipoAberturaProtocolo() { return dadosVOSaida->dsTipoAberturaProtocolo; }
        char *getDsStatusProtocolo() { return dadosVOSaida->dsStatusProtocolo; }
};
