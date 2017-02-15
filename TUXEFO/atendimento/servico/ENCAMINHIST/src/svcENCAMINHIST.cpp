

#include "../include/cEncaminHist.h"

DECLARE_TUXEDO_SERVICE(ENCAMINHIST);

void implENCAMINHIST::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implENCAMINHIST::Execute()");
    
    int retorno;

    try
    {
        cEncaminHist ob( dnode,xml_g );
        retorno = ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Opera��o conclu�da");
    
    ULOG_END("implENCAMINHIST::Execute()");
}

