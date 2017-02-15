#include <stdio.h>
#include "../include/CBxaHieItr.h"

CBaixaHierarquiaItr::CBaixaHierarquiaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaHierarquia    = NULL;
}

CBaixaHierarquiaItr::~CBaixaHierarquiaItr()
{
	ZeraBaixaHierarquia();
}

int CBaixaHierarquiaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CBaixaHierarquiaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CBaixaHierarquiaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CBaixaHierarquiaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CBaixaHierarquiaItr::Quantidade( void )
{
	return _iQuantidade;
}

STBaixaHierarquiaRegistro* CBaixaHierarquiaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrBaixaHierarquia[ _iPosicao ];
	else
		return 0;
}

STBaixaHierarquiaRegistro* CBaixaHierarquiaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrBaixaHierarquia[ nPosicao ];

	}
	else
		return 0;
}

void CBaixaHierarquiaItr::Add(
			char* cidBaixa,
			char* cidBaixaHierarquia )
{
	_iQuantidade++;
	stcrBaixaHierarquia = (struct STBaixaHierarquiaRegistro*) realloc( stcrBaixaHierarquia, sizeof(STBaixaHierarquiaRegistro)*_iQuantidade );
	strcpy( stcrBaixaHierarquia[_iQuantidade-1].cidBaixa, cidBaixa);
	strcpy( stcrBaixaHierarquia[_iQuantidade-1].cidBaixaHierarquia, cidBaixaHierarquia);
}


void CBaixaHierarquiaItr::ZeraBaixaHierarquia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrBaixaHierarquia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaHierarquia    = NULL;
}
