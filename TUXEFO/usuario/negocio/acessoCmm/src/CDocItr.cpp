#include <stdio.h>
#include "CDocItr.h"

CDocumentoItr::CDocumentoItr()
{
  //ULOG_START("CDocumentoItr::CDocumentoItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrDocumento    = NULL;
  //ULOG_END("CDocumentoItr::CDocumentoItr()");
}

CDocumentoItr::~CDocumentoItr()
{
	//ULOG_START("CDocumentoItr::~CDocumentoItr()");
	ZeraDocumento();
	//ULOG_END("CDocumentoItr::~CDocumentoItr()");
}

int CDocumentoItr::Primeiro( void )
{
	//ULOG_START(" CDocumentoItr::Primeiro(  )");
	_iPosicao = 0;
	//ULOG_END(" CDocumentoItr::Primeiro(  )");
	return _iPosicao;
}

int CDocumentoItr::Proximo( void )
{
	//ULOG_START(" CDocumentoItr::Proximo(  )");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END(" CDocumentoItr::Proximo(  )");
	return _iPosicao;
}

int CDocumentoItr::Anterior( void )
{
	//ULOG_START(" CDocumentoItr::Anterior(  )");
	if( _iPosicao > 0 )
		_iPosicao--;
    //ULOG_END(" CDocumentoItr::Anterior(  )");
	return _iPosicao;
}

int CDocumentoItr::Ultimo( void )
{
	//ULOG_START(" CDocumentoItr::Ultimo(  )");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    
    //ULOG_END(" CDocumentoItr::Ultimo(  )");
	return _iPosicao;
}

int CDocumentoItr::Quantidade( void )
{
	//ULOG_START(" CDocumentoItr::Ultimo(  )");
	//ULOG_END(" CDocumentoItr::Ultimo(  )");
	return _iQuantidade;	
}

STDocumentoRegistro* CDocumentoItr::Registro( void )
{
	//ULOG_START("CDocumentoItr::Registro(  )");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CDocumentoItr::Registro(  )");
		return &stcrDocumento[ _iPosicao ];
	}
	else
	{
		//ULOG_END(" CDocumentoItr::Registro(  )");
		return 0;
	}
}

STDocumentoRegistro* CDocumentoItr::Registro(int nPosicao)
{
	//ULOG_START("CDocumentoItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CDocumentoItr::Registro()");
		return &stcrDocumento[ nPosicao ];
		
	}
	else
	{
	    //ULOG_END("CDocumentoItr::Registro( )");
		return 0;
	}
}

void CDocumentoItr::Add( char* cidDocumento,
	                     char* ccdCpfCnpjBase,
	                     char* ccdCnpjFilial,
	                     char* ccdCpfCnpjControle,
	                     char* cnrDocumento,
	                     char* csgOrgaoExpedidor,
	                     char* cdtEmissao,
	                     char* cidPais,
	                     char* csgPais,
	                     char* cnmPais,
	                     char* cidUF,
	                     char* csgUF,
	                     char* cnmUF,
	                     char* cidTipoDocumento,
	                     char* csgTipoDocumento,
	                     char* cdsTipoDocumento )
{
	//ULOG_START(" CDocumentoItr::Add() ");
	_iQuantidade++;
	stcrDocumento = (struct STDocumentoRegistro*) realloc( stcrDocumento, sizeof(STDocumentoRegistro)*_iQuantidade );
	strcpy( stcrDocumento[_iQuantidade-1].cidDocumento, cidDocumento );
	strcpy( stcrDocumento[_iQuantidade-1].ccdCpfCnpjBase, ccdCpfCnpjBase );
	strcpy( stcrDocumento[_iQuantidade-1].ccdCnpjFilial, ccdCnpjFilial );
	strcpy( stcrDocumento[_iQuantidade-1].ccdCpfCnpjControle, ccdCpfCnpjControle );
	strcpy( stcrDocumento[_iQuantidade-1].cnrDocumento, cnrDocumento );
	strcpy( stcrDocumento[_iQuantidade-1].csgOrgaoExpedidor, csgOrgaoExpedidor );
	strcpy( stcrDocumento[_iQuantidade-1].cdtEmissao, cdtEmissao );
	strcpy( stcrDocumento[_iQuantidade-1].cidPais, cidPais );
	strcpy( stcrDocumento[_iQuantidade-1].csgPais, csgPais );
	strcpy( stcrDocumento[_iQuantidade-1].cnmPais, cnmPais );
	strcpy( stcrDocumento[_iQuantidade-1].cidUF, cidUF );
	strcpy( stcrDocumento[_iQuantidade-1].csgUF, csgUF );
	strcpy( stcrDocumento[_iQuantidade-1].cnmUF, cnmUF );
	strcpy( stcrDocumento[_iQuantidade-1].cidTipoDocumento, cidTipoDocumento );
	strcpy( stcrDocumento[_iQuantidade-1].csgTipoDocumento, csgTipoDocumento );
	strcpy( stcrDocumento[_iQuantidade-1].cdsTipoDocumento, cdsTipoDocumento );
	//ULOG_END(" CDocumentoItr::Add( )");
}


void CDocumentoItr::ZeraDocumento( void )
{
	//ULOG_START(" CDocumentoItr::ZeraDocumento(  )");
	if( _iQuantidade > 0 )
	{
		free( stcrDocumento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrDocumento    = NULL;
	//ULOG_END(" CDocumentoItr::ZeraDocumento(  )");
}