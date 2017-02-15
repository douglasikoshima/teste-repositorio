#ifndef CTipoEnderecoH
#define CTipoEnderecoH

#include <tuxfw.h>
#include <CTipoEnderecoItr.h>


class CTipoEndereco : public CTipoEnderecoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoEndereco();
		~CTipoEndereco();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoEndereco );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

