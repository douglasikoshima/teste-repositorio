#include <stdio.h>
#include "../include/CSatItr.h"

CPesquisaSatisfacaoItr::CPesquisaSatisfacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacao    = NULL;
}

CPesquisaSatisfacaoItr::~CPesquisaSatisfacaoItr()
{
	ZeraPesquisaSatisfacao();
}

int CPesquisaSatisfacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPesquisaSatisfacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPesquisaSatisfacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPesquisaSatisfacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPesquisaSatisfacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STPesquisaSatisfacaoRegistro* CPesquisaSatisfacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPesquisaSatisfacao[ _iPosicao ];
	else
		return 0;
}

STPesquisaSatisfacaoRegistro* CPesquisaSatisfacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPesquisaSatisfacao[ nPosicao ];

	}
	else
		return 0;
}

void CPesquisaSatisfacaoItr::Add(
			char* cidPesquisaSatisfacao,
			char* cnmPesquisaSatisfacao )
{
	_iQuantidade++;
	stcrPesquisaSatisfacao = (struct STPesquisaSatisfacaoRegistro*) realloc( stcrPesquisaSatisfacao, sizeof(STPesquisaSatisfacaoRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacao[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
	strcpy( stcrPesquisaSatisfacao[_iQuantidade-1].cnmPesquisaSatisfacao, cnmPesquisaSatisfacao);
}


void CPesquisaSatisfacaoItr::ZeraPesquisaSatisfacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPesquisaSatisfacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacao    = NULL;
}
