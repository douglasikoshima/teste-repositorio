#include <stdio.h>
#include "../include/CNvlItr.h"

CNivelItr::CNivelItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivel    = NULL;
}

CNivelItr::~CNivelItr()
{
	ZeraNivel();
}

int CNivelItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNivelItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNivelItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNivelItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNivelItr::Quantidade( void )
{
	return _iQuantidade;
}

STNivelRegistro* CNivelItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNivel[ _iPosicao ];
	else
		return 0;
}

STNivelRegistro* CNivelItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNivel[ nPosicao ];

	}
	else
		return 0;
}

void CNivelItr::Add(
			char* cidNivel,
			char* cdsNivel)
{
	_iQuantidade++;
	stcrNivel = (struct STNivelRegistro*) realloc( stcrNivel, sizeof(STNivelRegistro)*_iQuantidade );
	strcpy( stcrNivel[_iQuantidade-1].cidNivel, cidNivel);
	strcpy( stcrNivel[_iQuantidade-1].cdsNivel, cdsNivel);
}

void CNivelItr::Add(
			char* cidNivel,
			char* cidNivelPai,
			char* cdsNivel,
			char* cdsPath,
			int   iLevel  )
{
	_iQuantidade++;
	stcrNivel = (struct STNivelRegistro*) realloc( stcrNivel, sizeof(STNivelRegistro)*_iQuantidade );
	strcpy( stcrNivel[_iQuantidade-1].cidNivel, cidNivel);
	strcpy( stcrNivel[_iQuantidade-1].cidNivelPai, cidNivelPai);
	strcpy( stcrNivel[_iQuantidade-1].cdsNivel, cdsNivel);
	strcpy( stcrNivel[_iQuantidade-1].cdsPath, cdsPath);
	stcrNivel[_iQuantidade-1].iLevel = iLevel;
}


void CNivelItr::ZeraNivel( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNivel );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivel    = NULL;
}


void CNivelItr::SetErro( char* cErro )
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
