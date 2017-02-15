/*
 * Serviço de Sonda de sistema, ativado via URA
 * Versão inicial, 12/05/2004
 */

#include <time.h>
#include "Sonda.hpp"
#include "../../../negocio/commons/include/Commom.hpp"
#include <tuxfw.h>

// Declarações de funções Pro*C
bool sisDisponivel(char*);
char* getDataBanco();

DECLARE_TUXEDO_SERVICE(SONDA);

void implSONDA::Execute(DOMNode* dnode, XMLGen* xml_g) {

	int iResult = RET_OK;
	int statCom = 1;
	int iAtlys = 0;
	int iPPS = 0;
	int iNGIN = 0;
	
	// Busca quais sistemas estão indisponíveis.
	if (!sisDisponivel("ATY")) {
		iAtlys = 1;
		statCom = 2;
	}

	if (!sisDisponivel("PPS")) {
		iPPS = 1;
		statCom = 2;
	}

	if (!sisDisponivel("NGN")) {
		iNGIN = 1;
		statCom = 2;
	}

	// Seta flag de retorno.
	xml_g->addItem(TAG_STATCOM, statCom);
	if (iAtlys)
		xml_g->addItem(TAG_CD_SIS_INDISP,VL_ATLYS);
	if (iPPS)
		xml_g->addItem(TAG_CD_SIS_INDISP,VL_PPS);
	if (iNGIN)
		xml_g->addItem(TAG_CD_SIS_INDISP,VL_NGIN);

	// Coloca hora e data.
	time_t curr;
	tm local;
	char sData[20];
	time(&curr);
	local=*(localtime(&curr));
	memset(sData, 0, 20);
	sprintf(sData, "%02d/%02d/%d %02d:%02d:%02d", local.tm_mday, local.tm_mon+1, local.tm_year+1900, local.tm_hour, local.tm_min, local.tm_sec);
	xml_g->addItem("dtDataHora", sData);

	setStatusCode("24I0000","Successfully executed");
}
