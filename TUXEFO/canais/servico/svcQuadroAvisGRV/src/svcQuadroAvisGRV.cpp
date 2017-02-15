
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void LeuMensagem( DOMNode * dnode );

extern void markAsRead( char * cdContaPrm, char * idMensagemPrm );


DECLARE_TUXEDO_SERVICE( QDROAVISGRV );
void implQDROAVISGRV::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    
    ULOG( "Operacao solicitada [%d]", opcao );
    
    switch( opcao )
    {
        case 1 : LeuMensagem( dnode );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}



void LeuMensagem( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    char * idMensagemPrm = helper.walkTree( dnode, "idMensagem", 0 );
    
    markAsRead( cdContaPrm, idMensagemPrm );
}
