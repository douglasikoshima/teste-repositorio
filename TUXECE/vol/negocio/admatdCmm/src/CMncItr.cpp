#include <stdio.h>
#include "../include/CMncItr.h"

CMunicipioItr::CMunicipioItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMunicipio    = NULL;
}

CMunicipioItr::~CMunicipioItr()
{
	ZeraMunicipio();
}

int CMunicipioItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CMunicipioItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CMunicipioItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CMunicipioItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CMunicipioItr::Quantidade( void )
{
	return _iQuantidade;
}

STMunicipioRegistro* CMunicipioItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrMunicipio[ _iPosicao ];
	else
		return 0;
}

STMunicipioRegistro* CMunicipioItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrMunicipio[ nPosicao ];

	}
	else
		return 0;
}

void CMunicipioItr::Add(
			char* cidMunicipio,
			char* cnmMunicipio,
			char* cidUF )
{
	_iQuantidade++;
	stcrMunicipio = (struct STMunicipioRegistro*) realloc( stcrMunicipio, sizeof(STMunicipioRegistro)*_iQuantidade );
	strcpy( stcrMunicipio[_iQuantidade-1].cidMunicipio, cidMunicipio);
	strcpy( stcrMunicipio[_iQuantidade-1].cnmMunicipio, cnmMunicipio);
	strcpy( stcrMunicipio[_iQuantidade-1].cidUF, cidUF);
}


void CMunicipioItr::ZeraMunicipio( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrMunicipio );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMunicipio    = NULL;
}
