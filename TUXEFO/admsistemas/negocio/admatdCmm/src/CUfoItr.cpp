#include <stdio.h>
#include "../include/CUfoItr.h"

CUFOperadoraItr::CUFOperadoraItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUFOperadora    = NULL;
}

CUFOperadoraItr::~CUFOperadoraItr()
{
	ZeraUFOperadora();
}

int CUFOperadoraItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CUFOperadoraItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CUFOperadoraItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CUFOperadoraItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CUFOperadoraItr::Quantidade( void )
{
	return _iQuantidade;
}

STUFOperadoraRegistro* CUFOperadoraItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrUFOperadora[ _iPosicao ];
	else
		return 0;
}

STUFOperadoraRegistro* CUFOperadoraItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrUFOperadora[ nPosicao ];

	}
	else
		return 0;
}

void CUFOperadoraItr::Add(
							char* cidUFOperadora
						   ,char* cidPessoaDeParaOperadora
						   ,char* cidUF
					     )
{
	_iQuantidade++;
	stcrUFOperadora = (struct STUFOperadoraRegistro*) realloc( stcrUFOperadora, sizeof(STUFOperadoraRegistro)*_iQuantidade );
	strcpy( stcrUFOperadora[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrUFOperadora[_iQuantidade-1].cidPessoaDeParaOperadora, cidPessoaDeParaOperadora);
	strcpy( stcrUFOperadora[_iQuantidade-1].cidUF, cidUF);
}

void CUFOperadoraItr::Add(
							char* cidUFOperadora
						   ,char* cnmUFOperadora
						   ,char* cidPessoaDeParaOperadora
						   ,char* cidUF
						   ,char* csgUF
						   ,char* cnmUF
					     )
{
	_iQuantidade++;
	stcrUFOperadora = (struct STUFOperadoraRegistro*) realloc( stcrUFOperadora, sizeof(STUFOperadoraRegistro)*_iQuantidade );
	strcpy( stcrUFOperadora[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrUFOperadora[_iQuantidade-1].cnmUFOperadora, cnmUFOperadora);
	strcpy( stcrUFOperadora[_iQuantidade-1].cidPessoaDeParaOperadora, cidPessoaDeParaOperadora);
	strcpy( stcrUFOperadora[_iQuantidade-1].cidUF, cidUF);
	strcpy( stcrUFOperadora[_iQuantidade-1].csgUF, csgUF);
	strcpy( stcrUFOperadora[_iQuantidade-1].cnmUF, cnmUF);
}

void CUFOperadoraItr::ZeraUFOperadora( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrUFOperadora );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUFOperadora    = NULL;
}
