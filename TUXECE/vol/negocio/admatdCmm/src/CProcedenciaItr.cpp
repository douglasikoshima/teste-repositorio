#include <stdio.h>
#include "../include/CProcedenciaItr.h"

CProcedItr::CProcedItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrProcedencia    = NULL;
}

CProcedItr::~CProcedItr()
{
	ZeraProcedencia();
}

int CProcedItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CProcedItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CProcedItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CProcedItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CProcedItr::Quantidade( void )
{
	return _iQuantidade;
}

STProcedenciaRegistro* CProcedItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrProcedencia[ _iPosicao ];
	else
		return 0;
}

STProcedenciaRegistro* CProcedItr::Registro(int nPosicao)
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

void CProcedItr::Add(
			char* cidProcedencia,
			char* cnmProcedencia )
{
	_iQuantidade++;
	stcrProcedencia = (struct STProcedenciaRegistro*) realloc( stcrProcedencia, sizeof(STProcedenciaRegistro)*_iQuantidade );
	strcpy( stcrProcedencia[_iQuantidade-1].cidProcedencia, cidProcedencia);
	strcpy( stcrProcedencia[_iQuantidade-1].cnmProcedencia, cnmProcedencia);
}


void CProcedItr::ZeraProcedencia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrProcedencia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrProcedencia    = NULL;
}
