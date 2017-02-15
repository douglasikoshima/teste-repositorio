#include <stdio.h>
#include <CPaisItr.h>

CPaisItr::CPaisItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPais    = NULL;
}

CPaisItr::~CPaisItr()
{
	ZeraPais();
}

int CPaisItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPaisItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPaisItr::Quantidade( void )
{
	return _iQuantidade;
}

STPaisRegistro* CPaisItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPais[ _iPosicao ];
	else
		return 0;
}

STPaisRegistro* CPaisItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPais[ nPosicao ];
		
	}
	else
		return 0;
}

void CPaisItr::Add( char* cid, char* csigla, char* cdescricao, char* cnacionalidade )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 500;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrPais = (struct STPaisRegistro*) 
                realloc( stcrPais, sizeof(STPaisRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrPais = (struct STPaisRegistro*) realloc( stcrPais, sizeof(STPaisRegistro)*_iQuantidade );
	strcpy( stcrPais[_iQuantidade-1].cidPais, cid );
	strcpy( stcrPais[_iQuantidade-1].csgPais, csigla );
	strcpy( stcrPais[_iQuantidade-1].cdsPais, cdescricao );
	strcpy( stcrPais[_iQuantidade-1].cdsNacionalidade, cnacionalidade );
}


void CPaisItr::ZeraPais( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPais );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPais    = NULL;
}