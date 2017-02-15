#include <stdio.h>
#include <CTipoComunicacaoItr.h>

CTipoComunicacaoItr::CTipoComunicacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoComunicacao    = NULL;
}

CTipoComunicacaoItr::~CTipoComunicacaoItr()
{
	ZeraTipoComunicacao();
}

int CTipoComunicacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoComunicacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoComunicacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoComunicacaoRegistro* CTipoComunicacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoComunicacao[ _iPosicao ];
	else
		return 0;
}

STTipoComunicacaoRegistro* CTipoComunicacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoComunicacao[ nPosicao ];

	}
	else
		return 0;
}

void CTipoComunicacaoItr::Add( 
			char* cidTipoComunicacao,
			char* csgTipoComunicacao,
			char* cdsTipoComunicacao,
			char* cidFormaRetorno )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrTipoComunicacao = (struct STTipoComunicacaoRegistro*) 
                realloc( stcrTipoComunicacao, sizeof(STTipoComunicacaoRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrTipoComunicacao = (struct STTipoComunicacaoRegistro*) realloc( stcrTipoComunicacao, sizeof(STTipoComunicacaoRegistro)*_iQuantidade );
	strcpy( stcrTipoComunicacao[_iQuantidade-1].cidTipoComunicacao, cidTipoComunicacao);
	strcpy( stcrTipoComunicacao[_iQuantidade-1].csgTipoComunicacao, csgTipoComunicacao);
	strcpy( stcrTipoComunicacao[_iQuantidade-1].cdsTipoComunicacao, cdsTipoComunicacao);
	strcpy( stcrTipoComunicacao[_iQuantidade-1].cidFormaRetorno, cidFormaRetorno);
}


void CTipoComunicacaoItr::ZeraTipoComunicacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoComunicacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoComunicacao    = NULL;
}
