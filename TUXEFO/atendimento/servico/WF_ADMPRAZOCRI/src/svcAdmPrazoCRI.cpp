

#include "../include/cAdmPrazoCRI.h"

DECLARE_TUXEDO_SERVICE(ADMPRAZOCRI);

void implADMPRAZOCRI::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implADMPRAZOCRI::Execute()"); 
    unsigned long idUsuario = strtoul( getUser(),0,10 );
    
    cAdmPrazoCRI ob( idUsuario,dnode,xml_g );

    try
    {
        ob.Processa();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("09I0000","Operação concluída");
    
    ULOG_END("implADMPRAZOCRI::Execute()"); 
}

