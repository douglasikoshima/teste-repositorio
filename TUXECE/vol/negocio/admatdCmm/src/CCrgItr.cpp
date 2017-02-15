#include <stdio.h>
#include "../include/CCrgItr.h"

CCargoItr::CCargoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCargo    = NULL;
}

CCargoItr::~CCargoItr()
{
	ZeraCargo();
}

int CCargoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCargoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCargoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCargoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCargoItr::Quantidade( void )
{
	return _iQuantidade;
}

STCargoRegistro* CCargoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCargo[ _iPosicao ];
	else
		return 0;
}

STCargoRegistro* CCargoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCargo[ nPosicao ];

	}
	else
		return 0;
}

void CCargoItr::Add(
			char* cidCargo,
			char* cnmCargo )
{
	_iQuantidade++;
	stcrCargo = (struct STCargoRegistro*) realloc( stcrCargo, sizeof(STCargoRegistro)*_iQuantidade );
	strcpy( stcrCargo[_iQuantidade-1].cidCargo, cidCargo);
	strcpy( stcrCargo[_iQuantidade-1].cnmCargo, cnmCargo);
}


void CCargoItr::ZeraCargo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCargo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCargo    = NULL;
}
