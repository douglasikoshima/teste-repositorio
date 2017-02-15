//--------------------------------------------------------------------------------------------------------------
// GRAVASENHA - Service Implementation
//--------------------------------------------------------------------------------------------------------------

#include "../include/GravaSenha.h"

//--------------------------------------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(GRAVASENHA);

//--------------------------------------------------------------------------------------------------------------

void implGRAVASENHA::Execute( DOMNode*dnode, XMLGen*xml_g )
{
   ULOG_START("implGRAVASENHA::Execute()");
   TString retCode;
   TString retMesg;

	CGravaSenha rc(dnode, xml_g, getUser());
	try
	{
		rc.Executar();
		rc.EnviaSMS();
		retCode = rc.getStatus(); 
		retMesg = rc.getStaMsg(); 
	}
    catch( TuxBasicOraException *tboe )
    {
        char errCode[16];
        sprintf(errCode,"00E%04d",tboe->eCode<0?-1*tboe->eCode:tboe->eCode);

        retCode = errCode;
        retMesg = tboe->pMsg;

        delete tboe;
    }
	catch(...)
	{
		retCode = rc.getStatus(); 
		retMesg = rc.getStaMsg(); 
	}

   setStatusCode(retCode.c_str(),retMesg.c_str());
   ULOG_END("implGRAVASENHA::Execute()");    
}
