#include <stdio.h>
#include "CNvlItr.h"

CNvlItr::CNvlItr()
{
	//ULOG_START("CNvlItr::CNvlItr()");
	_iQuantidade = 0;
	_iNivel    = 0;
	stcrNivel    = NULL;
	//ULOG_END("CNvlItr::CNvlItr()");
}

CNvlItr::~CNvlItr()
{
	//ULOG_START("CNvlItr::~CNvlItr()");
	ZeraNivel();
	//ULOG_END("CNvlItr::~CNvlItr()");
}

int CNvlItr::Primeiro( void )
{
	//ULOG_START("CNvlItr::Primeiro( )");
	_iNivel = 0;
	//ULOG_END("CNvlItr::Primeiro( )");
	return _iNivel;
}

int CNvlItr::Proximo( void )
{
	//ULOG_START("CNvlItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iNivel < (_iQuantidade-1) )
			_iNivel++;
	}
	//ULOG_END("CNvlItr::Proximo()");
	return _iNivel;
}

int CNvlItr::Anterior( void )
{
	//ULOG_START("CNvlItr::Anterior()");
	if( _iNivel > 0 )
		_iNivel--;
    //ULOG_END("CNvlItr::Anterior()");
	return _iNivel;
}

int CNvlItr::Ultimo( void )
{
	//ULOG_START("CNvlItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iNivel = _iQuantidade-1;
	else
		_iNivel = 0;
    //ULOG_END("CNvlItr::Ultimo()");
	return _iNivel;
}

int CNvlItr::Quantidade( void )
{
	return _iQuantidade;
}

STNivelRegistro* CNvlItr::Registro( void )
{
	//ULOG_START("CNvlItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CNvlItr::Registro()");
		return &stcrNivel[ _iNivel ];
	}
	else
	{
	    //ULOG_END("CNvlItr::Registro()");
		return 0;
	}
}

STNivelRegistro* CNvlItr::Registro(int nNivel)
{
	//ULOG_START("CNvlItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nNivel >= _iQuantidade )
			nNivel = Ultimo();

		//ULOG_END("CNvlItr::Registro()");
		return &stcrNivel[ nNivel ];
		
	}
	else
	{
		//ULOG_END("CNvlItr::Registro()");
		return 0;
	}
}

void CNvlItr::Add( char* cid, char* cnome )
{
	//ULOG_START("CNvlItr::Add()");
	_iQuantidade++;
	stcrNivel = (struct STNivelRegistro*) realloc( stcrNivel, sizeof(STNivelRegistro)*_iQuantidade );
	strcpy( stcrNivel[_iQuantidade-1].cidNivel, cid );
	strcpy( stcrNivel[_iQuantidade-1].cdsNivel, cnome );
    //ULOG_END("CNvlItr::Add()");
}


void CNvlItr::ZeraNivel( void )
{
	//ULOG_START("CNvlItr::ZeraNivel()");
	if( _iQuantidade > 0 )
	{
		free( stcrNivel );
	}
	_iQuantidade = 0;
	_iNivel    = 0;
	stcrNivel    = NULL;
	//ULOG_END("CNvlItr::ZeraNivel()");
}