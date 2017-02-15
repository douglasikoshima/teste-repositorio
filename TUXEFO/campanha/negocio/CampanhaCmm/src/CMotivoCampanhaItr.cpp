#include <stdio.h>
#include <CMotivoCampanhaItr.h>



CMotivoCampanhaItr::CMotivoCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMotivoCampanha    = NULL;
}



CMotivoCampanhaItr::~CMotivoCampanhaItr()
{
	ZeraMotivoCampanha();
}



int CMotivoCampanhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}



int CMotivoCampanhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}



int CMotivoCampanhaItr::Anterior( void )

{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}



int CMotivoCampanhaItr::Ultimo( void )
{
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}



int CMotivoCampanhaItr::Quantidade( void )

{
	return _iQuantidade;
}



STMotivoCampanhaRegistro* CMotivoCampanhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrMotivoCampanha[ _iPosicao ];
	else
		return 0;
}



STMotivoCampanhaRegistro* CMotivoCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrMotivoCampanha[ nPosicao ];
	}
	else
		return 0;
}



void CMotivoCampanhaItr::Add( char* cidMotivoCampanha, 
						      char* cidSubCampanhaHistorico, 
							  char* cidTipoStatusCampanha, 
							  char* cidTipoMotivoCampanha, 
							  char* cidTipoSubMotivoCampanha )
{
	_iQuantidade++;
	stcrMotivoCampanha = (struct STMotivoCampanhaRegistro*) realloc( stcrMotivoCampanha, sizeof(STMotivoCampanhaRegistro)*_iQuantidade );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidMotivoCampanha, cidMotivoCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidSubCampanhaHistorico, cidSubCampanhaHistorico );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidTipoStatusCampanha, cidTipoStatusCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidTipoMotivoCampanha, cidTipoMotivoCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidTipoSubMotivoCampanha, cidTipoSubMotivoCampanha );
}

void CMotivoCampanhaItr::Add( char* cidMotivoCampanha, 
						      char* cidSubCampanhaHistorico, 
							  char* cidTipoStatusCampanha, 
							  char* cidTipoMotivoCampanha, 
							  char* cidTipoSubMotivoCampanha,
							  char* cinAtivo )
{
	_iQuantidade++;
	stcrMotivoCampanha = (struct STMotivoCampanhaRegistro*) realloc( stcrMotivoCampanha, sizeof(STMotivoCampanhaRegistro)*_iQuantidade );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidMotivoCampanha, cidMotivoCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidSubCampanhaHistorico, cidSubCampanhaHistorico );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidTipoStatusCampanha, cidTipoStatusCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidTipoMotivoCampanha, cidTipoMotivoCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cidTipoSubMotivoCampanha, cidTipoSubMotivoCampanha );
	strcpy( stcrMotivoCampanha[_iQuantidade-1].cinAtivo, cinAtivo );
}

void CMotivoCampanhaItr::ZeraMotivoCampanha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrMotivoCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrMotivoCampanha    = NULL;
}