#include <stdio.h>
#include <CValorPossivelItr.h>

CValorPossivelItr::CValorPossivelItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrValorPossivel    = NULL;
}

CValorPossivelItr::~CValorPossivelItr()
{
	ZeraValorPossivel();
}

int CValorPossivelItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CValorPossivelItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CValorPossivelItr::Quantidade( void )
{
	return _iQuantidade;
}

STValorPossivelRegistro* CValorPossivelItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrValorPossivel[ _iPosicao ];
	else
		return 0;
}

STValorPossivelRegistro* CValorPossivelItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrValorPossivel[ nPosicao ];

	}
	else
		return 0;
}

void CValorPossivelItr::Add( 
						char* cidValorPossivel,
						char* cdsValorPossivel,
						char* cidAtributo,
						char* csqApresentacao,
						char* cinDisponibilidade 
					  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 500;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrValorPossivel = (struct STValorPossivelRegistro*) 
                realloc( stcrValorPossivel, sizeof(STValorPossivelRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrValorPossivel = (struct STValorPossivelRegistro*) realloc( stcrValorPossivel, sizeof(STValorPossivelRegistro)*_iQuantidade );
	strcpy( stcrValorPossivel[_iQuantidade-1].cidValorPossivel, cidValorPossivel);
	strcpy( stcrValorPossivel[_iQuantidade-1].cdsValorPossivel, cdsValorPossivel);
	strcpy( stcrValorPossivel[_iQuantidade-1].cidAtributo, cidAtributo);
	strcpy( stcrValorPossivel[_iQuantidade-1].csqApresentacao, csqApresentacao);
	strcpy( stcrValorPossivel[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
}


void CValorPossivelItr::ZeraValorPossivel( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrValorPossivel );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrValorPossivel    = NULL;
}
