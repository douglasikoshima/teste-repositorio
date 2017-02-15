#include <stdio.h>
#include "../include/CCAItr.h"

CCargoAtribuicaoItr::CCargoAtribuicaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCargoAtribuicao    = NULL;
}

CCargoAtribuicaoItr::~CCargoAtribuicaoItr()
{
	ZeraCargoAtribuicao();
}

int CCargoAtribuicaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCargoAtribuicaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCargoAtribuicaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCargoAtribuicaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCargoAtribuicaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STCargoAtribuicaoRegistro* CCargoAtribuicaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCargoAtribuicao[ _iPosicao ];
	else
		return 0;
}

STCargoAtribuicaoRegistro* CCargoAtribuicaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCargoAtribuicao[ nPosicao ];

	}
	else
		return 0;
}

void CCargoAtribuicaoItr::Add(
			char* cidCargoAtribuicao,
			char* cidCargo,
			char* cidAtribuicao )
{
	_iQuantidade++;
	stcrCargoAtribuicao = (struct STCargoAtribuicaoRegistro*) realloc( stcrCargoAtribuicao, sizeof(STCargoAtribuicaoRegistro)*_iQuantidade );
	strcpy( stcrCargoAtribuicao[_iQuantidade-1].cidCargoAtribuicao, cidCargoAtribuicao);
	strcpy( stcrCargoAtribuicao[_iQuantidade-1].cidCargo, cidCargo);
	strcpy( stcrCargoAtribuicao[_iQuantidade-1].cidAtribuicao, cidAtribuicao);
}


void CCargoAtribuicaoItr::ZeraCargoAtribuicao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCargoAtribuicao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCargoAtribuicao    = NULL;
}
