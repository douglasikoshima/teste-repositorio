#include <stdio.h>
#include "CContatoFolhaUsuarioSkillItr.h"

CContatoFolhaUsuarioSkillItr::CContatoFolhaUsuarioSkillItr()
{
   //ULOG_START("CContatoFolhaUsuarioSkillItr::CContatoFolhaUsuarioSkillItr()");

	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaUsuarioSkill    = NULL;
	
   //ULOG_END("CContatoFolhaUsuarioSkillItr::CContatoFolhaUsuarioSkillItr()");	
}

CContatoFolhaUsuarioSkillItr::~CContatoFolhaUsuarioSkillItr()
{
	//ULOG_START("CContatoFolhaUsuarioSkillItr::~CContatoFolhaUsuarioSkillItr()");
	
	ZeraContatoFolhaUsuarioSkill();

    //ULOG_END("CContatoFolhaUsuarioSkillItr::~CContatoFolhaUsuarioSkillItr()");
}

int CContatoFolhaUsuarioSkillItr::Quantidade( void )
{
	//ULOG_START(" CContatoFolhaUsuarioSkillItr::Quantidade(  )");
	//ULOG_END(" CContatoFolhaUsuarioSkillItr::Quantidade(  )");
	return _iQuantidade;
}

STContatoFolhaUsuarioSkillRegistro* CContatoFolhaUsuarioSkillItr::Registro( void )
{
	//ULOG_START("CContatoFolhaUsuarioSkillItr::Registro(  )");
	
	if( _iQuantidade > 0 )
	{
		//ULOG_END("CContatoFolhaUsuarioSkillItr::Registro(  )");
		return &stcrContatoFolhaUsuarioSkill[ _iPosicao ];
	}
	else
	{
	    //ULOG_END("CContatoFolhaUsuarioSkillItr::Registro(  )");
		return 0;
	}
}

STContatoFolhaUsuarioSkillRegistro* CContatoFolhaUsuarioSkillItr::Registro(int nPosicao)
{
	//ULOG_START("CContatoFolhaUsuarioSkillItr::Registro( )");
	if( _iQuantidade > 0 )
	{
		if( nPosicao >= _iQuantidade )
			nPosicao = _iQuantidade-1;

	    //ULOG_END("CContatoFolhaUsuarioSkillItr::Registro( )");
		return &stcrContatoFolhaUsuarioSkill[ nPosicao ];

	}
	else
	{
	    //ULOG_END("CContatoFolhaUsuarioSkillItr::Registro( )");
		return 0;
	}
}

void CContatoFolhaUsuarioSkillItr::Add( 
					 	 char* cidGrupoSkill
					 	,char* cidContatoFolhaUsuario
	  )
{
	//ULOG_START("CContatoFolhaUsuarioSkillItr::Add()");
	
	_iQuantidade++;
	stcrContatoFolhaUsuarioSkill = (struct STContatoFolhaUsuarioSkillRegistro*) realloc( stcrContatoFolhaUsuarioSkill, sizeof(STContatoFolhaUsuarioSkillRegistro)*_iQuantidade );
	strcpy( stcrContatoFolhaUsuarioSkill[_iQuantidade-1].cidGrupoSkill, cidGrupoSkill );
	strcpy( stcrContatoFolhaUsuarioSkill[_iQuantidade-1].cidContatoFolhaUsuario, cidContatoFolhaUsuario );
	
	//ULOG_END("CContatoFolhaUsuarioSkillItr::Add()");
}


void CContatoFolhaUsuarioSkillItr::ZeraContatoFolhaUsuarioSkill( void )
{
	//ULOG_START("CContatoFolhaUsuarioSkillItr::ZeraContatoFolhaUsuarioSkill( )");
	if( _iQuantidade > 0 )
	{
		free( stcrContatoFolhaUsuarioSkill );
	}
	_iQuantidade = 0;
	_iPosicao    = 0;
	stcrContatoFolhaUsuarioSkill = NULL;
	//ULOG_END("CContatoFolhaUsuarioSkillItr::ZeraContatoFolhaUsuarioSkill( )");
}