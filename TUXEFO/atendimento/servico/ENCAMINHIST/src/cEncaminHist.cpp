#include "../include/cEncaminHist.h"

//
// Prototipo Pro-C
//
extern int proCAtualizaHistorico
( 
    const unsigned long idGrupoPrm, 
    char * sMsgOut, 
    const char * sUsuarioPrm 
);

//
//------------------------------------------------------------------------
cEncaminHist::cEncaminHist( DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}



int cEncaminHist::Processa( void )
{

    AtualizaHistorico();

    return 0;
}




int cEncaminHist::AtualizaHistorico( void )
{
    char sMsgRet[ 256 ];

    memset( sMsgRet, 0x0, sizeof(sMsgRet) );

    proCAtualizaHistorico( idGrupo, sMsgRet, sUsuario );

    return 0;
}



void cEncaminHist::carregaDados( void )
{
	char * p;

    if (p = tx.walkTree( entrada, "idGrupo", 0 ), p ) 
    {
        idGrupo = strtoul( p,0,10 );
        XMLString::release( &p );
    }

    if (p = tx.walkTree( entrada, "dsLogin", 0 ), p ) 
    {
        strcpy( sUsuario, p );
        XMLString::release( &p );
    }

}

