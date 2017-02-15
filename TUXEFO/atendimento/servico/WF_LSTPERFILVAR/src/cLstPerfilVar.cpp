#include "../include/cLstPerfilVar.h"

//
// Prototipos
//
extern bool proCGetPerfil( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilTipoLinha( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilSegmentacao( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilTipoCarteira( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilTipoCanal( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilProcedencia( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilNatureza( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilTipoCliente( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilRegional( const unsigned long idPerfilPrm, XMLGen * xml_g );
extern bool proCListaPerfilGrupoAbertura( const unsigned long idPerfilPrm, XMLGen * xml_g );

extern bool proCListaPerfilTipoLinhaAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilSegmentacaoAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilTipoCarteiraAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilTipoCanalAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilProcedenciaAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilNaturezaAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilTipoClienteAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilRegionalAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );
extern bool proCListaPerfilGrupoAberturaAssoc( const unsigned long idPerfilPrm, XMLGen * Saida );

//
//------------------------------------------------------------------------
cLstPerfilVar::cLstPerfilVar( DOMNode * dnode, XMLGen * xml_g )
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}




void cLstPerfilVar::ListaPerfil( void )
{

    saida->createTag( "PerfilVariaveisVO" );
    saida->addProp( "xmlns","admsistemas.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns2","cliente.fo.vivo.com.br/vo" );
    saida->addProp( "xmlns:ns3","workflow.fo.vivo.com.br/vo" );
        proCGetPerfil( idPerfil,saida );
        saida->createTag( "PerfilVariaveisDisponiveisVO" );
            ListaPerfilTipoCliente( saida );
            ListaPerfilNatureza( saida );
            ListaPerfilTipoCarteira( saida );
            ListaPerfilSegmentacao( saida );
            ListaPerfilProcedencia( saida );
            ListaPerfilRegional( saida );

            ListaPerfilGrupoAbertura( saida );

            ListaPerfilTipoLinha( saida );
            ListaPerfilTipoCanal( saida );
        saida->closeTag();
        saida->createTag( "PerfilVariaveisAssociadasVO" );
            ListaPerfilTipoClienteAssoc( saida );
            ListaPerfilNaturezaAssoc( saida );
            ListaPerfilTipoCarteiraAssoc( saida );
            ListaPerfilSegmentacaoAssoc( saida );
            ListaPerfilProcedenciaAssoc( saida );
            ListaPerfilRegionalAssoc( saida );

            ListaPerfilGrupoAberturaAssoc( saida );

            ListaPerfilTipoLinhaAssoc( saida );
            ListaPerfilTipoCanalAssoc( saida );
        saida->closeTag();
    saida->closeTag();

}




bool cLstPerfilVar::ListaPerfilTipoLinha( XMLGen * xml_g  )
{
    return proCListaPerfilTipoLinha( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoLinhaAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilTipoLinhaAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilSegmentacao( XMLGen * xml_g  )
{
    return proCListaPerfilSegmentacao( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilSegmentacaoAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilSegmentacaoAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoCarteira( XMLGen * xml_g  )
{
    return proCListaPerfilTipoCarteira( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoCarteiraAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilTipoCarteiraAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoCanal( XMLGen * xml_g  )
{
    return proCListaPerfilTipoCanal( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoCanalAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilTipoCanalAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilProcedencia( XMLGen * xml_g  )
{
    return proCListaPerfilProcedencia( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilProcedenciaAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilProcedenciaAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilNatureza( XMLGen * xml_g  )
{
    return proCListaPerfilNatureza( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilNaturezaAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilNaturezaAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoCliente( XMLGen * xml_g  )
{
    return proCListaPerfilTipoCliente( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilTipoClienteAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilTipoClienteAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilRegional( XMLGen * xml_g  )
{
    return proCListaPerfilRegional( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilRegionalAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilRegionalAssoc( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilGrupoAbertura( XMLGen * xml_g  )
{
    return proCListaPerfilGrupoAbertura( idPerfil, xml_g );
}




bool cLstPerfilVar::ListaPerfilGrupoAberturaAssoc( XMLGen * xml_g  )
{
    return proCListaPerfilGrupoAberturaAssoc( idPerfil, xml_g );
}




void cLstPerfilVar::carregaDados( void )
{

	char * p;

	if (p = tx.walkTree( entrada, "idPerfil", 0 ), p ) 
	{
		idPerfil = strtoul( p,0,10 );
		XMLString::release(&p);
	}

}

