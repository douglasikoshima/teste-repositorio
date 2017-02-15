#include <stdio.h>
#include "CNvlHieItr.h"

CNvlHieItr::CNvlHieItr()
{
	//ULOG_START("CNvlHieItr::CNvlHieItr()");
	_iQuantidade = 0;
	_iNivelHierarquia    = 0;
	stcrNivelHierarquia    = NULL;
	//ULOG_END("CNvlHieItr::CNvlHieItr()");
}

CNvlHieItr::~CNvlHieItr()
{
	//ULOG_START("CNvlHieItr::~CNvlHieItr()");
	ZeraNivelHierarquia();
	//ULOG_END("CNvlHieItr::~CNvlHieItr()");
}

int CNvlHieItr::Primeiro( void )
{
	//ULOG_START("CNvlHieItr::Primeiro()");
	_iNivelHierarquia = 0;
	//ULOG_END("CNvlHieItr::Primeiro()");
	return _iNivelHierarquia;
}

int CNvlHieItr::Proximo( void )
{
	//ULOG_START("CNvlHieItr::Proximo()");
	if( _iQuantidade > 0 )
	{
		if( _iNivelHierarquia < (_iQuantidade-1) )
			_iNivelHierarquia++;
	}
	//ULOG_END("CNvlHieItr::Proximo()");
	return _iNivelHierarquia;
}

int CNvlHieItr::Anterior( void )
{
	//ULOG_START("CNvlHieItr::Anterior()");
	if( _iNivelHierarquia > 0 )
		_iNivelHierarquia--;
    
    //ULOG_END("CNvlHieItr::Anterior()");
	return _iNivelHierarquia;
}

int CNvlHieItr::Ultimo( void )
{
	//ULOG_START("CNvlHieItr::Ultimo()");
	if( _iQuantidade > 0 )
		_iNivelHierarquia = _iQuantidade-1;
	else
		_iNivelHierarquia = 0;

	//ULOG_END("CNvlHieItr::Ultimo()");
	return _iNivelHierarquia;
}

int CNvlHieItr::Quantidade( void )
{
	//ULOG_START("CNvlHieItr::Quantidade()");
	//ULOG_END("CNvlHieItr::Quantidade()");
	return _iQuantidade;
}

STNivelHierarquiaRegistro* CNvlHieItr::Registro( void )
{
	//ULOG_START("CNvlHieItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CNvlHieItr::Registro()");
		return &stcrNivelHierarquia[ _iNivelHierarquia ];
	}
	else
	{
	    //ULOG_END("CNvlHieItr::Registro()");
		return 0;
	}
}

STNivelHierarquiaRegistro* CNvlHieItr::Registro(int nNivelHierarquia)
{
	//ULOG_START("CNvlHieItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nNivelHierarquia >= _iQuantidade )
			nNivelHierarquia = Ultimo();

		//ULOG_END("CNvlHieItr::Registro()");
		return &stcrNivelHierarquia[ nNivelHierarquia ];
		
	}
	else
	{
	    //ULOG_END("CNvlHieItr::Registro()");
		return 0;
	}
}

void CNvlHieItr::Add( char* cidNivelHierarquia,
                      char* cidNivelPai, 
                      char* cidNivel, 
                      char* cidCargo )
{
	//ULOG_START("CNvlHieItr::Add()");
	_iQuantidade++;
	stcrNivelHierarquia = (struct STNivelHierarquiaRegistro*) realloc( stcrNivelHierarquia, sizeof(STNivelHierarquiaRegistro)*_iQuantidade );
	strcpy( stcrNivelHierarquia[_iQuantidade-1].cidNivelHierarquia, cidNivelHierarquia );
	strcpy( stcrNivelHierarquia[_iQuantidade-1].cidNivelPai, cidNivelPai );
	strcpy( stcrNivelHierarquia[_iQuantidade-1].cidNivel, cidNivel );
	strcpy( stcrNivelHierarquia[_iQuantidade-1].cidCargo, cidCargo );
	//ULOG_END("CNvlHieItr::Add()");
}


void CNvlHieItr::ZeraNivelHierarquia( void )
{
	
	//ULOG_START("CNvlHieItr::ZeraNivelHierarquia()");
	if( _iQuantidade > 0 )
	{
		free( stcrNivelHierarquia );
	}
	_iQuantidade = 0;
	_iNivelHierarquia    = 0;
	stcrNivelHierarquia    = NULL;
	//ULOG_END("CNvlHieItr::ZeraNivelHierarquia()");
}