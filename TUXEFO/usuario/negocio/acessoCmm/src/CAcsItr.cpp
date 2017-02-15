#include <stdio.h>
#include "CAcsItr.h"

CAcsItr::CAcsItr()
{
	//ULOG_START("CAcsItr::CAcsItr()");
	
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAcessoUsuario    = NULL;
	
	//ULOG_END("CAcsItr::CAcsItr()");
}

CAcsItr::~CAcsItr()
{
	//ULOG_START("CAcsItr::~CAcsItr()");

		ZeraAcessoUsuario();
	
	//ULOG_END("CAcsItr::~CAcsItr()");
}

int CAcsItr::Primeiro( void )
{
    //ULOG_START("CAcsItr::Primeiro(  )");
        
	_iPosicao = 0;

	//ULOG_END("CAcsItr::Primeiro(  )");
	return _iPosicao;
}

int CAcsItr::Proximo( void )
{
	//ULOG_START("CAcsItr::Proximo(  )");	
	
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	
	//ULOG_END("CAcsItr::Proximo(  )");
	return _iPosicao;
}

int CAcsItr::Anterior( void )
{
	//ULOG_START("CAcsItr::Anterior(  )");
		
	if( _iPosicao > 0 )
		_iPosicao--;

    //ULOG_END("CAcsItr::Anterior(  )");
	return _iPosicao;
}

int CAcsItr::Ultimo( void )
{
	//ULOG_START("CAcsItr::Ultimo(  )");
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

    //ULOG_END("CAcsItr::Ultimo(  )");
	return _iPosicao;
}

int CAcsItr::Quantidade( void )
{
	//ULOG_START("CAcsItr::Quantidade(  )");
	//ULOG_END("CAcsItr::Quantidade(  )");
	return _iQuantidade;
}

STAcessoUsuarioRegistro* CAcsItr::Registro( void )
{
	//ULOG_START("CAcsItr::Registro(  )");
	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CAcsItr::Registro(  )");
		return &stcrAcessoUsuario[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CAcsItr::Registro(  )");
		return 0;
	}
}

STAcessoUsuarioRegistro* CAcsItr::Registro(int nPosicao)
{
	//ULOG_START("CAcsItr::Registro( )");
	
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		//ULOG_END("CAcsItr::Registro( )");
		return &stcrAcessoUsuario[ nPosicao ];
		
	}
	else
	{
		//ULOG_END("CAcsItr::Registro()");
		return 0;
	}
}

void CAcsItr::Add( char* cid, char* cnome )
{
	//ULOG_START("CAcsItr::Add( )");
	
	_iQuantidade++;
	stcrAcessoUsuario = (struct STAcessoUsuarioRegistro*) realloc( stcrAcessoUsuario, sizeof(STAcessoUsuarioRegistro)*_iQuantidade );
	strcpy( stcrAcessoUsuario[_iQuantidade-1].cidAcessoUsuario, cid );
	strcpy( stcrAcessoUsuario[_iQuantidade-1].cnmAcessoUsuario, cnome );
	
	//ULOG_END("CAcsItr::Add( )");
}


void CAcsItr::ZeraAcessoUsuario( void )
{
	//ULOG_START("CAcsItr::ZeraAcessoUsuario(  )");
	
	if( _iQuantidade > 0 )
	{
		free( stcrAcessoUsuario );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrAcessoUsuario    = NULL;
	
	//ULOG_END("CAcsItr::ZeraAcessoUsuario(  )");
}