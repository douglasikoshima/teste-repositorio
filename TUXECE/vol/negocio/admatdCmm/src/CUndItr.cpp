#include <stdio.h>
#include "../include/CUndItr.h"

CDepartamentoItr::CDepartamentoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrDepartamento    = NULL;
}

CDepartamentoItr::~CDepartamentoItr()
{
	ZeraDepartamento();
}

int CDepartamentoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CDepartamentoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CDepartamentoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CDepartamentoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CDepartamentoItr::Quantidade( void )
{
	return _iQuantidade;
}

STDepartamentoRegistro* CDepartamentoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrDepartamento[ _iPosicao ];
	else
		return 0;
}

STDepartamentoRegistro* CDepartamentoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrDepartamento[ nPosicao ];

	}
	else
		return 0;
}

void CDepartamentoItr::Add(
			char* cidDepartamento,
			char* cnmDepartamento )
{
	_iQuantidade++;
	stcrDepartamento = (struct STDepartamentoRegistro*) realloc( stcrDepartamento, sizeof(STDepartamentoRegistro)*_iQuantidade );
	strcpy( stcrDepartamento[_iQuantidade-1].cidDepartamento, cidDepartamento);
	strcpy( stcrDepartamento[_iQuantidade-1].cnmDepartamento, cnmDepartamento);
}


void CDepartamentoItr::ZeraDepartamento( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrDepartamento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrDepartamento    = NULL;
}
