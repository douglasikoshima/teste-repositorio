#include <stdio.h>
#include "CAreItr.h"

CAreItr::CAreItr()
{
	//ULOG_START("CAreItr::CAreItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrArea    = NULL;
	//ULOG_END("CAreItr::CAreItr()");
}

CAreItr::~CAreItr()
{
	//ULOG_START("CAreItr::~CAreItr()");
	ZeraArea();
	//ULOG_END("CAreItr::~CAreItr()");
}

int CAreItr::Primeiro( void )
{
	//ULOG_START("CAreItr::Primeiro(  )");
	_iPosicao = 0;
	//ULOG_END("CAreItr::Primeiro(  )");
	return _iPosicao;
}

int CAreItr::Proximo( void )
{
	//ULOG_START("CAreItr::Proximo(  )");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CAreItr::Proximo(  )");
	return _iPosicao;
}

int CAreItr::Anterior( void )
{
	//ULOG_START("CAreItr::Anterior(  )");
	if( _iPosicao > 0 )
		_iPosicao--;
	//ULOG_END("CAreItr::Anterior(  )");
	return _iPosicao;
}

int CAreItr::Ultimo( void )
{
	//ULOG_START("CAreItr::Ultimo(  )");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
	//ULOG_END("CAreItr::Ultimo(  )");
	return _iPosicao;
}

int CAreItr::Quantidade( void )
{
	//ULOG_START("CAreItr::Quantidade(  )");
	//ULOG_END("CAreItr::Quantidade(  )");
	return _iQuantidade;
}

STAreaRegistro* CAreItr::Registro( void )
{
	//ULOG_START("CAreItr::Registro(  )");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("STAreaRegistro* CAreItr::Registro(  )");
		return &stcrArea[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CAreItr::Registro(  )");
		return 0;
	}
}

STAreaRegistro* CAreItr::Registro(int nPosicao)
{
	//ULOG_START("CAreItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
	    
	    //ULOG_END("CAreItr::Registro()");
		return &stcrArea[ nPosicao ];		
	}
	else
	{
	    //ULOG_END("CAreItr::Registro()");
		return 0;
	}
}

void CAreItr::Add( char* cid, char* cnome )
{
	//ULOG_START(" CAreItr::Add(  )");
	_iQuantidade++;
	stcrArea = (struct STAreaRegistro*) realloc( stcrArea, sizeof(STAreaRegistro)*_iQuantidade );
	strcpy( stcrArea[_iQuantidade-1].cidArea, cid );
	strcpy( stcrArea[_iQuantidade-1].cnmDepartamento, cnome );
	//ULOG_END(" CAreItr::Add(  )");
}


void CAreItr::ZeraArea( void )
{
	//ULOG_START(" CAreItr::ZeraArea(  )");
	if( _iQuantidade > 0 )
	{
		free( stcrArea );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrArea    = NULL;
	//ULOG_END(" CAreItr::ZeraArea(  )");
}