/*****************************************************************************
 *
 * Modulo:    CGrp
 * Arquivo:   CGrp.h
 * Proposito: servicos Pro*C++ para persistencia de banco de dados
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  --------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
#ifndef CGrpH

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/
#define CGrpH

/*****************************************************************************
 * Header
 ****************************************************************************/
#include<tuxfw.h>
#include "CGrpItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

/*****************************************************************************
 * Classe
 ****************************************************************************/
class CGrp : public CGrupoItr
{
	public:
		CGrp(){};
		~CGrp(){};
		char* RTrim(char *pszString);
		int GrpEditar(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GrpInserir(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GrpRemover(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GrpLista(DOMNode*dnode, XMLGen*xml_g);
		int GrpListaId(DOMNode*dnode, XMLGen*xml_g);
		int GrpListaPar(DOMNode*dnode, XMLGen*xml_g);
		int GrpCnlRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GrpCnlRelacao(DOMNode*dnode, XMLGen*xml_g);
		int GrpPrfRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GrpPrfRelacao(DOMNode*dnode, XMLGen*xml_g);
		int GrpImnRelaciona(DOMNode*dnode, XMLGen*xml_g, char* cLogUser);
		int GrpImnRelacao(DOMNode*dnode, XMLGen*xml_g);
		//Retorna a relacao de grupos de um usuario
		int GrupoUsuarioRelacao( char* cidUsuario );
		//Relaciona um grupo a um usuario
		int GrupoUsuarioInsert( char* cidPessoaUsuario
		                       ,char* cidGrupo
		                       ,char* cidUsuario );

		int TemAtendimentosAbertos( char* cidGrupo, 
									char* cidPessoaUsuario );

		int GrupoUsuarioInativoInsert( char* cidPessoaUsuario
									  ,char* cidGrupo
									  ,char* cidUsuario );

		int GrupoUsuarioInativoDelete( char* cidGrupo
									   ,char* cidPessoaUsuario );


		//Procura um grupo de um usuario dentro do iterator (nao vai ao BD)
		int Find( char* cidGrupo );
		//Apaga um registro de usuario grupo dentro do iterator (nao vai ao BD)
		int GrupoUsuarioDelete( char* cidUsuarioGrupo );
		//Insere usuario grupo na estrutura interna
		void GrupoUsuarioInsertFalso( char* cidPessoaUsuario
		                             ,char* cidGrupo );
		void getXml( char* cNomeTag, char* cNomeTagInterna, XMLGen*xml );
		
		int GrpListaTodos( void );
		int GrpListaGrupoSkill( char* pzcidGrupo );

		int GrpListaUsuario( char* pzcidGrupo, int iOperacao, int iPagina, int &iQtdeRegistros );
		int GrpListaUsuario( char* pzcidGrupo, char* pzclogin, int iOperacao, int iPagina, int &iQtdeRegistros );

		int GrpListaUsuarioParcial( char* pzcidGrupo, int iPagina, int &iQtdeRegistros );
		int GrpListaUsuarioParcial( char* pzcidGrupo, char* pzclogin, int iPagina, int &iQtdeRegistros );

		int GrpListaUsuarioTodos( char* pzcidGrupo, int iPagina, int &iQtdeRegistros );
		int GrpListaUsuarioTodos( char* pzcidGrupo, char* pzclogin, int iPagina, int &iQtdeRegistros );

    /*************************************************************************************/
    int GrpListaUsuariosAssociadosSkill( char* pzcidGrupoSkill, int iPagina, int &iQtdeRegistros );
    int GrpListaUsuariosNaoAssociadosSkill( char* pzcidGrupoSkill, int iPagina, int &iQtdeRegistros );
    int GrpListaUsuariosNaoAssociadosSkillPorLogin( char* pzcidGrupoSkill, char* pzclogin, int iPagina, int &iQtdeRegistros );
    
    /*************************************************************************************/
    int GrpListaContatosAssociadosSkill( char* pzcidGrupoSkill, int iPagina, int &iQtdeRegistros );
    int GrpListaContatosNaoAssociadosSkill( char* pzcidGrupoSkill, int iPagina, int &iQtdeRegistros );
  
};

#endif