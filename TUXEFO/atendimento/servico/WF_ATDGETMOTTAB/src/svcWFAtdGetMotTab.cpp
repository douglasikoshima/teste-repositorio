/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:32 $
 **/

#include "../include/cWFAtdGetMotTab.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDGETMOTTAB);

void implATDGETMOTTAB::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    char *msgErro = 0;
    char *codErro = 0;
    char *usuario;

    ULOG_START( "implATDGETMOTTAB::Execute()" );
    
    cWFAtdGetMotTab ob(dnode,xml_g);

    if (usuario=getUser(),usuario) 
        ob.setarIdUsuario(atoi(usuario));


    if ( ob.executar(&codErro,&msgErro) )
    {
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        if ( !msgErro ) msgErro = "Falha na execução";
        if ( !codErro ) codErro = "00E0000";

        ULOGE(mensagemSimples(msgErro));

        setStatusCode(codErro,msgErro);
    }

    ULOG_END( "implATDGETMOTTAB::Execute()" );
}
