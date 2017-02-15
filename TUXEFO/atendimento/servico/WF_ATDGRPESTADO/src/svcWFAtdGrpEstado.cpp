/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:01 $
 **/

#include "../include/cWFAtdGrpEstado.h"
#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(ATDGRPESTADO);

void implATDGRPESTADO::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDGRPESTADO::Execute()");
    bool resultado = true;
    char *user;

    cWFAtdGrpEstado ob(dnode,xml_g);

    
    if ( user=getUser(),!user ) 
    {
        ob.SetarErro("00E0000","Valor de 'user' � obrigat�rio.");
        resultado = false;
    }

    if ( resultado )
    {
        ob.setarIdUsuario(atoi(user));

        ULOG("user=%d",ob.obterIdUsuario());

        resultado=ob.Executar();
    }

    if ( resultado )
    {
        setStatusCode("00I0000","Opera��o concluida");
    }
    else
    {
        char *msgErro = ob.ObterTamMsgErro() ? ob.ObterMsgErro() : "Falha na execu��o";
        char *codErro = ob.ObterTamCodErro() ? ob.ObterCodErro() : "00E0000";

        ULOGE(msgErro);

        setStatusCode(codErro,msgErro);
    }

    ULOG_END("implATDGRPESTADO::Execute()");
}
