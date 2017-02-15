/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:43 $
 **/

#include "../include/cWFAtendimentoRel2.h"

DECLARE_TUXEDO_SERVICE(WFATDREL2);

void implWFATDREL2::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtdRel2 ob(dnode,xml_g);

    ob.setarNomeServico(getService());

    if ( ob.Executar() )
    {
	    setStatusCode("00I0000","Operação concluida");
    }
    else
    {
	    setStatusCode("00E0000","Falha na execução");
    }
}

