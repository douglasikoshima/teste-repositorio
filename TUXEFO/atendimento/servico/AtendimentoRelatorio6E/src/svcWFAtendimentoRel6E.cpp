/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:07 $
 **/

#include "../include/cWFAtendimentoRel6E.h"

DECLARE_TUXEDO_SERVICE(WFATDREL6E);

void implWFATDREL6E::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtdRel6E ob(dnode,xml_g);

    if ( ob.Executar() )
    {
	    setStatusCode("00I0000","Opera��o concluida");
    }
    else
    {
	    setStatusCode("00E0000","Falha na execu��o");
    }
}

