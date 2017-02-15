#include <stdio.h>
#include "../include/CCtpaItr.h"

CCTipoArvoreItr::CCTipoArvoreItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTpArvore    = NULL;
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
}

CCTipoArvoreItr::~CCTipoArvoreItr()
{
	Zera();
}

int CCTipoArvoreItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCTipoArvoreItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCTipoArvoreItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCTipoArvoreItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
    {
		_iPosicao = _iQuantidade-1;
    }
	else
    {
		_iPosicao = 0;
    }

	return _iPosicao;
}

int CCTipoArvoreItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoArvoreRegistro* CCTipoArvoreItr::Registro( void )
{
	if( _iQuantidade > 0 )
    {
		return &stcrTpArvore[ _iPosicao ];
    }

	return 0;
}

STTipoArvoreRegistro* CCTipoArvoreItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
        {
			nPosicao = Ultimo();
        }

		return &stcrTpArvore[ nPosicao ];
	}

	return 0;
}

void CCTipoArvoreItr::Add(char* cidTipoArvore,char* cdsTipoArvore)
{
	_iQuantidade++;

	stcrTpArvore = 
            (struct STTipoArvoreRegistro*) 
                realloc( stcrTpArvore, sizeof(STTipoArvoreRegistro)*_iQuantidade );

	strcpy( stcrTpArvore[_iQuantidade-1].cidTipoArvore, cidTipoArvore);
	strcpy( stcrTpArvore[_iQuantidade-1].cdsTipoArvore, cdsTipoArvore);
}

void CCTipoArvoreItr::Zera( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTpArvore );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTpArvore    = NULL;
}

int CCTipoArvoreItr::BytesAlocados( void )
{
	return ( _iQuantidade * sizeof( STTipoArvoreRegistro ) );
}

void CCTipoArvoreItr::SetErro( char* cErro )
{
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
	if( strlen( cErro ) >= 255 )
	{
		strncpy( cErroGeral, &cErro[0], 255 );
		cErro[255] = 0;
	}
	else
    {
		strcpy( cErroGeral, cErro );
    }
}
