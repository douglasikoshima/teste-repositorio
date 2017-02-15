#include <stdio.h>
#include "CContatoFolhaUsuarioItr.h"

CContatoFolhaUsuarioItr::CContatoFolhaUsuarioItr()
{
	//ULOG_START("CContatoFolhaUsuarioItr::CContatoFolhaUsuarioItr()");
	
	_iQuantidadeUsuarioGrupoSkill = 0;    // <-----------------------------------
	_iPosicaoUsuarioGrupoSkill    = 0;    // <-----------------------------------
	stcrUsuarioGrupoSkill         = NULL; // <-----------------------------------

	_iQuantidadeContatoGrupoSkill = 0;    // <-----------------------------------
	_iPosicaoContatoGrupoSkill    = 0;    // <-----------------------------------
	stcrContatoGrupoSkill         = NULL; // <-----------------------------------


	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaUsuario    = NULL;
	
	//ULOG_END("CContatoFolhaUsuarioItr::CContatoFolhaUsuarioItr()");
}

CContatoFolhaUsuarioItr::~CContatoFolhaUsuarioItr()
{
	//ULOG_START("CContatoFolhaUsuarioItr::~CContatoFolhaUsuarioItr()");
	ZeraUsuarioGrupoSkill();   // <----------------------------------------------
	ZeraContatoGrupoSkill();   // <----------------------------------------------
	ZeraContatoFolhaUsuario();
	//ULOG_END("CContatoFolhaUsuarioItr::~CContatoFolhaUsuarioItr()");
}

int CContatoFolhaUsuarioItr::Quantidade( void )
{
	//ULOG_START("int CContatoFolhaUsuarioItr::Quantidade( void )");
	//ULOG_END("int CContatoFolhaUsuarioItr::Quantidade( void )");
	return _iQuantidade;
}

/*>>>>***********************************************************************************/
int CContatoFolhaUsuarioItr::QuantidadeUsuarioGrupoSkill( void )
{
	//ULOG_START("CContatoFolhaUsuarioItr::QuantidadeUsuarioGrupoSkill(  )");
	//ULOG_END(" CContatoFolhaUsuarioItr::QuantidadeUsuarioGrupoSkill(  )");
	return _iQuantidadeUsuarioGrupoSkill;
}

STUsuarioGrupoSkillRegistro* CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill( void )
{
	//ULOG_START("CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill(  )");
	if( _iQuantidadeUsuarioGrupoSkill > 0 )
	{
		//ULOG_END("CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill(  )");
		return &stcrUsuarioGrupoSkill[ _iPosicaoUsuarioGrupoSkill ];
	}
	else
	{
		//ULOG_END("CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill(  )");
		return 0;
	}
}

STUsuarioGrupoSkillRegistro* CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill(int nPosicao)
{
	//ULOG_START("CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill()");
	
	if( _iQuantidadeUsuarioGrupoSkill > 0 )
	{
		if( nPosicao >= _iQuantidadeUsuarioGrupoSkill )
			nPosicao = _iQuantidadeUsuarioGrupoSkill-1;
	    
	    //ULOG_END("CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill( )");
		return &stcrUsuarioGrupoSkill[ nPosicao ];
	}
	else
	{
	    //ULOG_END("CContatoFolhaUsuarioItr::RegistroUsuarioGrupoSkill( )");
		return 0;
    }
}
/*<<<<***********************************************************************************/

STContatoFolhaUsuarioRegistro* CContatoFolhaUsuarioItr::Registro( void )
{
	//ULOG_START("CContatoFolhaUsuarioItr::Registro(  )");
	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CContatoFolhaUsuarioItr::Registro(  )");
		return &stcrContatoFolhaUsuario[ _iPosicao ];
	}
	else
	{
		//ULOG_END("CContatoFolhaUsuarioItr::Registro(  )");
		return 0;
	}
}

STContatoFolhaUsuarioRegistro* CContatoFolhaUsuarioItr::Registro(int nPosicao)
{
	//ULOG_START("CContatoFolhaUsuarioItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = _iQuantidade-1;

        //ULOG_END("CContatoFolhaUsuarioItr::Registro( )");
		return &stcrContatoFolhaUsuario[ nPosicao ];

	}
	else
	{
	    //ULOG_END("CContatoFolhaUsuarioItr::Registro()");
		return 0;
	}
}

