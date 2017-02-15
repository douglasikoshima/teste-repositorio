/* $Id: svcConsultaRamal.cpp,v 1.1 2009/07/31 15:33:32 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/cConsultaRamal.h"

DECLARE_TUXEDO_SERVICE(ConsRamal);

void implConsRamal::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implConsRamal::Execute()");
   
	int idCallCenter;

	idCallCenter = atoi( walkTree( dnode, "idCallCenter", 0 ) );

	xml_g->createTag("RamaisVO");
	xml_g->addProp("xmlns","senha.fo.vivo.com.br/vo");

	cConsultaRamal cR;
	cR.consultarRamal( idCallCenter, xml_g);

	xml_g->closeTag();

	setStatusCode("07I0000","Pesquisa realizada.");  
	
	ULOG_END("implConsRamal::Execute()");

}
