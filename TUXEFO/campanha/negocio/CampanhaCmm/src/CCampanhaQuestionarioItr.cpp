#include <stdio.h>
#include <CCampanhaQuestionarioItr.h>

CCampanhaQuestionarioItr::CCampanhaQuestionarioItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampanhaQuestionario    = NULL;
}

CCampanhaQuestionarioItr::~CCampanhaQuestionarioItr()
{
	ZeraCampanhaQuestionario();
}

int CCampanhaQuestionarioItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCampanhaQuestionarioItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCampanhaQuestionarioItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCampanhaQuestionarioItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCampanhaQuestionarioItr::Quantidade( void )
{
	return _iQuantidade;
}

STCampanhaQuestionarioRegistro* CCampanhaQuestionarioItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCampanhaQuestionario[ _iPosicao ];
	else
		return 0;
}

STCampanhaQuestionarioRegistro* CCampanhaQuestionarioItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCampanhaQuestionario[ nPosicao ];
		
	}
	else
		return 0;
}

void CCampanhaQuestionarioItr::Add( char* cidCampanhaQuestionario, 
							        char* cidCanalCampanha, 
							        char* cidPergunta,
							        char* cidUser,
							        char* cdtUltimaAlteracao )
{
	_iQuantidade++;
	stcrCampanhaQuestionario = (struct STCampanhaQuestionarioRegistro*) realloc( stcrCampanhaQuestionario, sizeof(STCampanhaQuestionarioRegistro)*_iQuantidade );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidCampanhaQuestionario, cidCampanhaQuestionario );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidCanalCampanha, cidCanalCampanha );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidPergunta, cidPergunta );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidUser, cidUser );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cdtUltimaAlteracao, cdtUltimaAlteracao );
}

void CCampanhaQuestionarioItr::Add( char* cidCampanhaQuestionario, 
								    char* cidCanalCampanha, 
								    char* cidPergunta,
								    char* cinAtivo )
{
	_iQuantidade++;
	stcrCampanhaQuestionario = (struct STCampanhaQuestionarioRegistro*) realloc( stcrCampanhaQuestionario, sizeof(STCampanhaQuestionarioRegistro)*_iQuantidade );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidCampanhaQuestionario, cidCampanhaQuestionario );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidCanalCampanha, cidCanalCampanha );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cidPergunta, cidPergunta );
	strcpy( stcrCampanhaQuestionario[_iQuantidade-1].cinAtivo, cinAtivo );
}


void CCampanhaQuestionarioItr::ZeraCampanhaQuestionario( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCampanhaQuestionario );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampanhaQuestionario    = NULL;
}