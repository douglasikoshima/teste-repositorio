#include "GestorContaContato.h"

CGestorContaContato::CGestorContaContato(void) {
	memset(&tGestorContaContato, 0x00, sizeof(TGestorContaContato));
}

bool CGestorContaContato::buscaGestorContaContato(void) {
    return clGestorContaContatopc.proCBuscaGestorContaContato(&tGestorContaContato);
}

void CGestorContaContato::insereGestorContaContato(void) {
    return clGestorContaContatopc.proCInsereGestorContaContato(&tGestorContaContato);
}

void CGestorContaContato::atualizaGestorContaContato(void) {
    return clGestorContaContatopc.proCAtualizaGestorContaContato(&tGestorContaContato);
}

void CGestorContaContato::apagaGestorContaContato(void) {
    return clGestorContaContatopc.proCApagaGestorContaContato(&tGestorContaContato);
}


void CGestorContaContato::clearStruct(void) {
    memset(&tGestorContaContato, 0x00, sizeof(TGestorContaContato));
}

void CGestorContaContato::setIdNrLinha(char *pszIdNrLinha) {
    strcpy(tGestorContaContato.szIdNrLinha, pszIdNrLinha);
}

void CGestorContaContato::setNrRamal(char *pszNrRamal) {
    strcpy(tGestorContaContato.szNrRamal, pszNrRamal);
}

void CGestorContaContato::setIdNrCPF(char *pszIdNrCPF) {
    strcpy(tGestorContaContato.szIdNrCPF, pszIdNrCPF);
}

void CGestorContaContato::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tGestorContaContato.szCdAreaRegistro, pszCdAreaRegistro);
}

void CGestorContaContato::setIdTipoComunicacao(char *pszIdTipoComunicacao) {
    strcpy(tGestorContaContato.szIdTipoComunicacao, pszIdTipoComunicacao);
}

void CGestorContaContato::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tGestorContaContato.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}


char *CGestorContaContato::getIdNrLinha(void) {
    static char szAux[LEN_IDNRLINHA + LEN_EOS];

    strcpy(szAux, tGestorContaContato.szIdNrLinha);
    return szAux;
}

char *CGestorContaContato::getNrRamal(void) {
    static char szAux[LEN_NRRAMAL + LEN_EOS];

    strcpy(szAux, tGestorContaContato.szNrRamal);
    return szAux;
}

char *CGestorContaContato::getIdNrCPF(void) {
    static char szAux[LEN_IDNRCPF + LEN_EOS];

    strcpy(szAux, tGestorContaContato.szIdNrCPF);
    return szAux;
}

char *CGestorContaContato::getCdAreaRegistro(void) {
    static char szAux[LEN_CDAREAREGISTROGC + LEN_EOS];

    strcpy(szAux, tGestorContaContato.szCdAreaRegistro);
    return szAux;
}

char *CGestorContaContato::getIdTipoComunicacao(void) {
    static char szAux[LEN_IDTIPOCOMUNICACAO + LEN_EOS];

    strcpy(szAux, tGestorContaContato.szIdTipoComunicacao);
    return szAux;
}
