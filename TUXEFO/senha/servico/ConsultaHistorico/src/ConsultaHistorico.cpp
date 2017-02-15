/* $Id: ConsultaHistorico.cpp,v 1.1 2009/07/31 15:34:23 a5110702 Exp $ */

#include <tuxfw.h>
#include "../include/ConsultaHistorico.h"

extern bool proCConsultaHistoricoUsuario(int idPessoa, char* nrTelefone, XMLGen*xml_g);
extern bool proCConsultaHistoricoCliente(int idPessoa, XMLGen*xml_g);
extern bool proCConsultaNomePessoa(int idPessoa, char* tag, XMLGen*xml_g);
extern bool  procConsultarListaRestritiva(char* telefone);

ConsultaHistorico::ConsultaHistorico()
{

}

void ConsultaHistorico::consultarHistorico(int idPessoa, char* telefone, int tpTitularidade, XMLGen*xml_g)
{

	if (tpTitularidade == 1)
		proCConsultaHistoricoUsuario(idPessoa, telefone, xml_g);
	else
		proCConsultaHistoricoCliente(idPessoa, xml_g);

}

void ConsultaHistorico::consultarNome(int idPessoaUsr, int idPessoaCli, XMLGen*xml_g)
{

	proCConsultaNomePessoa(idPessoaCli, "nmCliente", xml_g);
	proCConsultaNomePessoa(idPessoaUsr, "nmUsuario", xml_g);
	
}


bool ConsultaHistorico::consultarListaRestritiva(char* telefone)
{
	return procConsultarListaRestritiva(telefone);
}