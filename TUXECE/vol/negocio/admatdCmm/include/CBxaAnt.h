#ifndef CIndicadorAnatelBaixaH
#define CIndicadorAnatelBaixaH

#include <tuxfw.h>
#include "../include/CBxaAntItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CIndicadorAnatelBaixa : public CIndicadorAnatelBaixaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CIndicadorAnatelBaixa();
		~CIndicadorAnatelBaixa();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidIndicadorAnatelBaixa );
		//Recupera todos os registros ligados a uma baixa
		int ListIdBaixa( char* cidBaixa );
		//Recupera todos os registros
		int ListAll( void );
		//Retorna os indicadores relacionados a uma baixa
		int ListIdBaixaRel( char* cidBaixa );
		//Retorna os indicadores que nao estao relacionados a uma baixa
		int ListIdBaixaNaoRel( char* cidBaixa );
		//Insere um registro
		int Insert( char* cidBaixa
			       ,char* cidIndicadorAnatel
			       ,char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidIndicadorAnatelBaixa );
		//Apaga um ou mais registros
		int EraseBxa( char* cidBaixa );
		//Apaga um ou mais registros
		int EraseAnt( char* cidIndicadorAnatel );
};

#endif

