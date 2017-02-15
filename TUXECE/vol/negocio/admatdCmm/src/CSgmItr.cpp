#include <stdio.h>
#include "../include/CSgmItr.h"

CSegmentacaoItr::CSegmentacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSegmentacao    = NULL;
}

CSegmentacaoItr::~CSegmentacaoItr()
{
	ZeraSegmentacao();
}

int CSegmentacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSegmentacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CSegmentacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CSegmentacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSegmentacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STSegmentacaoRegistro* CSegmentacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSegmentacao[ _iPosicao ];
	else
		return 0;
}

STSegmentacaoRegistro* CSegmentacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSegmentacao[ nPosicao ];

	}
	else
		return 0;
}

void CSegmentacaoItr::Add(
			char* cidSegmentacao,
			char* csgSegmentacao,
			char* cdsSegmentacao,
			char* cvlPeso )
{
	_iQuantidade++;
	stcrSegmentacao = (struct STSegmentacaoRegistro*) realloc( stcrSegmentacao, sizeof(STSegmentacaoRegistro)*_iQuantidade );
	strcpy( stcrSegmentacao[_iQuantidade-1].cidSegmentacao, cidSegmentacao);
	strcpy( stcrSegmentacao[_iQuantidade-1].csgSegmentacao, csgSegmentacao);
	strcpy( stcrSegmentacao[_iQuantidade-1].cdsSegmentacao, cdsSegmentacao);
	strcpy( stcrSegmentacao[_iQuantidade-1].cvlPeso, cvlPeso);
}


void CSegmentacaoItr::ZeraSegmentacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSegmentacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSegmentacao    = NULL;
}
