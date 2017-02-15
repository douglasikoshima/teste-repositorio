/**
 * 
 * @modulo  Atendimento
 * @usecase Protocolo
 * @author  Cassio Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:42 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

#include "../include/cWfAtdFechaProt.h"
#include "../../../commons/cWfAtdProtocoloException.h"

DECLARE_TUXEDO_SERVICE(ATDFECHAPROT);

void implATDFECHAPROT::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    string codSaida = "04I0000";
    string msgSaida = "Executado com Sucesso";

    string codErr = "";
    string msgErr = "";

    cWfAtdFechaProt atdfechaprot(dnode,xml_g);

    try
    {
        atdfechaprot.setUser(getUser());

        atdfechaprot.Executar();
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

    xml_g->createTag("FechaProtocoloOutTO");
    xml_g->addProp("xmlns","atendimento.fo.vivo.com.br/vo");
        xml_g->addItem("cdError",codErr.size()==0?"0":(char*)codErr.c_str()+3);
        xml_g->addItem("msgError",(char*)msgErr.c_str());
        //xml_g->addItem("nrProtocolo",atdfechaprot.getIdAtendimentoProtocolo());
    xml_g->closeTag();

    setStatusCode((char*)codSaida.c_str(),(char*)msgSaida.c_str());
}
