
#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_chip( XMLGen *xml );
extern int sel_chip_filtro( int dddPrm, XMLGen *xml );

DECLARE_TUXEDO_SERVICE( SELCHIP );

void implSELCHIP::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START( "SELCHIP" );
    char parm[256];
    parm[0] = 0x0;
    int dddPrm = 0;

    xml_g->createTag( "tns:FidelizacaoManutChipVO" );
    xml_g->addProp( "xmlns:tns","fidelizacao.fo.vivo.com.br/vo" );
    xml_g->addProp( "xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance" );

    get_tag( parm, dnode, "dddPesquisa", 0, -1 );
    dddPrm = atoi(parm);
    
    if ( dddPrm > 0 )
    {
        sel_chip_filtro( dddPrm, xml_g );
    }
    else
    {
        sel_chip( xml_g );
    }
    
    xml_g->closeTag();
    setStatusCode( OKFID, OKMSG );
    ULOG_END( "SELCHIP" );
}

