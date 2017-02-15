#include <stdio.h>
#include "../include/CCttCmbItr.h"

CContatoCombosItr::CContatoCombosItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoCombos    = NULL;
}

CContatoCombosItr::~CContatoCombosItr()
{
	ZeraContatoCombos();
}

int CContatoCombosItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoCombosItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoCombosItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoCombosItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoCombosItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoCombosRegistro* CContatoCombosItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoCombos[ _iPosicao ];
	else
		return 0;
}

STContatoCombosRegistro* CContatoCombosItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoCombos[ nPosicao ];

	}
	else
		return 0;
}

void CContatoCombosItr::Add( char* cidCombo
							,char* csgCombo
							,char* cdsCombo )
{
	_iQuantidade++;
	stcrContatoCombos = (struct STContatoCombosRegistro*) realloc( stcrContatoCombos, sizeof(STContatoCombosRegistro)*_iQuantidade );
	strcpy( stcrContatoCombos[_iQuantidade-1].cidCombo, cidCombo);
	strcpy( stcrContatoCombos[_iQuantidade-1].csgCombo, csgCombo);
	strcpy( stcrContatoCombos[_iQuantidade-1].cdsCombo, cdsCombo);
}


void CContatoCombosItr::ZeraContatoCombos( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoCombos );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoCombos    = NULL;
}
