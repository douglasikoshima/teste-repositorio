#include <stdio.h>
#include "../include/CContatoGrupoItr.h"

CContatoGrupoItr::CContatoGrupoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoGrupo    = NULL;
}

CContatoGrupoItr::~CContatoGrupoItr()
{
	ZeraContatoGrupo();
}

int CContatoGrupoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoGrupoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoGrupoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoGrupoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoGrupoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoGrupoRegistro* CContatoGrupoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoGrupo[ _iPosicao ];
	else
		return 0;
}

STContatoGrupoRegistro* CContatoGrupoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoGrupo[ nPosicao ];

	}
	else
		return 0;
}

void CContatoGrupoItr::Add(
								char* cidContatoGrupo,
								char* cidContato,
								char* cidGrupo
							  )
{
	_iQuantidade++;
	stcrContatoGrupo = (struct STContatoGrupoRegistro*) realloc( stcrContatoGrupo, sizeof(STContatoGrupoRegistro)*_iQuantidade );
	strcpy( stcrContatoGrupo[_iQuantidade-1].cidContatoGrupo, cidContatoGrupo);
	strcpy( stcrContatoGrupo[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoGrupo[_iQuantidade-1].cidGrupo, cidGrupo);
}


void CContatoGrupoItr::ZeraContatoGrupo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoGrupo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoGrupo    = NULL;
}
