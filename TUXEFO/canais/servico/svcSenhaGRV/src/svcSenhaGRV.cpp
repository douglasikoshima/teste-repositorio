
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"


// Prototypes
void SalvaSenhaTmp( DOMNode * dnode );
void IncrementaTentativas( DOMNode * dnode );
void ZeraTentativas( DOMNode * dnode );
void BloqueiaSenha( DOMNode * dnode );
void AtualizaSenha( DOMNode * dnode );
void AssociaContaGestor( DOMNode * dnode, XMLGen* xml_g );
void AssociaContaGestor_VOLE( DOMNode * dnode );

extern void salvaSenhaTemporaria( char * gestorIdPrm, char * senhaTemporariaPrm );
extern void incrementTentativesCounter( char * idPessoaGestorPrm );
extern void resetTentativesCounter( char * idPessoaGestorPrm );
extern void blockSenha( char * idPessoaGestorPrm );
extern void atualizaSenha( char * gestorIdPrm, char * senhaTemporariaPrm, char * inTrocaSenhaPrm );
extern int associaContaByIdGestor( char * idPessoaGestorPrm, char * cdContaPrm );
extern void associaContaByIdGestor_VOLE( char * idPessoaGestorPrm, char * idContaPrm );
extern void associaGestorMaster( char * idPessoaGestorPrm, char * cdCNPJPrm );

DECLARE_TUXEDO_SERVICE(SENHAGRV);

void implSENHAGRV::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;
    

    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : SalvaSenhaTmp( dnode );
                 break;
                             
        case 2 : IncrementaTentativas( dnode );
                 break;

        case 3 : ZeraTentativas( dnode );
                 break;

        case 4 : BloqueiaSenha( dnode );
                 break;

        case 5 : AtualizaSenha( dnode );
                 break;

        case 6 : AssociaContaGestor_VOLE( dnode );
                 break;

        case 7 : AssociaContaGestor( dnode, xml_g );    /*   MVE   */
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}



void SalvaSenhaTmp( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * gestorIdPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * senhaTemporariaPrm = helper.walkTree( dnode, "cdSenha", 0 );
    
    salvaSenhaTemporaria( gestorIdPrm, senhaTemporariaPrm );
}



void IncrementaTentativas( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    incrementTentativesCounter( idPessoaGestorPrm );
}



void ZeraTentativas( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    resetTentativesCounter( idPessoaGestorPrm );
}



void BloqueiaSenha( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    blockSenha( idPessoaGestorPrm );
}



void AtualizaSenha( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * senhaTemporariaPrm = helper.walkTree( dnode, "cdSenha", 0 );
    char * inTrocaSenhaPrm = helper.walkTree( dnode, "inTrocaSenha", 0 );

    atualizaSenha( idPessoaGestorPrm, senhaTemporariaPrm, inTrocaSenhaPrm );
}



void AssociaContaGestor( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;
    DOMNode * dn;
    DOMNode * dnEmpresa;
    DOMNode * dnContas;
    int j;
    int k;
    int l;
    bool    flgErro;

    char * idPessoaGestorPrm;
    char * cdContaPrm;
    char * cdCnpjPrm;

    j = 0;
    flgErro = false;
    while ( dn = helper.walkDOM(dnode,"Gestor",j++ ) )
    {
        idPessoaGestorPrm = helper.walkTree( dn, "idPessoaGestor", 0 );
        if ( idPessoaGestorPrm[0] != 0x0 )
        {
            k = 0;
            while ( dnEmpresa = helper.walkDOM(dn,"Empresa",k++ ) )
            {
                cdCnpjPrm = helper.walkTree( dnEmpresa, "cdCnpj", 0 );

                l = 0;
                while ( dnContas = helper.walkDOM(dnEmpresa,"Contas",l++) )
                {
                    cdContaPrm = helper.walkTree( dnContas, "cdConta", 0 );
                    if ( cdContaPrm[0] != 0x0 )
                    {
                       if ( associaContaByIdGestor( idPessoaGestorPrm, cdContaPrm ) < 0 )
                       {
                            if ( flgErro != true )
                            {
                                flgErro = true;
                                xml_g->addItem( "COD_ERRO", "NOK" );
                                xml_g->addItem( "TEXTO_ERRO", "010 = Parâmetro cdConta inválido" );
                            }
                       }
                    }
                    else
                    {
                        if ( flgErro != true )
                        {
                            flgErro = true;
                            xml_g->addItem( "COD_ERRO", "NOK" );
                            xml_g->addItem( "TEXTO_ERRO", "007 = Parâmetro cdConta é requerido" );
                        }
                    }
                }
                
                ULOG( ">>> Valor de l [%d]", l );
                
                if ( l == 1 )
                {
                    if ( cdCnpjPrm[0] != 0x0 )
                    {
                        associaGestorMaster( idPessoaGestorPrm, cdCnpjPrm );
                    }
                    else
                    {
                        if ( flgErro != true )
                        {
                            flgErro = true;
                            xml_g->addItem( "COD_ERRO", "NOK" );
                            xml_g->addItem( "TEXTO_ERRO", "004 = Parâmetro CNPJ é requerido" );
                        }
                    }
                }
            }
            if ( flgErro != true )
            {
                xml_g->addItem( "COD_ERRO", "OK" );
                xml_g->addItem( "TEXTO_ERRO", "000 = Sucesso" );
            }
        }
        else
        {
            xml_g->addItem( "COD_ERRO", "NOK" );
            xml_g->addItem( "TEXTO_ERRO", "005 = Parâmetro identificador do Gestor é requerido" );
        }
        
    }

}



void AssociaContaGestor_VOLE( DOMNode * dnode )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    char * idContaPrm = helper.walkTree( dnode, "idConta", 0 );

    associaContaByIdGestor_VOLE( idPessoaGestorPrm, idContaPrm );
}