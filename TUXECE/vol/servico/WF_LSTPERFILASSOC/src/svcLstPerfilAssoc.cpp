

#include "../include/cLstPerfilAssoc.h"

DECLARE_TUXEDO_SERVICE(LSTPERFILASSOC);

void implLSTPERFILASSOC::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implLSTPERFILASSOC::Execute()");

    unsigned long idUsuario = strtoul( getUser(),0,10 );
    
    cLstPerfilAssoc ob( idUsuario,dnode,xml_g );

    try
    {
        ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Operação concluída");
    
    ULOG_END("implLSTPERFILASSOC::Execute()");
}

