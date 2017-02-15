#include <stdio.h>
#include <CTipoMotivoCampanhaItr.h>

CTipoMotivoCampanhaItr::CTipoMotivoCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoMotivoCampanha    = NULL;
}

CTipoMotivoCampanhaItr::~CTipoMotivoCampanhaItr()
{
	ZeraTipoMotivoCampanha();
}

int CTipoMotivoCampanhaItr::Primeiro()
{
	_iPosicao = 0;
	return _iPosicao;
}

int CTipoMotivoCampanhaItr::Proximo()
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CTipoMotivoCampanhaItr::Anterior()
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CTipoMotivoCampanhaItr::Ultimo()
{
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
	return _iPosicao;
}

int CTipoMotivoCampanhaItr::Quantidade()
{
	return _iQuantidade;
}

STTipoMotivoCampanhaRegistro* CTipoMotivoCampanhaItr::Registro()
{
	if( _iQuantidade > 0 )
		return &stcrTipoMotivoCampanha[ _iPosicao ];
	else
		return 0;
}

STTipoMotivoCampanhaRegistro* CTipoMotivoCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
		return &stcrTipoMotivoCampanha[ nPosicao ];
	}
	else
		return 0;
}

void CTipoMotivoCampanhaItr::Add( TString cidTipoMotivoCampanha, 
								  TString csgTipoMotivoCampanha, 
								  TString cdsTipoMotivoCampanha, 
								  TString cidPessoaUsuarioInclusao,
								  TString cidPessoaUsuarioAlteracao,
								  TString cdtInclusao,
								  TString cdtAlteracao,
								  TString cinAtivo,
								  int Tipo)
{
	STTipoMotivoCampanhaRegistro* st = AddNew();
	st->cidTipoMotivoCampanha		= cidTipoMotivoCampanha;
	st->csgTipoMotivoCampanha		= csgTipoMotivoCampanha;
	st->cdsTipoMotivoCampanha		= cdsTipoMotivoCampanha;
	st->cidPessoaUsuarioInclusao	= cidPessoaUsuarioInclusao;
	st->cidPessoaUsuarioAlteracao	= cidPessoaUsuarioAlteracao;
	st->cdtInclusao					= cdtInclusao;
	st->cdtAlteracao				= cdtAlteracao;
	st->cinAtivo					= cinAtivo;
	st->tipo						= Tipo; 
}



void CTipoMotivoCampanhaItr::Add( TString cidTipoMotivoCampanha, TString csgTipoMotivoCampanha, TString cdsTipoMotivoCampanha, int Tipo)
{
	STTipoMotivoCampanhaRegistro* st = AddNew();
	st->cidTipoMotivoCampanha	= cidTipoMotivoCampanha;
	st->csgTipoMotivoCampanha	= csgTipoMotivoCampanha;
	st->cdsTipoMotivoCampanha	= cdsTipoMotivoCampanha;
	st->tipo = Tipo; 
}

void CTipoMotivoCampanhaItr::ZeraTipoMotivoCampanha()
{
	if( _iQuantidade > 0 )
	{
		free( stcrTipoMotivoCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrTipoMotivoCampanha    = NULL;
}

STTipoMotivoCampanhaRegistro* CTipoMotivoCampanhaItr::AddNew()
{
	_iQuantidade++;
	stcrTipoMotivoCampanha = (struct STTipoMotivoCampanhaRegistro*) 
		realloc( stcrTipoMotivoCampanha, sizeof(STTipoMotivoCampanhaRegistro)*_iQuantidade );

	return &stcrTipoMotivoCampanha[_iQuantidade-1];
}

