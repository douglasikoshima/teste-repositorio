/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:56 $
 **/

#include "../include/cAndaObservacao.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ANDAOBSERVACAO);

void implANDAOBSERVACAO::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    char *msgErro = 0;
    char *codErro = 0;

    ULOG( "Executando implANDAOBSERVACAO::Execute()" );

    cAndaObs ob(dnode,xml_g);
   
    if ( ob.executar(&codErro,&msgErro) )
    {
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        if ( !msgErro ) msgErro = "Falha na execução";
        if ( !codErro ) codErro = "00E0000";

        ULOGE( msgErro );

        setStatusCode(codErro,msgErro);
    }
}

