#include "../include/cLstPerfilAssoc.h"

//
// Prototipos
//
extern void proCListaPerfilAssoc( unsigned long idContato, XMLGen * Saida );

//
//------------------------------------------------------------------------
cLstPerfilAssoc::cLstPerfilAssoc( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;
    idUser = idUsuario;

    carregaDados();
}




void cLstPerfilAssoc::Processa( void )
{
    ListaPerfilHabilitado();
}




void cLstPerfilAssoc::ListaPerfilHabilitado( void )
{    
    saida->createTag( "PerfilVariaveisVO" );
    saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        proCListaPerfilAssoc( idContato,saida );
    saida->closeTag();
}




void cLstPerfilAssoc::carregaDados( void )
{
    
    char * p;
    
    if (p = tx.walkTree( entrada, "contato", 0 ), p ) 
    {
        idContato = strtoul( p,0,10 );
        XMLString::release(&p);
    }
    
}
