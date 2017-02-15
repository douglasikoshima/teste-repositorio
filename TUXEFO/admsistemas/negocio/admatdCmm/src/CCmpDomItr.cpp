#include <stdio.h>
#include "../include/CCmpDomItr.h"

CCampoDominioItr::CCampoDominioItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampoDominio    = NULL;
}

CCampoDominioItr::~CCampoDominioItr()
{
	ZeraCampoDominio();
}

int CCampoDominioItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCampoDominioItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCampoDominioItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCampoDominioItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCampoDominioItr::Quantidade( void )
{
	return _iQuantidade;
}

STCampoDominioRegistro* CCampoDominioItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCampoDominio[ _iPosicao ];
	else
		return 0;
}

STCampoDominioRegistro* CCampoDominioItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCampoDominio[ nPosicao ];

	}
	else
		return 0;
}

void CCampoDominioItr::Add(
			char* cidCampo,
			char* cdsQuery,
			char* cnmAtributoIdentificador,
			char* cnmAtributoDescritivo )
{
	_iQuantidade++;
	stcrCampoDominio = (struct STCampoDominioRegistro*) realloc( stcrCampoDominio, sizeof(STCampoDominioRegistro)*_iQuantidade );
	strcpy( stcrCampoDominio[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrCampoDominio[_iQuantidade-1].cdsQuery, cdsQuery);
	strcpy( stcrCampoDominio[_iQuantidade-1].cnmAtributoIdentificador, cnmAtributoIdentificador);
	strcpy( stcrCampoDominio[_iQuantidade-1].cnmAtributoDescritivo, cnmAtributoDescritivo);
}

void CCampoDominioItr::Add(
			char* cidCampo,
			char* cdsQuery,
			char* cnmAtributoIdentificador,
			char* cnmAtributoDescritivo,
			char* cidTabelaDominio )
{
	_iQuantidade++;
	stcrCampoDominio = (struct STCampoDominioRegistro*) realloc( stcrCampoDominio, sizeof(STCampoDominioRegistro)*_iQuantidade );
	strcpy( stcrCampoDominio[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrCampoDominio[_iQuantidade-1].cdsQuery, cdsQuery);
	strcpy( stcrCampoDominio[_iQuantidade-1].cnmAtributoIdentificador, cnmAtributoIdentificador);
	strcpy( stcrCampoDominio[_iQuantidade-1].cnmAtributoDescritivo, cnmAtributoDescritivo);
	strcpy( stcrCampoDominio[_iQuantidade-1].cidTabelaDominio, cidTabelaDominio);
}

void CCampoDominioItr::ZeraCampoDominio( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCampoDominio );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampoDominio    = NULL;
}
