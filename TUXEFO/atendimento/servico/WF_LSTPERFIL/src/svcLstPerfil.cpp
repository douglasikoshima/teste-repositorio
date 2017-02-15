

#include "../include/cLstPerfil.h"

DECLARE_TUXEDO_SERVICE(LSTPERFIL);

void implLSTPERFIL::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implLSTPERFIL::Execute()");
    cLstPerfil ob( dnode,xml_g );

    try
    {
        ob.Perfil();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Operação concluída");
    
    ULOG_END("implLSTPERFIL::Execute()");
}

