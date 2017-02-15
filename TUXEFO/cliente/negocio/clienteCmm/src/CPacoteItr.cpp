#include <stdio.h>
#include <CPacoteItr.h>
#include <tuxfw.h>

CPacoteItr::CPacoteItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPacote    = NULL;
}

CPacoteItr::~CPacoteItr()
{
	ZeraPacote();
}

int CPacoteItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPacoteItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPacoteItr::Quantidade( void )
{
	return _iQuantidade;
}

STPacoteRegistro* CPacoteItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPacote[ _iPosicao ];
	else
		return 0;
}

STPacoteRegistro* CPacoteItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPacote[ nPosicao ];
	}
	else
		return 0;
}

void CPacoteItr::Add(char* cidServicoSistemaOrigem)
{
    //ULOGI("CPacoteItr::Add - cidServicoSistemaOrigem[%s]", cidServicoSistemaOrigem);

	_iQuantidade++;
	stcrPacote = (struct STPacoteRegistro*) realloc(stcrPacote, sizeof(STPacoteRegistro)*_iQuantidade);
	strcpy(stcrPacote[_iQuantidade-1].cidServicoSistemaOrigem, cidServicoSistemaOrigem);
}

void CPacoteItr::ZeraPacote(void)
{
	if( _iQuantidade > 0 )
	{
		free(stcrPacote);
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPacote = NULL;
}
