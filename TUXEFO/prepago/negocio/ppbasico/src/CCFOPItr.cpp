#include <stdio.h>
#include <CFOPItr.h>

CCFOPItr::CCFOPItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCFOP     = NULL;
}

CCFOPItr::~CCFOPItr()
{
	ZeraCFOP();
}

int CCFOPItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCFOPItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCFOPItr::Quantidade( void )
{
	return _iQuantidade;
}

STCFOPRegistro* CCFOPItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCFOP[ _iPosicao ];
	else
		return 0;
}

STCFOPRegistro* CCFOPItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCFOP[ nPosicao ];
		
	}
	else
		return 0;
}

void CCFOPItr::Add( char* cidCFOP, char* cdsCFOP)
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 500;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrCFOP = (struct STCFOPRegistro*) 
                realloc( stcrCFOP, sizeof(STCFOPRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrCFOP = (struct STCFOPRegistro*) realloc( stcrCFOP, sizeof(STCFOPRegistro)*_iQuantidade );
	strcpy( stcrCFOP[_iQuantidade-1].cidCFOP, cidCFOP );
	strcpy( stcrCFOP[_iQuantidade-1].cdsCFOP, cdsCFOP );
}


void CCFOPItr::ZeraCFOP( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCFOP );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCFOP    = NULL;
}
