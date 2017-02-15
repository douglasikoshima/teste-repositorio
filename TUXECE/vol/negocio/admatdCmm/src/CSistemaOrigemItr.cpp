#include <stdio.h>
#include "../include/CSistemaOrigemItr.h"

CSistemaOrigemItr::CSistemaOrigemItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSistemaOrigem    = NULL;
}

CSistemaOrigemItr::~CSistemaOrigemItr()
{
	ZeraSistemaOrigem();
}

int CSistemaOrigemItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSistemaOrigemItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CSistemaOrigemItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CSistemaOrigemItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSistemaOrigemItr::Quantidade( void )
{
	return _iQuantidade;
}

STSistemaOrigemRegistro* CSistemaOrigemItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSistemaOrigem[ _iPosicao ];
	else
		return 0;
}

STSistemaOrigemRegistro* CSistemaOrigemItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSistemaOrigem[ nPosicao ];

	}
	else
		return 0;
}

void CSistemaOrigemItr::Add(
			char* cidSistemaOrigem,
			char* cnmSistemaOrigem )
{
	_iQuantidade++;
	stcrSistemaOrigem = (struct STSistemaOrigemRegistro*) realloc( stcrSistemaOrigem, sizeof(STSistemaOrigemRegistro)*_iQuantidade );
	strcpy( stcrSistemaOrigem[_iQuantidade-1].cidSistemaOrigem, cidSistemaOrigem);
	strcpy( stcrSistemaOrigem[_iQuantidade-1].cnmSistemaOrigem, cnmSistemaOrigem);
}


void CSistemaOrigemItr::ZeraSistemaOrigem( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSistemaOrigem );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSistemaOrigem    = NULL;
}
