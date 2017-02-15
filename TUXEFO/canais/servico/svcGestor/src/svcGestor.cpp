
#include <stdio.h>
#include <tuxfw.h>
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"

// Prototypes
void BuscaGestorTelefone( DOMNode * dnode, XMLGen* xml_g );
void BuscaGestorTelefoneCPF( DOMNode * dnode, XMLGen* xml_g );
void BuscaGestorTelefoneSemCPF( DOMNode * dnode, XMLGen* xml_g );
void BuscaGestorCPF( DOMNode * dnode, XMLGen* xml_g  );
void BuscaGestorConta( DOMNode * dnode, XMLGen * xml_g );
void BuscaEnderecoGestor( DOMNode * dnode, XMLGen * xml_g );
void ValidaGestor( DOMNode * dnode, XMLGen * xml_g );
void ValidaGestorDuplicidade( DOMNode * dnode, XMLGen* xml_g );
void BuscaConsultor( DOMNode * dnode, XMLGen * xml_g );
void BuscaGestorTelefoneCPF_VOLE( DOMNode * dnode, XMLGen* xml_g );
void BuscaGestorTelefoneSemCPF_VOLE( DOMNode * dnode, XMLGen* xml_g );

extern void getGestorByPhoneNumber( char * phoneNumberPrm, bool identifyOnlyByLinhaVivo, XMLGen* xml_g );
extern void getGestorByPhoneNumberConta( char * cdContaPrm, XMLGen* xml_g );
extern int  getGestorByPhoneNumberCPF_VOLE( char * phoneNumberPrm, char * cpfPrm, bool identifyOnlyByLinhaVivo, XMLGen* xml_g );
extern int  getGestorByPhoneNumberCPF( char * phoneNumberPrm, char * cpfPrm, bool identifyOnlyByLinhaVivo, XMLGen* xml_g );
extern int  ValidaGestorByPhoneNumberCPF( char * phoneNumberPrm, char * cpfPrm );
extern int  getGestorByPhoneNumberWithOutCpf_VOLE( char * phoneNumberPrm, bool identifyOnlyByLinhaVivo, XMLGen* xml_g );
extern int  getGestorByPhoneNumberWithOutCpf( char * phoneNumberPrm, bool identifyOnlyByLinhaVivo, XMLGen* xml_g );
extern int  ValidaGestorByPhoneNumberWithOutCpf( char * phoneNumberPrm );
extern int  getGestorByCpf( char * s_cpf, char * s_idPessoaGestor );
extern int  ValidaGestorByCpf( char * s_cpf );
extern void getGestorByConta( char * idContaPrm, XMLGen* xml_g );
extern void getEnderecoGestor( char * idPessoaGestorPrm, XMLGen* xml_g );
extern void validaGestorCPFTelefone( char * cpfPrm, char * telefonePrm, bool identifyOnlyByLinhaVivo, XMLGen* xml_g );
extern void getConsultorByCnPj( char * cnpjPrm, XMLGen * xml_g );
extern void getGestorProtocolo( char * nrProtocoloPrm, XMLGen * xml_g );

DECLARE_TUXEDO_SERVICE( GESTOR );
void implGESTOR::Execute( DOMNode* dnode, XMLGen* xml_g ) 
{   
    CTuxHelperClever helper;
    char* p0 = NULL;

    p0 = helper.walkTree( dnode, "cdOperacao", 0 );
    int opcao = atoi( p0 );
    ULOG( "Solicitou opcao [%d]", opcao );
    switch( opcao )
    {
        case 1 : BuscaGestorTelefone( dnode, xml_g );
                 break;
                             
        case 2 : BuscaGestorCPF( dnode, xml_g );
                 break;

        case 3 : BuscaGestorConta( dnode, xml_g  );
                 break;

        case 4 : BuscaEnderecoGestor( dnode, xml_g  );
                 break;

        case 5 : ValidaGestor( dnode, xml_g  );
                 break;

        case 6 : BuscaGestorTelefoneCPF_VOLE( dnode, xml_g  );
                 break;

        case 7 : BuscaGestorTelefoneSemCPF_VOLE( dnode, xml_g  );
                 break;

        case 8 : BuscaConsultor( dnode, xml_g  );
                 break;

        case 10 : ValidaGestorDuplicidade( dnode, xml_g  );
                  break;

        /*
        case 11 : BuscaGestorProtocolo( dnode, xml_g  );
                  break;
        */
        
        case 12 : BuscaGestorTelefoneCPF( dnode, xml_g  );
                 break;
                 
        case 13 : BuscaGestorTelefoneSemCPF( dnode, xml_g  );
                 break;
    }
    
    setStatusCode("11I0000","Sucesso");
}



void BuscaGestorTelefone( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * idContaPrm = helper.walkTree( dnode, "idConta", 0 );
    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;
    
    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );

    if ( idContaPrm[0] != 0x0 )
    {
       getGestorByPhoneNumberConta( idContaPrm, xml_g );
    }
    else
    {
       getGestorByPhoneNumber( nrLinhaPrm, onlyLinhaVivo, xml_g );
    }
}



