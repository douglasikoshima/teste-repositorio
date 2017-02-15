
#include "../../negocio/fidutil/include/retencao.hpp"

// Prototipo
extern void procVolumeDiario( DOMNode * dnode, int * TotalAprovadosPrm, int * TotalReprovadosPrm );


DECLARE_TUXEDO_SERVICE( RELVOLDIARIO );

void implRELVOLDIARIO::Execute( DOMNode * dnode, XMLGen * xml_g )
{
    ULOG_START( "RELVOLDIARIO" );
    int TotalAprovados = 0;
    int TotalReprovados = 0;
    int TotalConsultas = 0;

    try
    {
        xml_g->createTag( "RelatorioAdimplenciaVO" );
        xml_g->addProp( "xmlns","fidelizacao.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance" );

            procVolumeDiario( dnode, &TotalAprovados, &TotalReprovados );
            TotalConsultas = TotalAprovados + TotalReprovados;
            xml_g->addItem( "totalAdimplentes", TotalAprovados );
            xml_g->addItem( "totalInadimplentes", TotalReprovados );
            xml_g->addItem( "totalConsultas", TotalConsultas );

        xml_g->closeTag();

        setStatusCode( OKFID, OKMSG );
    }
    catch( ... )
    {
        throw;
    }

    ULOG_END( "RELVOLDIARIO" );
}
