/* $Id: svcConsultaRamalURA.cpp,v 1.1 2009/07/31 15:34:07 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/cConsultaRamalURA.h"

DECLARE_TUXEDO_SERVICE(ConsRamalURA);

void implConsRamalURA::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implConsRamalURA::Execute()");
   char * pIdRamal;
	int idRamalSenha;

   pIdRamal = walkTree( dnode, "idRamalSenha", 0 );
   if ( pIdRamal == NULL )
      idRamalSenha = 0;
   else
	   idRamalSenha = atoi( pIdRamal );

	cConsultaRamalURA cR;
	if ( idRamalSenha != 0 )
	{
   	xml_g->createTag("RamaisUraVO");
   	xml_g->addProp("xmlns","senha.fo.vivo.com.br/vo");
	   cR.consultarRamalURA( idRamalSenha, xml_g);
   	xml_g->closeTag();
	}
	else
      cR.consultaNavegacaoURA( dnode, xml_g );

	setStatusCode("07I0000","Pesquisa realizada.");  
	
	ULOG_END("implConsRamalURA::Execute()");

}
