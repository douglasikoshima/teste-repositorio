/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#include "../include/cWFAtdPesqEstSub.h"

#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(ATDPESQESTSUB);

void implATDPESQESTSUB::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDPESQESTSUB::Execute()");

    cWFAtdPesqEstSub ob(dnode,xml_g);

    ob.Executar();

    setStatusCode("00I0000","Operação concluida");

    ULOG_END("implATDPESQESTSUB::Execute()");
}
