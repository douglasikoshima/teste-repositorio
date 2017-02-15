#include <stdio.h>
#include <CTipoDocumentoItr.h>

CTipoDocumentoItr::CTipoDocumentoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoDocumento    = NULL;
}

CTipoDocumentoItr::~CTipoDocumentoItr()
{
	ZeraTipoDocumento();
}

int CTipoDocumentoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoDocumentoItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoDocumentoItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoDocumentoRegistro* CTipoDocumentoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoDocumento[ _iPosicao ];
	else
		return 0;
}

STTipoDocumentoRegistro* CTipoDocumentoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoDocumento[ nPosicao ];
		
	}
	else
		return 0;
}

void CTipoDocumentoItr::Add(    
								char* cidTipoDocumento,
								char* csgTipoDocumento,
								char* cdsTipoDocumento,
								char* cidTipoPessoa,
								char* cnrPrioridade
						   )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrTipoDocumento = (struct STTipoDocumentoRegistro*) 
                realloc( stcrTipoDocumento, sizeof(STTipoDocumentoRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	// stcrTipoDocumento = (struct STTipoDocumentoRegistro*) realloc( stcrTipoDocumento, sizeof(STTipoDocumentoRegistro)*_iQuantidade );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cidTipoDocumento, cidTipoDocumento );
	strcpy( stcrTipoDocumento[_iQuantidade-1].csgTipoDocumento, csgTipoDocumento );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cdsTipoDocumento, cdsTipoDocumento );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cnrPrioridade, cnrPrioridade );
}


void CTipoDocumentoItr::Add(    
								char* cidTipoDocumento,
								char* csgTipoDocumento,
								char* cdsTipoDocumento,
								char* cidTipoPessoa,
								char* cnrPrioridade,
								char* csgClassificacao
						   )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 50;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrTipoDocumento = (struct STTipoDocumentoRegistro*) 
                realloc( stcrTipoDocumento, sizeof(STTipoDocumentoRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrTipoDocumento = (struct STTipoDocumentoRegistro*) realloc( stcrTipoDocumento, sizeof(STTipoDocumentoRegistro)*_iQuantidade );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cidTipoDocumento, cidTipoDocumento );
	strcpy( stcrTipoDocumento[_iQuantidade-1].csgTipoDocumento, csgTipoDocumento );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cdsTipoDocumento, cdsTipoDocumento );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa );
	strcpy( stcrTipoDocumento[_iQuantidade-1].cnrPrioridade, cnrPrioridade );
	strcpy( stcrTipoDocumento[_iQuantidade-1].csgClassificacao, csgClassificacao );
}

void CTipoDocumentoItr::ZeraTipoDocumento( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoDocumento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoDocumento    = NULL;
}