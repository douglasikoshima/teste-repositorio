#include <stdio.h>
#include "CSubItr.h"

CSubItr::CSubItr()
{
	//ULOG_START("CSubItr::CSubItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSub    = NULL;
	//ULOG_END("CSubItr::CSubItr()");
}

CSubItr::~CSubItr()
{
	//ULOG_START("CSubItr::~CSubItr()");
	ZeraSub();
	//ULOG_END("CSubItr::~CSubItr()");
}

int CSubItr::Primeiro( void )
{
	//ULOG_START("CSubItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CSubItr::Primeiro()");
	return _iPosicao;
}

int CSubItr::Proximo( void )
{
	//ULOG_START("CSubItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CSubItr::Proximo()");
	return _iPosicao;
}

int CSubItr::Anterior( void )
{
	//ULOG_START("CSubItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;

    //ULOG_END("CSubItr::Anterior()");
	return _iPosicao;
}

int CSubItr::Ultimo( void )
{
	
	//ULOG_START("CSubItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CSubItr::Ultimo()");
	return _iPosicao;
}

int CSubItr::Quantidade( void )
{
	//ULOG_START("CSubItr::Quantidade()");
	//ULOG_END("CSubItr::Quantidade()");
	return _iQuantidade;
}

STSubRegistro* CSubItr::Registro( void )
{
	//ULOG_START("CSubItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CSubItr::Registro()");
		return &stcrSub[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CSubItr::Registro()");
		return 0;
	}
}

STSubRegistro* CSubItr::Registro(int nPosicao)
{
	//ULOG_START("CSubItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		//ULOG_END("CSubItr::Registro()");
		return &stcrSub[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CSubItr::Registro()");
		return 0;
	}
}

void CSubItr::Add( char* cidSubSistema, 
	               char* cnmSubSistema, 
	               char* cidSistema
	             )
{
	//ULOG_START("CSubItr::Add()");
	_iQuantidade++;
	stcrSub = (struct STSubRegistro*) realloc( stcrSub, sizeof(STSubRegistro)*_iQuantidade );
	strcpy( stcrSub[_iQuantidade-1].cidSubSistema, cidSubSistema );
	strcpy( stcrSub[_iQuantidade-1].cnmSubSistema, cnmSubSistema );
	strcpy( stcrSub[_iQuantidade-1].cidSistema, cidSistema );
    //ULOG_END("CSubItr::Add()");
}

void CSubItr::ZeraSub( void )
{
	//ULOG_START("CSubItr::ZeraSub()");
	if( _iQuantidade > 0 )
	{
		free( stcrSub );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSub    = NULL;
	//ULOG_END("CSubItr::ZeraSub()");
}