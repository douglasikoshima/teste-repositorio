/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Miguel Benaventes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:34 $
 **/

#include <stdio.h>
#include "../include/CGrupos.h"

CGrupos::CGrupos()
{
	_iQuantidade = 0L;
	_iPosicao    = 0L;
	pGrupoWrk    = NULL;
}

CGrupos::~CGrupos()
{
	ZeraGruposEncontrados();
}

long CGrupos::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

long CGrupos::Proximo( void )
{
	if( _iQuantidade > 0L )
	{
		if( _iPosicao < (_iQuantidade-1L) )
			_iPosicao++;
	}
	return _iPosicao;
}

long CGrupos::Anterior( void )
{
	if( _iPosicao > 0L )
		_iPosicao--;

	return _iPosicao;
}

long CGrupos::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1L;
	else
		_iPosicao = 0;

	return _iPosicao;
}

long CGrupos::Quantidade( void )
{
	return _iQuantidade;
}

GrupoWrk * CGrupos::Registro( void )
{
	if( _iQuantidade > 0 )
		return &pGrupoWrk[ _iPosicao ];
	else
		return 0;
}

GrupoWrk * CGrupos::Registro( long nPosicao )
{
	if( _iQuantidade > 0L )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &pGrupoWrk[ nPosicao ];

	}
	else
		return 0;
}

void CGrupos::Add( long idGrupoParam, 
                   long idContatoGrupoParam, 
                   long idSequenciaParam )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Junho, 2006 - Cassio
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

        pGrupoWrk = (struct GrupoWrk *) realloc( pGrupoWrk, sizeof(GrupoWrk)*tamBlocoAtu );
    }

    pGrupoWrk[_iQuantidade].idSeqMem = idSequenciaParam;
    pGrupoWrk[_iQuantidade].idGrupoMem = idGrupoParam;
    pGrupoWrk[_iQuantidade].idContatoGrupoMem = idContatoGrupoParam;
    pGrupoWrk[_iQuantidade].flagListaXml = 0L;

    _iQuantidade++;
}


void CGrupos::ZeraGruposEncontrados( void )
{
	if( _iQuantidade > 0 )
	{
		free( pGrupoWrk );
	}
	_iQuantidade = 0L;
	_iPosicao    = 0L;
	pGrupoWrk   = NULL;
}


