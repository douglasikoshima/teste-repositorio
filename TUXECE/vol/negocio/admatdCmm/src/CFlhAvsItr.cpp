#include <stdio.h>
#include "../include/CFlhAvsItr.h"

CContatoFolhaMensagemAvisoItr::CContatoFolhaMensagemAvisoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaMensagemAviso    = NULL;
}

CContatoFolhaMensagemAvisoItr::~CContatoFolhaMensagemAvisoItr()
{
	ZeraContatoFolhaMensagemAviso();
}

int CContatoFolhaMensagemAvisoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoFolhaMensagemAvisoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoFolhaMensagemAvisoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoFolhaMensagemAvisoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoFolhaMensagemAvisoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoFolhaMensagemAvisoRegistro* CContatoFolhaMensagemAvisoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoFolhaMensagemAviso[ _iPosicao ];
	else
		return 0;
}

STContatoFolhaMensagemAvisoRegistro* CContatoFolhaMensagemAvisoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoFolhaMensagemAviso[ nPosicao ];

	}
	else
		return 0;
}

void CContatoFolhaMensagemAvisoItr::Add(
			char* cidContato,
			char* cidMensagemAviso )
{
	_iQuantidade++;
	stcrContatoFolhaMensagemAviso = (struct STContatoFolhaMensagemAvisoRegistro*) realloc( stcrContatoFolhaMensagemAviso, sizeof(STContatoFolhaMensagemAvisoRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaMensagemAviso[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoFolhaMensagemAviso[_iQuantidade-1].cidMensagemAviso, cidMensagemAviso);
}


void CContatoFolhaMensagemAvisoItr::ZeraContatoFolhaMensagemAviso( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolhaMensagemAviso );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaMensagemAviso    = NULL;
}
