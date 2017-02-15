/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:50 $
 **/

#include "../include/cWFAtendimentoClickFolha.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDCLKFOLHA);

void implATDCLKFOLHA::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDCLKFOLHA::Execute()"); 
    char *msgErro = 0;
    char *codErro = 0;
    char *user;


    cWFAtdClickFolha ob(dnode,xml_g);
   
    if (user=getUser(),user) 
    {
        ob.setarIdPessoaUsuario(atoi(user));

        ULOG("idPessoaUsuario=%d",ob.obterIdPessoaUsuario());
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
    ULOG_END("implATDCLKFOLHA::Execute()"); 
}

