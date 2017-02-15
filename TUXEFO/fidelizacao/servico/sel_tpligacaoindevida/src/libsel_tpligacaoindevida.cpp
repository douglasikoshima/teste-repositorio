#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_tpligacaoindevida(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELTPLIGIND);

void implSELTPLIGIND::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[50];
  int idUsr;

  ULOG_START("SELTPLIGIND");
  
  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
	xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_tpligacaoindevida(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("SELTPLIGIND");
}

