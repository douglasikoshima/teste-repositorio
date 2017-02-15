
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void UltimosProtocolos( DOMNode * dnode, XMLGen* xml_g );
void HistoricoPorData( DOMNode * dnode, XMLGen* xml_g );
void HistoricoProtocolo( DOMNode * dnode, XMLGen * xml_g );
void HistoricoProtocoloData( DOMNode * dnode, XMLGen * xml_g );
void TelefoneHistoricoProtocolo( DOMNode * dnode, XMLGen * xml_g );
void DetalhesProtocolo( DOMNode * dnode, XMLGen * xml_g );

extern void getUltimosProtocolos( char * cdContaPrm, XMLGen * xml_g );
extern void getHistoricoProtocoloPorDataProtocolo( char * cdContaPrm, char * dataInicialPrm, char * dataFinalPrm, char * nrProtocoloPrm, XMLGen * xml_g );
extern void getHistoricoProtocoloPorNumero( char * cdContaPrm, char * nrProtocoloPrm, XMLGen * xml_g );
extern void getHistoricoProtocoloPorData( char * cdContaPrm, char * dataInicialPrm, char * dataFinalPrm, XMLGen * xml_g );
extern void getTelefoneHistoricoProtocolo( char * nrProtocoloPrm, XMLGen * xml_g );
extern void getDetalhesProtocolo( char * nrProtocoloPrm, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( PROTOCOLOS );
void implPROTOCOLOS::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : UltimosProtocolos( dnode, xml_g );
                 break;
                             
        case 2 : HistoricoPorData( dnode, xml_g );
                 break;

        case 3 : HistoricoProtocolo( dnode, xml_g );
                 break;
                             
        case 4 : HistoricoProtocoloData( dnode, xml_g );
                 break;

        case 5 : TelefoneHistoricoProtocolo( dnode, xml_g );
                 break;

        case 6 : DetalhesProtocolo( dnode, xml_g );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}


void UltimosProtocolos( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm        = helper.walkTree( dnode, "cdConta", 0 );
    
    getUltimosProtocolos( cdContaPrm, xml_g );
}



void HistoricoPorData( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm    = helper.walkTree( dnode, "cdConta", 0 );
    char * startDatePrm = helper.walkTree( dnode, "DataInicial", 0 );
    char * endDatePrm = helper.walkTree( dnode, "DataFinal", 0 );
    char * nrProtocoloPrm = helper.walkTree( dnode, "nrProtocolo", 0 );
    
    getHistoricoProtocoloPorDataProtocolo( cdContaPrm, startDatePrm, endDatePrm, nrProtocoloPrm, xml_g );
}



void HistoricoProtocolo( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    char * nrProtocoloPrm = helper.walkTree( dnode, "nrProtocolo", 0 );
    
    getHistoricoProtocoloPorNumero( cdContaPrm, nrProtocoloPrm, xml_g );
}



void HistoricoProtocoloData( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    char * startDatePrm = helper.walkTree( dnode, "DataInicial", 0 );
    char * endDatePrm = helper.walkTree( dnode, "DataFinal", 0 );
    
    getHistoricoProtocoloPorData( cdContaPrm, startDatePrm, endDatePrm, xml_g );
}



void TelefoneHistoricoProtocolo( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * nrProtocoloPrm = helper.walkTree( dnode, "nrProtocolo", 0 );
    
    getTelefoneHistoricoProtocolo( nrProtocoloPrm, xml_g );
}



void DetalhesProtocolo( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * nrProtocoloPrm = helper.walkTree( dnode, "nrProtocolo", 0 );
    
    getDetalhesProtocolo( nrProtocoloPrm, xml_g );
}