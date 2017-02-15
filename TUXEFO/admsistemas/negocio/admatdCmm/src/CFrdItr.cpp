#include <stdio.h>
#include "../include/CFrdItr.h"

CFeriadoItr::CFeriadoItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFeriado    = NULL;
}

CFeriadoItr::~CFeriadoItr()
{
	ZeraFeriado();
}

int CFeriadoItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CFeriadoItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CFeriadoItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CFeriadoItr::Ultimo( void )
{

	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CFeriadoItr::Quantidade( void )
{
	return _iQuantidade;
}

STFeriadoRegistro* CFeriadoItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrFeriado[ _iPosicao ];
	else
		return 0;
}

STFeriadoRegistro* CFeriadoItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrFeriado[ nPosicao ];

	}
	else
		return 0;
}

void CFeriadoItr::Add( char* cidFeriado,
					   char* cidTipoFeriado,
					   char* cdsTipoFeriado,
					   char* cidNomeFeriado,
				 	   char* cdsFeriado,
					   char* cinFeriadoMovel,
					   char* cdtDia,
					   char* cidUF,
					   char* csgUF,
					   char* cnmUF,
					   char* cidMunicipio,
					   char* cnmMunicipio,
					   char* cidHorarioVerao,
					   char* cnrHorarioVerao,
					   char* cdtInicio,
					   char* cdtFim,
					   char* cinRelatorio
					   )
{
	_iQuantidade++;
	stcrFeriado = (struct STFeriadoRegistro*) realloc( stcrFeriado, sizeof(STFeriadoRegistro)*_iQuantidade );
	strcpy( stcrFeriado[_iQuantidade-1].cidFeriado      , cidFeriado      );
	strcpy( stcrFeriado[_iQuantidade-1].cidTipoFeriado  , cidTipoFeriado  );
	strcpy( stcrFeriado[_iQuantidade-1].cdsTipoFeriado  , cdsTipoFeriado  );
	strcpy( stcrFeriado[_iQuantidade-1].cidNomeFeriado  , cidNomeFeriado  );
	strcpy( stcrFeriado[_iQuantidade-1].cdsFeriado      , cdsFeriado      );
	strcpy( stcrFeriado[_iQuantidade-1].cinFeriadoMovel , cinFeriadoMovel );
	strcpy( stcrFeriado[_iQuantidade-1].cdtDia          , cdtDia          );
	strcpy( stcrFeriado[_iQuantidade-1].cidUF           , cidUF           );
	strcpy( stcrFeriado[_iQuantidade-1].csgUF           , csgUF           );
	strcpy( stcrFeriado[_iQuantidade-1].cnmUF           , cnmUF           );
	strcpy( stcrFeriado[_iQuantidade-1].cidMunicipio    , cidMunicipio    );
	strcpy( stcrFeriado[_iQuantidade-1].cnmMunicipio    , cnmMunicipio    );
	strcpy( stcrFeriado[_iQuantidade-1].cidHorarioVerao , cidHorarioVerao );
	strcpy( stcrFeriado[_iQuantidade-1].cnrHorarioVerao , cnrHorarioVerao );
	strcpy( stcrFeriado[_iQuantidade-1].cdtInicio       , cdtInicio       );
	strcpy( stcrFeriado[_iQuantidade-1].cdtFim          , cdtFim          );
	strcpy( stcrFeriado[_iQuantidade-1].cinRelatorio    , cinRelatorio    );
}


void CFeriadoItr::ZeraFeriado( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrFeriado );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrFeriado    = NULL;
}
