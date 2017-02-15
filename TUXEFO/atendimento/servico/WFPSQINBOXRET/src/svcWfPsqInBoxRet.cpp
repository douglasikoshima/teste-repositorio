
#include "../include/cWfPsqInBoxRetPC.h"

DECLARE_TUXEDO_SERVICE(WFPSQINBOXRET);

void implWFPSQINBOXRET::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implWFPSQINBOXRET::Execute()");
   
   register int indx;
	Collection  resultado;
   char       *pBuffer;
   cWfPsqInBoxRetPC  rc;
	st_VariaveisPsqCTI dados;
   st_VariaveisPsqCTI *pCTI;

   pBuffer = walkTree( dnode, "idRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      dados.idretornowfcti[0] = 0x0;
   else
   {
      strcpy( dados.idretornowfcti,pBuffer );
      XMLString::release(&pBuffer);
   }

   pBuffer = walkTree( dnode, "dsRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      dados.dsretornowfcti[0] = 0x0;
   else
   {
      strcpy( dados.dsretornowfcti,pBuffer );
      XMLString::release(&pBuffer);
   }
   
   pBuffer = walkTree( dnode, "sgStatus", 0 );
   if ( pBuffer == NULL )
      dados.sgstatus[0] = 0x0;
   else
   {
      strcpy( dados.sgstatus, pBuffer );
      XMLString::release(&pBuffer);
   }
   
   rc.pesquisaWFRetornoCTI( &dados, &resultado );

   xml_g->createTag("RetornoWFCTIResultadoVO");
   xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
   for ( indx=0;indx < resultado.GetCount();indx++ )
   {
      pCTI  = (st_VariaveisPsqCTI *)resultado.GetItem(indx);

      xml_g->createTag("RetornoWFCTIVO");
      xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
         xml_g->addItem( "idRetornoWFCTI"  , pCTI->idretornowfcti );
         xml_g->addItem( "sgRetornoWFCTI"  , pCTI->sgretornowfcti );
         xml_g->addItem( "dsRetornoWFCTI"  , pCTI->dsretornowfcti );
         xml_g->addItem( "sgStatus"  , pCTI->sgstatus );
         xml_g->addItem( "inPadrao"  , pCTI->inpadrao );
      xml_g->closeTag();
   }
   xml_g->closeTag();

   setStatusCode("04I0000","Pesquisa Retorno CTI Concluida.");
   
   ULOG_START("implWFPSQINBOXRET::Execute()");

}
