#include <stdio.h>
#include <CSexoItr.h>

CSexoItr::CSexoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSexo    = NULL;
}

CSexoItr::~CSexoItr()
{
	ZeraSexo();
}

int CSexoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSexoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSexoItr::Quantidade( void )
{
	return _iQuantidade;
}

STSexoRegistro* CSexoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSexo[ _iPosicao ];
	else
		return 0;
}

STSexoRegistro* CSexoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSexo[ nPosicao ];

	}
	else
		return 0;
}

void CSexoItr::Add( 
			char* cidSexo,
			char* csgSexo,
			char* cdsSexo )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 2;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrSexo = (struct STSexoRegistro*) 
                realloc( stcrSexo, sizeof(STSexoRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrSexo = (struct STSexoRegistro*) realloc( stcrSexo, sizeof(STSexoRegistro)*_iQuantidade );
	strcpy( stcrSexo[_iQuantidade-1].cidSexo, cidSexo);
	strcpy( stcrSexo[_iQuantidade-1].csgSexo, csgSexo);
	strcpy( stcrSexo[_iQuantidade-1].cdsSexo, cdsSexo);
}


void CSexoItr::ZeraSexo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSexo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSexo    = NULL;
}
