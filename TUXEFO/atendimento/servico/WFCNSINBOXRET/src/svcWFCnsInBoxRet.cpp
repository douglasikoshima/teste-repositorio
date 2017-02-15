#include "../include/cWFCnsInBoxRetPC.h"

DECLARE_TUXEDO_SERVICE(WFCNSINBOXRET);

void implWFCNSINBOXRET::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implWFCNSINBOXRET::Execute()");
   register int indx;
	Collection  resultado;
   char       *pBuffer;
   cWFCnsInBoxRetPC  rc;
	st_VariaveisCnsCTI dados;
   st_VariaveisCnsCTI *pCTI;

   pBuffer = walkTree( dnode, "idRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      dados.idretornowfcti[0] = 0x0;
   else
   {
      strcpy( dados.idretornowfcti,pBuffer );
      XMLString::release(&pBuffer);
   }
   
   rc.consultaWFRetornoCTI( &dados,&resultado );

   for ( indx=0;indx < resultado.GetCount();indx++ )
   {
      pCTI  = (st_VariaveisCnsCTI *)resultado.GetItem(indx);

      xml_g->createTag("RetornoWFCTIVO");
      xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
         xml_g->addItem( "idRetornoWFCTI"  , pCTI->idretornowfcti );
         xml_g->addItem( "sgRetornoWFCTI"  , pCTI->sgretornowfcti );
         xml_g->addItem( "dsRetornoWFCTI"  , pCTI->dsretornowfcti );
         xml_g->addItem( "sgStatus"  , pCTI->sgstatus );
         xml_g->addItem( "inPadrao"  , pCTI->inpadrao );
      xml_g->closeTag();
   }

   setStatusCode("09I0000","Pesquisa Retorno CTI Concluida.");
   
   ULOG_END("implWFCNSINBOXRET::Execute()");

}
