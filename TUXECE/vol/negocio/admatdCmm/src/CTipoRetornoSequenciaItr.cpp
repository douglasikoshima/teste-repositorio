#include <stdio.h>
#include "../include/CTipoRetornoSequenciaItr.h"

CTipoRetornoSequenciaItr::CTipoRetornoSequenciaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoRetornoSequencia    = NULL;
}

CTipoRetornoSequenciaItr::~CTipoRetornoSequenciaItr()
{
	ZeraTipoRetornoSequencia();
}

int CTipoRetornoSequenciaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoRetornoSequenciaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoRetornoSequenciaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoRetornoSequenciaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoRetornoSequenciaItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoRetornoSequenciaRegistro* CTipoRetornoSequenciaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoRetornoSequencia[ _iPosicao ];
	else
		return 0;
}

STTipoRetornoSequenciaRegistro* CTipoRetornoSequenciaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoRetornoSequencia[ nPosicao ];

	}
	else
		return 0;
}

void CTipoRetornoSequenciaItr::Add(
								char* cidTipoRetornoSequencia,
								char* cidContatoTipoRetorno,
								char* cidSequencia
							  )
{
	_iQuantidade++;
	stcrTipoRetornoSequencia = (struct STTipoRetornoSequenciaRegistro*) realloc( stcrTipoRetornoSequencia, sizeof(STTipoRetornoSequenciaRegistro)*_iQuantidade );
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidTipoRetornoSequencia, cidTipoRetornoSequencia);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidContatoTipoRetorno, cidContatoTipoRetorno);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidSequencia, cidSequencia);
}
void CTipoRetornoSequenciaItr::Add(
								char* cidTipoRetornoSequencia,
								char* cidContatoTipoRetorno,
								char* cidSequencia,
								char* cidContato,
								char* cidTipoSequencia,
								char* cidGrupo,
								char* cidTipoRetornoContato
							  )
{
	_iQuantidade++;
	stcrTipoRetornoSequencia = (struct STTipoRetornoSequenciaRegistro*) realloc( stcrTipoRetornoSequencia, sizeof(STTipoRetornoSequenciaRegistro)*_iQuantidade );
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidTipoRetornoSequencia, cidTipoRetornoSequencia);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidContatoTipoRetorno, cidContatoTipoRetorno);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidSequencia, cidSequencia);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidTipoSequencia, cidTipoSequencia);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidGrupo, cidGrupo);
	strcpy( stcrTipoRetornoSequencia[_iQuantidade-1].cidTipoRetornoContato, cidTipoRetornoContato);
}


void CTipoRetornoSequenciaItr::ZeraTipoRetornoSequencia( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoRetornoSequencia );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoRetornoSequencia    = NULL;
}
