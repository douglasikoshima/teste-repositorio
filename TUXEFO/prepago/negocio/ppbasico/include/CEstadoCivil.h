#ifndef CEstadoCivilH
#define CEstadoCivilH

#include <tuxfw.h>
#include <CEstadoCivilItr.h>


class CEstadoCivil : public CEstadoCivilItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CEstadoCivil();
		~CEstadoCivil();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidEstadoCivil );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

