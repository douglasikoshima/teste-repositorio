#include "ContatoFuncionalidadePORT.h"

CContatoFuncionalidade::CContatoFuncionalidade(void) {
	memset(&tContatoFuncionalidade, 0x00, sizeof(TContatoFuncionalidade));
}

bool CContatoFuncionalidade::buscaContatoFuncionalidade(void) {
    return clContatoFuncionalidadepc.proCBuscaContatoFuncionalidade(&tContatoFuncionalidade);
}

void CContatoFuncionalidade::setCdFuncionalidade(char *pszCdFuncionalidade) {
    strcpy(tContatoFuncionalidade.szCdFuncionalidade, pszCdFuncionalidade);
}

char *CContatoFuncionalidade::getCdFuncionalidade(void) {
    static char szAux[LEN_CDFUNCIONALIDADE + LEN_EOS];

    strcpy(szAux, tContatoFuncionalidade.szCdFuncionalidade);
    return szAux;
}

char *CContatoFuncionalidade::getIdContato(void) {
    static char szAux[LEN_IDCONTATO + LEN_EOS];

    strcpy(szAux, tContatoFuncionalidade.szIdContato);
    return szAux;
}
