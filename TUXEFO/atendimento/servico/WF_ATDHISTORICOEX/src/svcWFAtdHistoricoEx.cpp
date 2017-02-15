/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:32 $
 **/

#include "../include/cWFAtdHistoricoEx.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE( ATDHISTORICOEX );

void implATDHISTORICOEX::Execute( DOMNode * dnode, XMLGen * xml_g ) 
{
    ULOG_START("implATDHISTORICOEX::Execute()");
    
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
            setStatusCode("04I0000","Operação concluida");
        }
        else
        {
            if ( !msgErro ) msgErro = "Falha na execução";
            if ( !codErro ) codErro = "00E0000";

            ULOGE(msgErro);

            setStatusCode(codErro,msgErro);
        }
    }
    else
    {
        char *p = "Valor de 'user' é obrigatório.";
        ULOGE(p);
        setStatusCode(codErro,p);
    }
    ULOG_END("implATDHISTORICOEX::Execute()");
}
