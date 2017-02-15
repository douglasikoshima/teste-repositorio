#include "../include/cRmvPerfil.h"

//
// Prototipos
//
extern short proCRemovePerfil( unsigned long idPerfilPrm );

//
//------------------------------------------------------------------------
cRmvPerfil::cRmvPerfil( DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}




short cRmvPerfil::Processa( void )
{
    return RemovePerfil();
}




short cRmvPerfil::RemovePerfil( void )
{
    return proCRemovePerfil( idPerfil );
}




void cRmvPerfil::carregaDados( void )
{
	char          * p;

    if (p = tx.walkTree( entrada, "idPerfil", 0 ), p ) 
    {
        idPerfil = strtoul( p,0,10 );
        XMLString::release(&p);
    }


}

