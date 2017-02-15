#include <stdio.h>
#include "../include/CFlhAntItr.h"

CIndicadorContatoFolhaItr::CIndicadorContatoFolhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrIndicadorContatoFolha    = NULL;
}

CIndicadorContatoFolhaItr::~CIndicadorContatoFolhaItr()
{
	ZeraIndicadorContatoFolha();
}

int CIndicadorContatoFolhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CIndicadorContatoFolhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CIndicadorContatoFolhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CIndicadorContatoFolhaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CIndicadorContatoFolhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STIndicadorContatoFolhaRegistro* CIndicadorContatoFolhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrIndicadorContatoFolha[ _iPosicao ];
	else
		return 0;
}

STIndicadorContatoFolhaRegistro* CIndicadorContatoFolhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrIndicadorContatoFolha[ nPosicao ];

	}
	else
		return 0;
}

void CIndicadorContatoFolhaItr::Add(
 									  char* cidIndicadorContatoFolha
									 ,char* cidContato
									 ,char* cidIndicadorAnatel
									 ,char* csgIndicador
									 ,char* cdsIndicador
			                       )
{
	_iQuantidade++;
	stcrIndicadorContatoFolha = (struct STIndicadorContatoFolhaRegistro*) realloc( stcrIndicadorContatoFolha, sizeof(STIndicadorContatoFolhaRegistro)*_iQuantidade );
	strcpy( stcrIndicadorContatoFolha[_iQuantidade-1].cidIndicadorContatoFolha, cidIndicadorContatoFolha);
	strcpy( stcrIndicadorContatoFolha[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrIndicadorContatoFolha[_iQuantidade-1].cidIndicadorAnatel, cidIndicadorAnatel);
	strcpy( stcrIndicadorContatoFolha[_iQuantidade-1].csgIndicador, csgIndicador);
	strcpy( stcrIndicadorContatoFolha[_iQuantidade-1].cdsIndicador, cdsIndicador);
}


void CIndicadorContatoFolhaItr::ZeraIndicadorContatoFolha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrIndicadorContatoFolha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrIndicadorContatoFolha    = NULL;
}
