#include <stdio.h>
#include "../include/CFlhCmpItr.h"

CContatoFolhaCampoItr::CContatoFolhaCampoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaCampo    = NULL;
}

CContatoFolhaCampoItr::~CContatoFolhaCampoItr()
{
	ZeraContatoFolhaCampo();
}

int CContatoFolhaCampoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CContatoFolhaCampoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CContatoFolhaCampoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CContatoFolhaCampoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CContatoFolhaCampoItr::Quantidade( void )
{
	return _iQuantidade;
}

STContatoFolhaCampoRegistro* CContatoFolhaCampoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrContatoFolhaCampo[ _iPosicao ];
	else
		return 0;
}

STContatoFolhaCampoRegistro* CContatoFolhaCampoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrContatoFolhaCampo[ nPosicao ];

	}
	else
		return 0;
}

void CContatoFolhaCampoItr::Add(
			char* cidContatoFolhaCampo,
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso )
{
	_iQuantidade++;
	stcrContatoFolhaCampo = (struct STContatoFolhaCampoRegistro*) realloc( stcrContatoFolhaCampo, sizeof(STContatoFolhaCampoRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidContatoFolhaCampo, cidContatoFolhaCampo);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].csqOrdemApresentacao, csqOrdemApresentacao);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidFaseProcesso, cidFaseProcesso);
	stcrContatoFolhaCampo[_iQuantidade-1].iInsertArray = 0;
}

void CContatoFolhaCampoItr::AddArray(
			char* cidContatoFolhaCampo,
			char* cidContato,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cidCampo,
			char* csqOrdemApresentacao,
			char* cidFaseProcesso )
{
	_iQuantidade++;
	stcrContatoFolhaCampo = (struct STContatoFolhaCampoRegistro*) realloc( stcrContatoFolhaCampo, sizeof(STContatoFolhaCampoRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidContatoFolhaCampo, cidContatoFolhaCampo);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidUFOperadora, cidUFOperadora);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidTipoLinha, cidTipoLinha);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidCampo, cidCampo);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].csqOrdemApresentacao, csqOrdemApresentacao);
	strcpy( stcrContatoFolhaCampo[_iQuantidade-1].cidFaseProcesso, cidFaseProcesso);
	stcrContatoFolhaCampo[_iQuantidade-1].iInsertArray = 1;
}

void CContatoFolhaCampoItr::ZeraContatoFolhaCampo( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolhaCampo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaCampo    = NULL;
}
