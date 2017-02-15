#include <stdio.h>
#include "../include/CCmpFseItr.h"

CFaseProcessoItr::CFaseProcessoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFaseProcesso    = NULL;
}

CFaseProcessoItr::~CFaseProcessoItr()
{
	ZeraFaseProcesso();
}

int CFaseProcessoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CFaseProcessoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CFaseProcessoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CFaseProcessoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CFaseProcessoItr::Quantidade( void )
{
	return _iQuantidade;
}

STFaseProcessoRegistro* CFaseProcessoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrFaseProcesso[ _iPosicao ];
	else
		return 0;
}

STFaseProcessoRegistro* CFaseProcessoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrFaseProcesso[ nPosicao ];

	}
	else
		return 0;
}

void CFaseProcessoItr::Add(
			char* cidFaseProcesso,
			char* cnmFaseProcesso )
{
	_iQuantidade++;
	stcrFaseProcesso = (struct STFaseProcessoRegistro*) realloc( stcrFaseProcesso, sizeof(STFaseProcessoRegistro)*_iQuantidade );
	strcpy( stcrFaseProcesso[_iQuantidade-1].cidFaseProcesso, cidFaseProcesso);
	strcpy( stcrFaseProcesso[_iQuantidade-1].cnmFaseProcesso, cnmFaseProcesso);
}


void CFaseProcessoItr::ZeraFaseProcesso( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrFaseProcesso );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFaseProcesso    = NULL;
}
