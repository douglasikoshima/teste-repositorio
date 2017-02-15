/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:44 $
 **/

#include "../include/cWFAtendimentoRelIndResumo.h"

DECLARE_TUXEDO_SERVICE(WFATDRELINDRAC);

void implWFATDRELINDRAC::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtdRelRes ob(dnode,xml_g);

    if ( ob.Executar() )
    {
	    setStatusCode("00I0000","Operação concluida");
    }
    else
    {
	    setStatusCode("00E0000","Falha na execução");
    }
}

