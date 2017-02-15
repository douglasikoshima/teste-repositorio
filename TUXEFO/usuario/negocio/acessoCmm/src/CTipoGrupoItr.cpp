#include <stdio.h>
#include "CTipoGrupoItr.h"

CTipoGrupoItr::CTipoGrupoItr()
{
	//ULOG_START("CTipoGrupoItr::CTipoGrupoItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoGrupo    = NULL;
	//ULOG_END("CTipoGrupoItr::CTipoGrupoItr()");
}

CTipoGrupoItr::~CTipoGrupoItr()
{
	//ULOG_START("CTipoGrupoItr::~CTipoGrupoItr()");
	ZeraTipoGrupo();
	//ULOG_END("CTipoGrupoItr::~CTipoGrupoItr()");
}

int CTipoGrupoItr::Quantidade( void )
{
	//ULOG_START("CTipoGrupoItr::Quantidade()");
	//ULOG_END("CTipoGrupoItr::Quantidade()");
	return _iQuantidade;
}

STTipoGrupoRegistro* CTipoGrupoItr::Registro( void )
{
	//ULOG_START("CTipoGrupoItr::Registro()");
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CTipoGrupoItr::Registro()");
		return &stcrTipoGrupo[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CTipoGrupoItr::Registro()");
		return 0;
	}
}

STTipoGrupoRegistro* CTipoGrupoItr::Registro(int nPosicao)
{
	//ULOG_START("CTipoGrupoItr::Registro()");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = _iQuantidade-1;

		//ULOG_END("CTipoGrupoItr::Registro()");
		return &stcrTipoGrupo[ nPosicao ];

	}
	else
	{
		//ULOG_END("CTipoGrupoItr::Registro()");
		return 0;
	}
}

void CTipoGrupoItr::Add( 
					 	 char* cidTipoGrupo
					 	,char* csgTipoGrupo
					 	,char* cdsTipoGrupo
	  )
{
	//ULOG_START("CTipoGrupoItr::Add()");
	_iQuantidade++;
	stcrTipoGrupo = (struct STTipoGrupoRegistro*) realloc( stcrTipoGrupo, sizeof(STTipoGrupoRegistro)*_iQuantidade );
	strcpy( stcrTipoGrupo[_iQuantidade-1].cidTipoGrupo, cidTipoGrupo );
	strcpy( stcrTipoGrupo[_iQuantidade-1].csgTipoGrupo, csgTipoGrupo );
	strcpy( stcrTipoGrupo[_iQuantidade-1].cdsTipoGrupo, cdsTipoGrupo );
	//ULOG_END("CTipoGrupoItr::Add()");
}


void CTipoGrupoItr::ZeraTipoGrupo( void )
{
	//ULOG_START("CTipoGrupoItr::ZeraTipoGrupo()");
	if( _iQuantidade > 0 )
	{
		free( stcrTipoGrupo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoGrupo = NULL;
	//ULOG_END("CTipoGrupoItr::ZeraTipoGrupo()");
}