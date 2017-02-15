#include "SistemaOrigemTMA.h"

CSistemaOrigem::CSistemaOrigem(void) {
	memset(&tSistemaOrigem, 0x00, sizeof(TSistemaOrigem));
}

bool CSistemaOrigem::buscaSistemaOrigem(void) {
    return clSistemaOrigempc.proCBuscaSistemaOrigem(&tSistemaOrigem);
}

void CSistemaOrigem::setSgSistemaOrigem(char *pszSgSistemaOrigem) {
    strcpy(tSistemaOrigem.szSgSistemaOrigem, pszSgSistemaOrigem);
}

char *CSistemaOrigem::getIdSistemaOrigem(void) {
    static char szAux[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tSistemaOrigem.szIdSistemaOrigem);
    return szAux;
}
