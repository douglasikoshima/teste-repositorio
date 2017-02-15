/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/

#include "../include/cWFAtdObtReprese.h"

DECLARE_TUXEDO_SERVICE(ATDOBTREPRESE);

void implATDOBTREPRESE::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDOBTREPRESE::Execute()");
    
    cWFAtdObtReprese ob(dnode,xml_g);

    if ( ob.Executar() )
    {
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        char *msgErro = ob.ObterTamMsgErro() ? ob.ObterMsgErro() : "Falha na execução";
        char *codErro = ob.ObterTamCodErro() ? ob.ObterCodErro() : "00E0000";

        ULOGE(msgErro);

        setStatusCode(codErro,msgErro);
    }
    ULOG_END("implATDOBTREPRESE::Execute()");    
}
