#include <stdio.h>
#include "CPgnItr.h"

CPgnItr::CPgnItr()
{
	//ULOG_START("CPgnItr::CPgnItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPagina    = NULL;
	//ULOG_END("CPgnItr::CPgnItr()");
}

CPgnItr::~CPgnItr()
{
	//ULOG_START("CPgnItr::~CPgnItr()");
	ZeraPagina();
	//ULOG_END("CPgnItr::~CPgnItr()");
}

int CPgnItr::Primeiro( void )
{
	//ULOG_START("CPgnItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CPgnItr::Primeiro()");
	return _iPosicao;
}

int CPgnItr::Proximo( void )
{
	//ULOG_START("CPgnItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CPgnItr::Proximo()");
	return _iPosicao;
}

int CPgnItr::Anterior( void )
{
	//ULOG_START("CPgnItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CPgnItr::Anterior()");
	return _iPosicao;
}

int CPgnItr::Ultimo( void )
{
	//ULOG_START("CPgnItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    //ULOG_END("CPgnItr::Ultimo()");
	return _iPosicao;
}

int CPgnItr::Quantidade( void )
{
	//ULOG_START(" CPgnItr::Quantidade()");
	//ULOG_END(" CPgnItr::Quantidade()");
	return _iQuantidade;
}

STPaginaRegistro* CPgnItr::Registro( void )
{
	//ULOG_START("CPgnItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CPgnItr::Registro()");
		return &stcrPagina[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CPgnItr::Registro()");
		return 0;
	}
}

STPaginaRegistro* CPgnItr::Registro(int nPosicao)
{
	//ULOG_START("CPgnItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

        //ULOG_END("CPgnItr::Registro()");
		return &stcrPagina[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CPgnItr::Registro()");
		return 0;
	}
}

void CPgnItr::Add( char* cid, char* cnome, char* curl, char* cindisponibilidade, char* cidsubsistema, char* cnmsubsistema )
{
	//ULOG_START("CPgnItr::Add()");
	_iQuantidade++;
	stcrPagina = (struct STPaginaRegistro*) realloc( stcrPagina, sizeof(STPaginaRegistro)*_iQuantidade );
	strcpy( stcrPagina[_iQuantidade-1].cidPagina, cid );
	strcpy( stcrPagina[_iQuantidade-1].cnmPagina, cnome );
	strcpy( stcrPagina[_iQuantidade-1].cnmUrl, curl );
	strcpy( stcrPagina[_iQuantidade-1].cIndisponibilidade, cindisponibilidade );
	strcpy( stcrPagina[_iQuantidade-1].cidSubSistema, cidsubsistema );
	strcpy( stcrPagina[_iQuantidade-1].cnmSubSistema, cnmsubsistema );
	//ULOG_END("CPgnItr::Add()");
}


void CPgnItr::ZeraPagina( void )
{
	//ULOG_START("CPgnItr::ZeraPagina()");
	if( _iQuantidade > 0 )
	{
		free( stcrPagina );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPagina    = NULL;
	//ULOG_END("CPgnItr::ZeraPagina()");
}