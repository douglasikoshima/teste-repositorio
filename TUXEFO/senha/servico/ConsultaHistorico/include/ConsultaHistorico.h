/* $Id: ConsultaHistorico.h,v 1.1 2009/07/31 15:34:11 a5110702 Exp $ */

#ifndef CONSULTAHISTORICOSENHA
	#define CONSULTAHISTORICOSENHA

#include <tuxfw.h>

class ConsultaHistorico
{

	public:
		ConsultaHistorico();
		void consultarHistorico(int idPessoa, char* telefone, int tpTitularidade, XMLGen* xml_g);
		void consultarNome(int idPessoaUsr, int idPessoaCli, XMLGen*xml_g);
		bool consultarListaRestritiva(char* nrTelefone);

};
 
#endif

