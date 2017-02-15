#ifndef CTipoLinhaH
#define CTipoLinhaH

#include <tuxfw.h>
#include <CTipoLinhaItr.h>


class CTipoLinha : public CTipoLinhaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoLinha();
		~CTipoLinha();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoLinha );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

