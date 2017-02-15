/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:01 $
 **/

#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"

DECLARE_TUXEDO_SERVICE(WFOBTCAMPODIN);

void implWFOBTCAMPODIN::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START("implWFOBTCAMPODIN::Execute()");

    cWFAtendimentoFrm objAtdFrm( dnode,xml_g );
    objAtdFrm.obtemCampoDinamico();
	
    setStatusCode("04I0000","Obtencao de Campo Dinamico Concluido");
    
    ULOG_END("implWFOBTCAMPODIN::Execute()");
}
