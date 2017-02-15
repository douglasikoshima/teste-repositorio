//
// $Id: libget_listaatendimento.cpp,v 1.1 2009/07/31 15:34:23 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETLISTAATENDI);

extern int get_listaatendimento(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETLISTAATENDI::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[255];
  char * ptUsr;
  int operacao=0;

	ULOG_START("GETLISTAATENDI");

	ptUsr = getUser();

	//  Obtendo dados do xml
	get_tag(parm, dnode, "getreg", 0, -1);
	get_tag(parm, dnode, "operacao", 0, -1);
	operacao=atoi(parm);

	xml_g->createTag("ListaAtendimentoCampanhaVO");

	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");

	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	get_listaatendimento(ptUsr,dnode,xml_g);

	xml_g->closeTag();

	ULOG_END("GETLISTAATENDI");

    setStatusCode(OKCMP,"Succes Execution");
}
