#include <stdio.h>
#include "../include/CContatoGrupoRCItr.h"

CContatoGrupoRCItr::CContatoGrupoRCItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoGrupoRC    = NULL;
}

CContatoGrupoRCItr::~CContatoGrupoRCItr()
{
	ZeraContatoGrupoRC();
}

int CContatoGrupoRCItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoGrupoRCRegistro* CContatoGrupoRCItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoGrupoRC[ _iPosicao ];
	else
		return 0;
}

STContatoGrupoRCRegistro* CContatoGrupoRCItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = _iQuantidade-1;

		return &stcrContatoGrupoRC[ nPosicao ];

	}
	else
		return 0;
}

void CContatoGrupoRCItr::Add(
					 	 char* cidContatoGrupoRC
					 	,char* cidContato
					 	,char* cidGrupo
	  )
{
	_iQuantidade++;
	stcrContatoGrupoRC = (struct STContatoGrupoRCRegistro*) realloc( stcrContatoGrupoRC, sizeof(STContatoGrupoRCRegistro)*_iQuantidade );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cidContatoGrupoRC, cidContatoGrupoRC );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cidContato, cidContato );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cidGrupo, cidGrupo );
}

// carrega o grupo
void CContatoGrupoRCItr::Add(
					 	 char* cidContatoGrupoRC
					 	,char* cidContato
					 	,char* cidGrupo
                        ,char* cdsGrupo
	  )
{
	_iQuantidade++;
	stcrContatoGrupoRC = (struct STContatoGrupoRCRegistro*) realloc( stcrContatoGrupoRC, sizeof(STContatoGrupoRCRegistro)*_iQuantidade );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cidContatoGrupoRC, cidContatoGrupoRC );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cidContato, cidContato );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cidGrupo, cidGrupo );
	strcpy( stcrContatoGrupoRC[_iQuantidade-1].cdsGrupo, cdsGrupo );
}



void CContatoGrupoRCItr::ZeraContatoGrupoRC( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoGrupoRC );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoGrupoRC = NULL;
}