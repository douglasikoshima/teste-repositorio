#include "../include/cLstPerfil.h"

//
// Prototipos
//
//extern void proCListaPerfil( XMLGen * Saida );
extern void proCListaPerfil
( 
    st_LstPerfil * dados,
    st_vlLstPerfil * status,
    XMLGen * Saida  
);

extern void proCListaNomePerfil
( 
    char * sNomePerfil, 
    st_LstPerfil * dados,
    st_vlLstPerfil * status,
    XMLGen * Saida 
);
//
//------------------------------------------------------------------------
cLstPerfil::cLstPerfil( DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;
    sNomePerfil[0] = 0x0;

	carregaDados();
}




void cLstPerfil::Perfil( void )
{
    saida->createTag( "PerfilVariaveisVO" );
    saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        if ( !strlen(sNomePerfil) )
        {
            ListaTodoPerfil( saida );
        }
        else
        {
            ListaNomePerfil( saida );
        }
    saida->closeTag();
}




void cLstPerfil::ListaTodoPerfil( XMLGen * xml_g  )
{
    proCListaPerfil( &m_stDados,&m_vlDados,xml_g );
}




void cLstPerfil::ListaNomePerfil( XMLGen * xml_g  )
{
    proCListaNomePerfil( sNomePerfil,&m_stDados,&m_vlDados,xml_g );
}




void cLstPerfil::carregaDados( void )
{

	char * p;

	if (p = tx.walkTree( entrada, "nmPerfil", 0 ), p ) 
	{
        if ( strlen(p) > 0 )
        {
            strcpy( sNomePerfil,p );
        }
		XMLString::release(&p);
	}

    if (p=tx.walkTree( entrada, "inTotal", 0 ),p) 
    {
        m_stDados.bloco = atoi(p);
        m_vlDados.bloco = 1;
        XMLString::release(&p);
    }
    
    if (p=tx.walkTree( entrada, "qtdLinhasBloco", 0 ),p) // num linhas no bloco
    {
        m_stDados.qtdLinhasBloco = atoi(p);
        m_vlDados.qtdLinhasBloco = 1;
        XMLString::release(&p);
    }
    else
    {
        m_stDados.qtdLinhasBloco = NUM_LINHAS;
    }

}

