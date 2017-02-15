
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void ItensMenu( DOMNode * dnode, XMLGen* xml_g );

extern void getItensMenu( char * idCanalPrm, 
                          char * idTipoCarteiraPrm,
                          char * idUFOperadoraPrm,
                          char * idSegmentacaoPrm, 
                          XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( MENU );
void implMENU::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : ItensMenu( dnode, xml_g );
                 break;
                             
    }
    
    setStatusCode("11I0000","Sucesso");
}


void ItensMenu( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * idCanalPrm = helper.walkTree( dnode, "idCanal", 0 );
    char * idTipoCarteiraPrm = helper.walkTree( dnode, "idTipoCarteira", 0 );
    char * idUFOperadoraPrm = helper.walkTree( dnode, "idUFOperadora", 0 );
    char * idSegmentacaoPrm = helper.walkTree( dnode, "idSegmentacao", 0 );
    
    getItensMenu( idCanalPrm, idTipoCarteiraPrm, idUFOperadoraPrm, idSegmentacaoPrm, xml_g );
}
