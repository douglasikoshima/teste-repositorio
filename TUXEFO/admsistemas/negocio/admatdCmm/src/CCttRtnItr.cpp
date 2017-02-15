#include <stdio.h>
#include "../include/CCttRtnItr.h"

CTipoRetornoContatoItr::CTipoRetornoContatoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoRetornoContato    = NULL;
}

CTipoRetornoContatoItr::~CTipoRetornoContatoItr()
{
	ZeraTipoRetornoContato();
}

int CTipoRetornoContatoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoRetornoContatoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoRetornoContatoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoRetornoContatoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoRetornoContatoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoRetornoContatoRegistro* CTipoRetornoContatoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoRetornoContato[ _iPosicao ];
	else
		return 0;
}

STTipoRetornoContatoRegistro* CTipoRetornoContatoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoRetornoContato[ nPosicao ];

	}
	else
		return 0;
}

void CTipoRetornoContatoItr::Add(
			char* cidTipoRetornoContato,
			char* cnmTipoRetornoContato )
{
	_iQuantidade++;
	stcrTipoRetornoContato = (struct STTipoRetornoContatoRegistro*) realloc( stcrTipoRetornoContato, sizeof(STTipoRetornoContatoRegistro)*_iQuantidade );
	strcpy( stcrTipoRetornoContato[_iQuantidade-1].cidTipoRetornoContato, cidTipoRetornoContato);
	strcpy( stcrTipoRetornoContato[_iQuantidade-1].cnmTipoRetornoContato, cnmTipoRetornoContato);
}


void CTipoRetornoContatoItr::ZeraTipoRetornoContato( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoRetornoContato );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoRetornoContato    = NULL;
}
