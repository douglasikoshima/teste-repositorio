#include <stdio.h>
#include <CTipoCarteiraItr.h>

CTipoCarteiraItr::CTipoCarteiraItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoCarteira    = NULL;
}

CTipoCarteiraItr::~CTipoCarteiraItr()
{
	ZeraTipoCarteira();
}

int CTipoCarteiraItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoCarteiraItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoCarteiraItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoCarteiraRegistro* CTipoCarteiraItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoCarteira[ _iPosicao ];
	else
		return 0;
}

STTipoCarteiraRegistro* CTipoCarteiraItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoCarteira[ nPosicao ];
		
	}
	else
		return 0;
}

void CTipoCarteiraItr::Add( 
							char* cidTipoCarteira, 
					        char* csgTipoCarteira, 
					        char* cdsTipoCarteira, 
					        char* cvlPeso,
					        char* cinCorporativo
	                      )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrTipoCarteira = (struct STTipoCarteiraRegistro*) 
                realloc( stcrTipoCarteira, sizeof(STTipoCarteiraRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrTipoCarteira = (struct STTipoCarteiraRegistro*) realloc( stcrTipoCarteira, sizeof(STTipoCarteiraRegistro)*_iQuantidade );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cidTipoCarteira, cidTipoCarteira );
	strcpy( stcrTipoCarteira[_iQuantidade-1].csgTipoCarteira, csgTipoCarteira );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cdsTipoCarteira, cdsTipoCarteira );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cvlPeso, cvlPeso );
	strcpy( stcrTipoCarteira[_iQuantidade-1].cinCorporativo, cinCorporativo );
}


void CTipoCarteiraItr::ZeraTipoCarteira( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoCarteira );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoCarteira    = NULL;
}