/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:03 $
 **/

#include "../include/cWFIBWFPSUsuario.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(IBWFPSUSUARIO);

void implIBWFPSUSUARIO::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implIBWFPSUSUARIO::Execute()");
    cIBWFPSUsuario ob(dnode,xml_g);

    if ( ob.Executar() ) 
    {
	    setStatusCode("00E0000","Falha na execução");
    }
    else
    {
	    setStatusCode("00I0000","Operação concluida");
    }
    
    ULOG_END("implIBWFPSUSUARIO::Execute()");
}

