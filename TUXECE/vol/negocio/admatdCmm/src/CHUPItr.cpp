#include <stdio.h>
#include "../include/CHUPItr.h"

CHierarquiaDeptoPessoaItr::CHierarquiaDeptoPessoaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrHierarquiaDeptoPessoa    = NULL;
}

CHierarquiaDeptoPessoaItr::~CHierarquiaDeptoPessoaItr()
{
	ZeraHierarquiaDeptoPessoa();
}

int CHierarquiaDeptoPessoaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CHierarquiaDeptoPessoaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CHierarquiaDeptoPessoaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CHierarquiaDeptoPessoaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CHierarquiaDeptoPessoaItr::Quantidade( void )
{
	return _iQuantidade;
}

STHierarquiaDeptoPessoaRegistro* CHierarquiaDeptoPessoaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrHierarquiaDeptoPessoa[ _iPosicao ];
	else
		return 0;
}

STHierarquiaDeptoPessoaRegistro* CHierarquiaDeptoPessoaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrHierarquiaDeptoPessoa[ nPosicao ];

	}
	else
		return 0;
}

void CHierarquiaDeptoPessoaItr::Add(
			char* cidHierarquiaDeptoPessoa,
			char* cidNivelCargo,
			char* cidPessoa,
			char* cidOrganizacaoDepartamento )
{
	_iQuantidade++;
	stcrHierarquiaDeptoPessoa = (struct STHierarquiaDeptoPessoaRegistro*) realloc( stcrHierarquiaDeptoPessoa, sizeof(STHierarquiaDeptoPessoaRegistro)*_iQuantidade );
	strcpy( stcrHierarquiaDeptoPessoa[_iQuantidade-1].cidHierarquiaDeptoPessoa, cidHierarquiaDeptoPessoa);
	strcpy( stcrHierarquiaDeptoPessoa[_iQuantidade-1].cidNivelCargo, cidNivelCargo);
	strcpy( stcrHierarquiaDeptoPessoa[_iQuantidade-1].cidPessoa, cidPessoa);
	strcpy( stcrHierarquiaDeptoPessoa[_iQuantidade-1].cidOrganizacaoDepartamento, cidOrganizacaoDepartamento);
}


void CHierarquiaDeptoPessoaItr::ZeraHierarquiaDeptoPessoa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrHierarquiaDeptoPessoa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrHierarquiaDeptoPessoa    = NULL;
}
