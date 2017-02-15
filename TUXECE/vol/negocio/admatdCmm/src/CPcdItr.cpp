#include <stdio.h>
#include "../include/CPcdItr.h"

CProcedenciaItr::CProcedenciaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrProcedencia    = NULL;
}

CProcedenciaItr::~CProcedenciaItr()
{
	ZeraProcedencia();
}

int CProcedenciaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CProcedenciaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CProcedenciaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CProcedenciaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CProcedenciaItr::Quantidade( void )
{
	return _iQuantidade;
}

STProcedenciaRegistro* CProcedenciaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrProcedencia[ _iPosicao ];
	else
		return 0;
}

STProcedenciaRegistro* CProcedenciaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrProcedencia[ nPosicao ];

	}
	else
		return 0;
}

void CProcedenciaItr::Add(
			char* cidProcedencia,
			char* csgProcedencia,
			char* cdsProcedencia,
			char* cvlPeso )
{
	_iQuantidade++;
	stcrProcedencia = (struct STProcedenciaRegistro*) realloc( stcrProcedencia, sizeof(STProcedenciaRegistro)*_iQuantidade );
	strcpy( stcrProcedencia[_iQuantidade-1].cidProcedencia, cidProcedencia);
	strcpy( stcrProcedencia[_iQuantidade-1].csgProcedencia, csgProcedencia);
	strcpy( stcrProcedencia[_iQuantidade-1].cdsProcedencia, cdsProcedencia);
	strcpy( stcrProcedencia[_iQuantidade-1].cvlPeso, cvlPeso);
}


void CProcedenciaItr::ZeraProcedencia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrProcedencia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrProcedencia    = NULL;
}
