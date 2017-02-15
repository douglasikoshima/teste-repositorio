
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void ContaDocumento_VOLE( DOMNode * dnode, XMLGen* xml_g );
void ContaDocumento( DOMNode * dnode, XMLGen* xml_g );
void ContaAssociada( DOMNode * dnode, XMLGen* xml_g );
void JaCadastrada( DOMNode * dnode, XMLGen * xml_g );

extern void getContaByCnpjAccountNumber_VOLE( char * idPessoaGestorPrm, char * cnpjPrm, char * accountNumberPrm, XMLGen * xml_g );
extern void getContaByCnpjAccountNumber( char * idPessoaGestorPrm, char * cnpjPrm, char * accountNumberPrm, XMLGen * xml_g );
extern void hasContaAssociada( char * idPessoaGestorPrm, XMLGen * xml_g );
extern void jaCadastrada( char * idPessoaGestor, char * idConta, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( CONTAS );
void implCONTAS::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : ContaDocumento_VOLE( dnode, xml_g );
                 break;
                             
        case 2 : ContaAssociada( dnode, xml_g );
                 break;

        case 3 : JaCadastrada( dnode, xml_g );
                 break;

        case 4 : ContaDocumento( dnode, xml_g );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}


void ContaDocumento_VOLE( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * cnpjPrm        = helper.walkTree( dnode, "nrDocumento", 0 );
    char * cdContaPrm        = helper.walkTree( dnode, "cdConta", 0 );
    
    getContaByCnpjAccountNumber_VOLE( idPessoaGestorPrm, cnpjPrm, cdContaPrm, xml_g );
}



void ContaDocumento( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;
    DOMNode * dn;
    int j;

    char * idPessoaGestorPrm;
    char * cnpjPrm;
    char * cdContaPrm;
    
    j = 0;
    while ( dn = helper.walkDOM(dnode,"Gestor",j++ ) )
    {
        idPessoaGestorPrm = helper.walkTree( dn, "idPessoaGestor", 0 );
        cnpjPrm = helper.walkTree( dn, "nrDocumento", 0 );
        cdContaPrm = helper.walkTree( dn, "cdConta", 0 );
        
        getContaByCnpjAccountNumber( idPessoaGestorPrm, cnpjPrm, cdContaPrm, xml_g );
    }

    
    //getContaByCnpjAccountNumber( idPessoaGestorPrm, cnpjPrm, cdContaPrm, xml_g );
}



void ContaAssociada( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    hasContaAssociada( idPessoaGestorPrm, xml_g );
}



void JaCadastrada( DOMNode * dnode, XMLGen * xml_g )
{
    ULOG_START( "SVC JaCadastrada()" );
    
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * idContaPrm        = helper.walkTree( dnode, "idConta", 0 );
    
    ULOG( "idPessoaGestorPrm [%s]", idPessoaGestorPrm );
    ULOG( "idContaPrm [%s]", idContaPrm );
    
    jaCadastrada( idPessoaGestorPrm, idContaPrm, xml_g );
    
    ULOG_END( "SVC JaCadastrada()" );

}

