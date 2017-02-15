#ifndef CPrazoAtendimentoH
#define CPrazoAtendimentoH

#include <tuxfw.h>
#include "../include/CPrazoAtendimentoItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPrazoAtendimento : public CPrazoAtendimentoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CPrazoAtendimento();
		~CPrazoAtendimento();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidPrazoAtendimento );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cqtHorasPrazoContato,
				char* cidSegmentacao,
				char* cidProcedencia,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidPrazoAtendimento,
				char* cidContato,
				char* cqtHorasPrazoContato,
				char* cidSegmentacao,
				char* cidProcedencia,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidPrazoAtendimento );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
};

#endif

