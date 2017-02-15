//
// $Id: libins_aparelho.cpp,v 1.1.2.1 2010/10/05 20:41:12 a5114878 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int ins_aparelho(int usuario, DOMNode*dnode, XMLGen*xml);
extern void InsPessoaParcela( int usuario, DOMNode * dnode, XMLGen * xml );


DECLARE_TUXEDO_SERVICE( INSAPARELHO );

void implINSAPARELHO::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    int idUsr;
    char parm[256];

    ULOG_START("INSAPARELHO");

    idUsr = get_idUsuario(getUser());
    parm[0] = 0x0;

    xml_g->createTag("tns:retornoVO");
    xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

    get_tag( parm, dnode, "cdOperacao", 0, -1 );
    if ( !strcmp(parm,"1") )
    {
        InsPessoaParcela( idUsr, dnode, xml_g );
    }
    else
    {
        ins_aparelho( idUsr, dnode, xml_g );
    }

    xml_g->closeTag();
    setStatusCode(OKFID,OKMSG);

    ULOG_END("INSAPARELHO");
}

