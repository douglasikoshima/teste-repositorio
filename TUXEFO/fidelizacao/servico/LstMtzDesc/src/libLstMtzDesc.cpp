
#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(LSTMTZDESC);

extern int LstMtzDesc( int usuario, DOMNode*dnode, XMLGen*xml );

void implLSTMTZDESC::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    int idUsr;
    ULOG_START("LSTMTZDESC");

    idUsr = get_idUsuario(getUser());
    xml_g->createTag("tns:FidelizacaoCadastroDescontoVO");
    xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        LstMtzDesc( idUsr, dnode, xml_g );
    xml_g->closeTag();

    setStatusCode(OKFID,OKMSG);
    ULOG_END("LSTMTZDESC");
}
