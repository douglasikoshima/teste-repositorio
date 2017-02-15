#include <stdio.h>
#include "../include/COHItr.h"

COrganizacaoHierarquiaItr::COrganizacaoHierarquiaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrOrganizacaoHierarquia    = NULL;
}

COrganizacaoHierarquiaItr::~COrganizacaoHierarquiaItr()
{
	ZeraOrganizacaoHierarquia();
}

int COrganizacaoHierarquiaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int COrganizacaoHierarquiaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int COrganizacaoHierarquiaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int COrganizacaoHierarquiaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int COrganizacaoHierarquiaItr::Quantidade( void )
{
	return _iQuantidade;
}

STOrganizacaoHierarquiaRegistro* COrganizacaoHierarquiaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrOrganizacaoHierarquia[ _iPosicao ];
	else
		return 0;
}

STOrganizacaoHierarquiaRegistro* COrganizacaoHierarquiaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrOrganizacaoHierarquia[ nPosicao ];

	}
	else
		return 0;
}

void COrganizacaoHierarquiaItr::Add(
			char* cidOrganizacao,
			char* cidOrganizacaoPai )
{
	_iQuantidade++;
	stcrOrganizacaoHierarquia = (struct STOrganizacaoHierarquiaRegistro*) realloc( stcrOrganizacaoHierarquia, sizeof(STOrganizacaoHierarquiaRegistro)*_iQuantidade );
	strcpy( stcrOrganizacaoHierarquia[_iQuantidade-1].cidOrganizacao, cidOrganizacao);
	strcpy( stcrOrganizacaoHierarquia[_iQuantidade-1].cidOrganizacaoPai, cidOrganizacaoPai);
}


void COrganizacaoHierarquiaItr::ZeraOrganizacaoHierarquia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrOrganizacaoHierarquia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrOrganizacaoHierarquia    = NULL;
}

void COrganizacaoHierarquiaItr::SetErro( char* cErro )
{
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
	if( strlen( cErro ) >= 255 )
	{
		strncpy( cErroGeral, &cErro[0], 255 );
		cErro[255] = 0;
	}
	else
		strcpy( cErroGeral, cErro );
}
