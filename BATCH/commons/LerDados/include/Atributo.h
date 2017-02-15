/**
 * Essa classe provem uma forma f�cil de acesso a arquivos de pares de configura��o.
 * 
 * Os arquivos podem conter coment�rios, desde que a linha seja iniciada pelo caracter '#'.
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

