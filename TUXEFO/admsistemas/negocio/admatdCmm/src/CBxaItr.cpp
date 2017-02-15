#include <stdio.h>
#include "../include/CBxaItr.h"

CBaixaItr::CBaixaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixa    = NULL;
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
}

CBaixaItr::~CBaixaItr()
{
	ZeraBaixa();
}

int CBaixaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CBaixaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CBaixaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CBaixaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CBaixaItr::Quantidade( void )
{
	return _iQuantidade;
}

STBaixaRegistro* CBaixaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrBaixa[ _iPosicao ];
	else
		return 0;
}

STBaixaRegistro* CBaixaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrBaixa[ nPosicao ];

	}
	else
		return 0;
}

void CBaixaItr::Add( char* cidBaixa,
			         char* cidBaixaPai,
			         char* cidNomeBaixa,
					 char* cnmBaixa,
					 char* cinDisponibilidade,
			         char* cdsPath,
					 int   iLevel )
{
	_iQuantidade++;
	stcrBaixa = (struct STBaixaRegistro*) realloc( stcrBaixa, sizeof(STBaixaRegistro)*_iQuantidade );
	strcpy( stcrBaixa[_iQuantidade-1].cidBaixa, cidBaixa);
	strcpy( stcrBaixa[_iQuantidade-1].cidBaixaPai, cidBaixaPai);
	strcpy( stcrBaixa[_iQuantidade-1].cidNomeBaixa, cidNomeBaixa);
	strcpy( stcrBaixa[_iQuantidade-1].cnmBaixa, cnmBaixa);
	strcpy( stcrBaixa[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrBaixa[_iQuantidade-1].cdsPath, cdsPath);
	stcrBaixa[_iQuantidade-1].iLevel = iLevel;
}

void CBaixaItr::Add( char* cidBaixa )
{
	_iQuantidade++;
	stcrBaixa = (struct STBaixaRegistro*) realloc( stcrBaixa, sizeof(STBaixaRegistro)*_iQuantidade );
	strcpy( stcrBaixa[_iQuantidade-1].cidBaixa, cidBaixa);
}


void CBaixaItr::ZeraBaixa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrBaixa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixa    = NULL;
}

void CBaixaItr::SetErro( char* cErro )
{
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
	if( strlen( cErro ) >= 255 )
	{
		strncpy( cErroGeral, &cErro[0], 255 );
		cErro[255] = 0;
	}
	else
		strcpy( cErroGeral, cErro );
}
