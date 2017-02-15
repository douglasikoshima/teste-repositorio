#include <stdio.h>
#include <CTipoLinhaItr.h>

CTipoLinhaItr::CTipoLinhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoLinha    = NULL;
}

CTipoLinhaItr::~CTipoLinhaItr()
{
	ZeraTipoLinha();
}

int CTipoLinhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoLinhaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoLinhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoLinhaRegistro* CTipoLinhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoLinha[ _iPosicao ];
	else
		return 0;
}

STTipoLinhaRegistro* CTipoLinhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoLinha[ nPosicao ];

	}
	else
		return 0;
}

void CTipoLinhaItr::Add( 
			char* cidTipoLinha,
			char* csgTipoLinha,
			char* cdsTipoLinha,
			char* cvlPeso )
{
	_iQuantidade++;
	stcrTipoLinha = (struct STTipoLinhaRegistro*) realloc( stcrTipoLinha, sizeof(STTipoLinhaRegistro)*_iQuantidade );
	strcpy( stcrTipoLinha[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrTipoLinha[_iQuantidade-1].csgTipoLinha, csgTipoLinha);
	strcpy( stcrTipoLinha[_iQuantidade-1].cdsTipoLinha, cdsTipoLinha);
	strcpy( stcrTipoLinha[_iQuantidade-1].cvlPeso, cvlPeso);
}


void CTipoLinhaItr::ZeraTipoLinha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoLinha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoLinha    = NULL;
}
