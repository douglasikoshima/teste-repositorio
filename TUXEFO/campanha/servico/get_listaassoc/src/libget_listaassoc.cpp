//
// $Id: libget_listaassoc.cpp,v 1.1 2009/07/31 15:34:22 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int get_listaassoc( int usuario, DOMNode*dnode,XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETLISTAASSOC);

void implGETLISTAASSOC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	int idUsr;

	ULOG_START("GETLISTAASSOC");

	idUsr = get_idUsuario(getUser());

	xml_g->createTag("tns:ListaCampanhaApoioVO");
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	get_listaassoc(idUsr,dnode,xml_g);

	xml_g->closeTag();

	setStatusCode(OKCMP,OKMSG);

	ULOG_END("GETLISTAASSOCIADA");
}
