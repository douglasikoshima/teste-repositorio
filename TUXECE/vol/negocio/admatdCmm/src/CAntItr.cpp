#include <stdio.h>
#include "../include/CAntItr.h"

CIndicadorAnatelItr::CIndicadorAnatelItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrIndicadorAnatel    = NULL;
}

CIndicadorAnatelItr::~CIndicadorAnatelItr()
{
	ZeraIndicadorAnatel();
}

int CIndicadorAnatelItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CIndicadorAnatelItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CIndicadorAnatelItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CIndicadorAnatelItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CIndicadorAnatelItr::Quantidade( void )
{
	return _iQuantidade;
}

STIndicadorAnatelRegistro* CIndicadorAnatelItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrIndicadorAnatel[ _iPosicao ];
	else
		return 0;
}

STIndicadorAnatelRegistro* CIndicadorAnatelItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrIndicadorAnatel[ nPosicao ];

	}
	else
		return 0;
}

void CIndicadorAnatelItr::Add(
			char* cidIndicadorAnatel,
			char* csgIndicador,
			char* cdsIndicador )
{
	_iQuantidade++;
	stcrIndicadorAnatel = (struct STIndicadorAnatelRegistro*) realloc( stcrIndicadorAnatel, sizeof(STIndicadorAnatelRegistro)*_iQuantidade );
	strcpy( stcrIndicadorAnatel[_iQuantidade-1].cidIndicadorAnatel, cidIndicadorAnatel);
	strcpy( stcrIndicadorAnatel[_iQuantidade-1].csgIndicador, csgIndicador);
	strcpy( stcrIndicadorAnatel[_iQuantidade-1].cdsIndicador, cdsIndicador);
}


void CIndicadorAnatelItr::ZeraIndicadorAnatel( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrIndicadorAnatel );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrIndicadorAnatel    = NULL;
}
