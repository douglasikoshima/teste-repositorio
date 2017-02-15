#include <stdio.h>
#include "../include/CSequenciaItr.h"

CSequenciaItr::CSequenciaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSequencia    = NULL;
}

CSequenciaItr::~CSequenciaItr()
{
	ZeraSequencia();
}

int CSequenciaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSequenciaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CSequenciaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CSequenciaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSequenciaItr::Quantidade( void )
{
	return _iQuantidade;
}

STSequenciaRegistro* CSequenciaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSequencia[ _iPosicao ];
	else
		return 0;
}

STSequenciaRegistro* CSequenciaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSequencia[ nPosicao ];

	}
	else
		return 0;
}

void CSequenciaItr::Add(
							char* cidSequencia,
							char* cidContatoGrupo,
							char* cidTipoSequencia,
							char* csqOrdem
					   )
{
	_iQuantidade++;
	stcrSequencia = (struct STSequenciaRegistro*) realloc( stcrSequencia, sizeof(STSequenciaRegistro)*_iQuantidade );
	strcpy( stcrSequencia[_iQuantidade-1].cidSequencia, cidSequencia);
	strcpy( stcrSequencia[_iQuantidade-1].cidContatoGrupo, cidContatoGrupo);
	strcpy( stcrSequencia[_iQuantidade-1].cidTipoSequencia, cidTipoSequencia);
	strcpy( stcrSequencia[_iQuantidade-1].csqOrdem, csqOrdem);
}
void CSequenciaItr::Add(
							char* cidSequencia,
							char* cidContatoGrupo,
							char* cidTipoSequencia,
							char* csqOrdem,
							char* cidGrupo
					   )
{
	_iQuantidade++;
	stcrSequencia = (struct STSequenciaRegistro*) realloc( stcrSequencia, sizeof(STSequenciaRegistro)*_iQuantidade );
	strcpy( stcrSequencia[_iQuantidade-1].cidSequencia, cidSequencia);
	strcpy( stcrSequencia[_iQuantidade-1].cidContatoGrupo, cidContatoGrupo);
	strcpy( stcrSequencia[_iQuantidade-1].cidTipoSequencia, cidTipoSequencia);
	strcpy( stcrSequencia[_iQuantidade-1].csqOrdem, csqOrdem);
	strcpy( stcrSequencia[_iQuantidade-1].cidGrupo, cidGrupo);
}


void CSequenciaItr::ZeraSequencia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSequencia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSequencia    = NULL;
}
