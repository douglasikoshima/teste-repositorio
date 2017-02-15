#include <stdio.h>
#include "CSisOrgItr.h"

CSisOrgItr::CSisOrgItr()
{
	//ULOG_START("CSisOrgItr::CSisOrgItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSistemaOrigem    = NULL;
    //ULOG_END("CSisOrgItr::CSisOrgItr()");
}

CSisOrgItr::~CSisOrgItr()
{
	//ULOG_START("CSisOrgItr::~CSisOrgItr()");
	ZeraSistemaOrigem();
	//ULOG_END("CSisOrgItr::~CSisOrgItr()");
}

int CSisOrgItr::Primeiro( void )
{
	//ULOG_START("CSisOrgItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CSisOrgItr::Primeiro()");
	return _iPosicao;
}

int CSisOrgItr::Proximo( void )
{
	//ULOG_START("CSisOrgItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CSisOrgItr::Proximo()");
	return _iPosicao;
}

int CSisOrgItr::Anterior( void )
{
	//ULOG_START("CSisOrgItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CSisOrgItr::Anterior()");
	return _iPosicao;
}

int CSisOrgItr::Ultimo( void )
{
	//ULOG_START("CSisOrgItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CSisOrgItr::Ultimo()");
	return _iPosicao;
}

int CSisOrgItr::Quantidade( void )
{
	//ULOG_START("CSisOrgItr::Quantidade()");
	//ULOG_END("CSisOrgItr::Quantidade()");
	return _iQuantidade;
}

STSistemaOrigemRegistro* CSisOrgItr::Registro( void )
{
	//ULOG_START("CSisOrgItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CSisOrgItr::Registro()");
		return &stcrSistemaOrigem[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CSisOrgItr::Registro()");
		return 0;
	}
}

STSistemaOrigemRegistro* CSisOrgItr::Registro(int nPosicao)
{
	//ULOG_START("CSisOrgItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
	    //ULOG_END("CSisOrgItr::Registro()");
		return &stcrSistemaOrigem[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CSisOrgItr::Registro()");
		return 0;
	}
}

void CSisOrgItr::Add( char* cid, char* csigla, char* cnome )
{
	//ULOG_START("CSisOrgItr::Add()");
	_iQuantidade++;
	stcrSistemaOrigem = (struct STSistemaOrigemRegistro*) realloc( stcrSistemaOrigem, sizeof(STSistemaOrigemRegistro)*_iQuantidade );
	strcpy( stcrSistemaOrigem[_iQuantidade-1].cidSistemaOrigem, cid );
	strcpy( stcrSistemaOrigem[_iQuantidade-1].csgSistemaOrigem, csigla );
	strcpy( stcrSistemaOrigem[_iQuantidade-1].cnmSistemaOrigem, cnome );
	//ULOG_END("CSisOrgItr::Add()");
}


void CSisOrgItr::ZeraSistemaOrigem( void )
{
	//ULOG_START("CSisOrgItr::ZeraSistemaOrigem()");
	if( _iQuantidade > 0 )
	{
		free( stcrSistemaOrigem );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSistemaOrigem       = NULL;
	//ULOG_END("CSisOrgItr::ZeraSistemaOrigem()");
}