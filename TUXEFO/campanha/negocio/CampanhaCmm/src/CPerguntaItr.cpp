#include <stdio.h>
#include <CPerguntaItr.h>

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

void CPerguntaItr::Add( char* cidPergunta, 
				        char* cidTipoApresentacaoPergunta, 
					    char* cdsPergunta, 
					    char* cdsScriptPergunta, 
					    char* csqApresentacao,
					    char* cinEncerramento,
					    char* cinDisponibilidade,
					    char* cinObrigatoria )
{
	_iQuantidade++;
	stcrPergunta = (struct STPerguntaRegistro*) realloc( stcrPergunta, sizeof(STPerguntaRegistro)*_iQuantidade );
	strcpy( stcrPergunta[_iQuantidade-1].cidPergunta, cidPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cidTipoApresentacaoPergunta, cidTipoApresentacaoPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cdsPergunta, cdsPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].cdsScriptPergunta, cdsScriptPergunta );
	strcpy( stcrPergunta[_iQuantidade-1].csqApresentacao, csqApresentacao );
	strcpy( stcrPergunta[_iQuantidade-1].cinEncerramento , cinEncerramento );
	strcpy( stcrPergunta[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
	strcpy( stcrPergunta[_iQuantidade-1].cinObrigatoria   , cinObrigatoria );
}


void CPerguntaItr::ZeraPergunta( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPergunta );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPergunta    = NULL;
}
