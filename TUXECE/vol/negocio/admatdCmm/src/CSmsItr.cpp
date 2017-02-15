#include <stdio.h>
#include "../include/CSmsItr.h"

CCSmsItr::CCSmsItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAtribuicao    = NULL;
}

CCSmsItr::~CCSmsItr()
{
	ZeraAtribuicao();
}

int CCSmsItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCSmsItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCSmsItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCSmsItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCSmsItr::Quantidade( void )
{
	return _iQuantidade;
}

STClassificacaoSMSRegistro* CCSmsItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrAtribuicao[ _iPosicao ];
	else
		return 0;
}

STClassificacaoSMSRegistro* CCSmsItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrAtribuicao[ nPosicao ];

	}
	else
		return 0;
}

void CCSmsItr::Add(
			char* cidClassificacaoSMS,
			char* cdsClassificacaoSMS )
{
	_iQuantidade++;
	stcrAtribuicao = (struct STClassificacaoSMSRegistro*) realloc( stcrAtribuicao, sizeof(STClassificacaoSMSRegistro)*_iQuantidade );
	strcpy( stcrAtribuicao[_iQuantidade-1].cidClassificacaoSMS, cidClassificacaoSMS);
	strcpy( stcrAtribuicao[_iQuantidade-1].cdsClassificacaoSMS, cdsClassificacaoSMS);
}


void CCSmsItr::ZeraAtribuicao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrAtribuicao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAtribuicao    = NULL;
}
