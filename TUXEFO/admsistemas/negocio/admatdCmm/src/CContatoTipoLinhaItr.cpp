#include <stdio.h>
#include "../include/CContatoTipoLinhaItr.h"

CContatoTipoLinhaItr::CContatoTipoLinhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoLinha    = NULL;
}

CContatoTipoLinhaItr::~CContatoTipoLinhaItr()
{
	ZeraContatoTipoLinha();
}

int CContatoTipoLinhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoTipoLinhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoTipoLinhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoTipoLinhaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoTipoLinhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoTipoLinhaRegistro* CContatoTipoLinhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoTipoLinha[ _iPosicao ];
	else
		return 0;
}

STContatoTipoLinhaRegistro* CContatoTipoLinhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoTipoLinha[ nPosicao ];

	}
	else
		return 0;
}

void CContatoTipoLinhaItr::Add(
								char* cidContatoTipoLinha,
								char* cidContato,
								char* cidTipoLinha
							  )
{
	_iQuantidade++;
	stcrContatoTipoLinha = (struct STContatoTipoLinhaRegistro*) realloc( stcrContatoTipoLinha, sizeof(STContatoTipoLinhaRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoLinha[_iQuantidade-1].cidContatoTipoLinha, cidContatoTipoLinha);
	strcpy( stcrContatoTipoLinha[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoTipoLinha[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
}


void CContatoTipoLinhaItr::ZeraContatoTipoLinha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoTipoLinha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoLinha    = NULL;
}
