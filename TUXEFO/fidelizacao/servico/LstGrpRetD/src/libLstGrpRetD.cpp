
#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(LSTGRPRETD);

extern int LstGruposDesconto( XMLGen * xml );

void implLSTGRPRETD::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    int idUsr;
    ULOG_START("LSTGRPRETD");

    idUsr = get_idUsuario(getUser());
    xml_g->createTag("tns:FidelizacaoRelDescGrupoVO");
    xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        LstGruposDesconto( xml_g );
    xml_g->closeTag();

    setStatusCode(OKFID,OKMSG);
    ULOG_END("LSTGRPRETD");
}
