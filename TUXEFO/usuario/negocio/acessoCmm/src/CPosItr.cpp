#include <stdio.h>
#include "CPosItr.h"

CPosItr::CPosItr()
{
	//ULOG_START("CPosItr::CPosItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPosicao    = NULL;
	//ULOG_END("CPosItr::CPosItr()");
}

CPosItr::~CPosItr()
{
	//ULOG_START("CPosItr::~CPosItr()");
	ZeraPosicao();
	//ULOG_END("CPosItr::~CPosItr()");
}

int CPosItr::Primeiro( void )
{
	//ULOG_START("CPosItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CPosItr::Primeiro()");
	return _iPosicao;
}

int CPosItr::Proximo( void )
{
	//ULOG_START("CPosItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CPosItr::Proximo()");
	return _iPosicao;
}

int CPosItr::Anterior( void )
{
	//ULOG_START("CPosItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    
    //ULOG_END("CPosItr::Anterior()");
	return _iPosicao;
}

int CPosItr::Ultimo( void )
{
	//ULOG_START("CPosItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CPosItr::Ultimo()");
	return _iPosicao;
}

int CPosItr::Quantidade( void )
{
	//ULOG_START("CPosItr::Quantidade()");
	//ULOG_END("CPosItr::Quantidade()");
	return _iQuantidade;
}

STPosicaoRegistro* CPosItr::Registro( void )
{
	//ULOG_START("CPosItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CPosItr::Registro()");
		return &stcrPosicao[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CPosItr::Registro()");
		return 0;
	}
}

STPosicaoRegistro* CPosItr::Registro(int nPosicao)
{
	//ULOG_START("CPosItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        
        //ULOG_END("CPosItr::Registro()");
		return &stcrPosicao[ nPosicao ];		
	}
	else
	{
		//ULOG_END("CPosItr::Registro()");
		return 0;
	}
}

void CPosItr::Add( char* cid, char* cnome )
{
	//ULOG_START("CPosItr::Add()");
	_iQuantidade++;
	stcrPosicao = (struct STPosicaoRegistro*) realloc( stcrPosicao, sizeof(STPosicaoRegistro)*_iQuantidade );
	strcpy( stcrPosicao[_iQuantidade-1].cidPosicao, cid );
	strcpy( stcrPosicao[_iQuantidade-1].cdsPosicao, cnome );
	//ULOG_END("CPosItr::Add()");
}


void CPosItr::ZeraPosicao( void )
{
	//ULOG_START("CPosItr::ZeraPosicao()");
	if( _iQuantidade > 0 )
	{
		free( stcrPosicao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPosicao    = NULL;
	//ULOG_END("CPosItr::ZeraPosicao()");
}