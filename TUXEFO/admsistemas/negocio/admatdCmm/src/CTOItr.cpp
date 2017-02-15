#include <stdio.h>
#include "../include/CTOItr.h"

CTipoOrganizacaoItr::CTipoOrganizacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoOrganizacao    = NULL;
}

CTipoOrganizacaoItr::~CTipoOrganizacaoItr()
{
	ZeraTipoOrganizacao();
}

int CTipoOrganizacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoOrganizacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoOrganizacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoOrganizacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoOrganizacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoOrganizacaoRegistro* CTipoOrganizacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoOrganizacao[ _iPosicao ];
	else
		return 0;
}

STTipoOrganizacaoRegistro* CTipoOrganizacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoOrganizacao[ nPosicao ];

	}
	else
		return 0;
}

void CTipoOrganizacaoItr::Add(
			char* cidTipoOrganizacao,
			char* cdsTipoOrganizacao )
{
	_iQuantidade++;
	stcrTipoOrganizacao = (struct STTipoOrganizacaoRegistro*) realloc( stcrTipoOrganizacao, sizeof(STTipoOrganizacaoRegistro)*_iQuantidade );
	strcpy( stcrTipoOrganizacao[_iQuantidade-1].cidTipoOrganizacao, cidTipoOrganizacao);
	strcpy( stcrTipoOrganizacao[_iQuantidade-1].cdsTipoOrganizacao, cdsTipoOrganizacao);
}


void CTipoOrganizacaoItr::ZeraTipoOrganizacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoOrganizacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoOrganizacao    = NULL;
}
