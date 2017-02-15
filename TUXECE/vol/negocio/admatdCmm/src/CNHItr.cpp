#include <stdio.h>
#include "../include/CNHItr.h"

CNivelHierarquiaItr::CNivelHierarquiaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivelHierarquia    = NULL;
}

CNivelHierarquiaItr::~CNivelHierarquiaItr()
{
	ZeraNivelHierarquia();
}

int CNivelHierarquiaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNivelHierarquiaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNivelHierarquiaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNivelHierarquiaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNivelHierarquiaItr::Quantidade( void )
{
	return _iQuantidade;
}

STNivelHierarquiaRegistro* CNivelHierarquiaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNivelHierarquia[ _iPosicao ];
	else
		return 0;
}

STNivelHierarquiaRegistro* CNivelHierarquiaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNivelHierarquia[ nPosicao ];

	}
	else
		return 0;
}

void CNivelHierarquiaItr::Add(
			char* cidNivel,
			char* cidNivelPai )
{
	_iQuantidade++;
	stcrNivelHierarquia = (struct STNivelHierarquiaRegistro*) realloc( stcrNivelHierarquia, sizeof(STNivelHierarquiaRegistro)*_iQuantidade );
	strcpy( stcrNivelHierarquia[_iQuantidade-1].cidNivel, cidNivel);
	strcpy( stcrNivelHierarquia[_iQuantidade-1].cidNivelPai, cidNivelPai);
}


void CNivelHierarquiaItr::ZeraNivelHierarquia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNivelHierarquia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivelHierarquia    = NULL;
}


void CNivelHierarquiaItr::SetErro( char* cErro )
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
