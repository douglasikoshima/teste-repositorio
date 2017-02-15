#include <stdio.h>
#include "../include/CPerItr.h"

CPerguntaItr::CPerguntaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPergunta    = NULL;
}

CPerguntaItr::~CPerguntaItr()
{
	ZeraPergunta();
}

int CPerguntaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPerguntaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPerguntaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPerguntaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPerguntaItr::Quantidade( void )
{
	return _iQuantidade;
}

STPerguntaRegistro* CPerguntaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPergunta[ _iPosicao ];
	else
		return 0;
}

STPerguntaRegistro* CPerguntaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPergunta[ nPosicao ];

	}
	else
		return 0;
}

void CPerguntaItr::Add( char* cidPesquisaSatisfacao,
					    char* cnmPesquisaSatisfacao,
					    char* cidPergunta,
				        char* cidTipoApresentacaoPergunta,
				        char* csgTipoApresentacaoPergunta,
				        char* cdsTipoApresentacaoPergunta,
					    char* cdsPergunta,
					    char* cdsScriptPergunta,
					    char* csqApresentacao,
					    char* cinEncerramento,
					    char* cinDisponibilidade,
					    char* cinObrigatoria )
{
	_iQuantidade++;
	stcrPergunta = (struct STPerguntaRegistro*) realloc( stcrPergunta, sizeof(STPerguntaRegistro)*_iQuantidade );
	stcrPergunta[_iQuantidade-1].pzResposta = NULL;
	strcpy( stcrPergunta[_iQuantidade-1].cidPesquisaSatisfacao, cidPesquisaSatisfacao );
	strcpy( stcrPergunta[_iQuantidade-1].cnmPesquisaSatisfacao, cnmPesquisaSatisfacao );
	strcpy( stcrPergunta[_iQuantidade-1].cidPergunta, cidPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cidTipoApresentacaoPergunta, cidTipoApresentacaoPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].csgTipoApresentacaoPergunta, csgTipoApresentacaoPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cdsTipoApresentacaoPergunta, cdsTipoApresentacaoPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cdsPergunta, cdsPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cdsScriptPergunta, cdsScriptPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].csqApresentacao, csqApresentacao );
	strcpy( stcrPergunta[_iQuantidade-1].cinEncerramento , cinEncerramento );
	strcpy( stcrPergunta[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
	strcpy( stcrPergunta[_iQuantidade-1].cinObrigatoria, cinObrigatoria );
}

void CPerguntaItr::Add( char* cidPergunta,
				        int   isqApresentacao )
{
	_iQuantidade++;
	stcrPergunta = (struct STPerguntaRegistro*) realloc( stcrPergunta, sizeof(STPerguntaRegistro)*_iQuantidade );
	stcrPergunta[_iQuantidade-1].pzResposta = NULL;
	strcpy( stcrPergunta[_iQuantidade-1].cidPergunta, cidPergunta );
	stcrPergunta[_iQuantidade-1].isqApresentacao = isqApresentacao;
}

void CPerguntaItr::AddReposta( int   iRegistro,
                               char* cidResposta,
				               char* cidPergunta,
					           char* cdsResposta,
					           char* cdsScriptResposta,
					           char* csqApresentacao,
					           char* cinEncerramento,
					           char* cinDisponibilidade,
					           char* cidPerguntaSalto,
					           char* cinAtivo )
{

	if( Quantidade() > 0 )
	{
		if( ( iRegistro <= Quantidade() ) && ( iRegistro > 0 ) )
		{
			if( stcrPergunta[iRegistro-1].pzResposta == NULL )
				stcrPergunta[iRegistro-1].pzResposta = new CResposta();

			stcrPergunta[iRegistro-1].pzResposta->Add( cidResposta,
					                                   cidPergunta,
					                                   cdsResposta,
					                                   cdsScriptResposta,
					                                   csqApresentacao,
					                                   cinEncerramento,
					                                   cinDisponibilidade,
					                                   cidPerguntaSalto,
					                                   cinAtivo );
		}
	}
}


void CPerguntaItr::ZeraPergunta( void )
{
	if( _iQuantidade > 0 )
	{
		for( int iCont = 0; iCont < _iQuantidade; iCont++ )
		{
			if( stcrPergunta[iCont].pzResposta != NULL )
				delete stcrPergunta[iCont].pzResposta;
		}
		free( stcrPergunta );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPergunta = NULL;
}