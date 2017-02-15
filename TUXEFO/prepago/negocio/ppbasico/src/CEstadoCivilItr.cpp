#include <stdio.h>
#include <CEstadoCivilItr.h>

CEstadoCivilItr::CEstadoCivilItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrEstadoCivil    = NULL;
}

CEstadoCivilItr::~CEstadoCivilItr()
{
	ZeraEstadoCivil();
}

int CEstadoCivilItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CEstadoCivilItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CEstadoCivilItr::Quantidade( void )
{
	return _iQuantidade;
}

STEstadoCivilRegistro* CEstadoCivilItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrEstadoCivil[ _iPosicao ];
	else
		return 0;
}

STEstadoCivilRegistro* CEstadoCivilItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrEstadoCivil[ nPosicao ];

	}
	else
		return 0;
}

void CEstadoCivilItr::Add( 
			char* cidEstadoCivil,
			char* csgEstadoCivil,
			char* cdsEstadoCivil )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Maio, 2007 - Cassio
    int tamBlocoGrupo = 5;
    if ( (_iQuantidade % tamBlocoGrupo) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoGrupo;
        int tamBlocoAtu = (Resto+1) * tamBlocoGrupo;

	    stcrEstadoCivil = (struct STEstadoCivilRegistro*) 
                realloc( stcrEstadoCivil, sizeof(STEstadoCivilRegistro)*tamBlocoAtu );
    }
	_iQuantidade++;
	//stcrEstadoCivil = (struct STEstadoCivilRegistro*) realloc( stcrEstadoCivil, sizeof(STEstadoCivilRegistro)*_iQuantidade );
	strcpy( stcrEstadoCivil[_iQuantidade-1].cidEstadoCivil, cidEstadoCivil);
	strcpy( stcrEstadoCivil[_iQuantidade-1].csgEstadoCivil, csgEstadoCivil);
	strcpy( stcrEstadoCivil[_iQuantidade-1].cdsEstadoCivil, cdsEstadoCivil);
}


void CEstadoCivilItr::ZeraEstadoCivil( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrEstadoCivil );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrEstadoCivil    = NULL;
}
