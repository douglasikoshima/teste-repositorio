#ifndef CContatoGrupoH
#define CContatoGrupoH

#include <tuxfw.h>
#include "../include/CContatoGrupoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoGrupo : public CContatoGrupoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoGrupo();
		~CContatoGrupo();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoGrupo );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cidGrupo,
				char* cidUsuarioAlteracao 
			);
		//Copia os registros de um contato de origem para um novo de destino
		int InsertCopiaCRI(
							 char* cidContatoOrigem
							,char* cidContatoDestino
							,char* cidUser
						  );
		//Copia os registros de um contato de origem para um novo de destino
		int InsertCopiaRC(
							 char* cidContatoOrigem
							,char* cidContatoDestino
							,char* cidUser
						  );
		//Atualiza um registro
		int Update( 
				char* cidContatoGrupo,
				char* cidContato,
				char* cidGrupo,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidContatoGrupo );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
};

#endif

