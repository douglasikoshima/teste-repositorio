/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:30 $
 **/

#include "../include/cWFAtdAttrUsuSem.h"

DECLARE_TUXEDO_SERVICE(ATDATTRUSUSEM);

void implATDATTRUSUSEM::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    ULOG_START("implATDATTRUSUSEM::Execute()"); 

    cWFAtdAttrUsuSem ob(dnode,xml_g);

    try
    {
        int user = atoi(getUser());
        ob.setarIdUsuario(user);
        ob.Executar();
        setStatusCode("00I0000","Operação concluida");
    }
    catch (TuxBasicOraException *ex)
    {
        ULOGE("sqlca.sqlcode %d:%s",ex->eCode,ex->pMsg);

        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);
        setStatusCode(codErro,ex->pMsg);

        delete ex;
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());

        setStatusCode(ex->getCode(),ex->getMessage());

        delete ex;
    }
    catch (...)
    {
        ULOGE("erro desconhecido");
        setStatusCode("04E9999","erro desconhecido");
    }

    ULOG_END("implATDATTRUSUSEM::Execute()"); 
}
