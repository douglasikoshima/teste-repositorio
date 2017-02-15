#include <stdio.h>
#include "CNvlHieItr.h"

CNvlHieItr::CNvlHieItr()
{
   //ULOG_START("CNvlHieItr::CNvlHieItr()");
	_iQuantidade = 0;
	_iOperadoraDepto    = 0;
	stcrOperadoraDepto    = NULL;
   //ULOG_END("CNvlHieItr::CNvlHieItr()");
}

CNvlHieItr::~CNvlHieItr()
{
	//ULOG_START("CNvlHieItr::~CNvlHieItr()");
	ZeraOperadoraDepto();
	//ULOG_END("CNvlHieItr::~CNvlHieItr()");
}

int CNvlHieItr::Primeiro( void )
{
	//ULOG_START("CNvlHieItr::Primeiro()");
	_iOperadoraDepto = 0;
	//ULOG_END("CNvlHieItr::Primeiro()");
	return _iOperadoraDepto;
}

int CNvlHieItr::Proximo( void )
{
    //ULOG_START("CNvlHieItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iOperadoraDepto < (_iQuantidade-1) )
			_iOperadoraDepto++;
	}
	//ULOG_END("CNvlHieItr::Proximo()");
	return _iOperadoraDepto;
}

int CNvlHieItr::Anterior( void )
{
	//ULOG_START("CNvlHieItr::Anterior()");
	if( _iOperadoraDepto > 0 )
		_iOperadoraDepto--;

	//ULOG_END("CNvlHieItr::Anterior()");
	return _iOperadoraDepto;
}

int CNvlHieItr::Ultimo( void )
{
	//ULOG_START("CNvlHieItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iOperadoraDepto = _iQuantidade-1;
	else
		_iOperadoraDepto = 0;
    //ULOG_END("CNvlHieItr::Ultimo()");
	return _iOperadoraDepto;
}

int CNvlHieItr::Quantidade( void )
{
	//ULOG_START("CNvlHieItr::Quantidade()");
	//ULOG_END("CNvlHieItr::Quantidade()");
	return _iQuantidade;
}

STOperadoraDeptoRegistro* CNvlHieItr::Registro( void )
{
	//ULOG_START("CNvlHieItr::Registro()");
	if( _iQuantidade > 0 )
	{
	    //ULOG_END("CNvlHieItr::Registro()");
		return &stcrOperadoraDepto[ _iOperadoraDepto ];
	}
	else
    {
    	//ULOG_END("CNvlHieItr::Registro()");
		return 0;
	}
}

STOperadoraDeptoRegistro* CNvlHieItr::Registro(int nOperadoraDepto)
{
	//ULOG_START("CNvlHieItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nOperadoraDepto >= _iQuantidade )
			nOperadoraDepto = Ultimo();
        //ULOG_END("CNvlHieItr::Registro()");
		return &stcrOperadoraDepto[ nOperadoraDepto ];
		
	}
	else
	{
	    //ULOG_END("CNvlHieItr::Registro()");
		return 0;
	}
}

void CNvlHieItr::Add( char* cidOperadoraDepto,
                      char* cidArea, 
                      char* cidPessoaDeParaOperadora )
{
	//ULOG_START("CNvlHieItr::Add()");
	_iQuantidade++;
	stcrOperadoraDepto = (struct STOperadoraDeptoRegistro*) realloc( stcrOperadoraDepto, sizeof(STOperadoraDeptoRegistro)*_iQuantidade );
	strcpy( stcrOperadoraDepto[_iQuantidade-1].cidOperadoraDepto, cidOperadoraDepto );
	strcpy( stcrOperadoraDepto[_iQuantidade-1].cidArea, cidArea );
	strcpy( stcrOperadoraDepto[_iQuantidade-1].cidPessoaDeParaOperadora, cidPessoaDeParaOperadora );
	//ULOG_END("CNvlHieItr::Add()");
}


void CNvlHieItr::ZeraOperadoraDepto( void )
{
	//ULOG_START("CNvlHieItr::ZeraOperadoraDepto()");
	if( _iQuantidade > 0 )
	{
		free( stcrOperadoraDepto );
	}
	_iQuantidade = 0;
	_iOperadoraDepto    = 0;
	stcrOperadoraDepto    = NULL;
	//ULOG_END("CNvlHieItr::ZeraOperadoraDepto()");
}