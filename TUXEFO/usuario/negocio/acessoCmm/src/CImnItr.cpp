#include <stdio.h>
#include "CImnItr.h"

CImnItr::CImnItr()
{
    //ULOG_START("CImnItr::CImnItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrItemMenu    = NULL;
	//ULOG_END("CImnItr::CImnItr()");
}

CImnItr::~CImnItr()
{
	//ULOG_START("CImnItr::~CImnItr()");
	ZeraItemMenu();
	//ULOG_END("CImnItr::~CImnItr()");
}

int CImnItr::Primeiro( void )
{
	//ULOG_START("CImnItr::Primeiro( )");
	_iPosicao = 0;
	//ULOG_END("CImnItr::Primeiro( )");
	return _iPosicao;
}

int CImnItr::Proximo( void )
{
	//ULOG_START("CImnItr::Proximo( )");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END("CImnItr::Proximo( )");
	return _iPosicao;
}

int CImnItr::Anterior( void )
{
	//ULOG_START("CImnItr::Anterior( )");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END("CImnItr::Anterior( )");
	return _iPosicao;
}

int CImnItr::Ultimo( void )
{
	//ULOG_START("CImnItr::Ultimo( )");
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

    //ULOG_END("CImnItr::Ultimo( )");
	return _iPosicao;
}

int CImnItr::Quantidade( void )
{
	//ULOG_START("CImnItr::Quantidade( )");
	//ULOG_END("CImnItr::Quantidade( )");
	return _iQuantidade;
}

STItemMenuRegistro* CImnItr::Registro( void )
{
	//ULOG_START("CImnItr::Registro( )");
	if( _iQuantidade > 0 )
	{
	    //ULOG_END("CImnItr::Registro( )");
		return &stcrItemMenu[ _iPosicao ];
	}
	else
	{
	    //ULOG_END("CImnItr::Registro( )");
		return 0;
	}
}

STItemMenuRegistro* CImnItr::Registro(int nPosicao)
{
	//ULOG_START("CImnItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		//ULOG_END("CImnItr::Registro()");
		return &stcrItemMenu[ nPosicao ];
		
	}
	else
	{
        //ULOG_END("CImnItr::Registro()");   	
		return 0;
	}
}

void CImnItr::Add( char* cid, 
	                            char* cnome, 
	                            char* chint, 
	                            char* capresentacao, 
	                            char* cvisibilidade, 
	                            char* cidsubsistema, 
	                            int   ilevel,
	                            char* cpai,
								char* cinFolha)
{
	//ULOG_START("CImnItr::Add()");
	_iQuantidade++;
	stcrItemMenu = (struct STItemMenuRegistro*) realloc( stcrItemMenu, sizeof(STItemMenuRegistro)*_iQuantidade );
	strcpy( stcrItemMenu[_iQuantidade-1].cidItemMenu    , cid );
	strcpy( stcrItemMenu[_iQuantidade-1].cnmItemMenu    , cnome );
	strcpy( stcrItemMenu[_iQuantidade-1].cdsHint        , chint );
	strcpy( stcrItemMenu[_iQuantidade-1].csqApresentacao, capresentacao );
	strcpy( stcrItemMenu[_iQuantidade-1].cinVisibilidade, cvisibilidade );
	strcpy( stcrItemMenu[_iQuantidade-1].cidSubSistema  , cidsubsistema );
	stcrItemMenu[_iQuantidade-1].iLevel = ilevel ;
	strcpy( stcrItemMenu[_iQuantidade-1].cidItemMenuPai , cpai );
	strcpy( stcrItemMenu[_iQuantidade-1].cinFolha , cinFolha );
	//ULOG_END("CImnItr::Add()");
}

void CImnItr::Add( char* cidItemMenu
	              ,int   isqApresentacao )
{
	//ULOG_START("CImnItr::Add()");
	_iQuantidade++;
	stcrItemMenu = (struct STItemMenuRegistro*) realloc( stcrItemMenu, sizeof(STItemMenuRegistro)*_iQuantidade );
	strcpy( stcrItemMenu[_iQuantidade-1].cidItemMenu    , cidItemMenu );
	stcrItemMenu[_iQuantidade-1].isqApresentacao = isqApresentacao;
	//ULOG_END("CImnItr::Add()");
}



void CImnItr::ZeraItemMenu( void )
{
	//ULOG_START("CImnItr::ZeraItemMenu()");
	if( _iQuantidade > 0 )
	{
		free( stcrItemMenu );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrItemMenu    = NULL;
	//ULOG_END("CImnItr::ZeraItemMenu()");
}