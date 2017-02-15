#include <stdio.h>
#include "../include/CCmpClsItr.h"

CCampoClassificadorItr::CCampoClassificadorItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampoClassificador    = NULL;
}

CCampoClassificadorItr::~CCampoClassificadorItr()
{
	ZeraCampoClassificador();
}

int CCampoClassificadorItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCampoClassificadorItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCampoClassificadorItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCampoClassificadorItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCampoClassificadorItr::Quantidade( void )
{
	return _iQuantidade;
}

STCampoClassificadorRegistro* CCampoClassificadorItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCampoClassificador[ _iPosicao ];
	else
		return 0;
}

STCampoClassificadorRegistro* CCampoClassificadorItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCampoClassificador[ nPosicao ];

	}
	else
		return 0;
}

void CCampoClassificadorItr::Add(
			char* cidClassificadorCampo,
			char* cnmClassificadorCampo )
{
	_iQuantidade++;
	stcrCampoClassificador = (struct STCampoClassificadorRegistro*) realloc( stcrCampoClassificador, sizeof(STCampoClassificadorRegistro)*_iQuantidade );
	strcpy( stcrCampoClassificador[_iQuantidade-1].cidClassificadorCampo, cidClassificadorCampo);
	strcpy( stcrCampoClassificador[_iQuantidade-1].cnmClassificadorCampo, cnmClassificadorCampo);
}


void CCampoClassificadorItr::ZeraCampoClassificador( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCampoClassificador );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampoClassificador    = NULL;
}
