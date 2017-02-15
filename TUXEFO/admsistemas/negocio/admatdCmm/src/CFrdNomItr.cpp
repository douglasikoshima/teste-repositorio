#include <stdio.h>
#include "../include/CFrdNomItr.h"

CNomeFeriadoItr::CNomeFeriadoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNomeFeriado    = NULL;
}

CNomeFeriadoItr::~CNomeFeriadoItr()
{
	ZeraNomeFeriado();
}

int CNomeFeriadoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNomeFeriadoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNomeFeriadoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNomeFeriadoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNomeFeriadoItr::Quantidade( void )
{
	return _iQuantidade;
}

STNomeFeriadoRegistro* CNomeFeriadoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNomeFeriado[ _iPosicao ];
	else
		return 0;
}

STNomeFeriadoRegistro* CNomeFeriadoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNomeFeriado[ nPosicao ];

	}
	else
		return 0;
}

void CNomeFeriadoItr::Add(
			char* cidNomeFeriado,
			char* cdsFeriado )
{
	_iQuantidade++;
	stcrNomeFeriado = (struct STNomeFeriadoRegistro*) realloc( stcrNomeFeriado, sizeof(STNomeFeriadoRegistro)*_iQuantidade );
	strcpy( stcrNomeFeriado[_iQuantidade-1].cidNomeFeriado, cidNomeFeriado);
	strcpy( stcrNomeFeriado[_iQuantidade-1].cdsFeriado, cdsFeriado);
}


void CNomeFeriadoItr::ZeraNomeFeriado( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNomeFeriado );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNomeFeriado    = NULL;
}
