#include <stdio.h>
#include "../include/CCttHieItr.h"

CContatoHierarquiaItr::CContatoHierarquiaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoHierarquia    = NULL;
}

CContatoHierarquiaItr::~CContatoHierarquiaItr()
{
	ZeraContatoHierarquia();
}

int CContatoHierarquiaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoHierarquiaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoHierarquiaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoHierarquiaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoHierarquiaItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoHierarquiaRegistro* CContatoHierarquiaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoHierarquia[ _iPosicao ];
	else
		return 0;
}

STContatoHierarquiaRegistro* CContatoHierarquiaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoHierarquia[ nPosicao ];

	}
	else
		return 0;
}

void CContatoHierarquiaItr::Add(
			char* cidContato,
			char* cidContatoPai )
{
	_iQuantidade++;
	stcrContatoHierarquia = (struct STContatoHierarquiaRegistro*) realloc( stcrContatoHierarquia, sizeof(STContatoHierarquiaRegistro)*_iQuantidade );
	strcpy( stcrContatoHierarquia[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoHierarquia[_iQuantidade-1].cidContatoPai, cidContatoPai);
}


void CContatoHierarquiaItr::ZeraContatoHierarquia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoHierarquia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoHierarquia    = NULL;
}
