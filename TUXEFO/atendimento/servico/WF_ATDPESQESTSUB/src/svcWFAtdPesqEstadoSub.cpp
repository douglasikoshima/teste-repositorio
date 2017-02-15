/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:01 $
 **/

#include "../include/cWFAtdPesqEstadoSub.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDPESQESTSUB);

void implATDPESQESTSUB::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_END("implATDPESQESTSUB::Execute()");
    char *msgErro = 0;
    char *codErro = 0;

    cWFAtdPesqEstadoSub ob(dnode,xml_g);

    

    if ( ob.Executar(&codErro,&msgErro) )
    {
        setStatusCode("00I0000","Opera��o concluida");
    }
    else
    {
        if ( !msgErro ) msgErro = "Falha na execu��o";
        if ( !codErro ) codErro = "00E0000";

        ULOGE(msgErro);

        setStatusCode(codErro,msgErro);
    }
    
    ULOG_END("implATDPESQESTSUB::Execute()");
}