/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:22 $
 **/

#include "../include/cWFAtendimentoRel4.h"

DECLARE_TUXEDO_SERVICE(WFATDREL4);

void implWFATDREL4::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtdRel4 ob(dnode,xml_g);

    if ( ob.Executar() )
    {
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        setStatusCode("00E0000","Falha na execução");
    }
}

