#include <stdio.h>
#include "CUFItr.h"

CUFItr::CUFItr()
{
	//ULOG_START("CUFItr::CUFItr()");	
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUF    = NULL;
	//ULOG_END("CUFItr::CUFItr()");	
}

CUFItr::~CUFItr()
{
	//ULOG_START("CUFItr::~CUFItr()");	
	ZeraUF();
	//ULOG_END("CUFItr::~CUFItr()");	
}

int CUFItr::Primeiro( void )
{
	//ULOG_START("CUFItr::Primeiro()");	
	_iPosicao = 0;
	//ULOG_END("CUFItr::Primeiro()");	
	return _iPosicao;
}

int CUFItr::Proximo( void )
{
	//ULOG_START("CUFItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CUFItr::Proximo()");
	return _iPosicao;
}

int CUFItr::Anterior( void )
{
	//ULOG_START("CUFItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CUFItr::Anterior()");
	return _iPosicao;
}

int CUFItr::Ultimo( void )
{
	//ULOG_START("CUFItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CUFItr::Ultimo()");
	return _iPosicao;
}

int CUFItr::Quantidade( void )
{
	//ULOG_START("CUFItr::Quantidade()");
	//ULOG_END("CUFItr::Quantidade()");
	return _iQuantidade;
}

STUFRegistro* CUFItr::Registro( void )
{
	//ULOG_START("CUFItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CUFItr::Registro()");
		return &stcrUF[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CUFItr::Registro()");
		return 0;
	}
}

STUFRegistro* CUFItr::Registro(int nPosicao)
{
	//ULOG_START("CUFItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		//ULOG_END("CUFItr::Registro()");
		return &stcrUF[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CUFItr::Registro()");
		return 0;
	}
}

void CUFItr::Add( char* cid, char* csigla, char* cnome )
{
	//ULOG_START("CUFItr::Add()");
	_iQuantidade++;
	stcrUF = (struct STUFRegistro*) realloc( stcrUF, sizeof(STUFRegistro)*_iQuantidade );
	strcpy( stcrUF[_iQuantidade-1].cidUF, cid );
	strcpy( stcrUF[_iQuantidade-1].csgUF, csigla );
	strcpy( stcrUF[_iQuantidade-1].cnmUF, cnome );
	//ULOG_END("CUFItr::Add()");
}


void CUFItr::ZeraUF( void )
{
	//ULOG_START("CUFItr::ZeraUF()");
	if( _iQuantidade > 0 )
	{
		free( stcrUF );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUF       = NULL;
	//ULOG_END("CUFItr::ZeraUF()");
}