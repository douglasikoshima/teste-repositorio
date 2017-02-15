//
// $Id: libstart_campanha.cpp,v 1.1 2009/07/31 15:33:42 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int start_campanha( char* usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(STARTCAMPANHA);

void implSTARTCAMPANHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char* cidUsr;
	char  cidCanalCampanha[255+1];
	char  cidPessoaUsuario[255+1];
	char  cidListaConteudo[255+1];

	ULOG_START("STARTCAMPANHA");
	
	cidUsr = getUser();
	
	get_tag(cidCanalCampanha,dnode,"idCanalCampanha",0,0);
	get_tag(cidPessoaUsuario,dnode,"idPessoaUsuario",0,0);
	get_tag(cidListaConteudo,dnode,"idListaConteudo",0,0);

	if( strlen( cidCanalCampanha ) <= 0 )
	{
		setStatusCode("00E0000","idCanalCampanha esta nulo");
		return;
	}
	if( strlen( cidListaConteudo ) <= 0 )
	{
		setStatusCode("00E0000","idListaConteudo esta nulo");
		return;
	}

	xml_g->createTag("tns:startCampanhaVO");
	xml_g->addProp("xmlns:tns","questionario.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	
	start_campanha(cidUsr,dnode,xml_g);
	
	xml_g->closeTag();
	
	setStatusCode(OKCMP, OKMSG);
	
	ULOG_END("STARTCAMPANHA");
}

