
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"


// Prototypes
extern void proCbuscarGestorLinha( char * buffer, XMLGen * Saida );
extern void proCbuscarCNPJs( char * nrDocumento, XMLGen * Saida );
extern void proCbuscarContasPagadoras( char * nrCNPJ, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE(CNSGESTAOLIN);

void implCNSGESTAOLIN::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    char buffer[65];
    char nrDocumento[65];
    char nrCNPJ[32];
    int idCampanha = 0;

    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    if ( strcmp( "buscarGestorLinha",p0 ) == 0 )
    {
        p0 = helper.walkTree( dnode, "nrLinha", 0 );
        if ( p0 != NULL )
        {
        strcpy( buffer,p0 );
        proCbuscarGestorLinha( buffer,xml_g );
    }
    }

    if ( strcmp( "buscarCNPJs",p0 ) == 0 )
    {
        p0 = helper.walkTree( dnode, "nrDocumentoGestor", 0 );
        if ( p0 != NULL )
        {
            strcpy( nrDocumento,p0 );
            proCbuscarCNPJs( nrDocumento,xml_g );
        }
    
    }
    
    if ( strcmp( "buscarContasPagadoras",p0 ) == 0 )
    {
        p0 = helper.walkTree( dnode, "nrCNPJ", 0 );
        if ( p0 != NULL )
        {
            strcpy( nrCNPJ,p0 );
            proCbuscarContasPagadoras( nrCNPJ,xml_g );
        }
    }

   setStatusCode("11I0000","Sucesso");
   
}
