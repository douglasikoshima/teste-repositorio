#include <stdio.h>
#include <CRspPxmItr.h>

CRespostaProximaPerguntaItr::CRespostaProximaPerguntaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrRespostaProximaPergunta    = NULL;
}

CRespostaProximaPerguntaItr::~CRespostaProximaPerguntaItr()
{
	ZeraRespostaProximaPergunta();
}

int CRespostaProximaPerguntaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CRespostaProximaPerguntaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CRespostaProximaPerguntaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CRespostaProximaPerguntaItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CRespostaProximaPerguntaItr::Quantidade( void )
{
	return _iQuantidade;
}

STRespostaProximaPerguntaRegistro* CRespostaProximaPerguntaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrRespostaProximaPergunta[ _iPosicao ];
	else
		return 0;
}

STRespostaProximaPerguntaRegistro* CRespostaProximaPerguntaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrRespostaProximaPergunta[ nPosicao ];

	}
	else
		return 0;
}

void CRespostaProximaPerguntaItr::Add( 
			char* cidRespPxPerg,
			char* cinAtivo,
			char* cidResposta,
			char* cidPergunta )
{
	_iQuantidade++;
	stcrRespostaProximaPergunta = (struct STRespostaProximaPerguntaRegistro*) realloc( stcrRespostaProximaPergunta, sizeof(STRespostaProximaPerguntaRegistro)*_iQuantidade );
	strcpy( stcrRespostaProximaPergunta[_iQuantidade-1].cidRespPxPerg, cidRespPxPerg);
	strcpy( stcrRespostaProximaPergunta[_iQuantidade-1].cinAtivo, cinAtivo);
	strcpy( stcrRespostaProximaPergunta[_iQuantidade-1].cidResposta, cidResposta);
	strcpy( stcrRespostaProximaPergunta[_iQuantidade-1].cidPergunta, cidPergunta);
}


void CRespostaProximaPerguntaItr::ZeraRespostaProximaPergunta( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrRespostaProximaPergunta );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrRespostaProximaPergunta    = NULL;
}
