/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:27 $
 **/

#include "../include/cWFAtdFechDocTec.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDFECHDOCTEC);

void implATDFECHDOCTEC::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDFECHDOCTEC::Execute()");
    char *msgErro = 0;
    char *codErro = 0;

    cWFAtdFechDocTec ob(dnode,xml_g);    

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
    ULOG_END("implATDFECHDOCTEC::Execute()");
}
