#ifndef CHorarioVeraoH
#define CHorarioVeraoH

#include <tuxfw.h>
#include "../include/CHrrVraItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CHorarioVerao : public CHorarioVeraoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CHorarioVerao();
		~CHorarioVerao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidHorarioVerao );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidUF,
			char* cnrHorarioVerao,
			char* cdtInicio,
			char* cdtFim,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidHorarioVerao,
			char* cidUF,
			char* cnrHorarioVerao,
			char* cdtInicio,
			char* cdtFim,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidHorarioVerao );
};

#endif

