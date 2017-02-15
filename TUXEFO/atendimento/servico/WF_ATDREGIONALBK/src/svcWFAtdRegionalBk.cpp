/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:00 $
 **/

#include "../include/cWFAtdRegionalBk.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDREGIONALBK);

void implATDREGIONALBK::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDREGIONALBK::Execute()");
    char *msgErro = 0;
    char *codErro = 0;

    cWFAtdRegionalBk ob(dnode,xml_g);

    

    if ( ob.Executar() )
    {
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        msgErro = ob.ObterTamMsgErro() ? ob.ObterMsgErro() : "Falha na execução";
        codErro = ob.ObterTamCodErro() ? ob.ObterCodErro() : "00E0000";

        ULOGE(msgErro);

        setStatusCode(codErro,msgErro);
    }
    ULOG_END("implATDREGIONALBK::Execute()");
}
