#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include "../include/cAtdPortHist.h"

DECLARE_TUXEDO_SERVICE( ATDPORTHIST );

void implATDPORTHIST::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    ULOG_START("implATDPORTHIST::Execute()");

    string codErr = "04I0000";
    string msgErr = "Concluído com Sucesso";
    char *user = getUser();

    try
    {
        cAtdPortHist oAtdPortHist( dnode, xml_g, user );

        oAtdPortHist.Executar();

    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        codErr = codErro;
        msgErr = ex->pMsg;

        ULOGE("ErroOracle: %d:%s",ex->eCode,ex->pMsg);

        delete ex;
    }
    catch (TuxException *ex)
    {
        codErr = ex->getCode();
        msgErr = ex->getMessage();

        ULOGE("Erro Tuxedo: %s:%s",codErr.c_str(),msgErr.c_str());

        delete ex;
    }
    catch(...)
    {
        codErr = "04E9999";
        msgErr = "Erro desconhecido";

        ULOGE("Erro : %s:%s",codErr.c_str(),msgErr.c_str());
    }

    setStatusCode((char*)codErr.c_str(),(char*)msgErr.c_str());

    ULOG_END("implATDPORTHIST::Execute()");
}
