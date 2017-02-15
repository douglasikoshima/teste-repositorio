/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:06 $
 **/

#include "../include/cWFAtExcFisica.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDEXCFISICA);

void implATDEXCFISICA::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDEXCFISICA::Execute()");
    char *msgErro = 0;
    char *codErro = 0;

    cWFAtExcFisica ob(dnode,xml_g);

    

    if ( ob.Executar() )
    {
        setStatusCode("07I0000","Operação concluida");
    }
    else
    {
        msgErro = ob.ObterTamMsgErro() ? ob.ObterMsgErro() : "Falha na execução";
        codErro = ob.ObterTamCodErro() ? ob.ObterCodErro() : "07E0000";

        ULOGE(msgErro);

        setStatusCode(codErro,msgErro);
    }
    ULOG_END("implATDEXCFISICA::Execute()");
}
