/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.114.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cWFAtdInboxDispo.h"

DECLARE_TUXEDO_SERVICE(ATDINBOXDISPO);

void implATDINBOXDISPO::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    
    ULOG_START("implATDINBOXDISPO::Execute()");
    
    SmallString retCode;
    SmallString retMesg;

    try
    {
        //long idPessoaUsuario = 0;
        //char *p;

        //if ( p = walkTree( dnode, "idPessoaUsuario", 0 ),p )
        //{
        //    idPessoaUsuario = atol(p);
        //}

        cWFAtdInboxDispo ob(dnode,xml_g,getUser());

        if ( ob.Executar() )
        {
            retCode = "04I0000";
            retMesg = "Operação concluida";
        }
        else
        {
            char *msgErro = ob.ObterTamMsgErro() ? ob.ObterMsgErro() : "Falha na execução";
            char *codErro = ob.ObterTamCodErro() ? ob.ObterCodErro() : "04E0000";

            retCode = codErro;
            retMesg = msgErro;
        }
    }
    catch ( TuxBasicOraException *tboe )
    {
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);

        retCode = errCode;
        retMesg = tboe->pMsg;

        delete tboe;
    }
    catch( TuxException* pTux )
    {
        retCode = "04E9998";
        retMesg = pTux->getMessage();

        delete pTux;
    }
    catch(...)
    {
        retCode = "04E9999";
        retMesg = "Excecao desconhecida";
    }

    setStatusCode(retCode.c_str(),retMesg.c_str());

    ULOG_END("implATDINBOXDISPO::Execute()");
}
