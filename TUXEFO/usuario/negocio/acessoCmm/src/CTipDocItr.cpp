#include <stdio.h>
#include "CTipDocItr.h"

CTipDocItr::CTipDocItr()
{
	//ULOG_START("CTipDocItr::CTipDocItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoDocumento    = NULL;
	//ULOG_END("CTipDocItr::CTipDocItr()");
}

CTipDocItr::~CTipDocItr()
{
	//ULOG_START("CTipDocItr::~CTipDocItr()");
	ZeraTipoDocumento();
	//ULOG_END("CTipDocItr::~CTipDocItr()");
}

int CTipDocItr::Primeiro( void )
{
	//ULOG_START("CTipDocItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CTipDocItr::Primeiro()");
	return _iPosicao;
}

int CTipDocItr::Proximo( void )
{
	//ULOG_START("CTipDocItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CTipDocItr::Proximo()");
	return _iPosicao;
}

int CTipDocItr::Anterior( void )
{
	//ULOG_START("CTipDocItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;

	//ULOG_END("CTipDocItr::Anterior()");
	return _iPosicao;
}

int CTipDocItr::Ultimo( void )
{
	//ULOG_START("CTipDocItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CTipDocItr::Ultimo()");
	return _iPosicao;
}

int CTipDocItr::Quantidade( void )
{
	//ULOG_START("CTipDocItr::Quantidade()");
	//ULOG_END("CTipDocItr::Quantidade()");
	return _iQuantidade;
}

STTipoDocumentoRegistro* CTipDocItr::Registro( void )
{
	//ULOG_START("CTipDocItr::Registro()");
	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CTipDocItr::Registro()");
		return &stcrTipoDocumento[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CTipDocItr::Registro()");
		return 0;
	}
}

STTipoDocumentoRegistro* CTipDocItr::Registro(int nPosicao)
{
	//ULOG_START("CTipDocItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		//ULOG_END("CTipDocItr::Registro()");
		return &stcrTipoDocumento[ nPosicao ];		
	}
	else
	{
		//ULOG_END("CTipDocItr::Registro()");
		return 0;
	}
}

void CTipDocItr::Add( char* cid, char* csigla, char* cdescricao, char* cidtipopessoa )
{
	//ULOG_START("CTipDocItr::Add()");
	_iQuantidade++;
	stcrTipoDocumento = (struct STTipoDocumentoRegistro*) realloc( stcrTipoDocumento, sizeof(STTipoDocumentoRegistro)*_iQuantidade );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cidTipoDocumento, cid );
	strcpy( stcrTipoDocumento[_iQuantidade-1].csgTipoDocumento, csigla );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cdsTipoDocumento, cdescricao );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cidTipoPessoa, cidtipopessoa );
    //ULOG_END("CTipDocItr::Add()");
}


void CTipDocItr::ZeraTipoDocumento( void )
{
	//ULOG_START("CTipDocItr::ZeraTipoDocumento()");
	if( _iQuantidade > 0 )
	{
		free( stcrTipoDocumento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoDocumento    = NULL;
	//ULOG_END("CTipDocItr::ZeraTipoDocumento()");
}