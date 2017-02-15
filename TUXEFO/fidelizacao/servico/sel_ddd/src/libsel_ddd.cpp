


#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_ddd( XMLGen *xml );

DECLARE_TUXEDO_SERVICE( SELDDD );

void implSELDDD::Execute( DOMNode *dnode, XMLGen *xml_g )
{
  ULOG_START( "SELDDD" );

  xml_g->createTag( "tns:FidelizacaoManutChipVO" );
  xml_g->addProp( "xmlns:tns","fidelizacao.fo.vivo.com.br/vo" );
  xml_g->addProp( "xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance" );

  sel_ddd( xml_g );

  xml_g->closeTag();

  setStatusCode( OKFID,OKMSG );

  ULOG_END( "SELDDD" );
}

