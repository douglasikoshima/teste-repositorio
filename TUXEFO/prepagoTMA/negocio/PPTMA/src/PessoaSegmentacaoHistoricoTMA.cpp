#include "tuxfw.h"
#include "PessoaSegmentacaoHistoricopcTMA.h"
#include "PessoaSegmentacaoHistoricoTMA.h"

CPessoaSegmentacaoHistorico::CPessoaSegmentacaoHistorico(void) {
    memset(&tPessoaSegmentacaoHistorico, 0x00, sizeof(TPessoaSegmentacaoHistorico));
}

void CPessoaSegmentacaoHistorico::setIdPessoaSegmentacao(char *pszIdPessoaSegmentacao) {
    strcpy(tPessoaSegmentacaoHistorico.szIdPessoaSegmentacao, pszIdPessoaSegmentacao);
}

void CPessoaSegmentacaoHistorico::setIdSegmentacao(char *pszIdSegmentacao) {
    strcpy(tPessoaSegmentacaoHistorico.szIdSegmentacao, pszIdSegmentacao);
}

void CPessoaSegmentacaoHistorico::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaSegmentacaoHistorico.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaSegmentacaoHistorico::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao){
    strcpy(tPessoaSegmentacaoHistorico.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}


char *CPessoaSegmentacaoHistorico::getIdSegmentacao(void) {
    static char szAux[LEN_IDSEGMENTACAO + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacaoHistorico.szIdSegmentacao);
    return szAux;
}

char *CPessoaSegmentacaoHistorico::getIdPessoaSegmentacao(void) {
    static char szAux[LEN_IDPESSOASEGMENTACAO + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacaoHistorico.szIdPessoaSegmentacao);
    return szAux;
}

char *CPessoaSegmentacaoHistorico::getIdPessoaDePara(void) {
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaSegmentacaoHistorico.szIdPessoaDePara);
    return szAux;
}


bool CPessoaSegmentacaoHistorico::buscaPessoaSegmentacaoHistorico(void) {
    return clPessoaSegmentacaoHistoricopc.proCBuscaPessoaSegmentacaoHistorico(&tPessoaSegmentacaoHistorico);
}

bool CPessoaSegmentacaoHistorico::buscaPessoaSegmentacaoHistorico(TPessoaSegmentacaoHistorico *ptPessoaSegmentacaoHistoricoAux) {
    return clPessoaSegmentacaoHistoricopc.proCBuscaPessoaSegmentacaoHistorico(ptPessoaSegmentacaoHistoricoAux);
}

void CPessoaSegmentacaoHistorico::inserePessoaSegmentacaoHistorico(void) {
    clPessoaSegmentacaoHistoricopc.proCInserePessoaSegmentacaoHistorico(&tPessoaSegmentacaoHistorico);
}
