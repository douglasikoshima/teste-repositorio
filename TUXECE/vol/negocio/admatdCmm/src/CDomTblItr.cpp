#include <stdio.h>
#include "../include/CDomTblItr.h"

CTabelaDominioItr::CTabelaDominioItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTabelaDominio    = NULL;
}

CTabelaDominioItr::~CTabelaDominioItr()
{
	ZeraTabelaDominio();
}

int CTabelaDominioItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTabelaDominioItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTabelaDominioItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTabelaDominioItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTabelaDominioItr::Quantidade( void )
{
	return _iQuantidade;
}

STTabelaDominioRegistro* CTabelaDominioItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTabelaDominio[ _iPosicao ];
	else
		return 0;
}

STTabelaDominioRegistro* CTabelaDominioItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTabelaDominio[ nPosicao ];

	}
	else
		return 0;
}

void CTabelaDominioItr::Add(
			char* cidTabelaDominio,
			char* cnmTabelaDominio )
{
	_iQuantidade++;
	stcrTabelaDominio = (struct STTabelaDominioRegistro*) realloc( stcrTabelaDominio, sizeof(STTabelaDominioRegistro)*_iQuantidade );
	strcpy( stcrTabelaDominio[_iQuantidade-1].cidTabelaDominio, cidTabelaDominio);
	strcpy( stcrTabelaDominio[_iQuantidade-1].cnmTabelaDominio, cnmTabelaDominio);
}


void CTabelaDominioItr::ZeraTabelaDominio( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTabelaDominio );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTabelaDominio    = NULL;
}
