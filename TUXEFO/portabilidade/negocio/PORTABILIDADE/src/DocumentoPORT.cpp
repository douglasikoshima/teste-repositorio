#include "DocumentoPORT.h"

CDocumento::CDocumento(void) {
    memset(&tDocumento, 0x00, sizeof(TDocumento));
}

CDocumento::~CDocumento(void) {
}

void CDocumento::clearStruct(void) {
    memset(&tDocumento, 0x00, sizeof(TDocumento));
}

bool CDocumento::buscaDocumento(void) {
    return(clDocumentopc.proCBuscaDocumento(&tDocumento));
}

bool CDocumento::buscaDocumento(TDocumento *ptDocumento) {
    return(clDocumentopc.proCBuscaDocumento(ptDocumento));
}

bool CDocumento::buscaDocumentoChaveComposta(TDocumento *ptDocumento) {
    return(clDocumentopc.proCBuscaDocumentoChaveComposta(ptDocumento));
}

void CDocumento::insereDocumento(void) {
    clDocumentopc.proCInsereDocumento(&tDocumento);
}

void CDocumento::atualizaDocumento(void) {
    clDocumentopc.proCAtualizaDocumento(tDocumento);
}

char *CDocumento::getIdDocumento(void) {
    static char szAux[LEN_IDDOCUMENTO + LEN_EOS];

    strcpy(szAux, tDocumento.szIdDocumento);
    return szAux;
}

char *CDocumento::getDtEmissao(void) {
    static char szAux[LEN_DTEMISSAO + LEN_EOS];

    strcpy(szAux, tDocumento.szDtEmissao);
    return szAux;
}

char *CDocumento::getSgOrgaoExpedidor(void) {
    static char szAux[LEN_SGORGAOEXPEDIDOR + LEN_EOS];

    strcpy(szAux, tDocumento.szSgOrgaoExpedidor);
    return szAux;
}

char *CDocumento::getIdUF(void) {
    static char szAux[LEN_IDUF + LEN_EOS];

    strcpy(szAux, tDocumento.szIdUF);
    return szAux;
}

char *CDocumento::getNrDocumento(void) {
    static char szAux[LEN_NRDOCUMENTO + LEN_EOS];

    strcpy(szAux, tDocumento.szNrDocumento);
    return szAux;
}

char *CDocumento::getIdTipoDocumento(void) {
    static char szAux[LEN_IDTIPODOCUMENTO + LEN_EOS];

    strcpy(szAux, tDocumento.szIdTipoDocumento);
    return szAux;
}

void CDocumento::setIdPais(char *pszIdPais) {
    strcpy(tDocumento.szIdPais, pszIdPais);
}

void CDocumento::setIdUf(char *pszIdUF) {
    strcpy(tDocumento.szIdUF, pszIdUF);
}

void CDocumento::setNrDocumento(char *pszNrDocumento) {
    strcpy(tDocumento.szNrDocumento, pszNrDocumento);
}

void CDocumento::setIdDocumento(char *pszIdDocumento) {
    strcpy(tDocumento.szIdDocumento, pszIdDocumento);
}

void CDocumento::setIdUF(char *pszIdUF) {
    strcpy(tDocumento.szIdUF, pszIdUF);
}

void CDocumento::setIdTipoDocumento(char *pszIdTipoDocumento) {
    strcpy(tDocumento.szIdTipoDocumento, pszIdTipoDocumento);
}

void CDocumento::setDtEmissao(char *pszDtEmissao) {
    strcpy(tDocumento.szDtEmissao, pszDtEmissao);
}

void CDocumento::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tDocumento.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CDocumento::setSgOrgaoExpedidor(char *pszSgOrgaoExpedidor) {
    strcpy(tDocumento.szSgOrgaoExpedidor, pszSgOrgaoExpedidor);
}

void CDocumento::setStruct(TDocumento *ptDocumento) {
    memcpy(&tDocumento, ptDocumento, sizeof(TDocumento));
    memset(ptDocumento, 0x00, sizeof(TDocumento));
}
