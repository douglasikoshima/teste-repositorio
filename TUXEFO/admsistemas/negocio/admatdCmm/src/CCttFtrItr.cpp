#include <stdio.h>
#include "../include/CCttFtrItr.h"

CContatoFiltroItr::CContatoFiltroItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFiltro    = NULL;
}

CContatoFiltroItr::~CContatoFiltroItr()
{
	ZeraContatoFiltro();
}

int CContatoFiltroItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoFiltroItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoFiltroItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoFiltroItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoFiltroItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoFiltroRegistro* CContatoFiltroItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoFiltro[ _iPosicao ];
	else
		return 0;
}

STContatoFiltroRegistro* CContatoFiltroItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoFiltro[ nPosicao ];

	}
	else
		return 0;
}

void CContatoFiltroItr::Add( char* cidContatoFiltro
							,char* cidContato
							,char* cidFiltro
							,char* csgFiltro
							,char* cdsFiltro
							,char* cvlPeso )
{
	_iQuantidade++;
	stcrContatoFiltro = (struct STContatoFiltroRegistro*) realloc( stcrContatoFiltro, sizeof(STContatoFiltroRegistro)*_iQuantidade );
	strcpy( stcrContatoFiltro[_iQuantidade-1].cidContatoFiltro, cidContatoFiltro);
	strcpy( stcrContatoFiltro[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoFiltro[_iQuantidade-1].cidFiltro, cidFiltro);
	strcpy( stcrContatoFiltro[_iQuantidade-1].csgFiltro, csgFiltro);
	strcpy( stcrContatoFiltro[_iQuantidade-1].cdsFiltro, cdsFiltro);
	strcpy( stcrContatoFiltro[_iQuantidade-1].cvlPeso, cvlPeso);
}


void CContatoFiltroItr::ZeraContatoFiltro( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFiltro );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFiltro    = NULL;
}
