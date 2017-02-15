#include <stdio.h>
#include "../include/CSatPerItr.h"

CPesquisaSatisfacaoPerguntaItr::CPesquisaSatisfacaoPerguntaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacaoPergunta    = NULL;
}

CPesquisaSatisfacaoPerguntaItr::~CPesquisaSatisfacaoPerguntaItr()
{
	ZeraPesquisaSatisfacaoPergunta();
}

int CPesquisaSatisfacaoPerguntaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPesquisaSatisfacaoPerguntaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPesquisaSatisfacaoPerguntaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPesquisaSatisfacaoPerguntaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPesquisaSatisfacaoPerguntaItr::Quantidade( void )
{
	return _iQuantidade;
}

STPesquisaSatisfacaoPerguntaRegistro* CPesquisaSatisfacaoPerguntaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPesquisaSatisfacaoPergunta[ _iPosicao ];
	else
		return 0;
}

STPesquisaSatisfacaoPerguntaRegistro* CPesquisaSatisfacaoPerguntaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPesquisaSatisfacaoPergunta[ nPosicao ];

	}
	else
		return 0;
}

void CPesquisaSatisfacaoPerguntaItr::Add(
			char* cidPesquisaSatisfacaoPergunta,
			char* cidPesquisaSatisfacao,
			char* cidPergunta )
{
	_iQuantidade++;
	stcrPesquisaSatisfacaoPergunta = (struct STPesquisaSatisfacaoPerguntaRegistro*) realloc( stcrPesquisaSatisfacaoPergunta, sizeof(STPesquisaSatisfacaoPerguntaRegistro)*_iQuantidade );
	strcpy( stcrPesquisaSatisfacaoPergunta[_iQuantidade-1].cidPesquisaSatisfacaoPergunta, cidPesquisaSatisfacaoPergunta);
	strcpy( stcrPesquisaSatisfacaoPergunta[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
	strcpy( stcrPesquisaSatisfacaoPergunta[_iQuantidade-1].cidPergunta, cidPergunta);
}


void CPesquisaSatisfacaoPerguntaItr::ZeraPesquisaSatisfacaoPergunta( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPesquisaSatisfacaoPergunta );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPesquisaSatisfacaoPergunta    = NULL;
}
