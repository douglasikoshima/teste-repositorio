#include "PermissaoLinhaPUP.h"

CPermissaoLinhaPUP::CPermissaoLinhaPUP(void) {
    memset(&tPermissaoLinhaPUP, 0x00, sizeof(TPermissaoLinhaPUP));
}

bool CPermissaoLinhaPUP::buscaPermissaoLinhaPUP(void) {
    return(clPermissaoLinhaPUPpc.proCBuscaPermissaoLinhaPUP(&tPermissaoLinhaPUP));
}

bool CPermissaoLinhaPUP::buscaPermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP) {
    return(clPermissaoLinhaPUPpc.proCBuscaPermissaoLinhaPUP(ptPermissaoLinhaPUP));
}

void CPermissaoLinhaPUP::inserePermissaoLinhaPUP(void) {
	clPermissaoLinhaPUPpc.proCInserePermissaoLinhaPUP(&tPermissaoLinhaPUP);
}

void CPermissaoLinhaPUP::apagaPermissaoLinhaPUP(void) {
    clPermissaoLinhaPUPpc.proCApagaPermissaoLinhaPUP(&tPermissaoLinhaPUP);
}


void CPermissaoLinhaPUP::AlteraCadastroLinhaPUP(void) {
    clPermissaoLinhaPUPpc.proCAlteraCadastroLinhaPUP(&tPermissaoLinhaPUP);
}

void CPermissaoLinhaPUP::clearStruct(void) {
    memset(&tPermissaoLinhaPUP, 0x00, sizeof(TPermissaoLinhaPUP));
}

void CPermissaoLinhaPUP::setIdLinhaTelefonica(char *pszIdLinhaTelefonica) {
    strcpy(tPermissaoLinhaPUP.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CPermissaoLinhaPUP::setAlteraCadastroLinhaPUP( char* idLinhaTelefonicaPrm,char* idUsuarioPrm ) {
    strcpy( tPermissaoLinhaPUP.szIdLinhaTelefonica,idLinhaTelefonicaPrm );
    strcpy( tPermissaoLinhaPUP.szIdUsuarioAlteracao,idUsuarioPrm );
}

void CPermissaoLinhaPUP::setSgPermissaoPUP(char *pszSgPermissaoPUP) {
    strcpy(tPermissaoLinhaPUP.szSgPermissaoPUP, pszSgPermissaoPUP);
}

void CPermissaoLinhaPUP::setInAtivo(char *pszInAtivo) {
    strcpy(tPermissaoLinhaPUP.szInAtivo, pszInAtivo);
}

void CPermissaoLinhaPUP::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPermissaoLinhaPUP.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPermissaoLinhaPUP::setDtExpiracao(char *pszDtExpiracao) {
    strcpy(tPermissaoLinhaPUP.szDtExpiracao, pszDtExpiracao);
}

char *CPermissaoLinhaPUP::getIdLinhaTelefonica(void) {
    static char szAux[LEN_IDLINHATELEFONICA + LEN_EOS];

    strcpy(szAux, tPermissaoLinhaPUP.szIdLinhaTelefonica);
    return szAux;
}

char *CPermissaoLinhaPUP::getSgPermissaoPUP(void) {
    static char szAux[LEN_SGPERMISSAOPUP + LEN_EOS];

    strcpy(szAux, tPermissaoLinhaPUP.szSgPermissaoPUP);
    return szAux;
}
