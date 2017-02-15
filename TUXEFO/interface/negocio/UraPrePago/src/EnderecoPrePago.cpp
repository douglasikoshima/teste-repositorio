// EnderecoPrePago.cpp: implementation of the EnderecoPrePago class.
//
//////////////////////////////////////////////////////////////////////

#include "../include/EnderecoPrePago.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

EnderecoPrePago::EnderecoPrePago()
{
	idEndereco[0] = 0;
	inAtualEndereco[0] = 0;
	idTipoEnderecoSelecionado[0] = 0;
	dsTituloLogradouro[0] = 0;
	dsTipoLogradouro[0] = 0;
	dsLogradouro[0] = 0;
	nrEndereco[0] = 0;
	dsComplementoEndereco[0] = 0;
	dsBairro[0] = 0;
	dsMunicipio[0] = 0;
	nrCEP[0] = 0;
	idUFEndereco[0] = 0;
	dsUFEndereco[0] = 0;
	idPaisEndereco[0] = 0;
	dsPaisEndereco[0] = 0;
	inEnderecoSujo = 0;
}

EnderecoPrePago::~EnderecoPrePago()
{

}

void EnderecoPrePago::setIdEndereco(char*buffer)
{
	Util::strnullcpy(this->idEndereco,buffer);
}
void EnderecoPrePago::setInAtualEndereco(char*buffer)
{
	Util::strnullcpy(this->inAtualEndereco,buffer);
}
void EnderecoPrePago::setIdTipoEnderecoSelecionado(char*buffer)
{
	Util::strnullcpy(this->idTipoEnderecoSelecionado,buffer);
}
void EnderecoPrePago::setDsTituloLogradouro(char*buffer)
{
	Util::strnullcpy(this->dsTituloLogradouro,buffer);
}
void EnderecoPrePago::setDsTipoLogradouro(char*buffer)
{
	Util::strnullcpy(this->dsTipoLogradouro,buffer);
}
void EnderecoPrePago::setDsLogradouro(char*buffer)
{
	Util::strnullcpy(this->dsLogradouro,buffer);
}
void EnderecoPrePago::setNrEndereco(char*buffer)
{
	Util::strnullcpy(this->nrEndereco,buffer);
}
void EnderecoPrePago::setDsComplementoEndereco(char*buffer)
{
	Util::strnullcpy(this->dsComplementoEndereco,buffer);
}
void EnderecoPrePago::setDsBairro(char*buffer)
{
	Util::strnullcpy(this->dsBairro,buffer);
}
void EnderecoPrePago::setDsMunicipio(char*buffer)
{
	Util::strnullcpy(this->dsMunicipio,buffer);
}
void EnderecoPrePago::setNrCEP(char*buffer)
{
	Util::strnullcpy(this->nrCEP,buffer);
}
void EnderecoPrePago::setIdUFEndereco(char*buffer)
{
	Util::strnullcpy(this->idUFEndereco,buffer);
}
void EnderecoPrePago::setDsUFEndereco(char*buffer)
{
	Util::strnullcpy(this->dsUFEndereco,buffer);
}
void EnderecoPrePago::setIdPaisEndereco(char*buffer)
{
	Util::strnullcpy(this->idPaisEndereco,buffer);
}
void EnderecoPrePago::setDsPaisEndereco(char*buffer)
{
	Util::strnullcpy(this->dsPaisEndereco,buffer);
}
void EnderecoPrePago::setInEnderecoSujo(int inEnderecoSujo)
{
	this->inEnderecoSujo = inEnderecoSujo;
}
char* EnderecoPrePago::getIdEndereco()
{
	return this->idEndereco;
}
char* EnderecoPrePago::getInAtualEndereco()
{
	return this->inAtualEndereco;
}
char* EnderecoPrePago::getIdTipoEnderecoSelecionado()
{
	return this->idTipoEnderecoSelecionado;
}
char* EnderecoPrePago::getDsTituloLogradouro()
{
	return this->dsTituloLogradouro;
}
char* EnderecoPrePago::getDsTipoLogradouro()
{
	return this->dsTituloLogradouro;
}
char* EnderecoPrePago::getDsLogradouro()
{
	return this->dsLogradouro;
}
char* EnderecoPrePago::getNrEndereco()
{
	return this->nrEndereco;
}
char* EnderecoPrePago::getDsComplementoEndereco()
{
	return this->dsComplementoEndereco;
}
char* EnderecoPrePago::getDsBairro()
{
	return this->dsBairro;
}
char* EnderecoPrePago::getDsMunicipio()
{
	return this->dsMunicipio;
}
char* EnderecoPrePago::getNrCEP()
{
	return this->nrCEP;
}
char* EnderecoPrePago::getIdUFEndereco()
{
	return this->idUFEndereco;
}
char* EnderecoPrePago::getDsUFEndereco()
{
	return this->dsUFEndereco;
}
char* EnderecoPrePago::getIdPaisEndereco()
{
	return this->idPaisEndereco;
}
char* EnderecoPrePago::getDsPaisEndereco()
{
	return this->dsPaisEndereco;
}
int EnderecoPrePago::getInEnderecoSujo()
{
	return this->inEnderecoSujo;
}
