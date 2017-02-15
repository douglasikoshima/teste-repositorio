//
// $Id: libget_listaconteudo.cpp,v 1.1 2009/07/31 15:34:06 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETLISTACONTEU);

extern int get_listaconteudo(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETLISTACONTEU::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char parm[255];
	char * ptUsr;
	int operacao=0;

	ULOG_START("GETLISTACONTEU");

	ptUsr = getUser();

	//  Obtendo dados do xml
	get_tag(parm, dnode, "getreg", 0, -1);
	get_tag(parm, dnode, "operacao", 0, -1);
	operacao=atoi(parm);

	if( operacao == 0 || operacao == 2 || operacao == 3 )
		xml_g->createTag("ListaAtendimentoCampanhaVO");
	else 
		xml_g->createTag("ContatoAtendimentoCampanhaVO");

	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	get_listaconteudo(ptUsr,dnode,xml_g);

	xml_g->closeTag();

	ULOG_END("GETLISTACONTEU");
	setStatusCode(OKCMP,"Succes Execution");
}
