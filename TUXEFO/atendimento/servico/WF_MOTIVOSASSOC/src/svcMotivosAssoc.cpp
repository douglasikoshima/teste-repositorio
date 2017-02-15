/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Marcelo Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:18 $
 **/

#include "../include/cMotivosAssoc.h"

DECLARE_TUXEDO_SERVICE(MOTIVOSASSOC);

void implMOTIVOSASSOC::Execute( DOMNode * dnode,XMLGen * xml_g ) 
{
    ULOG_START("implMOTIVOSASSOC::Execute()");
    char *usuario;
    cMotivosAssoc ob( dnode,xml_g );
    short retorno;
    string sCode;
    string mCode;

    try
    {
        if (usuario=getUser(),usuario) 
        {
        retorno = ob.Processa();
    }
        else
        {
            retorno = -3;
        }
    }
    catch (TuxBasicOraException *ex)
    {
		ULOG("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;

        delete ex;
	}
    catch (TuxException *ex)
    {
		ULOG("Erro %s:%s",ex->getCode(),ex->getMessage());

        sCode = ex->getCode();
        mCode = ex->getMessage();

        delete ex;
    }
    catch(...)
    {
        throw;
    }

    if ( retorno == 0 )
    {
        sCode = "09I0000";
        mCode = "Operação concluída";
    }
    else if ( retorno == -1 )
    {
        sCode = "09W0001";
        mCode = "Motivo encontrado na base de dados";
    }
    else if ( retorno == -2 )
    {
        sCode = "09W0002";
        mCode = "Motivo nao existe na base de dados";
    }
    else if ( retorno == -3 )
    {
        sCode = "09W0003";
        mCode = "Valor de 'user' é obrigatório.";
    }
    else if ( retorno == -4 )
    {
        sCode = "09E0004";
        mCode = "falha de inserção";
    }

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());
    
    ULOG_END("implMOTIVOSASSOC::Execute()");
}
