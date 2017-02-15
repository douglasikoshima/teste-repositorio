//--------------------------------------------------------------------------------------------------------------
// LISTAIDPOS - Service Implementation
//--------------------------------------------------------------------------------------------------------------

#include "../include/ValidaIDPosPC.h"

//--------------------------------------------------------------------------------------------------------------

DECLARE_TUXEDO_SERVICE(VALIDAIDPOS);

//--------------------------------------------------------------------------------------------------------------

void implVALIDAIDPOS::Execute( DOMNode*dnode, XMLGen*xml_g )
{
   ULOG_START("implVALIDAIDPOS::Execute()");

   TString retCode;
   TString retMesg;

	CValidaIDPos rc(dnode, xml_g, getUser());

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
   xml_g->addItem("idTipoRelacionamento" , rc.getIdTipoRelacionamento()); 
   xml_g->addItem("idPessoa" , rc.IdPessoa.c_str()); 
   xml_g->closeTag();
   ULOG_END("implVALIDAIDPOS::Execute()");

}

//--------------------------------------------------------------------------------------------------------------
