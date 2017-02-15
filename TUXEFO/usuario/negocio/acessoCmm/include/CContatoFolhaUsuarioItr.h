#ifndef CContatoFolhaUsuarioItrH
#define CContatoFolhaUsuarioItrH

#include <stdlib.h>
#include <string.h>
#include <tuxfw.h>

/****************************************************************************/
struct STUsuarioGrupoSkillRegistro
{
	char cidUsuarioGrupoSkill[21+1];
	char cidGrupoSkill[21+1];
	char cidPessoaUsuario[21+1];
};

struct STContatoGrupoSkillRegistro
{
	char cidContatoGrupoSkill[21+1];
	char cidGrupoSkill[21+1];
	char cidContato[21+1];
};
/****************************************************************************/
typedef struct {
    char pzcidSkillUsuario[21+1];        
    char pzcusuariosSelecionados[21+1];
    char pzcidUser[21+1];
} TVector;

typedef struct {
    char pzcidSkillContato[21+1];
    char pzccontatosSelecionados[21+1];
    char pzcidUser[21+1];
} TVector2;
/****************************************************************************/


struct STContatoFolhaUsuarioRegistro
{
	char cidContatoFolhaUsuario[21+1];
	char cidPessoaUsuario[21+1];
	char cidContato[21+1];
	char cidGrupoSkill[21+1];
};

class CContatoFolhaUsuarioItr
{
public:
	CContatoFolhaUsuarioItr();
	~CContatoFolhaUsuarioItr();
	
	int Quantidade( void );
	STContatoFolhaUsuarioRegistro* Registro( void );
	STContatoFolhaUsuarioRegistro* Registro(int nPosicao);
	
	/***********************************************************************/
	int QuantidadeUsuarioGrupoSkill( void );
	STUsuarioGrupoSkillRegistro* RegistroUsuarioGrupoSkill( void );
	STUsuarioGrupoSkillRegistro* RegistroUsuarioGrupoSkill(int nPosicao);
  /***********************************************************************/

private:

	/***********************************************************************/
	STUsuarioGrupoSkillRegistro* stcrUsuarioGrupoSkill;
	int _iQuantidadeUsuarioGrupoSkill;
	int _iPosicaoUsuarioGrupoSkill;
	
	STContatoGrupoSkillRegistro* stcrContatoGrupoSkill;
	int _iQuantidadeContatoGrupoSkill;
	int _iPosicaoContatoGrupoSkill;
  /***********************************************************************/

	STContatoFolhaUsuarioRegistro* stcrContatoFolhaUsuario;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
		 char* cidContatoFolhaUsuario
		,char* cidPessoaUsuario
		,char* cidContato
		,char* cidGrupoSkill
	);

  void ZeraContatoFolhaUsuario( void );
  
  /***********************************************************************/
	void AddUsuarioGrupoSkill( 
		 char* cidUsuarioGrupoSkill
		,char* cidGrupoSkill
		,char* cidPessoaUsuario
	);

  void ZeraUsuarioGrupoSkill( void );
  void ZeraContatoGrupoSkill( void );
	/***********************************************************************/
};
#endif