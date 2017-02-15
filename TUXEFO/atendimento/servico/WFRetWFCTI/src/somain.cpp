/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M. Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author:
 **/

#include "../../../commons/routerLib/include/RouterClient.h"

#include "../../../commons/msgPadrao.h"

#include "../include/cWFRetWFCTI.h"


#define INCLUIR   'I'
#define ALTERAR   'A'
#define EXCLUIR   'E'
#define CONSULTAR 'C'
#define CONSULTAR_POR_USUARIO 'U'
#define CONSULTAR_POR_GRUPO   'G'

DECLARE_TUXEDO_SERVICE(WFRetWFCTI);

void implWFRetWFCTI::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
   ULOG_START("implWFRetWFCTI::Execute()");  
   bool resultado;
   char operacao = '0';
   char *msgErro;
   char *codErro;
   char *p = walkTree( dnode, "tipoOperacao", 0 );
   char *user;
   char *uuid;
	int  ret = 0;

	if (p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

    cWFRetWFCTI ob(dnode,xml_g);

    ULOGE("Executando implWFRetWFCTI::Execute ...");

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
    else
    {
        ob.setarUser(atoi(user));
        ob.setarUUID(atoi(uuid));

        ULOG("user=%d",ob.obterUser());

        ULOG("UUID=%d",ob.obterUUID());

        if ( INCLUIR == operacao )
        {
            resultado = ob.Inserir();
        }
        else if ( ALTERAR == operacao )
        {
            resultado = ob.Alterar();
        }
        else if ( EXCLUIR == operacao )
        {
            resultado = ob.Delete();
        }
        else if ( CONSULTAR_POR_USUARIO == operacao )
        {
            resultado = ob.ConsultarPorUsuario(ob.obterUser(),ob.obterUUID());
        }
        else if ( CONSULTAR_POR_GRUPO == operacao )
        {
            resultado = ob.ConsultarPorGrupo(ob.obterUser(),ob.obterUUID());
        }
        else
        {
            ob.SetarErro("00E0000","Tipo de operação invalido");
            resultado = false;
        }
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
    
    ULOG_END("implWFRetWFCTI::Execute()");  
}
