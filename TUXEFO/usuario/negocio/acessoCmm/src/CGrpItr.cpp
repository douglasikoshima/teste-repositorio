#include <stdio.h>
#include "CGrpItr.h"

CGrupoItr::CGrupoItr()
{
	//ULOG_START("CGrupoItr::CGrupoItr()");
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrGrupo    = NULL;
	//ULOG_END("CGrupoItr::CGrupoItr()");
}

CGrupoItr::~CGrupoItr()
{
	//ULOG_START("CGrupoItr::~CGrupoItr()");
	ZeraGrupo();
	//ULOG_END("CGrupoItr::~CGrupoItr()");
}

int CGrupoItr::Primeiro( void )
{
	//ULOG_START(" CGrupoItr::Primeiro(  )");
	_iPosicao = 0;
	//ULOG_END(" CGrupoItr::Primeiro(  )");
	return _iPosicao;
}

int CGrupoItr::Proximo( void )
{
	//ULOG_START(" CGrupoItr::Proximo(  )");
	if( _iQuantidade > 0 )
	{
		if( _iPosicao < (_iQuantidade-1) )
			_iPosicao++;
	}
	//ULOG_END(" CGrupoItr::Proximo(  )");
	return _iPosicao;
}

int CGrupoItr::Anterior( void )
{
	//ULOG_START(" CGrupoItr::Anterior(  )");
	if( _iPosicao > 0 )
		_iPosicao--;
    
    //ULOG_END(" CGrupoItr::Anterior(  )");
	return _iPosicao;
}

int CGrupoItr::Ultimo( void )
{
    //ULOG_START(" CGrupoItr::Ultimo(  )");
	if( _iQuantidade > 0 )
		_iPosicao = _iQuantidade-1;
	else
		_iPosicao = 0;
    
    //ULOG_END(" CGrupoItr::Ultimo(  )");
	return _iPosicao;
}

int CGrupoItr::Quantidade( void )
{
	//ULOG_START(" CGrupoItr::Quantidade(  )");
	//ULOG_END(" CGrupoItr::Quantidade(  )");
	return _iQuantidade;
}

STGrupoRegistro* CGrupoItr::Registro( void )
{
	//ULOG_START("CGrupoItr::Registro(  )");

	if( _iQuantidade > 0 )
	{
	    //ULOG_END(" CGrupoItr::Registro(  )");
		return &stcrGrupo[ _iPosicao ];
	}
	else
	{
	    //ULOG_END(" CGrupoItr::Registro(  )");
		return 0;
	}
}

STGrupoRegistro* CGrupoItr::Registro(int nPosicao)
{
	//ULOG_START(" CGrupoItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = Ultimo();
        //ULOG_END("CGrupoItr::Registro( )");
		return &stcrGrupo[ nPosicao ];

	}
	else
	{
		//ULOG_END(" CGrupoItr::Registro( )");
		return 0;
	}
}

void CGrupoItr::Add( 
					  char* cidUsuarioGrupo
					 ,char* cidPessoaUsuario
					 ,char* cidGrupo
                     ,char* cinSupervisor 
			       )
{
	//ULOG_START(" CGrupoItr::Add()");
	_iQuantidade++;
	stcrGrupo = (struct STGrupoRegistro*) realloc( stcrGrupo, sizeof(STGrupoRegistro)*_iQuantidade );
	strcpy( stcrGrupo[_iQuantidade-1].cidUsuarioGrupo, cidUsuarioGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cidPessoaUsuario, cidPessoaUsuario);
	strcpy( stcrGrupo[_iQuantidade-1].cidGrupo, cidGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cinSupervisor, cinSupervisor);
	//ULOG_END(" CGrupoItr::Add( )");
}

void CGrupoItr::Add( 
					  char* cidUsuarioGrupo
					 ,char* cidPessoaUsuario
					 ,char* cidGrupo
                     ,char* cinSupervisor 
					 ,char* cdsTIPOGRUPO
			       )
{
	//ULOG_START(" CGrupoItr::Add()");
	_iQuantidade++;
	stcrGrupo = (struct STGrupoRegistro*) realloc( stcrGrupo, sizeof(STGrupoRegistro)*_iQuantidade );
	strcpy( stcrGrupo[_iQuantidade-1].cidUsuarioGrupo, cidUsuarioGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cidPessoaUsuario, cidPessoaUsuario);
	strcpy( stcrGrupo[_iQuantidade-1].cidGrupo, cidGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cinSupervisor, cinSupervisor);
	strcpy( stcrGrupo[_iQuantidade-1].cdsTIPOGRUPO, cdsTIPOGRUPO);
    //ULOG_END("CGrupoItr::Add()");
}

void CGrupoItr::Add( 
					  char* cidGrupo
					 ,char* cnmGrupo
			       )
{
	//ULOG_START(" CGrupoItr::Add( )");
	_iQuantidade++;
	stcrGrupo = (struct STGrupoRegistro*) realloc( stcrGrupo, sizeof(STGrupoRegistro)*_iQuantidade );
	strcpy( stcrGrupo[_iQuantidade-1].cidGrupo, cidGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cnmGrupo, cnmGrupo);
	//ULOG_END(" CGrupoItr::Add( )");
}

void CGrupoItr::Add( 
					  char* cidGrupo
					 ,char* cnmGrupo 
					 ,char* cidTIPOGRUPO
			       )
{
	//ULOG_START(" CGrupoItr::Add()");
	_iQuantidade++;
	stcrGrupo = (struct STGrupoRegistro*) realloc( stcrGrupo, sizeof(STGrupoRegistro)*_iQuantidade );
	strcpy( stcrGrupo[_iQuantidade-1].cidGrupo, cidGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cnmGrupo, cnmGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cidTIPOGRUPO, cidTIPOGRUPO);
	//ULOG_END(" CGrupoItr::Add()");
}

void CGrupoItr::Add( 
					  char* cidGrupo
					 ,char* cnmGrupo 
					 ,char* cdsTIPOGRUPO
					 ,char* cidTIPOGRUPO
					 ,int iTipo
			       )
{
	//ULOG_START(" CGrupoItr::Add()");
	_iQuantidade++;
	stcrGrupo = (struct STGrupoRegistro*) realloc( stcrGrupo, sizeof(STGrupoRegistro)*_iQuantidade );
	strcpy( stcrGrupo[_iQuantidade-1].cidGrupo, cidGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cnmGrupo, cnmGrupo);
	strcpy( stcrGrupo[_iQuantidade-1].cdsTIPOGRUPO, cdsTIPOGRUPO);
	strcpy( stcrGrupo[_iQuantidade-1].cidTIPOGRUPO, cidTIPOGRUPO);
	//ULOG_END(" CGrupoItr::Add()");
}


void CGrupoItr::ZeraGrupo( void )
{
	//ULOG_START("CGrupoItr::ZeraGrupo(  )");
	if( _iQuantidade > 0 )
	{
		free( stcrGrupo );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrGrupo    = NULL;
	//ULOG_END(" CGrupoItr::ZeraGrupo(  )");
}
