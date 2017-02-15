

#include "../include/cCfgPerfilGruCon.h"

DECLARE_TUXEDO_SERVICE(CFGPRFGRUCON);

void implCFGPRFGRUCON::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implCFGPRFGRUCON::Execute()");
    unsigned long idUsuario = strtoul( getUser(),0,10 );
    
    cCfgPerfilGruCon ob( idUsuario,dnode,xml_g );

    try
    {
        ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Operação concluída");
    
    ULOG_END("implCFGPRFGRUCON::Execute()");
}

