#include <stdio.h>
#include "../include/CBxaListarItr.h"

CBaixaItr::CBaixaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrBaixa    = NULL;
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
}

CBaixaItr::~CBaixaItr()
{
	ZeraBaixa();
}

int CBaixaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CBaixaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CBaixaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CBaixaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CBaixaItr::Quantidade( void )
{
	return _iQuantidade;
}

STBaixaRegistro* CBaixaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrBaixa[ _iPosicao ];

	return 0;
}

STBaixaRegistro* CBaixaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrBaixa[ nPosicao ];
	}

	return 0;
}

void CBaixaItr::Add( char* cidBaixa,
			         char* cidBaixaPai,
			         char* cidNomeBaixa,
					 char* cnmBaixa,
			         char* cdsPath,
					 int   iLevel )
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez
    // Junho, 2006 - Cassio
    if ( (_iQuantidade % tamBlocoBaixa) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoBaixa;
        int tamBlocoAtu = (Resto+1) * tamBlocoBaixa;

	    stcrBaixa = (struct STBaixaRegistro*) realloc( stcrBaixa, sizeof(STBaixaRegistro)*tamBlocoAtu );
    }

	strcpy( stcrBaixa[_iQuantidade].cidBaixa, cidBaixa);
	strcpy( stcrBaixa[_iQuantidade].cidBaixaPai, cidBaixaPai);
	strcpy( stcrBaixa[_iQuantidade].cidNomeBaixa, cidNomeBaixa);
	strcpy( stcrBaixa[_iQuantidade].cnmBaixa, cnmBaixa);
	strcpy( stcrBaixa[_iQuantidade].cdsPath, cdsPath);
	stcrBaixa[_iQuantidade].iLevel = iLevel;

	_iQuantidade++;
}


void CBaixaItr::ZeraBaixa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrBaixa );
	    stcrBaixa = NULL;
	}

	_iQuantidade = 0;
	_iPosicao    = 0;
}

void CBaixaItr::SetErro( char* cErro )
{
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
	if( strlen( cErro ) >= 255 )
	{
		strncpy( cErroGeral, &cErro[0], 255 );
		cErro[255] = 0;
	}
	else
		strcpy( cErroGeral, cErro );
}
