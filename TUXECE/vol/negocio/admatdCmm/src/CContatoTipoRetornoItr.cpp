#include <stdio.h>
#include "../include/CContatoTipoRetornoItr.h"

CContatoTipoRetornoItr::CContatoTipoRetornoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoRetorno    = NULL;
}

CContatoTipoRetornoItr::~CContatoTipoRetornoItr()
{
	ZeraContatoTipoRetorno();
}

int CContatoTipoRetornoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoTipoRetornoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoTipoRetornoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoTipoRetornoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoTipoRetornoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoTipoRetornoRegistro* CContatoTipoRetornoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoTipoRetorno[ _iPosicao ];
	else
		return 0;
}

STContatoTipoRetornoRegistro* CContatoTipoRetornoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoTipoRetorno[ nPosicao ];

	}
	else
		return 0;
}

void CContatoTipoRetornoItr::Add(
								char* cidContatoTipoRetorno,
								char* cidContato,
								char* cidTipoRetornoContato
							  )
{
	_iQuantidade++;
	stcrContatoTipoRetorno = (struct STContatoTipoRetornoRegistro*) realloc( stcrContatoTipoRetorno, sizeof(STContatoTipoRetornoRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoRetorno[_iQuantidade-1].cidContatoTipoRetorno, cidContatoTipoRetorno);
	strcpy( stcrContatoTipoRetorno[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoTipoRetorno[_iQuantidade-1].cidTipoRetornoContato, cidTipoRetornoContato);
}


void CContatoTipoRetornoItr::ZeraContatoTipoRetorno( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoTipoRetorno );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoRetorno    = NULL;
}
