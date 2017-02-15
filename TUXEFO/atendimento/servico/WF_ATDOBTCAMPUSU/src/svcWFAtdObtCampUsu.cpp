/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:52 $
 **/

#include "../include/cWFAtdObtCampUsu.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDOBTCAMPUSU);

void implATDOBTCAMPUSU::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDOBTCAMPUSU::Execute()");
    bool resultado = true;
    char *msgErro;
    char *codErro;
    char *user;
    char *uuid;

    cWFAtdObtCampUsu ob(dnode,xml_g);

    

    if ( user=getUser(),!user ) 
    {
        ob.SetarErro("00E0000","Valor de 'user' é obrigatório.");
        resultado = false;
    }

    if ( uuid=getUUID(),!uuid ) 
    {
        ob.SetarErro("00E0000","Valor de 'userID' é obrigatório.");
        resultado = false;
    }

    if ( resultado )
    {
        ob.setarIdUsuario(atoi(user));
        ob.setarUUID(atoi(uuid));

        ULOG("user=%d",ob.obterIdUsuario());

        ULOG("uuid=%d",ob.obterUUID());

        resultado=ob.Executar();
    }

    if ( resultado )
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
    ULOG_START("implATDOBTCAMPUSU::Execute()");
}
