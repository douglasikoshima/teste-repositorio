//
// $Id: libapoio_campanha.cpp,v 1.1 2009/07/31 15:33:54 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETAPOIOCAMPANH);

extern int get_tipomotivocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_tiposubmotivocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_canal(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_tipocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_campanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_tipostatuscampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
extern int get_grupo(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETAPOIOCAMPANH::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[255];
    char * ptUsr;

        ULOG_START("GETAPOIOCAMPANH");
        ptUsr = getUser();

  //  Obtendo dados do xml
  get_tag(parm, dnode, "getreg", 0, -1);

  xml_g->createTag("tns:GrupoCampanhaApoioVO");
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	xml_g->createTag("tns:SubGrupoApoioVO");
		get_tipomotivocampanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();
	
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_tiposubmotivocampanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();
	
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_canal(ptUsr,dnode,xml_g);
	xml_g->closeTag();
	
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_tipocampanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();
	//xml_g->closeTag();
	
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_campanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();
	//xml_g->closeTag();
	
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_grupo(ptUsr,dnode,xml_g);
	xml_g->closeTag();
  
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_tipostatuscampanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();
  xml_g->closeTag();


        ULOG_END("GETAPOIOCAMPANH");
        setStatusCode(OKCMP,"Succes Execution");
}
