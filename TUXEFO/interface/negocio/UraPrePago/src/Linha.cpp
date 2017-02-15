// Linha.cpp: implementation of the Linha class.
//
//////////////////////////////////////////////////////////////////////

#include "../include/Linha.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

Linha::Linha()
{
	idLinha[0]=0;
	nrLinha[0]=0;
}

Linha::~Linha()
{

}

void Linha::setIdLinha(char*buffer)
{
	Util::strnullcpy(this->idLinha,buffer);
}
void Linha::setNrLinha(char*buffer)
{
	Util::strnullcpy(this->nrLinha,buffer);
}
char* Linha::getIdLinha()
{
	return this->idLinha;
}
char* Linha::getNrLinha()
{
	return this->nrLinha;
}
