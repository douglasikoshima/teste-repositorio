#include <stdio.h>
#include <CListaCanalCampanhaItr.h>

CListaCanalCampanhaItr::CListaCanalCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrListaCanalCampanha    = NULL;
}

CListaCanalCampanhaItr::~CListaCanalCampanhaItr()
{
	ZeraListaCanalCampanha();
}

int CListaCanalCampanhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CListaCanalCampanhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CListaCanalCampanhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CListaCanalCampanhaItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CListaCanalCampanhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STListaCanalCampanhaRegistro* CListaCanalCampanhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrListaCanalCampanha[ _iPosicao ];
	else
		return 0;
}

STListaCanalCampanhaRegistro* CListaCanalCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrListaCanalCampanha[ nPosicao ];
		
	}
	else
		return 0;
}

void CListaCanalCampanhaItr::Add( char* cidListaCanalCampanha,
	                              char* cidLista,
	                              char* cidCanalCampanha,
	                              char* cinAtivo )
{
	_iQuantidade++;
	stcrListaCanalCampanha = (struct STListaCanalCampanhaRegistro*) realloc( stcrListaCanalCampanha, sizeof(STListaCanalCampanhaRegistro)*_iQuantidade );
	strcpy( stcrListaCanalCampanha[_iQuantidade-1].cidListaCanalCampanha, cidListaCanalCampanha );
	strcpy( stcrListaCanalCampanha[_iQuantidade-1].cidLista, cidLista );
	strcpy( stcrListaCanalCampanha[_iQuantidade-1].cidCanalCampanha, cidCanalCampanha );
	strcpy( stcrListaCanalCampanha[_iQuantidade-1].cinAtivo, cinAtivo );
}


void CListaCanalCampanhaItr::ZeraListaCanalCampanha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrListaCanalCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrListaCanalCampanha    = NULL;
}