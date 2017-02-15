#include <stdio.h>
#include <CSubCampanhaFixaItr.h>

CSubCampanhaFixaItr::CSubCampanhaFixaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSubCampanhaFixa    = NULL;
}

CSubCampanhaFixaItr::~CSubCampanhaFixaItr()
{
	ZeraSubCampanhaFixa();
}

int CSubCampanhaFixaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSubCampanhaFixaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CSubCampanhaFixaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CSubCampanhaFixaItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSubCampanhaFixaItr::Quantidade( void )
{
	return _iQuantidade;
}

STSubCampanhaFixaRegistro* CSubCampanhaFixaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSubCampanhaFixa[ _iPosicao ];
	else
		return 0;
}

STSubCampanhaFixaRegistro* CSubCampanhaFixaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSubCampanhaFixa[ nPosicao ];
		
	}
	else
		return 0;
}

void CSubCampanhaFixaItr::Add( char* cidSubCampanhaFixa, 
				               char* cidCampanha, 
					           char* cinAtiva, 
					           char* cinDisponibilidade )
{
	_iQuantidade++;
	stcrSubCampanhaFixa = (struct STSubCampanhaFixaRegistro*) realloc( stcrSubCampanhaFixa, sizeof(STSubCampanhaFixaRegistro)*_iQuantidade );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cidSubCampanhaFixa, cidSubCampanhaFixa );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cidCampanha, cidCampanha );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cinAtiva, cinAtiva );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
}

void CSubCampanhaFixaItr::Add( char* cidSubCampanhaFixa, 
				               char* cnmSubCampanhaFixa, 
				               char* cidCampanha, 
					           char* cinAtiva, 
					           char* cinDisponibilidade )
{
	_iQuantidade++;
	stcrSubCampanhaFixa = (struct STSubCampanhaFixaRegistro*) realloc( stcrSubCampanhaFixa, sizeof(STSubCampanhaFixaRegistro)*_iQuantidade );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cidSubCampanhaFixa, cidSubCampanhaFixa );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cnmSubCampanhaFixa, cnmSubCampanhaFixa );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cidCampanha, cidCampanha );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cinAtiva, cinAtiva );
	strcpy( stcrSubCampanhaFixa[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
}

void CSubCampanhaFixaItr::ZeraSubCampanhaFixa( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSubCampanhaFixa );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSubCampanhaFixa    = NULL;
}