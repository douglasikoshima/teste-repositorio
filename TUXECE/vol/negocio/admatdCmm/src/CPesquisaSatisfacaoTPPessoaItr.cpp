#include <stdio.h>
#include "../include/CPesquisaSatisfacaoTPPessoaItr.h"

CPesquisaSatisfacaoTPPessoaItr::CPesquisaSatisfacaoTPPessoaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacaoTPPessoa    = NULL;
}

CPesquisaSatisfacaoTPPessoaItr::~CPesquisaSatisfacaoTPPessoaItr()
{
	ZeraPesquisaSatisfacaoTPPessoa();
}

int CPesquisaSatisfacaoTPPessoaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPesquisaSatisfacaoTPPessoaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPesquisaSatisfacaoTPPessoaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPesquisaSatisfacaoTPPessoaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPesquisaSatisfacaoTPPessoaItr::Quantidade( void )
{
	return _iQuantidade;
}

STPesquisaSatisfacaoTPPessoaRegistro* CPesquisaSatisfacaoTPPessoaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPesquisaSatisfacaoTPPessoa[ _iPosicao ];
	else
		return 0;
}

STPesquisaSatisfacaoTPPessoaRegistro* CPesquisaSatisfacaoTPPessoaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPesquisaSatisfacaoTPPessoa[ nPosicao ];

	}
	else
		return 0;
}

void CPesquisaSatisfacaoTPPessoaItr::Add(
								char* cidPesquisaSatisfacao,
								char* cidTipoPessoa
							  )
{
	_iQuantidade++;
	stcrPesquisaSatisfacaoTPPessoa = (struct STPesquisaSatisfacaoTPPessoaRegistro*) realloc( stcrPesquisaSatisfacaoTPPessoa, sizeof(STPesquisaSatisfacaoTPPessoaRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacaoTPPessoa[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
	strcpy( stcrPesquisaSatisfacaoTPPessoa[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa);
}


void CPesquisaSatisfacaoTPPessoaItr::ZeraPesquisaSatisfacaoTPPessoa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPesquisaSatisfacaoTPPessoa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacaoTPPessoa    = NULL;
}
