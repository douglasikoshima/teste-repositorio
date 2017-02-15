#include <stdio.h>
#include "../include/CBxaListarMsgItr.h"


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

	return 0;
}

void CBaixaMensagemItr::Add( char* cidBaixaMensagem
							,char* cidBaixa
							,char* cidTipoComunicacao
							,char* cdsTipoComunicacao
							,char* cidMensagem
							,char* cdsMensagem )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Junho, 2006 - Cassio
    if ( (_iQuantidade % tamBlocoBaixaItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoBaixaItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoBaixaItr;

	    stcrBaixaMensagem = 
            (struct STBaixaMensagemRegistro*) realloc( stcrBaixaMensagem, 
                                    sizeof(STBaixaMensagemRegistro)*tamBlocoAtu );
    }

	strcpy( stcrBaixaMensagem[_iQuantidade].cidBaixaMensagem, cidBaixaMensagem);
	strcpy( stcrBaixaMensagem[_iQuantidade].cidBaixa, cidBaixa);
	strcpy( stcrBaixaMensagem[_iQuantidade].cidTipoComunicacao, cidTipoComunicacao);
	strcpy( stcrBaixaMensagem[_iQuantidade].cdsTipoComunicacao, cdsTipoComunicacao);
	strcpy( stcrBaixaMensagem[_iQuantidade].cdsMensagem, cdsMensagem);
	strcpy( stcrBaixaMensagem[_iQuantidade].cidMensagem, cidMensagem);

	_iQuantidade++;
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
