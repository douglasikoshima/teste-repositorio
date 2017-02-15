#include <stdio.h>
#include "../include/CResItr.h"

CRespostaItr::CRespostaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrResposta    = NULL;
}

CRespostaItr::~CRespostaItr()
{
	ZeraResposta();
}

int CRespostaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CRespostaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CRespostaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CRespostaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CRespostaItr::Quantidade( void )
{
	return _iQuantidade;
}

STRespostaRegistro* CRespostaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrResposta[ _iPosicao ];
	else
		return 0;
}

STRespostaRegistro* CRespostaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrResposta[ nPosicao ];

	}
	else
		return 0;
}

void CRespostaItr::Add( char* cidResposta,
				        char* cidPergunta,
					    char* cdsResposta,
					    char* cdsScriptResposta,
					    char* csqApresentacao,
					    char* cinEncerramento,
					    char* cinDisponibilidade,
					    char* cidPerguntaSalto,
					    char* cinAtivo )
{
	_iQuantidade++;
	stcrResposta = (struct STRespostaRegistro*) realloc( stcrResposta, sizeof(STRespostaRegistro)*_iQuantidade );
	strcpy( stcrResposta[_iQuantidade-1].cidResposta, cidResposta );
	strcpy( stcrResposta[_iQuantidade-1].cidPergunta, cidPergunta );
	strcpy( stcrResposta[_iQuantidade-1].cdsResposta, cdsResposta );
	strcpy( stcrResposta[_iQuantidade-1].cdsScriptResposta, cdsScriptResposta );
	strcpy( stcrResposta[_iQuantidade-1].csqApresentacao, csqApresentacao );
	strcpy( stcrResposta[_iQuantidade-1].cinEncerramento , cinEncerramento );
	strcpy( stcrResposta[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
	strcpy( stcrResposta[_iQuantidade-1].cidPerguntaSalto, cidPerguntaSalto );
	strcpy( stcrResposta[_iQuantidade-1].cinAtivo, cinAtivo );
}

void CRespostaItr::Add( char* cidResposta,
				        int   isqApresentacao )
{
	_iQuantidade++;
	stcrResposta = (struct STRespostaRegistro*) realloc( stcrResposta, sizeof(STRespostaRegistro)*_iQuantidade );
	strcpy( stcrResposta[_iQuantidade-1].cidResposta, cidResposta );
	stcrResposta[_iQuantidade-1].isqApresentacao = isqApresentacao;
}

void CRespostaItr::ZeraResposta( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrResposta );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrResposta    = NULL;
}