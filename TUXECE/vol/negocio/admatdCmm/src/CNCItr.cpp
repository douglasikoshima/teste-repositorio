#include <stdio.h>
#include "../include/CNCItr.h"

CNivelCargoItr::CNivelCargoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivelCargo    = NULL;
}

CNivelCargoItr::~CNivelCargoItr()
{
	ZeraNivelCargo();
}

int CNivelCargoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNivelCargoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNivelCargoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNivelCargoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNivelCargoItr::Quantidade( void )
{
	return _iQuantidade;
}

STNivelCargoRegistro* CNivelCargoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNivelCargo[ _iPosicao ];
	else
		return 0;
}

STNivelCargoRegistro* CNivelCargoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNivelCargo[ nPosicao ];

	}
	else
		return 0;
}

void CNivelCargoItr::Add(
			char* cidNivelCargo,
			char* cidNivel,
			char* cidCargo )
{
	_iQuantidade++;
	stcrNivelCargo = (struct STNivelCargoRegistro*) realloc( stcrNivelCargo, sizeof(STNivelCargoRegistro)*_iQuantidade );
	strcpy( stcrNivelCargo[_iQuantidade-1].cidNivelCargo, cidNivelCargo);
	strcpy( stcrNivelCargo[_iQuantidade-1].cidNivel, cidNivel);
	strcpy( stcrNivelCargo[_iQuantidade-1].cidCargo, cidCargo);
}


void CNivelCargoItr::ZeraNivelCargo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNivelCargo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivelCargo    = NULL;
}
