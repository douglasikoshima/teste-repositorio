#include <string.h>
#include "../include/Arquivo.hpp"

Arquivo::Arquivo()
{
	log.setNivel(2);
	log.logDebug("inicializando o Arquivo");	
}
Arquivo::~Arquivo()
{
	log.logDebug("finalizando o Arquivo");	
}


void Arquivo::setArquivo(char *nome, char *path) {	

	this->nome = nome;
	this->arquivo =  path;
	this->arquivo += "/";
	this->arquivo += nome;
	
	log.logDebug( (char*) this->arquivo.c_str());	

}

char* Arquivo::getArquivo() {
	log.logDebug("getArquivo");
	return (char*)this->arquivo.c_str();
}

char* Arquivo::getNome() {
	log.logDebug("getNome");
	return (char*)this->nome.c_str();
}

void Arquivo::setPathDescompactado(char *path) {
	int size = this->nome.length() - 4;
	this->descompactado = path;
	this->descompactado += "/";
	this->descompactado.append(nome.c_str(), size);
}

char* Arquivo::getPathDescompactado() {
	return (char*)this->descompactado.c_str();
}