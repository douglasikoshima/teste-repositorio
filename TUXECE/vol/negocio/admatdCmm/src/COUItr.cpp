#include <stdio.h>
#include "../include/COUItr.h"

COrganizacaoDepartamentoItr::COrganizacaoDepartamentoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrOrganizacaoDepartamento    = NULL;
}

COrganizacaoDepartamentoItr::~COrganizacaoDepartamentoItr()
{
	ZeraOrganizacaoDepartamento();
}

int COrganizacaoDepartamentoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int COrganizacaoDepartamentoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int COrganizacaoDepartamentoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int COrganizacaoDepartamentoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int COrganizacaoDepartamentoItr::Quantidade( void )
{
	return _iQuantidade;
}

STOrganizacaoDepartamentoRegistro* COrganizacaoDepartamentoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrOrganizacaoDepartamento[ _iPosicao ];
	else
		return 0;
}

STOrganizacaoDepartamentoRegistro* COrganizacaoDepartamentoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrOrganizacaoDepartamento[ nPosicao ];

	}
	else
		return 0;
}

void COrganizacaoDepartamentoItr::Add(
			char* cidOrganizacaoDepartamento,
			char* cidDepartamento,
			char* cidOrganizacao )
{
	_iQuantidade++;
	stcrOrganizacaoDepartamento = (struct STOrganizacaoDepartamentoRegistro*) realloc( stcrOrganizacaoDepartamento, sizeof(STOrganizacaoDepartamentoRegistro)*_iQuantidade );
	strcpy( stcrOrganizacaoDepartamento[_iQuantidade-1].cidOrganizacaoDepartamento, cidOrganizacaoDepartamento);
	strcpy( stcrOrganizacaoDepartamento[_iQuantidade-1].cidDepartamento, cidDepartamento);
	strcpy( stcrOrganizacaoDepartamento[_iQuantidade-1].cidOrganizacao, cidOrganizacao);
}


void COrganizacaoDepartamentoItr::ZeraOrganizacaoDepartamento( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrOrganizacaoDepartamento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrOrganizacaoDepartamento    = NULL;
}
