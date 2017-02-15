#include <stdio.h>
#include <CCnaeItr.h>

CCnaeItr::CCnaeItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCnae    = NULL;
}

CCnaeItr::~CCnaeItr()
{
	ZeraCnae();
}

int CCnaeItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCnaeItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCnaeItr::Quantidade( void )
{
	return _iQuantidade;
}

void CCnaeItr::ZeraCnae( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCnae );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCnae    = NULL;
}

void CCnaeItr::Add(    
					char* szIdCnae,
					char* szCdCnae,
					char* szDsCnae,
					char* szCdCfop
                  )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 500;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrCnae = (struct STCnaeRegistro*) 
                realloc( stcrCnae, sizeof(STCnaeRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrCnae = (struct STCnaeRegistro*) realloc( stcrCnae, sizeof(STCnaeRegistro)*_iQuantidade );
	strcpy( stcrCnae[_iQuantidade-1].szIdCnae, szIdCnae );
	strcpy( stcrCnae[_iQuantidade-1].szCdCnae, szCdCnae );
	strcpy( stcrCnae[_iQuantidade-1].szDsCnae, szDsCnae );
	strcpy( stcrCnae[_iQuantidade-1].szCdCfop, szCdCfop );
}

STCnaeRegistro* CCnaeItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCnae[ nPosicao ];
		
	}
	else
		return 0;
}
