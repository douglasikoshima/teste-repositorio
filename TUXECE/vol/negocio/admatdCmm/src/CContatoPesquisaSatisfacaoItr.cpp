#include <stdio.h>
#include "../include/CContatoPesquisaSatisfacaoItr.h"

CContatoPesquisaSatisfacaoItr::CContatoPesquisaSatisfacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoPesquisaSatisfacao    = NULL;
}

CContatoPesquisaSatisfacaoItr::~CContatoPesquisaSatisfacaoItr()
{
	ZeraContatoPesquisaSatisfacao();
}

int CContatoPesquisaSatisfacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoPesquisaSatisfacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoPesquisaSatisfacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoPesquisaSatisfacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoPesquisaSatisfacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoPesquisaSatisfacaoRegistro* CContatoPesquisaSatisfacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoPesquisaSatisfacao[ _iPosicao ];
	else
		return 0;
}

STContatoPesquisaSatisfacaoRegistro* CContatoPesquisaSatisfacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoPesquisaSatisfacao[ nPosicao ];

	}
	else
		return 0;
}

void CContatoPesquisaSatisfacaoItr::Add(
								char* cidContatoPesquisaSatisfacao,
								char* cidContato,
								char* cidPesquisaSatisfacao
							  )
{
	_iQuantidade++;
	stcrContatoPesquisaSatisfacao = (struct STContatoPesquisaSatisfacaoRegistro*) realloc( stcrContatoPesquisaSatisfacao, sizeof(STContatoPesquisaSatisfacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidContatoPesquisaSatisfacao, cidContatoPesquisaSatisfacao);
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
}
void CContatoPesquisaSatisfacaoItr::Add(
								char* cidContatoPesquisaSatisfacao,
								char* cidContato,
								char* cidPesquisaSatisfacao,
								char* cidTipoPessoa
							  )
{
	_iQuantidade++;
	stcrContatoPesquisaSatisfacao = (struct STContatoPesquisaSatisfacaoRegistro*) realloc( stcrContatoPesquisaSatisfacao, sizeof(STContatoPesquisaSatisfacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidContatoPesquisaSatisfacao, cidContatoPesquisaSatisfacao);
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao);
	strcpy( stcrContatoPesquisaSatisfacao[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa);
}


void CContatoPesquisaSatisfacaoItr::ZeraContatoPesquisaSatisfacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoPesquisaSatisfacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoPesquisaSatisfacao    = NULL;
}
