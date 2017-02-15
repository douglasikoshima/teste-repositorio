#include <stdio.h>
#include "../include/CTipPesItr.h"

CTipoPessoaItr::CTipoPessoaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoPessoa    = NULL;
}

CTipoPessoaItr::~CTipoPessoaItr()
{
	ZeraTipoPessoa();
}

int CTipoPessoaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoPessoaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoPessoaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoPessoaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoPessoaItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoPessoaRegistro* CTipoPessoaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoPessoa[ _iPosicao ];
	else
		return 0;
}

STTipoPessoaRegistro* CTipoPessoaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoPessoa[ nPosicao ];

	}
	else
		return 0;
}

void CTipoPessoaItr::Add( char* cidTipoPessoa,
		                  char* csgTipoPessoa,
			              char* cdsTipoPessoa )
{
	_iQuantidade++;
	stcrTipoPessoa = (struct STTipoPessoaRegistro*) realloc( stcrTipoPessoa, sizeof(STTipoPessoaRegistro)*_iQuantidade );
	strcpy( stcrTipoPessoa[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa );
	strcpy( stcrTipoPessoa[_iQuantidade-1].csgTipoPessoa, csgTipoPessoa );
	strcpy( stcrTipoPessoa[_iQuantidade-1].cdsTipoPessoa, cdsTipoPessoa );
}


void CTipoPessoaItr::ZeraTipoPessoa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoPessoa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoPessoa    = NULL;
}