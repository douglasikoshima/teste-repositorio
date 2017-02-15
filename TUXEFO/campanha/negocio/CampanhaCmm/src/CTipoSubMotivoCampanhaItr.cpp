#include <stdio.h>
#include <CTipoSubMotivoCampanhaItr.h>

CTipoSubMotivoCampanhaItr::CTipoSubMotivoCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoSubMotivoCampanha    = NULL;
}

CTipoSubMotivoCampanhaItr::~CTipoSubMotivoCampanhaItr()
{
	ZeraTipoSubMotivoCampanha();
}

int CTipoSubMotivoCampanhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoSubMotivoCampanhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoSubMotivoCampanhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoSubMotivoCampanhaItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CTipoSubMotivoCampanhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STTipoSubMotivoCampanhaRegistro* CTipoSubMotivoCampanhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrTipoSubMotivoCampanha[ _iPosicao ];
	else
		return 0;
}

STTipoSubMotivoCampanhaRegistro* CTipoSubMotivoCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrTipoSubMotivoCampanha[ nPosicao ];
		
	}
	else
		return 0;
}

void CTipoSubMotivoCampanhaItr::Add( char* cidTipoSubMotivoCampanha, 
							         char* csgTipoSubMotivoCampanha, 
								     char* cdsTipoSubMotivoCampanha, 
								     char* cinDisponibilidade, 
								     char* cidPessoaUsuarioInclusao,
								     char* cidPessoaUsuarioAlteracao,
								     char* cdtInclusao,
								     char* cdtAlteracao,
									 char* cinAtivo )
{
	_iQuantidade++;
	stcrTipoSubMotivoCampanha = (struct STTipoSubMotivoCampanhaRegistro*) realloc( stcrTipoSubMotivoCampanha, sizeof(STTipoSubMotivoCampanhaRegistro)*_iQuantidade );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cidTipoSubMotivoCampanha, cidTipoSubMotivoCampanha );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].csgTipoSubMotivoCampanha, csgTipoSubMotivoCampanha );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cdsTipoSubMotivoCampanha, cdsTipoSubMotivoCampanha );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cidPessoaUsuarioInclusao, cidPessoaUsuarioInclusao );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cidPessoaUsuarioAlteracao , cidPessoaUsuarioAlteracao );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cdtInclusao, cdtInclusao );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cdtAlteracao   , cdtAlteracao );
	strcpy( stcrTipoSubMotivoCampanha[_iQuantidade-1].cinAtivo, cinAtivo );
}


void CTipoSubMotivoCampanhaItr::ZeraTipoSubMotivoCampanha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoSubMotivoCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoSubMotivoCampanha    = NULL;
}