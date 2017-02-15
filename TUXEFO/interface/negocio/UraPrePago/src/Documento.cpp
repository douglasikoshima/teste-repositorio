// Documento.cpp: implementation of the Documento class.
//
//////////////////////////////////////////////////////////////////////

#include "../include/Documento.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

Documento::Documento()
{
	idDocumento[0]=0;
	idTipoDocumentoSelecionado[0]=0;
	nrDocumento[0]=0;
	dtExpedicao[0]=0;
	dsOrgaoEmissor[0]=0;
	idUfSelecionado[0]=0;
}

Documento::~Documento()
{

}

void Documento::setIdDocumento(char*buffer)
{
	Util::strnullcpy(this->idDocumento,buffer);
}
void Documento::setIdTipoDocumentoSelecionado(char*buffer)
{
	Util::strnullcpy(this->idTipoDocumentoSelecionado,buffer);
}
void Documento::setNrDocumento(char*buffer)
{
	Util::strnullcpy(this->nrDocumento,buffer);
}
void Documento::setDtExpedicao(char*buffer)
{
	Util::strnullcpy(this->dtExpedicao,buffer);
}
void Documento::setDsOrgaoEmissor(char*buffer)
{
	Util::strnullcpy(this->dsOrgaoEmissor,buffer);
}
void Documento::setIdUfSelecionado(char*buffer)
{
	Util::strnullcpy(this->idUfSelecionado,buffer);
}
char* Documento::getIdDocumento()
{
	return this->idDocumento;
}
char* Documento::getIdTipoDocumentoSelecionado()
{
	return this->idTipoDocumentoSelecionado;
}
char* Documento::getNrDocumento()
{
	return this->nrDocumento;
}
char* Documento::getDtExpedicao()
{
	return this->dtExpedicao;
}
char* Documento::getDsOrgaoEmissor()
{
	return this->dsOrgaoEmissor;
}
char* Documento::getIdUfSelecionado()
{
	return this->idUfSelecionado;
}
