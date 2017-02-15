/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:28 $
 **/

#include <tuxfw.h>

DECLARE_TUXEDO_SERVICE(WFAtendimentoFila);

void implWFAtendimentoFila::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    setStatusCode("00I0000","Operação concluida");
}
