//
// $Id: libget_pergunta.cpp,v 1.1 2009/07/31 15:33:54 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETPERGUNTA);

extern int get_pergunta(char * usuario, DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETPERGUNTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
     char * ptUsr;

     ULOG_START("GETPERGUNTA");

     ptUsr = getUser();

     xml_g->createTag("CadastroPerguntaVO");
	 xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	 xml_g->addProp("xsi:schemaLocation","campanha.fo.vivo.com.br/vo");
	 xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

     //  xml_g->createTag("tns:SubGrupoApoioVO");   // Acrescentado
	 get_pergunta(ptUsr,dnode,xml_g);
     //  xml_g->closeTag();                         // Acrescentado

     xml_g->closeTag();

     ULOG_END("GETPERGUNTA");

     setStatusCode(OKCMP,"Succes Execution");
}

