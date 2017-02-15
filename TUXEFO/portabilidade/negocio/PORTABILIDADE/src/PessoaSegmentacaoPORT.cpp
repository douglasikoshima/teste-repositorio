#include "tuxfw.h"
#include "PessoaSegmentacaopcPORT.h"
#include "PessoaSegmentacaoPORT.h"

CPessoaSegmentacao::CPessoaSegmentacao(void) {
    memset(&tPessoaSegmentacao, 0x00, sizeof(TPessoaSegmentacao));
}


void CPessoaSegmentacao::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaSegmentacao.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaSegmentacao::setIdPessoaSegmentacao(char *pszIdPessoaSegmentacao) {
    strcpy(tPessoaSegmentacao.szIdPessoaSegmentacao, pszIdPessoaSegmentacao);
}

void CPessoaSegmentacao::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaSegmentacao.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}


char *CPessoaSegmentacao::getIdPessoaSegmentacao(void) {
    static char szAux[LEN_IDPESSOASEGMENTACAO + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacao.szIdPessoaSegmentacao);
    return szAux;
}

char *CPessoaSegmentacao::getIdPessoaDePara(void) {
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacao.szIdPessoaDePara);
    return szAux;
}


bool CPessoaSegmentacao::buscaPessoaSegmentacao(void) {
    return clPessoaSegmentacaopc.proCBuscaPessoaSegmentacao(&tPessoaSegmentacao);
}

bool CPessoaSegmentacao::buscaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacaoAux) {
    return clPessoaSegmentacaopc.proCBuscaPessoaSegmentacao(ptPessoaSegmentacaoAux);
}

void CPessoaSegmentacao::inserePessoaSegmentacao(void) {
    clPessoaSegmentacaopc.proCInserePessoaSegmentacao(&tPessoaSegmentacao);
}

void CPessoaSegmentacao::atualizaPessoaSegmentacao(void) {
    clPessoaSegmentacaopc.proCAtualizaPessoaSegmentacao(&tPessoaSegmentacao);
}
