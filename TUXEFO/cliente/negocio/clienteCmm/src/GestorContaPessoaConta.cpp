#include "GestorContaPessoaConta.h"

CGestorContaPessoaConta::CGestorContaPessoaConta(void) {
	memset(&tGestorContaPessoaConta, 0x00, sizeof(TGestorContaPessoaConta));
}

bool CGestorContaPessoaConta::buscaGestorContaPessoaConta(void) {
    return clGestorContaPessoaContapc.proCBuscaGestorContaPessoaConta(&tGestorContaPessoaConta);
}

void CGestorContaPessoaConta::insereGestorContaPessoaConta(void) {
    return clGestorContaPessoaContapc.proCInsereGestorContaPessoaConta(&tGestorContaPessoaConta);
}

void CGestorContaPessoaConta::atualizaGestorContaPessoaConta(void) {
    return clGestorContaPessoaContapc.proCAtualizaGestorContaPessoaConta(&tGestorContaPessoaConta);
}

void CGestorContaPessoaConta::apagaGestorContaPessoaConta(void) {
    return clGestorContaPessoaContapc.proCApagaGestorContaPessoaConta(&tGestorContaPessoaConta);
}


void CGestorContaPessoaConta::clearStruct(void) {
	memset(&tGestorContaPessoaConta, 0x00, sizeof(TGestorContaPessoaConta));
}

void CGestorContaPessoaConta::setIdNrCPF(char *pszIdNrCPF) {
    strcpy(tGestorContaPessoaConta.szIdNrCPF, pszIdNrCPF);
}

void CGestorContaPessoaConta::setIdPessoaConta(char *pszIdPessoaConta) {
    strcpy(tGestorContaPessoaConta.szIdPessoaConta, pszIdPessoaConta);
}

void CGestorContaPessoaConta::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tGestorContaPessoaConta.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}


char *CGestorContaPessoaConta::getIdNrCPF(void) {
    static char szAux[LEN_IDNRCPF + LEN_EOS];

    strcpy(szAux, tGestorContaPessoaConta.szIdNrCPF);
    return szAux;
}

char *CGestorContaPessoaConta::getIdPessoaConta(void) {
    static char szAux[LEN_IDPESSOACONTA + LEN_EOS];

    strcpy(szAux, tGestorContaPessoaConta.szIdPessoaConta);
    return szAux;
}
