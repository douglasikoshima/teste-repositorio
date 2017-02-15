#ifndef CContatoFolhaUsuarioH
#define CContatoFolhaUsuarioH

#include <tuxfw.h>
#include "CContatoFolhaUsuarioItr.h"
#include "CSafePointer.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CContatoFolhaUsuario : public CContatoFolhaUsuarioItr ,  public TuxHelper
{
  private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFolhaUsuario();
		~CContatoFolhaUsuario();
		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoFolhaUsuario );
		//Recupera todos os registros
		int ListAll( void );
		//Insere n registros
		//int Insert(TVector stVector[]);
		//Edita um registro
		int Update(
					 	 char* cidContatoFolhaUsuario
					 	,char* cidPessoaUsuario
					 	,char* cidContato
						,char* cidGrupoSkill
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidContatoFolhaUsuario );
		//Apaga todos os registros daquele usuario contido em um certo grupo
		int DeleteDependencia( 
							   char* cidGrupo
			                  ,char* cidUsuario 
							 );
		//Recupera a lista de usuarios com skill
		int Pesquisa( DOMNode* dnXmlIn, XMLGen* XmlSaida, char* pzcidGrupo, char* pzcidGrupoSkill, int iPagina, int &iQtdeRegistros );

		/**************************************************************/
		//Deleta todos os usuarios associados ao skill e em seguida insere os usuários selecionados para associacao.
		int DeleteInsertUsuarioGrupoSkill(TVector stVector[], char* delUsuarios, int insertUsuarios);
		
		//Deleta todos os contatos associados ao skill e em seguida insere os contatos selecionados para associacao.
		int DeleteInsertContatoGrupoSkill(TVector2 stVector[], char* delContatos, int insertContatos);
		/**************************************************************/
};
#endif