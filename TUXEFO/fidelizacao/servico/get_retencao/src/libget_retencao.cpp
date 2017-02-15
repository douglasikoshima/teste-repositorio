//
// $Id: libget_retencao.cpp,v 1.1 2009/07/31 15:34:00 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(GETRETENCAO);

extern int get_list_retencao(int usuario, DOMNode*dnode, XMLGen*xml);

void implGETRETENCAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	int idUsr;
	int iTotReg = 0;
	
	ULOG_START("GETRETENCAO");

	idUsr = get_idUsuario(getUser());

	xml_g->createTag("tns:RetencaoVO");
	xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	get_list_retencao(idUsr,dnode,xml_g);


	xml_g->closeTag();
	
	setStatusCode(OKFID,OKMSG);

	ULOG_END("GETRETENCAO");
}
