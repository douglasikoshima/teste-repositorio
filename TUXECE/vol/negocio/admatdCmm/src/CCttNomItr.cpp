#include <stdio.h>
#include "../include/CCttNomItr.h"

CNomeContatoItr::CNomeContatoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNomeContato    = NULL;
}

CNomeContatoItr::~CNomeContatoItr()
{
	ZeraNomeContato();
}

int CNomeContatoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNomeContatoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNomeContatoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNomeContatoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNomeContatoItr::Quantidade( void )
{
	return _iQuantidade;
}

STNomeContatoRegistro* CNomeContatoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNomeContato[ _iPosicao ];
	else
		return 0;
}

STNomeContatoRegistro* CNomeContatoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNomeContato[ nPosicao ];

	}
	else
		return 0;
}

void CNomeContatoItr::Add(
			char* cidNomeContato,
			char* cnmContato )
{
	_iQuantidade++;
	stcrNomeContato = (struct STNomeContatoRegistro*) realloc( stcrNomeContato, sizeof(STNomeContatoRegistro)*_iQuantidade );
	strcpy( stcrNomeContato[_iQuantidade-1].cidNomeContato, cidNomeContato);
	strcpy( stcrNomeContato[_iQuantidade-1].cnmContato, cnmContato);
}


void CNomeContatoItr::ZeraNomeContato( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNomeContato );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNomeContato    = NULL;
}
