#include <stdio.h>
#include "../include/CCttFchItr.h"

CTipoFechamentoContatoItr::CTipoFechamentoContatoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoFechamentoContato    = NULL;
}

CTipoFechamentoContatoItr::~CTipoFechamentoContatoItr()
{
	ZeraTipoFechamentoContato();
}

int CTipoFechamentoContatoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoFechamentoContatoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoFechamentoContatoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoFechamentoContatoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoFechamentoContatoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoFechamentoContatoRegistro* CTipoFechamentoContatoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoFechamentoContato[ _iPosicao ];
	else
		return 0;
}

STTipoFechamentoContatoRegistro* CTipoFechamentoContatoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoFechamentoContato[ nPosicao ];

	}
	else
		return 0;
}

void CTipoFechamentoContatoItr::Add(
			char* cidTipoFechamentoContato,
			char* cnmTipoFechamentoContato )
{
	_iQuantidade++;
	stcrTipoFechamentoContato = (struct STTipoFechamentoContatoRegistro*) realloc( stcrTipoFechamentoContato, sizeof(STTipoFechamentoContatoRegistro)*_iQuantidade );
	strcpy( stcrTipoFechamentoContato[_iQuantidade-1].cidTipoFechamentoContato, cidTipoFechamentoContato);
	strcpy( stcrTipoFechamentoContato[_iQuantidade-1].cnmTipoFechamentoContato, cnmTipoFechamentoContato);
}


void CTipoFechamentoContatoItr::ZeraTipoFechamentoContato( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoFechamentoContato );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoFechamentoContato    = NULL;
}
