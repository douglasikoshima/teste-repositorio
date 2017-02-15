
#include "../../negocio/fidutil/include/retencao.hpp"

// Prototipo
extern void procConsultaConexoes( DOMNode * dnode, XMLGen * xml_g );


DECLARE_TUXEDO_SERVICE( RELCNXCREDITO );
void implRELCNXCREDITO::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    ULOG_START( "RELCNXCREDITO" );
    int TotalAprovados = 0;
    int TotalReprovados = 0;


    try
    {
        xml_g->createTag( "tns:relOfertasVO" );
        xml_g->addProp( "xmlns:tns","fidelizacao.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance" );

        procConsultaConexoes( dnode, xml_g );

        xml_g->closeTag();

        setStatusCode( OKFID, OKMSG );
    }
    catch( ... )
    {
        throw;
    }

    ULOG_END( "RELCNXCREDITO" );
}
