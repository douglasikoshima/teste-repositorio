#include <stdio.h>
#include "../include/CFlhDprItr.h"

CContatoFolhaEnvioDPRItr::CContatoFolhaEnvioDPRItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaEnvioDPR    = NULL;
}

CContatoFolhaEnvioDPRItr::~CContatoFolhaEnvioDPRItr()
{
	ZeraContatoFolhaEnvioDPR();
}

int CContatoFolhaEnvioDPRItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoFolhaEnvioDPRItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoFolhaEnvioDPRItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoFolhaEnvioDPRItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoFolhaEnvioDPRItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoFolhaEnvioDPRRegistro* CContatoFolhaEnvioDPRItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoFolhaEnvioDPR[ _iPosicao ];
	else
		return 0;
}

STContatoFolhaEnvioDPRRegistro* CContatoFolhaEnvioDPRItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoFolhaEnvioDPR[ nPosicao ];

	}
	else
		return 0;
}

void CContatoFolhaEnvioDPRItr::Add(
			char* cidContato )
{
	_iQuantidade++;
	stcrContatoFolhaEnvioDPR = (struct STContatoFolhaEnvioDPRRegistro*) realloc( stcrContatoFolhaEnvioDPR, sizeof(STContatoFolhaEnvioDPRRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaEnvioDPR[_iQuantidade-1].cidContato, cidContato);
}


void CContatoFolhaEnvioDPRItr::ZeraContatoFolhaEnvioDPR( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolhaEnvioDPR );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaEnvioDPR    = NULL;
}
