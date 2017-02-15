#ifndef CContatoFolhaUsuarioSkillItrH
#define CContatoFolhaUsuarioSkillItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STContatoFolhaUsuarioSkillRegistro
{
	char cidGrupoSkill[21+1];
	char cidContatoFolhaUsuario[21+1];
};

class CContatoFolhaUsuarioSkillItr
{
public:
	CContatoFolhaUsuarioSkillItr();
	~CContatoFolhaUsuarioSkillItr();
	int Quantidade( void );
	STContatoFolhaUsuarioSkillRegistro* Registro( void );
	STContatoFolhaUsuarioSkillRegistro* Registro(int nPosicao);

private:
	STContatoFolhaUsuarioSkillRegistro* stcrContatoFolhaUsuarioSkill;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
		 char* cidGrupoSkill
		,char* cidContatoFolhaUsuario
	);
   void ZeraContatoFolhaUsuarioSkill( void );
};
#endif