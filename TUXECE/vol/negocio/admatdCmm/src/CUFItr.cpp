#include <stdio.h>
#include "../include/CUFItr.h"

CUFItr::CUFItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUF    = NULL;
}

CUFItr::~CUFItr()
{
	ZeraUF();
}

int CUFItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CUFItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CUFItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CUFItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CUFItr::Quantidade( void )
{
	return _iQuantidade;
}

STUFRegistro* CUFItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrUF[ _iPosicao ];
	else
		return 0;
}

STUFRegistro* CUFItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrUF[ nPosicao ];

	}
	else
		return 0;
}

void CUFItr::Add(
			char* cidUF,
			char* csgUF,
			char* cnmUF,
			char* cnrFusoHorario )
{
	_iQuantidade++;
	stcrUF = (struct STUFRegistro*) realloc( stcrUF, sizeof(STUFRegistro)*_iQuantidade );
	strcpy( stcrUF[_iQuantidade-1].cidUF, cidUF);
	strcpy( stcrUF[_iQuantidade-1].csgUF, csgUF);
	strcpy( stcrUF[_iQuantidade-1].cnmUF, cnmUF);
	strcpy( stcrUF[_iQuantidade-1].cnrFusoHorario, cnrFusoHorario);
}


void CUFItr::ZeraUF( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrUF );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUF    = NULL;
}
