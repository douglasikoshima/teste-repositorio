/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

#include "../include/cWfAtdGetDadoPro.h"
#include "../../../commons/cWfAtdProtocoloException.h"

DECLARE_TUXEDO_SERVICE(ATDGETDADOPRO);

void implATDGETDADOPRO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implATDGETDADOPRO::Execute()");

    string codSaida = "04I0000";
    string msgSaida = "Executado com Sucesso";

    string codErr = "";
    string msgErr = "";

    cWfAtdGetDadoPro cwfatdgetdadopro(dnode,xml_g);

    try
    {
        cwfatdgetdadopro.Executar();
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"04I%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        codErr = codErro;
        msgErr = ex->pMsg;

        codSaida = codErr;
        msgSaida = msgErr;

        ULOGE("ErroOracle: %d:%s",ex->eCode,ex->pMsg);

        delete ex;
    }
    catch (TuxException *ex)
    {
        codErr = ex->getCode();
        msgErr = ex->getMessage();

        codSaida = codErr;
        msgSaida = msgErr;

        ULOGE("Erro Tuxedo: %s:%s",codErr.c_str(),msgErr.c_str());

        delete ex;
    }
    catch(...)
    {
        codErr = "04I9999";
        msgErr = "Erro desconhecido";

        codSaida = codErr;
        msgSaida = msgErr;

        ULOGE("Erro: %s:%s",codErr.c_str(),msgErr.c_str());
    }

    xml_g->createTag("GetDadosProtocoloOutTO");
    xml_g->addProp("xmlns","atendimento.fo.vivo.com.br/vo");
        xml_g->addItem("cdError",codErr.size()==0?"0":(char*)codErr.c_str()+3);
        xml_g->addItem("msgError",(char*)msgErr.c_str());

        if ( codErr.size()==0 )
        {
            xml_g->addItem("nrProtocolo",cwfatdgetdadopro.getIdAtendimentoProtocolo());
            xml_g->addItem("idSistemaOrigem",cwfatdgetdadopro.getIdSistemaOrigem());
            xml_g->addItem("idPessoaDePara",cwfatdgetdadopro.getIdPessoaDePara());
            xml_g->addItem("cdConta",cwfatdgetdadopro.getCdConta());
            xml_g->addItem("idLinhaTelefonica",cwfatdgetdadopro.getIdLinhaTelefonica());
            xml_g->addItem("cdAreaRegistro",cwfatdgetdadopro.getCdAreaRegistro());
            xml_g->addItem("nrTelefone",cwfatdgetdadopro.getNrTelefone());
            xml_g->addItem("cdAreaRegistroSMS",cwfatdgetdadopro.getCdAreaRegistroSMS());
            xml_g->addItem("nrTelefoneSMS",cwfatdgetdadopro.getNrTelefoneSMS());
            xml_g->addItem("dtAbertura",cwfatdgetdadopro.getDtAbertura());
            xml_g->addItem("dtEncerramento",cwfatdgetdadopro.getDtEncerramento());
            xml_g->addItem("qtProcessoAberto",cwfatdgetdadopro.getQtProcessoAberto());
            xml_g->addItem("qtProcessoPendente",cwfatdgetdadopro.getQtProcessoPendente());
            xml_g->addItem("idTipoAberturaProtocolo",cwfatdgetdadopro.getIdTipoAberturaProtocolo());
            xml_g->addItem("dsTipoAberturaProtocolo",cwfatdgetdadopro.getDsTipoAberturaProtocolo());
            xml_g->addItem("dsStatusProtocolo",cwfatdgetdadopro.getDsStatusProtocolo());
        }

    xml_g->closeTag();

    setStatusCode((char*)codSaida.c_str(),(char*)msgSaida.c_str());

    ULOG_END("implATDGETDADOPRO::Execute()");
}
