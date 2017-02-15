#ifndef CUFH
#define CUFH

#include <tuxfw.h>
#include <CUFItr.h>


class CUF : public CUFItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CUF();
		~CUF();

		//Monta o xml padrao semelhante a tabela
		void getXmlBasico( char* cNomeTag, XMLGen*xml );
		//Mota um xml para listas combos
		void getXmlLista( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidUF );
		//Recupera todos os registros
		int ListAll( void );
};

#endif

