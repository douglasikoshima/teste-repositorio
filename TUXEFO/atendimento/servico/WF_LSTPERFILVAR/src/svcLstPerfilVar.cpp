

#include "../include/cLstPerfilVar.h"

DECLARE_TUXEDO_SERVICE(LSTPERFILVAR);

void implLSTPERFILVAR::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implLSTPERFILVAR::Execute()");

    cLstPerfilVar ob( dnode,xml_g );

    try
    {
        ob.ListaPerfil();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Operação concluída");
    
    ULOG_END("implLSTPERFILVAR::Execute()");
}

