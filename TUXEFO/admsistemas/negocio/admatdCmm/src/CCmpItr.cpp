#include <stdio.h>
#include "../include/CCmpItr.h"

CCampoItr::CCampoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampo    = NULL;
}

CCampoItr::~CCampoItr()
{
	ZeraCampo();
}

int CCampoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCampoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCampoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCampoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCampoItr::Quantidade( void )
{
	return _iQuantidade;
}

STCampoRegistro* CCampoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCampo[ _iPosicao ];
	else
		return 0;
}

STCampoRegistro* CCampoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCampo[ nPosicao ];

	}
	else
		return 0;
}

void CCampoItr::Add(
			char* cidCampo,
			char* cnmCampo,
			char* cidTipoDadoCampo,
			char* cidMascaraApresentacaoCampo,
			char* cidLayoutApresentacaoCampo,
			char* cnrTamanho,
			char* cinDisponibilidade,
			char* cidClassificadorCampo,
			char* cnmClassificadorCampo,
			char* cinFiltro )
{
	_iQuantidade++;
	stcrCampo = (struct STCampoRegistro*) realloc( stcrCampo, sizeof(STCampoRegistro)*_iQuantidade );
	strcpy( stcrCampo[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmCampo, cnmCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidTipoDadoCampo, cidTipoDadoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidMascaraApresentacaoCampo, cidMascaraApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidLayoutApresentacaoCampo, cidLayoutApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnrTamanho, cnrTamanho);
	strcpy( stcrCampo[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrCampo[_iQuantidade-1].cidClassificadorCampo, cidClassificadorCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmClassificadorCampo, cnmClassificadorCampo);
	strcpy( stcrCampo[_iQuantidade-1].cinFiltro, cinFiltro);
}

void CCampoItr::Add(
			char* cidCampo,
			char* cnmCampo,
			char* cidTipoDadoCampo,
			char* cidMascaraApresentacaoCampo,
			char* cidLayoutApresentacaoCampo,
			char* cnrTamanho,
			char* cinDisponibilidade,
			char* cidClassificadorCampo,
			char* cnmClassificadorCampo,
			char* cinFiltro,
		    char* cinPesquisa,
		    char* cinObrigatorio
	 )
{
	_iQuantidade++;
	stcrCampo = (struct STCampoRegistro*) realloc( stcrCampo, sizeof(STCampoRegistro)*_iQuantidade );
	strcpy( stcrCampo[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmCampo, cnmCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidTipoDadoCampo, cidTipoDadoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidMascaraApresentacaoCampo, cidMascaraApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidLayoutApresentacaoCampo, cidLayoutApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnrTamanho, cnrTamanho);
	strcpy( stcrCampo[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrCampo[_iQuantidade-1].cidClassificadorCampo, cidClassificadorCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmClassificadorCampo, cnmClassificadorCampo);
	strcpy( stcrCampo[_iQuantidade-1].cinFiltro, cinFiltro);
	strcpy( stcrCampo[_iQuantidade-1].cinPesquisa, cinPesquisa);
	strcpy( stcrCampo[_iQuantidade-1].cinObrigatorio, cinObrigatorio);
}

void CCampoItr::Add(
		char* cidCampo,
		char* cnmCampo,
		char* cidTipoDadoCampo,
		char* csgTipoDadoCampo,
		char* cnmTipoDadoCampo,
		char* cinDominio,
		char* cidMascaraApresentacaoCampo,
		char* cnmMascaraApresentacaoCampo,
		char* csgMascaraApresentacaoCampo,
		char* cidLayoutApresentacaoCampo,
		char* cnmLayoutApresentacaoCampo,
		char* csgLayoutApresentacaoCampo,
		char* cnrTamanho,
		char* cinDisponibilidade,
		char* cidClassificadorCampo,
		char* cnmClassificadorCampo,
		char* cinFiltro,
		char* cinPesquisa,
		char* cinObrigatorio,
		char* ctemDominio
	)
{
	_iQuantidade++;
	stcrCampo = (struct STCampoRegistro*) realloc( stcrCampo, sizeof(STCampoRegistro)*_iQuantidade );
	strcpy( stcrCampo[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmCampo, cnmCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidTipoDadoCampo, cidTipoDadoCampo);
	strcpy( stcrCampo[_iQuantidade-1].csgTipoDadoCampo, csgTipoDadoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmTipoDadoCampo, cnmTipoDadoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cinDominio, cinDominio);
	strcpy( stcrCampo[_iQuantidade-1].cidMascaraApresentacaoCampo, cidMascaraApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmMascaraApresentacaoCampo, cnmMascaraApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].csgMascaraApresentacaoCampo, csgMascaraApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cidLayoutApresentacaoCampo, cidLayoutApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmLayoutApresentacaoCampo, cnmLayoutApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].csgLayoutApresentacaoCampo, csgLayoutApresentacaoCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnrTamanho, cnrTamanho);
	strcpy( stcrCampo[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrCampo[_iQuantidade-1].cidClassificadorCampo, cidClassificadorCampo);
	strcpy( stcrCampo[_iQuantidade-1].cnmClassificadorCampo, cnmClassificadorCampo);
	strcpy( stcrCampo[_iQuantidade-1].cinFiltro, cinFiltro);
	strcpy( stcrCampo[_iQuantidade-1].cinPesquisa, cinPesquisa);
	strcpy( stcrCampo[_iQuantidade-1].cinObrigatorio, cinObrigatorio);
	strcpy( stcrCampo[_iQuantidade-1].ctemDominio, ctemDominio);
}

void CCampoItr::ZeraCampo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCampo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampo    = NULL;
}
