#include <stdio.h>
#include "../include/CAtvItr.h"

CAtribuicaoItr::CAtribuicaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAtribuicao    = NULL;
}

CAtribuicaoItr::~CAtribuicaoItr()
{
	ZeraAtribuicao();
}

int CAtribuicaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CAtribuicaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CAtribuicaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CAtribuicaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CAtribuicaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STAtribuicaoRegistro* CAtribuicaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrAtribuicao[ _iPosicao ];
	else
		return 0;
}

STAtribuicaoRegistro* CAtribuicaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrAtribuicao[ nPosicao ];

	}
	else
		return 0;
}

void CAtribuicaoItr::Add(
			char* cidAtribuicao,
			char* cnmAtribuicao )
{
	_iQuantidade++;
	stcrAtribuicao = (struct STAtribuicaoRegistro*) realloc( stcrAtribuicao, sizeof(STAtribuicaoRegistro)*_iQuantidade );
	strcpy( stcrAtribuicao[_iQuantidade-1].cidAtribuicao, cidAtribuicao);
	strcpy( stcrAtribuicao[_iQuantidade-1].cnmAtribuicao, cnmAtribuicao);
}


void CAtribuicaoItr::ZeraAtribuicao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrAtribuicao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAtribuicao    = NULL;
}
