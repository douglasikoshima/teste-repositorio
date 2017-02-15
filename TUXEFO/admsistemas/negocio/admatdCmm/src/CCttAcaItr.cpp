#include <stdio.h>
#include "../include/CCttAcaItr.h"

CContatoAcaoItr::CContatoAcaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoAcao    = NULL;
}

CContatoAcaoItr::~CContatoAcaoItr()
{
	ZeraContatoAcao();
}

int CContatoAcaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoAcaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoAcaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoAcaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoAcaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoAcaoRegistro* CContatoAcaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoAcao[ _iPosicao ];
	else
		return 0;
}

STContatoAcaoRegistro* CContatoAcaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoAcao[ nPosicao ];

	}
	else
		return 0;
}

void CContatoAcaoItr::Add(
			char* cidContatoAcao,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cnmURLContatoAcao,
			char* cidContato )
{
	_iQuantidade++;
	stcrContatoAcao = (struct STContatoAcaoRegistro*) realloc( stcrContatoAcao, sizeof(STContatoAcaoRegistro)*_iQuantidade );
	strcpy( stcrContatoAcao[_iQuantidade-1].cidContatoAcao, cidContatoAcao);
	strcpy( stcrContatoAcao[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrContatoAcao[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrContatoAcao[_iQuantidade-1].cnmURLContatoAcao, cnmURLContatoAcao);
	strcpy( stcrContatoAcao[_iQuantidade-1].cidContato, cidContato);
}


void CContatoAcaoItr::ZeraContatoAcao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoAcao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoAcao    = NULL;
}
