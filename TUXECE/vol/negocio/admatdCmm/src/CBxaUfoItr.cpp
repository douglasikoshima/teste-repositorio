#include <stdio.h>
#include "../include/CBxaUfoItr.h"

CBaixaUfoperadoraItr::CBaixaUfoperadoraItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaUfoperadora    = NULL;
}

CBaixaUfoperadoraItr::~CBaixaUfoperadoraItr()
{
	ZeraBaixaUfoperadora();
}

int CBaixaUfoperadoraItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CBaixaUfoperadoraItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CBaixaUfoperadoraItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CBaixaUfoperadoraItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CBaixaUfoperadoraItr::Quantidade( void )
{
	return _iQuantidade;
}

STBaixaUfoperadoraRegistro* CBaixaUfoperadoraItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrBaixaUfoperadora[ _iPosicao ];
	else
		return 0;
}

STBaixaUfoperadoraRegistro* CBaixaUfoperadoraItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrBaixaUfoperadora[ nPosicao ];

	}
	else
		return 0;
}

void CBaixaUfoperadoraItr::Add(
			char* cidBaixaUFOperadora,
			char* cidBaixa,
			char* cidUFOperadora,
			char* cdsUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* csgUF )
{
	_iQuantidade++;
	stcrBaixaUfoperadora = (struct STBaixaUfoperadoraRegistro*) realloc( stcrBaixaUfoperadora, sizeof(STBaixaUfoperadoraRegistro)*_iQuantidade );
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cidBaixaUFOperadora, cidBaixaUFOperadora);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cidBaixa, cidBaixa);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cdtInicioVigencia, cdtInicioVigencia);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cdtFimVigencia, cdtFimVigencia);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrBaixaUfoperadora[_iQuantidade-1].csgUF, csgUF);
}


void CBaixaUfoperadoraItr::ZeraBaixaUfoperadora( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrBaixaUfoperadora );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaUfoperadora    = NULL;
}
