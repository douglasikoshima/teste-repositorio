#include <stdio.h>
#include "../include/CBxaAntItr.h"

CIndicadorAnatelBaixaItr::CIndicadorAnatelBaixaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrIndicadorAnatelBaixa    = NULL;
}

CIndicadorAnatelBaixaItr::~CIndicadorAnatelBaixaItr()
{
	ZeraIndicadorAnatelBaixa();
}

int CIndicadorAnatelBaixaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CIndicadorAnatelBaixaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CIndicadorAnatelBaixaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CIndicadorAnatelBaixaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CIndicadorAnatelBaixaItr::Quantidade( void )
{
	return _iQuantidade;
}

STIndicadorAnatelBaixaRegistro* CIndicadorAnatelBaixaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrIndicadorAnatelBaixa[ _iPosicao ];
	else
		return 0;
}

STIndicadorAnatelBaixaRegistro* CIndicadorAnatelBaixaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrIndicadorAnatelBaixa[ nPosicao ];

	}
	else
		return 0;
}

void CIndicadorAnatelBaixaItr::Add( char* cidIndicadorAnatelBaixa
								   ,char* cidBaixa
								   ,char* cidIndicadorAnatel
								   ,char* csgIndicador
								   ,char* cdsIndicador )
{
	_iQuantidade++;
	stcrIndicadorAnatelBaixa = (struct STIndicadorAnatelBaixaRegistro*) realloc( stcrIndicadorAnatelBaixa, sizeof(STIndicadorAnatelBaixaRegistro)*_iQuantidade );
	strcpy( stcrIndicadorAnatelBaixa[_iQuantidade-1].cidIndicadorAnatelBaixa, cidIndicadorAnatelBaixa);
	strcpy( stcrIndicadorAnatelBaixa[_iQuantidade-1].cidBaixa, cidBaixa);
	strcpy( stcrIndicadorAnatelBaixa[_iQuantidade-1].cidIndicadorAnatel, cidIndicadorAnatel);
	strcpy( stcrIndicadorAnatelBaixa[_iQuantidade-1].csgIndicador, csgIndicador);
	strcpy( stcrIndicadorAnatelBaixa[_iQuantidade-1].cdsIndicador, cdsIndicador);
}


void CIndicadorAnatelBaixaItr::ZeraIndicadorAnatelBaixa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrIndicadorAnatelBaixa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrIndicadorAnatelBaixa    = NULL;
}
