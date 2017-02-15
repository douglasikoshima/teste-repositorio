#include <stdio.h>
#include "../include/CNivelSequenciaItr.h"

CNivelSequenciaItr::CNivelSequenciaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivelSequencia    = NULL;
}

CNivelSequenciaItr::~CNivelSequenciaItr()
{
	ZeraNivelSequencia();
}

int CNivelSequenciaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CNivelSequenciaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CNivelSequenciaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CNivelSequenciaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CNivelSequenciaItr::Quantidade( void )
{
	return _iQuantidade;
}

STNivelSequenciaRegistro* CNivelSequenciaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrNivelSequencia[ _iPosicao ];
	else
		return 0;
}

STNivelSequenciaRegistro* CNivelSequenciaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrNivelSequencia[ nPosicao ];

	}
	else
		return 0;
}

void CNivelSequenciaItr::Add(
								char* cidNivelSequencia,
								char* cidSequenciaMandatoria,
								char* cidSequencia,
								char* cnrNivel,
								char* csqOrdem
							  )
{
	_iQuantidade++;
	stcrNivelSequencia = (struct STNivelSequenciaRegistro*) realloc( stcrNivelSequencia, sizeof(STNivelSequenciaRegistro)*_iQuantidade );
	strcpy( stcrNivelSequencia[_iQuantidade-1].cidNivelSequencia, cidNivelSequencia);
	strcpy( stcrNivelSequencia[_iQuantidade-1].cidSequenciaMandatoria, cidSequenciaMandatoria);
	strcpy( stcrNivelSequencia[_iQuantidade-1].cidSequencia, cidSequencia);
	strcpy( stcrNivelSequencia[_iQuantidade-1].cnrNivel, cnrNivel);
	strcpy( stcrNivelSequencia[_iQuantidade-1].csqOrdem, csqOrdem);
}


void CNivelSequenciaItr::ZeraNivelSequencia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrNivelSequencia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrNivelSequencia    = NULL;
}
