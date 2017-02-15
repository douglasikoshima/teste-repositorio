/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/

#include "../include/cWFAtdDetPessoaEx.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE( ATDDETPESSOAEX );

void implATDDETPESSOAEX::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDDETPESSOAEX::Execute()");
    char *msgErro = 0;
    char *codErro = 0;
    char *usuario;

    cWFAtdDetPessoaEx ob(dnode,xml_g);

    if (usuario=getUser(),usuario) 
    {
        ob.setarIdUsuario(atoi(usuario));

        ULOG("idUsuario=%d",ob.obterIdUsuario());
        if ( ob.executar(&codErro,&msgErro) )
        {
            setStatusCode("00I0000","Operação concluida");
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
    ULOG_END("implATDDETPESSOAEX::Execute()");
}
