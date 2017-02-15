//
// $Id: libget_bonus.cpp,v 1.1 2009/07/31 15:34:06 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(GETBONUS);

extern int get_list_bonus(int usuario, DOMNode*dnode, XMLGen*xml);
extern int get_list_bonusURA(int usuario, DOMNode*dnode, XMLGen*xml);

void implGETBONUS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  char parm[255];
  int inUra=-1;
  
  ULOG_START("GETBONUS");

  idUsr = get_idUsuario(getUser());

  inUra =  get_tag(parm, dnode, "inURA", 0, -1); 

	
	  
   if(inUra==-1)
	{
	  xml_g->createTag("tns:listaBonusVO");
	  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
	  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");	
	  
		 get_list_bonus(idUsr, dnode,xml_g);
  	}
   else
	{
	  xml_g->createTag("ListaBonusVO");
	  xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
	  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	
		get_list_bonusURA(idUsr, dnode,xml_g);
	}
	 xml_g->closeTag();  
  setStatusCode(OKFID,OKMSG);

  ULOG_END("GETBONUS");
}
