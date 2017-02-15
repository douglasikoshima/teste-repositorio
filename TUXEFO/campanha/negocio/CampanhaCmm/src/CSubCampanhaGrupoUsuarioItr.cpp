#include <stdio.h>
#include <CSubCampanhaGrupoUsuarioItr.h>

CSubCampanhaGrupoUsuarioItr::CSubCampanhaGrupoUsuarioItr()
{
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSubCampanhaGrupoUsuario    = NULL;
}

CSubCampanhaGrupoUsuarioItr::~CSubCampanhaGrupoUsuarioItr()
{
	ZeraSubCampanhaGrupoUsuario();
}

int CSubCampanhaGrupoUsuarioItr::Primeiro( void )
{
	_iPosicao = 0;
	return _iPosicao;
}

int CSubCampanhaGrupoUsuarioItr::Proximo( void )
{
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	return _iPosicao;
}

int CSubCampanhaGrupoUsuarioItr::Anterior( void )
{
	if( _iPosicao > 0 )
		_iPosicao--;

	return _iPosicao;
}

int CSubCampanhaGrupoUsuarioItr::Ultimo( void )
{
	
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;

	return _iPosicao;
}

int CSubCampanhaGrupoUsuarioItr::Quantidade( void )
{
	return _iQuantidade;
}

STSubCampanhaGrupoUsuarioRegistro* CSubCampanhaGrupoUsuarioItr::Registro( void )
{
	if( _iQuantidade > 0 )
		return &stcrSubCampanhaGrupoUsuario[ _iPosicao ];
	else
		return 0;
}

STSubCampanhaGrupoUsuarioRegistro* CSubCampanhaGrupoUsuarioItr::Registro(int nPosicao)
{
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();

		return &stcrSubCampanhaGrupoUsuario[ nPosicao ];
		
	}
	else
		return 0;
}

void CSubCampanhaGrupoUsuarioItr::Add( char* cidSubCampanhaGrupoUsuario,
			                           char* cidSubCampanhaFixa,
			                           char* cidGrupo,
			                           char* cinAtivo )
{
	_iQuantidade++;
	stcrSubCampanhaGrupoUsuario = (struct STSubCampanhaGrupoUsuarioRegistro*) realloc( stcrSubCampanhaGrupoUsuario, sizeof(STSubCampanhaGrupoUsuarioRegistro)*_iQuantidade );
	strcpy( stcrSubCampanhaGrupoUsuario[_iQuantidade-1].cidSubCampanhaGrupoUsuario, cidSubCampanhaGrupoUsuario );
	strcpy( stcrSubCampanhaGrupoUsuario[_iQuantidade-1].cidSubCampanhaFixa, cidSubCampanhaFixa );
	strcpy( stcrSubCampanhaGrupoUsuario[_iQuantidade-1].cidGrupo, cidGrupo );
	strcpy( stcrSubCampanhaGrupoUsuario[_iQuantidade-1].cinAtivo, cinAtivo );
}


void CSubCampanhaGrupoUsuarioItr::ZeraSubCampanhaGrupoUsuario( void )
{
	if( _iQuantidade > 0 )
	{
		free( stcrSubCampanhaGrupoUsuario );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrSubCampanhaGrupoUsuario    = NULL;
}