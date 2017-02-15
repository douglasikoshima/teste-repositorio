#include "../include/cLstPerfilGru.h"

//
// Prototipos
//
extern void proCListaPerfilGru( unsigned long idContato,XMLGen * Saida );

//
//------------------------------------------------------------------------
cLstPerfilGru::cLstPerfilGru( unsigned long idUsuario, DOMNode * dnode, XMLGen * xml_g )
{
    
	entrada = dnode;
	saida   = xml_g;
    idUser = idUsuario;

    carregaDados();

}




void cLstPerfilGru::Processa( void )
{
    ListaPerfilHabilitado();
}




void cLstPerfilGru::ListaPerfilHabilitado( void )
{
    saida->createTag( "PerfilVariaveisVO" );
    saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        proCListaPerfilGru( idContato,saida );
    saida->closeTag();

}




void cLstPerfilGru::carregaDados( void )
{
    
    char * p;
    
    if (p = tx.walkTree( entrada, "contato", 0 ), p ) 
    {
        idContato = strtoul( p,0,10 );
        XMLString::release(&p);
    }
    
}
