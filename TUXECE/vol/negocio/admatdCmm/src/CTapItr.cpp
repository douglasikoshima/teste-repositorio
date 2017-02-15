#include <stdio.h>
#include "../include/CTapItr.h"

CTipoApresentacaoPerguntaItr::CTipoApresentacaoPerguntaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoApresentacaoPergunta    = NULL;
}

CTipoApresentacaoPerguntaItr::~CTipoApresentacaoPerguntaItr()
{
	ZeraTipoApresentacaoPergunta();
}

int CTipoApresentacaoPerguntaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoApresentacaoPerguntaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoApresentacaoPerguntaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoApresentacaoPerguntaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoApresentacaoPerguntaItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoApresentacaoPerguntaRegistro* CTipoApresentacaoPerguntaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoApresentacaoPergunta[ _iPosicao ];
	else
		return 0;
}

STTipoApresentacaoPerguntaRegistro* CTipoApresentacaoPerguntaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoApresentacaoPergunta[ nPosicao ];

	}
	else
		return 0;
}

void CTipoApresentacaoPerguntaItr::Add(
			char* cidTipoApresentacaoPergunta,
			char* csgTipoApresentacaoPergunta,
			char* cdsTipoApresentacaoPergunta )
{
	_iQuantidade++;
	stcrTipoApresentacaoPergunta = (struct STTipoApresentacaoPerguntaRegistro*) realloc( stcrTipoApresentacaoPergunta, sizeof(STTipoApresentacaoPerguntaRegistro)*_iQuantidade );
	strcpy( stcrTipoApresentacaoPergunta[_iQuantidade-1].cidTipoApresentacaoPergunta, cidTipoApresentacaoPergunta);
	strcpy( stcrTipoApresentacaoPergunta[_iQuantidade-1].csgTipoApresentacaoPergunta, csgTipoApresentacaoPergunta);
	strcpy( stcrTipoApresentacaoPergunta[_iQuantidade-1].cdsTipoApresentacaoPergunta, cdsTipoApresentacaoPergunta);
}


void CTipoApresentacaoPerguntaItr::ZeraTipoApresentacaoPergunta( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoApresentacaoPergunta );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoApresentacaoPergunta    = NULL;
}
