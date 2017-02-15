// ListaContato.cpp: implementation of the ListaContato class.
//
//////////////////////////////////////////////////////////////////////

#include "../include/ListaContato.h"
#include "../include/Util.h"
//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

ListaContato::ListaContato()
{
	idContato[0]=0;
	idTipoContatoSelecionado[0]=0;
	dsContato[0]=0;
	nmContato[0]=0;
	novoCadastro = false;
}

ListaContato::~ListaContato()
{

}
char* ListaContato::getIdContato()
{
	return this->idContato;
}
char* ListaContato::getIdTipoContatoSelecionado()
{
	return this->idTipoContatoSelecionado;
}
char* ListaContato::getDsContato()
{
	return this->dsContato;
}
char* ListaContato::getNmContato()
{
	return this->nmContato;
}
void ListaContato::setIdContato(char*buffer)
{
	Util::strnullcpy(this->idContato,buffer);
}
void ListaContato::setIdTipoContatoSelecionado(char*buffer)
{
	Util::strnullcpy(this->idTipoContatoSelecionado,buffer);
}
void ListaContato::setDsContato(char*buffer)
{
	Util::strnullcpy(this->dsContato,buffer);
}
void ListaContato::setNmContato(char*buffer)
{
	Util::strnullcpy(this->nmContato,buffer);
}

void ListaContato::setNovoCadastro(bool novoCadastro) {
	this->novoCadastro = novoCadastro;
}

bool ListaContato::getNovoCadastro() {
	return this->novoCadastro;
}
