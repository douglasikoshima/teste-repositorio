//
// $Id: libapoio_campanha.cpp,v 1.1 2009/07/31 15:34:04 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"
#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )

DECLARE_TUXEDO_SERVICE(GETAPOIOCAMPA);

extern int get_tipomotivocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_tiposubmotivocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_canal(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_tipocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_campanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_tipostatuscampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_grupo(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETAPOIOCAMPA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    char  parm[255];
    char* ptUsr;
    int   iTudo = 0;
    char* cOperacao = walkTree( dnode, "operacao", 0 );

	if( STRLENNULL( cOperacao ) <= 0 )
	{
    	setStatusCode("05E0001","operacao está nulo");
    	return;
	}
	
    ULOG_START("GETAPOIOCAMPA");
	//Recupeando o XML
    ptUsr = getUser();
  	//  Obtendo dados do xml
  	get_tag(parm, dnode, "getreg", 0, -1);

  	xml_g->createTag("tns:GrupoCampanhaApoioVO");
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	
	xml_g->createTag("tns:SubGrupoApoioVO");

	if( strcmp( cOperacao, "1" ) == 0 )
	{
		get_campanha(ptUsr,dnode,xml_g);
	}
	else if( strcmp( cOperacao, "2" ) == 0 )
	{
		get_tipomotivocampanha(ptUsr,dnode,xml_g);
	}
	else if( strcmp( cOperacao, "3" ) == 0 )
	{
		get_tiposubmotivocampanha(ptUsr,dnode,xml_g);
	}
	else if( strcmp( cOperacao, "4" ) == 0 )
	{
		get_tipocampanha(ptUsr,dnode,xml_g);
	}
	else
	{
    	setStatusCode("05E0002","Código de operação inválido");
    	return;
	}
/*
get_canal(ptUsr,dnode,xml_g);
get_tipocampanha(ptUsr,dnode,xml_g);
get_grupo(ptUsr,dnode,xml_g);
get_tipostatuscampanha(ptUsr,dnode,xml_g);
*/

  	xml_g->closeTag();//xml_g->createTag("tns:SubGrupoApoioVO");

  	xml_g->closeTag();//xml_g->createTag("tns:GrupoCampanhaApoioVO");

    ULOG_END("GETAPOIOCAMPA");
    setStatusCode(OKCMP,"Succes Execution");
        
	XMLString::release( &cOperacao );
}
