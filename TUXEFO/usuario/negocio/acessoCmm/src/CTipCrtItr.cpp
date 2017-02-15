#include <stdio.h>
#include "CTipCrtItr.h"

CTipCrtItr::CTipCrtItr()
{
    //ULOG_START("CTipCrtItr::CTipCrtItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoCarteira    = NULL;
	//ULOG_END("CTipCrtItr::CTipCrtItr()");
}

CTipCrtItr::~CTipCrtItr()
{
	//ULOG_START("CTipCrtItr::~CTipCrtItr()");
	ZeraTipoCarteira();
	//ULOG_END("CTipCrtItr::~CTipCrtItr()");
}

int CTipCrtItr::Primeiro( void )
{
	//ULOG_START("CTipCrtItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CTipCrtItr::Primeiro()");
	return _iPosicao;
}

int CTipCrtItr::Proximo( void )
{
	//ULOG_START("CTipCrtItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CTipCrtItr::Proximo()");
	return _iPosicao;
}

int CTipCrtItr::Anterior( void )
{
	//ULOG_START("CTipCrtItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CTipCrtItr::Anterior()");
	return _iPosicao;
}

int CTipCrtItr::Ultimo( void )
{
	//ULOG_START("CTipCrtItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CTipCrtItr::Ultimo()");
	return _iPosicao;
}

int CTipCrtItr::Quantidade( void )
{
	//ULOG_START("CTipCrtItr::Quantidade()");
	//ULOG_END("CTipCrtItr::Quantidade()");
	return _iQuantidade;
}

STTipoCarteiraRegistro* CTipCrtItr::Registro( void )
{
	//ULOG_START("CTipCrtItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CTipCrtItr::Registro()");
		return &stcrTipoCarteira[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CTipCrtItr::Registro()");
		return 0;
	}
}

STTipoCarteiraRegistro* CTipCrtItr::Registro(int nPosicao)
{
	//ULOG_START("CTipCrtItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CTipCrtItr::Registro()");
		return &stcrTipoCarteira[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CTipCrtItr::Registro()");
		return 0;
	}
}

void CTipCrtItr::Add( char* cid, char* csigla, char* cdescricao, char* cpeso )
{
	//ULOG_START("CTipCrtItr::Add()");
	_iQuantidade++;
	stcrTipoCarteira = (struct STTipoCarteiraRegistro*) realloc( stcrTipoCarteira, sizeof(STTipoCarteiraRegistro)*_iQuantidade );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cidTipoCarteira, cid );
	strcpy( stcrTipoCarteira[_iQuantidade-1].csgTipoCarteira, csigla );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cdsTipoCarteira, cdescricao );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cvlPeso, cpeso );
	//ULOG_END("CTipCrtItr::Add()");
}


void CTipCrtItr::ZeraTipoCarteira( void )
{
	//ULOG_START("CTipCrtItr::ZeraTipoCarteira()");
	if( _iQuantidade > 0 )
	{
		free( stcrTipoCarteira );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoCarteira    = NULL;
	//ULOG_END("CTipCrtItr::ZeraTipoCarteira()");
}