void BuscaGestorCPF( DOMNode * dnode, XMLGen* xml_g  )
{
    CTuxHelperClever helper;

    char idPessoaGestorPrm[64];
    char * nrDocumentoPrm = helper.walkTree( dnode, "nrDocumento", 0 );
    memset( idPessoaGestorPrm, 0x0, sizeof(idPessoaGestorPrm) );
    
    getGestorByCpf( nrDocumentoPrm, idPessoaGestorPrm );
    xml_g->createTag( "PessoaGestorCPFVO" );
        xml_g->addItem( "idPessoaGestor", idPessoaGestorPrm );
    xml_g->closeTag();
}



void BuscaGestorTelefoneCPF_VOLE( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    char * nrDocumentoPrm   = helper.walkTree( dnode, "nrDocumento", 0 );
    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;
    
    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );

    getGestorByPhoneNumberCPF_VOLE( nrLinhaPrm, nrDocumentoPrm, onlyLinhaVivo, xml_g );
}



void BuscaGestorTelefoneCPF( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    char * nrDocumentoPrm   = helper.walkTree( dnode, "nrDocumento", 0 );
    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;
    
    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );

    getGestorByPhoneNumberCPF( nrLinhaPrm, nrDocumentoPrm, onlyLinhaVivo, xml_g );
}



void BuscaGestorTelefoneSemCPF_VOLE( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;

    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );
    
    getGestorByPhoneNumberWithOutCpf_VOLE( nrLinhaPrm, onlyLinhaVivo, xml_g );
}



void BuscaGestorTelefoneSemCPF( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;

    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );
    
    getGestorByPhoneNumberWithOutCpf( nrLinhaPrm, onlyLinhaVivo, xml_g );
}



void ValidaGestorDuplicidade( DOMNode * dnode, XMLGen* xml_g )
{
    CTuxHelperClever helper;

    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    char * nrDocumentoPrm   = helper.walkTree( dnode, "nrDocumento", 0 );

    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;

    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );
    
    if ( nrLinhaPrm[0] != 0x0 && nrDocumentoPrm[0] != 0x0 )
    {
        if (ValidaGestorByPhoneNumberCPF( nrLinhaPrm, nrDocumentoPrm ) < 0 )
        {
            xml_g->createTag( "RetornoVO" );
                xml_g->addItem( "MensagemRetorno", "true" );
                xml_g->addItem( "CodigoRetorno", 006 );
            xml_g->closeTag();
        }
        else
        {
            xml_g->createTag( "RetornoVO" );
                xml_g->addItem( "MensagemRetorno", "false" );
                xml_g->addItem( "CodigoRetorno", 000 );
            xml_g->closeTag();
        }
    }
    else
    {
        if ( nrLinhaPrm[0] != 0x0 && nrDocumentoPrm[0] == 0x0 )
        {
            if (ValidaGestorByPhoneNumberWithOutCpf( nrLinhaPrm ) < 0 )
            {
                xml_g->createTag( "RetornoVO" );
                    xml_g->addItem( "MensagemRetorno", "true" );
                    xml_g->addItem( "CodigoRetorno", 007 );
                xml_g->closeTag();
            }
            else
            {
                xml_g->createTag( "RetornoVO" );
                    xml_g->addItem( "MensagemRetorno", "false" );
                    xml_g->addItem( "CodigoRetorno", 000 );
                xml_g->closeTag();
            }
        }
        else
        {
            if ( nrLinhaPrm[0] == 0x0 && nrDocumentoPrm[0] != 0x0 )
            {
                if (ValidaGestorByCpf( nrDocumentoPrm ) < 0 )
                {
                    xml_g->createTag( "RetornoVO" );
                        xml_g->addItem( "MensagemRetorno", "true" );
                        xml_g->addItem( "CodigoRetorno", 002 );
                    xml_g->closeTag();
                }
                else
                {
                    xml_g->createTag( "RetornoVO" );
                        xml_g->addItem( "MensagemRetorno", "false" );
                        xml_g->addItem( "CodigoRetorno", 000 );
                    xml_g->closeTag();
                }
            }
        }
    }
    
}



void BuscaGestorConta( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * idContaPrm = helper.walkTree( dnode, "idConta", 0 );
    
    getGestorByConta( idContaPrm, xml_g );
}



void BuscaEnderecoGestor( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * idPessoaGestorPrm = helper.walkTree( dnode, "idPessoaGestor", 0 );
    
    getEnderecoGestor( idPessoaGestorPrm, xml_g );
}



void ValidaGestor( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * LinhaVivoPrm = helper.walkTree( dnode, "LinhaVivo", 0 );
    char * nrLinhaPrm   = helper.walkTree( dnode, "nrTelefone", 0 );
    char * nrDocumentoPrm = helper.walkTree( dnode, "nrDocumento", 0 );
    bool onlyLinhaVivo = ( strcmp(LinhaVivoPrm,"false") ) ? true : false ;
    
    ULOG( "onlyLinhaVivo [%d]", onlyLinhaVivo );
    
    validaGestorCPFTelefone( nrDocumentoPrm, nrLinhaPrm, onlyLinhaVivo, xml_g );
}



void BuscaConsultor( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * cnpjPrm = helper.walkTree( dnode, "nrDocumento", 0 );
    
    getConsultorByCnPj( cnpjPrm, xml_g );

}



void BuscaGestorProtocolo( DOMNode * dnode, XMLGen * xml_g )
{
    CTuxHelperClever helper;

    char * nrProtocolo = helper.walkTree( dnode, "nrProtocolo", 0 );
    
    getGestorProtocolo( nrProtocolo, xml_g );

}
