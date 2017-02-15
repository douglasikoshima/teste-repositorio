/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:32:56 $
 **/

#include "../include/cWFAtFechMasPqUs.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATFECHMASPQUS);

void implATFECHMASPQUS::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATFECHMASPQUS::Execute()");
    
    char *msgErro = 0;
    char *codErro = 0;

    cWFAtFechMasPqUs cwfatfechmaspqus(dnode,xml_g);

    cwfatfechmaspqus.setarIdUsuario(getUser());

    if ( cwfatfechmaspqus.Executar() )
    {
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        msgErro = cwfatfechmaspqus.ObterTamMsgErro() ? cwfatfechmaspqus.ObterMsgErro() : "Falha na execução";
        codErro = cwfatfechmaspqus.ObterTamCodErro() ? cwfatfechmaspqus.ObterCodErro() : "00E0000";

        ULOGE(mensagemSimples(msgErro));

        setStatusCode(codErro,msgErro);
    }

    ULOG_END("implATFECHMASPQUS::Execute()");
}
