/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:46 $
 **/

#include "../../WF_ATDOBTCAMPUSU/include/cWFAtdObtCampUsu.h"
#include "../../IBWFPSUsuario/include/cWFIBWFPSUsuario.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(CNSINFUSU);

void implCNSINFUSU::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implCNSINFUSU::Execute()");
    
    bool resultado = true;
    char *msgErro;
    char *codErro;
    char *user;
    int idUsuario = -1;
 //   char *uuid;

    cIBWFPSUsuario  obUsu(dnode,xml_g);

    cWFAtdObtCampUsu ob(dnode,xml_g);

    

    if ( user=getUser(),!user ) 
    {
        ob.SetarErro("00E0000","Valor de 'user' é obrigatório.");
        resultado = false;
    }

    /*
    if ( uuid=getUUID(),!uuid ) 
    {
        ob.SetarErro("00E0000","Valor de 'userID' é obrigatório.");
        resultado = false;
    }
    */

    if ( resultado )
    {
        ob.setarIdUsuario(atoi(user));
        idUsuario = atoi(user);

/*
        ob.setarUUID(atoi(uuid));
*/
        ob.setarUUID( 0 );

        ULOG("user=%d",ob.obterIdUsuario());

         xml_g->createTag("RWFInboxUserVO");
         xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
            obUsu.ObtemUsuario( idUsuario,xml_g );
            resultado=ob.RetornoCTI( xml_g );
         xml_g->closeTag();

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
    ULOG_END("implCNSINFUSU::Execute()");
}
