#ifndef CValorPossivelH
#define CValorPossivelH

#include <tuxfw.h>
#include <CValorPossivelItr.h>


class CValorPossivel : public CValorPossivelItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CValorPossivel();
		~CValorPossivel();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Monta um XML com id e descricao para combos
		void getXmlCombo( char* cNomeTag,
                          char* cidTag,
                          char* cdsTag,
                          XMLGen* xml );
		//Recupera um registro
		int ListId( char* cidValorPossivel );
		//Recupera todos os registros
		int ListAll( void );
		//Dado um atributo, os valores possiveis daquele atributo
		int ListAtributo( char* cidEscolaridade );
		//Dado um atributo e o valor possivel de um dado atributo e um cliente
		int ListAtributoPorIdPessoa( char* cidAtributo, char* cidPessoa );
		//Recupera somente os registros referentes a escolaridade
		int ListEscolaridade( void );
		//Recupera somente os registros referentes a ocupacao
		int ListOcupacao( void );
		//Recupera a escolaridade de uma pessoa
		int ListEscolaridadePorIdPessoa( char* cidPessoa );
		//Recupera a ocupacao de uma pessoa
		int ListOcupacaoPorIdPessoa( char* cidPessoa );
};

#endif

