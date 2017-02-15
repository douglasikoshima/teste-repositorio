#ifndef CContatoFolhaUsuarioSkillH
#define CContatoFolhaUsuarioSkillH

#include <tuxfw.h>
#include "CContatoFolhaUsuarioSkillItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CContatoFolhaUsuarioSkill : public CContatoFolhaUsuarioSkillItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFolhaUsuarioSkill();
		~CContatoFolhaUsuarioSkill();
		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidGrupoSkill );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(
					 	 char* cidGrupoSkill
					 	,char* cidContatoFolhaUsuario
					 	,char* cidUser
		);
		//Edita um registro
		int Update(
					 	 char* cidGrupoSkill
					 	,char* cidContatoFolhaUsuario
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidGrupoSkill );
};
#endif