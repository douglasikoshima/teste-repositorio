#include <stdio.h>
#include "CCrgItr.h"

CCrgItr::CCrgItr()
{
	//ULOG_START("CCrgItr::CCrgItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCargo    = NULL;
	//ULOG_END("CCrgItr::CCrgItr()");
}

CCrgItr::~CCrgItr()
{
	//ULOG_START("CCrgItr::~CCrgItr()");
	ZeraCargo();
	//ULOG_END("CCrgItr::~CCrgItr()");
}

int CCrgItr::Primeiro( void )
{
	//ULOG_START(" CCrgItr::Primeiro(  )");
	_iPosicao = 0;
	//ULOG_END(" CCrgItr::Primeiro(  )");
	return _iPosicao;
}

int CCrgItr::Proximo( void )
{
	//ULOG_START(" CCrgItr::Proximo(  )");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END(" CCrgItr::Proximo(  )");
	return _iPosicao;
}

int CCrgItr::Anterior( void )
{
	//ULOG_START(" CCrgItr::Anterior(  )");
	if( _iPosicao > 0 )
		_iPosicao--;

    //ULOG_END(" CCrgItr::Anterior(  )");
	return _iPosicao;
}

int CCrgItr::Ultimo( void )
{
	//ULOG_START(" CCrgItr::Ultimo(  )");
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	//ULOG_END(" CCrgItr::Ultimo(  )");
	return _iPosicao;
}

int CCrgItr::Quantidade( void )
{
	//ULOG_START(" CCrgItr::Quantidade(  )");
	//ULOG_END(" CCrgItr::Quantidade(  )");
	return _iQuantidade;
}

STCargoRegistro* CCrgItr::Registro( void )
{
	//ULOG_START(" CCrgItr::Registro(  )");
	if( _iQuantidade > 0 )
	{
		//ULOG_END(" CCrgItr::Registro(  )");
		return &stcrCargo[ _iPosicao ];
	}
	else
	{
		//ULOG_END(" CCrgItr::Registro(  )");
		return 0;
	}
}

STCargoRegistro* CCrgItr::Registro(int nPosicao)
{
    //ULOG_START(" CCrgItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END(" CCrgItr::Registro( )");
		return &stcrCargo[ nPosicao ];
		
	}
	else
	{
		//ULOG_END(" CCrgItr::Registro( )");
		return 0;
	}
}

void CCrgItr::Add( char* cid, char* cnome )
{
	//ULOG_START(" CCrgItr::Add(  )");
	_iQuantidade++;
	stcrCargo = (struct STCargoRegistro*) realloc( stcrCargo, sizeof(STCargoRegistro)*_iQuantidade );
	strcpy( stcrCargo[_iQuantidade-1].cidCargo, cid );
	strcpy( stcrCargo[_iQuantidade-1].cnmCargo, cnome );
	//ULOG_END(" CCrgItr::Add( )");
}


void CCrgItr::ZeraCargo( void )
{
	//ULOG_START(" CCrgItr::ZeraCargo(  )");
	if( _iQuantidade > 0 )
	{
		free( stcrCargo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCargo    = NULL;
	//ULOG_END(" CCrgItr::ZeraCargo( )");
}