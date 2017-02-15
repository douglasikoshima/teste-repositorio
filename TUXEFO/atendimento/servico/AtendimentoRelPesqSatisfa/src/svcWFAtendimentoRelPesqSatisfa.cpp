/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:07 $
 **/

#include "../include/cWFAtendimentoRelPesqSatisfa.h"

DECLARE_TUXEDO_SERVICE(WFATDRELPESATI);

void implWFATDRELPESATI::Execute(DOMNode*dnode,XMLGen*xml_g) 
{
    cWFAtdRelPSS ob(dnode,xml_g);

    try
    {
        ob.Executar();
        setStatusCode("00I0000","Operação concluida");
    }
    catch (TuxException *ex)
    {
        ULOGE("Erro %s:%s",ex->getCode(),ex->getMessage());
        setStatusCode(ex->getCode(),ex->getMessage());
        delete ex;
    }
    catch (TuxBasicOraException *ex)
    {
        char codErro[25];
        sprintf(codErro,"04E%04d",ex->eCode<0 ?ex->eCode*(-1):ex->eCode);

        setStatusCode(codErro,ex->pMsg);

        delete ex;
    }
    catch(...)
    {
        ULOGE("erro desconhecido");
        setStatusCode("04E9999", "Erro desconhecido");
    }
}

