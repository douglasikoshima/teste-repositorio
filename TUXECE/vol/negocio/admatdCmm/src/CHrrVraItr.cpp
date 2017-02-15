#include <stdio.h>
#include "../include/CHrrVraItr.h"

CHorarioVeraoItr::CHorarioVeraoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrHorarioVerao    = NULL;
}

CHorarioVeraoItr::~CHorarioVeraoItr()
{
	ZeraHorarioVerao();
}

int CHorarioVeraoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CHorarioVeraoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CHorarioVeraoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CHorarioVeraoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CHorarioVeraoItr::Quantidade( void )
{
	return _iQuantidade;
}

STHorarioVeraoRegistro* CHorarioVeraoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrHorarioVerao[ _iPosicao ];
	else
		return 0;
}

STHorarioVeraoRegistro* CHorarioVeraoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrHorarioVerao[ nPosicao ];

	}
	else
		return 0;
}

void CHorarioVeraoItr::Add(
			char* cidHorarioVerao,
			char* cidUF,
			char* cnrHorarioVerao,
			char* cdtInicio,
			char* cdtFim )
{
	_iQuantidade++;
	stcrHorarioVerao = (struct STHorarioVeraoRegistro*) realloc( stcrHorarioVerao, sizeof(STHorarioVeraoRegistro)*_iQuantidade );
	strcpy( stcrHorarioVerao[_iQuantidade-1].cidHorarioVerao, cidHorarioVerao);
	strcpy( stcrHorarioVerao[_iQuantidade-1].cidUF, cidUF);
	strcpy( stcrHorarioVerao[_iQuantidade-1].cnrHorarioVerao, cnrHorarioVerao);
	strcpy( stcrHorarioVerao[_iQuantidade-1].cdtInicio, cdtInicio);
	strcpy( stcrHorarioVerao[_iQuantidade-1].cdtFim, cdtFim);
}


void CHorarioVeraoItr::ZeraHorarioVerao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrHorarioVerao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrHorarioVerao    = NULL;
}
