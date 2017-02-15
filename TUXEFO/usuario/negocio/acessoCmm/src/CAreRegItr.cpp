#include <stdio.h>
#include "CAreRegItr.h"

CAreRegItr::CAreRegItr()
{
	//ULOG_START("CAreRegItr::CAreRegItr()");
	
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAreaRegistro    = NULL;
	
	//ULOG_END("CAreRegItr::CAreRegItr()");
}

CAreRegItr::~CAreRegItr()
{
	//ULOG_START("CAreRegItr::~CAreRegItr()");
	ZeraAreaRegistro();
    //ULOG_END("CAreRegItr::~CAreRegItr()");
}

int CAreRegItr::Primeiro( void )
{
    //ULOG_START("CAreRegItr::Primeiro(  )");
	_iPosicao = 0;
	//ULOG_END("CAreRegItr::Primeiro(  )");
	return _iPosicao;
}

int CAreRegItr::Proximo( void )
{
	//ULOG_START("CAreRegItr::Proximo(  )");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CAreRegItr::Proximo(  )");
	return _iPosicao;
}

int CAreRegItr::Anterior( void )
{
	//ULOG_START("CAreRegItr::Anterior(  )");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CAreRegItr::Anterior(  )");
	return _iPosicao;
}

int CAreRegItr::Ultimo( void )
{
	//ULOG_START("CAreRegItr::Ultimo(  )");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    
    //ULOG_END("CAreRegItr::Ultimo(  )");
	return _iPosicao;
}

int CAreRegItr::Quantidade( void )
{
	//ULOG_START(" CAreRegItr::Quantidade(  )");
	//ULOG_END(" CAreRegItr::Quantidade(  )");
	return _iQuantidade;
}

STAreaRegistroRegistro* CAreRegItr::Registro( void )
{
	//ULOG_START("CAreRegItr::Registro(  )");
	if( _iQuantidade > 0 )
	{
	    //ULOG_END("CAreRegItr::Registro(  )");
		return &stcrAreaRegistro[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CAreRegItr::Registro(  )");
		return 0;
	}
}

STAreaRegistroRegistro* CAreRegItr::Registro(int nPosicao)
{
	//ULOG_START("CAreRegItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

        //ULOG_END("CAreRegItr::Registro( )");
		return &stcrAreaRegistro[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CAreRegItr::Registro( )");
		return 0;
	}
}

void CAreRegItr::Add( char* cidAreaRegistro
	                 ,char* ccdAreaRegistro
	                 ,char* cnmAreaRegistro
	                 ,char* cidUFOperadora )
{
	//ULOG_START(" CAreRegItr::Add()");
	_iQuantidade++;
	stcrAreaRegistro = (struct STAreaRegistroRegistro*) realloc( stcrAreaRegistro, sizeof(STAreaRegistroRegistro)*_iQuantidade );
	strcpy( stcrAreaRegistro[_iQuantidade-1].cidAreaRegistro, cidAreaRegistro );
	strcpy( stcrAreaRegistro[_iQuantidade-1].ccdAreaRegistro, ccdAreaRegistro );
	strcpy( stcrAreaRegistro[_iQuantidade-1].cnmAreaRegistro, cnmAreaRegistro );
	strcpy( stcrAreaRegistro[_iQuantidade-1].cidUFOperadora, cidUFOperadora );
	//ULOG_END(" CAreRegItr::Add()");
}


void CAreRegItr::ZeraAreaRegistro( void )
{
	//ULOG_START(" CAreRegItr::ZeraAreaRegistro(  )");
	
	if( _iQuantidade > 0 )
	{
		free( stcrAreaRegistro );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAreaRegistro    = NULL;
	
	//ULOG_END(" CAreRegItr::ZeraAreaRegistro(  )");
}