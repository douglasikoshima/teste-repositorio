/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/

#include "../include/cWFAtdAssocDocTe.h"
#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(ATDASSOCDOCTE);

void implATDASSOCDOCTE::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDASSOCDOCTE::Execute()"); 
    
    bool resultado = true;
    char *msgErro;
    char *codErro;
    char *user;

    cWFAtdAssocDocTe ob(dnode,xml_g);

    if ( user=getUser(),!user ) 
    {
        ob.SetarErro("00E0000","Valor de 'user' é obrigatório.");
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
        setStatusCode("00I0000","Operação concluida");
    }
    else
    {
        msgErro = ob.ObterTamMsgErro() ? ob.ObterMsgErro() : "Falha na execução";
        codErro = ob.ObterTamCodErro() ? ob.ObterCodErro() : "00E0000";

        ULOGE(mensagemSimples(msgErro));

        setStatusCode(codErro,msgErro);
    }
    ULOG_END("implATDASSOCDOCTE::Execute()"); 
}
