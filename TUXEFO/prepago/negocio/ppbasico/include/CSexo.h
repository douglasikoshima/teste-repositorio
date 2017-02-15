#ifndef CSexoH
#define CSexoH

#include <tuxfw.h>
#include <CSexoItr.h>


class CSexo : public CSexoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CSexo();
		~CSexo();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidSexo );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

