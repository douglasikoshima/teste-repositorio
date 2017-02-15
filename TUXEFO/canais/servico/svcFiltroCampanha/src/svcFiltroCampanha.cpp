
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"



extern void proCListaAreaRegistro( XMLGen * Saida );
extern void proCListaTipoLinha( XMLGen * Saida );
extern void proCListaSegmentacao( XMLGen * Saida );
extern void proCListaCampanha( XMLGen * Saida, int tpCampanha );
extern void proCListaContato( XMLGen * Saida );

DECLARE_TUXEDO_SERVICE(CAMPFILTRO);

void implCAMPFILTRO::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{

   CTuxHelperClever helper;
   char* p = NULL;	   
   int tpCampanha = 0;
   
   char *user = getUser();

   p = helper.walkTree( dnode, "tpCampanha", 0 );
   if ( p != NULL )
   {
      tpCampanha = atoi( p );
   }
   
   
	xml_g->createTag("CampanhaVO");
	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns3","admsistemas.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns2","cliente.fo.vivo.com.br/vo");
	   xml_g->createTag("disponiveis");
         proCListaAreaRegistro( xml_g );
         proCListaTipoLinha( xml_g );
         proCListaSegmentacao( xml_g );
      xml_g->closeTag();
      proCListaCampanha( xml_g, tpCampanha );
      proCListaContato( xml_g );
   xml_g->closeTag();

   setStatusCode("13I0000","Sucesso");
   
}
