

#include "../include/cLstGrupoAssoc.h"

DECLARE_TUXEDO_SERVICE(LSTGRUPOASSOC);

void implLSTGRUPOASSOC::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implLSTGRUPOASSOC::Execute()");
    
    unsigned long idUsuario = strtoul( getUser(),0,10 );
    
    cLstGrupoAssoc ob( idUsuario,dnode,xml_g );

    try
    {
        ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Opera��o conclu�da");
    
    ULOG_END("implLSTGRUPOASSOC::Execute()");
}