void CContatoFolhaUsuarioItr::Add( 
					 	 char* cidContatoFolhaUsuario
					 	,char* cidPessoaUsuario
					 	,char* cidContato
						,char* cidGrupoSkill
	  )
{
	//ULOG_START("CContatoFolhaUsuarioItr::Add( )");
	_iQuantidade++;
	stcrContatoFolhaUsuario = (struct STContatoFolhaUsuarioRegistro*) realloc( stcrContatoFolhaUsuario, sizeof(STContatoFolhaUsuarioRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaUsuario[_iQuantidade-1].cidContatoFolhaUsuario, cidContatoFolhaUsuario );
	strcpy( stcrContatoFolhaUsuario[_iQuantidade-1].cidPessoaUsuario, cidPessoaUsuario );
	strcpy( stcrContatoFolhaUsuario[_iQuantidade-1].cidContato, cidContato );
	strcpy( stcrContatoFolhaUsuario[_iQuantidade-1].cidGrupoSkill, cidGrupoSkill );
	//ULOG_END("CContatoFolhaUsuarioItr::Add()");
}

void CContatoFolhaUsuarioItr::ZeraContatoFolhaUsuario( void )
{
	//ULOG_START("CContatoFolhaUsuarioItr::ZeraContatoFolhaUsuario(  )");
	
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolhaUsuario );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaUsuario = NULL;
	
	//ULOG_END("CContatoFolhaUsuarioItr::ZeraContatoFolhaUsuario(  )");
}


/*>>>>******************************************************************************************************/
void CContatoFolhaUsuarioItr::AddUsuarioGrupoSkill( 
													 char* cidUsuarioGrupoSkill
													,char* cidGrupoSkill
													,char* cidPessoaUsuario
	  																					)
{
	//ULOG_START("CContatoFolhaUsuarioItr::AddUsuarioGrupoSkill( )");
//_iQuantidade++;
	_iQuantidadeUsuarioGrupoSkill++;

	stcrUsuarioGrupoSkill = (struct STUsuarioGrupoSkillRegistro*) realloc( stcrUsuarioGrupoSkill, sizeof(STUsuarioGrupoSkillRegistro)*_iQuantidadeUsuarioGrupoSkill );
	
	strcpy( stcrUsuarioGrupoSkill[_iQuantidadeUsuarioGrupoSkill-1].cidUsuarioGrupoSkill, cidUsuarioGrupoSkill );
	strcpy( stcrUsuarioGrupoSkill[_iQuantidadeUsuarioGrupoSkill-1].cidGrupoSkill, cidGrupoSkill );
	strcpy( stcrUsuarioGrupoSkill[_iQuantidadeUsuarioGrupoSkill-1].cidPessoaUsuario, cidPessoaUsuario );
	//ULOG_END("CContatoFolhaUsuarioItr::AddUsuarioGrupoSkill()");
}

void CContatoFolhaUsuarioItr::ZeraUsuarioGrupoSkill( void )
{
	//ULOG_START("CContatoFolhaUsuarioItr::ZeraUsuarioGrupoSkill(  )");
	if( _iQuantidadeUsuarioGrupoSkill > 0 )
	{
		//free( stcrContatoFolhaUsuario );
		free( stcrUsuarioGrupoSkill ); 
	}
	_iQuantidadeUsuarioGrupoSkill = 0;
	_iPosicaoUsuarioGrupoSkill    = 0;
	//stcrContatoFolhaUsuario = NULL;
	stcrUsuarioGrupoSkill = NULL; 
	//ULOG_END("CContatoFolhaUsuarioItr::ZeraUsuarioGrupoSkill( )");
}

void CContatoFolhaUsuarioItr::ZeraContatoGrupoSkill( void )
{
	//ULOG_START("CContatoFolhaUsuarioItr::ZeraContatoGrupoSkill( )");
	if( _iQuantidadeContatoGrupoSkill > 0 )
	{
		free( stcrContatoGrupoSkill ); 
	}
	_iQuantidadeContatoGrupoSkill = 0;
	_iPosicaoContatoGrupoSkill    = 0;
	stcrContatoGrupoSkill = NULL; 
	//ULOG_END("CContatoFolhaUsuarioItr::ZeraContatoGrupoSkill( )");
}
/*<<<*******************************************************************************************************/