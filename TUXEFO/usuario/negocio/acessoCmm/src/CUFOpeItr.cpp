#include <stdio.h>
#include "CUFOpeItr.h"

CUFOperadoraItr::CUFOperadoraItr()
{
	//ULOG_START("CUFOperadoraItr::CUFOperadoraItr()");	
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUFOperadora    = NULL;
	//ULOG_END("CUFOperadoraItr::CUFOperadoraItr()");	
}

CUFOperadoraItr::~CUFOperadoraItr()
{
	//ULOG_START("CUFOperadoraItr::~CUFOperadoraItr()");	
	ZeraUFOperadora();
	//ULOG_END("CUFOperadoraItr::~CUFOperadoraItr()");	
}

int CUFOperadoraItr::Primeiro( void )
{
	//ULOG_START("CUFOperadoraItr::Primeiro()");	
	_iPosicao = 0;
	//ULOG_END("CUFOperadoraItr::Primeiro()");	
	return _iPosicao;
}

int CUFOperadoraItr::Proximo( void )
{
	//ULOG_START("CUFOperadoraItr::Proximo( void ))");	
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CUFOperadoraItr::Proximo( void ))");	
	return _iPosicao;
}

int CUFOperadoraItr::Anterior( void )
{
	//ULOG_START("CUFOperadoraItr::Anterior()");	
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CUFOperadoraItr::Anterior()");	
	return _iPosicao;
}

int CUFOperadoraItr::Ultimo( void )
{
	//ULOG_START("CUFOperadoraItr::Ultimo()");	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CUFOperadoraItr::Ultimo()");	
	return _iPosicao;
}

int CUFOperadoraItr::Quantidade( void )
{
	//ULOG_START("CUFOperadoraItr::Quantidade()");	
	//ULOG_END("CUFOperadoraItr::Quantidade()");	
	return _iQuantidade;
}

STUFOperadoraRegistro* CUFOperadoraItr::Registro( void )
{
	//ULOG_START("CUFOperadoraItr::Registro()");	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CUFOperadoraItr::Registro()");	
		return &stcrUFOperadora[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CUFOperadoraItr::Registro()");	
		return 0;
	}
}

STUFOperadoraRegistro* CUFOperadoraItr::Registro(int nPosicao)
{
	//ULOG_START("CUFOperadoraItr::Registro()");	
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CUFOperadoraItr::Registro()");	
		return &stcrUFOperadora[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CUFOperadoraItr::Registro()");	
		return 0;
	}
}

void CUFOperadoraItr::Add( char* cidUFOperadora
				          ,char* cdsUFOperadora
				          ,char* cidUF
				          ,char* csgUF 
				          ,char* cnmUF )
{
	//ULOG_START("CUFOperadoraItr::Add()");	
	_iQuantidade++;
	stcrUFOperadora = (struct STUFOperadoraRegistro*) realloc( stcrUFOperadora, sizeof(STUFOperadoraRegistro)*_iQuantidade );
	strcpy( stcrUFOperadora[_iQuantidade-1].cidUFOperadora, cidUFOperadora );
	strcpy( stcrUFOperadora[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora );
	strcpy( stcrUFOperadora[_iQuantidade-1].cidUF         , cidUF          );
	strcpy( stcrUFOperadora[_iQuantidade-1].csgUF         , csgUF          );
	strcpy( stcrUFOperadora[_iQuantidade-1].cnmUF         , cnmUF          );
	//ULOG_END("CUFOperadoraItr::Add()");	
}


void CUFOperadoraItr::ZeraUFOperadora( void )
{
	//ULOG_START("CUFOperadoraItr::ZeraUFOperadora()");	
	if( _iQuantidade > 0 )
	{
		free( stcrUFOperadora );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrUFOperadora    = NULL;
	//ULOG_END("CUFOperadoraItr::ZeraUFOperadora()");	
}