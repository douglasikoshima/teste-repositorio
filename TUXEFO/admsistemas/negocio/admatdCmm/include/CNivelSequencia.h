#ifndef CNivelSequenciaH
#define CNivelSequenciaH

#include <tuxfw.h>
#include "../include/CNivelSequenciaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CNivelSequencia : public CNivelSequenciaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNivelSequencia();
		~CNivelSequencia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidNivelSequencia );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidSequenciaMandatoria,
				char* cidSequencia,
				char* cnrNivel,
				char* csqOrdem,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidNivelSequencia,
				char* cidSequenciaMandatoria,
				char* cidSequencia,
				char* cnrNivel,
				char* csqOrdem,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidNivelSequencia );
		//Relacao por Contato
		int RelacaoPoridSequenciaMandatoria( char* cidSequenciaMandatoria );
};

#endif

