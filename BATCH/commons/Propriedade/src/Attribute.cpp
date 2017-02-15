/**
 * Implementa as característas de um atributo.
 */

#include "../include/Atributo.h"

Atributo::Atributo(char* _nomeAtributo, char* _valorAtributo)
{

	nomeAtributo  = (char*) malloc( strlen(_nomeAtributo ) + 1 );
	valorAtributo = (char*) malloc( strlen(_valorAtributo) + 1 );

	strcpy(nomeAtributo,  _nomeAtributo);
	strcpy(valorAtributo, _valorAtributo);

}

Atributo::~Atributo()
{
	free(nomeAtributo);
	free(valorAtributo);
}

char* Atributo::getNomeAtributo()
{
	return nomeAtributo;
}

char* Atributo::getValorAtributo()
{
	return valorAtributo;
}

void Atributo::setNomeAtributo(char* _nomeAtributo)
{
	if (nomeAtributo)
		free(nomeAtributo);

	nomeAtributo  = (char*) malloc( strlen( _nomeAtributo ) + 1 );
	strcpy( nomeAtributo,  _nomeAtributo );
}

void Atributo::setValorAtributo(char* _valorAtributo)
{
	if (valorAtributo)
		free(valorAtributo);

	valorAtributo = (char*) malloc( strlen( _valorAtributo ) + 1 );
	strcpy( valorAtributo,  _valorAtributo );
}

