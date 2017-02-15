#include <stdio.h>
#include "CPaisItr.h"

CPaisItr::CPaisItr()
{
	//ULOG_START("CPaisItr::CPaisItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPais    = NULL;
	//ULOG_END("CPaisItr::CPaisItr()");
}

CPaisItr::~CPaisItr()
{
	//ULOG_START("CPaisItr::~CPaisItr()");
	ZeraPais();
	//ULOG_END("CPaisItr::~CPaisItr()");
}

int CPaisItr::Primeiro( void )
{
    //ULOG_START("CPaisItr::Primeiro()");
	_iPosicao = 0;
	//ULOG_END("CPaisItr::Primeiro()");
	return _iPosicao;
}

int CPaisItr::Proximo( void )
{
	//ULOG_START("CPaisItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CPaisItr::Proximo()");
	return _iPosicao;
}

int CPaisItr::Anterior( void )
{
	//ULOG_START("CPaisItr::Anterior()");
	if( _iPosicao > 0 )
		_iPosicao--;
    
    //ULOG_END("CPaisItr::Anterior()");
	return _iPosicao;
}

int CPaisItr::Ultimo( void )
{
	//ULOG_START("CPaisItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

    //ULOG_END("CPaisItr::Ultimo()");
	return _iPosicao;
}

int CPaisItr::Quantidade( void )
{
	//ULOG_START("CPaisItr::Quantidade()");
	//ULOG_END("CPaisItr::Quantidade()");
	return _iQuantidade;
}

STPaisRegistro* CPaisItr::Registro( void )
{
	//ULOG_START("CPaisItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CPaisItr::Registro()");
		return &stcrPais[ _iPosicao ];
	}
	else
	{
	    //ULOG_END("CPaisItr::Registro()");
		return 0;
	}
}

STPaisRegistro* CPaisItr::Registro(int nPosicao)
{
	//ULOG_START("CPaisItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		//ULOG_END("CPaisItr::Registro()");
		return &stcrPais[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CPaisItr::Registro()");
		return 0;
	}
}

void CPaisItr::Add( char* cid, char* csigla, char* cdescricao, char* cnacionalidade )
{
	//ULOG_START("CPaisItr::Add()");
	_iQuantidade++;
	stcrPais = (struct STPaisRegistro*) realloc( stcrPais, sizeof(STPaisRegistro)*_iQuantidade );
	strcpy( stcrPais[_iQuantidade-1].cidPais, cid );
	strcpy( stcrPais[_iQuantidade-1].csgPais, csigla );
	strcpy( stcrPais[_iQuantidade-1].cdsPais, cdescricao );
	strcpy( stcrPais[_iQuantidade-1].cdsNacionalidade, cnacionalidade );
	//ULOG_END("CPaisItr::Add()");
}


void CPaisItr::ZeraPais( void )
{
	//ULOG_START("CPaisItr::ZeraPais()");
	if( _iQuantidade > 0 )
	{
		free( stcrPais );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPais    = NULL;
	//ULOG_END("CPaisItr::ZeraPais()");
}