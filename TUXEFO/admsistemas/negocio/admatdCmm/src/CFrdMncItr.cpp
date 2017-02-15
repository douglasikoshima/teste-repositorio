#include <stdio.h>
#include "../include/CFrdMncItr.h"

CMunicipioFeriadoItr::CMunicipioFeriadoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMunicipioFeriado    = NULL;
}

CMunicipioFeriadoItr::~CMunicipioFeriadoItr()
{
	ZeraMunicipioFeriado();
}

int CMunicipioFeriadoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CMunicipioFeriadoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CMunicipioFeriadoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CMunicipioFeriadoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CMunicipioFeriadoItr::Quantidade( void )
{
	return _iQuantidade;
}

STMunicipioFeriadoRegistro* CMunicipioFeriadoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrMunicipioFeriado[ _iPosicao ];
	else
		return 0;
}

STMunicipioFeriadoRegistro* CMunicipioFeriadoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrMunicipioFeriado[ nPosicao ];

	}
	else
		return 0;
}

void CMunicipioFeriadoItr::Add(
			char* cidMunicipioFeriado,
			char* cidMunicipio,
			char* cidFeriado )
{
	_iQuantidade++;
	stcrMunicipioFeriado = (struct STMunicipioFeriadoRegistro*) realloc( stcrMunicipioFeriado, sizeof(STMunicipioFeriadoRegistro)*_iQuantidade );
	strcpy( stcrMunicipioFeriado[_iQuantidade-1].cidMunicipioFeriado, cidMunicipioFeriado);
	strcpy( stcrMunicipioFeriado[_iQuantidade-1].cidMunicipio, cidMunicipio);
	strcpy( stcrMunicipioFeriado[_iQuantidade-1].cidFeriado, cidFeriado);
}


void CMunicipioFeriadoItr::ZeraMunicipioFeriado( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrMunicipioFeriado );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMunicipioFeriado    = NULL;
}
