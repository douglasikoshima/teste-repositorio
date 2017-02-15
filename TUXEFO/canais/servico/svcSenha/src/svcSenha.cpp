
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void SenhaByIdPessoaGestor( DOMNode * dnode, XMLGen* xml_g );
void SenhaJaCadastrada( DOMNode * dnode, XMLGen* xml_g );
void SenhaByConta( DOMNode * dnode, XMLGen * xml_g );

extern void getSenhaByIdPessoaGestor( char * idPessoaGestorPrm, XMLGen* xml_g );
extern bool isSenhaJaCadastrada( char * gestorIdPrm );
extern void getSenhaByConta( char * cdContaPrm, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( SENHA );
void implSENHA::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    

    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : SenhaByIdPessoaGestor( dnode, xml_g );
                 break;
                             
        case 2 : SenhaJaCadastrada( dnode, xml_g );
                 break;

        case 3 : SenhaByConta( dnode, xml_g  );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}



void SenhaByIdPessoaGestor( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    getSenhaByIdPessoaGestor( gestorIdPrm, xml_g );
}



void SenhaJaCadastrada( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;
    char resp[2];

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    bool cadastrada = isSenhaJaCadastrada( idPessoaGestorPrm );
    
    if ( cadastrada == true )
    {
       strcpy( resp,"S" );
    }
    else
    {
       strcpy( resp,"N" );
    }
    
    xml_g->createTag( "SenhaContaVO" );
       xml_g->addItem( "SenhaCadastrada", resp );
    xml_g->closeTag();
}



void SenhaByConta( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    
    getSenhaByConta( cdContaPrm, xml_g );
}
