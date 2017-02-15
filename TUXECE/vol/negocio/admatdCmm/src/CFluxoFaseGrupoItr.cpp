#include <stdio.h>
#include "../include/CFluxoFaseGrupoItr.h"

CFluxoFaseGrupoItr::CFluxoFaseGrupoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFluxoFaseGrupo    = NULL;
}

CFluxoFaseGrupoItr::~CFluxoFaseGrupoItr()
{
	ZeraFluxoFaseGrupo();
}

int CFluxoFaseGrupoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CFluxoFaseGrupoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CFluxoFaseGrupoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CFluxoFaseGrupoItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CFluxoFaseGrupoItr::Quantidade( void )
{
	return _iQuantidade;
}

STFluxoFaseGrupoRegistro* CFluxoFaseGrupoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrFluxoFaseGrupo[ _iPosicao ];
	else
		return 0;
}

STFluxoFaseGrupoRegistro* CFluxoFaseGrupoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrFluxoFaseGrupo[ nPosicao ];
		
	}
	else
		return 0;
}

void CFluxoFaseGrupoItr::Add( char* cidFluxoFaseGrupo, 
						      char* cidGrupo, 
							  char* cidContato, 
							  char* cidTipoLinha, 
							  char* cidTipoPessoa,
							  char* cidTipoRelacionamento,
							  char* cidTipoCarteira,
							  char* cidSegmentacao,
							  char* cidTipoFechamentoContato )
{
	_iQuantidade++;
	stcrFluxoFaseGrupo = (struct STFluxoFaseGrupoRegistro*) realloc( stcrFluxoFaseGrupo, sizeof(STFluxoFaseGrupoRegistro)*_iQuantidade );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidFluxoFaseGrupo, cidFluxoFaseGrupo );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidGrupo, cidGrupo );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidContato, cidContato );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidTipoLinha, cidTipoLinha );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidTipoPessoa, cidTipoPessoa );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidTipoRelacionamento , cidTipoRelacionamento );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidTipoCarteira, cidTipoCarteira );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidSegmentacao, cidSegmentacao );
	strcpy( stcrFluxoFaseGrupo[_iQuantidade-1].cidTipoFechamentoContato, cidTipoFechamentoContato );
}

void CFluxoFaseGrupoItr::ZeraFluxoFaseGrupo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrFluxoFaseGrupo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFluxoFaseGrupo    = NULL;
}