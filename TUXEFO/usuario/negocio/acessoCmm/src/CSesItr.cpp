#include <stdio.h>
#include "CSesItr.h"

CSesItr::CSesItr()
{
	//ULOG_START("CSesItr::CSesItr()");
	_iQuantidade = 0;
	_iSessao    = 0;
	stcrSessao    = NULL;
	//ULOG_END("CSesItr::CSesItr()");
}

CSesItr::~CSesItr()
{
	//ULOG_START("CSesItr::~CSesItr()");
	ZeraSessao();
	//ULOG_END("CSesItr::~CSesItr()");
}

int CSesItr::Primeiro( void )
{
	//ULOG_START("CSesItr::Primeiro()");
	_iSessao = 0;
	//ULOG_END("CSesItr::Primeiro()");
	return _iSessao;
}

int CSesItr::Proximo( void )
{
	//ULOG_START("CSesItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iSessao < (_iQuantidade-1) )
			_iSessao++;
	}
	//ULOG_END("CSesItr::Proximo()");
	return _iSessao;
}

int CSesItr::Anterior( void )
{
	//ULOG_START("CSesItr::Anterior()");
	if( _iSessao > 0 )
		_iSessao--;
    
    //ULOG_END("CSesItr::Anterior()");
	return _iSessao;
}

int CSesItr::Ultimo( void )
{
	//ULOG_START("CSesItr::Ultimo()");
	
	if( _iQuantidade > 0 )
		_iSessao = _iQuantidade-1;
	else
		_iSessao = 0;

    //ULOG_END("CSesItr::Ultimo()");
	return _iSessao;
}

int CSesItr::Quantidade( void )
{
	//ULOG_START("CSesItr::Quantidade()");
	//ULOG_END("CSesItr::Quantidade()");
	return _iQuantidade;
}

STSessaoRegistro* CSesItr::Registro( void )
{
	//ULOG_START("CSesItr::Registro()");
	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CSesItr::Registro()");
		return &stcrSessao[ _iSessao ];
	}
	else
	{
		//ULOG_END("CSesItr::Registro()");
		return 0;
	}
}

STSessaoRegistro* CSesItr::Registro(int nSessao)
{
	//ULOG_START("CSesItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nSessao >= _iQuantidade )
			nSessao = Ultimo();
        //ULOG_END("CSesItr::Registro()");
		return &stcrSessao[ nSessao ];
		
	}
	else
	{
		//ULOG_END("CSesItr::Registro()");
		return 0;
	}
}

void CSesItr::Add( char* cid, char* cnome )
{
	//ULOG_START("CSesItr::Add()");
	_iQuantidade++;
	stcrSessao = (struct STSessaoRegistro*) realloc( stcrSessao, sizeof(STSessaoRegistro)*_iQuantidade );
	strcpy( stcrSessao[_iQuantidade-1].cidSessao, cid );
	strcpy( stcrSessao[_iQuantidade-1].cdsSessao, cnome );
    //ULOG_END("CSesItr::Add()");
}


void CSesItr::ZeraSessao( void )
{
	//ULOG_START("CSesItr::ZeraSessao()");
	if( _iQuantidade > 0 )
	{
		free( stcrSessao );
	}
	_iQuantidade = 0;
	_iSessao    = 0;
	stcrSessao    = NULL;
	//ULOG_END("CSesItr::ZeraSessao()");
}