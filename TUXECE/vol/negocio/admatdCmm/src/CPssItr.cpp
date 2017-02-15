#include <stdio.h>
#include "../include/CPssItr.h"

CPessoaItr::CPessoaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPessoa    = NULL;
}

CPessoaItr::~CPessoaItr()
{
	ZeraPessoa();
}

int CPessoaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPessoaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPessoaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPessoaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPessoaItr::Quantidade( void )
{
	return _iQuantidade;
}

STPessoaRegistro* CPessoaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPessoa[ _iPosicao ];
	else
		return 0;
}

STPessoaRegistro* CPessoaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPessoa[ nPosicao ];

	}
	else
		return 0;
}

void CPessoaItr::Add(
			char* cidPessoaUsuario,
			char* cnmLoginUsuario,
			char* cidStatusUsuario,
			char* cinDisponivelWF )
{
	_iQuantidade++;
	stcrPessoa = (struct STPessoaRegistro*) realloc( stcrPessoa, sizeof(STPessoaRegistro)*_iQuantidade );
	strcpy( stcrPessoa[_iQuantidade-1].cidPessoaUsuario, cidPessoaUsuario);
	strcpy( stcrPessoa[_iQuantidade-1].cnmLoginUsuario, cnmLoginUsuario);
	strcpy( stcrPessoa[_iQuantidade-1].cidStatusUsuario, cidStatusUsuario);
	strcpy( stcrPessoa[_iQuantidade-1].cinDisponivelWF, cinDisponivelWF);
}


void CPessoaItr::ZeraPessoa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPessoa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPessoa    = NULL;
}
