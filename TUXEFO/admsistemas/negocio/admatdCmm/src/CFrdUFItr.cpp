#include <stdio.h>
#include "../include/CFrdUFItr.h"

CUFFeriadoItr::CUFFeriadoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUFFeriado    = NULL;
}

CUFFeriadoItr::~CUFFeriadoItr()
{
	ZeraUFFeriado();
}

int CUFFeriadoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CUFFeriadoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CUFFeriadoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CUFFeriadoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CUFFeriadoItr::Quantidade( void )
{
	return _iQuantidade;
}

STUFFeriadoRegistro* CUFFeriadoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrUFFeriado[ _iPosicao ];
	else
		return 0;
}

STUFFeriadoRegistro* CUFFeriadoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrUFFeriado[ nPosicao ];

	}
	else
		return 0;
}

void CUFFeriadoItr::Add(
			char* cidUFFeriado,
			char* cidUF,
			char* cidFeriado )
{
	_iQuantidade++;
	stcrUFFeriado = (struct STUFFeriadoRegistro*) realloc( stcrUFFeriado, sizeof(STUFFeriadoRegistro)*_iQuantidade );
	strcpy( stcrUFFeriado[_iQuantidade-1].cidUFFeriado, cidUFFeriado);
	strcpy( stcrUFFeriado[_iQuantidade-1].cidUF, cidUF);
	strcpy( stcrUFFeriado[_iQuantidade-1].cidFeriado, cidFeriado);
}


void CUFFeriadoItr::ZeraUFFeriado( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrUFFeriado );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUFFeriado    = NULL;
}
