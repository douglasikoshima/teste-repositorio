/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:59 $
 **/

#include "../include/cWFAtdPesqEstSubEx.h"

#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(ATDPSQESTSUBEX);

void implATDPSQESTSUBEX::Execute( DOMNode * dnode, XMLGen * xml_g ) 
{
    ULOG_START("implATDPSQESTSUBEX::Execute()");

    cWFAtdPesqEstSubEx ob( dnode, xml_g );

    ob.Executar();

    setStatusCode("00I0000","Operação concluida");

    ULOG_END("implATDPSQESTSUBEX::Execute()");
}
