#include <stdio.h>
#include <CCanalCampanhaItr.h>

CCanalCampanhaItr::CCanalCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCanalCampanha    = NULL;
}

CCanalCampanhaItr::~CCanalCampanhaItr()
{
	ZeraCanalCampanha();
}

int CCanalCampanhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCanalCampanhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCanalCampanhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCanalCampanhaItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCanalCampanhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STCanalCampanhaRegistro* CCanalCampanhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCanalCampanha[ _iPosicao ];
	else
		return 0;
}

STCanalCampanhaRegistro* CCanalCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCanalCampanha[ nPosicao ];
		
	}
	else
		return 0;
}

void CCanalCampanhaItr::Add( char* cidCanalCampanha,
	                         char* cidSubCampanhaHistorico,
	                         char* cidCanalUFOperadora,
	                         char* csqApresentacao,
	                         char* cinDisponibilidade,
	                         char* cinAtivo )
{
	_iQuantidade++;
	stcrCanalCampanha = (struct STCanalCampanhaRegistro*) realloc( stcrCanalCampanha, sizeof(STCanalCampanhaRegistro)*_iQuantidade );
	strcpy( stcrCanalCampanha[_iQuantidade-1].cidCanalCampanha, cidCanalCampanha );
	strcpy( stcrCanalCampanha[_iQuantidade-1].cidSubCampanhaHistorico, cidSubCampanhaHistorico );
	strcpy( stcrCanalCampanha[_iQuantidade-1].cidCanalUFOperadora, cidCanalUFOperadora );
	strcpy( stcrCanalCampanha[_iQuantidade-1].csqApresentacao, csqApresentacao );
	strcpy( stcrCanalCampanha[_iQuantidade-1].cinDisponibilidade, cinDisponibilidade );
	strcpy( stcrCanalCampanha[_iQuantidade-1].cinAtivo, cinAtivo );
}


void CCanalCampanhaItr::ZeraCanalCampanha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCanalCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCanalCampanha    = NULL;
}