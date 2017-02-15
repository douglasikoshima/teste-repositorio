//
// $Id: libget_criterios.cpp,v 1.1 2009/07/31 15:34:48 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(GETCRITERIO);

extern int getTipoCliente	( XMLGen*xml );
extern int getClasificacao	( XMLGen*xml );
extern int getGrupo			( XMLGen*xml );
extern int getOferta		( XMLGen*xml );
extern int getOperadora		( XMLGen*xml );

void implGETCRITERIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETCRITERIO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:RelatorioRetencaoInputVO");
  xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  
	getTipoCliente(xml_g);	
	getClasificacao(xml_g);
	getGrupo(xml_g);
	getOferta(xml_g);
	getOperadora(xml_g);

	xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("GETCRITERIO");
}