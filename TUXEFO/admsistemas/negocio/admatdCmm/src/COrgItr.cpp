#include <stdio.h>
#include "../include/COrgItr.h"

COrganizacaoItr::COrganizacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrOrganizacao    = NULL;
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
}

COrganizacaoItr::~COrganizacaoItr()
{
	ZeraOrganizacao();
}

int COrganizacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int COrganizacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int COrganizacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int COrganizacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int COrganizacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STOrganizacaoRegistro* COrganizacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrOrganizacao[ _iPosicao ];
	else
		return 0;
}

STOrganizacaoRegistro* COrganizacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrOrganizacao[ nPosicao ];

	}
	else
		return 0;
}

void COrganizacaoItr::Add( char* cidOrganizacao,
			         char* cidOrganizacaoPai,
			         char* cidTipoOrganizacao,
					 char* cdsTipoOrganizacao,
			         char* cdsPath,
					 int   iLevel )
{
	_iQuantidade++;
	stcrOrganizacao = (struct STOrganizacaoRegistro*) realloc( stcrOrganizacao, sizeof(STOrganizacaoRegistro)*_iQuantidade );
	strcpy( stcrOrganizacao[_iQuantidade-1].cidOrganizacao, cidOrganizacao);
	strcpy( stcrOrganizacao[_iQuantidade-1].cidOrganizacaoPai, cidOrganizacaoPai);
	strcpy( stcrOrganizacao[_iQuantidade-1].cidTipoOrganizacao, cidTipoOrganizacao);
	strcpy( stcrOrganizacao[_iQuantidade-1].cdsTipoOrganizacao, cdsTipoOrganizacao);
	strcpy( stcrOrganizacao[_iQuantidade-1].cdsPath, cdsPath);
	stcrOrganizacao[_iQuantidade-1].iLevel = iLevel;
}


void COrganizacaoItr::ZeraOrganizacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrOrganizacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrOrganizacao    = NULL;
}

void COrganizacaoItr::SetErro( char* cErro )
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
