#include <stdio.h>
#include "CStsItr.h"

CStsItr::CStsItr()
{
	//ULOG_START("CStsItr::CStsItr()");	
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrStatusUsuario    = NULL;
	//ULOG_END("CStsItr::CStsItr()");	
}

CStsItr::~CStsItr()
{
	//ULOG_START("CStsItr::~CStsItr()");	
	ZeraStatusUsuario();
	//ULOG_END("CStsItr::~CStsItr()");	
}

int CStsItr::Primeiro( void )
{
	//ULOG_START("CStsItr::Primeiro()");	
	_iPosicao = 0;
	//ULOG_END("CStsItr::Primeiro()");	
	return _iPosicao;
}

int CStsItr::Proximo( void )
{
	//ULOG_START("CStsItr::Proximo()");	
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CStsItr::Proximo()");	
	return _iPosicao;
}

int CStsItr::Anterior( void )
{
	//ULOG_START("CStsItr::Anterior()");	
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CStsItr::Anterior()");	
	return _iPosicao;
}

int CStsItr::Ultimo( void )
{
	//ULOG_START("CStsItr::Ultimo()");	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CStsItr::Ultimo()");	
	return _iPosicao;
}

int CStsItr::Quantidade( void )
{
	//ULOG_START("CStsItr::Quantidade()");
	//ULOG_END("CStsItr::Quantidade()");		
	return _iQuantidade;
}

STStatusUsuarioRegistro* CStsItr::Registro( void )
{
    //ULOG_START("CStsItr::Registro()");	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CStsItr::Registro()");	
		return &stcrStatusUsuario[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CStsItr::Registro()");	
		return 0;
	}
}

STStatusUsuarioRegistro* CStsItr::Registro(int nPosicao)
{
	//ULOG_START("CStsItr::Registro()");	
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CStsItr::Registro()");
		return &stcrStatusUsuario[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CStsItr::Registro()");
		return 0;
	}
}

void CStsItr::Add( char* cid, char* csigla, char* cdescricao, char* cindisponibilidade )
{
	//ULOG_START("CStsItr::Add()");
	_iQuantidade++;
	stcrStatusUsuario = (struct STStatusUsuarioRegistro*) realloc( stcrStatusUsuario, sizeof(STStatusUsuarioRegistro)*_iQuantidade );
	strcpy( stcrStatusUsuario[_iQuantidade-1].cidStatusUsuario, cid );
	strcpy( stcrStatusUsuario[_iQuantidade-1].csgStatusUsuario, csigla );
	strcpy( stcrStatusUsuario[_iQuantidade-1].cdsStatusUsuario, cdescricao );
	strcpy( stcrStatusUsuario[_iQuantidade-1].cIndisponibilidade, cindisponibilidade );
	//ULOG_END("CStsItr::Add()");
}


void CStsItr::ZeraStatusUsuario( void )
{
	//ULOG_START("CStsItr::ZeraStatusUsuario()");
	if( _iQuantidade > 0 )
	{
		free( stcrStatusUsuario );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrStatusUsuario    = NULL;
	//ULOG_END("CStsItr::ZeraStatusUsuario()");
}