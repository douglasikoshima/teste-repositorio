/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:19 $
 **/


#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFTRATCRI);

void implWFTRATCRI::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    setStatusCode("04I0000","Processo concluído.");
}
