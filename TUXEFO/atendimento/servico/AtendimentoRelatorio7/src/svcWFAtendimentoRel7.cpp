/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:42 $
 **/

#include "../include/cWFAtendimentoRel7.h"
#include "../include/MapaErros.h"
#include "../../../commons/SmallString.h"

DECLARE_TUXEDO_SERVICE(WFATDREL7);

void implWFATDREL7::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtendimentoRel7 ob(dnode,xml_g);

    try
    {
        ob.ObterRelatorio();
    }
    catch(...)
    {
        throw;
    }

    setStatusCode("00I0000","Operação concluida");
}
