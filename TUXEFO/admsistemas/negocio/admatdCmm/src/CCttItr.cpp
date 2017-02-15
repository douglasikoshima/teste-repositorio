#include <stdio.h>
#include "../include/CCttItr.h"

CContatoItr::CContatoItr() : tamBlocoCCItr(100)
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContato    = NULL;
	memset( cErroGeral, 0, sizeof( cErroGeral ) );
}

CContatoItr::~CContatoItr()
{
	ZeraContato();
}

int CContatoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoRegistro* CContatoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContato[ _iPosicao ];
	else
		return 0;
}

STContatoRegistro* CContatoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContato[ nPosicao ];

	}
	else
		return 0;
}

void CContatoItr::Add(  char* cidContato,
						char* cidContatoPai,
						char* cidNomeContato,
						char* cnmContato,
						char* cinDisponibilidade,
			            char* cdsPath,
						int   iLevel,
						int  iFolha,
                        char* cdsTipoProcesso)
{
    // Só realoca quando preencher o bloco anterior, ou quando for a primeira vez--Cassio
    if ( (_iQuantidade % tamBlocoCCItr) == 0 )
    {
        int Resto = _iQuantidade / tamBlocoCCItr;
        int tamBlocoAtu = (Resto+1) * tamBlocoCCItr;

	    stcrContato = 
            (struct STContatoRegistro*) 
                realloc(stcrContato,sizeof(STContatoRegistro)*tamBlocoAtu);
    }

	strcpy(stcrContato[_iQuantidade].cidContato,cidContato);
	strcpy(stcrContato[_iQuantidade].cidContatoPai,cidContatoPai);
	strcpy(stcrContato[_iQuantidade].cidNomeContato,cidNomeContato);
	strcpy(stcrContato[_iQuantidade].cnmContato,cnmContato);
	strcpy(stcrContato[_iQuantidade].cinDisponibilidade,cinDisponibilidade);
	strcpy(stcrContato[_iQuantidade].cdsPath,cdsPath);
	strcpy(stcrContato[_iQuantidade].cdsTipoProcesso,cdsTipoProcesso);
	stcrContato[_iQuantidade].iLevel = iLevel;
	stcrContato[_iQuantidade].iFolha = iFolha;

	_iQuantidade++;
}

void CContatoItr::ZeraContato( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContato );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContato    = NULL;
}

int CContatoItr::BytesAlocados( void )
{
	return ( _iQuantidade * sizeof( STContatoRegistro ) );
}


void CContatoItr::SetErro( char* cErro )
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
