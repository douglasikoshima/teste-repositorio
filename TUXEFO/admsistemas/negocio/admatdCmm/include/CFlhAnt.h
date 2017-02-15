#ifndef CIndicadorContatoFolhaH
#define CIndicadorContatoFolhaH

#include <tuxfw.h>
#include "../include/CFlhAntItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CIndicadorContatoFolha : public CIndicadorContatoFolhaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CIndicadorContatoFolha();
		~CIndicadorContatoFolha();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidIndicadorContatoFolha );
		//Recupera todos os registros ligados a uma baixa
		int ListIdContatoFolha( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Retorna os indicadores relacionados a uma baixa
		int ListIdContatoFolhaRel( char* cidContato );
		//Retorna os indicadores que nao estao relacionados a uma baixa
		int ListIdContatoFolhaNaoRel( char* cidContato );
		//Insere um registro
		int Insert( char* cidContato
			       ,char* cidIndicadorAnatel
			       ,char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidIndicadorContatoFolha );
		//Apaga um ou mais registros
		int EraseCtt( char* cidContato );
		//Apaga um ou mais registros
		int EraseAnt( char* cidIndicadorAnatel );
};

#endif

