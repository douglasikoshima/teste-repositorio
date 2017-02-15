#include <stdio.h>
#include "../include/CFrdTipItr.h"

CTipoFeriadoItr::CTipoFeriadoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoFeriado    = NULL;
}

CTipoFeriadoItr::~CTipoFeriadoItr()
{
	ZeraTipoFeriado();
}

int CTipoFeriadoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoFeriadoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoFeriadoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoFeriadoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoFeriadoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoFeriadoRegistro* CTipoFeriadoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoFeriado[ _iPosicao ];
	else
		return 0;
}

STTipoFeriadoRegistro* CTipoFeriadoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoFeriado[ nPosicao ];

	}
	else
		return 0;
}

void CTipoFeriadoItr::Add(
			char* cidTipoFeriado,
			char* cdsTipoFeriado )
{
	_iQuantidade++;
	stcrTipoFeriado = (struct STTipoFeriadoRegistro*) realloc( stcrTipoFeriado, sizeof(STTipoFeriadoRegistro)*_iQuantidade );
	strcpy( stcrTipoFeriado[_iQuantidade-1].cidTipoFeriado, cidTipoFeriado);
	strcpy( stcrTipoFeriado[_iQuantidade-1].cdsTipoFeriado, cdsTipoFeriado);
}


void CTipoFeriadoItr::ZeraTipoFeriado( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoFeriado );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoFeriado    = NULL;
}
