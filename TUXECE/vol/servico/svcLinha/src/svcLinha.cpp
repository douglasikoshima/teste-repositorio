
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void TemLinhaAssociada( DOMNode * dnode, XMLGen* xml_g );
void LinhaVivo( DOMNode * dnode, XMLGen* xml_g );
void TelefonePorConta( DOMNode * dnode, XMLGen * xml_g );
void TipoLinha( DOMNode * dnode, XMLGen* xml_g );

extern bool hasLinhaAssociada( char * cdContaPrm );
extern bool isLinhaVivo( char * areaCodePrm, char * phoneNumberPrm );
extern void getTelefonePorConta( char * idContaPrm, XMLGen * xml_g );
extern void getTipoLinhaVivo( char * areaCodePrm, char * phoneNumberPrm, char * tpLinha );

DECLARE_TUXEDO_SERVICE( LINHAS );
void implLINHAS::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char * p0 = NULL;
    

    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : TemLinhaAssociada( dnode, xml_g );
                 break;
                             
        case 2 : LinhaVivo( dnode, xml_g );
                 break;

        case 3 : TelefonePorConta( dnode, xml_g );
                 break;

        case 4 : TipoLinha( dnode, xml_g );
                 break;

    }
    
    setStatusCode("11I0000","Sucesso");
}


void TemLinhaAssociada( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;
    char retorno[2];

    char * cdContaPrm = helper.walkTree( dnode, "idConta", 0 );
    
    if ( hasLinhaAssociada( cdContaPrm ) == true )
       strcpy( retorno, "S" );
    else
       strcpy( retorno, "N" );
    
    xml_g->createTag( "TelefoneVO" );
       xml_g->addItem( "LinhaAssociada", retorno );
    xml_g->closeTag();
}



void LinhaVivo( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;
    char retorno[2];

    char * cdAreaPrm = helper.walkTree( dnode, "cdArea", 0 );
    char * nrTelefonePrm = helper.walkTree( dnode, "nrTelefone", 0 );
    
    if ( isLinhaVivo( cdAreaPrm, nrTelefonePrm ) == true )
       strcpy( retorno, "S" );
    else
       strcpy( retorno, "N" );
    
    xml_g->createTag( "TelefoneVO" );
       xml_g->addItem( "LinhaVivo",  retorno );
    xml_g->closeTag();
}



void TelefonePorConta( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "idConta", 0 );
    
    getTelefonePorConta( cdContaPrm, xml_g );
}



void TipoLinha( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;
    char tpLinha[64];

    char * cdAreaPrm = helper.walkTree( dnode, "cdArea", 0 );
    char * nrTelefonePrm = helper.walkTree( dnode, "nrTelefone", 0 );
    memset( tpLinha, 0x0, sizeof(tpLinha) );
    
    getTipoLinhaVivo( cdAreaPrm, nrTelefonePrm, tpLinha );
    
    xml_g->createTag( "TelefoneVO" );
       xml_g->addItem( "TipoLinha", tpLinha );
    xml_g->closeTag();
}
