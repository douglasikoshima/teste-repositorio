/**
 * Servidor fora de uso, apenas para compila��o do servidor sWRKt09
 **/ 


#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(DUMMY);

void implDUMMY::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    setStatusCode("04I0000","Processo conclu�do.");
}


