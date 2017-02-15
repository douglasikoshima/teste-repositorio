//
// $Id: libsel_listacampanha.cpp,v 1.1 2009/07/31 15:34:23 a5110702 Exp $
//

#include "sel_listacampanha.h"

DECLARE_TUXEDO_SERVICE(SELLISTACAMP);

void implSELLISTACAMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	CSEL_LISTACAMPANHA *pListaCamp=new CSEL_LISTACAMPANHA();
	char *ptUsr;

	ptUsr = getUser();
	if(pListaCamp->setMap(ptUsr,dnode,xml_g))
	{
		pListaCamp->lista();
		setStatusCode("05I0000","Succes Execution");
	}
	else
		setStatusCode("05E0000","Error");

	delete pListaCamp;
}

