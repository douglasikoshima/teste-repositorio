
#include "../../negocio/fidutil/include/retencao.hpp"

extern int InsereMtzDesconto( int usuario, DOMNode*dnode, XMLGen*xml );

DECLARE_TUXEDO_SERVICE( INSMTZDESC );

void implINSMTZDESC::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    int idUsr;

    ULOG_START( "INSMTZDESC" );

    idUsr = get_idUsuario( getUser() );

    xml_g->createTag("tns:FidelizacaoCadastroDescontoVO");
    xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

    InsereMtzDesconto( idUsr, dnode, xml_g );

    xml_g->closeTag();

    setStatusCode( OKFID, OKMSG );

    ULOG_END( "INSMTZDESC" );
}

