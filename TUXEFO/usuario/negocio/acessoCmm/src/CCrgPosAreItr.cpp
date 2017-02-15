#include <stdio.h>
#include "CCrgPosAreItr.h"

CCrgPosAreItr::CCrgPosAreItr()
{
	//ULOG_START("CCrgPosAreItr::CCrgPosAreItr()");
	_iQuantidade = 0;
	_iCargoPosicaoArea    = 0;
	stcrCargoPosicaoArea    = NULL;
	//ULOG_END("CCrgPosAreItr::CCrgPosAreItr()");
}

CCrgPosAreItr::~CCrgPosAreItr()
{
	//ULOG_START("CCrgPosAreItr::~CCrgPosAreItr()");
	ZeraCargoPosicaoArea();
	//ULOG_END("CCrgPosAreItr::~CCrgPosAreItr()");
}

int CCrgPosAreItr::Primeiro( void )
{
	//ULOG_START(" CCrgPosAreItr::Primeiro(  )");
	_iCargoPosicaoArea = 0;
	//ULOG_END(" CCrgPosAreItr::Primeiro(  )");
	return _iCargoPosicaoArea;
}

int CCrgPosAreItr::Proximo( void )
{
	//ULOG_START(" CCrgPosAreItr::Proximo(  )");
	if( _iQuantidade > 0 )
	{
		if( _iCargoPosicaoArea < (_iQuantidade-1) )
			_iCargoPosicaoArea++;
	}
	//ULOG_END(" CCrgPosAreItr::Proximo(  )");
	return _iCargoPosicaoArea;
}

int CCrgPosAreItr::Anterior( void )
{
	//ULOG_START(" CCrgPosAreItr::Anterior(  )");
	if( _iCargoPosicaoArea > 0 )
		_iCargoPosicaoArea--;

	//ULOG_END(" CCrgPosAreItr::Anterior(  )");
	return _iCargoPosicaoArea;
}

int CCrgPosAreItr::Ultimo( void )
{
	//ULOG_START(" CCrgPosAreItr::Ultimo(  )");
	if( _iQuantidade > 0 )
		_iCargoPosicaoArea = _iQuantidade-1;
	else
		_iCargoPosicaoArea = 0;
    //ULOG_END(" CCrgPosAreItr::Ultimo(  )");
	return _iCargoPosicaoArea;
}

int CCrgPosAreItr::Quantidade( void )
{
	//ULOG_START(" CCrgPosAreItr::Quantidade(  )");
	//ULOG_END(" CCrgPosAreItr::Quantidade(  )");
	return _iQuantidade;
}

STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro( void )
{
	//ULOG_START("STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro(  )");
	if( _iQuantidade > 0 )
	{
	    //ULOG_END("STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro(  )");
		return &stcrCargoPosicaoArea[ _iCargoPosicaoArea ];
	}
	else
	{
		//ULOG_END("STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro(  )");
		return 0;
	}
}

STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro(int nCargoPosicaoArea)
{
	//ULOG_START("STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nCargoPosicaoArea >= _iQuantidade )
			nCargoPosicaoArea = Ultimo();
        //ULOG_END("STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro( )");
		return &stcrCargoPosicaoArea[ nCargoPosicaoArea ];
		
	}
	else
	{
	    //ULOG_END("STCargoPosicaoAreaRegistro* CCrgPosAreItr::Registro( )");
		return 0;
	}
}

void CCrgPosAreItr::Add( char* cidCargoPosicaoArea,
                      char* cidCargo, 
                      char* cidPosicao, 
                      char* cidDepartamento )
{
	//ULOG_START("CCrgPosAreItr::Add(  )");
	_iQuantidade++;
	stcrCargoPosicaoArea = (struct STCargoPosicaoAreaRegistro*) realloc( stcrCargoPosicaoArea, sizeof(STCargoPosicaoAreaRegistro)*_iQuantidade );
	strcpy( stcrCargoPosicaoArea[_iQuantidade-1].cidCargoPosicaoArea, cidCargoPosicaoArea );
	strcpy( stcrCargoPosicaoArea[_iQuantidade-1].cidCargo, cidCargo );
	strcpy( stcrCargoPosicaoArea[_iQuantidade-1].cidPosicao, cidPosicao );
	strcpy( stcrCargoPosicaoArea[_iQuantidade-1].cidDepartamento, cidDepartamento );
	strcpy( stcrCargoPosicaoArea[_iQuantidade-1].cidDepartamento, cidPessoaUsuario );
	strcpy( stcrCargoPosicaoArea[_iQuantidade-1].cidDepartamento, cidSessao );
    //ULOG_END("CCrgPosAreItr::Add(  )");
}


void CCrgPosAreItr::ZeraCargoPosicaoArea( void )
{
	//ULOG_START(" CCrgPosAreItr::ZeraCargoPosicaoArea(  )");
	if( _iQuantidade > 0 )
	{
		free( stcrCargoPosicaoArea );
	}
	_iQuantidade = 0;
	_iCargoPosicaoArea    = 0;
	stcrCargoPosicaoArea    = NULL;
	//ULOG_END(" CCrgPosAreItr::ZeraCargoPosicaoArea(  )");
}