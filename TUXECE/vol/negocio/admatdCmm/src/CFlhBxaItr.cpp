#include <stdio.h>
#include "../include/CFlhBxaItr.h"

CContatoFolhaBaixaItr::CContatoFolhaBaixaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaBaixa    = NULL;
}

CContatoFolhaBaixaItr::~CContatoFolhaBaixaItr()
{
	ZeraContatoFolhaBaixa();
}

int CContatoFolhaBaixaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoFolhaBaixaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoFolhaBaixaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoFolhaBaixaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoFolhaBaixaItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoFolhaBaixaRegistro* CContatoFolhaBaixaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoFolhaBaixa[ _iPosicao ];
	else
		return 0;
}

STContatoFolhaBaixaRegistro* CContatoFolhaBaixaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoFolhaBaixa[ nPosicao ];

	}
	else
		return 0;
}

void CContatoFolhaBaixaItr::Add(
			char* cidContatoFolhaBaixa,
			char* cidContato,
			char* cidBaixa )
{
	_iQuantidade++;
	stcrContatoFolhaBaixa = (struct STContatoFolhaBaixaRegistro*) realloc( stcrContatoFolhaBaixa, sizeof(STContatoFolhaBaixaRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaBaixa[_iQuantidade-1].cidContatoFolhaBaixa, cidContatoFolhaBaixa);
	strcpy( stcrContatoFolhaBaixa[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoFolhaBaixa[_iQuantidade-1].cidBaixa, cidBaixa);
}


void CContatoFolhaBaixaItr::ZeraContatoFolhaBaixa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolhaBaixa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaBaixa    = NULL;
}
