
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void BuscaMensagens( DOMNode * dnode, XMLGen* xml_g );
void BuscaMensagensSize( DOMNode * dnode, XMLGen* xml_g );

extern void getMensagens( char * accountNumberPrm, XMLGen * xml_g );
extern void getMensagensSize( char * accountNumberPrm, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( QUADROAVISO );
void implQUADROAVISO::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : BuscaMensagens( dnode, xml_g );
                 break;

        case 2 : BuscaMensagensSize( dnode, xml_g );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}


void BuscaMensagens( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    
    getMensagens( cdContaPrm, xml_g );
}



void BuscaMensagensSize( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    
    getMensagensSize( cdContaPrm, xml_g );
}
