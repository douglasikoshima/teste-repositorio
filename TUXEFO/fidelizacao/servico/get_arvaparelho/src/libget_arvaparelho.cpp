//
// $Id: libget_arvaparelho.cpp,v 1.1 2009/07/31 15:34:18 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_arvaparelho( int usuario,DOMNode *dnode,XMLGen *xml );

DECLARE_TUXEDO_SERVICE( GETARVAPARELHO );

void implGETARVAPARELHO::Execute( DOMNode*dnode,XMLGen*xml_g )
{
    ULOG_START("GETARVAPARELHO");

    int idUsr = get_idUsuario( getUser() );

    get_arvaparelho( idUsr,dnode,xml_g );

    setStatusCode( OKFID,OKMSG );

    ULOG_END( "GETARVAPARELHO" );
}

