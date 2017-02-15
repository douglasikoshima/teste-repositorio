#include "ApoioTipoComunicacao.h"

CApoioTipoComunicacao::CApoioTipoComunicacao(void) {
	memset(&tTipoComunicacao, 0x00, sizeof(TTipoComunicacao));
}

bool CApoioTipoComunicacao::buscaTipoComunicacao(void) {
    return clTipoComunicacaopc.proCBuscaTipoComunicacao(&tTipoComunicacao);
}


void CApoioTipoComunicacao::setSgClassificacao(char *pszSgClassificacao) {
    strcpy(tTipoComunicacao.szSgClassificacao, pszSgClassificacao);
}

void CApoioTipoComunicacao::setSgTipoComunicacao(char *pszSgTipoComunicacao) {
    strcpy(tTipoComunicacao.szSgTipoComunicacao, pszSgTipoComunicacao);
}

void CApoioTipoComunicacao::setDsTipoComunicacao(char *pszDsTipoComunicacao) {
    strcpy(tTipoComunicacao.szDsTipoComunicacao, pszDsTipoComunicacao);
}

char *CApoioTipoComunicacao::getSgTipoComunicacao(void) {
    static char szAux[LEN_SGTIPOCOMUNICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szSgTipoComunicacao);
    return szAux;
}

char *CApoioTipoComunicacao::getIdTipoComunicacao(void) {
    static char szAux[LEN_IDTIPOCOMUNICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szIdTipoComunicacao);
    return szAux;
}

char *CApoioTipoComunicacao::getSgClassificacao(void) {
    static char szAux[LEN_SGCLASSIFICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szSgClassificacao);
    return szAux;
}

char *CApoioTipoComunicacao::getDsTipoComunicacao(void) {
    static char szAux[LEN_DSTIPOCOMUNICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szDsTipoComunicacao);
    return szAux;
}
