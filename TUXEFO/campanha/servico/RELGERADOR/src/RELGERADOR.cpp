#define RELGERADORCPP

#include "../../negocio/CampanhaCmm/include/SRelatorioCampanha.h"
#include "../../negocio/CampanhaCmm/include/CRelatorioCampanha.h"
#include "../../negocio/CampanhaCmm/include/CRelatorioFactory.h"
#include "../../negocio/CampanhaCmm/include/CTipoMotivoCampanha.h"

DECLARE_TUXEDO_SERVICE(RELGERADOR);

void implRELGERADOR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	// Cria o ponteiro Factory do Relat�rio
	CRelatorioCampanha* poRelatorio = NULL;
	char* cIdUser = getUser();
	int   iIdUser = atoi(cIdUser);

	// Log de in�cio de execu��o
	//ulog_start(RELGERADOR);
	try
	{
		// Busca o Relat�rio
		// Checa se existe um relat�rio v�lido
		// Monta XML de retorno
		
		//Eder: Nao entendo porque retorna poRelatorio se nao usa a apaga na sequencia
		//Eder: Isto deve ser melhorado.
		poRelatorio = CRelatorioFactory::getRelatorio( dnode, xml_g, iIdUser );

		//Statuscode de final de execu��o
		setStatusCode("05I0000","Sucesso na execucao do servico!");

		// Destroi o ponteiro Factory do Relat�rio
		delete poRelatorio;
		poRelatorio = NULL;
	}
	catch (...)
	{
		//Statuscode de final de execu��o
		setStatusCode("05E0000","Falha na execucao do servico!");

		// Destroi o ponteiro Factory do Relat�rio
		if (poRelatorio)
			delete poRelatorio;
	}
}
