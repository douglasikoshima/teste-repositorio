/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/

#include "../include/cWFAtdLstComTTGr.h"
#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(ATDLSTCOMTTGR);

void implATDLSTCOMTTGR::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDLSTCOMTTGR::Execute()");
    char *usuario;

    cWFAtdLstComTTGr ob(dnode,xml_g);

    if (usuario=getUser(),usuario) 
    {
        ob.setarIdUsuario(atoi(usuario));

        ULOG("idUsuario=%d" ,ob.obterIdUsuario());
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
    }
    else
    {
        char *p = "Valor de 'user' é obrigatório.";
        ULOGE(p);
        setStatusCode("00E0000",p);
    }
    ULOG_START("implATDLSTCOMTTGR::Execute()");
}
