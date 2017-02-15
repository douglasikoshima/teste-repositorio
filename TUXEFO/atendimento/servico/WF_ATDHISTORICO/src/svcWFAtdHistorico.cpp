/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:27 $
 **/

#include "../include/cWFAtdHistorico.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDHISTORICO);

void implATDHISTORICO::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDHISTORICO::Execute()");
    
    char *msgErro = 0;
    char *codErro = 0;
    char *usuario;

    cWFAtdHistorico ob(dnode,xml_g);

    if (usuario=getUser(),usuario) 
    {
        ob.setarIdUsuario(atoi(usuario));

        ULOG("idUsuario=%d",ob.obterIdUsuario());
        if ( ob.executar(&codErro,&msgErro) )
        {
            setStatusCode("04I0000","Opera��o concluida");
        }
        else
        {
            if ( !msgErro ) msgErro = "Falha na execu��o";
            if ( !codErro ) codErro = "00E0000";

            ULOGE(msgErro);

            setStatusCode(codErro,msgErro);
        }
    }
    else
    {
        char *p = "Valor de 'user' � obrigat�rio.";
        ULOGE(p);
        setStatusCode(codErro,p);
    }
    ULOG_END("implATDHISTORICO::Execute()");
}
