#include <stdio.h>
#include "../include/CFlhPrzItr.h"

CPrazoAtendimentoItr::CPrazoAtendimentoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPrazoAtendimento    = NULL;
}

CPrazoAtendimentoItr::~CPrazoAtendimentoItr()
{
	ZeraPrazoAtendimento();
}

int CPrazoAtendimentoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CPrazoAtendimentoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CPrazoAtendimentoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CPrazoAtendimentoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CPrazoAtendimentoItr::Quantidade( void )
{
	return _iQuantidade;
}

STPrazoAtendimentoRegistro* CPrazoAtendimentoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrPrazoAtendimento[ _iPosicao ];
	else
		return 0;
}

STPrazoAtendimentoRegistro* CPrazoAtendimentoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrPrazoAtendimento[ nPosicao ];

	}
	else
		return 0;
}

void CPrazoAtendimentoItr::Add(
			char* cidPrazoAtendimento,
			char* cidContato,
			char* cidSegmentacao,
			char* cidProcedencia,
			char* cqtDiasPrazoAtendimento )
{
	_iQuantidade++;
	stcrPrazoAtendimento = (struct STPrazoAtendimentoRegistro*) realloc( stcrPrazoAtendimento, sizeof(STPrazoAtendimentoRegistro)*_iQuantidade );
	strcpy( stcrPrazoAtendimento[_iQuantidade-1].cidPrazoAtendimento, cidPrazoAtendimento);
	strcpy( stcrPrazoAtendimento[_iQuantidade-1].cidContato, cidContato);
	strcpy( stcrPrazoAtendimento[_iQuantidade-1].cidSegmentacao, cidSegmentacao);
	strcpy( stcrPrazoAtendimento[_iQuantidade-1].cidProcedencia, cidProcedencia);
	strcpy( stcrPrazoAtendimento[_iQuantidade-1].cqtDiasPrazoAtendimento, cqtDiasPrazoAtendimento);
}


void CPrazoAtendimentoItr::ZeraPrazoAtendimento( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrPrazoAtendimento );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrPrazoAtendimento    = NULL;
}
