
#include "../../negocio/fidutil/include/retencao.hpp"

extern int InsereMtzDescontoAssoc( int usuario, DOMNode*dnode, XMLGen*xml );

DECLARE_TUXEDO_SERVICE( INSMTZDASS );

void implINSMTZDASS::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    int idUsr;

    ULOG_START( "INSMTZDASS" );

    idUsr = get_idUsuario( getUser() );

    xml_g->createTag("tns:retornoVO");
    xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

    InsereMtzDescontoAssoc( idUsr, dnode, xml_g );

    xml_g->closeTag();

    setStatusCode( OKFID, OKMSG );

    ULOG_END( "INSMTZDASS" );
}

