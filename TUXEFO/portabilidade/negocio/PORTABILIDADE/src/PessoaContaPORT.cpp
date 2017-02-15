#include "PessoaContaPORT.h"

CPessoaConta::CPessoaConta(void) {
    memset(&tPessoaConta, 0x00, sizeof(TPessoaConta));
}

void CPessoaConta::atualizaPessoaConta(void) {
    clPessoaContapc.proCAtualizaPessoaConta(tPessoaConta);
}

void CPessoaConta::inserePessoaConta(void) {
    clPessoaContapc.proCInserePessoaConta(&tPessoaConta);
}

bool CPessoaConta::buscaPessoaConta(void) {
    return(clPessoaContapc.proCBuscaPessoaConta(&tPessoaConta));
}

bool CPessoaConta::buscaPessoaConta(TPessoaConta *ptPessoaContaAux) {
    return(clPessoaContapc.proCBuscaPessoaConta(ptPessoaContaAux));
}

void CPessoaConta::apagaPessoaConta(void) {
    clPessoaContapc.proCApagaPessoaConta(tPessoaConta);
}


void CPessoaConta::setIdConta(char *pszIdConta) {
    strcpy(tPessoaConta.szIdConta, pszIdConta);
}

void CPessoaConta::setIdTipoRelacionamento(char *pszIdTipoRelacionamento) {
    strcpy(tPessoaConta.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaConta::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaConta.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaConta::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaConta.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaConta::setDtPessoaConta(char *pszDtPessoaConta) {
    strcpy(tPessoaConta.szDtPessoaConta, pszDtPessoaConta);
}

void CPessoaConta::setIdPessoaConta(char *pszIdPessoaConta) {
    strcpy(tPessoaConta.szIdPessoaConta, pszIdPessoaConta);
}

char *CPessoaConta::getIdPessoaDePara(void) {
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaConta.szIdPessoaDePara);
    return szAux;
}

char *CPessoaConta::getIdPessoaConta(void) {
    static char szAux[LEN_IDPESSOACONTA + LEN_EOS];

    strcpy(szAux, tPessoaConta.szIdPessoaConta);
    return szAux;
}

char *CPessoaConta::getIdConta(void) {
    static char szAux[LEN_IDCONTA + LEN_EOS];

    strcpy(szAux, tPessoaConta.szIdConta);
    return szAux;
}

char *CPessoaConta::getIdTipoRelacionamento(void) {
    static char szAux[LEN_IDTIPORELACIONAMENTO + LEN_EOS];

    strcpy(szAux, tPessoaConta.szIdTipoRelacionamento);
    return szAux;
}
