#include <stdio.h>
#include "../include/CAvsItr.h"

CMensagemAvisoItr::CMensagemAvisoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMensagemAviso    = NULL;
}

CMensagemAvisoItr::~CMensagemAvisoItr()
{
	ZeraMensagemAviso();
}

int CMensagemAvisoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CMensagemAvisoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CMensagemAvisoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CMensagemAvisoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CMensagemAvisoItr::Quantidade( void )
{
	return _iQuantidade;
}

STMensagemAvisoRegistro* CMensagemAvisoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrMensagemAviso[ _iPosicao ];
	else
		return 0;
}

STMensagemAvisoRegistro* CMensagemAvisoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrMensagemAviso[ nPosicao ];

	}
	else
		return 0;
}

void CMensagemAvisoItr::Add(
			char* cidMensagemAviso,
			char* cdsMensagemAviso )
{
	_iQuantidade++;
	stcrMensagemAviso = (struct STMensagemAvisoRegistro*) realloc( stcrMensagemAviso, sizeof(STMensagemAvisoRegistro)*_iQuantidade );
	strcpy( stcrMensagemAviso[_iQuantidade-1].cidMensagemAviso, cidMensagemAviso);
	strcpy( stcrMensagemAviso[_iQuantidade-1].cdsMensagemAviso, cdsMensagemAviso);
}


void CMensagemAvisoItr::ZeraMensagemAviso( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrMensagemAviso );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMensagemAviso    = NULL;
}
