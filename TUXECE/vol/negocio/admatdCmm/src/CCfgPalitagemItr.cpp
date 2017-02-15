#include <stdio.h>
#include "../include/CCfgPalitagemItr.h"

CCfgPalitagemItr::CCfgPalitagemItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCfgPalitagem    = NULL;
}

CCfgPalitagemItr::~CCfgPalitagemItr()
{
	ZeraCfgPalitagem();
}

int CCfgPalitagemItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCfgPalitagemItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCfgPalitagemItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCfgPalitagemItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCfgPalitagemItr::Quantidade( void )
{
	return _iQuantidade;
}

STCfgPalitagemRegistro* CCfgPalitagemItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCfgPalitagem[ _iPosicao ];
	else
		return 0;
}

STCfgPalitagemRegistro* CCfgPalitagemItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCfgPalitagem[ nPosicao ];

	}
	else
		return 0;
}

void CCfgPalitagemItr::Add(
            char * cidSistemaOrigem ,
            char * csgServico ,
            char * cdsServico ,
            char * cidProcedencia,
            char * cdtUltimaAlteracao )
{
	_iQuantidade++;
	stcrCfgPalitagem = (struct STCfgPalitagemRegistro*) realloc( stcrCfgPalitagem, sizeof(STCfgPalitagemRegistro)*_iQuantidade );
	strcpy( stcrCfgPalitagem[_iQuantidade-1].cidSistemaOrigem, cidSistemaOrigem);
	strcpy( stcrCfgPalitagem[_iQuantidade-1].csgServico, csgServico);
	strcpy( stcrCfgPalitagem[_iQuantidade-1].cdsServico, cdsServico);
	strcpy( stcrCfgPalitagem[_iQuantidade-1].cidProcedencia, cidProcedencia);
	strcpy( stcrCfgPalitagem[_iQuantidade-1].cdtUltimaAlteracao, cdtUltimaAlteracao );
}


void CCfgPalitagemItr::ZeraCfgPalitagem( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCfgPalitagem );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCfgPalitagem    = NULL;
}
