#ifndef CPrazoAtendimentoH
#define CPrazoAtendimentoH

#include <tuxfw.h>
#include "../include/CFlhPrzItr.h"
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
		//Recupera todos os registros de um contato
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidContato,
			char* cidSegmentacao,
			char* cidProcedencia,
			char* cqtDiasPrazoAtendimento,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidPrazoAtendimento,
			char* cidContato,
			char* cidSegmentacao,
			char* cidProcedencia,
			char* cqtDiasPrazoAtendimento,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidPrazoAtendimento );
		//Apaga um ou mais registros
		int EraseFlh( char* cidContato );
		//Apaga um ou mais registros
		int EraseSgm( char* cidSegmentacao );
		//Apaga um ou mais registros
		int ErasePcd( char* cidProcedencia );
};

#endif

