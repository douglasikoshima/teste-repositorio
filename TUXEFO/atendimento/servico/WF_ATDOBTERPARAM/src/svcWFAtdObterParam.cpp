/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/

#include "../include/cWFAtdObterParam.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(ATDOBTERPARAM);

void implATDOBTERPARAM::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDOBTERPARAM::Execute()");
    
    char *msgErro = 0;
    char *codErro = 0;
    char *user;

    bool resultado = true;

    cWFAtdObterParam ob(dnode,xml_g);

	if ( user=getUser(),!user ) 
    {
        msgErro = "Valor de 'user' é obrigatório.";
		codErro = "09E0000";
		setStatusCode(codErro,msgErro);
        resultado = false;
    }

	if (resultado)
	{
		ob.setarIdUsuario(atoi(user));
		if ( ob.executar(&codErro,&msgErro) )
		{
			setStatusCode("04I0000","Operação concluida");
		}
		else
		{
			if ( !msgErro ) msgErro = "Falha na execução";
			if ( !codErro ) codErro = "09E0001";

			ULOGE(msgErro);

			setStatusCode(codErro,msgErro);
		}
	}
	
	ULOG_END("implATDOBTERPARAM::Execute()");
}
