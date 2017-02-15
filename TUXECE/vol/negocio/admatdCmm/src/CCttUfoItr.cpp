#include <stdio.h>
#include "../include/CCttUfoItr.h"

CContatoUfoperadoraItr::CContatoUfoperadoraItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoUfoperadora    = NULL;
}

CContatoUfoperadoraItr::~CContatoUfoperadoraItr()
{
	ZeraContatoUfoperadora();
}

int CContatoUfoperadoraItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoUfoperadoraItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoUfoperadoraItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoUfoperadoraItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoUfoperadoraItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoUfoperadoraRegistro* CContatoUfoperadoraItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoUfoperadora[ _iPosicao ];
	else
		return 0;
}

STContatoUfoperadoraRegistro* CContatoUfoperadoraItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoUfoperadora[ nPosicao ];

	}
	else
		return 0;
}

void CContatoUfoperadoraItr::Add(
			char* cidContatoUFOperadora,
			char* cidContato,
			char* cidUFOperadora,
			char* cdsUFOperadora,
			char* cdtInicioVigencia,
			char* cdtFimVigencia,
			char* cinDisponibilidade,
			char* csgUF )
{
	_iQuantidade++;
	stcrContatoUfoperadora = (struct STContatoUfoperadoraRegistro*) realloc( stcrContatoUfoperadora, sizeof(STContatoUfoperadoraRegistro)*_iQuantidade );
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cidContatoUFOperadora, cidContatoUFOperadora);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cdtInicioVigencia, cdtInicioVigencia);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cdtFimVigencia, cdtFimVigencia);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade);
	strcpy( stcrContatoUfoperadora[_iQuantidade-1].csgUF, csgUF);
}


void CContatoUfoperadoraItr::ZeraContatoUfoperadora( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoUfoperadora );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoUfoperadora    = NULL;
}
