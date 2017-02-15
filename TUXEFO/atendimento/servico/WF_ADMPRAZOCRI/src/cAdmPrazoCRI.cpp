#include "../include/cAdmPrazoCRI.h"

//
// Prototipos
//
extern bool proCGetPrazoCRI( XMLGen * saida );

extern bool proCPutPrazoCRI
( 
    unsigned long idUserPrm, 
    unsigned long valorPrm,
    XMLGen * saida 
);

//
//------------------------------------------------------------------------
cAdmPrazoCRI::cAdmPrazoCRI( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;
    idUser = idUsuario;

    carregaDados();
}




void cAdmPrazoCRI::Processa( void )
{

    if ( operacao == 1 ) // Consulta
    {
        GetValor();
    }
    else  // Atualiza
    {
        PutValor();
    }

}




void cAdmPrazoCRI::GetValor( void )
{

    proCGetPrazoCRI( saida );

}




void cAdmPrazoCRI::PutValor( void )
{

    proCPutPrazoCRI( idUser,valor,saida );

}




void cAdmPrazoCRI::carregaDados( void )
{
	char          * p;

    p = tx.walkTree( entrada,"acaoExecucao",0 );
    if ( p != NULL )
    {
        operacao = atoi( p );
        XMLString::release(&p);
    }

    p = tx.walkTree( entrada,"mensagem",0 );
    if ( p != NULL )
    {
        valor = strtoul( p,0,10 );
        XMLString::release(&p);
    }

}
