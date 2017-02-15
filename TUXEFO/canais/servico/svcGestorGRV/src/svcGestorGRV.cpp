
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"


// Prototypes
void AtualizaPrimeiroAcesso( DOMNode * dnode );
void RegistraLogin( DOMNode * dnode );
void AtualizaEMail( DOMNode * dnode );
void AtualizaTelefoneFixo( DOMNode * dnode );
void ContadorBanner( DOMNode * dnode );
void GravaCelularProtocolo( DOMNode * dnode );
void GravaLogAdm( DOMNode * dnode );
void GravaLoginGestor( DOMNode * dnode );

extern void updatePrimeiroAcesso( char * gestorIdPrm );
extern void insertLoginReg( char * idPessoaGestorPrm, char * phoneNumberPrm, bool isLinhaVivo );
extern void updateEmail( char * idPessoaGestorPrm, char * emailPrm );
extern void updateTelefoneFixo( char * idPessoaGestorPrm, char * telefonePrm );
extern void atualizaContadorBanner( char * idBannerPrm );
extern void gravaCelularProtocolo( char * idContaPrm, char * nrTelefoneSmsPrm );
extern void LogAdm( char * idPessoaGestorPrm, char * nrProtocoloPrm, char * nrCNPJPrm, char * cdContaPrm );
extern void GravaLogLogin( char * idPessoaGestorPrm, char * tpGestorPrm, char * IPClientePrm );

DECLARE_TUXEDO_SERVICE( GESTORGRV );
void implGESTORGRV::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    
    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    
    ULOG( "Operacao solicitada [%d]", opcao );
    
    switch( opcao )
    {
        case 1 : AtualizaPrimeiroAcesso( dnode );
                 break;
                             
        case 2 : RegistraLogin( dnode );
                 break;

        case 3 : AtualizaEMail( dnode );
                 break;

        case 4 : AtualizaTelefoneFixo( dnode );
                 break;

        case 5 : ContadorBanner( dnode );
                 break;

        case 6 : GravaCelularProtocolo( dnode );
                 break;

        case 7 : GravaLogAdm( dnode );
                 break;

        case 8 : GravaLoginGestor( dnode );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}



void AtualizaPrimeiroAcesso( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    updatePrimeiroAcesso( idPessoaGestorPrm );
}



void RegistraLogin( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * phoneNumberPrm = helper.walkTree( dnode, "nrTelefone", 0 );
    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    //bool isLinhaVivo = ( LinhaVivoPrm[0] == 'S' ) ? true : false ;
    bool isLinhaVivo = ( strcmp(LinhaVivoPrm, "true") == 0 ) ? true : false ;
    
    insertLoginReg( idPessoaGestorPrm, phoneNumberPrm, isLinhaVivo );
}



void AtualizaEMail( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * emailPrm = helper.walkTree( dnode, "dsEmail", 0 );
    
    updateEmail( idPessoaGestorPrm, emailPrm );
}



void AtualizaTelefoneFixo( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * nrLinhaPrm = helper.walkTree( dnode, "nrTelefone", 0 );
    
    updateTelefoneFixo( idPessoaGestorPrm, nrLinhaPrm );
}



void ContadorBanner( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idBannerPrm = helper.walkTree( dnode, "idBanner", 0 );
    
    atualizaContadorBanner( idBannerPrm );
}



void GravaCelularProtocolo( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idContaPrm = helper.walkTree( dnode, "idConta", 0 );
    char * nrTelefoneSmsPrm = helper.walkTree( dnode, "nrTelefoneSms", 0 );
    
    gravaCelularProtocolo( idContaPrm, nrTelefoneSmsPrm );
}



void GravaLogAdm( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * nrProtocoloPrm = helper.walkTree( dnode, "nrProtocolo", 0 );
    char * nrCNPJPrm = helper.walkTree( dnode, "nrCNPJ", 0 );
    char * cdContaPrm = helper.walkTree( dnode, "cdConta", 0 );
    
    LogAdm( idPessoaGestorPrm, nrProtocoloPrm, nrCNPJPrm, cdContaPrm );
}



void GravaLoginGestor( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * tpGestorPrm = helper.walkTree( dnode, "sgTPGestor", 0 );
    char * IPClientePrm = helper.walkTree( dnode, "IPCliente", 0 );
    
    GravaLogLogin( idPessoaGestorPrm, tpGestorPrm, IPClientePrm );
}

