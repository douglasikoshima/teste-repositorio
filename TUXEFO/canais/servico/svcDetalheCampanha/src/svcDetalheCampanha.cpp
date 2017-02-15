
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"


// Prototypes
extern void proCListaContatoDetalhe( int idContato, XMLGen * Saida );
extern void proCDetalheCampanha( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanha( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanhaDDD_Sel( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanhaTipoLinha_Sel( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanhaSegmentacao_Sel( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanhaDDD_Disp( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanhaTipoLinha_Disp( int idCampanhaPrm, XMLGen * Saida );
extern void proCDetalheCampanhaSegmentacao_Disp( int idCampanhaPrm, XMLGen * Saida );

DECLARE_TUXEDO_SERVICE(DETALCAMPAN);

void implDETALCAMPAN::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
   CTuxHelperClever helper;
   
	char* p0 = NULL;	
	int	idCampanha = 0;

	p0 = helper.walkTree( dnode, "idCampanha", 0 );
	if ( p0 != NULL )
   {
      idCampanha = atoi(p0);
   }

	xml_g->createTag("CampanhaVO");
	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns3","admsistemas.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns2","cliente.fo.vivo.com.br/vo");

         proCDetalheCampanha( idCampanha, xml_g );
         xml_g->createTag( "disponiveis" );
            proCDetalheCampanhaDDD_Disp( idCampanha, xml_g );
            proCDetalheCampanhaTipoLinha_Disp( idCampanha, xml_g );
            proCDetalheCampanhaSegmentacao_Disp( idCampanha, xml_g );
         xml_g->closeTag();
         xml_g->createTag( "selecionados" );
            proCDetalheCampanhaDDD_Sel( idCampanha, xml_g );
            proCDetalheCampanhaTipoLinha_Sel( idCampanha, xml_g );
            proCDetalheCampanhaSegmentacao_Sel( idCampanha, xml_g );
         xml_g->closeTag();
         proCListaContatoDetalhe( idCampanha,xml_g );
         
   xml_g->closeTag();

   setStatusCode("13I0000","Sucesso");
   
}
