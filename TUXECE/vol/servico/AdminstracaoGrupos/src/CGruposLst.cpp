/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Miguel Benaventes
 * @version $Revision: 1.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/08/27 19:27:30 $
 **/

#include "../include/selectData.h"
#include "../include/CGruposLst.h"


CGruposLst::CGruposLst()
{
	_iQuantidadeLst = 0L;
	_iPosicaoLst    = 0L;
	pGrupoWrkLst    = NULL;
}

CGruposLst::~CGruposLst()
{
	ZeraGruposListaXML();
}

long CGruposLst::Primeiro( void )
{
	_iPosicaoLst = 0L;
	return _iPosicaoLst;
}

long CGruposLst::Proximo( void )
{
	if( _iQuantidadeLst > 0L )
	{
		if( _iPosicaoLst < (_iQuantidadeLst-1) )
			_iPosicaoLst++;
	}
	return _iPosicaoLst;
}

long CGruposLst::Anterior( void )
{
	if( _iPosicaoLst > 0L )
		_iPosicaoLst--;

	return _iPosicaoLst;
}

long CGruposLst::Ultimo( void )
{

	if( _iQuantidadeLst > 0L )
		_iPosicaoLst = _iQuantidadeLst-1L;
	else
		_iPosicaoLst = 0L;

	return _iPosicaoLst;
}

long CGruposLst::Quantidade( void )
{
	return _iQuantidadeLst;
}

GrupoWrkLst * CGruposLst::Registro( void )
{
	if( _iQuantidadeLst > 0 )
    {
		return &pGrupoWrkLst[ _iPosicaoLst ];
    }

	return 0;
}

GrupoWrkLst * CGruposLst::Registro( long nPosicao )
{
	if( _iQuantidadeLst > 0L )
	{
		if( nPosicao >= _iQuantidadeLst )
			nPosicao = Ultimo();

		return &pGrupoWrkLst[ nPosicao ];
	}

    return 0;
}

void CGruposLst::AddGrpLst(  long idGrupoParam )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Junho, 2006 - Cassio
    if ( (_iQuantidadeLst % tamBloco) == 0 )
    {
        int Resto = _iQuantidadeLst / tamBloco;
        int tamBlocoAtu = (Resto+1) * tamBloco;

        pGrupoWrkLst = (struct GrupoWrkLst *) realloc( pGrupoWrkLst, sizeof(GrupoWrkLst)*tamBlocoAtu );
    }

    //pGrupoWrkLst = (struct GrupoWrkLst *) realloc( pGrupoWrkLst, sizeof(GrupoWrkLst)*_iQuantidadeLst );
    pGrupoWrkLst[_iQuantidadeLst].idGrupoMemLst = idGrupoParam;
    pGrupoWrkLst[_iQuantidadeLst].flagSalvar = 1L;  // TRUE - Inicia-se salvando todos

    _iQuantidadeLst++;
}


void CGruposLst::ZeraGruposListaXML( void )
{
    ULOG_START("CGruposLst::ZeraGruposListaXML()");    
   
	if( _iQuantidadeLst > 0L )
	{
		free( pGrupoWrkLst );
	    pGrupoWrkLst   = NULL;
	}
	_iQuantidadeLst = 0L;
	_iPosicaoLst    = 0L;
	
	ULOG_END("CGruposLst::ZeraGruposListaXML()");    
}

void CGruposLst::ListaGrupos( DOMNode*node )
{
    ULOG_START("CGruposLst::ListaGrupos()");

	DOMNode * subnode;
	int stItem,item;
    long idGrupo;
	char * pdata;

	item=0;

    for ( ;; )
    {
        stItem=0;
        if( !(subnode=walkDOM(node,"GrupoVO",&stItem,item++)) )
            break;

        pdata=walkTree(subnode,"codigo",0);
        idGrupo = atol(pdata);
        XMLString::release(&pdata);
      
        /* deixa em memoria todos os grupos que serao processados */
        AddGrpLst( idGrupo );
   }

   ULOG_END("CGruposLst::ListaGrupos()");   
}
