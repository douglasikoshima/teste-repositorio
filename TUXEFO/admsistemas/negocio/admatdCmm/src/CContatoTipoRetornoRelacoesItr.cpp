#include <stdio.h>
#include "../include/CContatoTipoRetornoRelacoesItr.h"

CContatoTipoRetornoRelacoesItr::CContatoTipoRetornoRelacoesItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoRetornoRelacoes    = NULL;
}

CContatoTipoRetornoRelacoesItr::~CContatoTipoRetornoRelacoesItr()
{
	ZeraContatoTipoRetornoRelacoes();
}

int CContatoTipoRetornoRelacoesItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoTipoRetornoRelacoesItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoTipoRetornoRelacoesItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoTipoRetornoRelacoesItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoTipoRetornoRelacoesItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoTipoRetornoRelacoesRegistro* CContatoTipoRetornoRelacoesItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoTipoRetornoRelacoes[ _iPosicao ];
	else
		return 0;
}

STContatoTipoRetornoRelacoesRegistro* CContatoTipoRetornoRelacoesItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoTipoRetornoRelacoes[ nPosicao ];

	}
	else
		return 0;
}

void CContatoTipoRetornoRelacoesItr::Add(
								char* cidContatoTipoRetornoRelacoes,
								char* cidContatoTipoRetorno,
								char* cidContatoRelacao,
								char* cidTipoRetornoContato
							  )
{
	_iQuantidade++;
	stcrContatoTipoRetornoRelacoes = (struct STContatoTipoRetornoRelacoesRegistro*) realloc( stcrContatoTipoRetornoRelacoes, sizeof(STContatoTipoRetornoRelacoesRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidContatoTipoRetornoRelacoes, cidContatoTipoRetornoRelacoes);
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidContatoTipoRetorno, cidContatoTipoRetorno);
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidContatoRelacao, cidContatoRelacao);
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidTipoRetornoContato, cidTipoRetornoContato);
}

void CContatoTipoRetornoRelacoesItr::Add(
										  char* cidContatoRelacao,
										  char* cdsContatoRelacao
									    )
{
	_iQuantidade++;
	stcrContatoTipoRetornoRelacoes = (struct STContatoTipoRetornoRelacoesRegistro*) realloc( stcrContatoTipoRetornoRelacoes, sizeof(STContatoTipoRetornoRelacoesRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidContatoRelacao, cidContatoRelacao);
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cdsContatoRelacao, cdsContatoRelacao);
}

void CContatoTipoRetornoRelacoesItr::Add(
										  char* cidContatoTipoRetornoRelacoes,
			  							  char* cidContatoRelacao,
										  char* cdsContatoRelacao
									    )
{
	_iQuantidade++;
	stcrContatoTipoRetornoRelacoes = (struct STContatoTipoRetornoRelacoesRegistro*) realloc( stcrContatoTipoRetornoRelacoes, sizeof(STContatoTipoRetornoRelacoesRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidContatoTipoRetornoRelacoes, cidContatoTipoRetornoRelacoes);
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cidContatoRelacao, cidContatoRelacao);
	strcpy( stcrContatoTipoRetornoRelacoes[_iQuantidade-1].cdsContatoRelacao, cdsContatoRelacao);
}

void CContatoTipoRetornoRelacoesItr::ZeraContatoTipoRetornoRelacoes( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoTipoRetornoRelacoes );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoRetornoRelacoes    = NULL;
}
