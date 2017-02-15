#include <stdio.h>
#include "../include/CBxaNomItr.h"

CNomeBaixaItr::CNomeBaixaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNomeBaixa    = NULL;
}

CNomeBaixaItr::~CNomeBaixaItr()
{
	ZeraNomeBaixa();
}

int CNomeBaixaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNomeBaixaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNomeBaixaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNomeBaixaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNomeBaixaItr::Quantidade( void )
{
	return _iQuantidade;
}

STNomeBaixaRegistro* CNomeBaixaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNomeBaixa[ _iPosicao ];
	else
		return 0;
}

STNomeBaixaRegistro* CNomeBaixaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNomeBaixa[ nPosicao ];

	}
	else
		return 0;
}

void CNomeBaixaItr::Add(
			char* cidNomeBaixa,
			char* cnmBaixa )
{
	_iQuantidade++;
	stcrNomeBaixa = (struct STNomeBaixaRegistro*) realloc( stcrNomeBaixa, sizeof(STNomeBaixaRegistro)*_iQuantidade );
	strcpy( stcrNomeBaixa[_iQuantidade-1].cidNomeBaixa, cidNomeBaixa);
	strcpy( stcrNomeBaixa[_iQuantidade-1].cnmBaixa, cnmBaixa);
}


void CNomeBaixaItr::ZeraNomeBaixa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNomeBaixa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNomeBaixa    = NULL;
}
