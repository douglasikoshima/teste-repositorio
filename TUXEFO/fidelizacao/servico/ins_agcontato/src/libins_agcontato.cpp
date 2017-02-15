//
// $Id: libins_agcontato.cpp,v 1.1.118.5 2012/06/12 20:58:48 a5110706 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int ins_agcontato(int usuario, DOMNode*dnode, XMLGen*xml);

extern int ins_pr(int usuario, DOMNode*dnode, XMLGen*xml);

extern int ins_cadpr(int usuario, DOMNode*dnode, XMLGen*xml);


DECLARE_TUXEDO_SERVICE(INSAGCONTATO);

void implINSAGCONTATO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  int iTipo;
  int iCad;
  char parm[255];

  iTipo=0;	
  iCad=0;
  ULOG_START("INSAGCONTATO");

  idUsr = get_idUsuario(getUser());

  iTipo= get_tag(parm,dnode,"cdOperacao",0,-1);	

  iCad= get_tag(parm,dnode,"dtNasc",0,-1);	

  if(!iTipo)
	{
	  ins_pr( idUsr,dnode,xml_g);
	}
  else if (!iCad)
  {
	ins_cadpr( idUsr,dnode,xml_g);
  }
  else
  {
	  ins_agcontato(idUsr,dnode,xml_g);
  }
  
  setStatusCode(OKFID,OKMSG);

  ULOG_END("INSAGCONTATO");
}

