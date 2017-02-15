#ifndef CPessoaH
#define CPessoaH

#include <tuxfw.h>
#include "../include/CPssItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CPessoa : public CPessoaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

        /* Atributos para receber os Ids de um determinado IdPessoa */
        /* Estou montando aqui para nao perder tempo em entender o codigo */
	    char cidUnidade[21+1];
	    char cidOrganizacao[21+1];
	    char cidCargo[21+1];
	    char cidNivel[21+1];

        int  iNotFound;
	public:
		CPessoa();
		CPessoa(char*);
		~CPessoa();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Monta o xml com atributos privates da classe
		void GetXmlId( char* cNomeTag, XMLGen*xml );
		//Lista relacionados e existentes
		int RelacaoHUP( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoHUP( char* cidNivelCargo );
		//Lista existentes
		int ExistemHUP( char* cidNivelCargo );
        //Lista existentes
		int ExistemORG( char* cidOrganizacaoDepartamento );

        //Getters
	    char* getUnidade(void);
	    char* getOrganizacao(void);
	    char* getCargo(void);
	    char* getNivel(void);
};

#endif

