#include <stdio.h>
#include "../include/CBxaMsgItr.h"

CBaixaMensagemItr::CBaixaMensagemItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaMensagem    = NULL;
}

CBaixaMensagemItr::~CBaixaMensagemItr()
{
	ZeraBaixaMensagem();
}

int CBaixaMensagemItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CBaixaMensagemItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CBaixaMensagemItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CBaixaMensagemItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CBaixaMensagemItr::Quantidade( void )
{
	return _iQuantidade;
}

STBaixaMensagemRegistro* CBaixaMensagemItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrBaixaMensagem[ _iPosicao ];
	else
		return 0;
}

STBaixaMensagemRegistro* CBaixaMensagemItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrBaixaMensagem[ nPosicao ];

	}
	else
		return 0;
}

void CBaixaMensagemItr::Add( char* cidBaixaMensagem
							,char* cidBaixa
							,char* cidTipoComunicacao
							,char* cdsTipoComunicacao
							,char* cidMensagem
							,char* cdsMensagem )
{
	_iQuantidade++;
	stcrBaixaMensagem = (struct STBaixaMensagemRegistro*) realloc( stcrBaixaMensagem, sizeof(STBaixaMensagemRegistro)*_iQuantidade );
	strcpy( stcrBaixaMensagem[_iQuantidade-1].cidBaixaMensagem, cidBaixaMensagem);
	strcpy( stcrBaixaMensagem[_iQuantidade-1].cidBaixa, cidBaixa);
	strcpy( stcrBaixaMensagem[_iQuantidade-1].cidTipoComunicacao, cidTipoComunicacao);
	strcpy( stcrBaixaMensagem[_iQuantidade-1].cdsTipoComunicacao, cdsTipoComunicacao);
	strcpy( stcrBaixaMensagem[_iQuantidade-1].cdsMensagem, cdsMensagem);
	strcpy( stcrBaixaMensagem[_iQuantidade-1].cidMensagem, cidMensagem);
}


void CBaixaMensagemItr::ZeraBaixaMensagem( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrBaixaMensagem );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixaMensagem    = NULL;
}
