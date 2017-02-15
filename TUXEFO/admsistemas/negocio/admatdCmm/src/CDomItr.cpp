#include <stdio.h>
#include "../include/CDomItr.h"

CDominioItr::CDominioItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrDominio    = NULL;
}

CDominioItr::~CDominioItr()
{
	ZeraDominio();
}

int CDominioItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CDominioItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CDominioItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CDominioItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CDominioItr::Quantidade( void )
{
	return _iQuantidade;
}

STDominioRegistro* CDominioItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrDominio[ _iPosicao ];
	else
		return 0;
}

STDominioRegistro* CDominioItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrDominio[ nPosicao ];

	}
	else
		return 0;
}

void CDominioItr::Add( STDominioRegistro* reg)
{
	_iQuantidade++;
	stcrDominio = (struct STDominioRegistro*) realloc( stcrDominio, sizeof(STDominioRegistro)*_iQuantidade );
	//memcpy( &stcrDominio[_iQuantidade-1],reg,sizeof(STDominioRegistro));
	strcpy( stcrDominio[_iQuantidade-1].cidDominio, reg->cidDominio);
	strcpy( stcrDominio[_iQuantidade-1].cnmDominio, reg->cnmDominio);
	strcpy( stcrDominio[_iQuantidade-1].cinDisponibilidade, reg->cinDisponibilidade);
	strcpy( stcrDominio[_iQuantidade-1].cidTabelaDominio, reg->cidTabelaDominio);
	strcpy( stcrDominio[_iQuantidade-1].cnmTabelaDominio, reg->cnmTabelaDominio);
	strcpy( stcrDominio[_iQuantidade-1].cidUFOperadora, reg->cidUFOperadora);
	strcpy( stcrDominio[_iQuantidade-1].cidTipoLinha, reg->cidTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].csgTipoLinha, reg->csgTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].cdsTipoLinha, reg->cdsTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].cdsUFOperadora, reg->cdsUFOperadora);
}


void CDominioItr::Add(
			char* cidDominio,
			char* cidTabelaDominio,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cnmDominio,
			char* cinDisponibilidade )
{
	_iQuantidade++;
	stcrDominio = (struct STDominioRegistro*) realloc( stcrDominio, sizeof(STDominioRegistro)*_iQuantidade );
	strcpy( stcrDominio[_iQuantidade-1].cidDominio, cidDominio);
	strcpy( stcrDominio[_iQuantidade-1].cidTabelaDominio, cidTabelaDominio);
	strcpy( stcrDominio[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrDominio[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].cnmDominio, cnmDominio);
	strcpy( stcrDominio[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
}

void CDominioItr::Add(
		char* cidDominio,
		char* cnmDominio,
		char* cinDisponibilidade,
		char* cidTabelaDominio,
		char* cnmTabelaDominio,
		char* cidUFOperadora,
		char* cdsUFOperadora,
		char* cidTipoLinha,
		char* csgTipoLinha,
		char* cdsTipoLinha)
{
	_iQuantidade++;
	stcrDominio = (struct STDominioRegistro*) realloc( stcrDominio, sizeof(STDominioRegistro)*_iQuantidade );
	strcpy( stcrDominio[_iQuantidade-1].cidDominio, cidDominio);
	strcpy( stcrDominio[_iQuantidade-1].cnmDominio, cnmDominio);
	strcpy( stcrDominio[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrDominio[_iQuantidade-1].cidTabelaDominio, cidTabelaDominio);
	strcpy( stcrDominio[_iQuantidade-1].cnmTabelaDominio, cnmTabelaDominio);
	strcpy( stcrDominio[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrDominio[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].csgTipoLinha, csgTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].cdsTipoLinha, cdsTipoLinha);
	strcpy( stcrDominio[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);

}

void CDominioItr::ZeraDominio( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrDominio );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrDominio    = NULL;
}
