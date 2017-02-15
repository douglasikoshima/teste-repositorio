//
// $Id: libget_ofdisp.cpp,v 1.1 2009/07/31 15:33:44 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_ofdisp( int usuario, DOMNode*dnode, XMLGen*xml );

DECLARE_TUXEDO_SERVICE( GETOFDISP );

void implGETOFDISP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("GETOFDISP");

    int idUsr;
    idUsr = get_idUsuario( getUser() );

    xml_g->createTag("tns:fidelizacaoListaGeralDescricaoVO");
    xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

    get_ofdisp( idUsr, dnode, xml_g );

    xml_g->closeTag();

    setStatusCode( OKFID,OKMSG );

    ULOG_END("GETOFDISP");
}

