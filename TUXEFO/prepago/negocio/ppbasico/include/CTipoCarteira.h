#ifndef CTipoCarteiraH
#define CTipoCarteiraH

#include <tuxfw.h>
#include <CTipoCarteiraItr.h>


class CTipoCarteira : public CTipoCarteiraItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoCarteira();
		~CTipoCarteira();

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

