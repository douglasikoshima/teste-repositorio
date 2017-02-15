/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @version $Revision: 1.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/08/27 19:27:29 $
 **/


#ifndef CContatoGrupoRCH
#define CContatoGrupoRCH


#include <tuxfw.h>
#include "../include/CContatoGrupoRCItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CContatoGrupoRC : public CContatoGrupoRCItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoGrupoRC();
		~CContatoGrupoRC();
		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoGrupoRC );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(
					 	 char* cidContato
					 	,char* cidGrupo
					 	,char* cidUser
		);
		//Insere um registro
		int Insert(
					 	 char* cidContato
					 	,char* cidGrupo
					 	,char* cidUser
					 	,int isqOrdem
		);
		
		//Edita um registro
		
		int Update(
					 	 char* cidContatoGrupoRC
					 	,char* cidContato
					 	,char* cidGrupo
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidContatoGrupoRC );
        // charles 
        // resposta ao cliente
        void GetXmlGrupo( char* cNomeTag, XMLGen*xml );
        int ListIdContatoFolhaRel( char* cidContatoGrupoRC );
        int ListIdContatoFolhaNaoRel( char* cidContatoGrupoRC );
        // Recupaera todos os grupos
        int ListAllGrupo( void );

};
#endif