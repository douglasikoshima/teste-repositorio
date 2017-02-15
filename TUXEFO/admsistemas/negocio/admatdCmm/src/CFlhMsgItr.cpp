#include <stdio.h>
#include "../include/CFlhMsgItr.h"

CFolhaMensagemAvisoItr::CFolhaMensagemAvisoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFolhaMensagemAviso    = NULL;
}

CFolhaMensagemAvisoItr::~CFolhaMensagemAvisoItr()
{
	ZeraFolhaMensagemAviso();
}

int CFolhaMensagemAvisoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CFolhaMensagemAvisoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CFolhaMensagemAvisoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CFolhaMensagemAvisoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CFolhaMensagemAvisoItr::Quantidade( void )
{
	return _iQuantidade;
}

STFolhaMensagemAvisoRegistro* CFolhaMensagemAvisoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrFolhaMensagemAviso[ _iPosicao ];
	else
		return 0;
}

STFolhaMensagemAvisoRegistro* CFolhaMensagemAvisoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrFolhaMensagemAviso[ nPosicao ];

	}
	else
		return 0;
}

void CFolhaMensagemAvisoItr::Add(
			char* cidContato,
			char* cdsMensagemAviso )
{
	_iQuantidade++;
	stcrFolhaMensagemAviso = (struct STFolhaMensagemAvisoRegistro*) realloc( stcrFolhaMensagemAviso, sizeof(STFolhaMensagemAvisoRegistro)*_iQuantidade );
	strcpy( stcrFolhaMensagemAviso[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrFolhaMensagemAviso[_iQuantidade-1].cdsMensagemAviso, cdsMensagemAviso);
}


void CFolhaMensagemAvisoItr::ZeraFolhaMensagemAviso( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrFolhaMensagemAviso );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFolhaMensagemAviso    = NULL;
}
