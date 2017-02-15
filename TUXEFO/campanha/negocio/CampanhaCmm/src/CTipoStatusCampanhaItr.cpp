#include <stdio.h>
#include <CTipoStatusCampanhaItr.h>

CTipoStatusCampanhaItr::CTipoStatusCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoStatusCampanha    = NULL;
}

CTipoStatusCampanhaItr::~CTipoStatusCampanhaItr()
{
	ZeraTipoStatusCampanha();
}

int CTipoStatusCampanhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoStatusCampanhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoStatusCampanhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoStatusCampanhaItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoStatusCampanhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoStatusCampanhaRegistro* CTipoStatusCampanhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoStatusCampanha[ _iPosicao ];
	else
		return 0;
}

STTipoStatusCampanhaRegistro* CTipoStatusCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoStatusCampanha[ nPosicao ];
		
	}
	else
		return 0;
}

void CTipoStatusCampanhaItr::Add( char* cidTipoStatusCampanha, 
							        char* csgTipoStatusCampanha, 
								    char* cdsTipoStatusCampanha )
{
	_iQuantidade++;
	stcrTipoStatusCampanha = (struct STTipoStatusCampanhaRegistro*) realloc( stcrTipoStatusCampanha, sizeof(STTipoStatusCampanhaRegistro)*_iQuantidade );
	strcpy( stcrTipoStatusCampanha[_iQuantidade-1].cidTipoStatusCampanha, cidTipoStatusCampanha );
	strcpy( stcrTipoStatusCampanha[_iQuantidade-1].csgTipoStatusCampanha, csgTipoStatusCampanha );
	strcpy( stcrTipoStatusCampanha[_iQuantidade-1].cdsTipoStatusCampanha, cdsTipoStatusCampanha );
}


void CTipoStatusCampanhaItr::ZeraTipoStatusCampanha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoStatusCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoStatusCampanha    = NULL;
}