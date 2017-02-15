/**
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/


#ifdef WIN32
#pragma warning(disable:4786)
#endif

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>

#include "../../../commons/queryMacro.h"
#include "../../../commons/msgPadrao.h"

#include <string>
using namespace std;

#include "../include/cWFRegContatoErro.h"

DECLARE_TUXEDO_SERVICE(REGCONTATOER);

void implREGCONTATOER::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implREGCONTATOER::Execute()");

    string sCode = "04I0000";
    string mCode = "Erro registrado.";

    char *user = getUser();

    try
    {
        char *msgErro = getHdrAttr("statusText");

		cWFRegContatoErro oWFRegContatoErro(dnode, user, msgErro );
		
		long idAtendimentoRegContatoErr = oWFRegContatoErro.incluir();
		
        ULOG("idAtendimentoRegContatoErr[%d]", idAtendimentoRegContatoErr );
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        sCode = codErro;
        mCode = ex->pMsg;

        delete ex;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        sCode = ex->getCode();
        mCode = ex->getMessage();

        delete ex;
    }
    catch (char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0097";
        mCode = errMsg;
    }
    catch (const char *errMsg)
    {
        ULOGE("%s",errMsg);

        sCode = "04E0098";
        mCode = errMsg;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");

        sCode = "04E9999";
        mCode = "erro desconhecido";
    }

    setStatusCode((char*)sCode.c_str(),(char*)mCode.c_str());

    ULOG_END("implREGCONTATOER::Execute()");
}
