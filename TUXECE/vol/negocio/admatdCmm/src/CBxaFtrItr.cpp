#include <stdio.h>
#include "../include/CBxaFtrItr.h"

CBaixaFiltroItr::CBaixaFiltroItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaFiltro    = NULL;
}

CBaixaFiltroItr::~CBaixaFiltroItr()
{
	ZeraBaixaFiltro();
}

int CBaixaFiltroItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CBaixaFiltroItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CBaixaFiltroItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CBaixaFiltroItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CBaixaFiltroItr::Quantidade( void )
{
	return _iQuantidade;
}

STBaixaFiltroRegistro* CBaixaFiltroItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrBaixaFiltro[ _iPosicao ];
	else
		return 0;
}

STBaixaFiltroRegistro* CBaixaFiltroItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrBaixaFiltro[ nPosicao ];

	}
	else
		return 0;
}

void CBaixaFiltroItr::Add( char* cidBaixaFiltro
							,char* cidBaixa
							,char* cidFiltro
							,char* csgFiltro
							,char* cdsFiltro
							,char* cvlPeso )
{
	_iQuantidade++;
	stcrBaixaFiltro = (struct STBaixaFiltroRegistro*) realloc( stcrBaixaFiltro, sizeof(STBaixaFiltroRegistro)*_iQuantidade );
	strcpy( stcrBaixaFiltro[_iQuantidade-1].cidBaixaFiltro, cidBaixaFiltro);
	strcpy( stcrBaixaFiltro[_iQuantidade-1].cidBaixa, cidBaixa);
	strcpy( stcrBaixaFiltro[_iQuantidade-1].cidFiltro, cidFiltro);
	strcpy( stcrBaixaFiltro[_iQuantidade-1].csgFiltro, csgFiltro);
	strcpy( stcrBaixaFiltro[_iQuantidade-1].cdsFiltro, cdsFiltro);
	strcpy( stcrBaixaFiltro[_iQuantidade-1].cvlPeso, cvlPeso);
}


void CBaixaFiltroItr::ZeraBaixaFiltro( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrBaixaFiltro );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaFiltro    = NULL;
}
