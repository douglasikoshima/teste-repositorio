//--------------------------------------------------------------------------------------------------------------
// LISTAIDPOS - Service Implementation
//--------------------------------------------------------------------------------------------------------------

#include "../include/ListaIDPosPC.h"

//--------------------------------------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(LISTAIDPOS);

//--------------------------------------------------------------------------------------------------------------

void implLISTAIDPOS::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implLISTAIDPOS::Execute()");
    TString retCode;
    TString retMesg;

	CListaIDPos rc(dnode, xml_g, getUser());

	try
	{
		rc.Executar(); 
		retCode = rc.getStatus(); 
		retMesg = rc.getStaMsg(); 
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
		retCode = rc.getStatus(); 
		retMesg = rc.getStaMsg(); 
        delete pTux;
    }
	catch(...)
	{
		retCode = rc.getStatus(); 
		retMesg = rc.getStaMsg(); 
	}

    setStatusCode(retCode.c_str(),retMesg.c_str());
    
    ULOG_START("implLISTAIDPOS::Execute()");

}

//--------------------------------------------------------------------------------------------------------------
