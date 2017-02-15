#include <stdio.h>
#include "../include/CContatoTipoCarteiraItr.h"

CContatoTipoCarteiraItr::CContatoTipoCarteiraItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoCarteira    = NULL;
}

CContatoTipoCarteiraItr::~CContatoTipoCarteiraItr()
{
	ZeraContatoTipoCarteira();
}

int CContatoTipoCarteiraItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoTipoCarteiraItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoTipoCarteiraItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoTipoCarteiraItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoTipoCarteiraItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoTipoCarteiraRegistro* CContatoTipoCarteiraItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoTipoCarteira[ _iPosicao ];
	else
		return 0;
}

STContatoTipoCarteiraRegistro* CContatoTipoCarteiraItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoTipoCarteira[ nPosicao ];

	}
	else
		return 0;
}

void CContatoTipoCarteiraItr::Add(
								char* cidContatoTipoCarteira,
								char* cidContato,
								char* cidTipoCarteira
							  )
{
	_iQuantidade++;
	stcrContatoTipoCarteira = (struct STContatoTipoCarteiraRegistro*) realloc( stcrContatoTipoCarteira, sizeof(STContatoTipoCarteiraRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoCarteira[_iQuantidade-1].cidContatoTipoCarteira, cidContatoTipoCarteira);
	strcpy( stcrContatoTipoCarteira[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoTipoCarteira[_iQuantidade-1].cidTipoCarteira, cidTipoCarteira);
}


void CContatoTipoCarteiraItr::ZeraContatoTipoCarteira( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoTipoCarteira );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoCarteira    = NULL;
}
