
#include "../include/cWFPsqGrpCTIPC.h"

DECLARE_TUXEDO_SERVICE(WFPSQGRPCTI);

void implWFPSQGRPCTI::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implWFPSQGRPCTI::Execute()");
   register int indx;
	Collection  resultado;
   char       *pBuffer;
   cWFPsqGrpCTIPC  rc;
	stVariaveisPsqGrpCTI dados;
   stVariaveisPsqGrpCTI *pCTI;

   pBuffer = walkTree( dnode, "idRetornoWFCTI", 0 );
   if ( pBuffer == NULL )
      dados.idretornowfcti[0] = 0x0;
   else
   {
      strcpy( dados.idretornowfcti,pBuffer );
      XMLString::release(&pBuffer);
   }
   
   pBuffer = walkTree( dnode, "inPadrao", 0 );
   if ( pBuffer == NULL )
      dados.inpadrao[0] = 0x0;
   else
   {
      strcpy( dados.inpadrao,pBuffer );
      XMLString::release(&pBuffer);
   }

   rc.SelectGroupsByRetWFCTI( &dados, &resultado );

   xml_g->createTag("RetornoWFCTIVO");
   xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo" );
   for ( indx=0;indx < resultado.GetCount();indx++ )
   {
      pCTI  = (stVariaveisPsqGrpCTI *)resultado.GetItem(indx);

      if ( atoi(pCTI->flagSelecionado) == 0 )
      {
         xml_g->createTag("WFGrupoDisponivelVO");
            xml_g->addItem( "idGrupo"  , pCTI->idGrupo );
            xml_g->addItem( "dsGrupo"  , pCTI->nmgrupo );
         xml_g->closeTag();
      }
      else
      {
         xml_g->createTag("WFGrupoSelecionadoVO");
            xml_g->addItem( "idGrupo"  , pCTI->idGrupo );
            xml_g->addItem( "dsGrupo"  , pCTI->nmgrupo );
         xml_g->closeTag();
      }

   }
   xml_g->closeTag();

	setStatusCode("04I0000","Pesquisa Retorno CTI Concluida.");
	
	ULOG_END("implWFPSQGRPCTI::Execute()");

}
