/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:50 $
 **/

#include "../include/cWFAtendimentoRel6ADet.h"

DECLARE_TUXEDO_SERVICE(WFATDREL6ADET);

void implWFATDREL6ADET::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtdRel6ADet ob(dnode,xml_g);

    if ( ob.Executar() )
    {
	    setStatusCode("00I0000","Opera��o concluida");
    }
    else
    {
	    setStatusCode("00E0000","Falha na execu��o");
    }
}

