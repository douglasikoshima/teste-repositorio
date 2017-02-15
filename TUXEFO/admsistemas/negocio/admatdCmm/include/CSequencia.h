#ifndef CSequenciaH
#define CSequenciaH

#include <tuxfw.h>
#include "../include/CSequenciaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CSequencia : public CSequenciaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CSequencia();
		~CSequencia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidSequencia );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContatoGrupo,
				char* cidTipoSequencia,
				char* csqOrdem,
				char* cidUsuarioAlteracao 
			);
		//Insere um registro, ( idGrupo nao usado, apenas necessario para pesquisa interna em copia de arvore)
		int Insert( 
				char* cidContatoGrupo,
				char* cidTipoSequencia,
				char* csqOrdem,
				char* cidGrupo,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidSequencia,
				char* cidContatoGrupo,
				char* cidTipoSequencia,
				char* csqOrdem,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidSequencia );
		//Relacao por Contato
		int RelacaoPoridContatoGrupo( char* cidContatoGrupo );
		//Procura por um item internamente na classe (nao busca na base de dados)
		int FindPoridGrupoTipoSequencia( char* cidContatoGrupo, char* cidTipoSequencia );
};

#endif

