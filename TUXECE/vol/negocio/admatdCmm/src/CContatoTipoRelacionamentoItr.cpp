#include <stdio.h>
#include "../include/CContatoTipoRelacionamentoItr.h"

CContatoTipoRelacionamentoItr::CContatoTipoRelacionamentoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoRelacionamento    = NULL;
}

CContatoTipoRelacionamentoItr::~CContatoTipoRelacionamentoItr()
{
	ZeraContatoTipoRelacionamento();
}

int CContatoTipoRelacionamentoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoTipoRelacionamentoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoTipoRelacionamentoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoTipoRelacionamentoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoTipoRelacionamentoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoTipoRelacionamentoRegistro* CContatoTipoRelacionamentoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoTipoRelacionamento[ _iPosicao ];
	else
		return 0;
}

STContatoTipoRelacionamentoRegistro* CContatoTipoRelacionamentoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoTipoRelacionamento[ nPosicao ];

	}
	else
		return 0;
}

void CContatoTipoRelacionamentoItr::Add(
								char* cidContatoTipoRelacionamento,
								char* cidContato,
								char* cidTipoRelacionamento
							  )
{
	_iQuantidade++;
	stcrContatoTipoRelacionamento = (struct STContatoTipoRelacionamentoRegistro*) realloc( stcrContatoTipoRelacionamento, sizeof(STContatoTipoRelacionamentoRegistro)*_iQuantidade );
	strcpy( stcrContatoTipoRelacionamento[_iQuantidade-1].cidContatoTipoRelacionamento, cidContatoTipoRelacionamento);
	strcpy( stcrContatoTipoRelacionamento[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoTipoRelacionamento[_iQuantidade-1].cidTipoRelacionamento, cidTipoRelacionamento);
}


void CContatoTipoRelacionamentoItr::ZeraContatoTipoRelacionamento( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoTipoRelacionamento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoTipoRelacionamento    = NULL;
}
