#include "../include/cMotivosAssoc.h"

//
// Prototipos
//
extern bool proCListaNaoAssociados( const unsigned long idMotivoPrm,  XMLGen * Saida );
extern bool proCListaAssociados( const unsigned long idMotivoPrm,  XMLGen * Saida );
extern short proCAssociaAtividades
( 
 unsigned long idMotivoPrm, 
 unsigned long idAtividadePrm  
);
extern void proCOrganizaMotivo( unsigned long idMotivoPrm );


//
//------------------------------------------------------------------------
cMotivosAssoc::cMotivosAssoc( DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}



//
// iOperacao == 0 - Insere
// iOperacao == 1 - Consulta
short cMotivosAssoc::Processa( void )
{
    short retorno;

    if ( iOperacao == 0 )
    {
        saida->createTag( "WFManterMotivosVO" );
        saida->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
            saida->createTag( "acoesDisponivel" );
                retorno = ListaNaoAssociados();
            saida->closeTag();
            saida->createTag( "acoesAssociado" );
                retorno = ListaAssociados();
            saida->closeTag();
        saida->closeTag();
    }
    else
    {
        retorno = AssociaMotivos();
    }


    return retorno;
}




short cMotivosAssoc::ListaNaoAssociados( void )
{
    return proCListaNaoAssociados( idMotivo,saida );
}




short cMotivosAssoc::ListaAssociados( void )
{
    return proCListaAssociados( idMotivo,saida );
}




short cMotivosAssoc::AssociaMotivos( void )
{

    int i;

    proCOrganizaMotivo( idMotivo );

    for ( i=0; i < pAtividade.size() ; i++ )
    {
        proCAssociaAtividades( 
            idMotivo, 
            pAtividade.at(i) );
    }
    
    return 0;
}




void cMotivosAssoc::carregaDados( void )
{
    int             i;
	char          * p;

    if (p = tx.walkTree( entrada, "idMotivo", 0 ), p ) 
    {
        idMotivo = strtoul( p,0,10 );
        XMLString::release(&p);
    }

    for ( i=0;;i++ )  
    {
        p = tx.walkTree( entrada,"idAtividade",i );
        if( p == NULL )
        {
            break;
        }
        pAtividade.push_back( strtoul(p,0,10) );
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "operacao", 0 ), p ) 
    {
        iOperacao = atoi( p );
        XMLString::release(&p);
    }

}

