#include <stdio.h>
#include "../include/CCttInfItr.h"

CContatoInformacaoItr::CContatoInformacaoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoInformacao    = NULL;
}

CContatoInformacaoItr::~CContatoInformacaoItr()
{
	ZeraContatoInformacao();
}

int CContatoInformacaoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoInformacaoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoInformacaoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoInformacaoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoInformacaoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoInformacaoRegistro* CContatoInformacaoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoInformacao[ _iPosicao ];
	else
		return 0;
}

STContatoInformacaoRegistro* CContatoInformacaoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoInformacao[ nPosicao ];

	}
	else
		return 0;
}

void CContatoInformacaoItr::Add( char* cidContatoInformacao
								,char* cidContato
								,char* cidUFOperadora
								,char* cdsUFOperadora
								,char* cidTipoLinha
								,char* cdsTipoLinha
								,char* cidTipoCliente
								,char* cdsTipoCliente
								,char* cnmURLContatoInformacao )
{
	_iQuantidade++;
	stcrContatoInformacao = (struct STContatoInformacaoRegistro*) realloc( stcrContatoInformacao, sizeof(STContatoInformacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContatoInformacao, cidContatoInformacao);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoLinha, cdsTipoLinha);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoCliente, cidTipoCliente);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoCliente, cdsTipoCliente);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cnmURLContatoInformacao, cnmURLContatoInformacao);
}

void CContatoInformacaoItr::AddUFOperadora( char* cidContato
					                       ,char* cidUFOperadora
					                       ,char* cdsUFOperadora )
{
	_iQuantidade++;
	stcrContatoInformacao = (struct STContatoInformacaoRegistro*) realloc( stcrContatoInformacao, sizeof(STContatoInformacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsUFOperadora, cdsUFOperadora);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContatoInformacao, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoLinha, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoLinha, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoCliente, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoCliente, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cnmURLContatoInformacao, "");

}

void CContatoInformacaoItr::AddTipoLinha( char* cidContato
				                         ,char* cidTipoLinha
				                         ,char* cdsTipoLinha )
{
	_iQuantidade++;
	stcrContatoInformacao = (struct STContatoInformacaoRegistro*) realloc( stcrContatoInformacao, sizeof(STContatoInformacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoLinha, cdsTipoLinha);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContatoInformacao, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidUFOperadora, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsUFOperadora, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoCliente, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoCliente, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cnmURLContatoInformacao, "");
}

void CContatoInformacaoItr::AddTipoCliente( char* cidContato
					                       ,char* cidTipoCliente
					                       ,char* cdsTipoCliente )
{
	_iQuantidade++;
	stcrContatoInformacao = (struct STContatoInformacaoRegistro*) realloc( stcrContatoInformacao, sizeof(STContatoInformacaoRegistro)*_iQuantidade );
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoCliente, cidTipoCliente);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoCliente, cdsTipoCliente);
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidContatoInformacao, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidUFOperadora, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsUFOperadora, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cidTipoLinha, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cdsTipoLinha, "");
	strcpy( stcrContatoInformacao[_iQuantidade-1].cnmURLContatoInformacao, "");
}

void CContatoInformacaoItr::ZeraContatoInformacao( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoInformacao );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoInformacao    = NULL;
}
