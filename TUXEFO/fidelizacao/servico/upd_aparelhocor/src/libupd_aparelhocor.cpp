//
// $Id: libupd_aparelhocor.cpp,v 1.1 2009/07/31 15:34:24 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"



extern int del_retencao();

extern int del_parametros();

extern int set_ofertas();

extern int set_sap(int usuario, DOMNode*dnode, XMLGen*xml);

extern int set_tpencerramento();


DECLARE_TUXEDO_SERVICE(UPDAPARELHOCOR);

void implUPDAPARELHOCOR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[255];
  int idUsr;
  int iOperacao=0;

  ULOG_START("UPDAPARELHOCOR");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  get_tag(parm,dnode,"Operacao",0,0);
  iOperacao=atoi(parm);

  switch(iOperacao)
  {
	  case 1:
		del_retencao();
	  break;
	  case 2:
		del_parametros();
	  break;
	  case 3:
		set_ofertas();
	  break;
	  case 4:
		set_sap(idUsr, dnode,xml_g);
	  break;
	  case 5:
		set_tpencerramento();
	  break;
	  case 0:
		ULOG("truncate(atendimento)");
		ULOG("truncate(atendimento.atendimento)");
		ULOG("truncate(acesso.unidade");
		ULOG("Base completamente apagada....ops...");
	  break;

  }

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);
  ULOG_END("UPDAPARELHOCOR");
}

