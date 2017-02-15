/**
 * Essa classe provem uma forma fácil de acesso a arquivos de pares de configuração.
 * 
 * Os arquivos podem conter comentários, desde que a linha seja iniciada pelo caracter '#'.
 * 
 * Todos atributos devem ser separados por um sinal de igualdade '='.
 * 
 */

#ifndef ATRIBUTOH
#define  ATRIBUTOH

#include <stdlib.h>
#include <string.h>

class Atributo
{
	public:

		Atributo(char* _nomeAtributo, char* _valorAtributo);
		~Atributo();

		void setNomeAtributo( char* _nomeAtributo);
		void setValorAtributo( char* _valorAtributo);

		char* getNomeAtributo();
		char* getValorAtributo();

	private:

		char*	nomeAtributo;
		char*	valorAtributo;

};

#endif

