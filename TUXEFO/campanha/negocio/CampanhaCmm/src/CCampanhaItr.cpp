#include <stdio.h>
#include <CCampanhaItr.h>

CCampanhaItr::CCampanhaItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampanha    = NULL;
}

CCampanhaItr::~CCampanhaItr()
{
	ZeraCampanha();
}

int CCampanhaItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CCampanhaItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CCampanhaItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CCampanhaItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CCampanhaItr::Quantidade( void )
{
	return _iQuantidade;
}

STCampanhaRegistro* CCampanhaItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrCampanha[ _iPosicao ];
	else
		return 0;
}

STCampanhaRegistro* CCampanhaItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrCampanha[ nPosicao ];
		
	}
	else
		return 0;
}

void CCampanhaItr::Add( char* cidCampanha, 
				        char* csgCampanha, 
					    char* cdsCampanha, 
					    char* cdtInclusao, 
					    char* cdtAlteracao,
					    char* cidPessoaUsuarioInclusao,
					    char* cidPessoaUsuarioAlteracao,
					    char* cinAtivo )
{
	_iQuantidade++;
	stcrCampanha = (struct STCampanhaRegistro*) realloc( stcrCampanha, sizeof(STCampanhaRegistro)*_iQuantidade );
	strcpy( stcrCampanha[_iQuantidade-1].cidCampanha, cidCampanha );
	strcpy( stcrCampanha[_iQuantidade-1].csgCampanha, csgCampanha );
	strcpy( stcrCampanha[_iQuantidade-1].cdsCampanha, cdsCampanha );
	strcpy( stcrCampanha[_iQuantidade-1].cdtInclusao, cdtInclusao );
	strcpy( stcrCampanha[_iQuantidade-1].cdtAlteracao, cdtAlteracao );
	strcpy( stcrCampanha[_iQuantidade-1].cidPessoaUsuarioInclusao , cidPessoaUsuarioInclusao );
	strcpy( stcrCampanha[_iQuantidade-1].cidPessoaUsuarioAlteracao, cidPessoaUsuarioAlteracao );
	strcpy( stcrCampanha[_iQuantidade-1].cinAtivo   , cinAtivo );
}


void CCampanhaItr::ZeraCampanha( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrCampanha );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrCampanha    = NULL;
}