#include <stdio.h>
#include "../include/CContatoSegmentacaoItr.h"

CContatoSegmentacaoItr::CContatoSegmentacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoSegmentacao    = NULL;
}

CContatoSegmentacaoItr::~CContatoSegmentacaoItr()
{
	ZeraContatoSegmentacao();
}

int CContatoSegmentacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoSegmentacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoSegmentacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoSegmentacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoSegmentacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoSegmentacaoRegistro* CContatoSegmentacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoSegmentacao[ _iPosicao ];
	else
		return 0;
}

STContatoSegmentacaoRegistro* CContatoSegmentacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoSegmentacao[ nPosicao ];

	}
	else
		return 0;
}

void CContatoSegmentacaoItr::Add(
								char* cidContatoSegmentacao,
								char* cidContato,
								char* cidSegmentacao
							  )
{
	_iQuantidade++;
	stcrContatoSegmentacao = (struct STContatoSegmentacaoRegistro*) realloc( stcrContatoSegmentacao, sizeof(STContatoSegmentacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoSegmentacao[_iQuantidade-1].cidContatoSegmentacao, cidContatoSegmentacao);
	strcpy( stcrContatoSegmentacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoSegmentacao[_iQuantidade-1].cidSegmentacao, cidSegmentacao);
}


void CContatoSegmentacaoItr::ZeraContatoSegmentacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoSegmentacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoSegmentacao    = NULL;
}
