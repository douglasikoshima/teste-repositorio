/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:41 $
 **/

#include "../include/cWFAtdPsqGrpUtSq.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDPSQGRPUTSQ);

void implATDPSQGRPUTSQ::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDPSQGRPUTSQ::Execute()");
    
    char *msgErro = 0;
    char *codErro = 0;

    cWFAtdPsqGrpUtSq ob(dnode,xml_g);

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
    
    ULOG_END("implATDPSQGRPUTSQ::Execute()");
}
