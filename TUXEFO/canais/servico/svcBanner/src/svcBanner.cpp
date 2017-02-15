
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void BuscaBanner( DOMNode * dnode, XMLGen* xml_g );
void BuscaBannerArea( DOMNode * dnode, XMLGen* xml_g );
void BuscaBannerbyID( DOMNode * dnode, XMLGen* xml_g );

extern void getBanners( char * sgUFPrm, XMLGen * xml_g );
extern void getBannerByLocalizacao( char * idBannerPrm, XMLGen * xml_g );
extern void getBannerById( char * idBannerPrm, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( BANNER );
void implBANNER::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : BuscaBanner( dnode, xml_g );
                 break;
                             
        case 2 : BuscaBannerArea( dnode, xml_g );
                 break;

        case 3 : BuscaBannerbyID( dnode, xml_g );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}


void BuscaBanner( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * sgUFPrm = helper.walkTree( dnode, "sgUF", 0 );
    
    getBanners( sgUFPrm, xml_g );
}



void BuscaBannerArea( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * idBannerPrm = helper.walkTree( dnode, "idBanner", 0 );
    
    getBannerByLocalizacao( idBannerPrm, xml_g );
}



void BuscaBannerbyID( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * idBannerPrm = helper.walkTree( dnode, "idBanner", 0 );
    
    getBannerById( idBannerPrm, xml_g );
}
