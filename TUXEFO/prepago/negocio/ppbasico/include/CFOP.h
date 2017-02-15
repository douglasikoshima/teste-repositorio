#ifndef CCFOPHH
#define CCFOPHH

#include <tuxfw.h>
#include <CFOPItr.h>


class CCFOP : public CCFOPItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CCFOP();
		~CCFOP();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoCarteira );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

