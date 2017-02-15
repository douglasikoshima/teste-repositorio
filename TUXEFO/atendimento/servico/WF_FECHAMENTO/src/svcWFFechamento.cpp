/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Marcelo Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:45 $
 **/

#include "../../../commons/routerLib/include/RouterClient.h"
#include "../include/cWFFechamento.h"

DECLARE_TUXEDO_SERVICE(FECHAMENTO);

void implFECHAMENTO::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implFECHAMENTO:Execute()");
    
    TString retCode = "04I0000";
    TString retMesg = "Execucao de FECHAMENTO Concluida";

    cWFFechamento rc(dnode, xml_g, getUser());

    try
    {
        rc.Executar(); 
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

    ULOG_END("implFECHAMENTO:Execute()");
}
