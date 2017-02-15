

#include "../include/cLstPerfilGru.h"

DECLARE_TUXEDO_SERVICE(LSTPERFILGRU);

void implLSTPERFILGRU::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implLSTPERFILGRU::Execute()");

    unsigned long idUsuario = strtoul( getUser(),0,10 );
    
    cLstPerfilGru ob( idUsuario,dnode,xml_g );

    try
    {
        ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Operação concluída");
    
    ULOG_END("implLSTPERFILGRU::Execute()");
}

